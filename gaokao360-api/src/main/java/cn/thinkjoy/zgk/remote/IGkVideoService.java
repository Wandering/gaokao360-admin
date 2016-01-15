package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.zgk.domain.GkVideo;
import cn.thinkjoy.zgk.domain.GkVideoInfo;
import cn.thinkjoy.zgk.dto.GkVideoDTO;

import java.util.Map;

/**
 * Created by admin on 2016/1/13.
 */
public interface IGkVideoService {
    /**
     * 获取政策摘要列表
     * @param conditions
     * @param page
     * @param rows
     * @return
     */
    BizData4Page getGkVideoList(Map<String, Object> conditions,Integer page,Integer rows);

    /**
     * 获取政策解读详情
     * @param id
     * @return
     */
    GkVideoDTO getGkVideoInfo(String id);


    /**
     * 点击量自增
     * @param id
     */
    public void hitInc(Object id);

}
