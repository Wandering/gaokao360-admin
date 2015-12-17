/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  AgentController.java 2015-12-15 17:52:12 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.IAgentService;
import cn.thinkjoy.gaokao360.service.ex.IAgentExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class AgentExController extends BaseController<IAgentExService> {


    @Autowired
    private IAgentExService agentExService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/agent")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/agents")
    @ResponseBody
    public BizData4Page findAllAgents(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/agent/{mainObj}doExcelImport")
    @ResponseBody
    public Map<String,Object> doExcelImport(@RequestParam("file") MultipartFile file, @PathVariable String mainObj){

        return this.doImport(file,mainObj);
    }
    @Override
    protected IAgentExService getMainService() {
        return agentExService;
    }

    /**
     * 在这里写数据处理
     * @param list
     * @param mainObj
     * @return
     */
    @Override
    protected String innerHandleImport(List<Map<String, String>> list, String mainObj) {
        try{
            this.getList(list);
        }catch (Exception e){

        }
        return "";
    }

    @Override
    protected Map<String, String> corrMap() {
        Map<String,String>  map = new HashMap<>();
        map.put("年份","yesrs");
        map.put("月份","manth");
        map.put("内容","content");
        map.put("省份","areaId");
        return map;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "agent";
    }

    @Override
    protected String getViewTitle() {
        return "招办电话";
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
