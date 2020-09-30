package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.ComponentParam;
import me.bl0ckchain.analyst.core.mapper.ComponentParamMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/08/2018
 */
@Repository
public class ComponentParamRepo extends AssociationRepository<Long, ComponentParam, ComponentParamMapper, Long, Long> {

}
