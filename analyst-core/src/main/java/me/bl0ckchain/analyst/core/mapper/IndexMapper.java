package me.bl0ckchain.analyst.core.mapper;

import java.util.List;

import me.bl0ckchain.analyst.core.entity.Index;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IndexMapper extends BaseMapper<Long, Index> {

    List<Long> selectIds();
}