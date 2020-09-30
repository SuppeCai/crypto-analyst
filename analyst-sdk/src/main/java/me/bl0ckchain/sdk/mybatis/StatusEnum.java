package me.bl0ckchain.sdk.mybatis;

import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;

/**
 * 对象状态.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 22/05/2018
 */
public enum StatusEnum implements ValueEnum {

    /**
     * 启用
     */
    ENABLED(0),
    /**
     * 审核中
     */
    AUDITING(1),
    /**
     * 禁用
     */
    DISABLED(2),
    /**
     * 删除
     */
    DELETED(3),
    /**
     * 拒绝
     */
    REJECTED(4);

    private final int value;

    StatusEnum(int value) {
        this.value = value;
    }

    /**
     * Get the integer value of this enum value, as defined in the Thrift IDL.
     */
    @Override
    public int getValue() {
        return value;
    }
}