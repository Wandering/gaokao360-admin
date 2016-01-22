package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.gaokao360.common.ServiceImplMaps;
import cn.thinkjoy.gaokao360.service.common.IDataDictService;
import cn.thinkjoy.gaokao360.service.common.IProvinceService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityExService;
import cn.thinkjoy.zgk.common.QueryUtil;
import cn.thinkjoy.zgk.dto.UniversityEnrollingChartDTO;
import cn.thinkjoy.zgk.dto.UniversityPlanChartDTO;
import cn.thinkjoy.zgk.remote.IUniversityService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Autowired
    private ServiceImplMaps serviceImplMaps;

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
        return universityExService.queryPage(condition, offset, rows, orederBy, sqlOrderEnum, selectorpage);
    }

    @Override
    public Integer getUniversityCount(Map<String, Object> condition) {
        return universityExService.count(condition);
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
     * 获取开设专业列表
     * @param condition
     * @param offset
     * @param rows
     * @param orderBy
     * @param sqlOrderEnumStr
     * @param selectorpage
     * @return
     */
    @Override
    public List getUniversityMajorListByUniversityId(long id,
                                                     Map<String,Object> condition,
                                                     int offset, int rows,
                                                     String orderBy, String sqlOrderEnumStr,
                                                     Map<String,Object> selectorpage){
        SqlOrderEnum sqlOrderEnum=SqlOrderEnum.ASC;
        if (sqlOrderEnumStr.equalsIgnoreCase("desc")){
            sqlOrderEnum=SqlOrderEnum.DESC;
        }
        return serviceImplMaps.get("universityMajorExService").queryPage(condition, offset, rows, orderBy, sqlOrderEnum, selectorpage);
    }

    /**
     * 院校招生计划各年各类别招生统计显示
     * @param params
     * @return
     */
    @Override
    public List<UniversityPlanChartDTO> queryUniversityPlanChart(Map<String, Object> params){
        return universityExService.queryUniversityPlanChart(params);
    }

    /**
     * 院校录取情况各年各类别各批次招生统计显示
     * @param params
     * @return
     */
    @Override
    public List<UniversityEnrollingChartDTO> queryUniversityEnrollingChart(Map<String,Object> params){
        return universityExService.queryUniversityEnrollingChart(params);
    }

    /**
     * 为dubbo调用查询提供公用接口
     * @param serviceName
     * @param condition
     * @param offset
     * @param rows
     * @param orderBy
     * @param sqlOrderEnumStr
     * @param selectorpage
     * @return
     */
    @Override
    public List queryPage(String serviceName,
                          Map<String,Object> condition,
                          int offset, int rows,
                          String orderBy, String sqlOrderEnumStr,
                          Map<String,Object> selectorpage){
        SqlOrderEnum sqlOrderEnum=SqlOrderEnum.ASC;
        if (sqlOrderEnumStr.equalsIgnoreCase("desc")){
            sqlOrderEnum=SqlOrderEnum.DESC;
        }
        return serviceImplMaps.get(serviceName).queryPage(condition, offset, rows, orderBy, sqlOrderEnum, selectorpage);
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


    /**
     * 根据名称模糊查询院校信息
     * @param name
     * @return
     */
    @Override
    public List getUniversityByName(String name){
        return universityExService.getUniversityByName(name);
    }

}
