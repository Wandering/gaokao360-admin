/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shishuo
 * $Id:  UserAccountServiceImpl.java 2015-07-13 09:45:17 $
 */
package cn.thinkjoy.gaokao360.service.common.ex.impl;


import cn.thinkjoy.gaokao360.dao.ex.IGKUpdateUserScoreDAO;
import cn.thinkjoy.gaokao360.service.common.ex.IGKUpdateUserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("GKUpdateUserScoreServiceImpl")
public class GKUpdateUserScoreServiceImpl implements IGKUpdateUserScoreService {


    @Autowired
    IGKUpdateUserScoreDAO gkUpdateUserScoreDAO;

    @Override
    public Map<String, Object> getUserScore(String account) {
        return gkUpdateUserScoreDAO.getUserScore(account);
    }

    @Override
    public boolean resetUserScore(Object id) {
        return gkUpdateUserScoreDAO.resetUserScore(id);
    }
}
