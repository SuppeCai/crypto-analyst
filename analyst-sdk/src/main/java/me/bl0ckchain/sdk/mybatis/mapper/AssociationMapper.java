package me.bl0ckchain.sdk.mybatis.mapper;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 27/06/2018
 */
public interface AssociationMapper<ID, T, K, FK> extends BaseMapper<ID, T> {

    /**
     * List associated entities's ids.
     *
     * @param key
     * @return
     */
    List<FK> listAssociatedIds(K key);
}
