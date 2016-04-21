package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/12.
 */
public class GkVideo extends BaseDomain {
    /** 课时排序 */
    private Integer sectionSort;
    /** 名称 */
    private String name;
    /** 文件地址 */
    private String fileUrl;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
