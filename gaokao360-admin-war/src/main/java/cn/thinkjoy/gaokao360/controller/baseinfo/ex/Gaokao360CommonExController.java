/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: basedata
 * $Id:  ClassesController.java 2015-07-29 12:04:59 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.managerui.controller.AbstractCommonController;
import cn.thinkjoy.common.managerui.controller.helpers.BaseServiceMaps;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.gaokao360.common.ServiceMaps;
import cn.thinkjoy.gaokao360.controller.baseinfo.ProvinceController;
import cn.thinkjoy.gaokao360.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class Gaokao360CommonExController extends AbstractCommonController {
    @Autowired
    private ServiceMaps serviceMaps;
    @Autowired
    private IProvinceService provinceService;

    @RequestMapping(value="/getProvince")
    @ResponseBody
    public List getProvince(){
        return  provinceService.findAll();
    }
    @Override
    protected BaseServiceMaps getServiceMaps() {
        return serviceMaps;
    }

    @Override
    protected IBaseService getExportService() {
        return null;
    }

    @Override
    public boolean getEnableDataPerm() {
        return false;
    }
}
