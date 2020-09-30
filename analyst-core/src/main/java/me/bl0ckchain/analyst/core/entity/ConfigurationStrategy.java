package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.sdk.mybatis.entity.UpdatableLongEntity;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
public class ConfigurationStrategy extends UpdatableLongEntity {

    private Long configurationId;

    private Long strategyId;

    public Long getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(Long configurationId) {
        this.configurationId = configurationId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

}