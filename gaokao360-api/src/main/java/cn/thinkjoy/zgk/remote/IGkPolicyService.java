package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.zgk.domain.GkHot;
import cn.thinkjoy.zgk.domain.GkPolicy;

import java.util.List;
import java.util.Map;

/**
 * 政策解读dubbo
 * Created by admin on 2016/1/4.
 */
public interface IGkPolicyService {

    /**
     * 获取政策摘要列表
     * @param conditions
     * @param page
     * @param rows
     * @return
     */
    BizData4Page getGkPolicyList(Map<String, Object> conditions,Integer page,Integer rows);

    /**
     * 获取政策解读详情
     * @param id
     * @return
     */
    GkPolicy getGkPolicyInfo(String id);
}
