package me.bl0ckchain.analyst.core.monitoring.analyzer;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 10/09/2018
 */
public enum CrossRangeEnum {

    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom");

    CrossRangeEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
