/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionDetail.java 2015-12-28 18:05:28 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class ProfessionDetail extends CreateBaseDomain<Long>{
	/** 相关专业 */
	private String relateMajor;
	/** 职业介绍 */
	private String introduction;
	/** 工作内容 */
	private String workContent;
	/** 从业要求 */
	private String vocationalDemand;
	/** 就业前景 */
	private String careerProspects;
	/**  */
	private Boolean isDelete;

	public ProfessionDetail(){
	}
	public void setRelateMajor(String value) {
		this.relateMajor = value;
	}

	public String getRelateMajor() {
		return this.relateMajor;
	}
	public void setIntroduction(String value) {
		this.introduction = value;
	}

	public String getIntroduction() {
		return this.introduction;
	}
	public void setWorkContent(String value) {
		this.workContent = value;
	}

	public String getWorkContent() {
		return this.workContent;
	}
	public void setVocationalDemand(String value) {
		this.vocationalDemand = value;
	}

	public String getVocationalDemand() {
		return this.vocationalDemand;
	}
	public void setCareerProspects(String value) {
		this.careerProspects = value;
	}

	public String getCareerProspects() {
		return this.careerProspects;
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
				.append("RelateMajor",getRelateMajor())
				.append("Introduction",getIntroduction())
				.append("WorkContent",getWorkContent())
				.append("VocationalDemand",getVocationalDemand())
				.append("CareerProspects",getCareerProspects())
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
		if(obj instanceof ProfessionDetail == false) return false;
		if(this == obj) return true;
		ProfessionDetail other = (ProfessionDetail)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}