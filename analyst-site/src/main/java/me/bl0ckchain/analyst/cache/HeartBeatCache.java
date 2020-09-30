package me.bl0ckchain.analyst.cache;

import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.utils.MessageUtils;
import me.bl0ckchain.sdk.cache.RedisCache;
import me.bl0ckchain.sdk.utils.CacheUtils;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 09/07/2018
 */
@Component
public class HeartBeatCache extends RedisCache<String> {


    public void put(String id) {
        put(MessageUtils.genSessionKey(id), CacheUtils.PLACE_HOLDER, UnitEnum.MIN.getSecond());
    }

    @Override
    public boolean exist(String id) {
        return super.exist(MessageUtils.genSessionKey(id));
    }
}
