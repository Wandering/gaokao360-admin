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
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.gaokao360.common.AreaMaps;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.service.common.ex.IGKUpdateUserScoreService;
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
public class GKUpdateUserScoreController extends BaseController {


    @Autowired
    private IGKUpdateUserScoreService gkUpdateUserScoreService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/userScore")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){
        return doRenderMainView(request, response);
    }
    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/userScores")
    @ResponseBody
    public BizData4Page findAllAgents(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    @RequestMapping(value="/getUserScore")
    @ResponseBody
    public Map<String,Object> getUserScore(@RequestParam String account){
        Map<String,Object> map=gkUpdateUserScoreService.getUserScore(account);
        if(map==null){
            return new HashMap<>();
        }
        return map;
    }

    @RequestMapping(value="/resetUserScore")
    @ResponseBody
    public boolean resetUserScore(@RequestParam String id){
        return gkUpdateUserScoreService.resetUserScore(id);
    }

    @Override
    protected IPageService getMainService() {
        return null;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "userScore";
    }

    @Override
    protected String getViewTitle() {
        return "重置用户分数";
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
