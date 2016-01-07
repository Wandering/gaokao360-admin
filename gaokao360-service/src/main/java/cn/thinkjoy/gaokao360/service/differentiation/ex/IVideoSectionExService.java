/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoSectionService.java 2015-12-04 13:22:05 $
 */

package cn.thinkjoy.gaokao360.service.differentiation.ex;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.dto.VideoSectionDTO;

import java.util.List;

public interface IVideoSectionExService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T>{
    public VideoSection queryByCourseId(Object courseId);
    public Long queryByMaxId();
    public void updateCourseId(Object courseId, Object id);
    public List<VideoSectionDTO> getVideoSectionByCourseId(Object courseId);
}
