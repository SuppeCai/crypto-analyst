package me.bl0ckchain.analyst.core.query;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 15/07/2018
 */
public class NoticeLogQuery extends TimeQuery {

    private Long assetPairId;

    public Long getAssetPairId() {
        return assetPairId;
    }

    public void setAssetPairId(Long assetPairId) {
        this.assetPairId = assetPairId;
    }
}
