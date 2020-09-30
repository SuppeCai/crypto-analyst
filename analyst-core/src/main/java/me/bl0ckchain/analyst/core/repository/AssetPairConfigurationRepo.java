package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.AssetPairConfiguration;
import me.bl0ckchain.analyst.core.mapper.AssetPairConfigurationMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Repository
public class AssetPairConfigurationRepo extends AssociationRepository<Long, AssetPairConfiguration, AssetPairConfigurationMapper, Long, Long> {

}
