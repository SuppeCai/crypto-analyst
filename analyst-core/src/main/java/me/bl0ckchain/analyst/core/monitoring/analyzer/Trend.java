package me.bl0ckchain.analyst.core.monitoring.analyzer;

import me.bl0ckchain.analyst.core.monitoring.indicator.Data;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 08/06/2018
 */
public class Trend {

    private TrendEnum trendEnum;

    private List<Data> slopes;

    private int duration;

    public TrendEnum getTrendEnum() {
        return trendEnum;
    }

    public void setTrendEnum(TrendEnum trendEnum) {
        this.trendEnum = trendEnum;
    }

    public List<Data> getSlopes() {
        return slopes;
    }

    public void setSlopes(List<Data> slopes) {
        this.slopes = slopes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
