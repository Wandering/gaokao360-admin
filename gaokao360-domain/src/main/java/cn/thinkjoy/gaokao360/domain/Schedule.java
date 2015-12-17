/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  Schedule.java 2015-12-15 19:05:44 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Schedule extends CreateBaseDomain<Long>{
    /** 省份 */
    private Long areaId;
    /** 月份 */
    private String month;
    /** 年份 */
    private String years;

	public Schedule(){
	}
    public void setAreaId(Long value) {
        this.areaId = value;
    }

    public Long getAreaId() {
        return this.areaId;
    }
    public void setMonth(String value) {
        this.month = value;
    }

    public String getMonth() {
        return this.month;
    }
    public void setYears(String value) {
        this.years = value;
    }

    public String getYears() {
        return this.years;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AreaId",getAreaId())
			.append("Month",getMonth())
			.append("Years",getYears())
			.append("Status",getStatus())
			.append("CreateDate",getCreateDate())
			.append("LastModDate",getLastModDate())
			.append("Creator",getCreator())
			.append("LastModifier",getLastModifier())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Schedule == false) return false;
		if(this == obj) return true;
		Schedule other = (Schedule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

