/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoSectionDAO.java 2015-12-04 13:22:05 $
 */
package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.VideoSection;

public interface IVideoSectioneExDAO extends IBaseDAO<VideoSection>{
    public VideoSection  queryByCourseId(Object courseId);
    public Long  queryByMaxId();
    public Long updateCourseId(Object courseId, Object id);
}
