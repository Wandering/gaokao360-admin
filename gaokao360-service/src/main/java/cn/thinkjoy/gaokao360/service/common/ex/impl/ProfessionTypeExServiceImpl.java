/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionTypeServiceImpl.java 2015-12-28 18:05:28 $
 */
package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.service.impl.AbstractPageService;
import cn.thinkjoy.gaokao360.dao.ex.IProfessionTypeExDAO;
import cn.thinkjoy.gaokao360.dto.ProfessionTypeDTO;
import cn.thinkjoy.gaokao360.service.common.ex.IProfessionTypeExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("ProfessionTypeExServiceImpl")
public class ProfessionTypeExServiceImpl extends AbstractPageService<IBaseDAO<ProfessionTypeDTO>, ProfessionTypeDTO> implements IProfessionTypeExService<IBaseDAO<ProfessionTypeDTO>,ProfessionTypeDTO> {
    @Autowired
    private IProfessionTypeExDAO professionTypeExDAO;

    @Override
    public IBaseDAO<ProfessionTypeDTO> getDao() {
        return professionTypeExDAO;
    }

    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows){
        return createBizData4Page(getDao(),conditions,curPage,offset,rows);
    }
    public BizData4Page createBizData4Page(IBaseDAO dao, Map<String, Object> conditions, int curPage, int offset, int rows){
        List<ProfessionTypeDTO> mainData = dao.queryPage(conditions, offset, rows, null, null,null);
        for(ProfessionTypeDTO professionTypeDTO:mainData){
            List list=professionTypeExDAO.queryListByParentId(professionTypeDTO.getId());
            professionTypeDTO.setChilds(list);
        }
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
    public List queryListByParentId(Object id) {
        return professionTypeExDAO.queryListByParentId(id);
    }
    @Override
    public Integer getOccupy(Object id) {
        return professionTypeExDAO.getOccupy(id);
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
//    public List<ProfessionType> findAll() {
//        return professionTypeDAO.findAll();
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
//    protected ProfessionTypeDAO getDao() {
//        return professionTypeDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
