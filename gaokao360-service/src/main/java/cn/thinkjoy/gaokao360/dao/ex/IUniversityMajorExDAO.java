/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorDAO.java 2015-12-30 15:19:55 $
 */
package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.UniversityMajor;
import cn.thinkjoy.gaokao360.dto.UniversityMajorDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUniversityMajorExDAO extends IBaseDAO<UniversityMajorDTO>{
    public List<UniversityMajorDTO> queryPage(@Param("condition") Map<String, Object> condition, @Param("offset") int offset, @Param("rows") int rows,
                             @Param("orderBy") String orderBy, @Param("sortBy") String sortBy);

}
