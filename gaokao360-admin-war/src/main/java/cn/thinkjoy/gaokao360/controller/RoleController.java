/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  RoleController.java 2015-11-30 22:45:17 $
 */

package cn.thinkjoy.gaokao360.controller;

import cn.thinkjoy.common.managerui.domain.Resource;
import cn.thinkjoy.common.managerui.domain.ResourceAction;
import cn.thinkjoy.common.managerui.domain.RoleResource;
import cn.thinkjoy.common.managerui.service.*;
import cn.thinkjoy.common.managerui.dao.IResourceGridDAO;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.utils.UserContext;
import cn.thinkjoy.dto.AssignDTO;
import cn.thinkjoy.dto.AssignDetailDTO;
import cn.thinkjoy.dto.ResourceDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.thinkjoy.common.managerui.controller.AbstractAdminController;

@Controller
@RequestMapping(value="/admin/gaokao360")
public class RoleController extends AbstractAdminController<IRoleService>{

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IResourceActionService resourceActionService;
    @Autowired
    private IRoleResourceService roleResourceService;
    @Autowired
    private IRoleService roleService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/role")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }
    /**
     * 给角色分配资源
     *
     * @param request
     * @param response
     * @param assign
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/role/assign")
    public Object assign(HttpServletRequest request,
                         HttpServletResponse response, AssignDTO assign) {

        String result = null;
        // 首页删除该角色的所有权限
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", assign.getObjId());
        roleResourceService.deleteByCondition(map);

        // 保存新的角色
        if (StringUtils.isNotBlank(assign.getResources())) {
            List<AssignDetailDTO> details = com.alibaba.fastjson.JSON
                    .parseArray(assign.getResources(), AssignDetailDTO.class);
            for (AssignDetailDTO assignDetail : details) {

                map.put("resourceId", assignDetail.getParentResourceId());
                map.put("resourceActionId", assignDetail.getResourceId());

                // 注意顶级父元素parentId ==0;
                RoleResource roleResource = new RoleResource();
                roleResource.setCreateDate(System.currentTimeMillis());
                roleResource.setCreator(UserContext.getCurrentUser().getId());
                roleResource.setRoleId(Integer.valueOf(assign.getObjId()));
                roleResource.setLastModDate(System.currentTimeMillis());
                roleResource.setLastModifier(UserContext.getCurrentUser().getId());
                roleResource.setStatus(1);

                Resource resource = resourceService.fetch(Long
                        .valueOf(assignDetail.getParentResourceId()));
                // 说明是顶级或者二级元素
                if (assignDetail.getParentResourceId() == 0 || resource == null
                        || resource.getParentId() == 0) {
                    roleResource.setResourceActionId(0);
                    roleResource.setResourceId(assignDetail.getResourceId());
                } else {
                    // 三级
                    roleResource.setResourceActionId(assignDetail
                            .getResourceId());
                    roleResource.setResourceId(assignDetail
                            .getParentResourceId());
                }

                roleResourceService.insert(roleResource);
            }
            result = "分配成功";
        }
        return result;
    }
    @RequestMapping(value = "/role/getAllResources")
    @ResponseBody
    public List<ResourceDTO> getAllResources(HttpServletRequest request,
                                             HttpServletResponse response) {

        List<ResourceDTO> resourceDTOs = Lists.newArrayList();
        String roleId = request.getParameter("roleId");

        // 获取所有父辈级菜单
        List<Resource> parentResources = resourceService
                .findList("parentId", 0);

        for (Resource resource : parentResources) {
            ResourceDTO resourceDTO = new ResourceDTO();
            resourceDTO.setId(resource.getId() + "");
            resourceDTO.setParentResourceId(0);
            resourceDTO.setResourceId(resource.getId());
            resourceDTO.setResourceName(resource.getName());

            List<Resource> childrens = resourceService.findList("parentId",
                    resource.getId());

            // 获取action

            for (Resource children : childrens) {
                // 获取二级资源
                ResourceDTO childrenDTO = new ResourceDTO();
                childrenDTO.setId(children.getId() + "");
                childrenDTO.setParentResourceId(resource.getId());
                childrenDTO.setResourceId(children.getId());
                childrenDTO.setResourceName(children.getName());

                List<ResourceAction> actions = resourceActionService.findList(
                        "resourceId", children.getId());
                if (CollectionUtils.isEmpty(actions)) {
                    //说明该三级菜单下没有二级菜单，这里判断该角色是否拥有该二级菜单

                    //判断该角色否有该二级菜单
                    Map<String, Object> map = new HashMap<>();
                    map.put("roleId", roleId);
                    map.put("resourceId", children.getId());
                    // 说明该权限属于该角色
                    RoleResource rr = roleResourceService.queryOne(map);
                    if (rr != null) {
                        childrenDTO.setRoleId(Long.valueOf(roleId));
                    }

                }

                for (ResourceAction action : actions) {
                    //获取三级资源
                    Map<String, Object> map = new HashMap<>();
                    map.put("roleId", roleId);
                    map.put("resourceActionId", action.getId());
                    map.put("resourceId", action.getResourceId());

                    // 说明该权限属于该角色
                    RoleResource rr = roleResourceService.queryOne(map);

                    ResourceDTO actionDTO = new ResourceDTO();
                    if (rr != null) {
                        actionDTO.setRoleId(Long.valueOf(roleId));
                    }

                    actionDTO.setId(action.getId() + "");
                    actionDTO.setParentResourceId(children.getId());
                    actionDTO.setResourceId(action.getId());
                    actionDTO.setResourceName(action.getName());

                    childrenDTO.getResourceInfos().add(actionDTO);
                }
                resourceDTO.getResourceInfos().add(childrenDTO);

            }
            resourceDTOs.add(resourceDTO);
        }

        return resourceDTOs;
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/roles")
    @ResponseBody
    public BizData4Page findAllRoles(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }

    @Override
    protected IRoleService getMainService() {
        return roleService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360";
    }

    @Override
    protected String getMainObjName() {
        return "role";
    }

    @Override
    protected String getViewTitle() {
        return "角色管理";
    }

    @Override
    protected String getParentTitle() {
        return "权限管理";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
}
