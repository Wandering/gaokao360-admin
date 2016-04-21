package cn.thinkjoy.zgk.remote;


import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkHot;

import java.util.List;
import java.util.Map;

/**
 * 高考热点dubbo
 * Created by admin on 2016/1/4.
 */
public interface IGkHotService {

    /**
     * 获取热点摘要列表 四个
     * @return
     */
    BizData4Page getGkHotList(Map<String, Object> conditions,Integer page,Integer rows);

    /**
     * 获取详情
     * @return
     */
    GkHot getGkHotInfo(Map<String, Object> conditions,String id);


}
