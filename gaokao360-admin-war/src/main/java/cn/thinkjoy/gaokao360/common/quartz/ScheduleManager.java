package cn.thinkjoy.gaokao360.common.quartz;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.common.ZGKConstant;
import cn.thinkjoy.gaokao360.common.ResetUserInfoJob;
import cn.thinkjoy.gaokao360.service.common.ex.IUserInfoExService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liusven on 16/1/12.
 */
@Component
public class ScheduleManager implements ApplicationListener<ContextRefreshedEvent> {

    private static SchedulerFactory factory = new StdSchedulerFactory();
    private static Scheduler sched = null;
    @Autowired
    private IUserInfoExService userInfoExService;
    private void init()
    {
        try {
            sched = factory.getScheduler();
            sched.start();
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("userInfoExService", userInfoExService);
            addJob(ResetUserInfoJob.class, ZGKConstant.EVERY_FIVE_SECOND, dataMap);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void addJob(Class <? extends Job> jobClass, String cronExp)
    {
        addJob(jobClass, cronExp, null);
    }

    public static void addJob(Class <? extends Job> jobClass, String cronExp, Map<String, Object> dataMap)
    {
        JobDetail job = newJob(jobClass)
                .withIdentity("myJob", "group1")
                .build();
        job.getJobDataMap().putAll(dataMap);
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                .build();
        try {
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            new BizException("error","Add "+ jobClass.getName().toString()+" job fail.");
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }
}
