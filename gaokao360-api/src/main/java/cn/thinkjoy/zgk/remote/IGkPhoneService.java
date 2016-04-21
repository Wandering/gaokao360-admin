package cn.thinkjoy.zgk.remote;


import cn.thinkjoy.zgk.domain.BizData4Page;

import java.util.Map;

/**
 * Created by admin on 2016/1/13.
 */
public interface IGkPhoneService {
    /**
     * 获取政策摘要列表
     * @param conditions
     * @param page
     * @param rows
     * @return
     */
    BizData4Page getGkPhoneList(Map<String, Object> conditions, Integer page, Integer rows);

}
