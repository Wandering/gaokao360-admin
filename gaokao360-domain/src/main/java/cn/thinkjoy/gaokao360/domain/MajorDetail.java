/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajorDetail.java 2015-12-29 18:12:00 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class MajorDetail extends CreateBaseDomain<Long>{
    /** 专业代码 */
    private String majorCode;
    /** 授予学位 */
    private String degreeOffered;
    /** 学习年限 */
    private Integer schoolingDuration;
    /** 开设课程 */
    private String offerCourses;
    /** 专业解读 */
    private String majorIntroduce;
    /** 相近专业 */
    private String similarMajors;
    /** 职业方向

职业方向 */
    private String specialisation;
    /** 优秀学长 */
    private String outstandingMentor;
    /**  */
    private Boolean idDelete;

	public MajorDetail(){
	}
    public void setMajorCode(String value) {
        this.majorCode = value;
    }

    public String getMajorCode() {
        return this.majorCode;
    }
    public void setDegreeOffered(String value) {
        this.degreeOffered = value;
    }

    public String getDegreeOffered() {
        return this.degreeOffered;
    }
    public void setSchoolingDuration(Integer value) {
        this.schoolingDuration = value;
    }

    public Integer getSchoolingDuration() {
        return this.schoolingDuration;
    }
    public void setOfferCourses(String value) {
        this.offerCourses = value;
    }

    public String getOfferCourses() {
        return this.offerCourses;
    }
    public void setMajorIntroduce(String value) {
        this.majorIntroduce = value;
    }

    public String getMajorIntroduce() {
        return this.majorIntroduce;
    }
    public void setSimilarMajors(String value) {
        this.similarMajors = value;
    }

    public String getSimilarMajors() {
        return this.similarMajors;
    }
    public void setSpecialisation(String value) {
        this.specialisation = value;
    }

    public String getSpecialisation() {
        return this.specialisation;
    }
    public void setOutstandingMentor(String value) {
        this.outstandingMentor = value;
    }

    public String getOutstandingMentor() {
        return this.outstandingMentor;
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
			.append("MajorCode",getMajorCode())
			.append("DegreeOffered",getDegreeOffered())
			.append("SchoolingDuration",getSchoolingDuration())
			.append("OfferCourses",getOfferCourses())
			.append("MajorIntroduce",getMajorIntroduce())
			.append("SimilarMajors",getSimilarMajors())
			.append("Specialisation",getSpecialisation())
			.append("OutstandingMentor",getOutstandingMentor())
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
		if(obj instanceof MajorDetail == false) return false;
		if(this == obj) return true;
		MajorDetail other = (MajorDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

