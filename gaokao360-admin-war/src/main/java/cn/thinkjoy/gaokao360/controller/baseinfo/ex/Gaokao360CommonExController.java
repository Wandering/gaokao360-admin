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
import cn.thinkjoy.gaokao360.controller.baseinfo.ProvinceController;
import cn.thinkjoy.gaokao360.service.IProvinceService;
import cn.thinkjoy.gaokao360.service.ISubjectService;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping(value="/admin/gaokao360/ex")
public class Gaokao360CommonExController extends AbstractCommonController {
    @Autowired
    private ServiceMaps serviceMaps;
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ISubjectService subjectService;

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
    @RequestMapping(value="/getContentUrl",method = RequestMethod.POST)
    @ResponseBody
    public String getContentUrl(@RequestParam("content")String content){

        String path = request.getSession().getServletContext().getRealPath("/upload");
        String filename="gk"+ System.currentTimeMillis()+".html";
        String url = "http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile";
        try {
            FileOutputStream outputStream = new FileOutputStream(path+"/"+filename);
            outputStream.write(content.getBytes());
        } catch (Exception e) {
            throw new BizException("",e.getLocalizedMessage());
        }
        FileSystemResource resource = new FileSystemResource(new File(path+"/"+filename));
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
        String st = template.postForObject(url, param, String.class);

        return st;
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
