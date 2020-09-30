package me.bl0ckchain.analyst.core.query;

import me.bl0ckchain.sdk.mybatis.StatusEnum;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 15/07/2018
 */
public class TimeQuery {

    /**
     * Create timestamp
     */
    private Long startAt;

    /**
     * Update timestamp
     */
    private Long endAt;

    private StatusEnum status;

    public Long getStartAt() {
        return startAt;
    }

    public void setStartAt(Long startAt) {
        this.startAt = startAt;
    }

    public Long getEndAt() {
        return endAt;
    }

    public void setEndAt(Long endAt) {
        this.endAt = endAt;
    }

}
