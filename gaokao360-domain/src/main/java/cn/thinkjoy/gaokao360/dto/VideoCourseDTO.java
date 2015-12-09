package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.VideoCourse;

/**
 * Created by admin on 2015/12/9.
 */
public class VideoCourseDTO extends VideoCourse {
    private String classifyName;
    private String subjectName;
    private String province;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

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
