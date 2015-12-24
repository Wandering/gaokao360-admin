package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.common.domain.BaseDomain;

/**
 * Created by admin on 2015/12/24.
 */
public class VideoSectionDTO extends BaseDomain{
    private String sectionName;
    private String sectionSort;
    private String fileUrl;


    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionSort() {
        return sectionSort;
    }

    public void setSectionSort(String sectionSort) {
        this.sectionSort = sectionSort;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
