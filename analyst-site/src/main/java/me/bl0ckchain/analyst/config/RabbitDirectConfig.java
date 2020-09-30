package me.bl0ckchain.analyst.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Configuration
public class RabbitDirectConfig {

    public static final String KLINE_ROUTER_KEY = "kline";
    public static final String MSG_TTL_ARG = "x-message-ttl";
    public static final int DEFAULT_MSG_TTL = 60 * 1000;

    @Bean
    public Queue directQueue() {

        Map<String, Object> args = new HashMap<>(1);
        args.put(MSG_TTL_ARG, DEFAULT_MSG_TTL);
        return new Queue(KLINE_ROUTER_KEY, false, false, true, args);
    }
}
