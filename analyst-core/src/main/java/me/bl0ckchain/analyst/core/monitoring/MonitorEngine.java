package me.bl0ckchain.analyst.core.monitoring;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import me.bl0ckchain.analyst.core.cache.CommonCache;
import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.Configuration;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.monitoring.indicator.ResultManager;
import me.bl0ckchain.analyst.core.monitoring.processor.AnalyzerProcessor;
import me.bl0ckchain.analyst.core.monitoring.processor.IndicatorProcessor;
import me.bl0ckchain.analyst.core.monitoring.processor.NotifierProcessor;
import me.bl0ckchain.analyst.core.monitoring.processor.StrategyProcessor;
import me.bl0ckchain.analyst.core.repository.PeriodRepo;
import me.bl0ckchain.analyst.core.service.ExchangeService;
import me.bl0ckchain.analyst.core.service.KlineService;
import me.bl0ckchain.analyst.core.service.MonitorService;
import me.bl0ckchain.analyst.core.util.KlineUtils;
import me.bl0ckchain.sdk.mybatis.StatusEnum;
import me.bl0ckchain.sdk.utils.CacheUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 04/06/2018
 */
@Component
public class MonitorEngine implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private int threadNum;

    @Autowired
    private KlineService klineService;
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private IndicatorProcessor indicatorProcessor;
    @Autowired
    private AnalyzerProcessor analyzerProcessor;
    @Autowired
    private StrategyProcessor strategyProcessor;
    @Autowired
    private NotifierProcessor notifierProcessor;
    @Autowired
    private PeriodRepo periodRepo;
    @Autowired
    private CommonCache cache;
    @Autowired
    private ResultManager resultManager;

    private ThreadPoolExecutor executor;

    public void handle(Kline kline) {

        String monitoringKey = KlineUtils.genMonitoringKey(kline);
        if (cache.exist(monitoringKey)) {
            return;
        }

        Exchange exchange = exchangeService.getSimpleExchange(kline.getExchangeId());
        AssetPair assetPair = exchangeService.getAssetPair(kline);
        if (exchange == null || assetPair == null || exchange.getStatus() != StatusEnum.ENABLED) {
            cache.put(monitoringKey, CacheUtils.PLACE_HOLDER, UnitEnum.HOUR.getSecond());
            return;
        }

        List<Configuration> configurations = monitorService.matchConfigurations(kline, assetPair.getConfigurations());
        if (CollectionUtils.isEmpty(configurations)) {
            cache.put(monitoringKey, CacheUtils.PLACE_HOLDER, UnitEnum.HOUR.getSecond());
            return;
        }

        try {
            doMonitor(exchange, assetPair, kline, configurations);
        } catch (Exception e) {
            logger.error("Kline handle error:", e);
        }

        UnitEnum unitEnum = kline.getUnit();
        int times = kline.getUnit().getValue();
        if (unitEnum != UnitEnum.MIN && unitEnum != UnitEnum.HOUR) {
            times *= times;
        }
        cache.put(monitoringKey, CacheUtils.PLACE_HOLDER, UnitEnum.MIN.getSecond() * times + times);
    }

    private void doMonitor(Exchange exchange, AssetPair assetPair, Kline kline, List<Configuration> configurations) {

        Period period = periodRepo.findByKline(kline);
        List<Kline> srcData = klineService.getLatestData(kline);
        if (CollectionUtils.isEmpty(srcData)) {
            return;
        }

        for (Configuration configuration : configurations) {
            List<Indicator> indicators = indicatorProcessor.listIndicators(configuration.getIndications());
            List<Notifier> notifiers = notifierProcessor.getNotifiers(configuration.getNotifications());
            List<Strategy> strategies = configuration.getStrategies();
            strategyProcessor.replaceExpressionPlaceholders(strategies);

            MonitorMetadata metadata = new MonitorMetadata(
                    exchange,
                    assetPair,
                    period,
                    srcData,
                    analyzerProcessor.analyzerMap,
                    notifiers,
                    cache,
                    resultManager,
                    indicators,
                    strategies);

            execute(metadata);
        }
    }

    private synchronized void execute(MonitorMetadata metadata) {
        MonitorPhaser phaser = new MonitorPhaser();
        for (int i = 0; i < this.threadNum; i++) {
            this.executor.submit(new MonitorTask(metadata, phaser));
        }

        while (this.executor.getTaskCount() != this.executor.getCompletedTaskCount()) {
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.threadNum = Runtime.getRuntime().availableProcessors() + 1;
        this.executor = new ThreadPoolExecutor(
                threadNum, threadNum, NumberUtils.LONG_ZERO,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }
}
