/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversityDict.java 2015-12-16 18:39:47 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class UniversityDict extends CreateBaseDomain<Long>{
    /** name */
    private String name;
    /** 类型 */
    private String type;
    /** 编码 */
    private Integer dictId;
    /** 备注 */
    private String note;

	public UniversityDict(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setType(String value) {
        this.type = value;
    }

    public String getType() {
        return this.type;
    }
    public void setDictId(Integer value) {
        this.dictId = value;
    }

    public Integer getDictId() {
        return this.dictId;
    }
    public void setNote(String value) {
        this.note = value;
    }

    public String getNote() {
        return this.note;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CreateDate",getCreateDate())
			.append("Status",getStatus())
			.append("Creator",getCreator())
			.append("LastModDate",getLastModDate())
			.append("LastModifier",getLastModifier())
			.append("Name",getName())
			.append("Type",getType())
			.append("DictId",getDictId())
			.append("Note",getNote())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UniversityDict == false) return false;
		if(this == obj) return true;
		UniversityDict other = (UniversityDict)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

