/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgksystem
 * $Id:  ExpertServiceRelation.java 2016-12-27 17:06:56 $
 */



package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class ExpertServiceRelation extends BaseDomain{
    private Integer expertId;
    private Integer serviceTypeId;
    private Long createDate;

	public ExpertServiceRelation(){
	}
    public void setExpertId(Integer value) {
        this.expertId = value;
    }

    public Integer getExpertId() {
        return this.expertId;
    }
    public void setServiceTypeId(Integer value) {
        this.serviceTypeId = value;
    }

    public Integer getServiceTypeId() {
        return this.serviceTypeId;
    }

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ExpertId",getExpertId())
			.append("ServiceTypeId",getServiceTypeId())
			.append("CreateDate",getCreateDate())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof ExpertServiceRelation == false) return false;
		if(this == obj) return true;
		ExpertServiceRelation other = (ExpertServiceRelation)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

