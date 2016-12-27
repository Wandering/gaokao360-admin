package cn.thinkjoy.gaokao360.service.common.ex.impl;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.dao.ex.IExpertInfoExDAO;
import cn.thinkjoy.gaokao360.dao.ex.IExpertServiceExDAO;
import cn.thinkjoy.gaokao360.service.baseservice.AbstractPageService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertInfoExService;
import cn.thinkjoy.gaokao360.service.common.ex.IExpertServiceExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangyongping on 2016/10/20.
 */
@Service("expertServiceExService")
public class ExpertServiceExServiceImpl extends AbstractPageService implements IExpertServiceExService {
    @Autowired
    private IExpertServiceExDAO expertServiceExDAO;
    @Override
    public IBaseDAO getDao() {
        return expertServiceExDAO;
    }


}
