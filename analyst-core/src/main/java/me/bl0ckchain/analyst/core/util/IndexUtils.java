package me.bl0ckchain.analyst.core.util;

import me.bl0ckchain.sdk.utils.CacheUtils;

/**
 * @author caisupeng
 * @version $Id: IndexUtils.java, v 0.1 2019-04-09 4:24 PM caisupeng Exp $$
 */
public class IndexUtils {

    public static final String INDEX_PREFIX = "i";

    public static String genKeyPrefix(Long id) {
        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(INDEX_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(id);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.ASTERISK);
        return builder.toString();
    }

    public static String genKey(Long id, Long date) {
        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(INDEX_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(id);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(date);
        return builder.toString();
    }
}