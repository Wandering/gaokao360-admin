package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkHot;

import java.util.Map;

/**
 * 高考热点dubbo
 * Created by admin on 2016/1/4.
 */
public interface IGkAdmissionLineService {
    /**
     * 获取热点摘要列表 四个
     * @return
     */
    BizData4Page getGkAdmissionLineList(Map<String, Object> conditions, Integer page, Integer rows);

}
