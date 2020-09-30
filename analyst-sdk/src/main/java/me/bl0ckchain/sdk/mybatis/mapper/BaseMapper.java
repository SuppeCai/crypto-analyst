package me.bl0ckchain.sdk.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 16/2/2
 */
public interface BaseMapper<ID, T> {

    /**
     * Select entity by primary key.
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(ID id);

    /**
     * Insert entity.
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * Insert entity with filled properties.
     *
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * Update entity if property is not null.
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * Update entity.
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * Delete entity by primary key.
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(ID id);

    /***
     * MUST mapping list method in entity mapper.xml!
     * get t list by id list
     *
     * @param ids
     * @return
     */
    List<T> selectByPrimaryKeys(@Param("ids")List<ID> ids);

}
