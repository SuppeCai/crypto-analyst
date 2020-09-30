package me.bl0ckchain.sdk.utils;

import org.springframework.util.PropertyPlaceholderHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 08/10/2018
 */
public class PlaceholderUtils {

    private static final String DEFAULT_PREFIX = "{";
    private static final String DEFAULT_SUFFIX = "}";

    private static final PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper(DEFAULT_PREFIX, DEFAULT_SUFFIX);

    public static List<String> findAllPlaceholders(String value) {

        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder(value);
        while (builder.indexOf(DEFAULT_PREFIX) >= 0 && builder.indexOf(DEFAULT_SUFFIX) >= 0) {
            int prefixIndex = builder.indexOf(DEFAULT_PREFIX);
            int suffixIndex = builder.indexOf(DEFAULT_SUFFIX);
            if (prefixIndex > suffixIndex) {
                throw new IllegalArgumentException("Can not pair placeholder's prefix and suffix");
            }
            String placeholder = builder.substring(prefixIndex + 1, suffixIndex);
            list.add(placeholder);
            builder = builder.delete(prefixIndex, suffixIndex + 1);
        }
        return list;
    }

    public static String replacePlaceholders(String value, Properties properties) {
        return propertyPlaceholderHelper.replacePlaceholders(value, properties);
    }
}
