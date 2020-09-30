package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.ConfigurationStrategy;
import me.bl0ckchain.analyst.core.mapper.ConfigurationStrategyMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/07/2018
 */
@Repository
public class ConfigurationStrategyRepo extends AssociationRepository<Long, ConfigurationStrategy, ConfigurationStrategyMapper, Long, Long> {
}
