package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.domain.PolicyInterpretation;
import cn.thinkjoy.gaokao360.remote.service.impl.base.BaseCommonService;
import cn.thinkjoy.gaokao360.service.differentiation.IPolicyInterpretationService;
import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkPolicy;
import cn.thinkjoy.zgk.remote.IGkPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/1/4.
 */
@Service("GkPolicyServiceImpl")
public class GkPolicyServiceImpl extends BaseCommonService implements IGkPolicyService {
    @Autowired
    IPolicyInterpretationService policyInterpretationService;
    /**
     * 获取热点摘要列表 四个
     * @return
     */
    @Override
    public BizData4Page getGkPolicyList(Map<String, Object> conditions,Integer page,Integer rows) {
        List<PolicyInterpretation> policyInterpretations = null;
        if(!conditions.containsKey("isIgnore")) {
            this.setIsIgnore(true);
        }
        return doPage(conditions,policyInterpretationService,page,rows);
    }
    /**
     * 获取详情
     * @return
     */
    @Override
    public GkPolicy getGkPolicyInfo(Map<String, Object> conditions,String id){
        this.setIsIgnore(true);
        return policyInterpretation2GkPolicy((PolicyInterpretation) policyInterpretationService.fetch(id));
    }
    @Override
    protected Object enhanceStateTransition(List conditions) {
        conditions=policyInterpretation2GkPolicy(conditions);
        return conditions;
    }



    private List<GkPolicy> policyInterpretation2GkPolicy(List<PolicyInterpretation> policyInterpretations){
        if(policyInterpretations==null)return null;
        List<GkPolicy> gkPolicys = new ArrayList<>();
        for(PolicyInterpretation policyInterpretation:policyInterpretations){
            gkPolicys.add(policyInterpretation2GkPolicy(policyInterpretation));
        }
        return gkPolicys;
    }

    private GkPolicy policyInterpretation2GkPolicy(PolicyInterpretation policyInterpretation){
        if(policyInterpretation==null)return null;
        GkPolicy gkPolicy = new GkPolicy();
        gkPolicy.setId(policyInterpretation.getId());
        gkPolicy.setTitle(policyInterpretation.getTitle());
        gkPolicy.setLastModDate(policyInterpretation.getLastModDate());
        gkPolicy.setSubContent(policyInterpretation.getSubContent());
        if(isIgnore()) {
            gkPolicy.setContent(policyInterpretation.getContent());
            gkPolicy.setLastModDate(policyInterpretation.getLastModDate());
        }
        return gkPolicy;
    }


}
