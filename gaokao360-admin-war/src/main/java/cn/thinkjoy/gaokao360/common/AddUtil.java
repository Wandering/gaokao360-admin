package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.service.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.ISubjectService;
import cn.thinkjoy.gaokao360.service.ex.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import scala.util.parsing.combinator.testing.Str;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/12/30.
 */
public class AddUtil extends BaseCommonUtil{

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

    private static AddUtil instance=null;
    public static AddUtil getInstance(){
        if(instance==null){
            synchronized(AddUtil.class){
                if(instance==null){
                    instance=new AddUtil();
                }
            }
        }
        return instance;
    }
    private AddUtil(){
    }
    public void innerHandleAdd(String mainObj, Map dataMap) throws Exception {
        this.setDataMap(dataMap);
        runMethod(mainObj);
    }
    public void runMethod(String mainObj) throws Exception {
        this.getClass().getMethod(mainObj).invoke(null);
    }

    /**
     * 下面是各个模块的添加方法，请使用mainObj来命名 例如areabatchline，会自动调用，否则会抛异常走默认接口
     */

    public void areabatchline(){
        Map<String,Object> map= new HashMap<>();
        map.put("areaId",dataMap.get("areaId"));
        getServiceMaps().get("areabatchline").deleteByCondition(map);
    }
    public void admissionbatch(){
        admissionBatchExService.insertMap(dataMap);
    }
    public void gkheadline(){
        dataMap.put("type", 1);
        getServiceMaps().get("gkinformationgkhot").insertMap(dataMap);
    }
    public void gkinformationgkhot(){
        dataMap.put("type", 0);
        getServiceMaps().get("gkinformationgkhot").insertMap(dataMap);
    }

    public void majored(){
        getServiceMaps().get("major").insertMap(dataMap);
        Long lid =(Long)getServiceMaps().get("major").selectMaxId();
        dataMap.put("id",lid);
        majoredExService.insertMapDetail(dataMap);
    }
    public void auditorium(){
        getServiceMaps().get("videocourse").insertMap(dataMap);
        Long lid = (Long)getServiceMaps().get("videocourse").selectMaxId();
        String sectionId=null;
        if(dataMap.containsKey("videoSectionDTOs")){
            sectionId = (String)dataMap.get("videoSectionDTOs");
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

    public void gkPsychology(){
        getServiceMaps().get("videocourse").insertMap(dataMap);
        Long lid = (Long)getServiceMaps().get("videocourse").selectMaxId();
        String sectionId=null;
        if(dataMap.containsKey("videoSectionDTOs")){
            sectionId = (String)dataMap.get("videoSectionDTOs");
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
    public void university(){
        universityExService.insertUniversity(dataMap);
    }

    public void majoredcategory(){
        majoredCategoryExService.insertCategory(dataMap);
    }
    public void universityenrolling(){
//        batch:1, //批次
//                universityMajorType:1, //院校类型
//                planEnrollingNumber:5000, //计划数
//                realEnrollingNumber:4000, //录取数
//                highestScore:400, //最高分
//                highestPrecedence:400, //最高位次
//                lowestScore:400, //最低分
//                lowestPrecedence:400,最低位次
//        averageScore:400, //平均位次
//                averagePrecedence:400,//平均分
        String batchContent=null;
        if(dataMap.containsKey("batchContent")){
            batchContent = (String)dataMap.get("batchContent");
        }
        if(batchContent!=null){
            JSONArray jsonArray = null;
            jsonArray = JSON.parseArray(batchContent);
            List<HashMap<String,Object>> maps= handleJSONArray(jsonArray);
            Map<String,Object> dataMap2 = new HashMap<>();
            for(Map map:maps){
                dataMap2.putAll(dataMap);
                dataMap2.putAll(map);
                getServiceMaps().get("universityenrolling").insertMap(dataMap);
            }
        }

    }

    public void professiontype(){
        String content = (String)dataMap.get("content");
        if(content!=null){

        }
        getServiceMaps().get("professiontype").insertMap(dataMap);
    }


}
