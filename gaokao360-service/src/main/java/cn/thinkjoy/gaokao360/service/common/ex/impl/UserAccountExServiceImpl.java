/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shishuo
 * $Id:  UserAccountServiceImpl.java 2015-07-13 09:45:17 $
 */
package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.gaokao360.dao.IUserAccountDAO;
import cn.thinkjoy.gaokao360.dao.IUserAccountExDAO;
import cn.thinkjoy.gaokao360.dao.IUserInfoDAO;
import cn.thinkjoy.gaokao360.dao.IUserInfoExDAO;
import cn.thinkjoy.gaokao360.domain.UserAccount;
import cn.thinkjoy.gaokao360.service.common.ex.IUserAccountExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("UserAccountExServiceImpl")
public class UserAccountExServiceImpl implements IUserAccountExService {

    @Autowired
    private IUserAccountExDAO userAccountExDAO;

    @Autowired
    private IUserAccountDAO userAccountDAO;

    @Autowired
    private IUserInfoDAO userInfoDAO;

    @Autowired
    private IUserInfoExDAO userInfoExDAO;

    @Override
    public int findUserAccountCountByPhone(String account,long areaId) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("account",account);
        params.put("areaId",areaId);
        return userAccountExDAO.findUserAccountCount(params);
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount){
        boolean flag = false;
        userAccountDAO.update(userAccount);
        flag = true;
        return flag;
    }

    @Override
    public UserAccount findUserAccountById(long id){
        return userAccountDAO.fetch(id);
    }


}
