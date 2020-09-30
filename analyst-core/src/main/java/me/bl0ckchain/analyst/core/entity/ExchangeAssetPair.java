package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.sdk.mybatis.entity.UpdatableEntity;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
public class ExchangeAssetPair extends UpdatableEntity<Long> {

    private Long exchangeId;

    private Long assetPairId;

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

}