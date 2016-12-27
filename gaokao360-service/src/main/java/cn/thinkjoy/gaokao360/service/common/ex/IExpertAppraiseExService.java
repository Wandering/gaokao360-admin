/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExpertAppraiseService.java 2016-10-19 15:14:27 $
 */

package cn.thinkjoy.gaokao360.service.common.ex;
import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.BaseDomain;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.service.IPageService;
import cn.thinkjoy.gaokao360.domain.ExpertInfo;

import java.util.List;
import java.util.Map;

public interface IExpertAppraiseExService<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T>,IPageService<D, T>{

    /**
     * 查询所有专家
     * @return
     */
    List<ExpertInfo> getAllExpert();

    /**
     * 查询该专家所有服务
     * @param id
     * @return
     */
    List<Map<String,Object>> getServiceTypeByExpert(Long id);

    /**
     * 查询所有专家服务
     *
     * @return
     */
    List<Map<String,Object>> queryAllExpertService();

    /**
     * 审核通过
     * @param id
     * @return
     */
    boolean auditPass(Long id);
}
