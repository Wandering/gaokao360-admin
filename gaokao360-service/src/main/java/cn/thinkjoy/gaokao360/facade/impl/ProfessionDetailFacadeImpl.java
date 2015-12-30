/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionDetailFacadeImpl.java 2015-12-28 18:05:28 $
 */
package cn.thinkjoy.gaokao360.facade.impl;


import cn.thinkjoy.gaokao360.facade.IProfessionDetailFacade;
import cn.thinkjoy.gaokao360.service.IProfessionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProfessionDetailFacadeImpl")
public class ProfessionDetailFacadeImpl implements IProfessionDetailFacade {
    @Autowired
    private IProfessionDetailService professionDetailService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        professionDetailService.add();
//    }
}
