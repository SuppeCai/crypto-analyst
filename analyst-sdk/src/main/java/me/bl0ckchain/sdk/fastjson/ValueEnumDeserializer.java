package me.bl0ckchain.sdk.fastjson;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 24/05/2018
 */
public class ValueEnumDeserializer implements ObjectDeserializer {

    private final Class<? extends ValueEnum> enumClass;

    private final Map<Integer, Enum> valueMap = new HashMap<>();
    private final Map<String, Enum> nameMap = new HashMap<>();


    public ValueEnumDeserializer(Class<? extends ValueEnum> enumClass) {
        this.enumClass = enumClass;

        try {
            Method valueMethod = enumClass.getMethod("values");
            Object[] values = (Object[]) valueMethod.invoke(null);
            for (Object value : values) {
                ValueEnum v = (ValueEnum) value;
                Enum e = (Enum) value;
                valueMap.put(v.getValue(), e);
                nameMap.put(e.name(), e);
            }
        } catch (Exception ex) {
            throw new JSONException("init enum values error, " + enumClass.getName());
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        try {
            Integer intValue = parser.parseObject(int.class);
            return (T) valueMap.get(intValue);
        } catch (JSONException e) {
            throw e;
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }
}
