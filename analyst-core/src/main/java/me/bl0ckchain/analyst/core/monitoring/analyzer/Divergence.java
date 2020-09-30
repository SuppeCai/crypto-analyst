package me.bl0ckchain.analyst.core.monitoring.analyzer;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 28/09/2018
 */
public class Divergence {

    private DivergenceEnum divergenceEnum;

    private Double scale;

    private Integer interval;

    public DivergenceEnum getDivergenceEnum() {
        return divergenceEnum;
    }

    public void setDivergenceEnum(DivergenceEnum divergenceEnum) {
        this.divergenceEnum = divergenceEnum;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
