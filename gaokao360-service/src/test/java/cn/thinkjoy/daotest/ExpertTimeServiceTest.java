/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityEnrollingService.java 2016-02-01 17:02:35 $
 */

package cn.thinkjoy.daotest;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.common.UserAreaContext;
import cn.thinkjoy.gaokao360.domain.*;
import cn.thinkjoy.gaokao360.service.common.IExpertInfoService;
import cn.thinkjoy.gaokao360.service.common.IExpertServiceDaysService;
import cn.thinkjoy.gaokao360.service.common.IExpertServiceTimesService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertInfoExService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertServiceDaysExService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityEnrollingExService;
import cn.thinkjoy.zgk.common.QueryUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springme.xml")
public class ExpertTimeServiceTest {
    @Autowired
    private IExpertServiceDaysService expertServiceDaysService;

    @Autowired
    private IExpertInfoService expertInfoService;
    @Autowired
    private IExpertServiceTimesService expertServiceTimesService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");

    public Boolean addDate(ExpertDate expertDate) {
        List<ExpertTime> expertTimes = new ArrayList<>();
        for (ExpertTime time : expertDate.getExpertTimes()) {
            try {
                Date start = sdf.parse(time.getPicker() + " " + time.getStart());
                Date end = sdf.parse(time.getPicker() + " " + time.getEnd());
                List<Date> dates = dateSplit(start, end);

                expertTimes = dateToExpertTime(dates);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map<String, Object> map = Maps.newHashMap();
            map.put("expertId", expertDate.getExpertId());
            map.put("serviceDay", expertDate.getExpertTimes().get(0).getPicker());
            ExpertServiceDays expertServiceDays = null;
            if (expertTimes != null && expertTimes.size() != 0) {
                expertServiceDays = (ExpertServiceDays) expertServiceDaysService.queryOne(map);
            }
            Long dayId = null;
            if (expertServiceDays == null) {
                expertServiceDays = new ExpertServiceDays();
                expertServiceDays.setIsAvailable("1");
                expertServiceDays.setExpertId(Integer.valueOf(expertDate.getExpertId()));
                try {
                    Date date = dayFmt.parse(expertDate.getExpertTimes().get(0).getPicker());
                    expertServiceDays.setServiceDay(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                expertServiceDaysService.add(expertServiceDays);
            }
            dayId = expertServiceDays.getId();
            List<ExpertServiceTimes> expertServiceTimes = expertTimeToTime(expertTimes,dayId);
            for (ExpertServiceTimes expertServiceTime:expertServiceTimes){
                expertServiceTimesService.add(expertServiceTime);
            }
        }
        return true;
    }


    private List<Date> dateSplit(Date startDate, Date endDate) throws Exception {
        if (!startDate.before(endDate)) throw new Exception("开始时间应该在结束时间之后");
        Long spi = endDate.getTime() - startDate.getTime();
        //一共分割成多少(小时)
        Long step = spi / (60 * 60 * 1000);

        List<Date> dateList = new ArrayList<>();
        for (long i = 0; i <= step; i++) {
            dateList.add(new Date(startDate.getTime() + 60 * 60 * 1000 * i));
        }
        return dateList;
    }

    private List<ExpertTime> dateToExpertTime(List<Date> dates) {
        SimpleDateFormat sdf_day = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm");
        String temp = null;
        List<ExpertTime> expertTimes = new ArrayList<>();
        for (Date date : dates) {
            if (temp == null) {
                temp = sdf_time.format(date);
            } else {
                ExpertTime expertTime = new ExpertTime();
                String day = sdf_day.format(date);
                String time = sdf_time.format(date);
                expertTime.setPicker(day);
                expertTime.setStart(temp);
                temp = time;
                expertTime.setEnd(time);
                expertTimes.add(expertTime);
            }
        }
        return expertTimes;
    }

    private ExpertServiceTimes expertTimeToTime(ExpertTime expertTime, Long dayId) {
        ExpertServiceTimes expertServiceTimes = new ExpertServiceTimes();
        expertServiceTimes.setIsAvailable("1");
        expertServiceTimes.setExpertDayId(Integer.valueOf(dayId+""));
        expertServiceTimes.setTimeSegment(expertTime.getStart() + "~" + expertTime.getEnd());
        return expertServiceTimes;
    }

    private List<ExpertServiceTimes> expertTimeToTime(List<ExpertTime> expertTimes, Long dayId) {
        List<ExpertServiceTimes> list = new ArrayList<>();
        for (ExpertTime expertTime : expertTimes) {
            list.add(expertTimeToTime(expertTime,dayId));
        }
        return list;
    }

    @Test
    public void test(){
        Map<String,Object> map = new HashMap<>();
        map.put("isChecked","1");
        List<ExpertInfo> expertInfos = expertInfoService.queryList(map,"id","desc");
        for (ExpertInfo expertInfo : expertInfos) {
            ExpertDate expertDate = new ExpertDate();
            expertDate.setExpertId(expertInfo.getId().toString());
            String start = "2017-01-26";
            String end = "2017-08-01";
            try {
                putExpertDate(start,end,expertInfo);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    private void putExpertDate(String start,String end,ExpertInfo expertInfo) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayFmt.parse(start));

        String tempDate = "";
        do {
            boolean flag = true;
            System.out.println("当前时间:"+ dayFmt.format(calendar.getTime()));
            System.out.println("当前专家:"+ expertInfo.getExpertName());

            int sunday = calendar.get(Calendar.DAY_OF_WEEK);
            if ( sunday == 7 || sunday == 1 )
                flag = false;
            ExpertDate expertDate = new ExpertDate();
            expertDate.setExpertId(expertInfo.getId().toString());
            List<ExpertTime> expertTimes = new ArrayList<>();
            ExpertTime expertTime = new ExpertTime();
            expertTime.setPicker(dayFmt.format(calendar.getTime()));
            if (flag) {
                expertTime.setStart("18:00");
                expertTime.setEnd("22:00");
            }else {
                expertTime.setStart("8:00");
                expertTime.setEnd("21:00");
            }
            expertTimes.add(expertTime);
            expertDate.setExpertTimes(expertTimes);
            addDate(expertDate);
            tempDate = dayFmt.format(calendar.getTime());
            calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+1);
        }while (!tempDate.equals(end));
    }
}
