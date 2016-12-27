package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.common.dao.IBaseDAO;
import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.domain.ExpertInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyongping on 2016/10/20.
 */
public interface IExpertAppraiseExDAO extends IBaseDAO {
    /**
     * 获取所有的专家
     * @return
     */
    List<ExpertInfo> getAllExpert();

    /**
     * 查询所有的专家服务
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
     *
     * @param id
     * @return
     */
    int auditPass(Long id);
}
