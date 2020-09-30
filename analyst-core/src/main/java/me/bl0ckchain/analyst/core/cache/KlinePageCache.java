package me.bl0ckchain.analyst.core.cache;

import com.github.pagehelper.PageInfo;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.sdk.cache.RedisCache;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 31/05/2018
 */
@Component
public class KlinePageCache extends RedisCache<PageInfo<Kline>> {

}
