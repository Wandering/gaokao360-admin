package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.common.domain.BaseDomain;

/**
 * Created by admin on 2015/12/29.
 */
public class GkQueryDomain extends BaseDomain{
    private String label;
    private String category;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
