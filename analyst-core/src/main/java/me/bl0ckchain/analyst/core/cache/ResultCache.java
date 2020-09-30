package me.bl0ckchain.analyst.core.cache;

import java.util.List;

import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import me.bl0ckchain.sdk.cache.RedisCache;
import org.springframework.stereotype.Component;

/**
 * @author caisupeng
 * @version $Id: ResultCache.java, v 0.1 2019-01-14 2:10 PM caisupeng Exp $$
 */
@Component
public class ResultCache extends RedisCache<List<Result>> {
}