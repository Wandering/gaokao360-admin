/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  VideoCourse.java 2016-01-13 15:03:07 $
 */





package cn.thinkjoy.gaokao360.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import cn.thinkjoy.common.domain.CreateBaseDomain;

import java.util.*;

public class VideoCourse extends CreateBaseDomain<Long>{
    /** 分类ID */
    private Long classifyId;
    /** 教师ID */
    private Long teacherId;
    /** 科目ID */
    private Long subjectId;
    /** managerId */
    private Long managerId;
    /** 教师名称 */
    private String teacher;
    /** 视频标题 */
    private String title;
    /** 信息内容 */
    private String content;
    /** 信息封面 */
    private String frontCover;
    /** 简介 */
    private String subcontent;
    /** 点击次数 */
    private Long hit;
    /** 年份 */
    private String years;
    /** 备考冲刺分类名称 */
    private String beikaochongciType;
    /** 排序 */
    private Integer courseSort;
    /** 信息封面 */
    private String frontcover1;
    /** 是否可以进入 */
    private Integer isAccept;
    /** 区域Id */
    private Long areaId;
    /** 简介 */
    private String subInfo;

	public VideoCourse(){
	}
    public void setClassifyId(Long value) {
        this.classifyId = value;
    }

    public Long getClassifyId() {
        return this.classifyId;
    }
    public void setTeacherId(Long value) {
        this.teacherId = value;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }
    public void setSubjectId(Long value) {
        this.subjectId = value;
    }

    public Long getSubjectId() {
        return this.subjectId;
    }
    public void setManagerId(Long value) {
        this.managerId = value;
    }

    public Long getManagerId() {
        return this.managerId;
    }
    public void setTeacher(String value) {
        this.teacher = value;
    }

    public String getTeacher() {
        return this.teacher;
    }
    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }
    public void setContent(String value) {
        this.content = value;
    }

    public String getContent() {
        return this.content;
    }
    public void setFrontCover(String value) {
        this.frontCover = value;
    }

    public String getFrontCover() {
        return this.frontCover;
    }
    public void setSubcontent(String value) {
        this.subcontent = value;
    }

    public String getSubcontent() {
        return this.subcontent;
    }
    public void setHit(Long value) {
        this.hit = value;
    }

    public Long getHit() {
        return this.hit;
    }
    public void setYears(String value) {
        this.years = value;
    }

    public String getYears() {
        return this.years;
    }
    public void setBeikaochongciType(String value) {
        this.beikaochongciType = value;
    }

    public String getBeikaochongciType() {
        return this.beikaochongciType;
    }
    public void setCourseSort(Integer value) {
        this.courseSort = value;
    }

    public Integer getCourseSort() {
        return this.courseSort;
    }
    public void setFrontcover1(String value) {
        this.frontcover1 = value;
    }

    public String getFrontcover1() {
        return this.frontcover1;
    }
    public void setIsAccept(Integer value) {
        this.isAccept = value;
    }

    public Integer getIsAccept() {
        return this.isAccept;
    }
    public void setAreaId(Long value) {
        this.areaId = value;
    }

    public Long getAreaId() {
        return this.areaId;
    }
    public void setSubInfo(String value) {
        this.subInfo = value;
    }

    public String getSubInfo() {
        return this.subInfo;
    }

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ClassifyId",getClassifyId())
			.append("TeacherId",getTeacherId())
			.append("SubjectId",getSubjectId())
			.append("ManagerId",getManagerId())
			.append("Teacher",getTeacher())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("FrontCover",getFrontCover())
			.append("Subcontent",getSubcontent())
			.append("Hit",getHit())
			.append("Years",getYears())
			.append("BeikaochongciType",getBeikaochongciType())
			.append("CourseSort",getCourseSort())
			.append("Frontcover1",getFrontcover1())
			.append("IsAccept",getIsAccept())
			.append("CreateDate",getCreateDate())
			.append("Status",getStatus())
			.append("LastModDate",getLastModDate())
			.append("AreaId",getAreaId())
			.append("Creator",getCreator())
			.append("LastModifier",getLastModifier())
			.append("SubInfo",getSubInfo())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof VideoCourse == false) return false;
		if(this == obj) return true;
		VideoCourse other = (VideoCourse)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

