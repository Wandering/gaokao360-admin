
/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  gaokao360ServiceMaps.java 2015-11-30 22:45:23 $
 */

package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.service.IBaseService;


import cn.thinkjoy.gaokao360.service.*;
import cn.thinkjoy.gaokao360.service.ex.IAuditoriumService;
import cn.thinkjoy.gaokao360.service.ex.IGkPsychologyService;
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
    @Autowired
    private IVideoClassifyService videoClassifyService;

    @Autowired
    private IVideoCourseService videoCourseService;

    @Autowired
    private IVideoSectionService videoSectionService;
    @Autowired
    private IAdmissionBatchService admissionBatchService;
    @Autowired
    private IAuditoriumService auditoriumService;
    @Autowired
    private IGkPsychologyService gkPsychologyService;

    @PostConstruct
    public void init(){
        super.init();
        serviceMap.put("gkinformationgkhot",gkinformationGkhotService);
        serviceMap.put("examinationpaper",examinationPaperService);
        serviceMap.put("policyinterpretation",policyInterpretationService);
        serviceMap.put("videoclassify",videoClassifyService);
        serviceMap.put("videocourse",videoCourseService);
        serviceMap.put("videosection",videoSectionService);
        serviceMap.put("admissionbatch",admissionBatchService);
        serviceMap.put("auditorium",auditoriumService);
        serviceMap.put("gkPsychology",gkPsychologyService);
    }

}
