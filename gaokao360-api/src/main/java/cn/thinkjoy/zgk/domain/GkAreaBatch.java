package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/21.
 */
public class GkAreaBatch extends BaseDomain {
    /** 区域 */
    private Long areaId;
    /** 内容 */
    private String content;

    /**省份**/
    private String province;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
