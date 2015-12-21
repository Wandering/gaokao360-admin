/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredCategoryController.java 2015-12-16 16:48:40 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.domain.GkBaseDomain;
import cn.thinkjoy.gaokao360.domain.MajoredCategory;
import cn.thinkjoy.gaokao360.service.IMajoredCategoryService;
import cn.thinkjoy.gaokao360.service.ex.IMajoredCategoryExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class MajoredCategoryExController extends BaseController<IMajoredCategoryExService> {


    @Autowired
    private IMajoredCategoryExService majoredCategoryExService;


    @Autowired
    private IMajoredCategoryService majoredCategoryService;
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


    /**
     * 获取学科门类
     * @return
     */
    @RequestMapping(value="/getMajoredCategoryList")
    @ResponseBody
    public List<GkBaseDomain> getMajoredCategoryList(){
        List<MajoredCategory> majoredCategories=majoredCategoryExService.queryList(new HashMap<String, Object>(),"id","asc");
        GkBaseDomain gkBaseDomain = null;
        List<GkBaseDomain> list=new ArrayList<>();
        for(MajoredCategory majoredCategory:majoredCategories){
            gkBaseDomain=new GkBaseDomain();
            gkBaseDomain.setName(majoredCategory.getName());
            gkBaseDomain.setId(majoredCategory.getId());
            list.add(gkBaseDomain);
        }

        return list;
    }

    /**
     * 根据PID获取学科门类
     * @param id
     * @return
     */
    @RequestMapping(value="/getMajoredCategoryByPid")
    @ResponseBody
    public List<MajoredCategory> getMajoredCategoryByPid(@RequestParam("id")String id){
        return majoredCategoryExService.queryListByParentId(id);
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
