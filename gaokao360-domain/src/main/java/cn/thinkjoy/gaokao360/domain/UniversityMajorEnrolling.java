/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajorEnrolling.java 2016-02-02 11:27:09 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class UniversityMajorEnrolling extends CreateBaseDomain<Long>{
    /** 专业所属学校ID */
    private Long universityId;
    /** 院校名称 */
    private String universityName;
    /** 专业名称(与zgk_major表的major_name字段无关，不做匹配) */
    private String majorName;
    /** 省份ID */
    private Long areaId;
    /** 年份 */
    private Integer year;
    /** 批次，对应字典表MAJOR_BATCH类型 */
    private Integer batch;
    /** 专业类别，1文史，2理工。对应字典表中的UNIVERSITY_MAJOR_TYPE类型 */
    private Integer majorType;
    /** 实际招生人数 */
    private Integer realEnrollingNumber;
    /** 录取平均分（查询时为0的，返回‘’或者‘－’） */
    private Integer averageScore;
    /** 录取最高分（无数据，暂不用） */
    private String highestScore;
    /** 录取最低分（无数据，暂不用） */
    private Integer lowestScore;
    /** 招生性质（无数据，暂时不用） */
    private Long admissionFeature;
    /** 专业ID（名称不匹配暂不用） */
    private Long majorId;
    /** 学制，1.四年制。对应字典表中的LENGTH_OF_SCHOOLING（无数据，暂时不用） */
    private String lengthOfSchooling;
    /** 录取最高位次(无数据，暂不用) */
    private Integer highestPrecedence;
    /** 录取平均位次(无数据，暂不用) */
    private Integer averagePrecedence;
    /** 录取最低位次(无数据，暂不用) */
    private Integer lowestPrecedence;
    /** 学费(无数据，暂不用) */
    private Integer schoolFee;
    /** 计划招生人数（暂无数据） */
    private Integer planEnrollingNumber;
    /**  */
    private Boolean isDelete;
    /** 保留字段 */
    private Boolean flag1;
    /** 保留字段 */
    private Boolean flag2;
    /** 保留字段 */
    private Boolean flag3;
    /** 保留字段 */
    private Boolean flag4;
    /** 保留字段 */
    private Boolean flag5;

    public UniversityMajorEnrolling(){
    }
    public void setUniversityId(Long value) {
        this.universityId = value;
    }

    public Long getUniversityId() {
        return this.universityId;
    }
    public void setUniversityName(String value) {
        this.universityName = value;
    }

    public String getUniversityName() {
        return this.universityName;
    }
    public void setMajorName(String value) {
        this.majorName = value;
    }

    public String getMajorName() {
        return this.majorName;
    }
    public void setAreaId(Long value) {
        this.areaId = value;
    }

    public Long getAreaId() {
        return this.areaId;
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
    public void setMajorType(Integer value) {
        this.majorType = value;
    }

    public Integer getMajorType() {
        return this.majorType;
    }
    public void setRealEnrollingNumber(Integer value) {
        this.realEnrollingNumber = value;
    }

    public Integer getRealEnrollingNumber() {
        return this.realEnrollingNumber;
    }
    public void setAverageScore(Integer value) {
        this.averageScore = value;
    }

    public Integer getAverageScore() {
        return this.averageScore;
    }
    public void setHighestScore(String value) {
        this.highestScore = value;
    }

    public String getHighestScore() {
        return this.highestScore;
    }
    public void setLowestScore(Integer value) {
        this.lowestScore = value;
    }

    public Integer getLowestScore() {
        return this.lowestScore;
    }
    public void setAdmissionFeature(Long value) {
        this.admissionFeature = value;
    }

    public Long getAdmissionFeature() {
        return this.admissionFeature;
    }
    public void setMajorId(Long value) {
        this.majorId = value;
    }

    public Long getMajorId() {
        return this.majorId;
    }
    public void setLengthOfSchooling(String value) {
        this.lengthOfSchooling = value;
    }

    public String getLengthOfSchooling() {
        return this.lengthOfSchooling;
    }
    public void setHighestPrecedence(Integer value) {
        this.highestPrecedence = value;
    }

    public Integer getHighestPrecedence() {
        return this.highestPrecedence;
    }
    public void setAveragePrecedence(Integer value) {
        this.averagePrecedence = value;
    }

    public Integer getAveragePrecedence() {
        return this.averagePrecedence;
    }
    public void setLowestPrecedence(Integer value) {
        this.lowestPrecedence = value;
    }

    public Integer getLowestPrecedence() {
        return this.lowestPrecedence;
    }
    public void setSchoolFee(Integer value) {
        this.schoolFee = value;
    }

    public Integer getSchoolFee() {
        return this.schoolFee;
    }
    public void setPlanEnrollingNumber(Integer value) {
        this.planEnrollingNumber = value;
    }

    public Integer getPlanEnrollingNumber() {
        return this.planEnrollingNumber;
    }
    public void setIsDelete(Boolean value) {
        this.isDelete = value;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }
    public void setFlag1(Boolean value) {
        this.flag1 = value;
    }

    public Boolean getFlag1() {
        return this.flag1;
    }
    public void setFlag2(Boolean value) {
        this.flag2 = value;
    }

    public Boolean getFlag2() {
        return this.flag2;
    }
    public void setFlag3(Boolean value) {
        this.flag3 = value;
    }

    public Boolean getFlag3() {
        return this.flag3;
    }
    public void setFlag4(Boolean value) {
        this.flag4 = value;
    }

    public Boolean getFlag4() {
        return this.flag4;
    }
    public void setFlag5(Boolean value) {
        this.flag5 = value;
    }

    public Boolean getFlag5() {
        return this.flag5;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("Id",getId())
                .append("UniversityId",getUniversityId())
                .append("UniversityName",getUniversityName())
                .append("MajorName",getMajorName())
                .append("AreaId",getAreaId())
                .append("Year",getYear())
                .append("Batch",getBatch())
                .append("MajorType",getMajorType())
                .append("RealEnrollingNumber",getRealEnrollingNumber())
                .append("AverageScore",getAverageScore())
                .append("HighestScore",getHighestScore())
                .append("LowestScore",getLowestScore())
                .append("AdmissionFeature",getAdmissionFeature())
                .append("MajorId",getMajorId())
                .append("LengthOfSchooling",getLengthOfSchooling())
                .append("HighestPrecedence",getHighestPrecedence())
                .append("AveragePrecedence",getAveragePrecedence())
                .append("LowestPrecedence",getLowestPrecedence())
                .append("SchoolFee",getSchoolFee())
                .append("PlanEnrollingNumber",getPlanEnrollingNumber())
                .append("CreateDate",getCreateDate())
                .append("Creator",getCreator())
                .append("LastModDate",getLastModDate())
                .append("LastModifier",getLastModifier())
                .append("IsDelete",getIsDelete())
                .append("Flag1",getFlag1())
                .append("Flag2",getFlag2())
                .append("Flag3",getFlag3())
                .append("Flag4",getFlag4())
                .append("Flag5",getFlag5())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof UniversityMajorEnrolling == false) return false;
        if(this == obj) return true;
        UniversityMajorEnrolling other = (UniversityMajorEnrolling)obj;
        return new EqualsBuilder()
                .append(getId(),other.getId())
                .isEquals();
    }
}

