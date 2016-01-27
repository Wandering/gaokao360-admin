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

import java.math.BigDecimal;
import java.util.*;

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

    @Override
    public Map<String, Object> getPredictUniversityInfo(Map<String, Object> params) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        List<Map<String, Object>> dataList = universityExService.getPredictUniversityInfo(params);
        caculateUniversityLists(params, resultMap, dataList);
        return resultMap;
    }

    private void caculateUniversityLists(Map<String, Object> params, Map<String, Object> resultMap, List<Map<String, Object>> dataList) {
        List<Map<String, Object>> oneList = new ArrayList<>();
        List<Map<String, Object>> twoList = new ArrayList<>();
        List<Map<String, Object>> threeList = new ArrayList<>();
        List<Map<String, Object>> fourList = new ArrayList<>();
        String score = params.get("params")+"";
        BigDecimal valueC = new BigDecimal(score);
        for (Map<String, Object> map : dataList)
        {
            String[] averageScoreArray = String.valueOf(map.get("averageScoreList")).split("@@");
            if(averageScoreArray.length >= 3)
            {
                BigDecimal bigOne = new BigDecimal(averageScoreArray[0]);
                BigDecimal smallOne = new BigDecimal(averageScoreArray[2]);
                if(bigOne.longValue()>0 && smallOne.longValue()>0)
                {
                    BigDecimal valueB = bigOne.multiply(smallOne).multiply(new BigDecimal(2)).setScale(2);
                    BigDecimal valueA = new BigDecimal(map.get("lowestScore")+"").setScale(2, BigDecimal.ROUND_HALF_UP);
                    if(valueA.longValue()>valueB.longValue())
                    {
                        if(valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()>=valueA.add(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).longValue() &&
                                valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()<=valueA.add(valueB.multiply(new BigDecimal(2))).setScale(2, BigDecimal.ROUND_HALF_UP).longValue())
                        {
                            oneList.add(map);
                        }
                        if(valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()>=valueA.setScale(2, BigDecimal.ROUND_HALF_UP).longValue() &&
                                valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()<=valueA.add(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).longValue())
                        {
                            twoList.add(map);
                        }
                        if(valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()>=valueA.subtract(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).longValue() &&
                                valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()<=valueA.setScale(2, BigDecimal.ROUND_HALF_UP).longValue())
                        {
                            threeList.add(map);
                        }
                        if(valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()>=valueA.subtract(valueB.multiply(new BigDecimal(2))).setScale(2, BigDecimal.ROUND_HALF_UP).longValue() &&
                                valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()<=valueA.subtract(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).longValue())
                        {
                            fourList.add(map);
                        }
                    }
                }
            }
        }
        setResultMap(resultMap, oneList, twoList, threeList, fourList);
    }

    private void setResultMap(Map<String, Object> resultMap, List<Map<String, Object>> oneList, List<Map<String, Object>> twoList, List<Map<String, Object>> threeList, List<Map<String, Object>> fourList) {
        if(fourList.size()>0)
        {
            Map<String, Object> mapFour = new LinkedHashMap<>();
            mapFour.put("count",fourList.size());
            mapFour.put("star", 4);
            mapFour.put("list", fourList);
            resultMap.put("4",mapFour);
        }

        if(threeList.size()>0)
        {
            Map<String, Object> mapThree = new LinkedHashMap<>();
            mapThree.put("count",threeList.size());
            mapThree.put("star", 3);
            mapThree.put("list", threeList);
            resultMap.put("3",mapThree);
        }

        if(twoList.size()>0)
        {
            Map<String, Object> mapTwo = new LinkedHashMap<>();
            mapTwo.put("count",twoList.size());
            mapTwo.put("star", 2);
            mapTwo.put("list", twoList);
            resultMap.put("2",mapTwo);
        }

        if(oneList.size()>0)
        {
            Map<String, Object> mapOne = new LinkedHashMap<>();
            mapOne.put("count",oneList.size());
            mapOne.put("star", 1);
            mapOne.put("list", oneList);
            resultMap.put("1",mapOne);
        }
    }

    @Override
    public Map<String, Object> getPredictProbability(Map<String, Object> params) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        List<Map<String, Object>> dataList = universityExService.getPredictProbability(params);
        String score = params.get("score")+"";
        BigDecimal valueC = new BigDecimal(score);
        if(dataList.size()>0)
        {
            getBatch(resultMap, dataList, valueC);
            getProbability(resultMap, dataList, valueC);
            resultMap.put("historyList", dataList);
        }
        resultMap.put("score", score);
        resultMap.put("type", params.get("type"));
        resultMap.put("universityName", params.get("universityName"));
        return resultMap;
    }

    private void getProbability(Map<String, Object> resultMap, List<Map<String, Object>> dataList, BigDecimal valueC) {
        if(dataList.size()==3)
        {
            int highScore = Integer.parseInt(dataList.get(0).get("minScore")+"");
            int midScore = Integer.parseInt(dataList.get(1).get("minScore")+"");
            int lowScore = Integer.parseInt(dataList.get(2).get("minScore")+"");
            BigDecimal valueA = new BigDecimal(highScore+midScore+lowScore).divide(new BigDecimal(3)).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal valueB = new BigDecimal(highScore*2).subtract(new BigDecimal(lowScore*2)).divide(new BigDecimal(3)).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal valueD = valueC.divide(valueA.add(valueB)).setScale(2,BigDecimal.ROUND_HALF_UP);
            if(valueD.longValue() > valueB.divide(valueA).add(new BigDecimal(1)).longValue())
            {
                resultMap.put("probability", 4);
            }
            else if(valueD.longValue() <= valueB.divide(valueA).add(new BigDecimal(1)).longValue()
                    && valueD.longValue()>1)
            {
                resultMap.put("probability", 3);
            }
            else if(valueD.longValue() <= 1 &&
               valueD.longValue() > new BigDecimal(1).subtract(valueB.divide(valueA)).longValue())
            {
                resultMap.put("probability", 2);
            }
            else if(valueD.longValue() <= new BigDecimal(1).subtract(valueB.divide(valueA)).longValue()
                    && valueD.longValue() >= new BigDecimal(1).subtract(valueB.divide(valueA).multiply(new BigDecimal(2))).longValue())
            {
                resultMap.put("probability", 1);
            }else
            {
                resultMap.put("probability", 1);
            }
        }
    }

    private void getBatch(Map<String, Object> resultMap, List<Map<String, Object>> dataList, BigDecimal valueC) {
        for (Map<String, Object> data:dataList) {
            if("2015".equals(data.get("year")) && null != data.get("minScore"))
            {
                String lowScore = data.get("minScore")+"";
                if("1".equals(data.get("batch"))&&valueC.longValue()>=new BigDecimal(lowScore).longValue())
                {
                    resultMap.put("batch", "一本");
                }else if("2".equals(data.get("batch"))&&valueC.longValue()>=new BigDecimal(lowScore).longValue())
                {
                    resultMap.put("batch", "二本");
                }else if("3".equals(data.get("batch"))&&valueC.longValue()>=new BigDecimal(lowScore).longValue())
                {
                    resultMap.put("batch", "三本");
                }else
                {
                    resultMap.put("batch", "专科");
                }
            }
        }
    }
}
