package me.bl0ckchain.sdk.scheduler;

import java.util.Map;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author caisupeng
 * @version $Id: SchedulerRegister.java, v 0.1 2019-04-09 3:32 PM caisupeng Exp $$
 */
public class SchedulerRegister implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerRegister.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        Map<String, Object> jobMap = context.getBeansWithAnnotation(JobCron.class);

        for (Object obj : jobMap.values()) {
            if (!(obj instanceof Job)) {
                throw new IllegalArgumentException("Must extend from Job.");
            }

            Job job = (Job) obj;
            Class jobClass = job.getClass();
            JobCron jobCron = AnnotationUtils.findAnnotation(jobClass, JobCron.class);

            try {
                reschedule(jobClass, jobCron.cron());
            } catch (SchedulerException e) {
                logger.error("Register scheduler error, name:" + jobClass.getSimpleName(), e);
            }
        }
    }

    private void reschedule(Class<? extends Job> jobClass, String cron) throws SchedulerException {
        String name = jobClass.getSimpleName();
        String group = jobClass.getPackage().getName();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(name, group).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        if (scheduler.deleteJob(JobKey.jobKey(name, group))) {
            logger.info("deleted job, name:" + name + " group:" + group);
        }
        scheduler.scheduleJob(jobDetail, trigger);
        logger.info("registered job, name:" + name + " group:" + group);
    }
}