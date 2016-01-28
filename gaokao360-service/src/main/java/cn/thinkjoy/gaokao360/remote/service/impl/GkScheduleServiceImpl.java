package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.domain.Schedule;
import cn.thinkjoy.gaokao360.service.differentiation.IScheduleService;
import cn.thinkjoy.zgk.domain.GkSchedule;
import cn.thinkjoy.zgk.dto.GkScheduleDTO;
import cn.thinkjoy.zgk.remote.IGkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2016/1/5.
 */
@Service("GkScheduleServiceImpl")
public class GkScheduleServiceImpl implements IGkScheduleService {

    //设置是否加载内容，默认不加载
    private boolean isIgnore=false;
    @Autowired
    private IScheduleService scheduleService;

    @Override
    public List<GkScheduleDTO> getScheduleList(Map<String,Object> condition,Integer num) {
        Integer year=null;
        Integer month=null;
        Calendar calendar=Calendar.getInstance();
        if(condition.containsKey("startMonth")){
            String startMonthStr=String.valueOf(condition.get("startMonth"));
            String[] strs=startMonthStr.split("-");
            year=Integer.parseInt(strs[0]);
            month=Integer.parseInt(strs[1]);
            calendar.add(month,0);
        }
        Boolean boo=(Boolean)condition.get("boo");
        Integer showMonth=null;
        if(condition.containsKey("showMonth")){
            showMonth=(Integer)condition.get("showMonth");
        }

        Map<String,Object> map = null;
        GkScheduleDTO gkScheduleDTO=null;
        List<GkScheduleDTO> gkScheduleDTOs=new ArrayList<>();
        for(int i=0;i<num;i++){

            if(condition.containsKey("startMonth")) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1;
            }
            calendar.add(Calendar.MONTH, -1);
            if(showMonth!=null && showMonth!=month ){
                continue;
            }
            map=new HashMap<>();
            map.put("years",Integer.valueOf(year));
            map.put("month",Integer.valueOf(month));
            gkScheduleDTO=new GkScheduleDTO();
            List<Schedule> schedules=scheduleService.queryList(map,"createDate","desc");
            if(condition.containsKey("scheduleRows")){
                Integer scheduleRows=(Integer)condition.get("scheduleRows");
                if(schedules!=null && schedules.size()>scheduleRows) {
                    schedules = schedules.subList(0, scheduleRows - 1);
                }
            }
            //设置是否加载内容
            this.setIsIgnore(false);
            gkScheduleDTO.setMonth(String.valueOf(month));
            gkScheduleDTO.setYears(String.valueOf(year));
            if(boo){
                gkScheduleDTO.setSchedules(schedule2GkSchedule(schedules));
            }
            gkScheduleDTOs.add(gkScheduleDTO);
        }
        return gkScheduleDTOs;
    }

    @Override
    public GkSchedule getScheduleInfo(Map<String, Object> conditions,String id) {
        Schedule schedule=(Schedule)scheduleService.fetch(id);
        //设置是否加载内容
        this.setIsIgnore(true);
        return schedule2GkSchedule(schedule);
    }

    private List<GkSchedule> schedule2GkSchedule(List<Schedule> schedules){
        if(schedules==null)return null;
        List<GkSchedule> gkSchedules= new ArrayList<>();
        for(Schedule schedule:schedules){
            gkSchedules.add(schedule2GkSchedule(schedule));
        }
        return gkSchedules;
    }

    private GkSchedule schedule2GkSchedule(Schedule schedule){
        if(schedule==null)return null;
        GkSchedule gkSchedule=new GkSchedule();
        gkSchedule.setId(schedule.getId());
        gkSchedule.setTitle(schedule.getTitle());
        gkSchedule.setYears(schedule.getYears());
        gkSchedule.setMonth(schedule.getMonth());
        if(isIgnore()) {
            gkSchedule.setContent(schedule.getContent());
            gkSchedule.setLastModDate(schedule.getLastModDate());
        }
        return gkSchedule;
    }

    public boolean isIgnore() {
        return isIgnore;
    }

    public void setIsIgnore(boolean isIgnore) {
        this.isIgnore = isIgnore;
    }

    public IScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(IScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}
