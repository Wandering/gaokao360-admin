/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorEnrollingPlanService.java 2016-04-29 15:38:17 $
 */

package cn.thinkjoy.gaokao360.service.common.ex.impl;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.dao.ex.IUniversityMajorEnrollingPlanExDAO;
import cn.thinkjoy.gaokao360.dto.UniversityMajorEnrollingPlanExDTO;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityMajorEnrollingPlanExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("UniversityMajorEnrollingPlanExServiceImpl")
@Scope("prototype")
public class UniversityMajorEnrollingPlanExServiceImpl extends AbstractPageService<IBaseDAO<UniversityMajorEnrollingPlanExDTO>, UniversityMajorEnrollingPlanExDTO> implements IUniversityMajorEnrollingPlanExService<IBaseDAO<UniversityMajorEnrollingPlanExDTO>,UniversityMajorEnrollingPlanExDTO> {

    @Autowired
    private IUniversityMajorEnrollingPlanExDAO universityMajorEnrollingPlanExDAO;
    @Override
    public IBaseDAO<UniversityMajorEnrollingPlanExDTO> getDao() {
        return universityMajorEnrollingPlanExDAO;
    }
}
