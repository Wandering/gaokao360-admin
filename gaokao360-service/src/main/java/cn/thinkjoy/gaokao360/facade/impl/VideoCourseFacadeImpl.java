/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoCourseFacadeImpl.java 2015-12-04 13:22:05 $
 */
package cn.thinkjoy.gaokao360.facade.impl;

import cn.thinkjoy.gaokao360.facade.IVideoCourseFacade;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VideoCourseFacadeImpl")
public class VideoCourseFacadeImpl implements IVideoCourseFacade {
    @Autowired
    private IVideoCourseService videoCourseService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        videoCourseService.add();
//    }
}
