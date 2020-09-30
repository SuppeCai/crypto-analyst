package me.bl0ckchain.analyst.core.monitoring.util;

import me.bl0ckchain.analyst.core.monitoring.IComponent;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 25/06/2018
 */
public class ComponentUtils {

    public static <T extends IComponent> Map<String, T> toCodeKeyMap(List<T> ts) {

        if (CollectionUtils.isEmpty(ts)) {
            return Collections.EMPTY_MAP;
        }

        Map<String, T> map = new HashMap<>();
        for (T t : ts) {
            if (t != null) {
                map.put(t.getCode(), t);
            }
        }
        return map;
    }
}
