package cn.thinkjoy.gaokao360.remote.service.impl;


import cn.thinkjoy.gaokao360.dao.ex.IZgkApeskDao;
import cn.thinkjoy.zgk.common.Criteria;
import cn.thinkjoy.zgk.common.StringUtil;
import cn.thinkjoy.zgk.domain.ZgkApesk;
import cn.thinkjoy.zgk.remote.IZgkApeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * zgk_apesk
 * @version 1.0 2015-09-28
 * @powerby hetgyd 
 */
@Service("ZgkApeskServiceImpl")
public class ZgkApeskServiceImpl implements IZgkApeskService {
    @Autowired
    private IZgkApeskDao zgkApeskDao;

    public List<ZgkApesk> query(Long userId, Integer acId, String liangbiao, String testEmail){
    	Criteria example =new Criteria();
    	if(userId>-1){
    		example.put("userId", userId);
    	}
    	if(acId>-1){
    		example.put("acId", acId);
    	}
    	if(!StringUtil.isNulOrBlank(liangbiao)){
    		example.put("liangBiao", liangbiao);
    	}
    	if(!StringUtil.isNulOrBlank(testEmail)){
    		example.put("testEmail", testEmail);
    	}
    	return selectByExample(example);
    }
    public int countByExample(Criteria example) {
        int count = this.zgkApeskDao.countByExample(example);
        return count;
    }

    public ZgkApesk selectByPrimaryKey(Integer id) {
        return this.zgkApeskDao.selectByPrimaryKey(id);
    }

    public List<ZgkApesk> selectByExample(Criteria example) {
        return this.zgkApeskDao.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.zgkApeskDao.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ZgkApesk record) {
        return this.zgkApeskDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ZgkApesk record) {
        return this.zgkApeskDao.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.zgkApeskDao.deleteByExample(example);
    }

    public int updateByExampleSelective(ZgkApesk record, Criteria example) {
        return this.zgkApeskDao.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(ZgkApesk record, Criteria example) {
        return this.zgkApeskDao.updateByExample(record, example.getCondition());
    }

    public int insert(ZgkApesk record) {
        return this.zgkApeskDao.insert(record);
    }

    public int insertSelective(ZgkApesk record) {
        return this.zgkApeskDao.insertSelective(record);
    }
}