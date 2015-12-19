package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.common.domain.BaseDomain;

/**
 * Created by admin on 2015/12/18.
 */
public class UniversityMajoredDTO extends BaseDomain{
    /**
     * 名字
     */
    private String name;
    /**科目类别**/
    private String majoredType;
    /**学位**/
    private String degree;
    /**教学时间**/
    private String schoolLength;
    /**教学费用**/
    private String feeStandard;
    /**院校ID**/
    private String universityId;
    /**院校名称**/
    private String universityName;
    /**学历**/
    private String batch;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajoredType() {
        return majoredType;
    }

    public void setMajoredType(String majoredType) {
        this.majoredType = majoredType;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSchoolLength() {
        return schoolLength;
    }

    public void setSchoolLength(String schoolLength) {
        this.schoolLength = schoolLength;
    }

    public String getFeeStandard() {
        return feeStandard;
    }

    public void setFeeStandard(String feeStandard) {
        this.feeStandard = feeStandard;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
