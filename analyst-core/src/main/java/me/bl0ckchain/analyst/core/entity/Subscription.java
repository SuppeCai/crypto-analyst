package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.sdk.mybatis.entity.UpdatableLongEntity;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
public class Subscription extends UpdatableLongEntity {

    private Long assetPairId;

    private Long indicatorId;

    private UnitEnum unit;

    private UnitNumEnum unitNum;

    private AssetPair assetPair;

    private Indication indication;

    public Long getAssetPairId() {
        return assetPairId;
    }

    public void setAssetPairId(Long assetPairId) {
        this.assetPairId = assetPairId;
    }

    public Long getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Long indicatorId) {
        this.indicatorId = indicatorId;
    }

    public UnitEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }

    public UnitNumEnum getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(UnitNumEnum unitNum) {
        this.unitNum = unitNum;
    }

    public AssetPair getAssetPair() {
        return assetPair;
    }

    public void setAssetPair(AssetPair assetPair) {
        this.assetPair = assetPair;
    }

    public Indication getIndication() {
        return indication;
    }

    public void setIndication(Indication indication) {
        this.indication = indication;
    }
}