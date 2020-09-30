package me.bl0ckchain.sdk.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 01/07/2018
 */
public class CacheAssembler implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        Map<String, RedisCache> cacheMap = context.getBeansOfType(RedisCache.class);

        RedisTemplate template = (RedisTemplate) context.getBean("redisTemplate");
        for (RedisCache cache : cacheMap.values()) {
            cache.setRedisTemplate(template);
        }
    }
}
