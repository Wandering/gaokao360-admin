/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorServiceImpl.java 2015-12-30 20:35:48 $
 */
package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.dao.ex.IUniversityMajorExDAO;
import cn.thinkjoy.gaokao360.dto.UniversityDTO;
import cn.thinkjoy.gaokao360.dto.UniversityMajorDTO;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityMajorExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("UniversityMajorExServiceImpl")
@Scope("prototype")
public class UniversityMajorExServiceImpl extends AbstractPageService<IBaseDAO<UniversityMajorDTO>, UniversityMajorDTO> implements IUniversityMajorExService<IBaseDAO<UniversityMajorDTO>,UniversityMajorDTO> {
    @Autowired
    private IUniversityMajorExDAO universityMajorExDAO;

    @Override
    public IBaseDAO<UniversityMajorDTO> getDao() {
        return universityMajorExDAO;
    }

    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows){
        return createBizData4Page(getDao(),conditions,curPage,offset,rows);
    }

    private BizData4Page createBizData4Page(IBaseDAO dao, Map<String, Object> conditions, int curPage, int offset, int rows){
        List<UniversityMajorDTO> mainData = dao.queryPage(conditions, offset, rows, null, null,null);

        int records = dao.count(conditions);
        BizData4Page bizData4Page = new BizData4Page();
        bizData4Page.setRows(mainData);
        bizData4Page.setPage(curPage);
        bizData4Page.setRecords(records);
        int total = records / rows;
        int mod = records % rows;
        if(mod > 0){
            total = total + 1;
        }
        bizData4Page.setTotal(total);

        return bizData4Page;
    }

    @Override
    public List<UniversityDTO> getMajorOpenUniversityList(Map<String, Object> map,int offset,int rows,String orderBy,String sortBy) {
        return universityMajorExDAO.getMajorOpenUniversityList(map,offset,rows,orderBy,sortBy);
    }

    @Override
    public boolean insertUniversityMajor(Map<String, Object> map) {
        return universityMajorExDAO.insertUniversityMajor(map);
    }

    @Override
    public boolean updateUniversityMajor(Map<String, Object> map) {
        return universityMajorExDAO.updateUniversityMajor(map);
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
//    public List<UniversityMajor> findAll() {
//        return universityMajorDAO.findAll();
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
//    protected UniversityMajorDAO getDao() {
//        return universityMajorDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
