/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ProvinceController.java 2015-12-07 11:26:48 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.IExpertReservationOrderService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertReservationOrderExService;
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
@RequestMapping(value="/admin/zgkadmin/ex")
public class ExpertReservationOrderExController extends BaseController<IExpertReservationOrderExService> {


    @Autowired
    private IExpertReservationOrderExService expertReservationOrderExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/expertreservationorder")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/expertreservationorders")
    @ResponseBody
    public BizData4Page findAllExpertReservationOrders(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
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
    protected IExpertReservationOrderExService getMainService() {
        return expertReservationOrderExService;
    }

    @Override
    protected String getBizSys() {
        return "zgkadmin/ex";
    }

    @Override
    protected String getMainObjName() {
        return "expertreservationorder";
    }

    @Override
    protected String getViewTitle() {
        return "订单状态管理";
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
