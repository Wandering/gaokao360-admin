/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  AgentServiceImpl.java 2015-12-15 17:52:12 $
 */
package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.cloudstack.context.CloudContextFactory;
import cn.thinkjoy.zgk.cloudstack.ISimpleCloud;
import cn.thinkjoy.zgk.cloudstack.InterfaceConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("SimpleCloudImpl")
public class SimpleCloudImpl implements ISimpleCloud{

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
        return InterfaceConst.hasWhiteList(clzName);
    }

    @Override
    public void setArea(String area) {
//        UserAreaContext.setCurrentUserArea(area);
    }

}
