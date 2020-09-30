package me.bl0ckchain.analyst.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.model.Message;
import me.bl0ckchain.analyst.core.notification.Alert;
import me.bl0ckchain.analyst.core.query.KlineQuery;
import me.bl0ckchain.analyst.core.repository.KlineRepo;
import me.bl0ckchain.analyst.core.service.NoticeService;
import me.bl0ckchain.analyst.endpoint.AnalysisEndPoint;
import me.bl0ckchain.analyst.scheduler.IndexDataJob;
import me.bl0ckchain.analyst.scheduler.KlineCollectJob;
import me.bl0ckchain.analyst.utils.APNsUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 18/05/2018
 */
@RestController
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private KlineRepo klineRepo;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private AnalysisEndPoint analysisEndPoint;

    @RequestMapping("/")
    public String home() {
        return "a";
    }

    @RequestMapping("/page")
    public String page(Long exchangeId, Long baseId, Long quoteId, UnitEnum unit, UnitNumEnum num, Long startAt, Long endAt, int page, int size) {
        KlineQuery query = new KlineQuery();
        query.setExchangeId(exchangeId);
        query.setBaseId(baseId);
        query.setQuoteId(quoteId);
        query.setUnit(unit);
        query.setUnitNum(num);
        query.setStartAt(startAt);
        query.setEndAt(endAt);
        query.setPage(page);
        query.setSize(size);
        PageInfo<Kline> info = klineRepo.findByPage(query);
        return JSON.toJSONString(info);
    }

    @RequestMapping("/reschedule")
    public String reschedule() {
        boolean isDone = false;
        try {
            reschedule(KlineCollectJob.class, "5 */1 * * * ?");
            reschedule(IndexDataJob.class, "5 */1 * * * ?");
            isDone = true;
        } catch (SchedulerException e) {
            logger.error("build scheduler error:", e);
        }
        return String.valueOf(isDone);
    }

    private void reschedule(Class<? extends Job> jobClass, String cron) throws SchedulerException {
        String name = jobClass.getSimpleName();
        String group = jobClass.getPackage().getName();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(name, group).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        scheduler.deleteJob(JobKey.jobKey(name, group));
    }

    @RequestMapping("/broadcast")
    public String broadcast() {
        Message message = new Message();
        message.setType(Message.Type.PONG);
        message.setData("aaaa");
        analysisEndPoint.broadcast(message);
        return "true";
    }

    @RequestMapping("/noticeLog")
    public String noticeLog() {
        NoticeLog log = new NoticeLog();
        log.setExchangeId(1L);
        log.setAssetPairId(1L);
        log.setPeriodId(10L);
        log.setStrategyId(1L);
        log.setNotifyAt(System.currentTimeMillis());
        noticeService.log(log);
        return "true";
    }

    @RequestMapping(value = "/apns/{alert}", method = RequestMethod.GET)
    public String apns(@PathVariable("alert") String text) {
        Alert alert = new Alert();
        alert.setTitle("This is title");
        alert.setSubtitle(text);
        alert.setBody("This is Body");
        APNsUtils.sendNotification(alert);
        return "true";
    }

}
