package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Mapper
public interface AssetPairMapper extends BaseMapper<Long, AssetPair> {

    List<Long> selectIds();

    /**
     * Select AssetPair by Asset's id.
     *
     * @param baseId
     * @param quoteId
     * @return
     */
    AssetPair selectByBaseIdAndQuoteId(@Param("baseId") Long baseId, @Param("quoteId") Long quoteId);
}