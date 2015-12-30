/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredDAO.java 2015-12-16 16:48:39 $
 */
package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.GkBaseDomain;
import cn.thinkjoy.gaokao360.domain.Majored;
import cn.thinkjoy.gaokao360.dto.MajorDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMajoredExDAO extends IBaseDAO<MajorDTO>{
    List<GkBaseDomain> getMajoredListByName(@Param("name")Object name);
    int insertMapDetail(@Param("map") Map<String, Object> entityMap);

}
