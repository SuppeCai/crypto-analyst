package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.mapper.StrategyMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.CacheRepository;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/07/2018
 */
@Repository
public class StrategyRepo extends CacheRepository<Long, Strategy, StrategyMapper> {

    public List<Long> listIds() {
        try {
            return mapper.selectIds();
        } catch (Exception e) {
            logger.error("listIds error:", e);
            throw e;
        }
    }
}
