
/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  gaokao360ServiceMaps.java 2015-11-30 22:45:23 $
 */

package cn.thinkjoy.gaokao360.common;


import cn.thinkjoy.gaokao360.service.common.*;
import cn.thinkjoy.gaokao360.service.differentiation.*;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IExaminationPaperExService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IGkPsychologyService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IGkinformationGkhotExService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IPolicyInterpretationExService;
import cn.thinkjoy.gaokao360.service.common.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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

    @Autowired
    private IExaminationPaperExService examinationPaperExService;
    @Autowired
    private IPolicyInterpretationExService policyInterpretationExService;
    @Autowired
    private IGkinformationGkhotExService gkinformationGkhotExService;

    @Autowired
    private IScheduleService scheduleService;
    @Autowired
    private IAgentService agentService;
    @Autowired
    private IAreabatchLineService areabatchLineService;

    @Autowired
    private IUniversityExService universityExService;
    @Autowired
    private IUniversityService universityService;

    @Autowired
    private IMajoredCategoryService majoredCategoryService;

    @Autowired
    private IMajoredCategoryExService majoredCategoryExService;

    @Autowired
    private IMajoredService majoredService;
    @Autowired
    private IVocabularyService vocabulary;
    @Autowired
    private IUniversityEnrollingService universityEnrollingService;
    @Autowired
    private IMajorService majorService;
    @Autowired
    private IMajorDetailService majorDetailService;

    @Autowired
    private IProfessionService professionService;

    @Autowired
    private IProfessionDetailService professionDetailService;

    @Autowired
    private IProfessionTypeService professionTypeService;

    @Autowired
    private IUniversityMajorExService universityMajorExService;

    @Autowired
    private IUniversityMajorEnrollingExService universityMajorEnrollingExService;

    @Autowired
    private IMajorBaseService majorBaseService;


    @Autowired
    private IMajoredExService majoredExService;

    @Autowired
    private ISchoolBaseService schoolBaseService;

    @Autowired
    private IExpertInfoService expertInfoService;

    @Autowired
    private IExpertAppraiseService expertAppraiseService;

    @Autowired
    private IExpertReservationOrderService expertReservationOrderService;

    @PostConstruct
    public void init(){
        super.init();
        serviceMap.put("gkinformationgkhot", gkinformationGkhotService);
        serviceMap.put("examinationpaper",examinationPaperService);
        serviceMap.put("policyinterpretation",policyInterpretationService);
        serviceMap.put("videoclassify",videoClassifyService);
        serviceMap.put("videocourse",videoCourseService);
        serviceMap.put("videosection",videoSectionService);
        serviceMap.put("admissionbatch",admissionBatchService);
        serviceMap.put("auditorium",auditoriumService);
        serviceMap.put("auditorium",auditoriumService);
        serviceMap.put("videocourseex",videoCourseService);
        serviceMap.put("gkinformationgkhotex",gkinformationGkhotExService);
        serviceMap.put("policyinterpretationex",policyInterpretationExService);
        serviceMap.put("auditoriumex",auditoriumService);
        serviceMap.put("gkpsychologyex",gkPsychologyService);
        serviceMap.put("schedule",scheduleService);
        serviceMap.put("agent",agentService);
        serviceMap.put("areabatchline",areabatchLineService);
        serviceMap.put("universityex",universityExService);
        serviceMap.put("university",universityService);
        serviceMap.put("majoredcategory",majoredCategoryService);
        serviceMap.put("majoredcategoryex",majoredCategoryExService);
        serviceMap.put("vocabulary",vocabulary);
        serviceMap.put("universityenrolling",universityEnrollingService);
        serviceMap.put("profession",professionService);
        serviceMap.put("professiondetail",professionDetailService);
        serviceMap.put("professiontype",professionTypeService);
        serviceMap.put("expertinfo",expertInfoService);
        serviceMap.put("expertappraise",expertAppraiseService);
        serviceMap.put("expertreservationorder",expertReservationOrderService);

        serviceMap.put("majored",majoredService);
        serviceMap.put("majoredex",majoredExService);
        serviceMap.put("majorbase",majorBaseService);
        serviceMap.put("schoolbase",schoolBaseService);
        serviceMap.put("majorDetail",majorDetailService);
        serviceMap.put("major",majorService);
        serviceMap.put("universitymajor",universityMajorExService);
        serviceMap.put("universitymajorenrolling",universityMajorEnrollingExService);
    }

}
