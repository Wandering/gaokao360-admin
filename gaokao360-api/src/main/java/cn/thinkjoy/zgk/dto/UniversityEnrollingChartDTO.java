package cn.thinkjoy.zgk.dto;

import java.io.Serializable;

/**
 * Created by zuohao on 16/1/18.
 */
public class UniversityEnrollingChartDTO  implements Serializable {
    /** 科类 */
    private String typeId;
    private String typeName;
    /** 批次 */
    private String batch;
    private String batchName;
    /** 年份 */
    private String year;
    /** 最高分 */
    private String highestScore;
    /** 最低分 */
    private String lowestScore;
    /** 计划数 */
    private String planEnrollingNumber;
    /** 实际录取数 */
    private String realEnrollingNumber;
    /** 最高位次 */
    private String highestPrecedence;
    /** 最低位次 */
    private String averageScore;
    /** 平均分 */
    private String averagePrecedence;
    /** 平均位次 */
    private String lowestPrecedence;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(String highestScore) {
        this.highestScore = highestScore;
    }

    public String getLowestScore() {
        return lowestScore;
    }

    public void setLowestScore(String lowestScore) {
        this.lowestScore = lowestScore;
    }

    public String getPlanEnrollingNumber() {
        return planEnrollingNumber;
    }

    public void setPlanEnrollingNumber(String planEnrollingNumber) {
        this.planEnrollingNumber = planEnrollingNumber;
    }

    public String getRealEnrollingNumber() {
        return realEnrollingNumber;
    }

    public void setRealEnrollingNumber(String realEnrollingNumber) {
        this.realEnrollingNumber = realEnrollingNumber;
    }

    public String getHighestPrecedence() {
        return highestPrecedence;
    }

    public void setHighestPrecedence(String highestPrecedence) {
        this.highestPrecedence = highestPrecedence;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public String getAveragePrecedence() {
        return averagePrecedence;
    }

    public void setAveragePrecedence(String averagePrecedence) {
        this.averagePrecedence = averagePrecedence;
    }

    public String getLowestPrecedence() {
        return lowestPrecedence;
    }

    public void setLowestPrecedence(String lowestPrecedence) {
        this.lowestPrecedence = lowestPrecedence;
    }
}
