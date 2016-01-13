package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.domain.GkSchedule;
import cn.thinkjoy.zgk.dto.GkScheduleDTO;

import java.util.List;

/**
 *  高考日程dubbo
 * Created by admin on 2016/1/4.
 */
public interface IGkScheduleService {

    /**
     * 获取热点摘要列表 四个
     * @return
     */
    List<GkScheduleDTO> getScheduleList(Integer num);

    /**
     * 获取详情
     * @return
     */
    GkSchedule getScheduleInfo(String id);
}
