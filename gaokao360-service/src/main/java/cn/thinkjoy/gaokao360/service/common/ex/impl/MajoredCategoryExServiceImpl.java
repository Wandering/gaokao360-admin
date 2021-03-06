/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredCategoryServiceImpl.java 2015-12-16 16:48:40 $
 */
package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.dao.IMajoredCategoryDAO;
import cn.thinkjoy.gaokao360.dao.ex.IMajoredCategoryExDAO;
import cn.thinkjoy.gaokao360.domain.MajoredCategory;
import cn.thinkjoy.gaokao360.dto.MajoredCategoryDTO;
import cn.thinkjoy.gaokao360.service.common.ex.IMajoredCategoryExService;
import cn.thinkjoy.zgk.dto.CategoryMajoredDTO;
import cn.thinkjoy.zgk.dto.MajoredCategoryRemoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("MajoredCategoryExServiceImpl")
@Scope("prototype")
public class MajoredCategoryExServiceImpl extends AbstractPageService<IBaseDAO<MajoredCategoryDTO>, MajoredCategoryDTO> implements IMajoredCategoryExService<IBaseDAO<MajoredCategoryDTO>,MajoredCategoryDTO> {
    @Autowired
    private IMajoredCategoryExDAO majoredCategoryExDAO;

    @Autowired
    private IMajoredCategoryDAO majoredCategoryDAO;

    @Override
    public IBaseDAO<MajoredCategoryDTO> getDao() {
        return majoredCategoryExDAO;
    }

    @Override
    public List<MajoredCategoryRemoteDTO> getMajoredCategory(Map<String,Object> map){
        return majoredCategoryExDAO.getMajoredCategory(map);
    }

    @Override
    public List<CategoryMajoredDTO> getCategoryMajoredList(Map<String,Object> map){
        return majoredCategoryExDAO.getCategoryMajoredList(map);
    }


    public BizData4Page queryPageByDataPerm(String resUri, Map<String, Object> conditions, int curPage, int offset, int rows){
        return createBizData4Page(getDao(),conditions,curPage,offset,rows);
    }

    public MajoredCategoryDTO fetch1(String id){
        MajoredCategoryDTO majoredCategoryDTO= majoredCategoryExDAO.fetch(id);
        List list=majoredCategoryExDAO.queryListByParentId(majoredCategoryDTO.getId());
        majoredCategoryDTO.setMajoredCategoryDTOs(list);
        return majoredCategoryDTO;
    }

    public BizData4Page createBizData4Page(IBaseDAO dao, Map<String, Object> conditions, int curPage, int offset, int rows){
        List<MajoredCategoryDTO> mainData = dao.queryPage(conditions, offset, rows, null, null,null);
        for(MajoredCategoryDTO majoredCategoryDTO:mainData){
            List list=majoredCategoryExDAO.queryListByParentId(majoredCategoryDTO.getId());
            majoredCategoryDTO.setMajoredCategoryDTOs(list);
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
        return majoredCategoryExDAO.queryListByParentId(id);
    }

    public void insertCategory(Map<String,Object> dataMap){
        dataMap.put("parentId","1");
        dataMap.put("level","1");
        majoredCategoryDAO.insertMap(dataMap);
        Long l=(Long)majoredCategoryDAO.selectMaxId();
        String majoredStr=(String)dataMap.get("majoredList");
        String[] majoredList=majoredStr.split("、");
        for(String str:majoredList){
            MajoredCategory majoredCategory = new MajoredCategory();
            majoredCategory.setName(str);
            majoredCategory.setLevel(2);
            majoredCategory.setParentId(l);
            majoredCategoryDAO.insert(majoredCategory);
        }

    }

    public void updateCategory(Map<String,Object> dataMap){
        dataMap.put("parentId","1");
        majoredCategoryDAO.updateMap(dataMap);
        Long l=Long.parseLong((String)dataMap.get("id"));
        Map<String,Object> map = new HashMap<>();
        String majoredStr=(String)dataMap.get("majoredList");
        String[] majoredList=majoredStr.split("、");
        for(String str:majoredList){
            MajoredCategory majoredCategory = new MajoredCategory();
            majoredCategory.setName(str);
            majoredCategory.setLevel(2);
            majoredCategory.setParentId(l);
            map=new HashMap<>();
            map.put("name",str);
            map.put("parentId",l);
            if(majoredCategoryDAO.queryOne(map,"id","asc")==null) {
                majoredCategoryDAO.insert(majoredCategory);
            }
        }

    }

    public void deleteCategory(Map<String,Object> dataMap){
        Long l=Long.parseLong((String)dataMap.get("id"));
        majoredCategoryDAO.deleteById(l);
        Map<String,Object> map = new HashMap<>();
        map.put("parentId",l);
        majoredCategoryDAO.deleteByCondition(map);
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
//    public List<MajoredCategory> findAll() {
//        return majoredCategoryDAO.findAll();
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
//    protected MajoredCategoryDAO getDao() {
//        return majoredCategoryDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
