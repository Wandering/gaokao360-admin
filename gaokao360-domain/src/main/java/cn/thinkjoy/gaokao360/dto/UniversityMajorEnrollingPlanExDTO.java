/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorEnrollingPlan.java 2016-04-29 15:38:17 $
 */





package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.gaokao360.domain.UniversityMajorEnrollingPlan;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class UniversityMajorEnrollingPlanExDTO extends UniversityMajorEnrollingPlan {
    /** 区域名称 */
    private String areaName;
    /** 批次名称 */
    private String batchName;
    /** 专业类别名称 */
    private String majorTypeName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getMajorTypeName() {
        return majorTypeName;
    }

    public void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }
}

