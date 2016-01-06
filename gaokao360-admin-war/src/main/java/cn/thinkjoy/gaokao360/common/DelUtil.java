package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.domain.GkinformationGkhot;
import cn.thinkjoy.gaokao360.domain.ProfessionType;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.service.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.ISubjectService;
import cn.thinkjoy.gaokao360.service.ex.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/12/30.
 */
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
    private IAdmissionBatchExService admissionBatchExService;

    private static DelUtil instance=null;
    public static DelUtil getInstance(){
        if(instance==null){
            synchronized(DelUtil.class){
                if(instance==null){
                    instance=new DelUtil();
                }
            }
        }
        return instance;
    }
    private DelUtil(){
    }
    public void innerHandleDel(String mainObj, Map dataMap) throws Exception {
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
        getServiceMaps().get("major").delete(dataMap.get("id"));
        getServiceMaps().get("majorDetail").delete(dataMap.get("id"));
    }

    public void university(){
        universityExService.deleteUniversity(dataMap);
    }

    public void majoredcategory(){
        majoredCategoryExService.deleteCategory(dataMap);
    }

    public void professiontype(){
        getServiceMaps().get("professiontype").delete(dataMap.get("id"));
        Map<String,Object> map = new HashMap<>();
        map.put("pid",dataMap.get("id"));
        getServiceMaps().get("professiontype").deleteByCondition(map);
    }
    public void runMethod(String mainObj) throws Exception {
        this.getClass().getMethod(mainObj).invoke(this);
    }

}
