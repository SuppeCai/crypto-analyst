package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.Configuration;
import me.bl0ckchain.analyst.core.mapper.ConfigurationMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.CacheRepository;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Repository
public class ConfigurationRepo extends CacheRepository<Long, Configuration, ConfigurationMapper> {

}
