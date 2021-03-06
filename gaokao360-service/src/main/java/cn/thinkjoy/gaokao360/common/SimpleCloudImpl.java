/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  AgentServiceImpl.java 2015-12-15 17:52:12 $
 */
package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.cloudstack.context.CloudContextFactory;
import cn.thinkjoy.zgk.cloudstack.BaseWhiteList;
import cn.thinkjoy.zgk.cloudstack.ISimpleCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("SimpleCloudImpl")
public class SimpleCloudImpl implements ISimpleCloud{

    @Autowired
    BaseWhiteList baseWhiteList;
    @Override
    public String getCloudProduct() {
        return CloudContextFactory.getCloudContext().getProduct();
    }

    @Override
    public String getCloudArea() {
        return null;
    }

    @Override
    public boolean hasWhiteList(String clzName) {
        return baseWhiteList.hasWhiteList(clzName);
    }

    @Override
    public void setArea(String area) {
        UserAreaContext.setCurrentUserArea(area);
    }

    @Override
    public void clearArea() {
        CustomerContextHolder.clearContextType();
        UserAreaContext.removeCurrentUseraArea();
    }
}
