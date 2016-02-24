/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shishuo
 * $Id:  UserAccountService.java 2015-07-13 09:45:17 $
 */

package cn.thinkjoy.gaokao360.service.common.ex;


import cn.thinkjoy.gaokao360.domain.UserInfo;

public interface IUserInfoExService {

    UserInfo findUserInfoById(long id);

    boolean updateUserInfoById(UserInfo userInfo);

    void updateUserCanTarget();
}
