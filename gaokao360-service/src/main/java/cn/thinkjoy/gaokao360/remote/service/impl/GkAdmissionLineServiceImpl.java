package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.dto.UniversityEnrollingDTO;
import cn.thinkjoy.gaokao360.remote.service.impl.base.BaseCommonService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityEnrollingExService;
import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkAdmissionLine;
import cn.thinkjoy.zgk.remote.IGkAdmissionLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/1/4.
 */
@Service("GkAdmissionLineServiceImpl")
public class GkAdmissionLineServiceImpl extends BaseCommonService implements IGkAdmissionLineService {

    //设置是否加载内容，默认不加载
    private boolean isIgnore=false;
    @Autowired
    IUniversityEnrollingExService universityEnrollingExService;
    /**
     * 获取历年分数线
     * @return
     */
    @Override
    public BizData4Page getGkAdmissionLineList(Map<String, Object> conditions,Integer page,Integer rows) {
        return doPage(conditions,universityEnrollingExService.getDao(),page,rows);
    }

    /**
     * 将父级domain转换重写实现
     * @param conditions
     * @return
     */
    @Override
    protected Object enhanceStateTransition(List conditions) {
        return domain2GkAdmissionLine(conditions);
    }

    /**
     * api需要domainList和admindomainList转换
     * @param universityEnrollingDTOs
     * @return
     */
    private List<GkAdmissionLine> domain2GkAdmissionLine(List<UniversityEnrollingDTO> universityEnrollingDTOs){
        if(universityEnrollingDTOs==null)return null;
        List<GkAdmissionLine> gkAdmissionLines = new ArrayList<>();
        for(UniversityEnrollingDTO universityEnrollingDTO:universityEnrollingDTOs){
            gkAdmissionLines.add(domain2GkAdmissionLine(universityEnrollingDTO));
        }
        return gkAdmissionLines;
    }

    /**
     * api需要domain和admindomain转换
     * @param universityEnrollingDTO
     * @return
     */
    private GkAdmissionLine domain2GkAdmissionLine(UniversityEnrollingDTO universityEnrollingDTO){
        GkAdmissionLine gkAdmissionLine=new GkAdmissionLine();
        gkAdmissionLine.setId(universityEnrollingDTO.getUniversityId());
        gkAdmissionLine.setName(universityEnrollingDTO.getName());
        gkAdmissionLine.setAverageScore(universityEnrollingDTO.getAverageScore());
        gkAdmissionLine.setBatchname(universityEnrollingDTO.getBatchname());
        gkAdmissionLine.setHighestScore(universityEnrollingDTO.getHighestScore());
        gkAdmissionLine.setLowestScore(universityEnrollingDTO.getLowestScore());
        gkAdmissionLine.setProperty(universityEnrollingDTO.getProperty());
        gkAdmissionLine.setTypename(universityEnrollingDTO.getTypename());
        gkAdmissionLine.setYear(universityEnrollingDTO.getYear());
        gkAdmissionLine.setSubjection(universityEnrollingDTO.getSubjection());
        return gkAdmissionLine;
    }

}
