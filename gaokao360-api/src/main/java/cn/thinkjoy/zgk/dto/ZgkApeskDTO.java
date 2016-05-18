package cn.thinkjoy.zgk.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by douzy on 16/5/5.
 */
public class ZgkApeskDTO implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 报告ID
     */
    private Integer reportId;

    /**
     * 测评时间
     */
    private Date createDate;

    /**
     * 生成测评结果时间
     */
    private Date reportDate;

    /**
     * 测评名称
     */
    private String name;

    /**
     * 结果URL
     */
    private String reportUrl;

    /**
     * 测评适用描述
     */
    private String gradeDescribe;

    /**
     *描述
     */
    private String introduce;

    /**
     * 图片URL
     */
    private String picUrl;

    /**
     * 测评类型
     */
    private Long type;

    /**
     * 测评次数
     */
    private Integer num;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getGradeDescribe() {
        return gradeDescribe;
    }

    public void setGradeDescribe(String gradeDescribe) {
        this.gradeDescribe = gradeDescribe;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
