/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionController.java 2015-12-28 18:05:26 $
 */

package cn.thinkjoy.gaokao360.controller;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.service.IProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/admin/zgk")
public class ProfessionController extends BaseController<IProfessionService> {


    @Autowired
    private IProfessionService professionService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/profession")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/professions")
    @ResponseBody
    public BizData4Page findAllProfessions(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IProfessionService getMainService() {
        return professionService;
    }

    @Override
    protected String getBizSys() {
        return "zgk";
    }

    @Override
    protected String getMainObjName() {
        return "profession";
    }

    @Override
    protected String getViewTitle() {
        return "职业信息";
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
