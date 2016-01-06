/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionDAO.java 2015-12-28 18:05:26 $
 */
package cn.thinkjoy.gaokao360.dao;


import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.Profession;

import java.util.List;
import java.util.Map;

public interface IProfessionDAO extends IBaseDAO<Profession> {

    List<Map<String, String>> queryProfessionCategory(Map<String, Object> dataMap);
    Map<String, String> queryProfessionDetail(int id);
    List<Map<String, String>> queryPageList(Map<String, Object> dataMap);
}
