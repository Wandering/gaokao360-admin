/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertCases.java 2016-12-26 11:24:38 $
 */





package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.gaokao360.domain.ExpertCases;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ExpertCasesDTO extends ExpertCases {
    private String expertName;
    private String serviceName;

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

