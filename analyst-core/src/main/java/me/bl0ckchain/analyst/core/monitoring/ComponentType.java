package me.bl0ckchain.analyst.core.monitoring;

import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 28/07/2018
 */
public enum ComponentType implements ValueEnum {

    ANALYSIS(0),
    INDICATION(1),
    STRATEGY(2),
    NOTIFICATION(3);

    private final int value;

    ComponentType(int value) {
        this.value = value;
    }

    /**
     * Get the integer value of this enum value, as defined in the Thrift IDL.
     */
    @Override
    public int getValue() {
        return value;
    }
}
