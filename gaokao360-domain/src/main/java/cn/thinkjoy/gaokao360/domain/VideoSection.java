/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoSection.java 2015-12-04 14:05:52 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class VideoSection extends CreateBaseDomain<Long>{
    /**  课程ID */
    private Long courseId;
    /** 课时名称 */
    private String sectionName;
    /** 课时排序 */
    private Integer sectionSort;
    /** 是否可以进入 */
    private Integer isAccept;
    /** 文件地址 */
    private String fileUrl;

	public VideoSection(){
	}
    public void setCourseId(Long value) {
        this.courseId = value;
    }

    public Long getCourseId() {
        return this.courseId;
    }
    public void setSectionName(String value) {
        this.sectionName = value;
    }

    public String getSectionName() {
        return this.sectionName;
    }
    public void setSectionSort(Integer value) {
        this.sectionSort = value;
    }

    public Integer getSectionSort() {
        return this.sectionSort;
    }
    public void setIsAccept(Integer value) {
        this.isAccept = value;
    }

    public Integer getIsAccept() {
        return this.isAccept;
    }
    public void setFileUrl(String value) {
        this.fileUrl = value;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CourseId",getCourseId())
			.append("SectionName",getSectionName())
			.append("SectionSort",getSectionSort())
			.append("IsAccept",getIsAccept())
			.append("FileUrl",getFileUrl())
			.append("CreateDate",getCreateDate())
			.append("Status",getStatus())
			.append("Creator",getCreator())
			.append("LastModDate",getLastModDate())
			.append("LastModifier",getLastModifier())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VideoSection == false) return false;
		if(this == obj) return true;
		VideoSection other = (VideoSection)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

