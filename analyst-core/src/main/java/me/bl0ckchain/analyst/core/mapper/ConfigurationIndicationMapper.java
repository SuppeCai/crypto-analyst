package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.ConfigurationIndication;
import me.bl0ckchain.sdk.mybatis.mapper.AssociationMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Mapper
public interface ConfigurationIndicationMapper extends AssociationMapper<Long, ConfigurationIndication, Long, Long> {

}