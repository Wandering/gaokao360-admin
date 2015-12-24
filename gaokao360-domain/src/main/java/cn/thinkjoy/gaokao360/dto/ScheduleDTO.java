/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  Schedule.java 2015-12-15 19:05:44 $
 */





package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.Schedule;

public class ScheduleDTO extends Schedule {
    private String  province;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}

