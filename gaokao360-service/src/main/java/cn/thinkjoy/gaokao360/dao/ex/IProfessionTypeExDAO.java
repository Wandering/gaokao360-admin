/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionTypeDAO.java 2015-12-28 18:05:28 $
 */
package cn.thinkjoy.gaokao360.dao.ex;


import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.ProfessionType;
import cn.thinkjoy.gaokao360.dto.ProfessionTypeDTO;

import java.util.List;

public interface IProfessionTypeExDAO extends IBaseDAO<ProfessionTypeDTO> {

    public List queryListByParentId(Object id);
    public Integer getOccupy(Object id);
}
