
/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  gaokao360ServiceMaps.java 2015-11-30 22:45:23 $
 */

package cn.thinkjoy.gaokao360.common;


import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.gaokao360.service.common.*;
import cn.thinkjoy.gaokao360.service.common.ex.*;
import cn.thinkjoy.gaokao360.service.differentiation.*;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IExaminationPaperExService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IGkPsychologyService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IGkinformationGkhotExService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IPolicyInterpretationExService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by shurrik on 14-9-24.
 */
@Service("ServiceMaps")
public class ServiceImplMaps {

    @Autowired
    private IMajoredService majoredService;
    @Autowired
    private IMajorService majorService;
    @Autowired
    private IMajorDetailService majorDetailService;

    @Autowired
    private IUniversityMajorExService universityMajorExService;

    @Autowired
    private IUniversityMajorEnrollingExService universityMajorEnrollingExService;

    @Autowired
    private IUniversityDetailService universityDetailService;

    @Autowired
    private IMajoredExService majoredExService;

    protected final Map<String, IBaseService> serviceMap = Maps.newHashMap();

    @PostConstruct
    public void init(){
//        super.init();

        serviceMap.put("majoredService",majoredService);
        serviceMap.put("majoredExService",majoredExService);
        serviceMap.put("majorDetailService",majorDetailService);
        serviceMap.put("majorService",majorService);
        serviceMap.put("universityMajorExService",universityMajorExService);
        serviceMap.put("universityMajorEnrollingExService",universityMajorEnrollingExService);
        serviceMap.put("universityDetailService",universityDetailService);
    }

    public IBaseService get(String mainObj){
        return serviceMap.get(mainObj);
    }
}
