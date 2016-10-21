/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ScheduleController.java 2015-12-16 10:49:57 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.SearchField;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.domain.ExpertInfo;
import cn.thinkjoy.gaokao360.service.common.IExpertAppraiseService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertAppraiseExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping(value="/admin/zgkadmin/ex")
public class ExpertAppraiseExController extends BaseController<IExpertAppraiseExService> {


    @Autowired
    private IExpertAppraiseExService expertAppraiseExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/expertappraise")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/expertappraises")
    @ResponseBody
    public BizData4Page findAllExpertAppraises(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }



    /**
     * 查询所有专家
     * @return
     */
    @RequestMapping(value="/queryAllExpert")
    @ResponseBody
    public List<ExpertInfo> queryAllExpert(){

        return expertAppraiseExService.getAllExpert();
    }



    /**
     * 查询专家对应的服务
     * @return
     */
    @RequestMapping(value="/queryServiceTypeByExpert")
    @ResponseBody
    public List<Map<String,Object>> queryServiceTypeByExpert(@RequestParam Long expertId){

        return expertAppraiseExService.getServiceTypeByExpert(expertId);
    }


    /**
     * 审核通过
     * @return
     */
    @RequestMapping(value="/auditPass")
    @ResponseBody
    public boolean auditPass(@RequestParam Long id){

        return expertAppraiseExService.auditPass(id);
    }


    @Override
    protected Map<String, Object> makeQueryCondition(HttpServletRequest request, HttpServletResponse response, String uri) {
        Map<String, Object> map = super.makeQueryCondition(request, response, uri);
        SearchField field = new SearchField();
        field.setData("isChecked");
        field.setOp("=");
        field.setData("2");
        map.put("isChecked",field);
        return map;
    }

    @Override
    protected IExpertAppraiseExService getMainService() {
        return expertAppraiseExService;
    }

    @Override
    protected String getBizSys() {
        return "zgkadmin/ex";
    }

    @Override
    protected String getMainObjName() {
        return "expertappraise";
    }

    @Override
    protected String getViewTitle() {
        return "服务评价管理";
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
