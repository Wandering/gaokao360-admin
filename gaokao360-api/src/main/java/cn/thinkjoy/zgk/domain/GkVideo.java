package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/12.
 */
public class GkVideo extends BaseDomain {
    /** 教师名称 */
    private String teacher;
    /** 课时排序 */
    private Integer sectionSort;
    /** 文件地址 */
    private String fileUrl;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getSectionSort() {
        return sectionSort;
    }

    public void setSectionSort(Integer sectionSort) {
        this.sectionSort = sectionSort;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
