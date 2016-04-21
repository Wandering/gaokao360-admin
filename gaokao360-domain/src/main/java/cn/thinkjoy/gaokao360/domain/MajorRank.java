/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  MajorRank.java 2015-12-29 16:07:19 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class MajorRank extends CreateBaseDomain<Long>{
    /** 专业ID */
    private Long majorId;
    /** 学校ID */
    private Long universityId;
    /** 学校专业排名 */
    private Integer rank;
    /**  */
    private Boolean isDelete;

	public MajorRank(){
	}
    public void setMajorId(Long value) {
        this.majorId = value;
    }

    public Long getMajorId() {
        return this.majorId;
    }
    public void setUniversityId(Long value) {
        this.universityId = value;
    }

    public Long getUniversityId() {
        return this.universityId;
    }
    public void setRank(Integer value) {
        this.rank = value;
    }

    public Integer getRank() {
        return this.rank;
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
			.append("MajorId",getMajorId())
			.append("UniversityId",getUniversityId())
			.append("Rank",getRank())
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
		if(obj instanceof MajorRank == false) return false;
		if(this == obj) return true;
		MajorRank other = (MajorRank)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

