/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  GkinformationGkhotFacadeImpl.java 2015-12-03 14:24:46 $
 */
package cn.thinkjoy.gaokao360.facade.impl;

import cn.thinkjoy.gaokao360.facade.IGkinformationGkhotFacade;
import cn.thinkjoy.gaokao360.service.differentiation.IGkinformationGkhotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GkinformationGkhotFacadeImpl")
public class GkinformationGkhotFacadeImpl implements IGkinformationGkhotFacade {
    @Autowired
    private IGkinformationGkhotService gkinformationGkhotService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        gkinformationGkhotService.add();
//    }
}
