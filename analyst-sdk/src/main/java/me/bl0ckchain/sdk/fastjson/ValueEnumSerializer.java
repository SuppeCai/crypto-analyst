package me.bl0ckchain.sdk.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 02/07/2018
 */
public class ValueEnumSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {

        SerializeWriter out = serializer.getWriter();
        if (object == null) {
            out.writeNull();
            return;
        }

        out.writeInt(((ValueEnum) object).getValue());
    }
}
