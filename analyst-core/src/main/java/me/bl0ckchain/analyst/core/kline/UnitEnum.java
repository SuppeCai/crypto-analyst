package me.bl0ckchain.analyst.core.kline;

import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
public enum UnitEnum implements ValueEnum {

    /**
     * minute, 1
     */
    MIN(1, 60, "min"),
    /**
     * hour, 2
     */
    HOUR(2, 60 * 60, "hour"),
    /**
     * day, 3
     */
    DAY(3, 24 * 60 * 60, "day"),
    /**
     * week, 4
     */
    WEEK(4, 7 * 24 * 60 * 60, "week");

    private final int value;
    private final int second;
    private final String name;

    UnitEnum(int value, int second, String name) {
        this.value = value;
        this.second = second;
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    public int getSecond() {
        return second;
    }

    public String getName() {
        return name;
    }
}


