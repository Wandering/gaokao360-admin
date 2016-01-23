/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorService.java 2015-12-30 15:19:55 $
 */

package cn.thinkjoy.gaokao360.service.common.ex;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.gaokao360.dto.UniversityDTO;

import java.util.List;
import java.util.Map;

public interface IUniversityMajorExService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T>{
    List<UniversityDTO> getMajorOpenUniversityList(Map<String,Object> map,int offset,int rows,String orderBy,String sortBy);
}
