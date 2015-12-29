/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityService.java 2015-12-16 18:39:46 $
 */

package cn.thinkjoy.gaokao360.service.ex;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.gaokao360.domain.GkBaseDomain;

import java.util.List;
import java.util.Map;

public interface IUniversityExService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T>{
    void insertUniversity(Map<String,Object> dataMap);
    void updateUniversity(Map<String,Object> dataMap);
    void deleteUniversity(Map<String,Object> dataMap);
    List<GkBaseDomain> getUniversityByName(String name);
}
