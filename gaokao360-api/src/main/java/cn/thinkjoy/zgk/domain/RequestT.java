package cn.thinkjoy.zgk.domain;

import java.io.Serializable;

/**
 * Created by admin on 2016/1/26.
 */
public class RequestT<T> implements Serializable {
    private T obj;
    private String area;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
