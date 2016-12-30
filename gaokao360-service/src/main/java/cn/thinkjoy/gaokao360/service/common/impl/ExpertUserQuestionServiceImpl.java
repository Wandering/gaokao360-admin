/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertUserQuestionServiceImpl.java 2016-12-30 11:34:05 $
 */
package cn.thinkjoy.gaokao360.service.common.impl;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.dao.IExpertUserQuestionDAO;
import cn.thinkjoy.gaokao360.dao.ex.IExpertUserQuestionExDAO;
import cn.thinkjoy.gaokao360.domain.ExpertUserQuestion;
import cn.thinkjoy.common.service.impl.AbstractPageService;
import cn.thinkjoy.gaokao360.service.common.IExpertUserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("ExpertUserQuestionServiceImpl")
public class ExpertUserQuestionServiceImpl extends AbstractPageService<IBaseDAO<ExpertUserQuestion>, ExpertUserQuestion> implements IExpertUserQuestionService<IBaseDAO<ExpertUserQuestion>,ExpertUserQuestion> {
    @Autowired
    private IExpertUserQuestionExDAO expertUserQuestionDAO;

    @Override
    public IBaseDAO<ExpertUserQuestion> getDao() {
        return expertUserQuestionDAO;
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
//    public List<ExpertUserQuestion> findAll() {
//        return expertUserQuestionDAO.findAll();
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
//    protected ExpertUserQuestionDAO getDao() {
//        return expertUserQuestionDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
