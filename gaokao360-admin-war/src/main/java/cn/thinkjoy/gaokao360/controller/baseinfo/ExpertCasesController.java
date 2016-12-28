/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertCasesController.java 2016-12-26 11:24:38 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo;

import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.IExpertCasesService;
import cn.thinkjoy.common.managerui.dao.IResourceGridDAO;
import cn.thinkjoy.common.managerui.service.IResourceGridService;
import cn.thinkjoy.common.domain.view.BizData4Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.thinkjoy.common.managerui.controller.AbstractAdminController;

@Controller
@RequestMapping(value="/admin/zgkadmin")
public class ExpertCasesController extends BaseController<IExpertCasesService> {


    @Autowired
    private IExpertCasesService expertCasesService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/expertcases")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/expertcasess")
    @ResponseBody
    public BizData4Page findAllExpertCasess(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IExpertCasesService getMainService() {
        return expertCasesService;
    }

    @Override
    protected String getBizSys() {
        return "zgkadmin";
    }

    @Override
    protected String getMainObjName() {
        return "expertcases";
    }

    @Override
    protected String getViewTitle() {
        return "智高考签约专家服务案例";
    }

    @Override
    protected String getParentTitle() {
        return "智高考签约专家服务案例";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
}
