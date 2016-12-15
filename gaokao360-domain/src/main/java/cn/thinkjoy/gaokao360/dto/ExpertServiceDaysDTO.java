/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertServiceDays.java 2016-11-02 11:26:27 $
 */



package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.gaokao360.domain.ExpertServiceDays;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

public class ExpertServiceDaysDTO extends ExpertServiceDays {
    private String expertName;
	private String timeSegment;
	private String timeIsAvailable;

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}

	public String getTimeSegment() {
		return timeSegment;
	}

	public void setTimeSegment(String timeSegment) {
		this.timeSegment = timeSegment;
	}

	public String getTimeIsAvailable() {
		return timeIsAvailable;
	}

	public void setTimeIsAvailable(String timeIsAvailable) {
		this.timeIsAvailable = timeIsAvailable;
	}

}

