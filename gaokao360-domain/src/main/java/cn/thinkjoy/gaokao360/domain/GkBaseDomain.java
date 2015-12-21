package cn.thinkjoy.gaokao360.domain;

import cn.thinkjoy.common.domain.BaseDomain;

/**
 * Created by admin on 2015/12/19.
 */
public class GkBaseDomain extends BaseDomain{
    private String name;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
