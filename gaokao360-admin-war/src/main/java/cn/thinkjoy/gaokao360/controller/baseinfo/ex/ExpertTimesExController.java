/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  AgentController.java 2015-12-15 17:52:12 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.service.IDataPermService;
import cn.thinkjoy.gaokao360.common.AreaMaps;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.IExpertServiceDaysService;
import cn.thinkjoy.gaokao360.service.common.IExpertServiceTimesService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IAgentExService;
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
@RequestMapping(value="/admin/zgkadmin/ex")
public class ExpertTimesExController extends BaseController<IExpertServiceTimesService> {

    @Autowired
    private IExpertServiceTimesService expertServiceTimesService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/expertservicetime")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/expertservicetimes")
    @ResponseBody
    public BizData4Page findAllAgents(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }


    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/save")
    @ResponseBody
    public void save(){
        return;
    }


    @Override
    protected IExpertServiceTimesService getMainService() {
        return expertServiceTimesService;
    }

    @Override
    protected String getBizSys() {
        return "zgkadmin/ex";
    }

    @Override
    protected String getMainObjName() {
        return "expertservicetime";
    }

    @Override
    protected String getViewTitle() {
        return "专家时间设置";
    }

    @Override
    protected String getParentTitle() {
        return "专家服务";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
}
