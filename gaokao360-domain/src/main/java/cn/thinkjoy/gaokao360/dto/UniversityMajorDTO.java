package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.UniversityMajor;

/**
 * Created by zuohao on 15/12/30.
 */
public class UniversityMajorDTO extends UniversityMajor {
    private String educationLevelName;

    public String getEducationLevelName() {
        return educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName) {
        this.educationLevelName = educationLevelName;
    }
}
