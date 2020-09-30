package me.bl0ckchain.analyst.core.mapper;

import com.github.pagehelper.Page;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.query.KlineQuery;
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
public interface KlineMapper extends BaseMapper<Long, Kline> {

    /**
     * Batch insert kline data list.
     *
     * @param list
     * @param unit
     * @param unitNum
     * @return
     */
    int batchInsert(@Param("list") List<Kline> list, @Param("unit") int unit, @Param("unitNum") int unitNum);

    /**
     * Select by unique keys.
     *
     * @param query
     * @return
     */
    Kline selectByQuery(KlineQuery query);

    /**
     * select kline data by page.
     *
     * @param query
     * @return
     */
    Page<Kline> selectByPage(KlineQuery query);
}