package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkEntry;

import java.util.Map;

/**
 * 高考热点dubbo
 * Created by admin on 2016/1/4.
 */
public interface IGkEntryService {
    /**
     * 获取热点摘要列表 四个
     * @return
     */
    BizData4Page getGkEntryList(Map<String, Object> conditions, Integer page, Integer rows);

    /**
     * 获取热点摘要列表 四个
     * @return
     */
    GkEntry getGkEntryInfo(Map<String, Object> conditions,Object id);
}
