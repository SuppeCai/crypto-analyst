package me.bl0ckchain.analyst.core.monitoring.indicator;

import me.bl0ckchain.analyst.core.monitoring.analyzer.CrossEnum;
import me.bl0ckchain.analyst.core.monitoring.analyzer.CrossRangeEnum;
import me.bl0ckchain.analyst.core.monitoring.analyzer.Trend;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 31/08/2018
 */
public class CrossResult extends Result {

    private List<Data> fastDestData;

    private List<Data> slowDestData;

    private Data latestFastDestData;

    private Data latestSlowDestData;

    private Trend fastTrend;

    private Trend slowTrend;

    private CrossEnum crossEnum;

    private double top;

    private double bottom;

    private CrossRangeEnum crossRangeEnum;

    private List<Data> crossData;

    private int crossTimes;

    private int rangePeriod;

    public List<Data> getFastDestData() {
        return fastDestData;
    }

    public void setFastDestData(List<Data> fastDestData) {
        this.fastDestData = fastDestData;
        if (CollectionUtils.isNotEmpty(fastDestData)) {
            this.latestFastDestData = fastDestData.get(0);
        }
    }

    public List<Data> getSlowDestData() {
        return slowDestData;
    }

    public void setSlowDestData(List<Data> slowDestData) {
        this.slowDestData = slowDestData;
        if (CollectionUtils.isNotEmpty(slowDestData)) {
            this.latestSlowDestData = slowDestData.get(0);
        }
    }

    public Data getLatestFastDestData() {
        return latestFastDestData;
    }

    public void setLatestFastDestData(Data latestFastDestData) {
        this.latestFastDestData = latestFastDestData;
    }

    public Data getLatestSlowDestData() {
        return latestSlowDestData;
    }

    public void setLatestSlowDestData(Data latestSlowDestData) {
        this.latestSlowDestData = latestSlowDestData;
    }

    public Trend getFastTrend() {
        return fastTrend;
    }

    public void setFastTrend(Trend fastTrend) {
        this.fastTrend = fastTrend;
    }

    public Trend getSlowTrend() {
        return slowTrend;
    }

    public void setSlowTrend(Trend slowTrend) {
        this.slowTrend = slowTrend;
    }

    public CrossEnum getCrossEnum() {
        return crossEnum;
    }

    public void setCrossEnum(CrossEnum crossEnum) {
        this.crossEnum = crossEnum;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public CrossRangeEnum getCrossRangeEnum() {
        return crossRangeEnum;
    }

    public void setCrossRangeEnum(CrossRangeEnum crossRangeEnum) {
        this.crossRangeEnum = crossRangeEnum;
    }

    public List<Data> getCrossData() {
        return crossData;
    }

    public void setCrossData(List<Data> crossData) {
        this.crossData = crossData;
    }

    public int getCrossTimes() {
        return crossTimes;
    }

    public void setCrossTimes(int crossTimes) {
        this.crossTimes = crossTimes;
    }

    public int getRangePeriod() {
        return rangePeriod;
    }

    public void setRangePeriod(int rangePeriod) {
        this.rangePeriod = rangePeriod;
    }
}
