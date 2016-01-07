/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  EnrollReportController.java 2015-12-15 17:20:07 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo;

import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.IEnrollReportService;
import cn.thinkjoy.common.domain.view.BizData4Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/admin/gaokao360")
public class EnrollReportController extends BaseController<IEnrollReportService> {


    @Autowired
    private IEnrollReportService enrollReportService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/enrollreport")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/enrollreports")
    @ResponseBody
    public BizData4Page findAllEnrollReports(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IEnrollReportService getMainService() {
        return enrollReportService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360";
    }

    @Override
    protected String getMainObjName() {
        return "enrollreport";
    }

    @Override
    protected String getViewTitle() {
        return "院校招生信息";
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
