package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.University;

/**
 * Created by admin on 2015/12/18.
 */
public class UniversityDTO extends University {
    private String province;
    private String majoredCount;
    /** 排名 */
    private String sort;
    /** 图片 */
    private String images;
    private String  eduLevelName;
    private String dictName;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    public String getEduLevelName() {
        return eduLevelName;
    }

    public void setEduLevelName(String eduLevelName) {
        this.eduLevelName = eduLevelName;
    }

    public String getMajoredCount() {
        return majoredCount;
    }

    public void setMajoredCount(String majoredCount) {
        this.majoredCount = majoredCount;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
