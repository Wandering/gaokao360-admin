package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/20.
 */
public class GkProfessional extends BaseDomain{
    /** 职业名称 */
    private String professionName;
    /** 职业简介 */
    private String professionDescription;
    /** 热门度 */
    private Integer hotDegree;
    /** 热门度 */
    private Integer salaryRank;
    /** 职业简介截取 */
    private String professionShort;

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getProfessionDescription() {
        return professionDescription;
    }

    public void setProfessionDescription(String professionDescription) {
        this.professionDescription = professionDescription;
    }

    public Integer getHotDegree() {
        return hotDegree;
    }

    public void setHotDegree(Integer hotDegree) {
        this.hotDegree = hotDegree;
    }

    public Integer getSalaryRank() {
        return salaryRank;
    }

    public void setSalaryRank(Integer salaryRank) {
        this.salaryRank = salaryRank;
    }

    public String getProfessionShort() {
        return professionShort;
    }

    public void setProfessionShort(String professionShort) {
        this.professionShort = professionShort;
    }
}
