package cn.thinkjoy.zgk.dto;

import java.io.Serializable;

/**
 * Created by zuohao on 16/1/19.
 */
public class MajoredCategoryRemoteDTO implements Serializable {
    private long id;
    private String name;
    private int level;
    private int parentId;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
