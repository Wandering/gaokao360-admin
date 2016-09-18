/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: managerui-biz-startup
 * $Id:  SchoolBase.java 2016-09-18 09:52:00 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class SchoolBase extends BaseDomain<Integer>{
    /** 院校代码 */
    private String schoolCode;
    /** 院校名称 */
    private String schoolName;
    /** 区域代码 */
    private String areaCode;
    /** 学校类型 幼儿园，小学<2xx ,初高中>=3xx */
    private Integer schoolType;

	public SchoolBase(){
	}
    public void setSchoolCode(String value) {
        this.schoolCode = value;
    }

    public String getSchoolCode() {
        return this.schoolCode;
    }
    public void setSchoolName(String value) {
        this.schoolName = value;
    }

    public String getSchoolName() {
        return this.schoolName;
    }
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    public String getAreaCode() {
        return this.areaCode;
    }
    public void setSchoolType(Integer value) {
        this.schoolType = value;
    }

    public Integer getSchoolType() {
        return this.schoolType;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SchoolCode",getSchoolCode())
			.append("SchoolName",getSchoolName())
			.append("AreaCode",getAreaCode())
			.append("SchoolType",getSchoolType())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SchoolBase == false) return false;
		if(this == obj) return true;
		SchoolBase other = (SchoolBase)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

