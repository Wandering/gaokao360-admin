/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  DataDictController.java 2015-12-29 10:29:59 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo;

import cn.thinkjoy.gaokao360.service.common.IDataDictService;
import cn.thinkjoy.common.domain.view.BizData4Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.thinkjoy.common.managerui.controller.AbstractAdminController;

@Controller
@Scope("prototype")
@RequestMapping(value="/admin/gaokao360")
public class DataDictController extends AbstractAdminController<IDataDictService>{


    @Autowired
    private IDataDictService dataDictService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/datadict")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/datadicts")
    @ResponseBody
    public BizData4Page findAllDataDicts(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @RequestMapping(value="/geBatch")
    @ResponseBody
    public List geBatch(){
        Map<String,Object> map = new HashMap<>();
        map.put("type","BATCHTYPE2");
        return dataDictService.queryList(map,"dictId","asc");
    }

    @RequestMapping(value="/getMajorType")
    @ResponseBody
    public List getMajorType(){
        Map<String,Object> map = new HashMap<>();
        map.put("type","UNIVERSITY_MAJOR_TYPE");
        return dataDictService.queryList(map,"dictId","asc");
    }
    @Override
    protected IDataDictService getMainService() {
        return dataDictService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360";
    }

    @Override
    protected String getMainObjName() {
        return "datadict";
    }

    @Override
    protected String getViewTitle() {
        return "字典表";
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
