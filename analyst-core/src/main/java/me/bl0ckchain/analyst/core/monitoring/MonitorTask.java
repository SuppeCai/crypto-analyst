package me.bl0ckchain.analyst.core.monitoring;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.googlecode.aviator.AviatorEvaluator;
import me.bl0ckchain.analyst.core.cache.CommonCache;
import me.bl0ckchain.analyst.core.entity.Analysis;
import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.monitoring.indicator.CrossResult;
import me.bl0ckchain.analyst.core.monitoring.indicator.MAResult;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import me.bl0ckchain.analyst.core.monitoring.indicator.ResultManager;
import me.bl0ckchain.analyst.core.monitoring.indicator.ResultStatusEnum;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.analyst.core.monitoring.processor.StrategyProcessor;
import me.bl0ckchain.analyst.core.util.NoticeUtils;
import me.bl0ckchain.analyst.core.util.PeriodUtils;
import me.bl0ckchain.sdk.utils.CacheUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author caisupeng
 * @version $Id: MonitorTask.java, v 0.1 2019-01-09 5:19 PM caisupeng Exp $$
 */
public class MonitorTask implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(MonitorTask.class);

    public static final int MAX_TIMEOUT = 4 * UnitEnum.HOUR.getSecond();
    public static final int HIGH_WEIGHT = 80;

    private static AtomicInteger GENERATE_CONTEXT_COUNT = new AtomicInteger(0);

    private static Queue<Result> RESULT_QUEUE = new ConcurrentLinkedQueue<>();
    private static List<Result> RESULTS = new CopyOnWriteArrayList<>();
    private static Map<String, Object> CONTEXT = new ConcurrentHashMap<>();
    private static Queue<Notice> NOTICE_QUEUE = new ConcurrentLinkedQueue<>();

    private MonitorMetadata metadata;
    private MonitorPhaser phaser;

    public MonitorTask(MonitorMetadata metadata, MonitorPhaser phaser) {
        this.metadata = metadata;
        this.phaser = phaser;
        this.phaser.register();
    }

    @Override
    public void run() {
        Exchange exchange = this.metadata.getExchange();
        AssetPair assetPair = this.metadata.getAssetPair();
        Period period = this.metadata.getPeriod();
        List<Kline> srcData = this.metadata.getSrcData();

        Map<String, Analyzer> analyzerMap = this.metadata.getAnalyzerMap();
        List<Notifier> notifiers = this.metadata.getNotifiers();
        Queue<Indicator> indicatorQueue = this.metadata.getIndicatorQueue();
        Queue<Strategy> strategyQueue = this.metadata.getStrategyQueue();

        CommonCache cache = this.metadata.getCache();
        ResultManager resultManager = this.metadata.getResultManager();
        phaser.arriveAndAwaitAdvance();

        calculate(indicatorQueue, srcData);
        analyze(analyzerMap);
        generateContext(resultManager, srcData.get(0));
        match(exchange, assetPair, period, cache, strategyQueue);
        notify(notifiers);
    }

    private void calculate(Queue<Indicator> indicatorQueue, List<Kline> srcData) {
        Indicator indicator = null;
        try {
            while ((indicator = indicatorQueue.poll()) != null) {
                List<Result> list = indicator.calculate(srcData);
                for (Result result : list) {
                    if (ResultStatusEnum.SUCCESS.equals(result.getStatusEnum())) {
                        result.setIndication(indicator.getIndication());
                        RESULT_QUEUE.add(result);
                        RESULTS.add(result);
                    }
                }
            }
        } catch (Exception e) {
            Kline kline = srcData.get(0);
            logger.error(Thread.currentThread().getName() + " calculate error, " + indicator.getName() +
                    kline.getBaseId() + "/" + kline.getQuoteId() + ": ", e);
        } finally {
            phaser.arriveAndAwaitAdvance();
        }
    }

    private void analyze(Map<String, Analyzer> analyzerMap) {
        try {
            Result result;
            while ((result = RESULT_QUEUE.poll()) != null) {
                Indication indication = result.getIndication();
                List<Analysis> analyses = indication.getAnalyses();
                for (Analysis analysis : analyses) {
                    Analyzer analyzer = analyzerMap.get(analysis.getCode());
                    if (analyzer.support(result)) {
                        analyzer.analyze(result);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(Thread.currentThread().getName() + " analyze error: ", e);
        } finally {
            phaser.arriveAndAwaitAdvance();
        }
    }

    private void generateContext(ResultManager resultManager, Kline kline) {
        try {
            if (GENERATE_CONTEXT_COUNT.getAndIncrement() == 0) {
                if (CollectionUtils.isEmpty(RESULTS)) {
                    return;
                }

                for (Result result : RESULTS) {
                    String key = getResultKey(result);
                    if (StringUtils.isNotBlank(key)) {
                        CONTEXT.put(key, result);
                    }
                    compressResult(result);
                }
                resultManager.put(kline, RESULTS);
                Map<String, List<Result>> resultMap = resultManager.getByPrefix(kline);
                resultMap.forEach((k, v) -> {
                    for (Result result : v) {
                        String key = getResultKey(result);
                        if (StringUtils.isNotBlank(key)) {
                            CONTEXT.put(k + CacheUtils._SEPARATOR + key, result);
                        }
                    }
                });
            }
        } catch (Exception e) {
            logger.error(Thread.currentThread().getName() + " generateContext error: ", e);
        } finally {
            phaser.arriveAndAwaitAdvance();
        }
    }

    private void match(Exchange exchange, AssetPair assetPair, Period period, CommonCache cache,
                       Queue<Strategy> strategyQueue) {
        try {
            if (MapUtils.isEmpty(CONTEXT)) {
                return;
            }
            Strategy strategy;
            while ((strategy = strategyQueue.poll()) != null) {
                String key = NoticeUtils.genCacheKey(exchange.getId(), assetPair.getId(), period, strategy.getId());
                if (cache.exist(key)) {
                    continue;
                }

                Boolean isMatch = false;
                try {
                    isMatch = (Boolean) AviatorEvaluator.execute(strategy.getExpression(), CONTEXT);
                } catch (Exception e) {
                }
                if (isMatch) {
                    StrategyProcessor.replaceDescriptionPlaceholders(strategy, CONTEXT);

                    long now = System.currentTimeMillis();

                    Notice notice = new Notice();
                    notice.setExchange(exchange);
                    notice.setAssetPair(assetPair);
                    notice.setPeriod(period);
                    notice.setStrategy(strategy);
                    notice.setNotifyAt(now);
                    NOTICE_QUEUE.add(notice);

                    long endAt = PeriodUtils.getEndAt(now, period.getUnitNum().getValue(), period.getUnit().getValue());
                    int timeout = (int) (endAt - now / 1000);
                    cache.put(key, CacheUtils.PLACE_HOLDER, strategy.getWeight() >= HIGH_WEIGHT ? MAX_TIMEOUT : timeout);
                }
            }
        } catch (Exception e) {
            logger.error(Thread.currentThread().getName() + " match error: ", e);
        } finally {
            phaser.arriveAndAwaitAdvance();
        }
    }

    private void notify(List<Notifier> notifiers) {
        try {
            Notice notice;
            while ((notice = NOTICE_QUEUE.poll()) != null) {
                for (Notifier notifier : notifiers) {
                    notifier.notice(notice);
                }
            }
        } catch (Exception e) {
            logger.error(Thread.currentThread().getName() + " notify error: ", e);
        } finally {
            if (GENERATE_CONTEXT_COUNT.get() != 0) {
                GENERATE_CONTEXT_COUNT.set(0);
                RESULTS = new CopyOnWriteArrayList<>();
                CONTEXT = new ConcurrentHashMap<>();
            }
            phaser.arriveAndAwaitAdvance();
        }
    }

    private String getResultKey(Result result) {
        String key = result.getName();
        if (StringUtils.isBlank(key)) {
            key = result.getIndication().getCode();
        }

        if (result instanceof MAResult) {
            key += ((MAResult) result).getInterval();
        }
        return key;
    }

    private void compressResult(Result result) {
        result.setSrcData(null);
        result.setDestData(null);
        if (result instanceof CrossResult) {
            CrossResult crossResult = (CrossResult) result;
            crossResult.setFastDestData(null);
            crossResult.setSlowDestData(null);
        }
    }
}