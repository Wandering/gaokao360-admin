package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.ExaminationPaper;

/**
 * Created by admin on 2015/12/7.
 */
public class ExaminationPaperDTO extends ExaminationPaper {
    private String  subjectName;
    private String province;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
