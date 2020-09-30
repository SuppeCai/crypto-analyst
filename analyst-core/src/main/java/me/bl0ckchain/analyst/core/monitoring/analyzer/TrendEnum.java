package me.bl0ckchain.analyst.core.monitoring.analyzer;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 13/06/2018
 */
public enum TrendEnum {

    RISE("rise"),
    TRANSVERSE("transverse"),
    FALL("fall");

    TrendEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
