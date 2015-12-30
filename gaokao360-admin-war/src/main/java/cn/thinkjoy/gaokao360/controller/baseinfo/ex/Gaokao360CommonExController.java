/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: basedata
 * $Id:  ClassesController.java 2015-07-29 12:04:59 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.common.managerui.controller.AbstractCommonController;
import cn.thinkjoy.common.managerui.controller.helpers.BaseServiceMaps;
import cn.thinkjoy.common.service.IBaseService;
import cn.thinkjoy.common.utils.ActionEnum;
import cn.thinkjoy.gaokao360.common.DomainReflex;
import cn.thinkjoy.gaokao360.common.ServiceMaps;
import cn.thinkjoy.gaokao360.domain.GkinformationGkhot;
import cn.thinkjoy.gaokao360.domain.PolicyInterpretation;
import cn.thinkjoy.gaokao360.domain.VideoCourse;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.dto.VideoCourseDTO;
import cn.thinkjoy.gaokao360.dto.VideoSectionDTO;
import cn.thinkjoy.gaokao360.service.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.IProvinceService;
import cn.thinkjoy.gaokao360.service.ISubjectService;
import cn.thinkjoy.gaokao360.service.ex.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class Gaokao360CommonExController extends AbstractCommonController {
    @Autowired
    private ServiceMaps serviceMaps;
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IAdmissionBatchService admissionBatchService;
    @Autowired
    private IAdmissionBatchExService admissionBatchExService;
    @Autowired
    private IVideoSectionExService videoSectionExService;
    @Autowired
    private IUniversityExService universityExService;
    @Autowired
    private IMajoredCategoryExService majoredCategoryExService;
    @Autowired
    private IMajoredExService majoredExService;


    @Override
    protected void innerHandleDel(String mainObj, Map dataMap) {
        if("policyinterpretation".equals(mainObj)){
            PolicyInterpretation policyInterpretation = (PolicyInterpretation)serviceMaps.get(mainObj).fetch(dataMap.get("id"));
            if(policyInterpretation!=null && policyInterpretation.getHtmlId()!=null) {
                delFileUrl(policyInterpretation.getHtmlId());
            }

        }else if("gkinformationgkhot".equals(mainObj)){
            GkinformationGkhot gkinformationGkhot =(GkinformationGkhot)serviceMaps.get(mainObj).fetch(dataMap.get("id"));
            if(gkinformationGkhot!=null && gkinformationGkhot.getHtmlId()!=null) {
                delFileUrl(gkinformationGkhot.getHtmlId());
            }
        }
        if("auditorium".equals(mainObj)||"gkPsychology".equals(mainObj)){
            serviceMaps.get("videocourse").delete(dataMap.get("id"));
            Map<String,Object> map=new HashMap<>();
            map.put("courseId",dataMap.get("id"));
            serviceMaps.get("videosection").deleteByCondition(map);
        }else if("gkheadline".equals(mainObj)){
            serviceMaps.get("gkinformationgkhot").delete(dataMap.get("id"));
        }else if("majored".equals(mainObj)){
            serviceMaps.get("major").delete(dataMap.get("id"));
            serviceMaps.get("majorDetail").delete(dataMap.get("id"));
        }else if("university".equals(mainObj)) {
            universityExService.deleteUniversity(dataMap);
        }else if("majoredcategory".equals(mainObj)) {
            majoredCategoryExService.deleteCategory(dataMap);
        }else {
            getServiceMaps().get(mainObj).delete(dataMap.get("id"));
        }

    }

    /**
     * 查询所有的科目
     * @return
     */
    @RequestMapping(value="/getSubject")
    @ResponseBody
    public List getSubject(){
        return  subjectService.findAll();
    }

    /**
     * 查询年份
     * @return
     */
    @RequestMapping(value="/{mainObj}/getYears")
    @ResponseBody
    public List getYears(@PathVariable String mainObj){
        List list = new ArrayList();
        list.add("2015");
        list.add("2014");
        list.add("2013");
        return list;
    }
    /**
     * 查询所有政策一级分类
     * @return
     */
    @RequestMapping(value="/getAdmissionBatch")
    @ResponseBody
    public List getAdmissionBatch(){
        return  admissionBatchService.findAll();
    }


    /**
     * 模板化fetch方法  查询ex单条数据
     * @param mainObj
     * @param id
     * @return
     */
    @RequestMapping(value="/{mainObj}fetch")
    @ResponseBody
    public Object fetch(@PathVariable String mainObj,@RequestParam("id")String id){
        return serviceMaps.get(mainObj+"ex").fetch(id);
    }
    /**
     * 解析html内容
     * @return
     */
    @RequestMapping(value="/getHTMLContent")
    @ResponseBody
    public String getHTMLContent(@RequestParam("htmlurl")String htmlurl){
        String htmlString = "";
        try {
            //生成一个URL对象
            URL url = new URL(htmlurl);
            //打开URL
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //得到输入流，即获得了网页的内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            String line;
            // 读取输入流的数据，并显示
            while ((line = reader.readLine()) != null) {
                htmlString+=line;
            }
        }catch (Exception e){
            throw new BizException("","读取页面失败");
        }
        return  htmlString;
    }

    @RequestMapping(value="/insertAdmissionBatch")
    @ResponseBody
    public String insertAdmissionBatch(){
        String htmlString = "";
        //TODO   支持多对象保存
        Map<String, Object> dataMap = Maps.newHashMap();

        String prop = null;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            dataMap.put(prop, request.getParameter(prop));
        }
        String operValue = request.getParameter("oper");
        if(ActionEnum.ADD.getAction().equals(operValue)) {
            serviceMaps.get("admissionbatch").insertMap(dataMap);
        }

        return  htmlString;
    }

    @Override
    protected void innerHandleAdd(String mainObj, Map dataMap) {
        if("areabatchline".equals(mainObj)){
            Map<String,Object> map= new HashMap<>();
            map.put("areaId",dataMap.get("areaId"));
            serviceMaps.get("areabatchline").deleteByCondition(map);
        }
        if("admissionbatch".equals(mainObj)){
            admissionBatchExService.insertMap(dataMap);
        }else if("gkheadline".equals(mainObj)){
            dataMap.put("type", 1);
            serviceMaps.get("gkinformationgkhot").insertMap(dataMap);
        }else if("gkinformationgkhot".equals(mainObj)){
            dataMap.put("type", 0);
            serviceMaps.get("gkinformationgkhot").insertMap(dataMap);
        }else if("majored".equals(mainObj)){
            serviceMaps.get("major").insertMap(dataMap);
            Long lid =(Long)serviceMaps.get("major").selectMaxId();
            dataMap.put("id",lid);
            majoredExService.insertMapDetail(dataMap);
        }else if("auditorium".equals(mainObj)||"gkPsychology".equals(mainObj)){
            serviceMaps.get("videocourse").insertMap(dataMap);
            Long lid = (Long)serviceMaps.get("videocourse").selectMaxId();
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
                    serviceMaps.get("videosection").insert(v);
                }
            }
        }else if("university".equals(mainObj)){
            universityExService.insertUniversity(dataMap);
        }else if("majoredcategory".equals(mainObj)){
            majoredCategoryExService.insertCategory(dataMap);
        }else{
            super.innerHandleAdd(mainObj, dataMap);
        }

    }

    /**
     * 公共获取单条数据的方法
     * @return
     */
    @RequestMapping(value="/{mainObj}queryone")
    @ResponseBody
    public Object queryOne(@PathVariable String mainObj,@RequestParam("id")String id){
        if("university".equals(mainObj)){
            return serviceMaps.get(mainObj+"ex").fetch(id);
        }else if("majoredcategory".equals(mainObj)){
            return majoredCategoryExService.fetch(id);
        }else if("gkheadline".equals(mainObj)){
            return serviceMaps.get("gkinformationgkhot").fetch(id);
        }else if("majored".equals(mainObj)){
            return serviceMaps.get("majored"+"ex").fetch(id);
        }else if("auditorium".equals(mainObj)||"gkpsychology".equals(mainObj)){
            VideoCourse videoCourse=(VideoCourse)serviceMaps.get("videocourse").fetch(id);
            VideoCourseDTO videoCourseDTO = new VideoCourseDTO();
            try {
                DomainReflex.ObjToDTO(videoCourse, videoCourseDTO);
            } catch (Exception e) {
                throw new BizException("","类型转换异常");
            }
            videoCourseDTO.setVideoSectionDTO((List<VideoSectionDTO>) videoSectionExService.getVideoSectionByCourseId(id));
            return videoCourseDTO;
        }else{
            return serviceMaps.get(mainObj).fetch(id);
        }
    }


    // 将jsonString转化为hashmap
    private HashMap<String, Object> fromJson2Map(String jsonString) {
        HashMap jsonMap = JSON.parseObject(jsonString, HashMap.class);

        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        for(Iterator iter = jsonMap.keySet().iterator(); iter.hasNext();){
            String key = (String)iter.next();
            if(jsonMap.get(key) instanceof JSONArray){
                JSONArray jsonArray = (JSONArray)jsonMap.get(key);
                List list = handleJSONArray(jsonArray);
                resultMap.put(key, list);
            }else{
                resultMap.put(key, jsonMap.get(key));
            }
        }
        return resultMap;
    }
    private  List<HashMap<String, Object>> handleJSONArray(JSONArray jsonArray){
        List list = new ArrayList();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            HashMap map = new HashMap<String, Object>();
            for (Map.Entry entry : jsonObject.entrySet()) {
                if(entry.getValue() instanceof  JSONArray){
                    map.put((String)entry.getKey(), handleJSONArray((JSONArray)entry.getValue()));
                }else{
                    map.put((String)entry.getKey(), entry.getValue());
                }
            }
            list.add(map);
        }
        return list;
    }
    /**
     * 富文本转接接口
     * @return
     */
    @RequestMapping(value="/getContentUrl",method = RequestMethod.POST)
    @ResponseBody
    public String getContentUrl(@RequestParam("content")String content){
        String st =null;
        String path = request.getSession().getServletContext().getRealPath("/upload");
        String filename = "gk" + System.currentTimeMillis() + ".html";
        String url = "http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile";
        try {
            try {
                FileOutputStream outputStream = new FileOutputStream(path + "/" + filename);
                outputStream.write(content.getBytes("UTF-8"));
                outputStream.close();
            } catch (Exception e) {
                throw new BizException("", e.getLocalizedMessage());
            }
            FileSystemResource resource = new FileSystemResource(new File(path + "/" + filename));
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
            RestTemplate template = new RestTemplate();
            //这里大家可以用其他的httpClient均可以
            param.add("file", resource);
            param.add("productCode", "gk360");
            param.add("bizSystem", "gk360");
            param.add("spaceName ", "gk360");
            param.add("userId ", "gk360");
            param.add("dirId ", "0");

            template.getMessageConverters().add(new FastJsonHttpMessageConverter());
            st = template.postForObject(url, param, String.class);
        }finally {
            File file = new File(path + "/" + filename);
            if(file.exists()){
                file.delete();
            }

        }
        return st;
    }


    /**
     * 删除富文本html
     * @param id
     * @return
     */
    public String delFileUrl(Object id){
        String st =null;
        String path = request.getSession().getServletContext().getRealPath("/upload");
        String filename = "gk" + System.currentTimeMillis() + ".html";
        String url = "http://cs-dev.thinkjoy.com.cn/rest/v1/delFile?fileId="+id;
        try {
            RestTemplate template = new RestTemplate();
            //这里大家可以用其他的httpClient均可以

            template.getMessageConverters().add(new FastJsonHttpMessageConverter());
            st = template.getForObject(url, String.class);
        }catch (Exception e){
            logger.error("删除错误");
        }
        return st;
    }
    @Override
    protected void innerHandleUpdate(String mainObj, Map dataMap) {
        if("policyinterpretation".equals(mainObj)){
            PolicyInterpretation policyInterpretation = (PolicyInterpretation)serviceMaps.get(mainObj).fetch(dataMap.get("id"));
            if(policyInterpretation!=null && policyInterpretation.getHtmlId()!=null) {
                delFileUrl(policyInterpretation.getHtmlId());
            }

        }else if("gkinformationgkhot".equals(mainObj)){
            GkinformationGkhot gkinformationGkhot =(GkinformationGkhot)serviceMaps.get(mainObj).fetch(dataMap.get("id"));
            if(gkinformationGkhot!=null && gkinformationGkhot.getHtmlId()!=null) {
                delFileUrl(gkinformationGkhot.getHtmlId());
            }
        }
        if("auditorium".equals(mainObj)||"gkPsychology".equals(mainObj)){
            serviceMaps.get("videocourse").updateMap(dataMap);
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
                    serviceMaps.get("videosection").insert(v);
                }
            }
        }else if("majored".equals(mainObj)){
            serviceMaps.get("major").updateMap(dataMap);
            serviceMaps.get("majorDetail").updateMap(dataMap);
        }else if("gkheadline".equals(mainObj)){
            serviceMaps.get("gkinformationgkhot").updateMap(dataMap);
        }else if("university".equals(mainObj)){
            universityExService.updateUniversity(dataMap);
        }else if("majoredcategory".equals(mainObj)){
            majoredCategoryExService.updateCategory(dataMap);
        }else {
            super.innerHandleUpdate(mainObj, dataMap);
        }
    }


    @Override
    protected BaseServiceMaps getServiceMaps() {
        return serviceMaps;
    }

    @Override
    protected IBaseService getExportService() {
        return null;
    }

    @Override
    public boolean getEnableDataPerm() {
        return false;
    }
}
