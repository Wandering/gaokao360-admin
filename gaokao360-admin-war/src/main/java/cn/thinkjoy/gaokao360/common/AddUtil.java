package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.domain.Major;
import cn.thinkjoy.gaokao360.domain.ProfessionType;
import cn.thinkjoy.gaokao360.domain.University;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.service.differentiation.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.common.ISubjectService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IAdmissionBatchExService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IVideoSectionExService;
import cn.thinkjoy.gaokao360.service.common.ex.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by admin on 2015/12/30.
 */
@Transactional
@Component
@Scope("prototype")
public class AddUtil extends BaseCommonUtil{

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

    public AddUtil(){
    }
    public void innerHandleAdd(String mainObj, Map dataMap) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.setDataMap(dataMap);

        runMethod(mainObj);
    }
    public void runMethod(String mainObj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.getClass().getMethod(mainObj).invoke(this);
    }

    /**
     * 下面是各个模块的添加方法，请使用mainObj来命名 例如areabatchline，会自动调用，否则会抛异常走默认接口
     */

    public void admissionbatch(){
        admissionBatchExService.insertMap(getDataMap());
    }
    public void gkheadline(){
        getDataMap().put("type", 1);
        getServiceMaps().get("gkinformationgkhot").insertMap(getDataMap());
    }
    public void gkinformationgkhot(){
        getDataMap().put("type", 0);
        getServiceMaps().get("gkinformationgkhot").insertMap(getDataMap());
    }

    public void majored(){
        long lid=majoredExService.insertMajored(getDataMap());
        majoredExService.insertDetail(getDataMap());
    }
//    public void majored(){
//        getServiceMaps().get("major").insertMap(getDataMap());
//        Long lid =(Long)getServiceMaps().get("major").selectMaxId();
//        getDataMap().put("id", lid);
//        majoredExService.insertMapDetail(getDataMap());
//    }
    public void auditorium(){
        getServiceMaps().get("videocourse").insertMap(getDataMap());
        Long lid = (Long)getServiceMaps().get("videocourse").selectMaxId();
        String sectionId=null;
        if(getDataMap().containsKey("videoSectionDTOs")) {
            sectionId = (String)getDataMap().get("videoSectionDTOs");
        }
        if(sectionId!=null){
            JSONArray jsonArray = null;
            jsonArray = JSON.parseArray(sectionId);
            List<HashMap<String,Object>> maps= handleJSONArray(jsonArray);
            for(Map map:maps){
                VideoSection v = new VideoSection();
                try {
                    DomainReflex.getObj(map, v);
                } catch (Exception e) {
                    throw new BizException("","map转换异常");
                }
                v.setCourseId(lid);
                getServiceMaps().get("videosection").insert(v);
            }
        }
    }

    public void gkpsychology(){
        getServiceMaps().get("videocourse").insertMap(getDataMap());
        Long lid = (Long)getServiceMaps().get("videocourse").selectMaxId();
        String sectionId=null;
        if(getDataMap().containsKey("videoSectionDTOs")) {
            sectionId = (String)getDataMap().get("videoSectionDTOs");
        }
        if(sectionId!=null){
            JSONArray jsonArray = null;
            jsonArray = JSON.parseArray(sectionId);
            List<HashMap<String,Object>> maps= super.handleJSONArray(jsonArray);
            for(Map map:maps){
                VideoSection v = new VideoSection();
                try {
                    DomainReflex.getObj(map, v);
                } catch (Exception e) {
                    throw new BizException("","map转换异常");
                }
                v.setCourseId(lid);
                getServiceMaps().get("videosection").insert(v);
            }
        }
    }
    public void university(){
        universityExService.insertUniversity(getDataMap());
    }

    public void majoredcategory(){
        majoredCategoryExService.insertCategory(getDataMap());
    }
    public void universityenrolling(){
        String batchContent=null;
        if(getDataMap().containsKey("batchContent")) {
            batchContent = (String)getDataMap().get("batchContent");
        }else{
            throw new BizException("","参数异常分数信息不能为空");
        }
        if(batchContent!=null){
            JSONArray jsonArray = null;
            jsonArray = JSON.parseArray(batchContent);
            List<HashMap<String,Object>> maps= super.handleJSONArray(jsonArray);
            for(Map map:maps){
                Map<String,Object> dataMap2 = new HashMap<>();
                if("00".equals(map.get("batch"))){
                    continue;
                }
                dataMap2.putAll(getDataMap());
                dataMap2.putAll(map);
                getServiceMaps().get("universityenrolling").insertMap(dataMap2);
            }
        }

    }

    public void professiontype(){
        String content = (String)getDataMap().get("content");
        dataMap.put("isDelete",false);
        getServiceMaps().get("professiontype").insertMap(dataMap);
        Long l=(Long)getServiceMaps().get("professiontype").selectMaxId();
        if(content!=null && !"".equals(content)){
            String[] majoredList=content.split("、");
            for(String str:majoredList){
                ProfessionType professionType = new ProfessionType();
                professionType.setProfessionType(str);
                professionType.setPid(l);
                professionType.setIsDelete(false);
                getServiceMaps().get("professiontype").insert(professionType);
            }
        }
    }

    public void universitymajorenrolling(){
        String batchContent=null;
        if(getDataMap().containsKey("batchContent")) {
            batchContent = (String)getDataMap().get("batchContent");
        }else{
            throw new BizException("","参数异常分数信息不能为空");
        }
        if(batchContent!=null){
            JSONArray jsonArray = null;
            jsonArray = JSON.parseArray(batchContent);
            List<HashMap<String,Object>> maps= super.handleJSONArray(jsonArray);
            for(Map map:maps){
                Map<String,Object> dataMap2 = new HashMap<>();
                dataMap2.putAll(getDataMap());
                dataMap2.putAll(map);
                dataMap2.put("isDelete", 0);
                dataMap2.put("isDelete", 0);
                University university = (University) getServiceMaps().get("university").findOne("id", dataMap2.get("universityId"));
                dataMap2.put("universityName",university.getName());
                Major major = (Major) getServiceMaps().get("major").findOne("id", dataMap2.get("majorId"));
                dataMap2.put("majorName",major.getMajorName());
                getServiceMaps().get("universitymajorenrolling").insertMap(dataMap2);
            }
        }

    }

}
