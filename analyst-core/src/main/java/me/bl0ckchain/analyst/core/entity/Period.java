package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.sdk.mybatis.entity.UpdatableEntity;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
public class Period extends UpdatableEntity<Long> {

    private UnitNumEnum unitNum;

    private UnitEnum unit;

    public UnitNumEnum getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(UnitNumEnum unitNum) {
        this.unitNum = unitNum;
    }

    public UnitEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }

}