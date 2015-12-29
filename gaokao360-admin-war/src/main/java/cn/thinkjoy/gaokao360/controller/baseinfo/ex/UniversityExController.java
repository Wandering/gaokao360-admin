/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityController.java 2015-12-16 18:39:46 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.IUniversityDictService;
import cn.thinkjoy.gaokao360.service.IUniversityService;
import cn.thinkjoy.gaokao360.service.ex.IUniversityExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class UniversityExController extends BaseController<IUniversityExService> {


    @Autowired
    private IUniversityDictService universityDictService;
    @Autowired
    private IUniversityExService universityExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/university")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/universitys")
    @ResponseBody
    public BizData4Page findAllUniversitys(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    /**
     * 院校基本信息学历层次
     * @return
     */
    @RequestMapping(value="/eduLevel",method = RequestMethod.GET)
    @ResponseBody
    public List eduLevel(){

        Map<String,Object> map = new HashMap<>();
        map.put("type","EDULEVEL");
        return universityDictService.queryList(map,"id","asc");
    }
    /**
     * 院校基本信息学历层次
     * @return
     */
    @RequestMapping(value="/getUniversityByName",method = RequestMethod.POST)
    @ResponseBody
    public List getUniversityByName(String name){
        return universityExService.getUniversityByName(name);
    }
    @Override
    protected IUniversityExService getMainService() {
        return universityExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "university";
    }

    @Override
    protected String getViewTitle() {
        return " 院校基本信息";
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
