/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: basedata
 * $Id:  ClassesController.java 2015-07-30 13:27:41 $
 */

package cn.thinkjoy.gaokao360.controller;


import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.managerui.controller.AbstractAdminController;
import cn.thinkjoy.common.managerui.dao.IResourceActionDAO;
import cn.thinkjoy.common.managerui.domain.Resource;
import cn.thinkjoy.common.managerui.domain.ResourceGrid;
import cn.thinkjoy.common.managerui.service.IResourceActionService;
import cn.thinkjoy.common.managerui.service.IRoleService;
import cn.thinkjoy.gaokao360.common.ImportExcelUtil;
import cn.thinkjoy.gaokao360.common.MenuUtils;
import cn.thinkjoy.gaokao360.common.ServiceMaps;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;


public abstract class BaseController<T> extends AbstractAdminController {
    @Autowired
    IResourceActionService resourceActionService;

    protected HttpServletRequest request;

    protected HttpServletResponse response;


    @Autowired
    private ServiceMaps serviceMaps;
    @Override
    protected void enhanceModelAndView(HttpServletRequest request, ModelAndView mav) {
        List<Resource> resourceList = (List) mav.getModel().get("resources");
        mav.addObject("resources", MenuUtils.getTreeMenu(resourceList));

        List<ResourceGrid> resourceGridList = (List) mav.getModel().get("cols");
        MenuUtils.setGridValue(resourceGridList);
        mav.addObject("cols", resourceGridList);

        if(getBizSys().equals("ucm") && getMainObjName().equals("index")) {

        } else {
            Set<String> set = (Set<String>) mav.getModel().get("actions");
            if (CollectionUtils.isEmpty(set) || set.size() == 0) {
                mav.setViewName("blank");
            }
        }

        String isframe =  request.getParameter("isframe");
        if(!StringUtils.isEmpty(isframe))
            mav.addObject("isframe", isframe);
        else
            mav.addObject("isframe", "true");
    }

    public Map<String,Object> doImport(MultipartFile file,String mainObj){
        String handleString=null;
        long startTime = System.currentTimeMillis();
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                // 文件保存路径
                // FIXME
                // 避免使用request session
                String filePath = request.getSession().getServletContext().getRealPath("/") + "/upload/"
                        + originalFilename;


                if(originalFilename.endsWith("xls") || originalFilename.endsWith("xlsx")){
                    //Map<String, Object> datas =  OldExcelUtil;
                    // 转存文件
                    file.transferTo(new File(filePath));
                    //daoMaps.get(mainObj).insert();
                }else {
                    throw  new BizException("","不是标准的Excel文件");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                List list= ImportExcelUtil.doImport(file);
                //处理获得的list
                innerHandleImport(list,mainObj);
            } catch (Exception e) {
                throw  new BizException("","读取数据异常");
            }
        }
        long endTime = System.currentTimeMillis();

        Map<String,Object> map = new HashMap<>();
        map.put("开始时间",startTime);
        map.put("结束时间",endTime);
        map.put("耗时",endTime-startTime);
        map.put("错误信息",handleString);
        return map;
    }

    protected String  innerHandleImport(List<Map<String,String>> list,String mainObj){
        return "true";
    }

    public final List getList(List<Map<String,String>> list){
        Map<String,String> corrMap=this.corrMap();
        List<Map<String,Object>> list1 = new ArrayList<>();
        for(Map map : list){
            Map<String,Object> keyMap=new HashMap<>();
            for(String s:corrMap.keySet()){
                if(map.containsKey(s)){
                    keyMap.put(corrMap.get(s),map.get(s));
                }
                list1.add(keyMap);
            }
        }
        return list1;
    }

    protected Map<String,String> corrMap(){
        return new HashMap<>();
    }

}
