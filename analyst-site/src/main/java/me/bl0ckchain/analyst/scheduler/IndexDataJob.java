package me.bl0ckchain.analyst.scheduler;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.bl0ckchain.analyst.core.cache.ObjectCache;
import me.bl0ckchain.analyst.core.entity.Index;
import me.bl0ckchain.analyst.core.entity.IndexData;
import me.bl0ckchain.analyst.core.model.IndexDataDTO;
import me.bl0ckchain.analyst.core.service.IndexService;
import me.bl0ckchain.analyst.core.util.IndexUtils;
import me.bl0ckchain.sdk.scheduler.JobCron;
import me.bl0ckchain.sdk.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author caisupeng
 * @version $Id: IndexDataCollectJob.java, v 0.1 2019-04-09 11:39 AM caisupeng Exp $$
 */
@JobCron(cron = "0 3 0 * * ?")
public class IndexDataJob implements Job {
    private final static Logger logger = LoggerFactory.getLogger(IndexDataJob.class);

    public static final int DEFAULT_TIMEOUT = 86400 * 10;

    public static final int DEFAULT_MILLISECOND = 1000;

    @Autowired
    private ObjectCache cache;
    @Autowired
    private IndexService indexService;

    @Override
    public void execute(JobExecutionContext context) {
        List<Index> indexes = indexService.listIndexes();
        if (CollectionUtils.isEmpty(indexes)) {
            logger.error("no index");
            return;
        }

        Long today = DateUtils.getToday() / 1000;
        for (Index index : indexes) {
            List<JSONObject> list = cache.getByPrefix(IndexUtils.genKeyPrefix(index.getId()));
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }

            for (JSONObject item : list) {
                IndexDataDTO dto = JSON.toJavaObject(item, IndexDataDTO.class);
                if (dto.getIsSaved() || today.equals(dto.getDate())) {
                    continue;
                }

                IndexData data = new IndexData();
                data.setIndexId(dto.getIndexId());
                data.setValue(dto.getValue());
                data.setDate(dto.getDate() * DEFAULT_MILLISECOND);
                indexService.saveData(data);
                item.put("isSaved", true);
                cache.put(IndexUtils.genKey(dto.getIndexId(), dto.getDate()), item, DEFAULT_TIMEOUT);
            }
        }
    }
}