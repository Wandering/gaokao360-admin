package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/20.
 */
public class GkProfessionDetail extends BaseDomain {
    /** 相关专业 */
    private String relateMajor;
    /** 职业介绍 */
    private String introduction;
    /** 工作内容 */
    private String workContent;
    /** 从业要求 */
    private String vocationalDemand;
    /** 就业前景 */
    private String careerProspects;

    public String getRelateMajor() {
        return relateMajor;
    }

    public void setRelateMajor(String relateMajor) {
        this.relateMajor = relateMajor;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getVocationalDemand() {
        return vocationalDemand;
    }

    public void setVocationalDemand(String vocationalDemand) {
        this.vocationalDemand = vocationalDemand;
    }

    public String getCareerProspects() {
        return careerProspects;
    }

    public void setCareerProspects(String careerProspects) {
        this.careerProspects = careerProspects;
    }
}
