package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/12.
 */
public class GkVideoInfo extends BaseDomain {
    /** 教师名称 */
    private String teacher;
    /** 视频标题 */
    private String title;
    /** 信息封面 */
    private String frontCover;
    /** 专家简介 */
    private String subcontent;
    /** 视频简介 */
    private String content;
    /** 视频数量 */
    private Integer videoCount;
    /** 模块名称 */
    private String modalName;
    /** 点播数 */
    private Long hit;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrontCover() {
        return frontCover;
    }

    public void setFrontCover(String frontCover) {
        this.frontCover = frontCover;
    }


    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public String getModalName() {
        return modalName;
    }

    public void setModalName(String modalName) {
        this.modalName = modalName;
    }

    public String getSubcontent() {
        return subcontent;
    }

    public void setSubcontent(String subcontent) {
        this.subcontent = subcontent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getHit() {
        return hit;
    }

    public void setHit(Long hit) {
        this.hit = hit;
    }
}
