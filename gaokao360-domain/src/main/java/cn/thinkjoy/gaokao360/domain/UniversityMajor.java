/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajor.java 2015-12-30 15:19:55 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class UniversityMajor extends CreateBaseDomain<Long>{
    /** 学校ID */
    private Long universityId;
    /** 专业名称 */
    private String majorName;
    /** 专业科类，1文史，2理工。对应字典表中的MAJOR_TYPE类型 */
    private Integer majorType;
    /** 学历层次 */
    private Integer educationLevel;
    /** 获得学位 */
    private String gainDegree;
    /** 专业排名 */
    private Integer majorRank;
    /**  */
    private Boolean isDelete;

	public UniversityMajor(){
	}
    public void setUniversityId(Long value) {
        this.universityId = value;
    }

    public Long getUniversityId() {
        return this.universityId;
    }
    public void setMajorName(String value) {
        this.majorName = value;
    }

    public String getMajorName() {
        return this.majorName;
    }
    public void setMajorType(Integer value) {
        this.majorType = value;
    }

    public Integer getMajorType() {
        return this.majorType;
    }
    public void setEducationLevel(Integer value) {
        this.educationLevel = value;
    }

    public Integer getEducationLevel() {
        return this.educationLevel;
    }
    public void setGainDegree(String value) {
        this.gainDegree = value;
    }

    public String getGainDegree() {
        return this.gainDegree;
    }
    public void setMajorRank(Integer value) {
        this.majorRank = value;
    }

    public Integer getMajorRank() {
        return this.majorRank;
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
			.append("UniversityId",getUniversityId())
			.append("MajorName",getMajorName())
			.append("MajorType",getMajorType())
			.append("EducationLevel",getEducationLevel())
			.append("GainDegree",getGainDegree())
			.append("MajorRank",getMajorRank())
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
		if(obj instanceof UniversityMajor == false) return false;
		if(this == obj) return true;
		UniversityMajor other = (UniversityMajor)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

