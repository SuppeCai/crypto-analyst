package me.bl0ckchain.analyst.core.monitoring.indicator;

import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.analyzer.Divergence;
import me.bl0ckchain.analyst.core.monitoring.analyzer.Level;
import me.bl0ckchain.analyst.core.monitoring.analyzer.Trend;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 03/09/2018
 */
public class Result {

    private String name;

    private Indication indication;

    private List<Kline> srcData;

    private List<Data> destData;

    private Trend trend;

    private Level level;

    private Divergence divergence;

    private Kline latestSrcData;

    private Data latestDestData;

    private Kline secondSrcData;

    private Data secondDestData;

    private ResultStatusEnum statusEnum = ResultStatusEnum.NO_RESULT;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Indication getIndication() {
        return indication;
    }

    public void setIndication(Indication indication) {
        this.indication = indication;
    }

    public List<Kline> getSrcData() {
        return srcData;
    }

    public void setSrcData(List<Kline> srcData) {
        this.srcData = srcData;
        if (CollectionUtils.isNotEmpty(srcData)) {
            this.latestSrcData = srcData.get(0);
            if (srcData.size() > 1) {
                this.secondSrcData = srcData.get(1);
            }
        }
    }

    public List<Data> getDestData() {
        return destData;
    }

    public void setDestData(List<Data> destData) {
        this.destData = destData;
        if (CollectionUtils.isNotEmpty(destData)) {
            this.latestDestData = destData.get(0);
            if (destData.size() > 1) {
                this.secondDestData = destData.get(1);
            }
        }
    }

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Kline getLatestSrcData() {
        return latestSrcData;
    }

    public Divergence getDivergence() {
        return divergence;
    }

    public void setDivergence(Divergence divergence) {
        this.divergence = divergence;
    }

    public void setLatestSrcData(Kline latestSrcData) {
        this.latestSrcData = latestSrcData;
    }

    public Data getLatestDestData() {
        return latestDestData;
    }

    public void setLatestDestData(Data latestDestData) {
        this.latestDestData = latestDestData;
    }

    public Kline getSecondSrcData() {
        return secondSrcData;
    }

    public void setSecondSrcData(Kline secondSrcData) {
        this.secondSrcData = secondSrcData;
    }

    public Data getSecondDestData() {
        return secondDestData;
    }

    public void setSecondDestData(Data secondDestData) {
        this.secondDestData = secondDestData;
    }

    public ResultStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(ResultStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
