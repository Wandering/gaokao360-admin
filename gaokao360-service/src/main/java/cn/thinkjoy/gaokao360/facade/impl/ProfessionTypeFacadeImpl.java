/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionTypeFacadeImpl.java 2015-12-28 18:05:28 $
 */
package cn.thinkjoy.gaokao360.facade.impl;

import cn.thinkjoy.gaokao360.facade.IProfessionTypeFacade;
import cn.thinkjoy.gaokao360.service.IProfessionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProfessionTypeFacadeImpl")
public class ProfessionTypeFacadeImpl implements IProfessionTypeFacade {
    @Autowired
    private IProfessionTypeService professionTypeService;


//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public void add() {
//        professionTypeService.add();
//    }
}
