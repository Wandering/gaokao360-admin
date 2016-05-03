/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorEnrollingPlan.java 2016-04-29 15:38:17 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class UniversityMajorEnrollingPlan extends BaseDomain<Long>{
    /** 学校名称 */
    private String universityName;
    /** 学校ID */
    private Long universityId;
    /** 年份 */
    private Integer year;
    /** 省份ID */
    private Long areaId;
    /** 批次 */
    private String batch;
    /** 科类，1文科，2理科 */
    private String majorType;
    /** 专业名称 */
    private String majorName;
    /** 计划招生数 */
    private Integer planEnrolling;
    /** 学年 */
    private Integer lengthOfSchooling;
    /** 学费 */
    private Integer schoolFee;
    /** 专业ID（专业名称不匹配，暂不用） */
    private Long majorId;

	public UniversityMajorEnrollingPlan(){
	}
    public void setUniversityName(String value) {
        this.universityName = value;
    }

    public String getUniversityName() {
        return this.universityName;
    }
    public void setUniversityId(Long value) {
        this.universityId = value;
    }

    public Long getUniversityId() {
        return this.universityId;
    }
    public void setYear(Integer value) {
        this.year = value;
    }

    public Integer getYear() {
        return this.year;
    }
    public void setAreaId(Long value) {
        this.areaId = value;
    }

    public Long getAreaId() {
        return this.areaId;
    }
    public void setBatch(String value) {
        this.batch = value;
    }

    public String getBatch() {
        return this.batch;
    }
    public void setMajorType(String value) {
        this.majorType = value;
    }

    public String getMajorType() {
        return this.majorType;
    }
    public void setMajorName(String value) {
        this.majorName = value;
    }

    public String getMajorName() {
        return this.majorName;
    }
    public void setPlanEnrolling(Integer value) {
        this.planEnrolling = value;
    }

    public Integer getPlanEnrolling() {
        return this.planEnrolling;
    }
    public void setLengthOfSchooling(Integer value) {
        this.lengthOfSchooling = value;
    }

    public Integer getLengthOfSchooling() {
        return this.lengthOfSchooling;
    }
    public void setSchoolFee(Integer value) {
        this.schoolFee = value;
    }

    public Integer getSchoolFee() {
        return this.schoolFee;
    }
    public void setMajorId(Long value) {
        this.majorId = value;
    }

    public Long getMajorId() {
        return this.majorId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UniversityName",getUniversityName())
			.append("UniversityId",getUniversityId())
			.append("Year",getYear())
			.append("AreaId",getAreaId())
			.append("Batch",getBatch())
			.append("MajorType",getMajorType())
			.append("MajorName",getMajorName())
			.append("PlanEnrolling",getPlanEnrolling())
			.append("LengthOfSchooling",getLengthOfSchooling())
			.append("SchoolFee",getSchoolFee())
			.append("MajorId",getMajorId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UniversityMajorEnrollingPlan == false) return false;
		if(this == obj) return true;
		UniversityMajorEnrollingPlan other = (UniversityMajorEnrollingPlan)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

