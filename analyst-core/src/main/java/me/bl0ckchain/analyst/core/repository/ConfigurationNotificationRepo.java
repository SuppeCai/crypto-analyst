package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.ConfigurationNotification;
import me.bl0ckchain.analyst.core.mapper.ConfigurationNotificationMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Repository
public class ConfigurationNotificationRepo extends AssociationRepository<Long, ConfigurationNotification, ConfigurationNotificationMapper, Long, Long> {

}
