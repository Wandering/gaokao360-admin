package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.UniversityMajorEnrolling;

/**
 * Created by zuohao on 16/1/6.
 */
public class UniversityMajorEnrollingDTO extends UniversityMajorEnrolling {

    private String universityName;

    private String batchName;

    private String admissionBatchName;

    private String majorTypeName;

    private String lengthOfSchoolingName;

    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getAdmissionBatchName() {
        return admissionBatchName;
    }

    public void setAdmissionBatchName(String admissionBatchName) {
        this.admissionBatchName = admissionBatchName;
    }

    public String getMajorTypeName() {
        return majorTypeName;
    }

    public void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }

    public String getLengthOfSchoolingName() {
        return lengthOfSchoolingName;
    }

    public void setLengthOfSchoolingName(String lengthOfSchoolingName) {
        this.lengthOfSchoolingName = lengthOfSchoolingName;
    }
}
