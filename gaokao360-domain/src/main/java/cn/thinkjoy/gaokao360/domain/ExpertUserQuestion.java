/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertUserQuestion.java 2016-12-30 11:34:05 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class ExpertUserQuestion extends BaseDomain<Integer>{
    /** 专家Id */
    private Integer expertId;
    /** 用户Id */
    private Integer userId;
    /** 用户名称 */
    private String userName;
    /** 用户问题 */
    private String userQuestion;
    /** 用户问题回答 */
    private String userAnswer;
    /**  */
    private Date createDate;

	public ExpertUserQuestion(){
	}
    public void setExpertId(Integer value) {
        this.expertId = value;
    }

    public Integer getExpertId() {
        return this.expertId;
    }
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public Integer getUserId() {
        return this.userId;
    }
    public void setUserName(String value) {
        this.userName = value;
    }

    public String getUserName() {
        return this.userName;
    }
    public void setUserQuestion(String value) {
        this.userQuestion = value;
    }

    public String getUserQuestion() {
        return this.userQuestion;
    }
    public void setUserAnswer(String value) {
        this.userAnswer = value;
    }

    public String getUserAnswer() {
        return this.userAnswer;
    }

    public void setCreateDate(Date value) {
        this.createDate = value;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ExpertId",getExpertId())
			.append("UserId",getUserId())
			.append("UserName",getUserName())
			.append("UserQuestion",getUserQuestion())
			.append("UserAnswer",getUserAnswer())
			.append("CreateDate",getCreateDate())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ExpertUserQuestion == false) return false;
		if(this == obj) return true;
		ExpertUserQuestion other = (ExpertUserQuestion)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

