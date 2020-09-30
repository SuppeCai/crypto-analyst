package me.bl0ckchain.sdk.mybatis.entity;

import com.alibaba.fastjson.annotation.JSONField;
import me.bl0ckchain.sdk.mybatis.StatusEnum;
import me.bl0ckchain.sdk.mybatis.id.Identifiable;

import java.io.Serializable;

/**
 * Base entity, define base properties.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 16/2/22
 */
public abstract class BaseEntity<ID> implements Identifiable<ID>, Serializable {

    /**
     * Primary key of the entity.
     */
    protected ID id;

    /**
     * Status of the entity.
     */
    protected StatusEnum status = StatusEnum.ENABLED;

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

    @JSONField(serialize = false)
    public boolean isNew() {

        if (this.id == null) {
            return true;
        }
        if (this.id instanceof Long && (Long) this.id == 0) {
            return true;
        }
        if (this.id instanceof Integer && (Integer) this.id == 0) {
            return true;
        }
        if (this.id instanceof String && this.id.equals("")) {
            return true;
        }
        return false;
    }

    public void init() {
    }

    public void onInsert() {
    }

    public void onUpdate() {
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
