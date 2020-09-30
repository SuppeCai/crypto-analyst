package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.Notification;
import me.bl0ckchain.analyst.core.mapper.NotificationMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.CacheRepository;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Repository
public class NotificationRepo extends CacheRepository<Long, Notification, NotificationMapper> {

    public List<Long> listIds() {
        try {
            return mapper.selectIds();
        } catch (Exception e) {
            logger.error("listIds error:", e);
            throw e;
        }
    }
}
