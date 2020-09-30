package me.bl0ckchain.analyst.core.monitoring;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import me.bl0ckchain.analyst.core.cache.CommonCache;
import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.monitoring.indicator.ResultManager;

/**
 * @author caisupeng
 * @version $Id: MonitorMetadata.java, v 0.1 2019-01-10 10:35 AM caisupeng Exp $$
 */
public class MonitorMetadata {
    private Exchange exchange;
    private AssetPair assetPair;
    private Period period;

    private List<Kline> srcData;
    private Map<String, Analyzer> analyzerMap;
    private List<Notifier> notifiers;
    private CommonCache cache;
    private ResultManager resultManager;

    private Queue<Indicator> indicatorQueue = new ConcurrentLinkedQueue<>();
    private Queue<Strategy> strategyQueue = new ConcurrentLinkedQueue<>();

    public MonitorMetadata(Exchange exchange, AssetPair assetPair, Period period, List<Kline> srcData,
                           Map<String, Analyzer> analyzerMap,
                           List<Notifier> notifiers,
                           CommonCache cache,
                           ResultManager resultManager,
                           List<Indicator> indicators,
                           List<Strategy> strategies) {
        this.exchange = exchange;
        this.assetPair = assetPair;
        this.period = period;
        this.srcData = srcData;
        this.analyzerMap = analyzerMap;
        this.notifiers = notifiers;
        this.cache = cache;
        this.resultManager = resultManager;
        this.indicatorQueue.addAll(indicators);
        this.strategyQueue.addAll(strategies);
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public AssetPair getAssetPair() {
        return assetPair;
    }

    public void setAssetPair(AssetPair assetPair) {
        this.assetPair = assetPair;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public List<Kline> getSrcData() {
        return srcData;
    }

    public void setSrcData(List<Kline> srcData) {
        this.srcData = srcData;
    }

    public Map<String, Analyzer> getAnalyzerMap() {
        return analyzerMap;
    }

    public List<Notifier> getNotifiers() {
        return notifiers;
    }

    public CommonCache getCache() {
        return cache;
    }

    public void setCache(CommonCache cache) {
        this.cache = cache;
    }

    public ResultManager getResultManager() {
        return resultManager;
    }

    public Queue<Indicator> getIndicatorQueue() {
        return indicatorQueue;
    }

    public Queue<Strategy> getStrategyQueue() {
        return strategyQueue;
    }
}