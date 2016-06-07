/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  AgentController.java 2015-12-15 17:52:12 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.SearchField;
import cn.thinkjoy.common.domain.SearchFilter;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.enumration.SearchEnum;
import cn.thinkjoy.common.service.IDataPermService;
import cn.thinkjoy.gaokao360.common.AreaMaps;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.differentiation.IAgentService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IAgentExService;
import cn.thinkjoy.zgk.common.QueryUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
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
@Scope("prototype")
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
    protected Map<String, Object> makeQueryCondition(HttpServletRequest request,HttpServletResponse response, String uri){
        //获取参数
        Map<String, Object> conditions = Maps.newHashMap();


        String sidx = request.getParameter("sidx");
        String sord = request.getParameter("sord");
        conditions.put("sort", sidx + " " + sord);

        String searchField = request.getParameter("searchField");
        String searchOper = request.getParameter("searchOper");
        String searchString = request.getParameter("searchString");
        conditions.put(searchField, searchString);

        //需要在controller处  对 是否有数据权限进行设定和处理
        if(getEnableDataPerm()) { //需要进行数据权限处理
            String whereSql = dataPermService.makeDataPermSql(uri);
            if (whereSql != null) {
                conditions.put("whereSql", whereSql);
            }
        }

        //增加搜索
        String filters = request.getParameter("filters");
        if (StringUtils.isNotBlank(filters)) {
            SearchFilter searchFilter = JSON.parseObject(filters,
                    SearchFilter.class);
            enhanceSearchFilter(searchFilter);
            if (!CollectionUtils.isEmpty(searchFilter.getRules())) {
                conditions.put("groupOp", searchFilter.getGroupOp());
                for (SearchField field : searchFilter.getRules()) {
                    if(field.getOp().equals(SearchEnum.lk.getCode()))
                        field.setData("%"+String.valueOf(field.getData()).trim()+"%");
                    field.setOp(SearchEnum.codeOf(field.getOp()).getDes());
                    conditions.put(getField(field.getField()), field);
                }
            }

        }
        enhanceSearchFilter(conditions);
        return conditions;
    }

    /**
     * 增强 或 改变 过滤条件内容
     * @param searchFilter
     */
    protected void enhanceSearchFilter(Map<String,Object> conditions) {
        if(conditions.isEmpty()){
            conditions=new HashMap<>();
        }
        Long area=areaMaps.getAreaId();
        if(area!=null) {
            QueryUtil.setMapOp(conditions, "areaId", "=", area);
        }
    }
    private String getField(String field)
    {
        return field.replace(".","");
    }
    @Autowired
    protected IDataPermService dataPermService;
    @Autowired
    AreaMaps areaMaps;
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
        return "招办联系电话";
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
