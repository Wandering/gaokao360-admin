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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by admin on 2015/12/30.
 */
@Transactional
@Component
@Scope("prototype")
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

    public UpdateUtil(){
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
        Long lid = Long.valueOf((String)dataMap.get("id"));
        String sectionId=null;
        if(dataMap.containsKey("videoSectionDTOs")){
            sectionId = (String)dataMap.get("videoSectionDTOs");
        }
        if(sectionId!=null){
            Map<String,Object> map1=new HashMap<>();
            map1.put("courseId", dataMap.get("id"));
            getServiceMaps().get("videosection").deleteByCondition(map1);
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
        Long lid = Long.valueOf((String)dataMap.get("id"));
        String sectionId=null;
        if(dataMap.containsKey("videoSectionDTOs")){
            sectionId = (String)dataMap.get("videoSectionDTOs");
        }
        if(sectionId!=null){
            Map<String,Object> map1=new HashMap<>();
            map1.put("courseId", dataMap.get("id"));;
            getServiceMaps().get("videosection").deleteByCondition(map1);
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
        Long l=Long.valueOf((String)dataMap.get("id"));
        if(content!=null && !"".equals(content)){
            Map<String,Object> map=null;
            String[] majoredList=content.split("、");
            map=new HashMap<>();
            map.put("pid",l);
            StringBuilder sStr=new StringBuilder();
            List<ProfessionType> professionTypes=getServiceMaps().get("professiontype").queryList(map,"id","asc");
            for(String str:majoredList){
                for(ProfessionType professionType:professionTypes){
                    if(professionType.getProfessionType().equals(str)){
                        sStr.append(professionType.getId()+":");
                    }
                }
            }
            String[] notdelStr1=sStr.toString().split(":");
            sStr=new StringBuilder();
            for(ProfessionType professionType:professionTypes){
                    sStr.append(professionType.getId()+":");
            }
            String[] notdelStr2=sStr.toString().split(":");
            List<String> list=compare(notdelStr1,notdelStr2);
            for(String str:list){
                getServiceMaps().get("professiontype").deleteById(str);
            }
            for(String str:majoredList){
                ProfessionType professionType = new ProfessionType();
                professionType.setProfessionType(str);
                professionType.setPid(l);
                professionType.setIsDelete(false);
                map=new HashMap<>();
                map.put("pid",l);
                map.put("professionType", str);
                if(getServiceMaps().get("professiontype").queryOne(map)==null) {
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


    public <T> List<T> compare(T[] t1, T[] t2) {
        List<T> list1 = Arrays.asList(t1);
        List<T> list2 = new ArrayList<T>();
        for (T t : t2) {
            if (!list1.contains(t)) {
                list2.add(t);
            }
        }
        return list2;
    }
}
