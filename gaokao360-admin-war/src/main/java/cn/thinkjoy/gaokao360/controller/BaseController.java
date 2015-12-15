/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: basedata
 * $Id:  ClassesController.java 2015-07-30 13:27:41 $
 */

package cn.thinkjoy.gaokao360.controller;


import cn.thinkjoy.common.managerui.controller.AbstractAdminController;
import cn.thinkjoy.common.managerui.domain.Resource;
import cn.thinkjoy.common.managerui.domain.ResourceGrid;
import cn.thinkjoy.gaokao360.common.MenuUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


public abstract class BaseController<T> extends AbstractAdminController {
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

}
