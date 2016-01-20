package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.dto.GkProfessionDTO;

import java.util.Map;

/**
 * Created by admin on 2016/1/13.
 */
public interface IGkProfessionalService {
    /**
     * 获取职业列表
     * @param conditions
     * @param page
     * @param rows
     * @return
     */
    BizData4Page getProfessionalList(Map<String, Object> conditions, Integer page, Integer rows);


    /**
     * 获取职业详情
     * @param id
     * @return
     */
    GkProfessionDTO getProfessionalInfo(Object id);

    /**
     * 获取职业分类
     * @param conditions
     * @return
     */
    Object getProfessionCategory(Map<String, Object> conditions);



}
