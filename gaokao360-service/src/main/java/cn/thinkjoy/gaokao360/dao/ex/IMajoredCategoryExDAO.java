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

import java.util.List;

public interface IMajoredCategoryExDAO extends IBaseDAO<MajoredCategoryDTO>{
    List queryListByParentId(Object id);
}
