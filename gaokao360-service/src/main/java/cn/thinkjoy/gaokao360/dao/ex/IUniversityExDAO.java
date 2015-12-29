/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityDAO.java 2015-12-16 18:39:46 $
 */
package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.University;
import cn.thinkjoy.gaokao360.domain.UniversityDict;
import cn.thinkjoy.gaokao360.dto.UniversityDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface IUniversityExDAO extends IBaseDAO<UniversityDTO>{
    Integer getMaxId();
    UniversityDict getDictByName(String name);
    Integer getDictMaxSort(String type);
    int insertDetail(@Param("map") Map<String, Object> entityMap);
}
