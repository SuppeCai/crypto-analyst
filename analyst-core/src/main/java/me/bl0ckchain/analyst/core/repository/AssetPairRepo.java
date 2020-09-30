package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.mapper.AssetPairMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.CacheRepository;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 02/06/2018
 */
@Repository
public class AssetPairRepo extends CacheRepository<Long, AssetPair, AssetPairMapper> {

    public List<Long> listIds() {
        try {
            return mapper.selectIds();
        } catch (Exception e) {
            logger.error("listIds error:", e);
            throw e;
        }
    }

    public AssetPair findByBaseIdAndQuoteId(Long baseId, Long quoteId) {

        try {
            return mapper.selectByBaseIdAndQuoteId(baseId, quoteId);
        } catch (Exception e) {
            logger.error("findByBaseIdAndQuoteId error:", e);
            throw e;
        }
    }
}
