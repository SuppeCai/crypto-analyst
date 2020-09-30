package me.bl0ckchain.sdk.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 16/6/27
 */
public class ReflectionUtils {

    public static <T> Class<T> getGenericClass(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return params.length > index - 1 ? (Class) params[index] : null;
    }

}
