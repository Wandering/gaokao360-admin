package cn.thinkjoy.zgk.remote;

import java.util.List;
import java.util.Map;

/**
 * Created by zuohao on 16/1/7.
 */
public interface IUniversityService {
    List getUniversityList(Map<String, Object> condition, int offset, int rows, String orederBy, String sqlOrderEnumStr, Map<String, Object> selectorpage);
}
