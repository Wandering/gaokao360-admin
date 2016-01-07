/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionFacadeImpl.java 2015-12-28 18:05:26 $
 */
package cn.thinkjoy.gaokao360.facade.impl;

import cn.thinkjoy.gaokao360.facade.IProfessionFacade;
import cn.thinkjoy.gaokao360.service.common.IProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProfessionFacadeImpl")
public class ProfessionFacadeImpl implements IProfessionFacade {
    @Autowired
    private IProfessionService professionService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        professionService.add();
//    }
}
