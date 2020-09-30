package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.mapper.NoticeLogMapper;
import me.bl0ckchain.analyst.core.query.NoticeLogQuery;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AbstractRepository;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 09/07/2018
 */
@Repository
public class NoticeLogRepo extends AbstractRepository<Long, NoticeLog, NoticeLogMapper> {

    public List<NoticeLog> findByQuery(NoticeLogQuery query) {
        try {
            return mapper.findByQuery(query);
        } catch (Exception e) {
            logger.error("find error:", e);
            throw e;
        }
    }

    public List<NoticeLog> findLatestByQuery(NoticeLogQuery query) {
        try {
            return mapper.findLatestByQuery(query);
        } catch (Exception e) {
            logger.error("find error:", e);
            throw e;
        }
    }
}
