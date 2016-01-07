/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorEnrollingController.java 2015-12-31 10:09:48 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.IUniversityMajorEnrollingService;
import cn.thinkjoy.gaokao360.service.ex.IUniversityMajorEnrollingExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class UniversityMajorEnrollingExController extends BaseController<IUniversityMajorEnrollingExService> {


    @Autowired
    private IUniversityMajorEnrollingExService universityMajorEnrollingExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/universitymajorenrolling")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/universitymajorenrollings")
    @ResponseBody
    public BizData4Page findAllUniversityMajorEnrollings(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IUniversityMajorEnrollingExService getMainService() {
        return universityMajorEnrollingExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "universitymajorenrolling";
    }

    @Override
    protected String getViewTitle() {
        return "专业招生信息";
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
