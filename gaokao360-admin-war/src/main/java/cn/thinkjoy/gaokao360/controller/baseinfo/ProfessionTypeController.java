/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionTypeController.java 2015-12-28 18:05:28 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.IProfessionTypeService;
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
@RequestMapping(value="/admin/zgk")
public class ProfessionTypeController extends BaseController<IProfessionTypeService> {


    @Autowired
    private IProfessionTypeService professionTypeService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/professiontype")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/professiontypes")
    @ResponseBody
    public BizData4Page findAllProfessionTypes(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IProfessionTypeService getMainService() {
        return professionTypeService;
    }

    @Override
    protected String getBizSys() {
        return "zgk";
    }

    @Override
    protected String getMainObjName() {
        return "professiontype";
    }

    @Override
    protected String getViewTitle() {
        return "职业分类";
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
