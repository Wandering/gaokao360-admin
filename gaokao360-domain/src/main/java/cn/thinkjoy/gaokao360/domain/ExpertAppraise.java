/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertAppraise.java 2016-10-19 15:14:27 $
 */



package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class ExpertAppraise extends BaseDomain{
    private Integer expertId;
    private Integer userId;
    private String userName;
    private String serverType;
    private String userComments;
    private String isChecked;
    private Long createDate;

	public ExpertAppraise(){
	}
    public void setExpertId(Integer value) {
        this.expertId = value;
    }

    public Integer getExpertId() {
        return this.expertId;
    }
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setUserName(String value) {
        this.userName = value;
    }

    public String getUserName() {
        return this.userName;
    }
    public void setServerType(String value) {
        this.serverType = value;
    }

    public String getServerType() {
        return this.serverType;
    }
    public void setUserComments(String value) {
        this.userComments = value;
    }

    public String getUserComments() {
        return this.userComments;
    }
    public void setIsChecked(String value) {
        this.isChecked = value;
    }

    public String getIsChecked() {
        return this.isChecked;
    }
    public void setCreateDate(Long value) {
        this.createDate = value;
    }

    public Long getCreateDate() {
        return this.createDate;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ExpertId",getExpertId())
			.append("UserId",getUserId())
			.append("UserName",getUserName())
			.append("ServerType",getServerType())
			.append("UserComments",getUserComments())
			.append("IsChecked",getIsChecked())
			.append("CreateDate",getCreateDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpertAppraise == false) return false;
		if(this == obj) return true;
		ExpertAppraise other = (ExpertAppraise)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

