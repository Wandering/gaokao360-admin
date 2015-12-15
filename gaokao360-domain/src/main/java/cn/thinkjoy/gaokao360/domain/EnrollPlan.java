/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  EnrollPlan.java 2015-12-15 17:20:06 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class EnrollPlan extends BaseDomain<Long>{
    /** 排序 */
    private Integer sort;
    /** 政策一级分类 */
    private Long batchId;
    /** 政策分类 */
    private String batch;
    /** 院校代码 */
    private String universityCode;
    /** 院校名称 */
    private String universityName;
    /** 地区 */
    private Integer provinceId;
    /** 地区名称 */
    private String provinceName;
    /** 城市id */
    private Integer cityId;
    /** 城市名称 */
    private String cityName;
    /** 区县id */
    private Integer countyId;
    /** 区县名称 */
    private String countyName;
    /** 专业名称 */
    private String subject;
    /** maoredName */
    private Integer majoredId;
    /**  */
    private String majoredName;
    /** majoredcode */
    private String majoredCode;
    /** 11 */
    private Integer enrollNumber;
    /** 学校长度 */
    private String schoolLength;
    /** 11 */
    private String foreignLanguage;
    /** 11 */
    private String feeStandard;
    /**  */
    private String enrollInfo;
    /**  */
    private Date creatDate;
    /**  */
    private String year;

	public EnrollPlan(){
	}
    public void setSort(Integer value) {
        this.sort = value;
    }

    public Integer getSort() {
        return this.sort;
    }
    public void setBatchId(Long value) {
        this.batchId = value;
    }

    public Long getBatchId() {
        return this.batchId;
    }
    public void setBatch(String value) {
        this.batch = value;
    }

    public String getBatch() {
        return this.batch;
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
    public void setProvinceId(Integer value) {
        this.provinceId = value;
    }

    public Integer getProvinceId() {
        return this.provinceId;
    }
    public void setProvinceName(String value) {
        this.provinceName = value;
    }

    public String getProvinceName() {
        return this.provinceName;
    }
    public void setCityId(Integer value) {
        this.cityId = value;
    }

    public Integer getCityId() {
        return this.cityId;
    }
    public void setCityName(String value) {
        this.cityName = value;
    }

    public String getCityName() {
        return this.cityName;
    }
    public void setCountyId(Integer value) {
        this.countyId = value;
    }

    public Integer getCountyId() {
        return this.countyId;
    }
    public void setCountyName(String value) {
        this.countyName = value;
    }

    public String getCountyName() {
        return this.countyName;
    }
    public void setSubject(String value) {
        this.subject = value;
    }

    public String getSubject() {
        return this.subject;
    }
    public void setMajoredId(Integer value) {
        this.majoredId = value;
    }

    public Integer getMajoredId() {
        return this.majoredId;
    }
    public void setMajoredName(String value) {
        this.majoredName = value;
    }

    public String getMajoredName() {
        return this.majoredName;
    }
    public void setMajoredCode(String value) {
        this.majoredCode = value;
    }

    public String getMajoredCode() {
        return this.majoredCode;
    }
    public void setEnrollNumber(Integer value) {
        this.enrollNumber = value;
    }

    public Integer getEnrollNumber() {
        return this.enrollNumber;
    }
    public void setSchoolLength(String value) {
        this.schoolLength = value;
    }

    public String getSchoolLength() {
        return this.schoolLength;
    }
    public void setForeignLanguage(String value) {
        this.foreignLanguage = value;
    }

    public String getForeignLanguage() {
        return this.foreignLanguage;
    }
    public void setFeeStandard(String value) {
        this.feeStandard = value;
    }

    public String getFeeStandard() {
        return this.feeStandard;
    }
    public void setEnrollInfo(String value) {
        this.enrollInfo = value;
    }

    public String getEnrollInfo() {
        return this.enrollInfo;
    }

    public void setCreatDate(Date value) {
        this.creatDate = value;
    }

    public Date getCreatDate() {
        return this.creatDate;
    }
    public void setYear(String value) {
        this.year = value;
    }

    public String getYear() {
        return this.year;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Sort",getSort())
			.append("BatchId",getBatchId())
			.append("Batch",getBatch())
			.append("UniversityCode",getUniversityCode())
			.append("UniversityName",getUniversityName())
			.append("ProvinceId",getProvinceId())
			.append("ProvinceName",getProvinceName())
			.append("CityId",getCityId())
			.append("CityName",getCityName())
			.append("CountyId",getCountyId())
			.append("CountyName",getCountyName())
			.append("Subject",getSubject())
			.append("MajoredId",getMajoredId())
			.append("MajoredName",getMajoredName())
			.append("MajoredCode",getMajoredCode())
			.append("EnrollNumber",getEnrollNumber())
			.append("SchoolLength",getSchoolLength())
			.append("ForeignLanguage",getForeignLanguage())
			.append("FeeStandard",getFeeStandard())
			.append("EnrollInfo",getEnrollInfo())
			.append("CreatDate",getCreatDate())
			.append("Year",getYear())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EnrollPlan == false) return false;
		if(this == obj) return true;
		EnrollPlan other = (EnrollPlan)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

