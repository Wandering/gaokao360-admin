/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoClassify.java 2015-12-04 14:49:02 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class VideoClassify extends CreateBaseDomain<Long>{
    /** 分类名称 */
    private String classifyName;

	public VideoClassify(){
	}
    public void setClassifyName(String value) {
        this.classifyName = value;
    }

    public String getClassifyName() {
        return this.classifyName;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ClassifyName",getClassifyName())
			.append("Status",getStatus())
			.append("CreateDate",getCreateDate())
			.append("LastModDate",getLastModDate())
			.append("Creator",getCreator())
			.append("LastModifier",getLastModifier())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VideoClassify == false) return false;
		if(this == obj) return true;
		VideoClassify other = (VideoClassify)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

