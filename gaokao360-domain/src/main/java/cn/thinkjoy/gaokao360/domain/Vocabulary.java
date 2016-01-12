/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  Vocabulary.java 2015-12-29 16:40:42 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class Vocabulary extends CreateBaseDomain<Long>{
    /** 分类id */
    private Long categoryId;
    /** 标题 */
    private String title;
    /** 摘要 */
    private String summary;
    /** 富文本内容 */
    private String content;
    /** 内容在云盘中的ID */
    private String htmlId;
    /** 排序 */
    private Integer sortId;
    /** 点击量 */
    private Integer hits;
    /** 区域Id */
    private Long areaId;

    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Vocabulary(){
	}
    public void setCategoryId(Long value) {
        this.categoryId = value;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }
    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }
    public void setSummary(String value) {
        this.summary = value;
    }

    public String getSummary() {
        return this.summary;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setHtmlId(String value) {
        this.htmlId = value;
    }

    public String getHtmlId() {
        return this.htmlId;
    }
    public void setSortId(Integer value) {
        this.sortId = value;
    }

    public Integer getSortId() {
        return this.sortId;
    }
    public void setHits(Integer value) {
        this.hits = value;
    }

    public Integer getHits() {
        return this.hits;
    }
    public void setAreaId(Long value) {
        this.areaId = value;
    }

    public Long getAreaId() {
        return this.areaId;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CategoryId",getCategoryId())
			.append("Title",getTitle())
			.append("Summary",getSummary())
			.append("Content",getContent())
			.append("HtmlId",getHtmlId())
			.append("SortId",getSortId())
			.append("Status",getStatus())
			.append("Creator",getCreator())
			.append("CreateDate",getCreateDate())
			.append("LastModifier",getLastModifier())
			.append("LastModDate",getLastModDate())
			.append("Hits",getHits())
			.append("AreaId",getAreaId())
			.append("AreaName",getAreaName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Vocabulary == false) return false;
		if(this == obj) return true;
		Vocabulary other = (Vocabulary)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

