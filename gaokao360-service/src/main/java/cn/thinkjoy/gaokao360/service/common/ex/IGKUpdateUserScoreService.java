/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  AdmissionBatchDAO.java 2015-12-09 16:15:38 $
 */
package cn.thinkjoy.gaokao360.service.common.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.AdmissionBatch;

import java.util.Map;

public interface IGKUpdateUserScoreService{

    Map<String,Object> getUserScore(String account);
    boolean resetUserScore(Object id);
}
