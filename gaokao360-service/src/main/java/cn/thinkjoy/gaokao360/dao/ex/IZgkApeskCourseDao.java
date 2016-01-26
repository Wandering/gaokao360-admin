package cn.thinkjoy.gaokao360.dao.ex;

import cn.thinkjoy.zgk.common.Criteria;
import cn.thinkjoy.zgk.domain.ZgkApeskCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IZgkApeskCourseDao {
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
    int deleteByPrimaryKey(Integer acId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ZgkApeskCourse record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ZgkApeskCourse record);

    /**
     * 根据条件查询记录集
     */
    List<ZgkApeskCourse> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    ZgkApeskCourse selectByPrimaryKey(Integer acId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") ZgkApeskCourse record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") ZgkApeskCourse record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ZgkApeskCourse record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ZgkApeskCourse record);
}