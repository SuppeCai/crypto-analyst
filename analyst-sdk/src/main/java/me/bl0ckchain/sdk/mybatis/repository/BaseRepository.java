package me.bl0ckchain.sdk.mybatis.repository;

import me.bl0ckchain.sdk.mybatis.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 25/06/2018
 */
public interface BaseRepository<ID, T extends BaseEntity<ID>> {

    /**
     * Find entity by id.
     *
     * @param id
     * @return
     */
    T find(ID id);

    /**
     * Save entity.
     *
     * @param t
     * @return
     */
    void save(T t);

    /**
     * Delete entity by id.
     *
     * @param id
     * @return
     */
    boolean delete(ID id);

    /**
     * Select entity list by ids.
     *
     * @param ids
     * @return
     */
    List<T> list(List<ID> ids);

    /**
     * Select entity map by ids.
     *
     * @param ids
     * @return
     */
    Map<ID, T> mget(List<ID> ids);

    /**
     * Query entity with out auto assemble.
     *
     * @return
     */
    BaseRepository simpleQuery();
}
