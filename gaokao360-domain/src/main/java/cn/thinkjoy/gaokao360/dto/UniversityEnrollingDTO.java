package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.gaokao360.domain.UniversityEnrolling;

/**
 * Created by admin on 2015/12/28.
 */
public class UniversityEnrollingDTO extends UniversityEnrolling {
    /** 位置 */
    private String areaid;
    /**未知名称**/
    private String province;
    /**院校名称**/
    private String name;
    /**批次**/
    private String batchname;
    /**专业类别**/
    private String typename;

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
