/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityDAO.java 2015-12-16 18:39:46 $
 */
package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.UniversityDict;
import cn.thinkjoy.gaokao360.dto.GkQueryDomain;
import cn.thinkjoy.gaokao360.dto.UniversityDTO;
import cn.thinkjoy.zgk.dto.UniversityEnrollingChartDTO;
import cn.thinkjoy.zgk.dto.UniversityMajorEnrollingPlanDTO;
import cn.thinkjoy.zgk.dto.UniversityPlanChartDTO;

import java.util.List;
import java.util.Map;

public interface IUniversityExDAO extends IBaseDAO<UniversityDTO>{
    Integer getMaxId();
    UniversityDict getDictByName(String name);
    Integer getDictMaxSort(String type);
    List<GkQueryDomain> getUniversityByName(String name);
    int insertDetail(Map<String, Object> entityMap);

    /**
     * 院校招生计划各年各类别招生统计显示
     * @param params
     * @return
     */
    List<UniversityPlanChartDTO> queryUniversityPlanChart(Map<String,Object> params);

    List<UniversityMajorEnrollingPlanDTO> getUniversityMajorEnrollingPlanList(Map<String,Object> params);

    List<UniversityEnrollingChartDTO> queryUniversityEnrollingChart(Map<String,Object> params);

    List<Map<String,Object>> queryPredictUniversityInfo(Map<String, Object> params);

    List<Map<String,Object>> queryPredictProbability(Map<String, Object> params);

    /**
     * 插入录取预测记录
     *
     * @param map
     */
    void insertPredictRecord(Map<String,Object> map);
}
