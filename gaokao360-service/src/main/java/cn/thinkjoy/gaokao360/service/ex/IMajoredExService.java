/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredService.java 2015-12-16 16:48:39 $
 */

package cn.thinkjoy.gaokao360.service.ex;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.gaokao360.domain.GkBaseDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMajoredExService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T>{
    List<GkBaseDomain> getMajoredListByName(Object name);

}
