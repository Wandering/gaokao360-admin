/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityEnrollingService.java 2016-02-01 17:02:35 $
 */

package cn.thinkjoy.daotest;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

import cn.thinkjoy.gaokao360.common.UserAreaContext;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityEnrollingExService;
import cn.thinkjoy.zgk.common.QueryUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springme.xml")
public class UniversityEnrollingServiceTest{
    @Autowired
    private IUniversityEnrollingExService universityEnrollingExService;

    @Test
    public void test(){
        //showcase
        UserAreaContext.setCurrentUserArea("sn");
        Map<String,Object>  map = new HashMap<>();
        QueryUtil.setMapOp(map,"enrollingyear","=","2012");
        QueryUtil.setMapOp(map,"enrollingbatch","=","2");
        QueryUtil.setMapOp(map,"entype","=","2");
        QueryUtil.setMapOp(map,"enrollingareaid","=","330000");
//        QueryUtil.setMapOp(map,"universityareaid","=","2012");
//        QueryUtil.setMapOp(map,"universityproperty","=","2012");

//        map.put("offset","1");
//        map.put("rows","10");
        map.put("orderBy","id");
        map.put("sortBy","desc");
        assertEquals(1, universityEnrollingExService.queryPage(map,1,10));
    }
}
