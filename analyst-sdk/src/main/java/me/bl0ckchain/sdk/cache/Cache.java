package me.bl0ckchain.sdk.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 31/05/2018
 */
public interface Cache<K, V> {

    V get(K key);

    Map<K, V> mget(List<K> keys);

    List<V> getByPrefix(K prefix);

    Map<K, V> mapByPrefix(K prefix);

    void put(K key, V value);

    void put(K key, V value, int timeout);

    void mput(Map<K, V> map);

    boolean remove(K value);

    boolean remove(Collection<K> keys);

    boolean removeByPrefix(K prefix);

    boolean exist(K key);

}
