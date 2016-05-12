/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  GkinformationGkhotController.java 2015-12-03 14:24:46 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo;

import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.differentiation.IGkinformationGkhotService;
import cn.thinkjoy.common.domain.view.BizData4Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping(value="/admin/gaokao360")
public class GkinformationGkhotController extends BaseController<IGkinformationGkhotService> {


    @Autowired
    private IGkinformationGkhotService gkinformationGkhotService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/gkinformationgkhot")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/gkinformationgkhots")
    @ResponseBody
    public BizData4Page findAllGkinformationGkhots(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected BizData4Page doPage(HttpServletRequest request, HttpServletResponse response) {
        Integer page = 1;
        if(request.getParameter("page") != null) {
            page = Integer.valueOf(request.getParameter("page"));
        }
        Integer rows = 10;
        if(request.getParameter("rows") != null) {
            rows = Integer.valueOf(request.getParameter("rows"));
        }

        String uri = request.getRequestURI().substring(0, request.getRequestURI().length() - 1);
        //获取参数
        Map<String, Object> conditions = makeQueryCondition(request, response, uri);

        enhancePageConditions(request,conditions);

        return getMainService().queryPageByDataPerm(uri, conditions, page, (page - 1) * rows, rows, "createDate", SqlOrderEnum.DESC);
    }


    @Override
    protected IGkinformationGkhotService getMainService() {
        return gkinformationGkhotService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360";
    }

    @Override
    protected String getMainObjName() {
        return "gkinformationgkhot";
    }

    @Override
    protected String getViewTitle() {
        return "高考热点信息";
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
