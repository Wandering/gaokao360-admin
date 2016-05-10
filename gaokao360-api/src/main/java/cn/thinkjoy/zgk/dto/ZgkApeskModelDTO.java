package cn.thinkjoy.zgk.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by douzy on 16/5/10.
 */
public class ZgkApeskModelDTO implements Serializable {
    private Integer userId;
    private Integer reportId;
    private Date createDate;
    private Date reportDate;
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


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
