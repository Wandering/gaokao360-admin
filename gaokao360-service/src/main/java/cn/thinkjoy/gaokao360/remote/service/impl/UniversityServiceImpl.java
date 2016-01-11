package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.gaokao360.domain.University;
import cn.thinkjoy.gaokao360.domain.UniversityDetail;
import cn.thinkjoy.gaokao360.service.common.IDataDictService;
import cn.thinkjoy.gaokao360.service.common.IProvinceService;
import cn.thinkjoy.gaokao360.service.common.IUniversityDictService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityExService;
import cn.thinkjoy.zgk.remote.IUniversityService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zuohao on 16/1/7.
 */
@Service("remoteUniversityServiceImpl")
public class UniversityServiceImpl implements IUniversityService {

    @Autowired
    private cn.thinkjoy.gaokao360.service.common.IUniversityService universityService;

    @Autowired
    private IUniversityExService universityExService;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private IDataDictService dataDictService;

    /**
     * 查询学校列表
     * @param condition
     * @param offset
     * @param rows
     * @param orederBy
     * @param sqlOrderEnumStr
     * @param selectorpage
     * @return
     */
    @Override
    public List getUniversityList(Map<String, Object> condition, int offset, int rows, String orederBy, String sqlOrderEnumStr, Map<String, Object> selectorpage) {
        SqlOrderEnum sqlOrderEnum=SqlOrderEnum.ASC;
        if(sqlOrderEnumStr.equalsIgnoreCase("DESC"))
            sqlOrderEnum=SqlOrderEnum.DESC;
        return universityService.queryPage(condition, offset, rows, orederBy, sqlOrderEnum, selectorpage);
    }

    /**
     * 查询院校详情
     * @param id
     * @return
     */
    @Override
    public Object getUniversityById(long id){
        return universityExService.fetch(id);
    }

    /**
     * 查询所有省份
     * @return
     */
    @Override
    public List getProvinceName(){
        return provinceService.findAll();
    }

    /**
     * 字典表通用查询
     * @param type
     * @return
     */
    @Override
    public List getDataDictListByType(String type){
        Map<String,Object> condition = Maps.newHashMap();
        condition.put("type",type);
        Map<String,Object> selector = Maps.newHashMap();
        selector.put("dictId","dictId");
        selector.put("name","name");
        return  dataDictService.queryList(condition,"dictId", SqlOrderEnum.ASC.toString(),selector);
    }

}
