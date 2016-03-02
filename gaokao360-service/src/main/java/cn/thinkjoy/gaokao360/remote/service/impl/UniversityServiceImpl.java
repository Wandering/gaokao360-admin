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

    /**
     * 院校预测算法接口
     * @param params
     * @return
     */
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
        for (Map<String, Object> map : dataList)
        {
            if("上海交通大学".equals(map.get("universityName")))
            {
                System.out.println("haha");
//                continue;
            }
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
                    minScoreList.add(Integer.parseInt(lowestScoreArray[i]));
                }
            }
            if(minScoreList.size()==0)
            {
                continue;
            }
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
                        avgScoreList.add(Integer.parseInt(averageScoreArray[i]));
                    }
                }
                if(avgScoreList.size() == 0)
                {
                    avgScoreList.addAll(minScoreList);
                }
            }
            BigDecimal valueA;
            BigDecimal valueB = BigDecimal.ZERO;
            int totleScore = 0;
            for (int i = 0; i < avgScoreList.size(); i++) {
                totleScore += avgScoreList.get(i);
            }
            valueA = new BigDecimal(totleScore).divide(new BigDecimal(avgScoreList.size()), 2, BigDecimal.ROUND_HALF_UP);
            map.put("avgLowScore", valueA);
            if(new BigDecimal(score).floatValue() < valueA.floatValue() - 99)
            {
                continue;
            }
            if(avgScoreList.size()==1)
            {
                valueB = BigDecimal.ZERO;
            }
            if(avgScoreList.size()==2)
            {
                valueB = new BigDecimal(avgScoreList.get(0)).subtract(new BigDecimal(avgScoreList.get(1)));
            }
            if(avgScoreList.size()==3)
            {
                BigDecimal bigOne = new BigDecimal(avgScoreList.get(0));
                BigDecimal smallOne = new BigDecimal(avgScoreList.get(2));
                valueB = bigOne.subtract(smallOne).
                        multiply(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP)
                        .divide(new BigDecimal(2),2, BigDecimal.ROUND_HALF_UP);
            }
            float floatValueA = new BigDecimal(score).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            if(floatValueA >= valueA.subtract(valueB.multiply(new BigDecimal(2))).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() &&
                    floatValueA < valueA.subtract(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue())
            {
                oneList.add(map);
                continue;
            }
            if(floatValueA >= valueA.subtract(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() &&
                    floatValueA < valueA.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue())
            {
                twoList.add(map);
                continue;
            }
            if(floatValueA >= valueA.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue() &&
                    floatValueA < valueA.add(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue())
            {
                threeList.add(map);
                continue;
            }
            if(floatValueA >= valueA.add(valueB).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue())
            {
                fourList.add(map);
            }
        }
        setResultMap(resultMap, oneList, twoList, threeList, fourList);
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
                return ((BigDecimal)o2.get("avgLowScore")).floatValue()>=
                        ((BigDecimal)o1.get("avgLowScore")).floatValue()?1:-1;
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
        params.put("startYear", 2012);
        params.put("endYear", 2014);
        List<Map<String, Object>> dataList = universityExService.getPredictProbability(params);
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if(dataList.size()>0)
        {
            String score = params.get("score")+"";
            BigDecimal valueC = new BigDecimal(score);
            Map<String,List<Integer>> minScoreMap = new HashMap<>();
            Map<String,List<Integer>> avgScoreMap = new HashMap<>();
            setScoreMap(dataList, minScoreMap, avgScoreMap);
            getBatch(resultMap, valueC, minScoreMap);
            getProbability(resultMap, avgScoreMap, minScoreMap, valueC);
            List<Map<String, Object>> historyList = new ArrayList<>();
            for (Map<String, Object> map : dataList)
            {
                if(null == map.get("minScore") || "0".equals(map.get("minScore")))
                {
                    map.put("minScore", "-");
                }
                if(null == map.get("avgScore") || "0".equals(map.get("avgScore")))
                {
                    map.put("avgScore", "-");
                }
                setBatch(map ,  map.get("batch")+"");
                historyList.add(map);
            }
            setHistoryList(historyList);
            resultMap.put("historyList", historyList);
        }else
        {
            resultMap.put("probability", 0);
            resultMap.put("batch", "无匹配");
            resultMap.put("historyList", dataList);
        }
        return resultMap;
    }

    private void setHistoryList(List<Map<String, Object>> historyList) {
        Collections.sort(historyList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return Integer.parseInt(o2.get("year") + "")>Integer.parseInt(o1.get("year") + "")?1:-1;
            }
        });
    }

    private void setScoreMap(List<Map<String, Object>> dataList, Map<String, List<Integer>> minScoreMap, Map<String, List<Integer>> avgScoreMap) {
        for (Map<String, Object> map : dataList)
        {
            String batch = map.get("batch") + "";
            List<Integer> minScoreList = minScoreMap.get(batch);
            if(null == minScoreList)
            {
                minScoreList = new ArrayList<>();
            }
            List<Integer> avgScoreList = avgScoreMap.get(batch);
            if(null == avgScoreList)
            {
                avgScoreList = new ArrayList<>();
            }
            String minScoreStr = map.get("minScore") + "";
            String avgScoreStr = map.get("avgScore") + "";
            if(isLegalScore(minScoreStr))
            {
                minScoreList.add(Integer.parseInt(minScoreStr));
                minScoreMap.put(batch, minScoreList);
            }
            if(isLegalScore(avgScoreStr))
            {
                avgScoreList.add(Integer.parseInt(avgScoreStr));
                avgScoreMap.put(batch, avgScoreList);
            }
        }
    }

    private void getBatch(Map<String, Object> resultMap, BigDecimal valueC, Map<String, List<Integer>> minScoreMap) {
        if(!minScoreMap.isEmpty())
        {
            Set<String> minBatchs = minScoreMap.keySet();
            if(minBatchs.size() == 0)
            {
                resultMap.put("batch", "无匹配");
                return;
            }
            if(minBatchs.size() == 1)
            {
                String batch = minBatchs.iterator().next();
                List<Integer> minScores = minScoreMap.get(batch);
                int totleScore = 0;
                for (int i = 0; i < minScores.size() ; i++) {
                    totleScore += minScores.get(i);
                }
                BigDecimal avgMinScore = new BigDecimal(totleScore).divide(new BigDecimal(minScores.size()), 2, BigDecimal.ROUND_HALF_UP);
                if(valueC.floatValue() > avgMinScore.floatValue())
                {
                    setBatch(resultMap, batch);
                }else
                {
                    resultMap.put("batch", "无匹配");
                    return;
                }
            }
            if(minBatchs.size() > 1)
            {
                for (String minBatch : minBatchs) {
                    List<Integer> minScores = minScoreMap.get(minBatch);
                    int totleScore = 0;
                    for (int i = 0; i < minScores.size() ; i++) {
                        totleScore += minScores.get(i);
                    }
                    BigDecimal avgMinScore = new BigDecimal(totleScore).divide(new BigDecimal(minScores.size()), 2, BigDecimal.ROUND_HALF_UP);
                    if(valueC.floatValue() > avgMinScore.floatValue())
                    {
                        setBatch(resultMap, minBatch);
                        break;
                    }
                }
                if(null == resultMap.get("batch"))
                {
                    resultMap.put("batch", "无匹配");
                }
            }
        }
    }

    private void setBatch(Map<String, Object> resultMap, String batch) {
        if("1".equals(batch))
        {
            resultMap.put("batch", "第一批");
        }
        else if("2".equals(batch))
        {
            resultMap.put("batch", "第二批");
        }
        else if("4".equals(batch))
        {
            resultMap.put("batch", "第三批");
        }else if("8".equals(batch))
        {
            resultMap.put("batch", "高职（专科）");
        }else
        {
            resultMap.put("batch", "无匹配");
        }
    }

    private boolean isLegalScore(String avgScoreStr) {
        return StringUtils.isNotEmpty(avgScoreStr) && isNumber(avgScoreStr) && !"0".equals(avgScoreStr);
    }

    private void getProbability(Map<String, Object> resultMap, Map<String,List<Integer>> avgScoreMap,
                                Map<String,List<Integer>> minScoreMap, BigDecimal valueC) {
        if("无匹配".equals(resultMap.get("batch")))
        {
            resultMap.put("probability", 0);
        }else
        {
            String matchBatchStr = resultMap.get("batch") + "";
            String matchBatch = "1";
            if("第一批".equals(matchBatchStr))
            {
                matchBatch = "1";
            }else if("第二批".equals(matchBatchStr))
            {
                matchBatch = "2";
            }
            else if("第三批".equals(matchBatchStr))
            {
                matchBatch = "4";
            }else if("高职（专科）".equals(matchBatchStr))
            {
                matchBatch = "8";
            }
            List<Integer> avgScores = avgScoreMap.get(matchBatch);
            if(null == avgScores)
            {
                avgScores = minScoreMap.get(matchBatch);
            }
            if(null ==avgScores || avgScores.size() == 0)
            {
                resultMap.put("probability", 0);
                return;
            }
            BigDecimal valueA;
            BigDecimal valueB;
            BigDecimal valueD;
            if(avgScores.size() == 3)
            {
                int highScore = avgScores.get(0);
                int midScore = avgScores.get(1);
                int lowScore = avgScores.get(2);
                valueA = new BigDecimal(highScore+midScore+lowScore).divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP);
                valueB = new BigDecimal(highScore*2).subtract(new BigDecimal(lowScore*2)).divide(new BigDecimal(3),2,BigDecimal.ROUND_HALF_UP);
                valueD = valueC.divide(valueA.add(valueB),2,BigDecimal.ROUND_HALF_UP);
                setProbability(resultMap, valueA, valueB, valueD);
            }else if(avgScores.size() == 2)
            {
                int highScore = avgScores.get(0);
                int lowScore = avgScores.get(1);
                valueA = new BigDecimal(highScore+lowScore).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
                valueB = new BigDecimal(highScore).subtract(new BigDecimal(lowScore)).setScale(2, BigDecimal.ROUND_HALF_UP);
                valueD = valueC.divide(valueA.add(valueB),2,BigDecimal.ROUND_HALF_UP);
                setProbability(resultMap, valueA, valueB, valueD);
            }
            else if(avgScores.size() == 1)
            {
                int highScore = avgScores.get(0);
                valueA = new BigDecimal(highScore);
                valueB = BigDecimal.ZERO;
                valueD = valueC.divide(valueA.add(valueB),2,BigDecimal.ROUND_HALF_UP);
                setProbability(resultMap, valueA, valueB, valueD);
            }
            else
            {
                resultMap.put("probability", 0);
            }
        }
    }

    /**
     * 难易预测计算
     * @param resultMap
     * @param valueA = 3年录取平均分累加/3
     * @param valueB = 3年录取平均分分差累加/3
     * @param valueD = 数个人分（valueC）/（录取平均分（valueA）+录取平均分分差（valueB））
     * valueD>1+valueB/valueA                           四颗星
     * valueD>[1,1+valueB/valueA]                       三颗星
     * valueD>[1-valueB/valueA,1]                       两颗星
     * valueD>[1-2valueB/valueA,1-valueB/valueA]        一颗星
     */
    private void setProbability(Map<String, Object> resultMap, BigDecimal valueA, BigDecimal valueB, BigDecimal valueD) {
        if(valueD.floatValue() > valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1)).floatValue())
        {
            resultMap.put("probability", 4);
        }
        else if(valueD.floatValue() <= valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1)).floatValue()
                && valueD.floatValue()>1)
        {
            resultMap.put("probability", 3);
        }
        else if(valueD.floatValue() <= 1 &&
                valueD.floatValue() > new BigDecimal(1).subtract(valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP)).floatValue())
        {
            resultMap.put("probability", 2);
        }
        else if(valueD.floatValue() <= new BigDecimal(1).subtract(valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP)).floatValue()
                && valueD.floatValue() >= new BigDecimal(1).subtract(valueB.divide(valueA,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(2))).floatValue())
        {
            resultMap.put("probability", 1);
        }
        else
        {
            resultMap.put("probability", 0);
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