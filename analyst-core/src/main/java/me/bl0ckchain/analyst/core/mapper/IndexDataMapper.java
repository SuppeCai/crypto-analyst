package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.IndexData;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IndexDataMapper extends BaseMapper<Long, IndexData> {
}