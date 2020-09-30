package me.bl0ckchain.analyst.core.model;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 12/07/2018
 */
public class NoticeDTO {

    private Long assetPairId;

    private String notice;

    private Long notifyAt;

    public Long getAssetPairId() {
        return assetPairId;
    }

    public void setAssetPairId(Long assetPairId) {
        this.assetPairId = assetPairId;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Long getNotifyAt() {
        return notifyAt;
    }

    public void setNotifyAt(Long notifyAt) {
        this.notifyAt = notifyAt;
    }
}
