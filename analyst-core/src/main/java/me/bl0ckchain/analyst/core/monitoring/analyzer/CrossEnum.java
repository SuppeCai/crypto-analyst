package me.bl0ckchain.analyst.core.monitoring.analyzer;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 22/06/2018
 */
public enum CrossEnum {

    NONE(0, ""),
    GOLDEN(1, "golden cross"),
    DEATH(2, "death cross");

    CrossEnum(int value, String name) {
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
