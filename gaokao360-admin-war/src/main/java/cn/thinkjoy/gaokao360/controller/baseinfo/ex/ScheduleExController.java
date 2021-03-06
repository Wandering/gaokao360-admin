/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ScheduleController.java 2015-12-16 10:49:57 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IScheduleExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("prototype")
@RequestMapping(value="/admin/gaokao360/ex")
public class ScheduleExController extends BaseController<IScheduleExService> {


    @Autowired
    private IScheduleExService scheduleExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/schedule")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/schedules")
    @ResponseBody
    public BizData4Page findAllSchedules(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IScheduleExService getMainService() {
        return scheduleExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "schedule";
    }

    @Override
    protected String getViewTitle() {
        return "高考日程";
    }

    @Override
    protected String getParentTitle() {
        return "数据模块";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
}
