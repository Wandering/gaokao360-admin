package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.domain.GkHot;

import java.util.List;

/**
 * Created by admin on 2016/1/4.
 */
public interface IGKScheduleService {

    /**
     * 获取热点摘要列表 四个
     * @return
     */
    List<GkHot> getScheduleList(String areaId,Integer num);

    /**
     * 获取详情
     * @return
     */
    GkHot getGkHotInfo(String id);

//    /**
//     * 获取热点摘要列表 四个
//     * @return
//     */
//    List<GkHot> getGkHotList();

}
