package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.gaokao360.service.common.ex.IUserInfoExService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by liusven on 16/1/12.
 */
public class ResetUserInfoJob implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String, Object> dataMap = context.getJobDetail().getJobDataMap();
        IUserInfoExService userInfoExService = (IUserInfoExService) dataMap.get("userInfoExService");
        userInfoExService.findUserInfoById(1);
        System.out.println("Reset userInfo!");
    }
}
