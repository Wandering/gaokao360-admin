package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.domain.GkSchedule;
import cn.thinkjoy.zgk.dto.GkScheduleDTO;

import java.util.List;

/**
 * Created by admin on 2016/1/4.
 */
public interface IGkScheduleService {

    /**
     * 获取热点摘要列表 四个
     * @return
     */
    List<GkScheduleDTO> getScheduleList(String areaId, Integer num);

    /**
     * 获取详情
     * @return
     */
    GkSchedule getScheduleInfo(String id);

//    /**
//     * 获取热点摘要列表 四个
//     * @return
//     */
//    List<GkHot> getGkHotList();

}
