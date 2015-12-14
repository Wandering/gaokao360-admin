/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  PolicyInterpretationController.java 2015-12-04 09:11:36 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.managerui.controller.AbstractAdminController;
import cn.thinkjoy.gaokao360.service.IPolicyInterpretationService;
import cn.thinkjoy.gaokao360.service.ex.IPolicyInterpretationExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class PolicyInterpretationExController extends AbstractAdminController<IPolicyInterpretationExService>{


    @Autowired
    private IPolicyInterpretationExService policyInterpretationExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/policyinterpretation")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }


    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/policyinterpretations")
    @ResponseBody
    public BizData4Page findAllPolicyInterpretations(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IPolicyInterpretationExService getMainService() {
        return policyInterpretationExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "policyinterpretation";
    }

    @Override
    protected String getViewTitle() {
        return "政策解读管理";
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
