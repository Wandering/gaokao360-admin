package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.dao.ex.IZgkApeskCourseDao;
import cn.thinkjoy.zgk.common.Criteria;
import cn.thinkjoy.zgk.common.StringUtil;
import cn.thinkjoy.zgk.domain.ZgkApeskCourse;
import cn.thinkjoy.zgk.remote.IZgkApeskCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * zgk_apesk_course
 * @version 1.0 2015-10-31
 * @powerby hetgyd
 */
@Service("ZgkApeskCourseServiceImpl")
public class ZgkApeskCourseServiceImpl implements IZgkApeskCourseService {
    @Autowired
    private IZgkApeskCourseDao zgkApeskCourseDao;

    public List<ZgkApeskCourse> query(Integer state, String batchs, int start, int size){
    	Criteria example=new Criteria();
    	if(state>-1){
    		example.put("state", state);
    	}
    	if(!StringUtil.isNulOrBlank(batchs)){
    		example.put("batch_in", batchs.split(","));
    	}
    	if(start>-1){
    		example.setMysqlOffset(start);
    	}
    	if(size>-1){
    		example.setMysqlLength(size);
    	}
    	example.setOrderByClause("ac_order");
    	return selectByExample(example);
    }

    /**通过量表查询
     * @param liangBiao
     * @return
     */
    public ZgkApeskCourse queryByLiangBiao(String liangBiao){
    	Criteria example=new Criteria();
    	if(!StringUtil.isNulOrBlank(liangBiao)){
    		example.put("liangBiao",liangBiao);
    		example.setOrderByClause("ac_order");
        	List<ZgkApeskCourse> list= selectByExample(example);
        	if(list!=null&&list.size()>0){
        		return list.get(0);
        	}
    	}


    	return null;
    }
    public int countByExample(Criteria example) {
        int count = this.zgkApeskCourseDao.countByExample(example);
        return count;
    }

    public ZgkApeskCourse selectByPrimaryKey(Integer acId) {
        return this.zgkApeskCourseDao.selectByPrimaryKey(acId);
    }

    public List<ZgkApeskCourse> selectByExample(Criteria example) {
        return this.zgkApeskCourseDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer acId) {
        return this.zgkApeskCourseDao.deleteByPrimaryKey(acId);
    }

    public int updateByPrimaryKeySelective(ZgkApeskCourse record) {
        return this.zgkApeskCourseDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ZgkApeskCourse record) {
        return this.zgkApeskCourseDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.zgkApeskCourseDao.deleteByExample(example);
    }

    public int updateByExampleSelective(ZgkApeskCourse record, Criteria example) {
        return this.zgkApeskCourseDao.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(ZgkApeskCourse record, Criteria example) {
        return this.zgkApeskCourseDao.updateByExample(record, example.getCondition());
    }

    public int insert(ZgkApeskCourse record) {
        return this.zgkApeskCourseDao.insert(record);
    }

    public ZgkApeskCourse insertSelective(ZgkApeskCourse record) {

        this.zgkApeskCourseDao.insertSelective(record);
        return record;
    }
}
