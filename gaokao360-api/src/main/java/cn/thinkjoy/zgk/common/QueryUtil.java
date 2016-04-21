package cn.thinkjoy.zgk.common;

import cn.thinkjoy.zgk.domain.SearchField;

import java.util.Map;

/**
 * Created by admin on 2016/1/4.
 */
public class QueryUtil {
    public static void setMapOp(Map<String,Object> map,String field,String op,Object data){
        SearchField searchField = new SearchField();
        searchField.setField(field);
        searchField.setOp(op);
        searchField.setData(data);
        map.put(field,searchField);
    }
}
