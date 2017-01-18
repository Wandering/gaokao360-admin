package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.domain.ExpertDate;
import cn.thinkjoy.gaokao360.domain.ExpertServiceDays;
import cn.thinkjoy.gaokao360.domain.ExpertServiceTimes;
import cn.thinkjoy.gaokao360.domain.ExpertTime;
import cn.thinkjoy.gaokao360.service.common.IExpertServiceDaysService;
import cn.thinkjoy.gaokao360.service.common.IExpertServiceTimesService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertServiceDaysExService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin/zgkadmin/ex")
public class ExpertDaysExController extends BaseController<IExpertServiceDaysExService> {


    @Autowired
    private IExpertServiceDaysService expertServiceDaysService;


    @Autowired
    private IExpertServiceDaysExService expertServiceDaysExService;

    @Autowired
    private IExpertServiceTimesService expertServiceTimesService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 页面主请求
     *
     * @param request
     * @param response
     * @return 返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value = "/expertserviceday")
    public ModelAndView renderMainView(HttpServletRequest request, HttpServletResponse response) {


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     *
     * @return
     */
    @RequestMapping(value = "/expertservicedays")
    @ResponseBody
    public BizData4Page findAllExpertServiceDays(HttpServletRequest request, HttpServletResponse response) {
        return doPage(request, response);
    }

    /**
     * 获取所有的组织信息
     *
     * @return
     */
    @RequestMapping(value = "/addDate")
    @ResponseBody
    public Boolean addDate(String expertDateStr) {
        ExpertDate expertDate = JSON.parseObject(expertDateStr, ExpertDate.class);

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

    @Override
    protected IExpertServiceDaysExService getMainService() {
        return expertServiceDaysExService;
    }


    @Override
    protected String getBizSys() {
        return "zgkadmin/ex";
    }

    @Override
    protected String getMainObjName() {
        return "expertserviceday";
    }

    @Override
    protected String getViewTitle() {
        return "专家天数设置";
    }

    @Override
    protected String getParentTitle() {
        return "专家服务";
    }

    @Override
    public boolean getEnableDataPerm() {
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
//
//    private Date getLastMonth() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.HOUR, -1);    //得到前一天
//        cal.add(Calendar.MONTH, -1);    //得到前一个月
//        long date = cal.getTimeInMillis();
//        System.out.println(format.format(new Date(date)) );
//        return new Date(date);
//    }
public static void main(String[] args) {
    String tt  = "2016-01-31 11:21";
    try {
        Date start = sdf.parse(tt);
    } catch (ParseException e) {
        e.printStackTrace();
    }

}
}
