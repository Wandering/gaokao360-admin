package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.domain.ProfessionType;
import cn.thinkjoy.gaokao360.domain.VideoCourse;
import cn.thinkjoy.gaokao360.dto.ProfessionTypeDTO;
import cn.thinkjoy.gaokao360.dto.VideoCourseDTO;
import cn.thinkjoy.gaokao360.dto.VideoSectionDTO;
import cn.thinkjoy.gaokao360.service.differentiation.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.common.ISubjectService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IAdmissionBatchExService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IVideoSectionExService;
import cn.thinkjoy.gaokao360.service.common.ex.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/12/30.
 */
public class QueryoneUtil extends BaseCommonUtil{

    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IAdmissionBatchService admissionBatchService;
    @Autowired
    private IVideoSectionExService videoSectionExService;
    @Autowired
    private IUniversityExService universityExService;
    @Autowired
    private IMajoredCategoryExService majoredCategoryExService;
    @Autowired
    private IMajoredExService majoredExService;

    private String mainObj;

    @Autowired
    private IAdmissionBatchExService admissionBatchExService;

    private static QueryoneUtil instance=null;
    public static QueryoneUtil getInstance(){
        if(instance==null){
            synchronized(QueryoneUtil.class){
                if(instance==null){
                    instance=new QueryoneUtil();
                }
            }
        }
        return instance;
    }
    private QueryoneUtil(){
    }

    public String getMainObj() {
        return mainObj;
    }

    public void setMainObj(String mainObj) {
        this.mainObj = mainObj;
    }

    public Object innerHandleUQueryone(String mainObj,Object id) throws Exception {
        this.setMainObj(mainObj);
        return runMethod(mainObj,id);
    }
    public Object runMethod(String mainObj,Object id) throws Exception {
        return this.getClass().getMethod(mainObj,new Class[]{Object.class}).invoke(this,id);
    }
    /**
     * 下面是各个模块的查询单个方法，请使用mainObj来命名 例如areabatchline，会自动调用，否则会抛异常走默认接口
     */


    public Object university(Object id){
        return getServiceMaps().get("university" + "ex").fetch(id);
    }


    public Object gkheadline(Object id){
        return getServiceMaps().get("gkinformationgkhot").fetch(id);
    }

    public Object majored(Object id){
        return getServiceMaps().get("majored" + "ex").fetch(id);
    }
    public Object majoredcategory(Object id){
        return majoredCategoryExService.fetch(id);
    }
    public Object auditorium(Object id){
        VideoCourse videoCourse=(VideoCourse)getServiceMaps().get("videocourse").fetch(id);
        VideoCourseDTO videoCourseDTO = new VideoCourseDTO();
        try {
            DomainReflex.ObjToDTO(videoCourse, videoCourseDTO);
        } catch (Exception e) {
            throw new BizException("","类型转换异常");
        }
        videoCourseDTO.setVideoSectionDTO((List<VideoSectionDTO>) videoSectionExService.getVideoSectionByCourseId(id));
        return videoCourseDTO;
    }
    public Object gkpsychology(Object id){
        VideoCourse videoCourse=(VideoCourse)getServiceMaps().get("videocourse").fetch(id);
        VideoCourseDTO videoCourseDTO = new VideoCourseDTO();
        try {
            DomainReflex.ObjToDTO(videoCourse, videoCourseDTO);
        } catch (Exception e) {
            throw new BizException("","类型转换异常");
        }
        videoCourseDTO.setVideoSectionDTO((List<VideoSectionDTO>) videoSectionExService.getVideoSectionByCourseId(id));
        return videoCourseDTO;
    }

    public Object professiontype(Object id){
        Long l=Long.valueOf(id.toString());
        ProfessionType p = (ProfessionType)getServiceMaps().get("professiontype").fetch(l);
        Map<String,Object> map = new HashMap<>();
        map.put("pid",l);
        List<ProfessionType> list=getServiceMaps().get("professiontype").queryList(map,"id","asc");
        ProfessionTypeDTO professionTypeDTO = new ProfessionTypeDTO();
        try {
            professionTypeDTO=(ProfessionTypeDTO)DomainReflex.ObjToDTO(p,professionTypeDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        professionTypeDTO.setChilds(list);
        return professionTypeDTO;
    }
}
