package me.bl0ckchain.analyst.core.entity;

import me.bl0ckchain.sdk.mybatis.entity.UpdatableEntity;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
public class Exchange extends UpdatableEntity<Long> {

    private String name;

    private String code;

    private String domain;

    private List<AssetPair> assetPairs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public List<AssetPair> getAssetPairs() {
        return assetPairs;
    }

    public void setAssetPairs(List<AssetPair> assetPairs) {
        this.assetPairs = assetPairs;
    }
}