/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredController.java 2015-12-16 16:48:39 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.IMajoredService;
import cn.thinkjoy.gaokao360.service.common.ex.IMajoredExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class MajoredExController extends BaseController<IMajoredExService> {


    @Autowired
    private IMajoredService majoredService;
    @Autowired
    private IMajoredExService majoredExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/majored")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/majoreds")
    @ResponseBody
    public BizData4Page findAllMajoreds(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @RequestMapping(value="/getMajoredList")
    @ResponseBody
    public List getMajoredList(String name){
        return   majoredExService.getMajoredListByName(name);
    }

    @Override
    protected IMajoredExService getMainService() {
        return majoredExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "majored";
    }

    @Override
    protected String getViewTitle() {
        return "专业门类";
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
