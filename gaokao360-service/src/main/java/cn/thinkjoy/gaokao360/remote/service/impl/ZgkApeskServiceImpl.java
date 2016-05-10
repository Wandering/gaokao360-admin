package cn.thinkjoy.gaokao360.remote.service.impl;


import cn.thinkjoy.gaokao360.dao.ex.IZgkApeskCourseDao;
import cn.thinkjoy.gaokao360.dao.ex.IZgkApeskDao;
import cn.thinkjoy.zgk.common.Criteria;
import cn.thinkjoy.zgk.common.StringUtil;
import cn.thinkjoy.zgk.domain.ZgkApesk;
import cn.thinkjoy.zgk.domain.ZgkApeskCourse;
import cn.thinkjoy.zgk.dto.ZgkApeskDTO;
import cn.thinkjoy.zgk.dto.ZgkApeskModelDTO;
import cn.thinkjoy.zgk.remote.IZgkApeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * zgk_apesk
 * @version 1.0 2015-09-28
 * @powerby hetgyd 
 */
@Service("ZgkApeskServiceImpl")
public class ZgkApeskServiceImpl implements IZgkApeskService {
    @Autowired
    private IZgkApeskDao zgkApeskDao;
    @Autowired
    private IZgkApeskCourseDao iZgkApeskCourseDao;

    public List<ZgkApesk> query(Long userId, Integer acId, String liangbiao, String testEmail) {
        Criteria example = new Criteria();
        if (userId > -1) {
            example.put("userId", userId);
        }
        if (acId > -1) {
            example.put("acId", acId);
        }
        if (!StringUtil.isNulOrBlank(liangbiao)) {
            example.put("liangBiao", liangbiao);
        }
        if (!StringUtil.isNulOrBlank(testEmail)) {
            example.put("testEmail", testEmail);
        }
        example.put("orderByClause", "create_date desc");
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

    public List<ZgkApeskDTO> selectUserApeskResult(Map map) {
        List<ZgkApeskCourse> zgkApeskCourses = iZgkApeskCourseDao.selectByExample(null);

        List<ZgkApeskDTO> zgkApeskDTOs=new ArrayList<>();

        for(ZgkApeskCourse zgkApeskCourse:zgkApeskCourses) {
            ZgkApeskDTO zgkApeskDTO = new ZgkApeskDTO();
            zgkApeskDTO.setName(zgkApeskCourse.getName());
            zgkApeskDTO.setReportUrl(zgkApeskCourse.getReportUrl());
            zgkApeskDTO.setGradeDescribe(zgkApeskCourse.getGradeDescribe());
            zgkApeskDTO.setType(zgkApeskCourse.getAcId());
            zgkApeskDTO.setIntroduce(zgkApeskCourse.getIntroduce());
            zgkApeskDTO.setPicUrl(zgkApeskCourse.getPicUrl());
            map.put("type", zgkApeskDTO.getType());
            ZgkApeskModelDTO zgkApeskModelDTO = zgkApeskDao.selectUserApeskModel(map);
            if (zgkApeskModelDTO != null) {
//                zgkApeskDTO.setNum(0);
//                zgkApeskDTO.setCreateDate(null);
//                zgkApeskDTO.setReportDate();
//                zgkApeskDTO.setReportId(zgkApeskModelDTO.getReportId());
//                zgkApeskDTO.setUserId(zgkApeskModelDTO.getUserId());
//            } else {
                zgkApeskDTO.setNum(zgkApeskModelDTO.getNum());
                zgkApeskDTO.setCreateDate(zgkApeskModelDTO.getCreateDate());
                zgkApeskDTO.setReportDate(zgkApeskModelDTO.getReportDate());
                zgkApeskDTO.setReportId(zgkApeskModelDTO.getReportId());
                zgkApeskDTO.setUserId(zgkApeskModelDTO.getUserId());
            }
            zgkApeskDTOs.add(zgkApeskDTO);
        }

        return zgkApeskDTOs;
    }
}