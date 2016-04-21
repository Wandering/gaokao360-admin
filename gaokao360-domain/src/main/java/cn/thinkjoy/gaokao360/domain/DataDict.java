/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  DataDict.java 2015-12-29 10:29:59 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class DataDict extends BaseDomain<Long>{
    /** 名字 */
    private String name;
    /** 序号 */
    private Integer dictId;
    /** 标识 */
    private String type;
    /** 备注 */
    private String note;

	public DataDict(){
	}
    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }
    public void setDictId(Integer value) {
        this.dictId = value;
    }

    public Integer getDictId() {
        return this.dictId;
    }
    public void setType(String value) {
        this.type = value;
    }

    public String getType() {
        return this.type;
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
			.append("Name",getName())
			.append("DictId",getDictId())
			.append("Type",getType())
			.append("Note",getNote())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof DataDict == false) return false;
		if(this == obj) return true;
		DataDict other = (DataDict)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

