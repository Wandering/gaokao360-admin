package cn.thinkjoy.zgk.domain;

import java.io.Serializable;
import java.util.Date;

public class ZgkApesk implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * ac_id
     */
    private Long acId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 才储量表名
     */
    private String liangBiao;

    /**
     * 才储用户邮箱:apesk_userId或用户手机号码
     */
    private String testEmail;

    /**
     * 报告id
     */
    private Integer reportId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 报告时间
     */
    private Date reportDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return ac_id
     */
    public Long getAcId() {
        return acId;
    }

    /**
     * @param acId 
	 *            ac_id
     */
    public void setAcId(Long acId) {
        this.acId = acId;
    }

    /**
     * @return 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return 才储量表名
     */
    public String getLiangBiao() {
        return liangBiao;
    }

    /**
     * @param liangBiao 
	 *            才储量表名
     */
    public void setLiangBiao(String liangBiao) {
        this.liangBiao = liangBiao;
    }

    /**
     * @return 才储用户邮箱:apesk_userId或用户手机号码
     */
    public String getTestEmail() {
        return testEmail;
    }

    /**
     * @param testEmail 
	 *            才储用户邮箱:apesk_userId或用户手机号码
     */
    public void setTestEmail(String testEmail) {
        this.testEmail = testEmail;
    }

    /**
     * @return 报告id
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * @param reportId 
	 *            报告id
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * @return 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate 
	 *            创建时间
     */
    public void setCreateDate(Date createDate) {
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
     * @return 报告时间
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate 
	 *            报告时间
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}