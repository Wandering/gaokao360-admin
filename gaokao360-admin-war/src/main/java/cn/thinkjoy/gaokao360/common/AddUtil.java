package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.gaokao360.domain.ProfessionType;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.service.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.ISubjectService;
import cn.thinkjoy.gaokao360.service.ex.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import scala.util.parsing.combinator.testing.Str;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
        this.getClass().getMethod(mainObj).invoke(this);
    }

    /**
     * 下面是各个模块的添加方法，请使用mainObj来命名 例如areabatchline，会自动调用，否则会抛异常走默认接口
     */

    public void areabatchline(){
        Map<String,Object> map= new HashMap<>();
        map.put("areaId",getDataMap().get("areaId"));
        getServiceMaps().get("areabatchline").deleteByCondition(map);
    }
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
        getServiceMaps().get("major").insertMap(getDataMap());
        Long lid =(Long)getServiceMaps().get("major").selectMaxId();
        getDataMap().put("id", lid);
        majoredExService.insertMapDetail(getDataMap());
    }
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
            Map<String,Object> dataMap2 = new HashMap<>();
            for(Map map:maps){
                dataMap2.putAll(getDataMap());
                dataMap2.putAll(map);
                getServiceMaps().get("universityenrolling").insertMap(dataMap2);
            }
        }

    }

    public void professiontype(){
        String content = (String)getDataMap().get("content");
        dataMap.put("pid","0");
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
}
