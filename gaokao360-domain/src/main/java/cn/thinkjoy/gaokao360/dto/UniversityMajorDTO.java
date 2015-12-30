package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.UniversityMajor;

/**
 * Created by zuohao on 15/12/30.
 */
public class UniversityMajorDTO extends UniversityMajor {
    private String majorTypeName;
    private String majorSubjectName;
    private String educationLevelName;
    private String gainDegreeName;

    public String getMajorTypeName() {
        return majorTypeName;
    }

    public void setMajorTypeName(String majorTypeName) {
        this.majorTypeName = majorTypeName;
    }

    public String getMajorSubjectName() {
        return majorSubjectName;
    }

    public void setMajorSubjectName(String majorSubjectName) {
        this.majorSubjectName = majorSubjectName;
    }

    public String getEducationLevelName() {
        return educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName) {
        this.educationLevelName = educationLevelName;
    }

    public String getGainDegreeName() {
        return gainDegreeName;
    }

    public void setGainDegreeName(String gainDegreeName) {
        this.gainDegreeName = gainDegreeName;
    }
}
