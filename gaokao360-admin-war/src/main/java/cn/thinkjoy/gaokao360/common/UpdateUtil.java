package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.domain.ProfessionType;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.service.differentiation.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.common.ISubjectService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IAdmissionBatchExService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IVideoSectionExService;
import cn.thinkjoy.gaokao360.service.common.ex.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2015/12/30.
 */
public class UpdateUtil extends BaseCommonUtil{

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

    private static UpdateUtil instance=null;
    public static UpdateUtil getInstance(){
        if(instance==null){
            synchronized(UpdateUtil.class){
                if(instance==null){
                    instance=new UpdateUtil();
                }
            }
        }
        return instance;
    }
    private UpdateUtil(){
    }

    public void innerHandleUpdate(String mainObj, Map dataMap) throws Exception {
        this.setDataMap(dataMap);
        runMethod(mainObj);
    }
    public void runMethod(String mainObj) throws Exception {
        this.getClass().getMethod(mainObj).invoke(this);
    }
    /**
     * 下面是各个模块的修改方法，请使用mainObj来命名 例如areabatchline，会自动调用，否则会抛异常走默认接口
     */


    public void gkpsychology(){
        getServiceMaps().get("videocourse").updateMap(dataMap);
        Long lid = (Long)dataMap.get("id");
        String sectionId=null;
        if(dataMap.containsKey("videoSectionDTOs")){
            sectionId = (String)dataMap.get("videoSectionDTOs");
        }
        if(sectionId!=null){
            Map<String,Object> map1=new HashMap<>();
            map1.put("courseId", dataMap.get("id"));
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


    public void auditorium(){
        getServiceMaps().get("videocourse").updateMap(dataMap);
        Long lid = (Long)dataMap.get("id");
        String sectionId=null;
        if(dataMap.containsKey("videoSectionDTOs")){
            sectionId = (String)dataMap.get("videoSectionDTOs");
        }
        if(sectionId!=null){
            Map<String,Object> map1=new HashMap<>();
            map1.put("courseId", dataMap.get("id"));
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


    public void majored(){
        getServiceMaps().get("major").updateMap(dataMap);
        getServiceMaps().get("majorDetail").updateMap(dataMap);
    }


    public void gkheadline(){
        getServiceMaps().get("gkinformationgkhot").updateMap(dataMap);
    }


    public void university(){
        universityExService.updateUniversity(dataMap);
    }


    public void majoredcategory(){
        majoredCategoryExService.updateCategory(dataMap);
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
            Map<String,Object> dataMap2 = new HashMap<>();
            for(Map map:maps){
                dataMap2.putAll(getDataMap());
                dataMap2.putAll(map);
                getServiceMaps().get("universityenrolling").updateMap(dataMap2);
            }
        }

    }

    public void professiontype(){
        String content = (String)getDataMap().get("content");
        dataMap.put("pid","0");
        dataMap.put("isDelete",false);
        getServiceMaps().get("professiontype").updateMap(dataMap);
        Long l=(Long)dataMap.get("id");
        if(content!=null && !"".equals(content)){
            String[] majoredList=content.split("、");
            Map<String,Object> map=null;
            for(String str:majoredList){
                ProfessionType professionType = new ProfessionType();
                professionType.setProfessionType(str);
                professionType.setPid(l);
                professionType.setIsDelete(false);
                map=new HashMap<>();
                map.put("pid",l);
                map.put("name",str);
                if(getServiceMaps().get("professiontype").queryOne(map)!=null) {
                    getServiceMaps().get("professiontype").insert(professionType);
                }
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
            Map<String,Object> dataMap2 = new HashMap<>();
            for(Map map:maps){
                dataMap2.putAll(getDataMap());
                dataMap2.putAll(map);
//                dataMap2.put("isDelete",0);
                getServiceMaps().get("universitymajorenrolling").updateMap(dataMap2);
            }
        }

    }
}
