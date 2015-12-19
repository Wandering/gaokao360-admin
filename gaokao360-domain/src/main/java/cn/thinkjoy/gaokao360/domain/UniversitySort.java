/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  UniversitySort.java 2015-12-18 11:11:01 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.BaseDomain;

import java.util.*;

public class UniversitySort extends BaseDomain<Long>{
    /** 院校ID */
    private Long universityId;
    /** 专业数 */
    private String majoredCount;
    /** 排名 */
    private String sort;
    /** 图片 */
    private String images;

	public UniversitySort(){
	}
    public void setUniversityId(Long value) {
        this.universityId = value;
    }

    public Long getUniversityId() {
        return this.universityId;
    }
    public void setMajoredCount(String value) {
        this.majoredCount = value;
    }

    public String getMajoredCount() {
        return this.majoredCount;
    }
    public void setSort(String value) {
        this.sort = value;
    }

    public String getSort() {
        return this.sort;
    }
    public void setImages(String value) {
        this.images = value;
    }

    public String getImages() {
        return this.images;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UniversityId",getUniversityId())
			.append("MajoredCount",getMajoredCount())
			.append("Sort",getSort())
			.append("Images",getImages())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UniversitySort == false) return false;
		if(this == obj) return true;
		UniversitySort other = (UniversitySort)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

