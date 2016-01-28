package cn.thinkjoy.zgk.cloudstack;

import java.util.List;

/**
 * Created by admin on 2016/1/27.
 */
public interface ISimpleCloud {
    /**
     * 获取云端当前客户端product
     * @return
     */
    String getCloudProduct();

    /**
     * 获取rpc接口传来的区域
     * @return
     */
    String getCloudArea();

    /**
     * 当前切入点是否在白名单中
     * @param clzName
     * @return
     */
    boolean hasWhiteList(String clzName);

    void setArea(String area);
}
