/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: managerui-biz-startup
 * $Id:  MajorBase.java 2016-09-07 09:32:52 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class MajorBase extends BaseDomain<Integer>{
    /** 专业类型，0本科／1专科。对应字典表中的MAJOR_TYPE类型。 */
    private Integer majorType;
    /** 学科门类CODE */
    private Integer disciplineCode;
    /** 学科门类 */
    private String disciplineName;
    /** 专业门类CODE */
    private Integer disciplineSubCode;
    /** 学科门类 */
    private String disciplineSubName;
    /** 专业CODE */
    private String majorCode;
    /** 专业名称 */
    private String majorName;
    /** 就业排名 */
    private String majorNote;
    /** 薪资排名 */
    private Integer salaryRank;
    /** 就业率 */
    private String employmentRate;

	public MajorBase(){
	}
    public void setMajorType(Integer value) {
        this.majorType = value;
    }

    public Integer getMajorType() {
        return this.majorType;
    }
    public void setDisciplineCode(Integer value) {
        this.disciplineCode = value;
    }

    public Integer getDisciplineCode() {
        return this.disciplineCode;
    }
    public void setDisciplineName(String value) {
        this.disciplineName = value;
    }

    public String getDisciplineName() {
        return this.disciplineName;
    }
    public void setDisciplineSubCode(Integer value) {
        this.disciplineSubCode = value;
    }

    public Integer getDisciplineSubCode() {
        return this.disciplineSubCode;
    }
    public void setDisciplineSubName(String value) {
        this.disciplineSubName = value;
    }

    public String getDisciplineSubName() {
        return this.disciplineSubName;
    }
    public void setMajorCode(String value) {
        this.majorCode = value;
    }

    public String getMajorCode() {
        return this.majorCode;
    }
    public void setMajorName(String value) {
        this.majorName = value;
    }

    public String getMajorName() {
        return this.majorName;
    }
    public void setMajorNote(String value) {
        this.majorNote = value;
    }

    public String getMajorNote() {
        return this.majorNote;
    }
    public void setSalaryRank(Integer value) {
        this.salaryRank = value;
    }

    public Integer getSalaryRank() {
        return this.salaryRank;
    }
    public void setEmploymentRate(String value) {
        this.employmentRate = value;
    }

    public String getEmploymentRate() {
        return this.employmentRate;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MajorType",getMajorType())
			.append("DisciplineCode",getDisciplineCode())
			.append("DisciplineName",getDisciplineName())
			.append("DisciplineSubCode",getDisciplineSubCode())
			.append("DisciplineSubName",getDisciplineSubName())
			.append("MajorCode",getMajorCode())
			.append("MajorName",getMajorName())
			.append("MajorNote",getMajorNote())
			.append("SalaryRank",getSalaryRank())
			.append("EmploymentRate",getEmploymentRate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MajorBase == false) return false;
		if(this == obj) return true;
		MajorBase other = (MajorBase)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

