package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.domain.AreabatchLine;
import cn.thinkjoy.gaokao360.domain.Schedule;
import cn.thinkjoy.gaokao360.dto.AreabatchLineDTO;
import cn.thinkjoy.gaokao360.remote.service.impl.base.BaseCommonService;
import cn.thinkjoy.gaokao360.service.common.ex.IAreabatchLineExService;
import cn.thinkjoy.gaokao360.service.differentiation.IScheduleService;
import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkAreaBatch;
import cn.thinkjoy.zgk.domain.GkSchedule;
import cn.thinkjoy.zgk.dto.GkScheduleDTO;
import cn.thinkjoy.zgk.remote.IGkAreaBatchService;
import cn.thinkjoy.zgk.remote.IGkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2016/1/5.
 */
@Service("GkAreaBatchServiceImpl")
public class GkAreaBatchServiceImpl extends BaseCommonService implements IGkAreaBatchService {

    @Autowired
    private IAreabatchLineExService areabatchLineExService;
    @Override
    public BizData4Page getGkAreaBatchList(Map<String, Object> conditions, Integer page, Integer rows) {
        return doPage(conditions,areabatchLineExService,page,rows);
    }

    @Override
    public GkAreaBatch getGkAreaBatchInfo(Map<String, Object> conditions,Object areaId) {
        return areaBatch2GkAreaBatch((AreabatchLineDTO)areabatchLineExService.fetch(areaId));
    }


    @Override
    protected Object enhanceStateTransition(List conditions) {
        return areaBatch2GkAreaBatch(conditions);
    }

    private List<GkAreaBatch> areaBatch2GkAreaBatch(List<AreabatchLineDTO> areabatchLineDTOs){
        if(areabatchLineDTOs==null)return null;
        List<GkAreaBatch> gkAreaBatches = new ArrayList<>();
        for(AreabatchLineDTO areabatchLineDTO:areabatchLineDTOs){
            gkAreaBatches.add(areaBatch2GkAreaBatch(areabatchLineDTO));
        }
        return gkAreaBatches;
    }

    private GkAreaBatch areaBatch2GkAreaBatch(AreabatchLineDTO areabatchLineDTO){
        if(areabatchLineDTO==null) return null;
        GkAreaBatch gkAreaBatch= new GkAreaBatch();
        gkAreaBatch.setAreaId(areabatchLineDTO.getAreaId());
        gkAreaBatch.setContent(areabatchLineDTO.getContent());
        gkAreaBatch.setProvince(areabatchLineDTO.getProvince());
        gkAreaBatch.setId(areabatchLineDTO.getId());
        return gkAreaBatch;
    }
}
