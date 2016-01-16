package cn.thinkjoy.zgk.dto;

import cn.thinkjoy.zgk.domain.BaseDomain;
import cn.thinkjoy.zgk.domain.GkVideo;
import cn.thinkjoy.zgk.domain.GkVideoInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/1/12.
 */
public class GkVideoDTO implements Serializable {
    private Object id;
    /**视频介绍信息**/
    private GkVideoInfo gkVideoInfo;
    /**视频列表**/
    private List<GkVideo> gkVideos;

    public GkVideoInfo getGkVideoInfo() {
        return gkVideoInfo;
    }

    public void setGkVideoInfo(GkVideoInfo gkVideoInfo) {
        this.gkVideoInfo = gkVideoInfo;
    }

    public List<GkVideo> getGkVideos() {
        return gkVideos;
    }

    public void setGkVideos(List<GkVideo> gkVideos) {
        this.gkVideos = gkVideos;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
