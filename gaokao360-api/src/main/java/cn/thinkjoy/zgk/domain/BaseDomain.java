package cn.thinkjoy.zgk.domain;

import java.io.Serializable;

/**
 * Created by admin on 2016/1/4.
 */
public class BaseDomain<T> implements Serializable{
    private T id;

    private Long lastModDate;
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public Long getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Long lastModDate) {
        this.lastModDate = lastModDate;
    }
}
