package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.Major;

/**
 * Created by admin on 2015/12/29.
 */
public class MajorDTO extends Major {
    /** 专业代码 */
    private String majorCode;
    /** 开设课程 */
    private String offerCourses;
    /** 专业解读 */
    private String majorIntroduce;
    /** 相近专业 */
    private String similarMajors;
    /** 职业方向
     职业方向 */
    private String specialisation;
    /** 优秀学长 */
    private String outstandingMentor;

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getOfferCourses() {
        return offerCourses;
    }

    public void setOfferCourses(String offerCourses) {
        this.offerCourses = offerCourses;
    }

    public String getMajorIntroduce() {
        return majorIntroduce;
    }

    public void setMajorIntroduce(String majorIntroduce) {
        this.majorIntroduce = majorIntroduce;
    }

    public String getSimilarMajors() {
        return similarMajors;
    }

    public void setSimilarMajors(String similarMajors) {
        this.similarMajors = similarMajors;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getOutstandingMentor() {
        return outstandingMentor;
    }

    public void setOutstandingMentor(String outstandingMentor) {
        this.outstandingMentor = outstandingMentor;
    }
}
