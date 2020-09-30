package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.Analysis;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Mapper
public interface AnalysisMapper extends BaseMapper<Long, Analysis> {

    List<Long> selectIds();
}