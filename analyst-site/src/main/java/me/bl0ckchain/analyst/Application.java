package me.bl0ckchain.analyst;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.model.Message;
import me.bl0ckchain.analyst.core.monitoring.ComponentType;
import me.bl0ckchain.sdk.fastjson.ValueEnumDeserializer;
import me.bl0ckchain.sdk.fastjson.ValueEnumSerializer;
import me.bl0ckchain.sdk.mybatis.StatusEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SpringBoot Application Class.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 18/05/2018
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SerializeConfig config = SerializeConfig.getGlobalInstance();
        ValueEnumSerializer serializer = new ValueEnumSerializer();
        config.put(StatusEnum.class, serializer);
        config.put(UnitEnum.class, serializer);
        config.put(UnitNumEnum.class, serializer);
        config.put(Message.Type.class, serializer);
        config.put(ComponentType.class, serializer);

        ParserConfig.getGlobalInstance().putDeserializer(StatusEnum.class, new ValueEnumDeserializer(StatusEnum.class));
        ParserConfig.getGlobalInstance().putDeserializer(UnitEnum.class, new ValueEnumDeserializer(UnitEnum.class));
        ParserConfig.getGlobalInstance().putDeserializer(UnitNumEnum.class, new ValueEnumDeserializer(UnitNumEnum.class));
        ParserConfig.getGlobalInstance().putDeserializer(Message.Type.class, new ValueEnumDeserializer(Message.Type.class));
        ParserConfig.getGlobalInstance().putDeserializer(ComponentType.class, new ValueEnumDeserializer(ComponentType.class));

        AviatorEvaluator.setOption(Options.PUT_CAPTURING_GROUPS_INTO_ENV, false);

        SpringApplication.run(Application.class, args);
    }
}
