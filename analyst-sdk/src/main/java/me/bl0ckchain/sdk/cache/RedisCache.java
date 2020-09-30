package me.bl0ckchain.sdk.cache;

import com.alibaba.fastjson.parser.ParserConfig;
import me.bl0ckchain.sdk.utils.ClassUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 31/05/2018
 */
public class RedisCache<V> implements Cache<String, V> {

    protected static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    public static final int DEFAULT_TIMEOUT = 4 * 60 * 60;

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    protected Class<V> vClass;

    protected RedisTemplate<String, V> template;

    public RedisCache() {
        this.vClass = (Class<V>) ClassUtils.getGenericClass(this.getClass());
    }

    public RedisCache(Class<V> clazz) {
        this.vClass = clazz;
    }

    @Override
    public V get(String k) {
        try {
            if (template.hasKey(k)) {
                return template.opsForValue().get(k);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("redis cache get error:", e);
            throw e;
        }
    }

    @Override
    public Map<String, V> mget(List<String> keys) {

        if (CollectionUtils.isEmpty(keys)) {
            return Collections.EMPTY_MAP;
        }

        try {
            Map<String, V> map = null;
            List<V> values = template.opsForValue().multiGet(keys);
            if (CollectionUtils.isNotEmpty(values)) {
                map = new HashMap<>();
                for (int i = 0; i < keys.size(); i++) {
                    V v = values.get(i);
                    if (v != null) {
                        map.put(keys.get(i), v);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            logger.error("redis cache mget error:", e);
            throw e;
        }
    }

    @Override
    public List<V> getByPrefix(String prefix) {
        try {
            List<V> values = null;
            Set<String> keys = template.keys(prefix);
            if (CollectionUtils.isNotEmpty(keys)) {
                values = template.opsForValue().multiGet(keys);
            }
            if (CollectionUtils.isNotEmpty(values)) {
                Iterator<V> iterator = values.iterator();
                while (iterator.hasNext()) {
                    V v = iterator.next();
                    if (v == null) {
                        iterator.remove();
                    }
                }
            }
            return values;
        } catch (Exception e) {
            logger.error("redis cache get error:", e);
            throw e;
        }
    }

    @Override
    public Map<String, V> mapByPrefix(String prefix) {
        try {
            Map<String, V> map = null;
            List<V> values = null;
            Set<String> keys = template.keys(prefix);
            if (CollectionUtils.isNotEmpty(keys)) {
                values = template.opsForValue().multiGet(keys);
            }
            if (CollectionUtils.isNotEmpty(values)) {
                map = new HashMap<>();
                Iterator<String> kIterator = keys.iterator();
                Iterator<V> iterator = values.iterator();
                while (iterator.hasNext()) {
                    V v = iterator.next();
                    if (v == null) {
                        iterator.remove();
                    }
                    String k = kIterator.next();
                    map.put(k, v);
                }
            }
            return map;
        } catch (Exception e) {
            logger.error("redis cache map error:", e);
            throw e;
        }
    }

    @Override
    public void put(String k, V v) {

        put(k, v, DEFAULT_TIMEOUT);
    }

    @Override
    public void put(String k, V v, int timeout) {

        if (v == null) {
            return;
        }

        try {
            template.opsForValue().set(k, v, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("redis cache put error:", e);
            throw e;
        }
    }

    @Override
    public void mput(Map<String, V> map) {

        if (MapUtils.isEmpty(map)) {
            return;
        }

        try {
            template.opsForValue().multiSet(map);
            for (String key : map.keySet()) {
                template.expire(key, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            logger.error("redis cache mput error:", e);
            throw e;
        }
    }

    @Override
    public boolean remove(String k) {
        try {
            return template.delete(k);
        } catch (Exception e) {
            logger.error("redis cache remove error:", e);
            throw e;
        }
    }

    @Override
    public boolean remove(Collection<String> keys) {

        if (CollectionUtils.isEmpty(keys)) {
            return true;
        }


        boolean isRemoved = false;
        try {
            if (CollectionUtils.isNotEmpty(keys)) {
                template.delete(keys);
            }
            isRemoved = true;
        } catch (Exception e) {
            logger.error("redis cache remove error:", e);
        }
        return isRemoved;
    }

    @Override
    public boolean removeByPrefix(String prefix) {

        boolean isRemoved = false;
        try {
            Set<String> keys = template.keys(prefix);
            isRemoved = remove(keys);
        } catch (Exception e) {
            logger.error("redis cache removeByPrefix error:", e);
        }
        return isRemoved;
    }

    @Override
    public boolean exist(String key) {
        return template.hasKey(key);
    }

    public void setRedisTemplate(RedisTemplate<String, V> template) {
        this.template = template;
    }
}
