package cn.thinkjoy.zgk.dto;


import java.io.Serializable;

/**
 * Created by admin on 2015/12/18.
 */
public class UniversityDTO implements Serializable{

    private long id;
    /** 学校名称 */
    private String name;
    /** 学校国码 */
    private String code;
    /** 院校省码 */
    private String provinceCode;
    /** 隶属 */
    private String subjection;
    /** 位置 */
    private String areaid;
    /** 院校类型，对应字典表中的UNIVERSITY_TYPE */
    private Integer type;
    /** 院校首页链接 */
    private String url;
    /** 院校logo链接 */
    private String photoUrl;
    /** 学校是否为985，211等 */
    private String property;
    /** 学校排名 */
    private Integer rank;
    /** 学历层次，本科或专科，对应字典表中的EDUCATION_LEVEL */
    private Integer educationLevel;
    /** 是否逻辑删除 */
    private Boolean isDelete;
    /** 学校地址 */
    private String address;
    /** 联系电话 */
    private String contactPhone;
    /** 院校简介 */
    private String universityIntro;
    /** 报考指南 */
    private String entranceIntro;
    /** 特色专业 */
    private String featureMajor;
    /** 类型名称 */
    private String typeName;
    /** 层次名称 */
    private String levelName;

    /** 层次名称 */
    private String province;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getSubjection() {
        return subjection;
    }

    public void setSubjection(String subjection) {
        this.subjection = subjection;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Integer educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getUniversityIntro() {
        return universityIntro;
    }

    public void setUniversityIntro(String universityIntro) {
        this.universityIntro = universityIntro;
    }

    public String getEntranceIntro() {
        return entranceIntro;
    }

    public void setEntranceIntro(String entranceIntro) {
        this.entranceIntro = entranceIntro;
    }

    public String getFeatureMajor() {
        return featureMajor;
    }

    public void setFeatureMajor(String featureMajor) {
        this.featureMajor = featureMajor;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
