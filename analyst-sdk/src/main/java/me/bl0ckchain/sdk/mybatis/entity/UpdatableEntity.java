package me.bl0ckchain.sdk.mybatis.entity;

/**
 * Base entity with create & update timestamp.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 2016/9/27
 */
public abstract class UpdatableEntity<ID> extends BaseEntity<ID>{

    /**
     * Create timestamp
     */
    private Long createAt;

    /**
     * Update timestamp
     */
    private Long updateAt;

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * Invoked when insert entity.
     */
    @Override
    public void onInsert() {
        long time = System.currentTimeMillis();
        this.setCreateAt(time);
        this.setUpdateAt(time);
    }

    /**
     * Invoked when update entity.
     */
    @Override
    public void onUpdate() {
        this.setUpdateAt(System.currentTimeMillis());
    }

}
