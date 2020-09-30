package me.bl0ckchain.analyst.core.monitoring.analyzer;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 07/07/2018
 */
public enum LevelEnum {

    SUPPORT("support"),
    RESISTANCE("resistance");

    LevelEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
