package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.gaokao360.domain.ExpertService;

import java.util.List;

/**
 * Created by yangyongping on 2016/10/20.
 */
public interface IExpertServiceExDAO extends IBaseDAO{

    List<ExpertService> findAllExpert();
}
