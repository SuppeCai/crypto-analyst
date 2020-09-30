package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.ExchangeAssetPair;
import me.bl0ckchain.analyst.core.mapper.ExchangeAssetPairMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Repository
public class ExchangeAssetPairRepo extends AssociationRepository<Long, ExchangeAssetPair, ExchangeAssetPairMapper, Long, Long> {

}
