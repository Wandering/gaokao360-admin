package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.dao.ex.IExpertInfoExDAO;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertAppraiseExService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertInfoExService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yangyongping on 2016/10/20.
 */
public class ExpertInfoExServiceImpl extends AbstractPageService implements IExpertInfoExService {
    @Autowired
    private IExpertInfoExDAO expertInfoExDAO;
    @Override
    public IBaseDAO getDao() {
        return expertInfoExDAO;
    }
}
