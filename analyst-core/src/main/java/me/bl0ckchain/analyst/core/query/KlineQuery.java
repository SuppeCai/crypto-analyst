package me.bl0ckchain.analyst.core.query;

import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 29/05/2018
 */
public class KlineQuery {

    private Long exchangeId;

    private Long baseId;

    private Long quoteId;

    private UnitEnum unit;

    private UnitNumEnum unitNum;

    private Long startAt;

    private Long endAt;

    private int page;

    private int size;

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }

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

    public UnitEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }

    public UnitNumEnum getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(UnitNumEnum unitNum) {
        this.unitNum = unitNum;
    }

    public Long getStartAt() {
        return startAt;
    }

    public void setStartAt(Long startAt) {
        this.startAt = startAt;
    }

    public Long getEndAt() {
        return endAt;
    }

    public void setEndAt(Long endAt) {
        this.endAt = endAt;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
