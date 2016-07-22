/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorEnrollingPlanDAO.java 2016-04-29 15:38:17 $
 */
package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.dto.UniversityMajorEnrollingPlanExDTO;

public interface IUniversityMajorEnrollingPlanExDAO extends IBaseDAO<UniversityMajorEnrollingPlanExDTO>{
	
    boolean universityNameExist(String universityName);
}
