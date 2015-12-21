/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityServiceImpl.java 2015-12-16 18:39:46 $
 */
package cn.thinkjoy.gaokao360.service.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.service.impl.AbstractPageService;
import cn.thinkjoy.gaokao360.dao.IUniversityDAO;
import cn.thinkjoy.gaokao360.dao.IUniversityDictDAO;
import cn.thinkjoy.gaokao360.dao.IUniversitySortDAO;
import cn.thinkjoy.gaokao360.dao.ex.IUniversityExDAO;
import cn.thinkjoy.gaokao360.domain.University;
import cn.thinkjoy.gaokao360.domain.UniversityDict;
import cn.thinkjoy.gaokao360.domain.UniversitySort;
import cn.thinkjoy.gaokao360.dto.UniversityDTO;
import cn.thinkjoy.gaokao360.service.ex.IUniversityExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("UniversityExServiceImpl")
public class UniversityExServiceImpl extends AbstractPageService<IBaseDAO<UniversityDTO>, UniversityDTO> implements IUniversityExService<IBaseDAO<UniversityDTO>,UniversityDTO>{
    @Autowired
    private IUniversityExDAO universityExDAO;
    @Autowired
    private IUniversityDAO universityDAO;

    @Autowired
    private IUniversitySortDAO  universitySortDAO;

    @Autowired
    private IUniversityDictDAO universityDictDAO;

    @Override
    public IBaseDAO<UniversityDTO> getDao() {
        return universityExDAO;
    }

    public void insertUniversity(Map<String,Object> dataMap){
        String property=(String) dataMap.get("property");
        UniversityDict universityDict=universityExDAO.getDictByName(property);
        if(universityDict!=null){
            dataMap.put("property",universityDict.getDictId());
        }else {
            universityDict=new UniversityDict();
            universityDict.setName(property);
            universityDict.setType("FEATURE");
            Integer sort=universityExDAO.getDictMaxSort("FEATURE");
            universityDict.setDictId(sort++);
            universityDictDAO.insert(universityDict);
            dataMap.put("property",sort);
        }
        universityDAO.insertMap(dataMap);
        dataMap.put("universityId", universityExDAO.getMaxId());
        universitySortDAO.insertMap(dataMap);
    }

    public void updateUniversity(Map<String,Object> dataMap){
        universityDAO.insertMap(dataMap);
        dataMap.put("universityId",dataMap.get("id"));
        Map<String,Object> map = new HashMap<>();
        map.put("universityId",dataMap.get("id"));
        UniversitySort sort = (UniversitySort)universitySortDAO.queryOne(map,null,null);
        dataMap.put("id",sort.getId());
        universitySortDAO.updateMap(dataMap);
    }

    public void deleteUniversity(Map<String,Object> dataMap){
        universityDAO.deleteById(dataMap.get("id"));
        Map<String,Object> map = new HashMap<>();
        map.put("universityId",dataMap.get("id"));
        universitySortDAO.deleteByCondition(map);
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
