package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.gaokao360.domain.Province;
import cn.thinkjoy.gaokao360.service.common.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/2/3.
 */
@Component
public class AreaMaps {
    private static Map<String, Long> areaMap = new HashMap<>();

    @Autowired
    private IProvinceService provinceService;

    @PostConstruct
    private Map<String, Long> getAreaMap()
    {
        if(areaMap.isEmpty())
        {
            initAreaInfo();
        }
        return areaMap;
    }


    private void initAreaInfo()
    {
        List<Province> list =  provinceService.findAll();
        for (Province province:list) {
            areaMap.put(province.getCode(), Long.parseLong(String.valueOf(province.getId())));
        }
    }

    public Long getAreaId(){
        //默认浙江省
        return Long.valueOf(String.valueOf(getAreaMap().get(UserAreaContext.getCurrentUserArea())).toString());
    }
}
