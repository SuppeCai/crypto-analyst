package me.bl0ckchain.analyst.core.monitoring.notifier;

import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.entity.Strategy;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 22/06/2018
 */
public class Notice {

    private Exchange exchange;

    private AssetPair assetPair;

    private Period period;

    private Strategy strategy;

    private Long notifyAt;

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

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Long getNotifyAt() {
        return notifyAt;
    }

    public void setNotifyAt(Long notifyAt) {
        this.notifyAt = notifyAt;
    }
}
