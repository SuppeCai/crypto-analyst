package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.mapper.IndicationMapper;
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
public class IndicationRepo extends CacheRepository<Long, Indication, IndicationMapper> {

    public List<Long> listIds() {
        try {
            return mapper.selectIds();
        } catch (Exception e) {
            logger.error("listIds error:", e);
            throw e;
        }
    }
}
