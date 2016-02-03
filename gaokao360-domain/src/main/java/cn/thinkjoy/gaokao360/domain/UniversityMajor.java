/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityMajor.java 2016-02-02 11:15:21 $
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
    /** 学校名称 */
    private String universityName;
    /** 专业名称 */
    private String majorName;
    /** 学历层次 */
    private Integer educationLevel;
    /** 薪资排名 */
    private Integer salaryRank;
    /** 就业排名 */
    private Integer jobRank;
    /** 特色重点专业(暂时不用) */
    private String majorFeature;
    /** 获得学位(无数据暂时不用) */
    private String gainDegree;
    /** 专业Id(名称不匹配暂时不用) */
    private Long majorId;
    /** 专业排名(无数据暂时不用) */
    private Integer majorRank;
    /** 专业类型(无数据暂时不用) */
    private Integer majorType;
    /** 专业科类，1文史，2理工。对应字典表中的MAJOR_SUBJECT类型(无数据暂时不用) */
    private Integer majorSubject;
    /** 是否逻辑删除 */
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

    public UniversityMajor(){
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
    public void setEducationLevel(Integer value) {
        this.educationLevel = value;
    }

    public Integer getEducationLevel() {
        return this.educationLevel;
    }
    public void setSalaryRank(Integer value) {
        this.salaryRank = value;
    }

    public Integer getSalaryRank() {
        return this.salaryRank;
    }
    public void setJobRank(Integer value) {
        this.jobRank = value;
    }

    public Integer getJobRank() {
        return this.jobRank;
    }
    public void setMajorFeature(String value) {
        this.majorFeature = value;
    }

    public String getMajorFeature() {
        return this.majorFeature;
    }
    public void setGainDegree(String value) {
        this.gainDegree = value;
    }

    public String getGainDegree() {
        return this.gainDegree;
    }
    public void setMajorId(Long value) {
        this.majorId = value;
    }

    public Long getMajorId() {
        return this.majorId;
    }
    public void setMajorRank(Integer value) {
        this.majorRank = value;
    }

    public Integer getMajorRank() {
        return this.majorRank;
    }
    public void setMajorType(Integer value) {
        this.majorType = value;
    }

    public Integer getMajorType() {
        return this.majorType;
    }
    public void setMajorSubject(Integer value) {
        this.majorSubject = value;
    }

    public Integer getMajorSubject() {
        return this.majorSubject;
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
                .append("EducationLevel",getEducationLevel())
                .append("SalaryRank",getSalaryRank())
                .append("JobRank",getJobRank())
                .append("MajorFeature",getMajorFeature())
                .append("GainDegree",getGainDegree())
                .append("MajorId",getMajorId())
                .append("MajorRank",getMajorRank())
                .append("MajorType",getMajorType())
                .append("MajorSubject",getMajorSubject())
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
        if(obj instanceof UniversityMajor == false) return false;
        if(this == obj) return true;
        UniversityMajor other = (UniversityMajor)obj;
        return new EqualsBuilder()
                .append(getId(),other.getId())
                .isEquals();
    }
}

