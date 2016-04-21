/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityServiceImpl.java 2015-12-16 18:39:46 $
 */
package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.dao.IUniversityDAO;
import cn.thinkjoy.gaokao360.dao.IUniversityDetailDAO;
import cn.thinkjoy.gaokao360.dao.ex.IUniversityExDAO;
import cn.thinkjoy.gaokao360.dto.UniversityDTO;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityExService;
import cn.thinkjoy.zgk.dto.UniversityEnrollingChartDTO;
import cn.thinkjoy.zgk.dto.UniversityMajorEnrollingPlanDTO;
import cn.thinkjoy.zgk.dto.UniversityPlanChartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("UniversityExServiceImpl")
@Scope("prototype")
public class UniversityExServiceImpl extends AbstractPageService<IBaseDAO<UniversityDTO>, UniversityDTO> implements IUniversityExService<IBaseDAO<UniversityDTO>,UniversityDTO>{
    @Autowired
    private IUniversityExDAO universityExDAO;
    @Autowired
    private IUniversityDAO universityDAO;

    @Autowired
    private IUniversityDetailDAO universityDetailDAO;

    @Override
    public IBaseDAO<UniversityDTO> getDao() {
        return universityExDAO;
    }

    public void insertUniversity(Map<String,Object> dataMap){
        universityDAO.insertMap(dataMap);
        Long lid = (Long)universityDAO.selectMaxId();
        dataMap.put("id",lid);
        universityExDAO.insertDetail(dataMap);
    }

    public void updateUniversity(Map<String,Object> dataMap){
        universityDAO.updateMap(dataMap);
        universityDetailDAO.updateMap(dataMap);
    }

    public void deleteUniversity(Map<String,Object> dataMap){
        universityDAO.deleteById(dataMap.get("id"));
        universityDetailDAO.deleteById(dataMap.get("id"));
    }

    @Override
    public List getUniversityByName(String name) {
        return universityExDAO.getUniversityByName(name);
    }

    /**
     * 院校招生计划各年各类别招生统计显示
     * @param params
     * @return
     */
    @Override
    public List<UniversityPlanChartDTO> queryUniversityPlanChart(Map<String, Object> params){
        return universityExDAO.queryUniversityPlanChart(params);
    }

    @Override
    public List<UniversityMajorEnrollingPlanDTO> getUniversityMajorEnrollingPlanList(Map<String,Object> params){
        return universityExDAO.getUniversityMajorEnrollingPlanList(params);
    }

    @Override
    public List<UniversityEnrollingChartDTO> queryUniversityEnrollingChart(Map<String, Object> params){
        return universityExDAO.queryUniversityEnrollingChart(params);
    }

    @Override
    public List<Map<String, Object>> getPredictUniversityInfo(Map<String, Object> params) {
        return universityExDAO.queryPredictUniversityInfo(params);
    }

    @Override
    public List<Map<String, Object>> getPredictProbability(Map<String, Object> params) {
        return universityExDAO.queryPredictProbability(params);
    }

//    @Override
//    public void insert(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void update(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void updateNull(BaseDomain entity) {
//
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//
//    @Override
//    public void deleteByProperty(String property, Object value) {
//
//    }
//
//    @Override
//    public BaseDomain fetch(Long id) {
//        return null;
//    }
//
//    @Override
//    public BaseDomain findOne(@Param("property") String property, @Param("value") Object value) {
//        return null;
//    }
//
//    @Override
//    public List findList(String property, Object value) {
//        return null;
//    }
//
//    @Override
//    public void deleteByCondition(Map condition) {
//
//    }
//
//    @Override
//    public void updateMap(@Param("map") Map entityMap) {
//
//    }
//
//    @Override
//    public List<University> findAll() {
//        return universityDAO.findAll();
//    }
//
//    @Override
//    public List like(String property, Object value) {
//        return null;
//    }
//
//    @Override
//    public Long selectMaxId() {
//        return null;
//    }
//
//    @Override
//    public BaseDomain updateOrSave(BaseDomain baseDomain, Long id) {
//        return null;
//    }
//
//    @Override
//    public BaseDomain selectOne(String mapperId, Object obj) {
//        return null;
//    }
//
//    @Override
//    public List selectList(String mapperId, Object obj) {
//        return null;
//    }
//
//    @Override
//    public Class getEntityClass() {
//        return null;
//    }
//
//    @Override
//    public int count(Map condition) {
//        return 0;
//    }
//
//    @Override
//    public BaseDomain queryOne(Map condition) {
//        return null;
//    }
//
//    @Override
//    public List queryList(@Param("condition") Map condition, @Param("orderBy") String orderBy, @Param("sortBy") String sortBy) {
//        return null;
//    }
//
//    @Override
//    public List queryPage(@Param("condition") Map condition, @Param("offset") int offset, @Param("rows") int rows) {
//        return null;
//    }
//
//    @Override
//    protected UniversityDAO getDao() {
//        return universityDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
