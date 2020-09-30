package me.bl0ckchain.analyst.core.model;

import java.math.BigDecimal;

import me.bl0ckchain.sdk.fastjson.Json;

/**
 * @author caisupeng
 * @version $Id: IndexDataDTO.java, v 0.1 2019-04-09 4:04 PM caisupeng Exp $$
 */
@Json
public class IndexDataDTO {

    private Long indexId;

    private BigDecimal value;

    private Long date;

    private Boolean isSaved;

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

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean saved) {
        isSaved = saved;
    }
}