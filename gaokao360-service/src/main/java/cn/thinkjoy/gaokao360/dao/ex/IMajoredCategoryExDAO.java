/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredCategoryDAO.java 2015-12-16 16:48:40 $
 */
package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.MajoredCategory;
import cn.thinkjoy.gaokao360.dto.MajoredCategoryDTO;
import cn.thinkjoy.zgk.dto.CategoryMajoredDTO;
import cn.thinkjoy.zgk.dto.MajoredCategoryRemoteDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMajoredCategoryExDAO extends IBaseDAO<MajoredCategoryDTO>{
    List queryListByParentId(Object id);

    List<MajoredCategoryRemoteDTO> getMajoredCategory(@Param("condition")Map<String,Object> map);

    List<CategoryMajoredDTO> getCategoryMajoredList(@Param("condition")Map<String,Object> map);
}
