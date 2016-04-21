/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityDetail.java 2015-12-28 14:38:16 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class UniversityDetail extends BaseDomain<Long>{
    /** 学校地址 */
    private String address;
    /** 联系电话 */
    private String contactPhone;
    /** 院校简介 */
    private String universityIntro;
    /** 报考指南 */
    private String entranceIntro;
    /** 特色专业 */
    private String featureMajor;

	public UniversityDetail(){
	}
    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return this.address;
    }
    public void setContactPhone(String value) {
        this.contactPhone = value;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }
    public void setUniversityIntro(String value) {
        this.universityIntro = value;
    }

    public String getUniversityIntro() {
        return this.universityIntro;
    }
    public void setEntranceIntro(String value) {
        this.entranceIntro = value;
    }

    public String getEntranceIntro() {
        return this.entranceIntro;
    }
    public void setFeatureMajor(String value) {
        this.featureMajor = value;
    }

    public String getFeatureMajor() {
        return this.featureMajor;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Address",getAddress())
			.append("ContactPhone",getContactPhone())
			.append("UniversityIntro",getUniversityIntro())
			.append("EntranceIntro",getEntranceIntro())
			.append("FeatureMajor",getFeatureMajor())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UniversityDetail == false) return false;
		if(this == obj) return true;
		UniversityDetail other = (UniversityDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

