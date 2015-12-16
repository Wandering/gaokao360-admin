/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajoredCategory.java 2015-12-16 16:48:40 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class MajoredCategory extends BaseDomain<Long>{
    /** 名称 */
    private String name;
    /** level */
    private Integer level;
    /** 父id */
    private Long parentId;

	public MajoredCategory(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setLevel(Integer value) {
        this.level = value;
    }

    public Integer getLevel() {
        return this.level;
    }
    public void setParentId(Long value) {
        this.parentId = value;
    }

    public Long getParentId() {
        return this.parentId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Level",getLevel())
			.append("ParentId",getParentId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MajoredCategory == false) return false;
		if(this == obj) return true;
		MajoredCategory other = (MajoredCategory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

