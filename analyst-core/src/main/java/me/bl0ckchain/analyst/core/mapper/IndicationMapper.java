package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Mapper
public interface IndicationMapper extends BaseMapper<Long, Indication> {

    List<Long> selectIds();
}