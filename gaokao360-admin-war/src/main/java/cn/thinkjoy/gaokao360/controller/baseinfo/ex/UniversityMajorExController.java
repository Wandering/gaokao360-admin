/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorController.java 2015-12-30 15:19:55 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.IDataDictService;
import cn.thinkjoy.gaokao360.service.common.IMajorService;
import cn.thinkjoy.gaokao360.service.common.IUniversityService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityMajorExService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class UniversityMajorExController extends BaseController<IUniversityMajorExService>{


    @Autowired
    private IUniversityMajorExService universityMajorExService;

    @Autowired
    private IMajorService majorService;

    @Autowired
    private IUniversityService universityService;

    @Autowired
    private IDataDictService dataDictService;

    /**
     * 查询所有专业名称
     * @return
     */
    @RequestMapping(value="/getMajoredNameList")
    @ResponseBody
    public List getMajoredNameList(){
        Map<String,Object> condition = Maps.newHashMap();
        Map<String,Object> selector = Maps.newHashMap();
        selector.put("id","id");
        selector.put("majorName","majorName");
        return  majorService.queryList(condition,null,null,selector);
    }

    /**
     * 查询所有学校名称
     * @return
     */
    @RequestMapping(value="/getUniversityNameList")
    @ResponseBody
    public List getUniversityNameList(){
        Map<String,Object> condition = Maps.newHashMap();
        Map<String,Object> selector = Maps.newHashMap();
        selector.put("id","id");
        selector.put("name","name");
        return  universityService.queryList(condition,null,null,selector);
    }

    /**
     * 字典表通用查询
     * @param type
     * @return
     */
    @RequestMapping(value="/getDataDictList",method = RequestMethod.GET)
    @ResponseBody
    public List getDataDictList(@RequestParam(value = "type",required = true)String type){
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("type",type);
        Map<String,Object> selector = Maps.newHashMap();
        selector.put("dictId","dictId");
        selector.put("name","name");
        return  dataDictService.queryList(condition,"dictId", SqlOrderEnum.ASC.toString(),selector);
    }

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/universitymajor")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/universitymajors")
    @ResponseBody
    public BizData4Page findAllUniversityMajors(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IUniversityMajorExService getMainService() {
        return universityMajorExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "universitymajor";
    }

    @Override
    protected String getViewTitle() {
        return "院校专业信息";
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
