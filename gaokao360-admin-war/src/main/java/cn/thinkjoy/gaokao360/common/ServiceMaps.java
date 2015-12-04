
/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  gaokao360ServiceMaps.java 2015-11-30 22:45:23 $
 */

package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.service.IBaseService;


import cn.thinkjoy.gaokao360.service.IExaminationPaperService;
import cn.thinkjoy.gaokao360.service.IGkinformationGkhotService;
import cn.thinkjoy.gaokao360.service.IPolicyInterpretationService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

import cn.thinkjoy.common.managerui.controller.helpers.BaseServiceMaps;

/**
 * Created by shurrik on 14-9-24.
 */
@Service("gaokao360ServiceMaps")
public class ServiceMaps extends BaseServiceMaps{

    @Autowired
    private IGkinformationGkhotService gkinformationGkhotService;
    @Autowired
    private IExaminationPaperService examinationPaperService;
    @Autowired
    private IPolicyInterpretationService policyInterpretationService;


    @PostConstruct
    public void init(){
        super.init();
        serviceMap.put("gkinformationGkhot",gkinformationGkhotService);
        serviceMap.put("examinationPaperService",examinationPaperService);
        serviceMap.put("policyInterpretationService",policyInterpretationService);
    }

}
