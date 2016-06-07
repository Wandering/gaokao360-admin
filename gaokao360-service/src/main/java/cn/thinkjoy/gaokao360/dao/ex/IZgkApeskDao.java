package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.zgk.common.Criteria;
import cn.thinkjoy.zgk.domain.ZgkApesk;
import cn.thinkjoy.zgk.dto.ZgkApeskDTO;
import cn.thinkjoy.zgk.dto.ZgkApeskModelDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IZgkApeskDao {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ZgkApesk record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ZgkApesk record);

    /**
     * 根据条件查询记录集
     */
    List<ZgkApesk> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    ZgkApesk selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") ZgkApesk record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") ZgkApesk record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ZgkApesk record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ZgkApesk record);

    /**
     * 获取测评结果
     * @param map
     * @return
     */
    List<ZgkApeskDTO> selectUserApeskResult(Map map);
    /**
     * 获取测评结果
     * @param map
     * @return
     */
    ZgkApeskModelDTO selectUserApeskModel(Map map);


    List<ZgkApesk> selectApeskLimit(Map map);
}