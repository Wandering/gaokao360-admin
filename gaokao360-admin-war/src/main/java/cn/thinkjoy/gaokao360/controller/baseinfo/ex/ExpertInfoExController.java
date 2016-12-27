/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ScheduleController.java 2015-12-16 10:49:57 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.domain.ExpertInfo;
import cn.thinkjoy.gaokao360.service.common.IExpertInfoService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertInfoExService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping(value="/admin/zgkadmin/ex")
public class ExpertInfoExController extends BaseController<IExpertInfoExService> {

    @Autowired
    private IExpertInfoService expertInfoService;
    @Autowired
    private IExpertInfoExService expertInfoExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/expertinfo")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/expertinfos")
    @ResponseBody
    public BizData4Page findAllExpertInfos(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/queryExpertName")
    @ResponseBody
    public List<ExpertInfo> findAllExpertName(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> param = Maps.newHashMap();
        param.put("id","id");
        param.put("expertName","expertName");
        Map<String,Object> param2 = Maps.newHashMap();
        return expertInfoService.queryList(param2,"id","desc",param);
    }

    /***
     * 添加查询条件

     * @param
    request
     * @param conditions
     */
    @
            Override
    protected void enhancePageConditions(HttpServletRequest request, Map conditions) {
        super.enhancePageConditions(request, conditions);
        conditions.put("sortBy","desc");
        conditions.put("orderBy","id");
    }

    @Override
    protected IExpertInfoExService getMainService() {
        return expertInfoExService;
    }

    @Override
    protected String getBizSys() {
        return "zgkadmin/ex";
    }

    @Override
    protected String getMainObjName() {
        return "expertinfo";
    }

    @Override
    protected String getViewTitle() {
        return "个人简历下载";
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
