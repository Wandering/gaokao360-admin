package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.utils.SqlOrderEnum;
import cn.thinkjoy.gaokao360.common.QueryUtil;
import cn.thinkjoy.gaokao360.domain.GkinformationGkhot;
import cn.thinkjoy.gaokao360.service.differentiation.IGkinformationGkhotService;
import cn.thinkjoy.zgk.domain.GkHot;
import cn.thinkjoy.zgk.remote.IGkHotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/1/4.
 */
@Service("GkHotServiceImpl")
public class GkHotServiceImpl implements IGkHotService {

    @Autowired
    IGkinformationGkhotService gkinformationGkhotService;
    /**
     * 获取热点摘要列表 四个
     * @return
     */
    @Override
    public List<GkHot> getGkHotList(String areaId,String type,Integer num) {
        if(num!=null){num=10;}
        List<GkinformationGkhot> gkinformationGkhots = null;
        Map<String,Object> map = new HashMap<>();
        map.put("groupOp","and");
        QueryUtil.setMapOp(map,"areaId","=",areaId);
        QueryUtil.setMapOp(map,"type","=",type);
        gkinformationGkhots= gkinformationGkhotService.listByPage(map,0,num,"hotdate", SqlOrderEnum.DESC);
        return GkinformationGkhot2GkHot(gkinformationGkhots);
    }

    /**
     * 获取详情
     * @return
     */
    @Override
    public GkHot getGkHotInfo(String id){
        return GkinformationGkhot2GkHot((GkinformationGkhot) gkinformationGkhotService.fetch(id));
    }

    private List<GkHot> GkinformationGkhot2GkHot(List<GkinformationGkhot> gkinformationGkhots){
        if(gkinformationGkhots==null)return null;
        List<GkHot> gkHots = new ArrayList<>();
        for(GkinformationGkhot gkinformationGkhot:gkinformationGkhots){
            gkHots.add(GkinformationGkhot2GkHot(gkinformationGkhot));
        }
        return gkHots;
    }

    private GkHot GkinformationGkhot2GkHot(GkinformationGkhot gkinformationGkhot){
        if(gkinformationGkhot==null)return null;
        GkHot gkHot = new GkHot();
        gkHot.setTitle(gkinformationGkhot.getHotInformation());
        gkHot.setContent(gkinformationGkhot.getInformationContent());
        gkHot.setSubContent(gkinformationGkhot.getInformationSubContent());
        gkHot.setImage(gkinformationGkhot.getImgUrl());
        gkHot.setHotDate(gkinformationGkhot.getHotdate());
        gkHot.setId(gkinformationGkhot.getId());
        return gkHot;
    }

//    /**
//     * 获取热点摘要列表 四个
//     * @return
//     */
//    List<GkHot> getGkHotList();

}
