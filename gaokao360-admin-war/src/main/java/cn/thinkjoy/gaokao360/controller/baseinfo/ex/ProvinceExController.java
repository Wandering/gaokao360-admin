/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ProvinceController.java 2015-12-07 11:26:48 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.IProvinceService;
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
public class ProvinceExController extends BaseController<IProvinceService> {


    @Autowired
    private IProvinceService provinceService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/province")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/provinces")
    @ResponseBody
    public BizData4Page findAllProvinces(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    /**
     * 查询所有的省份
     * @return
     */
    @RequestMapping(value="/getProvince")
    @ResponseBody
    public List getProvince(){
        return  provinceService.findAll();
    }
    @Override
    protected IProvinceService getMainService() {
        return provinceService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "province";
    }

    @Override
    protected String getViewTitle() {
        return "省份管理";
    }

    @Override
    protected String getParentTitle() {
        return "省份模块";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
}
