/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajorEmployment.java 2015-12-29 16:07:18 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class MajorEmployment extends CreateBaseDomain<Long>{
    /** 专业ID */
    private Long majorId;
    /** 年份 */
    private Integer year;
    /** 薪资 */
    private Long salary;
    /** 就业率 */
    private String employmentRate;
    /**  */
    private Boolean idDelete;

	public MajorEmployment(){
	}
    public void setMajorId(Long value) {
        this.majorId = value;
    }

    public Long getMajorId() {
        return this.majorId;
    }
    public void setYear(Integer value) {
        this.year = value;
    }

    public Integer getYear() {
        return this.year;
    }
    public void setSalary(Long value) {
        this.salary = value;
    }

    public Long getSalary() {
        return this.salary;
    }
    public void setEmploymentRate(String value) {
        this.employmentRate = value;
    }

    public String getEmploymentRate() {
        return this.employmentRate;
    }
    public void setIdDelete(Boolean value) {
        this.idDelete = value;
    }

    public Boolean getIdDelete() {
        return this.idDelete;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MajorId",getMajorId())
			.append("Year",getYear())
			.append("Salary",getSalary())
			.append("EmploymentRate",getEmploymentRate())
			.append("CreateDate",getCreateDate())
			.append("Creator",getCreator())
			.append("LastModDate",getLastModDate())
			.append("LastModifier",getLastModifier())
			.append("IdDelete",getIdDelete())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MajorEmployment == false) return false;
		if(this == obj) return true;
		MajorEmployment other = (MajorEmployment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

