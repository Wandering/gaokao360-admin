/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  EnrollReport.java 2015-12-15 17:20:07 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class EnrollReport extends BaseDomain<Long>{
    /** 排序 */
    private String sortId;
    /** 年份 */
    private String year;
    /** 分类id */
    private String batchId;
    /** 分类 */
    private String batch;
    /** 分类1 */
    private String batch1;
    /** 院校代码 */
    private String universityCode;
    /** 院校名称 */
    private String universityName;
    /** 1 */
    private String wsPlanNumber;
    /** 1 */
    private String wsEnrollNumber;
    /** 1 */
    private String wsHighestScore;
    /** 1 */
    private String wsLowestScore;
    /** 1 */
    private String wsLowestRank;
    /** 11 */
    private String wsAverageScore;
    /** 1 */
    private String wsAverageRank;
    /** 11 */
    private String lgPlanNumber;
    /** 1 */
    private String lgEnrollNumber;
    /** 1 */
    private String lgHighestScore;
    /** 1 */
    private String lgHighestRank;
    /** 11 */
    private String lgLowestScore;
    /** 1 */
    private String lgLowestRank;
    /** 1 */
    private String lgAverageScore;
    /** 1 */
    private String lgAverageRank;
    /** 1 */
    private String wsHighestRank;
    /** 1 */
    private String planNumber;
    /** 1 */
    private String enrollNumber;

	public EnrollReport(){
	}
    public void setSortId(String value) {
        this.sortId = value;
    }

    public String getSortId() {
        return this.sortId;
    }
    public void setYear(String value) {
        this.year = value;
    }

    public String getYear() {
        return this.year;
    }
    public void setBatchId(String value) {
        this.batchId = value;
    }

    public String getBatchId() {
        return this.batchId;
    }
    public void setBatch(String value) {
        this.batch = value;
    }

    public String getBatch() {
        return this.batch;
    }
    public void setBatch1(String value) {
        this.batch1 = value;
    }

    public String getBatch1() {
        return this.batch1;
    }
    public void setUniversityCode(String value) {
        this.universityCode = value;
    }

    public String getUniversityCode() {
        return this.universityCode;
    }
    public void setUniversityName(String value) {
        this.universityName = value;
    }

    public String getUniversityName() {
        return this.universityName;
    }
    public void setWsPlanNumber(String value) {
        this.wsPlanNumber = value;
    }

    public String getWsPlanNumber() {
        return this.wsPlanNumber;
    }
    public void setWsEnrollNumber(String value) {
        this.wsEnrollNumber = value;
    }

    public String getWsEnrollNumber() {
        return this.wsEnrollNumber;
    }
    public void setWsHighestScore(String value) {
        this.wsHighestScore = value;
    }

    public String getWsHighestScore() {
        return this.wsHighestScore;
    }
    public void setWsLowestScore(String value) {
        this.wsLowestScore = value;
    }

    public String getWsLowestScore() {
        return this.wsLowestScore;
    }
    public void setWsLowestRank(String value) {
        this.wsLowestRank = value;
    }

    public String getWsLowestRank() {
        return this.wsLowestRank;
    }
    public void setWsAverageScore(String value) {
        this.wsAverageScore = value;
    }

    public String getWsAverageScore() {
        return this.wsAverageScore;
    }
    public void setWsAverageRank(String value) {
        this.wsAverageRank = value;
    }

    public String getWsAverageRank() {
        return this.wsAverageRank;
    }
    public void setLgPlanNumber(String value) {
        this.lgPlanNumber = value;
    }

    public String getLgPlanNumber() {
        return this.lgPlanNumber;
    }
    public void setLgEnrollNumber(String value) {
        this.lgEnrollNumber = value;
    }

    public String getLgEnrollNumber() {
        return this.lgEnrollNumber;
    }
    public void setLgHighestScore(String value) {
        this.lgHighestScore = value;
    }

    public String getLgHighestScore() {
        return this.lgHighestScore;
    }
    public void setLgHighestRank(String value) {
        this.lgHighestRank = value;
    }

    public String getLgHighestRank() {
        return this.lgHighestRank;
    }
    public void setLgLowestScore(String value) {
        this.lgLowestScore = value;
    }

    public String getLgLowestScore() {
        return this.lgLowestScore;
    }
    public void setLgLowestRank(String value) {
        this.lgLowestRank = value;
    }

    public String getLgLowestRank() {
        return this.lgLowestRank;
    }
    public void setLgAverageScore(String value) {
        this.lgAverageScore = value;
    }

    public String getLgAverageScore() {
        return this.lgAverageScore;
    }
    public void setLgAverageRank(String value) {
        this.lgAverageRank = value;
    }

    public String getLgAverageRank() {
        return this.lgAverageRank;
    }
    public void setWsHighestRank(String value) {
        this.wsHighestRank = value;
    }

    public String getWsHighestRank() {
        return this.wsHighestRank;
    }
    public void setPlanNumber(String value) {
        this.planNumber = value;
    }

    public String getPlanNumber() {
        return this.planNumber;
    }
    public void setEnrollNumber(String value) {
        this.enrollNumber = value;
    }

    public String getEnrollNumber() {
        return this.enrollNumber;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SortId",getSortId())
			.append("Year",getYear())
			.append("BatchId",getBatchId())
			.append("Batch",getBatch())
			.append("Batch1",getBatch1())
			.append("UniversityCode",getUniversityCode())
			.append("UniversityName",getUniversityName())
			.append("WsPlanNumber",getWsPlanNumber())
			.append("WsEnrollNumber",getWsEnrollNumber())
			.append("WsHighestScore",getWsHighestScore())
			.append("WsLowestScore",getWsLowestScore())
			.append("WsLowestRank",getWsLowestRank())
			.append("WsAverageScore",getWsAverageScore())
			.append("WsAverageRank",getWsAverageRank())
			.append("LgPlanNumber",getLgPlanNumber())
			.append("LgEnrollNumber",getLgEnrollNumber())
			.append("LgHighestScore",getLgHighestScore())
			.append("LgHighestRank",getLgHighestRank())
			.append("LgLowestScore",getLgLowestScore())
			.append("LgLowestRank",getLgLowestRank())
			.append("LgAverageScore",getLgAverageScore())
			.append("LgAverageRank",getLgAverageRank())
			.append("WsHighestRank",getWsHighestRank())
			.append("PlanNumber",getPlanNumber())
			.append("EnrollNumber",getEnrollNumber())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EnrollReport == false) return false;
		if(this == obj) return true;
		EnrollReport other = (EnrollReport)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

