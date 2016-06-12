package cn.thinkjoy.zgk.remote;


import cn.thinkjoy.zgk.common.Criteria;
import cn.thinkjoy.zgk.domain.ZgkApesk;
import cn.thinkjoy.zgk.dto.ZgkApeskDTO;

import java.util.List;
import java.util.Map;

/**
 * zgk_apesk
 * @version 1.0 2015-09-28
 * @powerby hetgyd 
 */
public interface IZgkApeskService {
    List<ZgkApesk> query(Long userId, Integer acId, String liangbiao, String testEmail);

    int countByExample(Criteria example);

    ZgkApesk selectByPrimaryKey(Integer id);

    List<ZgkApesk> selectByExample(Criteria example);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZgkApesk record);

    int updateByPrimaryKey(ZgkApesk record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ZgkApesk record, Criteria example);

    int updateByExample(ZgkApesk record, Criteria example);

    int insert(ZgkApesk record);

    int insertSelective(ZgkApesk record);
    List<ZgkApeskDTO> selectUserApeskResult(Map map);

    public List<ZgkApesk> selectApeskCallBack(Map map);
    public List<ZgkApesk> selectApeskLimit(Map map);

}