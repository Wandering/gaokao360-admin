/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertReservationOrder.java 2016-10-24 09:47:11 $
 */



package cn.thinkjoy.gaokao360.domain;

import cn.thinkjoy.common.domain.CreateBaseDomain;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ExpertReservationOrder extends CreateBaseDomain {
    private String orderNo;
    private String orderStatus;
    private String userId;
    private String expertId;
    private String channel;
    private String serverContent;
    private String serverType;
    private String serverAddress;
    private Double serverPrice;
    private String contactPerson;
    private String contactPhone;
    private String contactQq;
    private String expectBeginDate;
    private String expectEndDate;
    private String questionDesc;
    private String meetingAddress;

	public ExpertReservationOrder(){
	}
    public void setOrderNo(String value) {
        this.orderNo = value;
    }

    public String getOrderNo() {
        return this.orderNo;
    }
    public void setOrderStatus(String value) {
        this.orderStatus = value;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }
    public void setUserId(String value) {
        this.userId = value;
    }

    public String getUserId() {
        return this.userId;
    }
    public void setExpertId(String value) {
        this.expertId = value;
    }

    public String getExpertId() {
        return this.expertId;
    }
    public void setChannel(String value) {
        this.channel = value;
    }

    public String getChannel() {
        return this.channel;
    }
    public void setServerContent(String value) {
        this.serverContent = value;
    }

    public String getServerContent() {
        return this.serverContent;
    }
    public void setServerType(String value) {
        this.serverType = value;
    }

    public String getServerType() {
        return this.serverType;
    }
    public void setServerAddress(String value) {
        this.serverAddress = value;
    }

    public String getServerAddress() {
        return this.serverAddress;
    }
    public void setServerPrice(Double value) {
        this.serverPrice = value;
    }

    public Double getServerPrice() {
        return this.serverPrice;
    }
    public void setContactPerson(String value) {
        this.contactPerson = value;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }
    public void setContactPhone(String value) {
        this.contactPhone = value;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }
    public void setContactQq(String value) {
        this.contactQq = value;
    }

    public String getContactQq() {
        return this.contactQq;
    }
    public void setExpectBeginDate(String value) {
        this.expectBeginDate = value;
    }

    public String getExpectBeginDate() {
        return this.expectBeginDate;
    }
    public void setExpectEndDate(String value) {
        this.expectEndDate = value;
    }

    public String getExpectEndDate() {
        return this.expectEndDate;
    }
    public void setQuestionDesc(String value) {
        this.questionDesc = value;
    }

    public String getQuestionDesc() {
        return this.questionDesc;
    }
    public void setMeetingAddress(String value) {
        this.meetingAddress = value;
    }

    public String getMeetingAddress() {
        return this.meetingAddress;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("OrderNo",getOrderNo())
			.append("OrderStatus",getOrderStatus())
			.append("UserId",getUserId())
			.append("ExpertId",getExpertId())
			.append("Channel",getChannel())
			.append("ServerContent",getServerContent())
			.append("ServerType",getServerType())
			.append("ServerAddress",getServerAddress())
			.append("ServerPrice",getServerPrice())
			.append("ContactPerson",getContactPerson())
			.append("ContactPhone",getContactPhone())
			.append("ContactQq",getContactQq())
			.append("ExpectBeginDate",getExpectBeginDate())
			.append("ExpectEndDate",getExpectEndDate())
			.append("CreateDate",getCreateDate())
			.append("QuestionDesc",getQuestionDesc())
			.append("MeetingAddress",getMeetingAddress())
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof ExpertReservationOrder == false) return false;
		if(this == obj) return true;
		ExpertReservationOrder other = (ExpertReservationOrder)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

