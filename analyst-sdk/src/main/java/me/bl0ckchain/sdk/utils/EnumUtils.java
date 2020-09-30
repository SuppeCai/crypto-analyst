package me.bl0ckchain.sdk.utils;

import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 22/05/2018
 */
public class EnumUtils {

    public static <E extends Enum<?> & ValueEnum> E valueOf(Class<E> enumClass, int value) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }

}
