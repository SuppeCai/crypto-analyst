package me.bl0ckchain.sdk.mybatis.repository;

import me.bl0ckchain.sdk.cache.RedisCache;
import me.bl0ckchain.sdk.mybatis.entity.BaseEntity;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;
import me.bl0ckchain.sdk.utils.CacheUtils;
import me.bl0ckchain.sdk.utils.ClassUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 01/07/2018
 */
public abstract class CacheRepository<ID, T extends BaseEntity<ID>, Mapper extends BaseMapper<ID, T>> extends AbstractRepository<ID, T, Mapper> {

    protected Class<T> tClass;
    /**
     * Cache of the Entity.
     */
    protected RedisCache<T> cache;

    public CacheRepository() {
        this.tClass = (Class<T>) ClassUtils.getGenericClass(this.getClass(), 1);
        this.cache = new RedisCache<>(this.tClass);
    }

    @Override
    public T find(ID id) {
        if (id == null) {
            return null;
        }

        String key = CacheUtils.genKey(this.tClass, id);
        T t = cache.get(key);
        if (t == null) {
            t = super.find(id);
            if (t != null) {
                cache.put(key, t);
            }
        }
        return t;
    }

    @Override
    public void save(T t) {

        if (t == null) {
            return;
        }

        super.save(t);

        String key = CacheUtils.genKey(t);
        if (cache.exist(key)) {
            cache.remove(key);
        }
    }

    @Override
    public boolean delete(ID id) {

        if (id == null) {
            return false;
        }

        boolean isDeleted = super.delete(id);
        String key = CacheUtils.genKey(this.tClass, id);
        if (cache.exist(key)) {
            cache.remove(key);
        }
        return isDeleted;
    }

    @Override
    public List<T> list(List<ID> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            return Collections.EMPTY_LIST;
        }

        List<String> keys = CacheUtils.genKeyList(this.tClass, ids);
        Map<String, T> map = cache.mget(keys);
        List<T> list;
        if (MapUtils.isNotEmpty(map)) {
            list = new ArrayList<>();
            List<ID> missedIds = new ArrayList<>();
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                if (!map.containsKey(key)) {
                    missedIds.add(ids.get(i));
                } else {
                    list.add(map.get(key));
                }
            }
            if (CollectionUtils.isNotEmpty(missedIds)) {
                List<T> extraList = super.list(missedIds);
                if (CollectionUtils.isNotEmpty(extraList)) {
                    list.addAll(extraList);
                    Map<String, T> extraMap = new HashMap<>();
                    for (T t : extraList) {
                        extraMap.put(CacheUtils.genKey(t), t);
                    }
                    cache.mput(extraMap);
                }
            }
        } else {
            list = super.list(ids);
            if (CollectionUtils.isEmpty(list)) {
                return Collections.EMPTY_LIST;
            }
            map = new HashMap<>();
            for (T t : list) {
                map.put(CacheUtils.genKey(t), t);
            }
            cache.mput(map);
        }
        return list;
    }

    @Override
    public Map<ID, T> mget(List<ID> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            return Collections.EMPTY_MAP;
        }

        Map<ID, T> result;
        List<String> keys = CacheUtils.genKeyList(this.tClass, ids);
        Map<String, T> map = cache.mget(keys);
        if (MapUtils.isNotEmpty(map)) {
            result = new HashMap<>();
            for (T t : map.values()) {
                result.put(t.getId(), t);
            }
            List<ID> missedIds = new ArrayList<>();
            for (int i = 0; i < keys.size(); i++) {
                if (!map.containsKey(keys.get(i))) {
                    missedIds.add(ids.get(i));
                }
            }
            if (CollectionUtils.isNotEmpty(missedIds)) {
                Map<ID, T> extraMap = mget(missedIds);
                if (MapUtils.isNotEmpty(extraMap)) {
                    result.putAll(extraMap);
                }
            }
        } else {
            result = super.mget(ids);
            if (MapUtils.isEmpty(result)) {
                return result;
            }
            for (T t : result.values()) {
                map = new HashMap<>();
                map.put(CacheUtils.genKey(t), t);
            }
            cache.mput(map);
        }
        return result;
    }

    public void setRedisTemplate(RedisTemplate template) {
        this.cache.setRedisTemplate(template);
    }

}
