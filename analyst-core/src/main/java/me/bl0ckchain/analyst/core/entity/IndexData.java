package me.bl0ckchain.analyst.core.entity;

import java.math.BigDecimal;

import me.bl0ckchain.sdk.mybatis.entity.UpdatableEntity;

public class IndexData extends UpdatableEntity<Long> {

    private Long indexId;

    private BigDecimal value;

    private Long date;

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}