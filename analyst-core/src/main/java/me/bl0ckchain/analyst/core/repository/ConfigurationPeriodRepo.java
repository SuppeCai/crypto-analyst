package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.ConfigurationPeriod;
import me.bl0ckchain.analyst.core.mapper.ConfigurationPeriodMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 08/07/2018
 */
@Repository
public class ConfigurationPeriodRepo extends AssociationRepository<Long, ConfigurationPeriod, ConfigurationPeriodMapper, Long, Long> {

}
