/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  University.java 2015-12-30 11:42:40 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class University extends CreateBaseDomain<Long>{
    /** 学校名称 */
    private String name;
    /** 学校国码 */
    private String code;
    /** 院校省码 */
    private String provinceCode;
    /** 隶属 */
    private String subjection;
    /** 位置 */
    private String areaid;
    /** 院校类型，对应字典表中的UNIVERSITY_TYPE */
    private Integer type;
    /** 院校首页链接 */
    private String url;
    /** 院校logo链接 */
    private String photoUrl;
    /** 学校是否为985，211等 */
    private String property;
    /** 学校排名 */
    private Integer rank;
    /** 选测等级 */
    private String xcRank;
    /** 学历层次，本科或专科，对应字典表中的EDULEVEL */
    private Integer educationLevel;
    /** 是否逻辑删除 */
    private Boolean isDelete;

	public University(){
	}

    public String getXcRank() {
        return xcRank;
    }

    public void setXcRank(String xcRank) {
        this.xcRank = xcRank;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setCode(String value) {
        this.code = value;
    }

    public String getCode() {
        return this.code;
    }
    public void setProvinceCode(String value) {
        this.provinceCode = value;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }
    public void setSubjection(String value) {
        this.subjection = value;
    }

    public String getSubjection() {
        return this.subjection;
    }
    public void setAreaid(String value) {
        this.areaid = value;
    }

    public String getAreaid() {
        return this.areaid;
    }
    public void setType(Integer value) {
        this.type = value;
    }

    public Integer getType() {
        return this.type;
    }
    public void setUrl(String value) {
        this.url = value;
    }

    public String getUrl() {
        return this.url;
    }
    public void setPhotoUrl(String value) {
        this.photoUrl = value;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }
    public void setProperty(String value) {
        this.property = value;
    }

    public String getProperty() {
        return this.property;
    }
    public void setRank(Integer value) {
        this.rank = value;
    }

    public Integer getRank() {
        return this.rank;
    }
    public void setEducationLevel(Integer value) {
        this.educationLevel = value;
    }

    public Integer getEducationLevel() {
        return this.educationLevel;
    }
    public void setIsDelete(Boolean value) {
        this.isDelete = value;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Code",getCode())
			.append("ProvinceCode",getProvinceCode())
			.append("Subjection",getSubjection())
			.append("Areaid",getAreaid())
			.append("Type",getType())
			.append("Url",getUrl())
			.append("PhotoUrl",getPhotoUrl())
			.append("Property",getProperty())
			.append("Rank",getRank())
			.append("EducationLevel",getEducationLevel())
			.append("CreateDate",getCreateDate())
			.append("Creator",getCreator())
			.append("LastModDate",getLastModDate())
			.append("LastModifier",getLastModifier())
			.append("IsDelete",getIsDelete())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof University == false) return false;
		if(this == obj) return true;
		University other = (University)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

