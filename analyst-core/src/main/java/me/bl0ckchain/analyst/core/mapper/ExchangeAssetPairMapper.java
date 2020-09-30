package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.ExchangeAssetPair;
import me.bl0ckchain.sdk.mybatis.mapper.AssociationMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Mapper
public interface ExchangeAssetPairMapper extends AssociationMapper<Long, ExchangeAssetPair, Long, Long> {

}