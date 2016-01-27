/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredCategoryService.java 2015-12-16 16:48:40 $
 */

package cn.thinkjoy.gaokao360.service.common.ex;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.zgk.dto.CategoryMajoredDTO;
import cn.thinkjoy.zgk.dto.MajoredCategoryRemoteDTO;

import java.util.List;
import java.util.Map;

public interface IMajoredCategoryExService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T>{

    public List queryListByParentId(Object id);

    public List<MajoredCategoryRemoteDTO> getMajoredCategory(Map<String,Object> map);

    List<CategoryMajoredDTO> getCategoryMajoredList(Map<String,Object> map);

    void insertCategory(Map<String,Object> dataMap);

    void updateCategory(Map<String,Object> dataMap);

    void deleteCategory(Map<String,Object> dataMap);

    T fetch1(String id);
}
