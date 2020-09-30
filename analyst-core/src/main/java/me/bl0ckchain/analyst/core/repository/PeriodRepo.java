package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.mapper.PeriodMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.CacheRepository;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 08/07/2018
 */
@Repository
public class PeriodRepo extends CacheRepository<Long, Period, PeriodMapper> {

    public List<Long> listIds() {
        try {
            return mapper.selectIds();
        } catch (Exception e) {
            logger.error("listIds error:", e);
            throw e;
        }
    }

    public Period findByKline(Kline kline) {
        try {
            return mapper.findByUnit(kline.getUnit(), kline.getUnitNum());
        } catch (Exception e) {
            logger.error("listIds error:", e);
            throw e;
        }
    }
}
