package cn.thinkjoy.zgk.remote;


import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkAreaBatch;

import java.util.Map;

/**
 * 地区批次线dubbo
 * Created by admin on 2016/1/4.
 */
public interface IGkAreaBatchService {

    /**
     * 获取列表 四个
     * @return
     */
    BizData4Page getGkAreaBatchList(Map<String, Object> conditions, Integer page, Integer rows);

    /**
     * 获取详情
     * @return
     */
    GkAreaBatch getGkAreaBatchInfo(Map<String, Object> conditions,Object areaId);


}
