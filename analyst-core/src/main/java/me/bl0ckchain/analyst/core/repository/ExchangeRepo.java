package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.mapper.ExchangeMapper;
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
public class ExchangeRepo extends CacheRepository<Long, Exchange, ExchangeMapper> {

    public List<Long> listIds() {
        try {
            return mapper.selectIds();
        } catch (Exception e) {
            logger.error("listIds error:", e);
            throw e;
        }
    }
}
