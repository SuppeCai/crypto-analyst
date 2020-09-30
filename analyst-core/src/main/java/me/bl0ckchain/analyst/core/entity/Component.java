package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.analyst.core.monitoring.ComponentType;
import me.bl0ckchain.sdk.mybatis.entity.UpdatableEntity;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
public class Component extends UpdatableEntity<Long> {

    private Long exchangeId;

    private Long assetPairId;

    private Long periodId;

    private ComponentType componentType;

    private Long componentId;

    private List<ComponentParam> params;

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Long getAssetPairId() {
        return assetPairId;
    }

    public void setAssetPairId(Long assetPairId) {
        this.assetPairId = assetPairId;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public List<ComponentParam> getParams() {
        return params;
    }

    public void setParams(List<ComponentParam> params) {
        this.params = params;
    }
}