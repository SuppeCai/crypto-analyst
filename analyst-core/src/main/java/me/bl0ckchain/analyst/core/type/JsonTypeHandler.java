package me.bl0ckchain.analyst.core.type;

import me.bl0ckchain.sdk.mybatis.type.BaseJsonTypeHandler;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 03/06/2018
 */
@MappedTypes(value = {List.class, Map.class})
public class JsonTypeHandler<T extends Object> extends BaseJsonTypeHandler<T> {

    public JsonTypeHandler(Class<T> clazz) {
        super(clazz);
    }
}
