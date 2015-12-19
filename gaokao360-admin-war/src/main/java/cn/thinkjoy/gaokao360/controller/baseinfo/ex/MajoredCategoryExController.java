/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredCategoryController.java 2015-12-16 16:48:40 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.IMajoredCategoryService;
import cn.thinkjoy.gaokao360.service.ex.IMajoredCategoryExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class MajoredCategoryExController extends BaseController<IMajoredCategoryExService> {


    @Autowired
    private IMajoredCategoryExService majoredCategoryExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/majoredcategory")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/majoredcategorys")
    @ResponseBody
    public BizData4Page findAllMajoredCategorys(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IMajoredCategoryExService getMainService() {
        return majoredCategoryExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "majoredcategory";
    }

    @Override
    protected String getViewTitle() {
        return "专业关系";
    }

    @Override
    protected String getParentTitle() {
        return "基础信息";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
}
