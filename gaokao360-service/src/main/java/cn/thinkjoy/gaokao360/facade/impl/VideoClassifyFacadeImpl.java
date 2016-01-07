/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoClassifyFacadeImpl.java 2015-12-04 13:22:04 $
 */
package cn.thinkjoy.gaokao360.facade.impl;

import cn.thinkjoy.gaokao360.facade.IVideoClassifyFacade;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VideoClassifyFacadeImpl")
public class VideoClassifyFacadeImpl implements IVideoClassifyFacade {
    @Autowired
    private IVideoClassifyService videoClassifyService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        videoClassifyService.add();
//    }
}
