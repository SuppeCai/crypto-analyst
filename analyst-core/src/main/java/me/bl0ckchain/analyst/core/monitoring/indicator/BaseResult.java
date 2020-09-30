package me.bl0ckchain.analyst.core.monitoring.indicator;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 03/09/2018
 */
public class BaseResult extends Result {

    private double change;

    private double secondChange;

    private double volumeAVG;

    private double secondVolumeAVG;

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getSecondChange() {
        return secondChange;
    }

    public void setSecondChange(double secondChange) {
        this.secondChange = secondChange;
    }

    public double getVolumeAVG() {
        return volumeAVG;
    }

    public void setVolumeAVG(double volumeAVG) {
        this.volumeAVG = volumeAVG;
    }

    public double getSecondVolumeAVG() {
        return secondVolumeAVG;
    }

    public void setSecondVolumeAVG(double secondVolumeAVG) {
        this.secondVolumeAVG = secondVolumeAVG;
    }
}
