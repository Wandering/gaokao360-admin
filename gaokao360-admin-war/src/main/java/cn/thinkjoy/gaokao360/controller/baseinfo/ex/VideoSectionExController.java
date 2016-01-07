/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoSectionController.java 2015-12-04 13:22:05 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoSectionService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IVideoSectionExService;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class VideoSectionExController extends BaseController<IVideoSectionExService> {


    @Autowired
    private IVideoSectionExService videoSectionExService;

    @Autowired
    private IVideoSectionService videoSectionService;
    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/videosection")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取视频列表
     * @return
     */
    @RequestMapping(value="/getVideoSection",method = RequestMethod.POST)
    @ResponseBody
    public Object getVideoSection(@RequestParam("id")String id){
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",id);
        return  videoSectionService.queryList(map,"sectionSort","asc");
    }
    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/insertVideoSection")
    @ResponseBody
    public Long insertVideoSection(HttpServletRequest request,HttpServletResponse response){
        //TODO   支持多对象保存
        Map<String, Object> dataMap = Maps.newHashMap();
        String prop = null;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            dataMap.put(prop, request.getParameter(prop));
        }
        videoSectionService.insertMap(dataMap);
        return videoSectionExService.queryByMaxId();
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/videosections")
    @ResponseBody
    public BizData4Page findAllVideoSections(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    public VideoSection queryByCourseId(String courseId){
        return videoSectionExService.queryByCourseId(courseId);
    }
    @Override
    protected IVideoSectionExService getMainService() {
        return videoSectionExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "videosection";
    }

    @Override
    protected String getViewTitle() {
        return "视频描述";
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
