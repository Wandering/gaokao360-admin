package cn.thinkjoy.zgk.remote;



import cn.thinkjoy.zgk.common.Criteria;
import cn.thinkjoy.zgk.domain.ZgkApeskCourse;

import java.util.List;

/**
 * zgk_apesk_course
 * @version 1.0 2015-10-31
 * @powerby hetgyd 
 */
public interface IZgkApeskCourseService {
    int countByExample(Criteria example);

    ZgkApeskCourse selectByPrimaryKey(Integer acId);

    List<ZgkApeskCourse> selectByExample(Criteria example);

    int deleteByPrimaryKey(Integer acId);

    int updateByPrimaryKeySelective(ZgkApeskCourse record);

    int updateByPrimaryKey(ZgkApeskCourse record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(ZgkApeskCourse record, Criteria example);

    int updateByExample(ZgkApeskCourse record, Criteria example);

    int insert(ZgkApeskCourse record);

    int insertSelective(ZgkApeskCourse record);

    ZgkApeskCourse queryByLiangBiao(String liangBiao);

    List<ZgkApeskCourse> query(Integer state, String batchs, int start, int size);
}