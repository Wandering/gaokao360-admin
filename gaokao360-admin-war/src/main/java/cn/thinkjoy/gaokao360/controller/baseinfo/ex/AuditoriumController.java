/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoCourseController.java 2015-12-04 13:22:05 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.ex.IAuditoriumService;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoClassifyService;
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
public class AuditoriumController extends BaseController<IAuditoriumService> {


    @Autowired
    private IVideoClassifyService videoClassifyService;
    @Autowired
    private IAuditoriumService auditoriumService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/auditorium")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/queryVideoType")
    @ResponseBody
    public Object queryVideoType(){
        return videoClassifyService.findAll();
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/auditoriums")
    @ResponseBody
    public BizData4Page findAllVideoCourses(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IAuditoriumService getMainService() {
        return auditoriumService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "auditorium";
    }

    @Override
    protected String getViewTitle() {
        return "高考学堂";
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
