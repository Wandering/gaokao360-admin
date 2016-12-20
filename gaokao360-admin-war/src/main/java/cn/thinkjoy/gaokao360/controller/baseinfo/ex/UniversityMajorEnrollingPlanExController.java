/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorEnrollingPlanController.java 2016-04-29 15:38:17 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.IUniversityMajorEnrollingPlanService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityMajorEnrollingPlanExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class UniversityMajorEnrollingPlanExController extends BaseController<IUniversityMajorEnrollingPlanExService> {


    @Autowired
    private IUniversityMajorEnrollingPlanExService universityMajorEnrollingPlanExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/universitymajorenrollingplan")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){
        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/universitymajorenrollingplans")
    @ResponseBody
    public BizData4Page findAllUniversityMajorEnrollingPlans(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IUniversityMajorEnrollingPlanExService getMainService() {
        return universityMajorEnrollingPlanExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "universitymajorenrollingplan";
    }

    @Override
    protected String getViewTitle() {
        return "学院专业招生计划信息";
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
