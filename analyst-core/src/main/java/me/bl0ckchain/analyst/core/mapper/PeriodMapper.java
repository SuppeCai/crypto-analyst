package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Mapper
public interface PeriodMapper extends BaseMapper<Long, Period> {

    List<Long> selectIds();

    /**
     * Find by unit.
     *
     * @param unit
     * @param unitNum
     * @return
     */
    Period findByUnit(@Param("unit") UnitEnum unit, @Param("unitNum") UnitNumEnum unitNum);
}