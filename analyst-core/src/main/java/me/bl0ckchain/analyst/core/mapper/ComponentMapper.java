package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.Component;
import me.bl0ckchain.analyst.core.query.ComponentQuery;
import me.bl0ckchain.sdk.mybatis.mapper.AssociationMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Mapper
public interface ComponentMapper extends AssociationMapper<Long, Component, Long, Long> {

    /**
     * Select id by query.
     *
     * @param query
     * @return
     */
    List<Long> selectIdByQuery(ComponentQuery query);
}