package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.Component;
import me.bl0ckchain.analyst.core.mapper.ComponentMapper;
import me.bl0ckchain.analyst.core.query.ComponentQuery;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/08/2018
 */
@Repository
public class ComponentRepo extends AssociationRepository<Long, Component, ComponentMapper, Long, Long> {

    public List<Long> findIdByQuery(ComponentQuery query) {

        try {
            return mapper.selectIdByQuery(query);
        } catch (Exception e) {
            logger.error("findByBaseIdAndQuoteId error:", e);
            throw e;
        }
    }
}
