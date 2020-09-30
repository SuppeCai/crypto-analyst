package me.bl0ckchain.analyst.core.monitoring.indicator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.bl0ckchain.analyst.core.cache.ResultCache;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.util.KlineUtils;
import me.bl0ckchain.sdk.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author caisupeng
 * @version $Id: ResultContainer.java, v 0.1 2019-01-15 1:51 PM caisupeng Exp $$
 */
@Component
public class ResultManager {

    @Autowired
    private ResultCache resultCache;

    public void put(Kline kline, List<Result> results) {
        int timeout = kline.getUnitNum().getValue() * kline.getUnit().getSecond();
        resultCache.put(KlineUtils.genResultKey(kline), results, timeout);
    }

    public Map<String, List<Result>> getByPrefix(Kline kline) {
        Map<String, List<Result>> resultMap = resultCache.mapByPrefix(KlineUtils.genResultPrefix(kline));
        Map<String, List<Result>> map = new HashMap<>(resultMap.size());
        resultMap.forEach((k, v) -> {
            String[] strings = k.split(CacheUtils.SEPARATOR);
            map.put(strings[strings.length - 1], v);
        });
        return map;
    }
}