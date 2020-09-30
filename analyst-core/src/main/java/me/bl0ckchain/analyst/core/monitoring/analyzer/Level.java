package me.bl0ckchain.analyst.core.monitoring.analyzer;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 08/06/2018
 */
public class Level {

    public Level(Double value, Double difference, Double diffRatio, CrossEnum crossEnum) {
        this.value = value;
        this.difference = difference;
        this.diffRatio = diffRatio;
        this.crossEnum = crossEnum;
    }

    private Double value;

    private Double difference;

    private Double diffRatio;

    private CrossEnum crossEnum;

    private LevelEnum levelEnum;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public Double getDiffRatio() {
        return diffRatio;
    }

    public void setDiffRatio(Double diffRatio) {
        this.diffRatio = diffRatio;
    }

    public CrossEnum getCrossEnum() {
        return crossEnum;
    }

    public void setCrossEnum(CrossEnum crossEnum) {
        this.crossEnum = crossEnum;
    }

    public LevelEnum getLevelEnum() {
        return levelEnum;
    }

    public void setLevelEnum(LevelEnum levelEnum) {
        this.levelEnum = levelEnum;
    }
}
