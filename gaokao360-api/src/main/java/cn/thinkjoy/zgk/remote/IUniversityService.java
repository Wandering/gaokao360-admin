package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.dto.UniversityEnrollingChartDTO;
import cn.thinkjoy.zgk.dto.UniversityMajorEnrollingPlanDTO;
import cn.thinkjoy.zgk.dto.UniversityPlanChartDTO;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by zuohao on 16/1/7.
 */
public interface IUniversityService {
    List getUniversityList(Map<String, Object> condition, int offset, int rows, String orederBy, String sqlOrderEnumStr, Map<String, Object> selectorpage);

    Integer getUniversityCount(Map<String, Object> condition);

    List getUniversityMajorListByUniversityId(long id,Map<String,Object> condition,int offset, int rows,String orderBy, String sqlOrderEnumStr,Map<String,Object> selectorpage);

    Object getUniversityById(long id);

    List getProvinceName();

    List getDataDictListByType(String type);

    List<UniversityPlanChartDTO> queryUniversityPlanChart(Map<String, Object> params);

    List<UniversityMajorEnrollingPlanDTO> getUniversityMajorEnrollingPlanList(Map<String,Object> params);

    List<UniversityEnrollingChartDTO> queryUniversityEnrollingChart(Map<String,Object> params);

    List queryPage(String serviceName,Map<String,Object> condition,int offset, int rows,String orderBy, String sqlOrderEnumStr,Map<String,Object> selectorpage);

    List getUniversityByName(String name);

    Map<String, Object> getPredictUniversityInfo(Map<String, Object> params);

    Map<String, Object> getPredictProbability(Map<String, Object> params);
}
