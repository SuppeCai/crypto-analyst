package me.bl0ckchain.analyst.core.kline;

import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
public enum UnitNumEnum implements ValueEnum {

    /**
     * 1
     */
    ONE(1),
    /***
     * 2
     */
    TWO(2),
    /**
     * 4
     */
    FOUR(4),
    /**
     * 5
     */
    FIVE(5),
    /**
     * 6
     */
    SIX(6),
    /**
     * 12
     */
    TWELVE(12),
    /**
     * 15
     */
    FIFTEEN(15),
    /**
     * 30
     */
    THIRTY(30);

    private final int value;

    UnitNumEnum(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

}
