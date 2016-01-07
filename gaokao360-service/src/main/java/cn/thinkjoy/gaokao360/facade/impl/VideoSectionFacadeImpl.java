/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoSectionFacadeImpl.java 2015-12-04 13:22:05 $
 */
package cn.thinkjoy.gaokao360.facade.impl;

import cn.thinkjoy.gaokao360.facade.IVideoSectionFacade;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VideoSectionFacadeImpl")
public class VideoSectionFacadeImpl implements IVideoSectionFacade {
    @Autowired
    private IVideoSectionService videoSectionService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        videoSectionService.add();
//    }
}
