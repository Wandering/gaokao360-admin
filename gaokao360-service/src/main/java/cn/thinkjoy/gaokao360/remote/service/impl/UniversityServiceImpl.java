package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.gaokao360.common.ServiceImplMaps;
import cn.thinkjoy.gaokao360.service.common.IDataDictService;
import cn.thinkjoy.gaokao360.service.common.IProvinceService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityExService;
import cn.thinkjoy.zgk.dto.UniversityEnrollingChartDTO;
import cn.thinkjoy.zgk.dto.UniversityMajorEnrollingPlanDTO;
import cn.thinkjoy.zgk.dto.UniversityPlanChartDTO;
import cn.thinkjoy.zgk.remote.IUniversityService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public List<UniversityMajorEnrollingPlanDTO> getUniversityMajorEnrollingPlanList(Map<String,Object> params){
        return universityExService.getUniversityMajorEnrollingPlanList(params);
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
        params.put("majorType",params.get("type"));
        params.put("startYear", 2012);
        params.put("endYear", 2014);
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
        String score = params.get("score")+"";
        BigDecimal valueC = new BigDecimal(score);
        for (Map<String, Object> map : dataList)
        {
            String[] averageScoreArray = String.valueOf(map.get("averageScoreList")+"").split("@@");
            if(averageScoreArray.length >= 3&& StringUtils.isNotEmpty(averageScoreArray[0])
                    &&StringUtils.isNotEmpty(averageScoreArray[1])&&StringUtils.isNotEmpty(averageScoreArray[2]))
            {
                BigDecimal bigOne = new BigDecimal(averageScoreArray[0]);
                BigDecimal smallOne = new BigDecimal(averageScoreArray[2]);
                if(bigOne.longValue()>0 && smallOne.longValue()>0
                        && StringUtils.isNotEmpty(map.get("avgLowestScore")+""))
                {
                    try{
                        BigDecimal valueB = bigOne.subtract(smallOne).multiply(new BigDecimal(2)).setScale(2);
                        BigDecimal valueA = new BigDecimal(map.get("avgLowestScore")+"").setScale(2, BigDecimal.ROUND_HALF_UP);
                        if(valueA.longValue()>=valueB.longValue()*2)
                        {
                            if(valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()>=valueA.add(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).longValue() &&
                                    valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()<=valueA.add(valueB.multiply(new BigDecimal(2))).setScale(2, BigDecimal.ROUND_HALF_UP).longValue())
                            {
                                fourList.add(map);
                            }
                            if(valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()>=valueA.setScale(2, BigDecimal.ROUND_HALF_UP).longValue() &&
                                    valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()<=valueA.add(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).longValue())
                            {
                                threeList.add(map);
                            }
                            if(valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()>=valueA.subtract(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).longValue() &&
                                    valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()<=valueA.setScale(2, BigDecimal.ROUND_HALF_UP).longValue())
                            {
                                twoList.add(map);
                            }
                            if(valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()>=valueA.subtract(valueB.multiply(new BigDecimal(2))).setScale(2, BigDecimal.ROUND_HALF_UP).longValue() &&
                                    valueC.setScale(2, BigDecimal.ROUND_HALF_UP).longValue()<=valueA.subtract(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).longValue())
                            {
                                oneList.add(map);
                            }
                        }
                    }catch (Exception e)
                    {
                    }
                }
            }
        }
        setResultMap(resultMap, oneList, twoList, threeList, fourList);
    }

    private void setResultMap(Map<String, Object> resultMap, List<Map<String, Object>> oneList, List<Map<String, Object>> twoList, List<Map<String, Object>> threeList, List<Map<String, Object>> fourList) {
        if(fourList.size()>0)
        {
            sortUniversityList(fourList);
            Map<String, Object> mapFour = new LinkedHashMap<>();
            int size = fourList.size()>5?5:fourList.size();
            mapFour.put("count",size);
            mapFour.put("star", 4);
            List<Map<String, Object>> list = fourList.subList(0, size);
            mapFour.put("list", list);
            resultMap.put("4",mapFour);
        }

        if(threeList.size()>0)
        {
            sortUniversityList(threeList);
            Map<String, Object> mapThree = new LinkedHashMap<>();
            int size = threeList.size()>5?5:threeList.size();
            mapThree.put("count", size);
            mapThree.put("star", 3);
            List<Map<String, Object>> list = threeList.subList(0, size);
            mapThree.put("list", list);
            resultMap.put("3",mapThree);
        }

        if(twoList.size()>0)
        {
            sortUniversityList(twoList);
            Map<String, Object> mapTwo = new LinkedHashMap<>();
            int size = twoList.size()>5?5:twoList.size();
            mapTwo.put("count", size);
            mapTwo.put("star", 2);
            List<Map<String, Object>> list = twoList.subList(0, size);
            mapTwo.put("list", list);
            resultMap.put("2",mapTwo);
        }

        if(oneList.size()>0)
        {
            sortUniversityList(oneList);
            Map<String, Object> mapOne = new LinkedHashMap<>();
            int size = oneList.size()>5?5:oneList.size();
            mapOne.put("count", size);
            mapOne.put("star", 1);
            List<Map<String, Object>> list = oneList.subList(0, size);
            mapOne.put("list", list);
            resultMap.put("1",mapOne);
        }
    }

    private void sortUniversityList(List<Map<String, Object>> list) {
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return ((BigDecimal)o2.get("avgLowestScore")).longValue()>=
                        ((BigDecimal)o1.get("avgLowestScore")).longValue()?1:-1;
            }
        });
    }

    @Override
    public Map<String, Object> getPredictProbability(Map<String, Object> params) {
        params.put("majorType", params.get("type"));
        params.put("startYear", 2012);
        params.put("endYear", 2014);
        List<Map<String, Object>> dataList = universityExService.getPredictProbability(params);
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if(dataList.size()>0)
        {
            String score = params.get("score")+"";
            BigDecimal valueC = new BigDecimal(score);
            getBatch(resultMap, params.get("type")+"" , valueC);
            List<Map<String, Object>> historyList = new ArrayList<>();
            for (Map<String, Object> map : dataList)
            {
                if(resultMap.get("batch").equals(map.get("batch")+""))
                {
                    historyList.add(map);
                }
            }
            getProbability(resultMap, historyList, valueC);
            Collections.sort(historyList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return Integer.parseInt(o2.get("year")+"")>Integer.parseInt(o1.get("year")+"")?1:-1;
                }
            });
            resultMap.put("historyList", historyList);
        }
        return resultMap;
    }

    private void getProbability(Map<String, Object> resultMap, List<Map<String, Object>> dataList, BigDecimal valueC) {
        BigDecimal valueA;
        BigDecimal valueB;
        BigDecimal valueD;
        if(dataList.size() == 3)
        {
            int highScore = Integer.parseInt(dataList.get(0).get("avgScore")+"");
            int midScore = Integer.parseInt(dataList.get(1).get("avgScore")+"");
            int lowScore = Integer.parseInt(dataList.get(2).get("avgScore")+"");
            valueA = new BigDecimal(highScore+midScore+lowScore).divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP);
            valueB = new BigDecimal(highScore*2).subtract(new BigDecimal(lowScore*2)).divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP);
            valueD = valueC.divide(valueA.add(valueB),2,BigDecimal.ROUND_HALF_UP);
            setProbability(resultMap, valueA, valueB, valueD);
        }else if(dataList.size() == 2)
        {
            int highScore = Integer.parseInt(dataList.get(0).get("avgScore")+"");
            int lowScore = Integer.parseInt(dataList.get(1).get("avgScore")+"");
            valueA = new BigDecimal(highScore+lowScore).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
            valueB = new BigDecimal(highScore).subtract(new BigDecimal(lowScore)).setScale(2, BigDecimal.ROUND_HALF_UP);
            valueD = valueC.divide(valueA.add(valueB),2,BigDecimal.ROUND_HALF_UP);
            setProbability(resultMap, valueA, valueB, valueD);
        }
        else
        {
            resultMap.put("probability", 0);
        }
    }

    private void setProbability(Map<String, Object> resultMap, BigDecimal valueA, BigDecimal valueB, BigDecimal valueD) {
        if(valueD.longValue() > valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1)).longValue())
        {
            resultMap.put("probability", 4);
        }
        else if(valueD.longValue() <= valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1)).longValue()
                && valueD.longValue()>1)
        {
            resultMap.put("probability", 3);
        }
        else if(valueD.longValue() <= 1 &&
                valueD.longValue() > new BigDecimal(1).subtract(valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP)).longValue())
        {
            resultMap.put("probability", 2);
        }
        else if(valueD.longValue() <= new BigDecimal(1).subtract(valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP)).longValue()
                && valueD.longValue() >= new BigDecimal(1).subtract(valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(2))).longValue())
        {
            resultMap.put("probability", 1);
        }
        else
        {
            resultMap.put("probability", 1);
        }
    }

    private void getBatch(Map<String, Object> resultMap, String type, BigDecimal valueC) {
        if("1".equals(type))
        {
           if(valueC.longValue()>=626)
           {
               resultMap.put("batch", "一本");
           }
           else if(valueC.longValue()>=472)
           {
               resultMap.put("batch", "二本");
           }
           else if(valueC.longValue()>=400)
           {
               resultMap.put("batch", "三本");
           }else if(valueC.longValue()>=300){
               resultMap.put("batch", "专科");
           }else
           {
               resultMap.put("batch", "无匹配批次");
           }
        }
        else
        {
            if(valueC.longValue()>=605)
            {
                resultMap.put("batch", "一本");
            }
            else if(valueC.longValue()>=428)
            {
                resultMap.put("batch", "二本");
            }
            else if(valueC.longValue()>=380)
            {
                resultMap.put("batch", "三本");
            }else if(valueC.longValue()>=300){
                resultMap.put("batch", "专科");
            }else
            {
                resultMap.put("batch", "无匹配批次");
            }
        }
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }
}
