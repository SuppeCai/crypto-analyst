package me.bl0ckchain.analyst.scheduler;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.bl0ckchain.analyst.core.cache.ObjectCache;
import me.bl0ckchain.analyst.core.entity.Index;
import me.bl0ckchain.analyst.core.model.IndexDataDTO;
import me.bl0ckchain.analyst.core.notification.Alert;
import me.bl0ckchain.analyst.core.service.IndexService;
import me.bl0ckchain.analyst.core.util.IndexUtils;
import me.bl0ckchain.analyst.utils.APNsUtils;
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
@JobCron(cron = "5 0 16 * * ?")
public class PushNotificationJob implements Job {
    private final static Logger logger = LoggerFactory.getLogger(PushNotificationJob.class);

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
            JSONObject json = cache.get(IndexUtils.genKey(index.getId(), today));
            if (json == null) {
                continue;
            }

            IndexDataDTO dto = JSON.toJavaObject(json, IndexDataDTO.class);
            Alert alert = new Alert();
            alert.setTitle(index.getName());
            alert.setBody(dto.getValue().toString());
            APNsUtils.sendNotification(alert);
        }
    }
}