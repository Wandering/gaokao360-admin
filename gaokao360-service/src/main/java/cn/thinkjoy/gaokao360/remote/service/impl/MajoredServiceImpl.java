package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.service.common.ex.IUniversityMajorExService;
import cn.thinkjoy.zgk.remote.IMajoredService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zuohao on 16/1/23.
 */
@Service("remoteMajoredServiceImpl")
public class MajoredServiceImpl implements IMajoredService {

    @Autowired
    private IUniversityMajorExService universityMajorExService;

    @Override
    public List getMajorOpenUniversityList(int majoredId,int offset,int rows,String orderBy,String sortBy){
        Map<String,Object> map= Maps.newHashMap();
        map.put("majorId", majoredId);
        return universityMajorExService.getMajorOpenUniversityList(map,offset,rows,orderBy,sortBy);
    }

}
