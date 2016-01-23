package cn.thinkjoy.zgk.dto;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zuohao on 16/1/19.
 */
public class MajoredCategoryRemoteDTO implements Serializable {
    private long id;
    private String name;
    private List<MajoredCategoryRemoteDTO> childList = Lists.newArrayList();
    private Integer childNumber;
    private Integer majoredNumber;

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

    public List<MajoredCategoryRemoteDTO> getChildList() {
        return childList;
    }

    public void setChildList(List<MajoredCategoryRemoteDTO> childList) {
        this.childList = childList;
    }

    public Integer getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
    }

    public Integer getMajoredNumber() {
        return majoredNumber;
    }

    public void setMajoredNumber(Integer majoredNumber) {
        this.majoredNumber = majoredNumber;
    }
}
