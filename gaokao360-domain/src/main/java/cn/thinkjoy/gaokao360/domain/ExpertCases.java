/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertCases.java 2016-12-26 11:24:38 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class ExpertCases extends BaseDomain<Long>{
    /** 专家ID */
    private Long expertId;
    /** 用户名称 */
    private String userName;
    /** 用户头像url，冗余字段，为假数据准备 */
    private String userImgUrl;
    /** 学校 */
    private String school;
    /** 服务类型ID */
    private Long serviceId;
    /** 用户反馈 */
    private String userComments;
    /**  */
    private String createDate;

	public ExpertCases(){
	}
    public void setExpertId(Long value) {
        this.expertId = value;
    }

    public Long getExpertId() {
        return this.expertId;
    }
    public void setUserName(String value) {
        this.userName = value;
    }

    public String getUserName() {
        return this.userName;
    }
    public void setUserImgUrl(String value) {
        this.userImgUrl = value;
    }

    public String getUserImgUrl() {
        return this.userImgUrl;
    }
    public void setSchool(String value) {
        this.school = value;
    }

    public String getSchool() {
        return this.school;
    }
    public void setServiceId(Long value) {
        this.serviceId = value;
    }

    public Long getServiceId() {
        return this.serviceId;
    }
    public void setUserComments(String value) {
        this.userComments = value;
    }

    public String getUserComments() {
        return this.userComments;
    }
    public void setCreateDate(String value) {
        this.createDate = value;
    }

    public String getCreateDate() {
        return this.createDate;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ExpertId",getExpertId())
			.append("UserName",getUserName())
			.append("UserImgUrl",getUserImgUrl())
			.append("School",getSchool())
			.append("ServiceId",getServiceId())
			.append("UserComments",getUserComments())
			.append("CreateDate",getCreateDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpertCases == false) return false;
		if(this == obj) return true;
		ExpertCases other = (ExpertCases)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

