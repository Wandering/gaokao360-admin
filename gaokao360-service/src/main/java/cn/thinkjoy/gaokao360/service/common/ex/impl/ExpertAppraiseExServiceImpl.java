package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.dao.ex.IExpertAppraiseExDAO;
import cn.thinkjoy.gaokao360.domain.ExpertInfo;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertAppraiseExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyongping on 2016/10/20.
 */
@Service("expertAppraiseExServiceImpl")
public class ExpertAppraiseExServiceImpl extends AbstractPageService implements IExpertAppraiseExService{

    @Autowired
    private IExpertAppraiseExDAO expertAppraiseExDAO;

    @Override
    public IBaseDAO getDao() {
        return expertAppraiseExDAO;
    }

    /**
     * 查询所有专家
     *
     * @return
     */
    @Override
    public List<ExpertInfo> getAllExpert() {
        return expertAppraiseExDAO.getAllExpert();
    }

    /**
     * 查询该专家所有服务
     *
     * @param id
     * @return
     */
    @Override
    public List<Map<String,Object>> getServiceTypeByExpert(Long id) {
        return expertAppraiseExDAO.getServiceTypeByExpert(id);
    }

    /**
     * 审核通过
     *
     * @param id
     * @return
     */
    @Override
    public boolean auditPass(Long id) {
        return expertAppraiseExDAO.auditPass(id)>0;
    }
}
