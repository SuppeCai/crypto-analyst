package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.monitoring.indicator.MetaData;
import me.bl0ckchain.sdk.mybatis.entity.UpdatableLongEntity;

import java.math.BigDecimal;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
public class Kline extends UpdatableLongEntity implements MetaData {

    private Long exchangeId;

    private Long baseId;

    private Long quoteId;

    private BigDecimal open;

    private BigDecimal close;

    private BigDecimal low;

    private BigDecimal high;

    private BigDecimal volume;

    private Integer volamount;

    private BigDecimal amount;

    private UnitEnum unit;

    private UnitNumEnum unitNum;

    private Long startAt;

    private Long endAt;

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

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Integer getVolamount() {
        return volamount;
    }

    public void setVolamount(Integer volamount) {
        this.volamount = volamount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    @Override
    public Long getStartAt() {
        return startAt;
    }

    public void setStartAt(Long startAt) {
        this.startAt = startAt;
    }

    @Override
    public Long getEndAt() {
        return endAt;
    }

    public void setEndAt(Long endAt) {
        this.endAt = endAt;
    }

    @Override
    public Double getValue() {
        return this.close.doubleValue();
    }
}