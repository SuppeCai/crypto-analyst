package me.bl0ckchain.analyst.core.model;

import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 09/07/2018
 */
public class Message {

    private Type type;

    private Object data;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum Type implements ValueEnum {

        PING(0),
        PONG(1),
        NOTICE(2);

        private final int value;

        Type(int value) {
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
}
