package me.bl0ckchain.sdk.mybatis.entity;


import me.bl0ckchain.sdk.mybatis.id.IdGenerator;
import me.bl0ckchain.sdk.snowflake.UUID;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 2016/9/27
 */
public class UpdatableLongEntity extends UpdatableEntity<Long> implements IdGenerator<Long> {

    /**
     * Generate long key.
     *
     * @return
     */
    @Override
    public Long generateID() {
        return UUID.next();
    }

    /**
     * Assign id when insert entity .
     */
    @Override
    public void init() {
        this.id = generateID();
    }
}
