package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.gaokao360.common.ServiceImplMaps;
import cn.thinkjoy.gaokao360.domain.DataDict;
import cn.thinkjoy.gaokao360.domain.Province;
import cn.thinkjoy.gaokao360.domain.University;
import cn.thinkjoy.gaokao360.domain.UniversityMajor;
import cn.thinkjoy.gaokao360.dto.GkQueryDomain;
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
    public List<UniversityMajor> getUniversityMajorListByUniversityId(long id,
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
    public List<Object> queryPage(String serviceName,
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
    public List<Province> getProvinceName(){
        return provinceService.findAll();
    }

    /**
     * 字典表通用查询
     * @param type
     * @return
     */
    @Override
    public List<DataDict> getDataDictListByType(String type){
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
    public List<GkQueryDomain> getUniversityByName(String name){
        return universityExService.getUniversityByName(name);
    }

    /**
     * 院校预测算法接口
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> getPredictUniversityInfo(Map<String, Object> params) {
        params.put("majorType",params.get("type"));
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
        for (Map<String, Object> map : dataList)
        {
            if(StringUtils.isEmpty(map.get("lowestScoreList")+""))
            {
                continue;
            }
            String[] lowestScoreArray = String.valueOf(map.get("lowestScoreList")+"").split("@@");
            if(lowestScoreArray.length == 0)
            {
                continue;
            }
            List<Integer> minScoreList = new ArrayList<>();
            for (int i = 0; i < lowestScoreArray.length ; i++) {
                if(isLegalScore(lowestScoreArray[i]))
                {
                    minScoreList.add(parseInt(lowestScoreArray[i]));
                }
            }
            if(minScoreList.size()==0)
            {
                continue;
            }
            List<Integer> avgScoreList = setAvgList(map, minScoreList);
            BigDecimal valueA = getValueA(map, avgScoreList);
            BigDecimal valueB = getValueB(avgScoreList);
            float valueC = getValueC(score);
            if(isConditionOne(valueA, valueB, valueC))
            {
                oneList.add(map);
                continue;
            }
            if(isConditionTwo(valueA, valueB, valueC))
            {
                twoList.add(map);
                continue;
            }
            if(isConditonThree(valueA, valueB, valueC))
            {
                threeList.add(map);
                continue;
            }
            if(isConditonFour(valueA, valueB, valueC))
            {
                fourList.add(map);
            }
        }
        setResultMap(resultMap, oneList, twoList, threeList, fourList);
    }

    private boolean isConditonFour(BigDecimal valueA, BigDecimal valueB, float valueC) {
        return valueC >= valueA.add(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private boolean isConditonThree(BigDecimal valueA, BigDecimal valueB, float valueC) {
        return valueC >= valueA.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() &&
                valueC < valueA.add(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private boolean isConditionTwo(BigDecimal valueA, BigDecimal valueB, float valueC) {
        return valueC >= valueA.subtract(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() &&
                valueC < valueA.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private boolean isConditionOne(BigDecimal valueA, BigDecimal valueB, float valueC) {
        return valueC >= valueA.subtract(valueB.multiply(new BigDecimal(2))).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() &&
                valueC < valueA.subtract(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private List<Integer> setAvgList(Map<String, Object> map, List<Integer> minScoreList) {
        List<Integer> avgScoreList = new ArrayList<>();
        if(StringUtils.isEmpty(map.get("averageScoreList")+""))
        {
            avgScoreList.addAll(minScoreList);
        }else
        {
            String[] averageScoreArray = String.valueOf(map.get("averageScoreList")+"").split("@@");
            for (int i = 0; i < averageScoreArray.length ; i++) {
                if(isLegalScore(averageScoreArray[i]))
                {
                    avgScoreList.add(parseInt(averageScoreArray[i]));
                }
            }
            if(avgScoreList.size() == 0)
            {
                avgScoreList.addAll(minScoreList);
            }
        }
        return avgScoreList;
    }

    private float getValueC(String score) {
        return new BigDecimal(score).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private BigDecimal getValueB(List<Integer> avgScoreList) {
        int avgScoreSize = avgScoreList.size();
        BigDecimal valueB = BigDecimal.ZERO;
        if(avgScoreSize==1)
        {
            valueB = BigDecimal.ZERO;
        }
        float sumDiff = 0f;
        int sumDiffSize = 0;
        if(avgScoreSize>=2)
        {
            for (int i = 0; i < avgScoreSize - 1 ; i++) {
                for (int j = i+1; j < avgScoreSize; j++) {
                    sumDiffSize++;
                    sumDiff += Math.abs(avgScoreList.get(i) - avgScoreList.get(j));
                }
            }
            valueB = new BigDecimal(sumDiff).divide(new BigDecimal(sumDiffSize), 2, BigDecimal.ROUND_HALF_DOWN);
        }
        return valueB;
    }

    private BigDecimal getValueA(Map<String, Object> map, List<Integer> avgScoreList) {
        BigDecimal valueA;
        int totleScore = 0;
        for (int i = 0; i < avgScoreList.size(); i++) {
            totleScore += avgScoreList.get(i);
        }
        valueA = new BigDecimal(totleScore).divide(new BigDecimal(avgScoreList.size()), 2, BigDecimal.ROUND_HALF_UP);
        map.put("avgLowScore", valueA);
        return valueA;
    }

    private void setResultMap(Map<String, Object> resultMap, List<Map<String, Object>> oneList, List<Map<String, Object>> twoList, List<Map<String, Object>> threeList, List<Map<String, Object>> fourList) {
        if(fourList.size()>0)
        {
            sortUniversityList(fourList);
            Map<String, Object> mapFour = new LinkedHashMap<>();
            int size = fourList.size()> 10 ? 10 :fourList.size();
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
            int size = threeList.size()> 10 ? 10 :threeList.size();
            mapThree.put("count", size);
            mapThree.put("star", 3);
            List<Map<String, Object>> list = threeList.subList(0, size);
            mapThree.put("list", list);
            resultMap.put("3",mapThree);
        }

        if(twoList.size() > 0)
        {
            sortUniversityList(twoList);
            Map<String, Object> mapTwo = new LinkedHashMap<>();
            int size = twoList.size()> 10 ? 10 : twoList.size();
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
            int size = oneList.size()> 10 ? 10 :oneList.size();
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
                if(((BigDecimal)o2.get("avgLowScore")).floatValue()==((BigDecimal)o1.get("avgLowScore")).floatValue())
                {
                    return 0;
                }
                return ((BigDecimal)o2.get("avgLowScore")).floatValue()>
                        ((BigDecimal)o1.get("avgLowScore")).floatValue() ? 1 : -1;
            }
        });
    }

    /**
     * 录取难易预测算法
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> getPredictProbability(Map<String, Object> params) {
        params.put("majorType", params.get("type"));
        List<Map<String, Object>> dataList = universityExService.getPredictUniversityInfo(params);
        Map<String, Object> resultMap = new LinkedHashMap<>();
        String batch = "";
        if(dataList.size() > 0)
        {
            String score = params.get("score")+"";
            for (Map<String, Object> map : dataList)
            {
                if(StringUtils.isEmpty(map.get("lowestScoreList")+""))
                {
                    continue;
                }
                String[] lowestScoreArray = String.valueOf(map.get("lowestScoreList")+"").split("@@");
                if(lowestScoreArray.length == 0)
                {
                    continue;
                }
                List<Integer> minScoreList = new ArrayList<>();
                for (int i = 0; i < lowestScoreArray.length ; i++) {
                    if(isLegalScore(lowestScoreArray[i]))
                    {
                        minScoreList.add(parseInt(lowestScoreArray[i]));
                    }
                }
                if(minScoreList.size()==0)
                {
                    continue;
                }
                List<Integer> avgScoreList = setAvgList(map, minScoreList);
                BigDecimal valueA = getValueA(map, avgScoreList);
                BigDecimal valueB = getValueB(avgScoreList);
                float valueC = getValueC(score);
                batch = String.valueOf(map.get("batch"));
                if(isConditionOne(valueA, valueB, valueC))
                {
                    resultMap.put("probability", 1);
                    break;
                }
                else if(isConditionTwo(valueA, valueB, valueC))
                {
                    resultMap.put("probability", 2);
                    break;
                }
                else if(isConditonThree(valueA, valueB, valueC))
                {
                    resultMap.put("probability", 3);
                    break;
                }
                else if(isConditonFour(valueA, valueB, valueC))
                {
                    resultMap.put("probability", 4);
                    break;
                }else
                {
                    resultMap.put("probability", 0);
                }
            }
        }else
        {
            resultMap.put("probability", 0);
        }
        params.put("batch", batch);
        List<Map<String, Object>> historyList = universityExService.getPredictProbability(params);
        setHistoryList(historyList);
        resultMap.put("historyList", historyList);
        return resultMap;
    }

    private void setHistoryList(List<Map<String, Object>> historyList) {
        Collections.sort(historyList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                if(Integer.parseInt(o2.get("year") + "") == Integer.parseInt(o1.get("year") + ""))
                {
                    return Integer.parseInt(o2.get("batch") + "") < Integer.parseInt(o1.get("batch") + "") ? 1 : -1;
                }
                return Integer.parseInt(o2.get("year") + "")>Integer.parseInt(o1.get("year") + "")?1:-1;
            }
        });
    }

    private boolean isLegalScore(String avgScoreStr) {
        return StringUtils.isNotEmpty(avgScoreStr) && isNumber(avgScoreStr) && !"0".equals(avgScoreStr);
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


    private int parseInt(String value)
    {
        return Math.round(Float.parseFloat(value));
    }
}