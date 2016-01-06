package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.ProfessionType;

import java.util.List;

/**
 * Created by admin on 2016/1/5.
 */
public class ProfessionTypeDTO extends ProfessionType {
    List<ProfessionType> childs;

    public List<ProfessionType> getChilds() {
        return childs;
    }

    public void setChilds(List<ProfessionType> childs) {
        this.childs = childs;
    }
}
