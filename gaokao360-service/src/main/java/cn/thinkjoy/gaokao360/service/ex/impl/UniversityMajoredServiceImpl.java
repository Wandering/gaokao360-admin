/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  AdmissionBatchServiceImpl.java 2015-12-09 16:15:38 $
 */
package cn.thinkjoy.gaokao360.service.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.service.impl.AbstractPageService;
import cn.thinkjoy.gaokao360.dao.IAdmissionBatchDAO;
import cn.thinkjoy.gaokao360.dao.ex.IUniversityMajoredDAO;
import cn.thinkjoy.gaokao360.domain.AdmissionBatch;
import cn.thinkjoy.gaokao360.dto.UniversityMajoredDTO;
import cn.thinkjoy.gaokao360.service.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.ex.IUniversityMajoredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("UniversityMajoredServiceImpl")
public class UniversityMajoredServiceImpl extends AbstractPageService<IBaseDAO<UniversityMajoredDTO>, UniversityMajoredDTO> implements IUniversityMajoredService<IBaseDAO<UniversityMajoredDTO>,UniversityMajoredDTO> {
    @Autowired
    private IUniversityMajoredDAO universityMajoredDAO;

    @Override
    public IBaseDAO<UniversityMajoredDTO> getDao() {
        return universityMajoredDAO;
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
//    public List<AdmissionBatch> findAll() {
//        return admissionBatchDAO.findAll();
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
//    protected AdmissionBatchDAO getDao() {
//        return admissionBatchDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
