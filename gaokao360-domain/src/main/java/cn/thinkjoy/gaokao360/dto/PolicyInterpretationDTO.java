package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.PolicyInterpretation;

/**
 * Created by admin on 2015/12/7.
 */
public class PolicyInterpretationDTO extends PolicyInterpretation {
    private String province;
    private String admissionBatch;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAdmissionBatch() {
        return admissionBatch;
    }

    public void setAdmissionBatch(String admissionBatch) {
        this.admissionBatch = admissionBatch;
    }
}
