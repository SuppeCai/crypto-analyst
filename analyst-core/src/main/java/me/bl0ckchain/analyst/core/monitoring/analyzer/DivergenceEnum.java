package me.bl0ckchain.analyst.core.monitoring.analyzer;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 28/09/2018
 */
public enum DivergenceEnum {

    NONE(0, "none"),
    POSITIVE(1, "positive"),
    NEGATIVE(2, "negative");

    DivergenceEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
