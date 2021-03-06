package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.remote.service.impl.base.BaseCommonService;
import cn.thinkjoy.gaokao360.domain.GkinformationGkhot;
import cn.thinkjoy.gaokao360.service.differentiation.IGkinformationGkhotService;
import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkHot;
import cn.thinkjoy.zgk.remote.IGkHotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/1/4.
 */
@Service("GkHotServiceImpl")
public class GkHotServiceImpl extends BaseCommonService implements IGkHotService {
    @Autowired
    IGkinformationGkhotService gkinformationGkhotService;
    /**
     * 获取热点摘要列表 四个
     * @return
     */
    @Override
    public BizData4Page getGkHotList(Map<String, Object> conditions,Integer page,Integer rows) {
        if(!conditions.containsKey("isIgnore")) {
            this.setIsIgnore(false);
        }
        return doPage(conditions,gkinformationGkhotService,page,rows);
    }

    @Override
    protected Object enhanceStateTransition(List conditions) {
        conditions=GkinformationGkhot2GkHot(conditions);
        return conditions;
    }

    /**
     * 获取详情
     * @return
     */
    @Override
    public GkHot getGkHotInfo(Map<String, Object> conditions,String id){
        this.setIsIgnore(true);
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
        if(this.isIgnore()) {
            gkHot.setContent(gkinformationGkhot.getInformationContent());
        }
        this.setIsIgnore(false);
        gkHot.setSubContent(gkinformationGkhot.getInformationSubContent());
        gkHot.setImage(gkinformationGkhot.getImgUrl());
        gkHot.setHotDate(gkinformationGkhot.getHotdate());
        gkHot.setId(gkinformationGkhot.getId());
        return gkHot;
    }


}
