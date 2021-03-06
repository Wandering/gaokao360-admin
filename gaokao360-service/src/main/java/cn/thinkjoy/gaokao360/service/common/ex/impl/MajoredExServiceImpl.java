/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredServiceImpl.java 2015-12-16 16:48:39 $
 */
package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.dao.IMajoredDAO;
import cn.thinkjoy.gaokao360.dao.ex.IMajoredExDAO;
import cn.thinkjoy.gaokao360.domain.GkBaseDomain;
import cn.thinkjoy.gaokao360.dto.MajorDTO;
import cn.thinkjoy.gaokao360.service.common.ex.IMajoredExService;
import cn.thinkjoy.zgk.dto.MajoredQueryDTO;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("MajoredExServiceImpl")
@Scope("prototype")
public class MajoredExServiceImpl extends AbstractPageService<IBaseDAO<MajorDTO>, MajorDTO> implements IMajoredExService<IBaseDAO<MajorDTO>,MajorDTO> {
    @Autowired
    private IMajoredDAO majoredDAO;

    @Autowired
    private IMajoredExDAO majoredExDAO;

    @Override
    public IBaseDAO<MajorDTO> getDao() {
        return majoredExDAO;
    }

    @Override
    public List<GkBaseDomain> getMajoredListByName(Object name) {
        return majoredExDAO.getMajoredListByName(name);
    }

    @Override
    public int insertMapDetail(Map<String, Object> entityMap) {
        return majoredExDAO.insertMapDetail(entityMap);
    }

    @Override
    public List<MajoredQueryDTO> getMajoredByName(String majoredName,String type){
        Map<String,Object> map= Maps.newHashMap();
        map.put("majoredName",majoredName);
        map.put("type",type);
        return majoredExDAO.getMajoredByName(map);
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
//    public List<Majored> findAll() {
//        return majoredDAO.findAll();
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
//    protected MajoredDAO getDao() {
//        return majoredDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
