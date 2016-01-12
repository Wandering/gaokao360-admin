package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.domain.GkHot;

import java.util.List;
import java.util.Map;

/**
 * 政策解读dubbo
 * Created by admin on 2016/1/4.
 */
public interface IGkPolicyService {

    /**
     * 获取政策摘要列表
     * @param map
     * @param num
     * @return
     */
    List<GkHot> getGkPolicyList(Integer num,Map<String,Object> map);

    /**
     * 获取政策解读详情
     * @param id
     * @return
     */
    GkHot getGkPolicyInfo(String id);
}
