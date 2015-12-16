package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.AreabatchLine;

/**
 * Created by admin on 2015/12/16.
 */
public class AreabatchLineDTO extends AreabatchLine {
    private String province;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
