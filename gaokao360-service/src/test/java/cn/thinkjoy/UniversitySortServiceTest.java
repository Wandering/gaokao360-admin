/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversitySortService.java 2015-12-18 11:11:01 $
 */

package cn.thinkjoy;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;

import cn.thinkjoy.gaokao360.dao.ex.IUniversityExDAO;
import cn.thinkjoy.gaokao360.dao.ex.IUniversityMajoredDAO;
import cn.thinkjoy.gaokao360.service.IUniversitySortService;

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
public class UniversitySortServiceTest{
    @Autowired
    private IUniversityMajoredDAO universityMajoredDAO;

    @Test
    public void test(){
        //showcase
        Map<String,Object> map = new HashMap<>();
        universityMajoredDAO.queryPage(map,1,10,"id","asc");
        System.out.print("--------------");
        universityMajoredDAO.count(map);
    }
}
