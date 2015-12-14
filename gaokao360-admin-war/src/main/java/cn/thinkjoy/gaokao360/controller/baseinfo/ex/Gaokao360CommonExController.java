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
import cn.thinkjoy.gaokao360.common.ServiceMaps;
import cn.thinkjoy.gaokao360.domain.PolicyInterpretation;
import cn.thinkjoy.gaokao360.service.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.IProvinceService;
import cn.thinkjoy.gaokao360.service.ISubjectService;
import cn.thinkjoy.gaokao360.service.ex.IAdmissionBatchExService;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class Gaokao360CommonExController extends AbstractCommonController {
    @Autowired
    private ServiceMaps serviceMaps;
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IAdmissionBatchService admissionBatchService;
    @Autowired
    private IAdmissionBatchExService admissionBatchExService;

    @Override
    protected void innerHandleDel(String mainObj, Map dataMap) {
        getServiceMaps().get(mainObj).delete(dataMap.get("id"));
    }

    /**
     * 查询所有的省份
     * @return
     */
    @RequestMapping(value="/getProvince")
    @ResponseBody
    public List getProvince(){
        return  provinceService.findAll();
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
     * 查询所有的科目
     * @return
     */
    @RequestMapping(value="/getYears")
    @ResponseBody
    public List getYears(){
        List list = new ArrayList();
        list.add("2015");
        list.add("2014");
        list.add("2013");
        return list;
    }
    /**
     * 查询所有的一级学科
     * @return
     */
    @RequestMapping(value="/getAdmissionBatch")
    @ResponseBody
    public List getAdmissionBatch(){
        return  admissionBatchService.findAll();
    }

    /**
     * 查询所有的一级学科
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

    @Override
    protected void innerHandleAdd(String mainObj, Map dataMap) {
        if("admissionbatch".equals(mainObj)){
            admissionBatchExService.insertMap(dataMap);
        }else{
            super.innerHandleAdd(mainObj, dataMap);
        }

    }

    /**
     * 公共获取单挑数据的方法
     * @return
     */
    @RequestMapping(value="/{mainObj}queryone")
    @ResponseBody
    public Object queryOne(@PathVariable String mainObj,@RequestParam("id")String id){
        return  serviceMaps.get(mainObj).fetch(id);
    }
    /**
     * 公共获取单挑数据的方法
     * @return
     */
    @RequestMapping(value="/getVideoSection")
    @ResponseBody
    public Object getVideoSection(@RequestParam("id")String id){
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",id);
        return  serviceMaps.get("videosection").queryList(map,"sectionSort","asc");
    }
    /**
     * 查询所有的科目
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



//    public String delFileUrl(String filename){
//        String st =null;
//        String path = request.getSession().getServletContext().getRealPath("/upload");
//        String filename = "gk" + System.currentTimeMillis() + ".html";
//        String url = "http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile";
//        try {
//            try {
//                FileOutputStream outputStream = new FileOutputStream(path + "/" + filename);
//                outputStream.write(content.getBytes("UTF-8"));
//            } catch (Exception e) {
//                throw new BizException("", e.getLocalizedMessage());
//            }
//            FileSystemResource resource = new FileSystemResource(new File(path + "/" + filename));
//            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
//            RestTemplate template = new RestTemplate();
//            //这里大家可以用其他的httpClient均可以
//            param.add("file", resource);
//            param.add("productCode", "gk360");
//            param.add("bizSystem", "gk360");
//            param.add("spaceName ", "gk360");
//            param.add("userId ", "gk360");
//            param.add("dirId ", "0");
//
//            template.getMessageConverters().add(new FastJsonHttpMessageConverter());
//            st = template.postForObject(url, param, String.class);
//
//        }finally {
//            File file = new File(path + "/" + filename);
//            if(file.exists()){
//                file.delete();
//            }
//        }
//        return st;
//    }
    @Override
    protected void innerHandleUpdate(String mainObj, Map dataMap) {
        if("policyinterpretation".equals(mainObj)){
            PolicyInterpretation policyInterpretation = (PolicyInterpretation)serviceMaps.get(mainObj).fetch(dataMap.get("id"));

        }else if("gkinformationgkhot".equals(mainObj)){
            serviceMaps.get(mainObj).fetch(dataMap.get("id"));
        }
        super.innerHandleUpdate(mainObj, dataMap);
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
