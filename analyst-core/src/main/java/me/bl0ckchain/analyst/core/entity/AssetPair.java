package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.sdk.mybatis.entity.UpdatableEntity;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */


public class AssetPair extends UpdatableEntity<Long> {

    private Long baseId;

    private Long quoteId;

    private Asset base;

    private Asset quote;

    private List<Configuration> configurations;

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public Asset getBase() {
        return base;
    }

    public void setBase(Asset base) {
        this.base = base;
    }

    public Asset getQuote() {
        return quote;
    }

    public void setQuote(Asset quote) {
        this.quote = quote;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
}