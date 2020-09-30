package me.bl0ckchain.sdk.utils;

import me.bl0ckchain.sdk.mybatis.id.Identifiable;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 01/07/2018
 */
public class CacheUtils {

    public static final String PLACE_HOLDER = "$";
    public static final String DEFAULT_PREFIX = "a";
    public static final String DEFAULT_ENTITY_PREFIX = "e";
    public static final String DEFAULT_RESULT_PREFIX = "r";
    public static final String SEPARATOR = ":";
    public static final String _SEPARATOR = "_";

    public static final String NUMBER_SIGN = "#";
    public static final String ASTERISK = "*";

    public static <T extends Identifiable> String genKey(T t) {

        if (t == null) {
            return StringUtils.EMPTY;
        }
        return genKey(t.getClass(), t.getId());
    }

    public static <T, ID> String genKey(Class<T> clazz, ID id) {

        if (clazz == null || id == null) {
            return StringUtils.EMPTY;
        }

        StringBuilder builder = getPrefix(clazz.getSimpleName());
        builder.append(SEPARATOR);
        builder.append(id);
        return builder.toString();
    }

    public static <T> String genKeyPrefix(Class<T> clazz) {

        if (clazz == null) {
            return StringUtils.EMPTY;
        }

        StringBuilder builder = getPrefix(clazz.getSimpleName());
        builder.append(ASTERISK);
        return builder.toString();
    }

    private static StringBuilder getPrefix(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(DEFAULT_PREFIX);
        builder.append(SEPARATOR);
        builder.append(DEFAULT_ENTITY_PREFIX);
        builder.append(SEPARATOR);
        builder.append(name);
        return builder;
    }

    public static <T extends Identifiable, ID> List<String> genKeyList(Class<T> clazz, List<ID> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            return Collections.EMPTY_LIST;
        }

        List<String> keys = new ArrayList<>();
        for (ID id : ids) {
            keys.add(genKey(clazz, id));
        }
        return keys;
    }
}
