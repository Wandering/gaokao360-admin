package cn.thinkjoy.zgk.domain;

import java.io.Serializable;
import java.util.Date;

public class ZgkApeskCourse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long acId;

    /**
     * 测试标题
     */
    private String name;

    /**
     * 才储量表标识
     */
    private String liangBiao;

    /**
     * 顺序
     */
    private Integer acOrder;

    /**
     * 图片
     */
    private String picUrl;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 批次
     */
    private String batch;

    /**
     * 创建时间
     */
    private Long createDate;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 报告地址
     */
    private String reportUrl;

    /**
     * 多个年级用,
     */
    private String grade;

    /**
     * 年级描述文本
     */
    private String gradeDescribe;

    /**
     * @return id
     */
    public Long getAcId() {
        return acId;
    }

    /**
     * @param acId
	 *            id
     */
    public void setAcId(Long acId) {
        this.acId = acId;
    }

    /**
     * @return 测试标题
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
	 *            测试标题
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 才储量表标识
     */
    public String getLiangBiao() {
        return liangBiao;
    }

    /**
     * @param liangBiao
	 *            才储量表标识
     */
    public void setLiangBiao(String liangBiao) {
        this.liangBiao = liangBiao;
    }

    /**
     * @return 顺序
     */
    public Integer getAcOrder() {
        return acOrder;
    }

    /**
     * @param acOrder
	 *            顺序
     */
    public void setAcOrder(Integer acOrder) {
        this.acOrder = acOrder;
    }

    /**
     * @return 图片
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * @param picUrl
	 *            图片
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * @return 介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * @param introduce
	 *            介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * @return 批次
     */
    public String getBatch() {
        return batch;
    }

    /**
     * @param batch
	 *            批次
     */
    public void setBatch(String batch) {
        this.batch = batch;
    }

    /**
     * @return 创建时间
     */
    public Long getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
	 *            创建时间
     */
    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
	 *            状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return 报告地址
     */
    public String getReportUrl() {
        return reportUrl;
    }

    /**
     * @param reportUrl
	 *            报告地址
     */
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    /**
     * @return 多个年级用,
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade
	 *            多个年级用,
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return 年级描述文本
     */
    public String getGradeDescribe() {
        return gradeDescribe;
    }

    /**
     * @param gradeDescribe
	 *            年级描述文本
     */
    public void setGradeDescribe(String gradeDescribe) {
        this.gradeDescribe = gradeDescribe;
    }
}
