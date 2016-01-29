package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.common.DomainReflex;
import cn.thinkjoy.gaokao360.common.ERRORCODE;
import cn.thinkjoy.gaokao360.domain.Profession;
import cn.thinkjoy.gaokao360.domain.ProfessionDetail;
import cn.thinkjoy.gaokao360.remote.service.impl.base.BaseCommonService;
import cn.thinkjoy.gaokao360.service.common.IProfessionDetailService;
import cn.thinkjoy.gaokao360.service.common.IProfessionService;
import cn.thinkjoy.gaokao360.service.common.IProfessionTypeService;
import cn.thinkjoy.zgk.domain.BizData4Page;
import cn.thinkjoy.zgk.domain.GkProfessionDetail;
import cn.thinkjoy.zgk.domain.GkProfessional;
import cn.thinkjoy.zgk.dto.GkProfessionDTO;
import cn.thinkjoy.zgk.remote.IGkProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2016/1/5.
 */
@Service("GkProfessionalServiceImpl")
public class GkProfessionalServiceImpl extends BaseCommonService implements IGkProfessionalService{

    @Autowired
    private IProfessionDetailService professionDetailService;
    @Autowired
    private IProfessionService professionService;


    @Override
    public BizData4Page getProfessionalList(Map<String, Object> conditions, Integer page, Integer rows) {
        return doPage(conditions,professionService.getDao(),page,rows);
    }

    @Override
    public GkProfessionDTO getProfessionalInfo(Map<String, Object> conditions,Object id) {

        return professional2DTO((Profession) professionService.fetch(id));
    }

    @Override
    public Object getProfessionCategory(Map<String, Object> conditions) {
        return professionService.findCategory(conditions);
    }

    @Override
    protected Object enhanceStateTransition(List conditions) {
        return professional2GkProfessional(map2Professional(conditions));
    }

    private List<GkProfessional> professional2GkProfessional(List<Profession> professions){
        if(professions==null)return null;
        List<GkProfessional> gkProfessionals= new ArrayList<>();
        for(Profession profession:professions){
            gkProfessionals.add(professional2GkProfessional(profession));
        }
        return gkProfessionals;
    }

    private GkProfessional professional2GkProfessional(Profession profession){
        if(profession==null)return null;
        GkProfessional gkProfessional=new GkProfessional();
        gkProfessional.setId(profession.getId());
        gkProfessional.setLastModDate(profession.getLastModDate());
        gkProfessional.setHotDegree(profession.getHotDegree());
        gkProfessional.setProfessionDescription(profession.getProfessionDescription());
        gkProfessional.setProfessionName(profession.getProfessionName());
        gkProfessional.setProfessionShort(profession.getProfessionShort());
        gkProfessional.setSalaryRank(profession.getSalaryRank());
        return gkProfessional;
    }

    private List<GkProfessionDetail> detail2GkDetail(List<ProfessionDetail> professionDetails){
        if(professionDetails==null)return null;
        List<GkProfessionDetail> gkProfessionDetails= new ArrayList<>();
        for(ProfessionDetail professionDetail:professionDetails){
            gkProfessionDetails.add(detail2GkDetail(professionDetail));
        }
        return gkProfessionDetails;
    }

    private GkProfessionDetail detail2GkDetail(ProfessionDetail professionDetail){
        if(professionDetail==null)return null;
        GkProfessionDetail gkProfessionDetail=new GkProfessionDetail();
        gkProfessionDetail.setId(professionDetail.getId());
        gkProfessionDetail.setCareerProspects(professionDetail.getCareerProspects());
        gkProfessionDetail.setIntroduction(professionDetail.getIntroduction());
        gkProfessionDetail.setRelateMajor(professionDetail.getRelateMajor());
        gkProfessionDetail.setVocationalDemand(professionDetail.getVocationalDemand());
        gkProfessionDetail.setWorkContent(professionDetail.getWorkContent());
        return gkProfessionDetail;
    }

    private List<GkProfessionDTO> professional2DTO(List<Profession> professions){
        if(professions==null)return null;
        List<GkProfessionDTO> gkProfessionDTOs= new ArrayList<>();
        for(Profession profession:professions){
            gkProfessionDTOs.add(professional2DTO(profession));
        }
        return gkProfessionDTOs;
    }

    private GkProfessionDTO professional2DTO(Profession profession){
        GkProfessionDTO gkProfessionDTO = new GkProfessionDTO();
        gkProfessionDTO.setGkProfessional(professional2GkProfessional(profession));
        gkProfessionDTO.setGkProfessionDetail(detail2GkDetail(getProfessionDetailById(profession.getId())));
        return gkProfessionDTO;
    }

    private ProfessionDetail getProfessionDetailById(Object id){
        return (ProfessionDetail)professionDetailService.fetch(id);
    }

    private Profession map2Professional(Map<String,Object> map){
        Profession p = new Profession();

        try {
            return (Profession)DomainReflex.getObj(map,p);
        } catch (Exception e) {
            throw new BizException(ERRORCODE.TRANSFORMATION.getCode(),ERRORCODE.TRANSFORMATION.getMessage());
        }
    }
    private List<Profession> map2Professional(List<Map<String,Object>> maps){
        if(maps==null)return null;
        List<Profession> professions= new ArrayList<>();
        for(Map<String,Object> map:maps){
            professions.add(map2Professional(map));
        }
        return professions;
    }
}
