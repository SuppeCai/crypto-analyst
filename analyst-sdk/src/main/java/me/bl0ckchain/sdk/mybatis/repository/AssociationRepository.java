package me.bl0ckchain.sdk.mybatis.repository;

import me.bl0ckchain.sdk.mybatis.entity.BaseEntity;
import me.bl0ckchain.sdk.mybatis.mapper.AssociationMapper;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 27/06/2018
 */
public abstract class AssociationRepository<ID, T extends BaseEntity<ID>, Mapper extends BaseMapper<ID, T>, K, FK> extends CacheRepository<ID, T, Mapper> {

    public List<FK> listAssociatedIds(K key) {
        try {
            return ((AssociationMapper) mapper).listAssociatedIds(key);
        } catch (Exception e) {
            logger.error("listAssociatedIds error:", e);
            throw e;
        }
    }
}
