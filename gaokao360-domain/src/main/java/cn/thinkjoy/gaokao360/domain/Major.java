/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  Major.java 2015-12-29 16:07:16 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Major extends CreateBaseDomain<Long>{
    /** 专业类型，0本科／1专科。对应字典表中的MAJOR_TYPE类型。 */
    private Integer majorType;
    /** 专业类别 */
    private String majorCategory;
    /** 学科门类 */
    private String disciplineCategories;
    /** 专业名称 */
    private String majorName;
    /** 就业排名 */
    private Integer employmentRank;
    /** 薪资排名 */
    private Integer salaryRank;
    /** 逻辑删除表示位，0为不删除，1为删除。 */
    private Boolean isDelete;

	public Major(){
	}
    public void setMajorType(Integer value) {
        this.majorType = value;
    }

    public Integer getMajorType() {
        return this.majorType;
    }
    public void setMajorCategory(String value) {
        this.majorCategory = value;
    }

    public String getMajorCategory() {
        return this.majorCategory;
    }
    public void setDisciplineCategories(String value) {
        this.disciplineCategories = value;
    }

    public String getDisciplineCategories() {
        return this.disciplineCategories;
    }
    public void setMajorName(String value) {
        this.majorName = value;
    }

    public String getMajorName() {
        return this.majorName;
    }
    public void setEmploymentRank(Integer value) {
        this.employmentRank = value;
    }

    public Integer getEmploymentRank() {
        return this.employmentRank;
    }
    public void setSalaryRank(Integer value) {
        this.salaryRank = value;
    }

    public Integer getSalaryRank() {
        return this.salaryRank;
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
			.append("MajorType",getMajorType())
			.append("MajorCategory",getMajorCategory())
			.append("DisciplineCategories",getDisciplineCategories())
			.append("MajorName",getMajorName())
			.append("EmploymentRank",getEmploymentRank())
			.append("SalaryRank",getSalaryRank())
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
		if(obj instanceof Major == false) return false;
		if(this == obj) return true;
		Major other = (Major)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

