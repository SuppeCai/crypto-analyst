package me.bl0ckchain.analyst.core.mapper;

import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.query.NoticeLogQuery;
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
public interface NoticeLogMapper extends BaseMapper<Long, NoticeLog> {

    /**
     * Find NoticeLog by query.
     *
     * @param query
     * @return
     */
    List<NoticeLog> findByQuery(NoticeLogQuery query);

    /**
     * Find latest NoticeLogs by query.
     *
     * @param query
     * @return
     */
    List<NoticeLog> findLatestByQuery(NoticeLogQuery query);

}