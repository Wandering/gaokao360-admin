/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  Majored.java 2015-12-16 16:48:39 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class Majored extends BaseDomain<Long>{
    /** 专业代码 */
    private String code;
    /** 专业名称 */
    private String name;
    /** 学历层次 */
    private String educationLevel;
    /** 学科分类 */
    private String subjectType;
    /** 专业分类 */
    private String majoredType;
    /** 专业分类 */
    private String degree;
    /** 相近专业 */
    private String similarMajored;
    /** 主干学科 */
    private String mainStudy;
    /** 主要课程 */
    private String mainCourse;
    /** 就业指导 */
    private String workGuide;
    /** 普通院校 */
    private String university;
    /** 重点院校 */
    private String stressUniversity;
    /** 其他 */
    private String other;
    /** 就业率 */
    private String jobRat;
    /** 平均收入 */
    private String incomeAve;
    /** 工作和专业相关度 */
    private String jobMajoredRel;

	public Majored(){
	}
    public void setCode(String value) {
        this.code = value;
    }

    public String getCode() {
        return this.code;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setEducationLevel(String value) {
        this.educationLevel = value;
    }

    public String getEducationLevel() {
        return this.educationLevel;
    }
    public void setSubjectType(String value) {
        this.subjectType = value;
    }

    public String getSubjectType() {
        return this.subjectType;
    }
    public void setMajoredType(String value) {
        this.majoredType = value;
    }

    public String getMajoredType() {
        return this.majoredType;
    }
    public void setDegree(String value) {
        this.degree = value;
    }

    public String getDegree() {
        return this.degree;
    }
    public void setSimilarMajored(String value) {
        this.similarMajored = value;
    }

    public String getSimilarMajored() {
        return this.similarMajored;
    }
    public void setMainStudy(String value) {
        this.mainStudy = value;
    }

    public String getMainStudy() {
        return this.mainStudy;
    }
    public void setMainCourse(String value) {
        this.mainCourse = value;
    }

    public String getMainCourse() {
        return this.mainCourse;
    }
    public void setWorkGuide(String value) {
        this.workGuide = value;
    }

    public String getWorkGuide() {
        return this.workGuide;
    }
    public void setUniversity(String value) {
        this.university = value;
    }

    public String getUniversity() {
        return this.university;
    }
    public void setStressUniversity(String value) {
        this.stressUniversity = value;
    }

    public String getStressUniversity() {
        return this.stressUniversity;
    }
    public void setOther(String value) {
        this.other = value;
    }

    public String getOther() {
        return this.other;
    }
    public void setJobRat(String value) {
        this.jobRat = value;
    }

    public String getJobRat() {
        return this.jobRat;
    }
    public void setIncomeAve(String value) {
        this.incomeAve = value;
    }

    public String getIncomeAve() {
        return this.incomeAve;
    }
    public void setJobMajoredRel(String value) {
        this.jobMajoredRel = value;
    }

    public String getJobMajoredRel() {
        return this.jobMajoredRel;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Name",getName())
			.append("EducationLevel",getEducationLevel())
			.append("SubjectType",getSubjectType())
			.append("MajoredType",getMajoredType())
			.append("Degree",getDegree())
			.append("SimilarMajored",getSimilarMajored())
			.append("MainStudy",getMainStudy())
			.append("MainCourse",getMainCourse())
			.append("WorkGuide",getWorkGuide())
			.append("University",getUniversity())
			.append("StressUniversity",getStressUniversity())
			.append("Other",getOther())
			.append("JobRat",getJobRat())
			.append("IncomeAve",getIncomeAve())
			.append("JobMajoredRel",getJobMajoredRel())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Majored == false) return false;
		if(this == obj) return true;
		Majored other = (Majored)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

