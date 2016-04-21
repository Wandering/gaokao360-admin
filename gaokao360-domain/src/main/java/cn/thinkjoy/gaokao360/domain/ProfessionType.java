/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionType.java 2015-12-28 18:05:28 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class ProfessionType extends CreateBaseDomain<Long>{
    /** 职业类型 */
    private String professionType;
    /** 父类ID */
    private Long pid;
    /** 是否删除，0不删除,1删除。 */
    private Boolean isDelete;

	public ProfessionType(){
	}
    public void setProfessionType(String value) {
        this.professionType = value;
    }

    public String getProfessionType() {
        return this.professionType;
    }
    public void setPid(Long value) {
        this.pid = value;
    }

    public Long getPid() {
        return this.pid;
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
			.append("ProfessionType",getProfessionType())
			.append("Pid",getPid())
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
		if(obj instanceof ProfessionType == false) return false;
		if(this == obj) return true;
		ProfessionType other = (ProfessionType)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

