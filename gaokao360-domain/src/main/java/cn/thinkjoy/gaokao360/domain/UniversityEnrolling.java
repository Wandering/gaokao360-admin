/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityEnrolling.java 2015-12-29 09:54:16 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class UniversityEnrolling extends CreateBaseDomain<Long>{
    /** 专业所属学校ID */
    private Long universityId;
    /** 年份 */
    private Integer year;
    /** 批次，对应字典表MAJOR_BATCH类型 */
    private Integer batch;
    /** 专业类别，1文史，2理工。对应字典表中的UNIVERSITY_MAJOR_TYPE类型 */
    private Integer universityMajorType;
    /** 计划招生人数 */
    private Integer planEnrollingNumber;
    /** 实际招生人数 */
    private Integer realEnrollingNumber;
    /** 学制，1.四年制。对应字典表中的LENGTH_OF_SCHOOLING */
    private String lengthOfSchooling;
    /** 学费,xxxx元/年 */
    private Integer schoolFee;
    /** 录取最高分 */
    private Integer highestScore;
    /** 录取最高位次 */
    private Integer highestPrecedence;
    /** 录取最低分 */
    private Integer lowestScore;
    /** 录取最低位次 */
    private Integer lowestPrecedence;
    /** 录取平均分 */
    private Integer averageScore;
    /** 录取平均位次 */
    private Integer averagePrecedence;
    /**  */
    private Boolean isDelete;

	public UniversityEnrolling(){
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
    public void setBatch(Integer value) {
        this.batch = value;
    }

    public Integer getBatch() {
        return this.batch;
    }
    public void setUniversityMajorType(Integer value) {
        this.universityMajorType = value;
    }

    public Integer getUniversityMajorType() {
        return this.universityMajorType;
    }
    public void setPlanEnrollingNumber(Integer value) {
        this.planEnrollingNumber = value;
    }

    public Integer getPlanEnrollingNumber() {
        return this.planEnrollingNumber;
    }
    public void setRealEnrollingNumber(Integer value) {
        this.realEnrollingNumber = value;
    }

    public Integer getRealEnrollingNumber() {
        return this.realEnrollingNumber;
    }
    public void setLengthOfSchooling(String value) {
        this.lengthOfSchooling = value;
    }

    public String getLengthOfSchooling() {
        return this.lengthOfSchooling;
    }
    public void setSchoolFee(Integer value) {
        this.schoolFee = value;
    }

    public Integer getSchoolFee() {
        return this.schoolFee;
    }
    public void setHighestScore(Integer value) {
        this.highestScore = value;
    }

    public Integer getHighestScore() {
        return this.highestScore;
    }
    public void setHighestPrecedence(Integer value) {
        this.highestPrecedence = value;
    }

    public Integer getHighestPrecedence() {
        return this.highestPrecedence;
    }
    public void setLowestScore(Integer value) {
        this.lowestScore = value;
    }

    public Integer getLowestScore() {
        return this.lowestScore;
    }
    public void setLowestPrecedence(Integer value) {
        this.lowestPrecedence = value;
    }

    public Integer getLowestPrecedence() {
        return this.lowestPrecedence;
    }
    public void setAverageScore(Integer value) {
        this.averageScore = value;
    }

    public Integer getAverageScore() {
        return this.averageScore;
    }
    public void setAveragePrecedence(Integer value) {
        this.averagePrecedence = value;
    }

    public Integer getAveragePrecedence() {
        return this.averagePrecedence;
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
			.append("Year",getYear())
			.append("Batch",getBatch())
			.append("UniversityMajorType",getUniversityMajorType())
			.append("PlanEnrollingNumber",getPlanEnrollingNumber())
			.append("RealEnrollingNumber",getRealEnrollingNumber())
			.append("LengthOfSchooling",getLengthOfSchooling())
			.append("SchoolFee",getSchoolFee())
			.append("HighestScore",getHighestScore())
			.append("HighestPrecedence",getHighestPrecedence())
			.append("LowestScore",getLowestScore())
			.append("LowestPrecedence",getLowestPrecedence())
			.append("AverageScore",getAverageScore())
			.append("AveragePrecedence",getAveragePrecedence())
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
		if(obj instanceof UniversityEnrolling == false) return false;
		if(this == obj) return true;
		UniversityEnrolling other = (UniversityEnrolling)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

