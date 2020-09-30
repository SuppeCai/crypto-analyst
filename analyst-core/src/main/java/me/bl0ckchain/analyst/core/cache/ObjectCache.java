package me.bl0ckchain.analyst.core.cache;

import com.alibaba.fastjson.JSONObject;
import me.bl0ckchain.sdk.cache.RedisCache;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 04/07/2018
 */
@Component
public class ObjectCache extends RedisCache<JSONObject> {

}
