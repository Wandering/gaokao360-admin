///*
// * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
// *
// * Project Name: shishuo
// * $Id:  UserAccountServiceImpl.java 2015-07-13 09:45:17 $
// */
//package cn.thinkjoy.gaokao360.service.common.ex.impl;
//
//import cn.thinkjoy.gaokao360.dao.IUserInfoDAO;
//import cn.thinkjoy.gaokao360.dao.IUserInfoExDAO;
//import cn.thinkjoy.gaokao360.domain.UserInfo;
//import cn.thinkjoy.gaokao360.service.common.ex.IUserInfoExService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service("UserInfoExServiceImpl")
//public class UserInfoExServiceImpl implements IUserInfoExService {
//
//    @Autowired
//    private IUserInfoDAO userInfoDAO;
//    @Autowired
//    private IUserInfoExDAO userInfoExDAO;
//    @Override
//    public UserInfo findUserInfoById(long id) {
//        return userInfoDAO.fetch(id);
//    }
//
//    @Override
//    public boolean updateUserInfoById(UserInfo userInfo) {
//        boolean flag = false;
//        userInfoDAO.update(userInfo);
//        flag = true;
//        return flag;
//    }
//
//    @Override
//    public void updateUserCanTarget() {
//        userInfoExDAO.updateUserCanTarget();
//    }
//}
