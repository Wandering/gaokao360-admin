/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoSectionServiceImpl.java 2015-12-04 13:22:05 $
 */
package cn.thinkjoy.gaokao360.service.differentiation.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.dao.ex.IVideoSectioneExDAO;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.dto.VideoSectionDTO;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IVideoSectionExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("VideoSectionExServiceImpl")
@Scope("prototype")
public class VideoSectionExServiceImpl extends AbstractPageService<IBaseDAO<VideoSection>, VideoSection> implements IVideoSectionExService<IBaseDAO<VideoSection>,VideoSection> {
    @Autowired
    private IVideoSectioneExDAO videoSectioneExDAO;

    @Override
    public IBaseDAO<VideoSection> getDao() {
        return videoSectioneExDAO;
    }

    @Override
    public VideoSection queryByCourseId(Object courseId) {
        return videoSectioneExDAO.queryByCourseId(courseId);
    }

    @Override
    public void updateCourseId(Object courseId,Object id) {
        videoSectioneExDAO.updateCourseId(courseId,id);
    }

    @Override
    public List<VideoSectionDTO> getVideoSectionByCourseId(Object courseId) {
        return videoSectioneExDAO.getVideoByCourseId(courseId);
    }

    @Override
    public Long queryByMaxId() {
        return videoSectioneExDAO.queryByMaxId();
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
//    public List<VideoSection> findAll() {
//        return videoSectionDAO.findAll();
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
//    protected VideoSectionDAO getDao() {
//        return videoSectionDAO;
//    }
//
//    @Override
//    public BizData4Page queryPageByDataPerm(String resUri, Map conditions, int curPage, int offset, int rows) {
//        return super.doQueryPageByDataPerm(resUri, conditions, curPage, offset, rows);
//    }


}
