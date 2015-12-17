/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredScoreLine.java 2015-12-16 16:48:41 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class MajoredScoreLine extends BaseDomain<Long>{
    /** 排名 */
    private String sort;
    /** 专业id */
    private String majoredId;
    /** 专业编码 */
    private String majoredCode;
    /** 专业名称 */
    private String majoredName;
    /** 学院编码 */
    private String universityCode;
    /** 学院名称 */
    private String universityName;
    /** 平均分 */
    private Integer averageScore;
    /** 最高分 */
    private Integer highestScore;
    /** 省id */
    private Integer provinceId;
    /** 省名称 */
    private String provinceName;
    /** 市id */
    private Integer cityId;
    /** 市名称 */
    private String ciyName;
    /** 区域id */
    private Integer countyId;
    /** 区域名称 */
    private String countyName;
    /** 科类 */
    private String subject;
    /** 录取批次 */
    private String enrollBatch;
    /** 年份 */
    private String year;

	public MajoredScoreLine(){
	}
    public void setSort(String value) {
        this.sort = value;
    }

    public String getSort() {
        return this.sort;
    }
    public void setMajoredId(String value) {
        this.majoredId = value;
    }

    public String getMajoredId() {
        return this.majoredId;
    }
    public void setMajoredCode(String value) {
        this.majoredCode = value;
    }

    public String getMajoredCode() {
        return this.majoredCode;
    }
    public void setMajoredName(String value) {
        this.majoredName = value;
    }

    public String getMajoredName() {
        return this.majoredName;
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
    public void setAverageScore(Integer value) {
        this.averageScore = value;
    }

    public Integer getAverageScore() {
        return this.averageScore;
    }
    public void setHighestScore(Integer value) {
        this.highestScore = value;
    }

    public Integer getHighestScore() {
        return this.highestScore;
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
    public void setCiyName(String value) {
        this.ciyName = value;
    }

    public String getCiyName() {
        return this.ciyName;
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
    public void setEnrollBatch(String value) {
        this.enrollBatch = value;
    }

    public String getEnrollBatch() {
        return this.enrollBatch;
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
			.append("MajoredId",getMajoredId())
			.append("MajoredCode",getMajoredCode())
			.append("MajoredName",getMajoredName())
			.append("UniversityCode",getUniversityCode())
			.append("UniversityName",getUniversityName())
			.append("AverageScore",getAverageScore())
			.append("HighestScore",getHighestScore())
			.append("ProvinceId",getProvinceId())
			.append("ProvinceName",getProvinceName())
			.append("CityId",getCityId())
			.append("CiyName",getCiyName())
			.append("CountyId",getCountyId())
			.append("CountyName",getCountyName())
			.append("Subject",getSubject())
			.append("EnrollBatch",getEnrollBatch())
			.append("Year",getYear())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MajoredScoreLine == false) return false;
		if(this == obj) return true;
		MajoredScoreLine other = (MajoredScoreLine)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

