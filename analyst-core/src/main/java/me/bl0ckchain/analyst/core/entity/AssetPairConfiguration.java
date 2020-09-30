package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.sdk.mybatis.entity.UpdatableLongEntity;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
public class AssetPairConfiguration extends UpdatableLongEntity {

    private Long assetPairId;

    private Long configurationId;

    public Long getAssetPairId() {
        return assetPairId;
    }

    public void setAssetPairId(Long assetPairId) {
        this.assetPairId = assetPairId;
    }

    public Long getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(Long configurationId) {
        this.configurationId = configurationId;
    }

}