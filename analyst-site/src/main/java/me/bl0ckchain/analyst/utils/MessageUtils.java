package me.bl0ckchain.analyst.utils;

import me.bl0ckchain.sdk.utils.CacheUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 09/07/2018
 */
public class MessageUtils {

    public static final String DEFAULT_DOMAIN = "m";

    public static String genSessionKey(String id) {
        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(DEFAULT_DOMAIN);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(id);
        return builder.toString();
    }
}
