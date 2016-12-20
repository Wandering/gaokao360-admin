package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.service.differentiation.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.common.ISubjectService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IAdmissionBatchExService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IVideoSectionExService;
import cn.thinkjoy.gaokao360.service.common.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2015/12/30.
 */
@Transactional
@Component
@Scope("prototype")
public class DelUtil extends BaseCommonUtil{

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
    @Autowired
    private IProfessionTypeExService professionTypeExService;


    @Autowired
    private IAdmissionBatchExService admissionBatchExService;

    public DelUtil(){
    }
    public void innerHandleDel(String mainObj, Map dataMap) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.setDataMap(dataMap);
        runMethod(mainObj);
    }
    public void gkpsychology(){
        getServiceMaps().get("videocourse").delete(dataMap.get("id"));
        Map<String,Object> map=new HashMap<>();
        map.put("courseId",dataMap.get("id"));
        getServiceMaps().get("videosection").deleteByCondition(map);
    }

    public void auditorium(){
        getServiceMaps().get("videocourse").delete(dataMap.get("id"));
        Map<String,Object> map=new HashMap<>();
        map.put("courseId",dataMap.get("id"));
        getServiceMaps().get("videosection").deleteByCondition(map);
    }

    public void gkheadline(){
        getServiceMaps().get("gkinformationgkhot").delete(dataMap.get("id"));
    }

    public void majored(){
        majoredExService.deleteMajorById(dataMap.get("id"));
        majoredExService.deleteMajorDetailById(dataMap.get("id"));
    }

    public void university(){
        universityExService.deleteUniversity(dataMap);
    }

    public void majoredcategory(){
        majoredCategoryExService.deleteCategory(dataMap);
    }

    public void professiontype(){
        Integer count = professionTypeExService.getOccupy(dataMap.get("id"));
        if(count!=0){
            throw new BizException(ERRORCODE.RESOURCEOCCUPY.getCode(),ERRORCODE.RESOURCEOCCUPY.getMessage());
        }
        getServiceMaps().get("professiontype").delete(dataMap.get("id"));
        Map<String,Object> map = new HashMap<>();
        map.put("pid",dataMap.get("id"));
        getServiceMaps().get("professiontype").deleteByCondition(map);
    }
    public void runMethod(String mainObj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.getClass().getMethod(mainObj).invoke(this);
    }

}
