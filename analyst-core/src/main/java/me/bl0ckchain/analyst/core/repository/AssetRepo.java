package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.Asset;
import me.bl0ckchain.analyst.core.mapper.AssetMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.CacheRepository;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Repository
public class AssetRepo extends CacheRepository<Long, Asset, AssetMapper> {

    public List<Long> listIds() {
        try {
            return mapper.selectIds();
        } catch (Exception e) {
            logger.error("listIds error:", e);
            throw e;
        }
    }
}
