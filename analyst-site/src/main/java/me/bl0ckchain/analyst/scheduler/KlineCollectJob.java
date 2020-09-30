package me.bl0ckchain.analyst.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.bl0ckchain.analyst.core.cache.ObjectCache;
import me.bl0ckchain.analyst.core.cache.KlinePageCache;
import me.bl0ckchain.analyst.core.converter.KlineConverter;
import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.model.KlineDTO;
import me.bl0ckchain.analyst.core.service.ExchangeService;
import me.bl0ckchain.analyst.core.service.KlineService;
import me.bl0ckchain.analyst.core.util.KlineUtils;
import me.bl0ckchain.analyst.core.util.PeriodUtils;
import me.bl0ckchain.sdk.scheduler.JobCron;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 24/05/2018
 */
@JobCron(cron = "5 */1 * * * ?")
public class KlineCollectJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(KlineCollectJob.class);

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private KlineService klineService;
    @Autowired
    private KlineConverter klineConverter;
    @Autowired
    private KlinePageCache pageCache;
    @Autowired
    private ObjectCache cache;
    /**
     * Last end period of each unit and unit number.
     */
    private Map<UnitEnum, Map<UnitNumEnum, Long>> lastMap;

    @PostConstruct
    public void init() {

        this.lastMap = new HashMap<>(5);
        UnitEnum[] enums = UnitEnum.values();
        for (UnitEnum e : enums) {
            Map<UnitNumEnum, Long> endAtMap = new HashMap<>(5);
            switch (e) {
                case MIN:
                    endAtMap.put(UnitNumEnum.ONE, 0L);
                    endAtMap.put(UnitNumEnum.FIVE, 0L);
                    endAtMap.put(UnitNumEnum.FIFTEEN, 0L);
                    endAtMap.put(UnitNumEnum.THIRTY, 0L);
                    break;
                case HOUR:
                    endAtMap.put(UnitNumEnum.ONE, 0L);
                    endAtMap.put(UnitNumEnum.TWO, 0L);
                    endAtMap.put(UnitNumEnum.FOUR, 0L);
                    endAtMap.put(UnitNumEnum.SIX, 0L);
                    endAtMap.put(UnitNumEnum.TWELVE, 0L);
                    break;
                case DAY:
                    endAtMap.put(UnitNumEnum.ONE, 0L);
                    break;
                case WEEK:
                    endAtMap.put(UnitNumEnum.ONE, 0L);
                    break;
                default:
                    break;
            }
            this.lastMap.put(e, endAtMap);
        }
    }

    @Override
    public void execute(JobExecutionContext context) {

        try {
            List<Exchange> exchanges = exchangeService.listExchanges();

            if (CollectionUtils.isEmpty(exchanges)) {
                logger.error("no exchange to be monitored.");
                return;
            }

            long now = System.currentTimeMillis();
            UnitEnum[] enums = UnitEnum.values();
            for (UnitEnum e : enums) {
                Map<UnitNumEnum, Long> endAtMap = lastMap.get(e);

                for (Map.Entry<UnitNumEnum, Long> entry : endAtMap.entrySet()) {
                    UnitNumEnum unitNum = entry.getKey();
                    if (e == UnitEnum.MIN && unitNum == UnitNumEnum.ONE) {
                        continue;
                    }
                    Long lastEndAt = entry.getValue();
                    Long newEndAt = PeriodUtils.getEndAt(now, unitNum.getValue(), e.getValue());

                    if (lastEndAt < newEndAt) {
                        saveData(exchanges, e, unitNum, newEndAt);
                        endAtMap.put(unitNum, newEndAt);
                    }
                }

            }
        } catch (Exception e) {
            logger.error("kline task error:", e);
            throw e;
        }
    }

    private void saveData(List<Exchange> exchanges, UnitEnum unit, UnitNumEnum unitNum, Long newEndAt) {


        for (Exchange exchange : exchanges) {
            List<AssetPair> assetPairs = exchange.getAssetPairs();
            if (CollectionUtils.isEmpty(assetPairs)) {
                continue;
            }

            for (AssetPair assetPair : assetPairs) {
                List<JSONObject> list = cache.getByPrefix(KlineUtils.genKeyPrefix(exchange, assetPair, unit, unitNum));
                if (CollectionUtils.isEmpty(list)) {
                    continue;
                }

                for (JSONObject item : list) {
                    KlineDTO dto = JSON.toJavaObject(item, KlineDTO.class);
                    if (!dto.getIsSaved() && dto.getEndAt() < newEndAt) {
                        try {
                            Kline kline = klineConverter.to(dto);
                            if (klineService.saveIfAbsent(kline)) {
                                pageCache.removeByPrefix(KlineUtils.genPagePrefix(kline));
                            }
                            dto.setIsSaved(true);
                            cache.put(KlineUtils.genKey(dto), JSON.parseObject(JSON.toJSONString(dto)), 10 * KlineUtils.getInterval(kline));
                        } catch (Exception e) {
                            logger.error("saveIfAbsent kline data error:", e);
                            logger.error("dto:" + JSON.toJSONString(dto) + " kline:" + JSON.toJSONString(klineConverter.to(dto)));
                        }
                    }
                }
            }
        }
    }
}
