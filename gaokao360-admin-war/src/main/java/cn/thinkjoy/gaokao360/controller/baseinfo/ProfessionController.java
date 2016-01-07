/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: zgk
 * $Id:  ProfessionController.java 2015-12-28 18:05:26 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.domain.Profession;
import cn.thinkjoy.gaokao360.service.common.IProfessionDetailService;
import cn.thinkjoy.gaokao360.service.common.IProfessionService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/zgk")
public class ProfessionController extends BaseController<IProfessionService> {


    @Autowired
    private IProfessionService professionService;

    @Autowired
    private IProfessionDetailService professionDetailService;

    /**
     * 页面主请求
     *
     * @param request
     * @param response
     * @return 返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value = "/professionDetail", method = RequestMethod.GET)
    public ModelAndView renderDetailView(HttpServletRequest request, HttpServletResponse response) {
        return doRenderDetailView(request, response);
    }

    @RequestMapping(value = "/profession", method = RequestMethod.GET)
    public ModelAndView renderMainView(HttpServletRequest request, HttpServletResponse response) {
        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     *
     * @return
     */
    @RequestMapping(value = "/professions", method = RequestMethod.GET)
    @ResponseBody
    public BizData4Page findAllProfessions(HttpServletRequest request, HttpServletResponse response) {
        return doPage(request, response);
    }

    @Override
    protected IProfessionService getMainService() {
        return professionService;
    }

    @Override
    protected String getBizSys() {
        return "zgk";
    }

    @Override
    protected String getMainObjName() {
        return "profession";
    }

    @Override
    protected String getViewTitle() {
        return "职业信息";
    }

    @Override
    protected String getParentTitle() {
        return "数据模块";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
    /**
     * 添加职业信息
     *
     * @return
     */
    @RequestMapping(value = "/addProfession", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addProfessions(HttpServletRequest request) {
        Map<String, Object> dataMap = Maps.newHashMap();
        Map<String, String> result = new HashMap<String, String >();
        String prop;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            dataMap.put(prop, request.getParameter(prop));
        }
        Object obj = professionService.findOne("profession_name", dataMap.get("professionName"));
        if (null != obj) {
            result.put("result", "The profession name is already exist!");
            return result;
        }
        obj = professionService.findOne("salary_rank", dataMap.get("salaryRank"));
        if (null != obj) {
            result.put("result", "The salary rank is already exist!");
            return result;
        }
        dataMap.put("idDelete", false);
        if(null != dataMap.get("introduction") && !"".equals(dataMap.get("introduction")))
        {
            String shortStr = String.valueOf(dataMap.get("introduction"));
            if(shortStr.length()>100)
            {
                shortStr = shortStr.substring(0, 100);
            }
            dataMap.put("short", shortStr);
        }
        int count = professionService.insertMap(dataMap);
        if (1 == count) {
            Profession profession = (Profession) professionService.findOne("profession_name", dataMap.get("professionName"));
            dataMap.put("isDelete", false);
            dataMap.put("id", profession.getId());
            professionDetailService.insertMap(dataMap);
        } else {
            result.put("result", "Add error!");
            return result;
        }

        result.put("result", "success");
        return result;
    }


//    class addProfessionDetailThread extends Thread {
//        private Map<String, Object> dataMap;
//        private String path;
//
//        public addProfessionDetailThread(Map<String, Object> dataMap, String path) {
//            this.dataMap = dataMap;
//            this.path = path;
//        }
//
//        @Override
//        public void run() {
//            Profession profession = (Profession) professionService.findOne("profession_name", dataMap.get("professionName"));
//            dataMap.put("isDelete", false);
//            dataMap.put("id", profession.getId());
//            saveContentAsHtml(path, dataMap, "relateMajor");
//            saveContentAsHtml(path, dataMap, "introduction");
//            saveContentAsHtml(path, dataMap, "workContent");
//            saveContentAsHtml(path, dataMap, "vocationalDemand");
//            saveContentAsHtml(path, dataMap, "careerProspects");
//            professionDetailService.insertMap(dataMap);
//        }
//    }
//
//    private void saveContentAsHtml(String path, Map<String, Object> dataMap, String key) {
//        if (null != dataMap.get(key) && !"".equals(dataMap.get(key))) {
//            Map<String, Object> map = WebUtils.saveContentAsHtml(path, String.valueOf(dataMap.get(key)));
//            if (null != map.get("id")) {
//                dataMap.put(key, map.get("id") + "@@" + map.get("fileUrl"));
//            }
//        }
//    }

    /**
     * 添加职业信息
     *
     * @return
     */
    @RequestMapping(value = "/editProfession", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> editProfessions(HttpServletRequest request) {
        Map<String, Object> dataMap = Maps.newHashMap();
        String prop;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            dataMap.put(prop, request.getParameter(prop));
        }
        Map<String, String> result = new HashMap<String, String>();
        List<Profession> obj = professionService.findList("profession_name", dataMap.get("professionName"));
        if ((null != obj && obj.size() > 1) || (null != obj && obj.size() == 1 && !dataMap.get("id").equals(obj.get(0).getId().toString()))) {
            result.put("result", "The profession name is already exist!");
            return result;
        }
        obj = professionService.findList("salary_rank", dataMap.get("salaryRank"));
        if ((null != obj && obj.size() > 1) || (null != obj && obj.size() == 1 && !dataMap.get("id").equals(obj.get(0).getId().toString()))) {
            result.put("result", "The salary rank is already exist!");
            return result;
        }
        dataMap.put("idDelete", false);
        int count = professionService.updateMap(dataMap);
        if (count != 1) {
            result.put("result", "Insert error!");
            return result;

        } else {
            professionDetailService.updateMap(dataMap);
        }
        result.put("result", "success");
        return result;
    }

//    class editProfessionDetailThread extends Thread {
//        private Map<String, Object> dataMap;
//        private String path;
//
//        public editProfessionDetailThread(Map<String, Object> dataMap, String path) {
//            this.dataMap = dataMap;
//            this.path = path;
//        }
//
//        @Override
//        public void run() {
//            ProfessionDetail profession = (ProfessionDetail) professionDetailService.findOne("id", dataMap.get("id"));
//            deleteOriginalFiles(profession);
//            dataMap.put("isDelete", false);
//            dataMap.put("id", profession.getId());
//            saveContentAsHtml(path, dataMap, "relateMajor");
//            saveContentAsHtml(path, dataMap, "introduction");
//            saveContentAsHtml(path, dataMap, "workContent");
//            saveContentAsHtml(path, dataMap, "vocationalDemand");
//            saveContentAsHtml(path, dataMap, "careerProspects");
//            professionDetailService.updateMap(dataMap);
//        }
//    }
//
//    private void deleteOriginalFiles(ProfessionDetail profession) {
//        if (null != profession.getRelateMajor() && profession.getRelateMajor().indexOf("@@") > 0) {
//            String[] urlInfo = profession.getRelateMajor().split("@@");
//            String urlId = urlInfo[0];
//            WebUtils.delFileUrl(urlId);
//        }
//        if (null != profession.getCareerProspects() && profession.getCareerProspects().indexOf("@@") > 0) {
//            String[] urlInfo = profession.getCareerProspects().split("@@");
//            String urlId = urlInfo[0];
//            WebUtils.delFileUrl(urlId);
//        }
//        if (null != profession.getIntroduction() && profession.getIntroduction().indexOf("@@") > 0) {
//            String[] urlInfo = profession.getIntroduction().split("@@");
//            String urlId = urlInfo[0];
//            WebUtils.delFileUrl(urlId);
//        }
//        if (null != profession.getVocationalDemand() && profession.getVocationalDemand().indexOf("@@") > 0) {
//            String[] urlInfo = profession.getVocationalDemand().split("@@");
//            String urlId = urlInfo[0];
//            WebUtils.delFileUrl(urlId);
//        }
//        if (null != profession.getWorkContent() && profession.getWorkContent().indexOf("@@") > 0) {
//            String[] urlInfo = profession.getWorkContent().split("@@");
//            String urlId = urlInfo[0];
//            WebUtils.delFileUrl(urlId);
//        }
//    }

    /**
     * 职业分类
     *
     * @return
     */
    @RequestMapping(value = "/getProfessionCategory", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, String>> getProfessionCategory(HttpServletRequest request)
    {
        Map<String, Object> dataMap = Maps.newHashMap();
        String prop;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            dataMap.put(prop, request.getParameter(prop));
        }
        return professionService.findCategory(dataMap);
    }

    /**
     * 查询职业详情
     *
     * @return
     */
    @RequestMapping(value = "/getProfessionDetail", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> findProfessionDetail(@RequestParam("professionId")int id)
    {
        return professionService.findProfessionDetail(id);
    }

    /**
     * 删除职业信息
     *
     * @return
     */
    @RequestMapping(value = "/deleteProfession", method = RequestMethod.POST)
    @ResponseBody
    public String deleteProfession(@RequestParam("professionId")int id)
    {
        professionService.deleteById(id);
        professionDetailService.deleteById(id);
        return "success";
    }

    @Override
    protected void enhancePageConditions(HttpServletRequest request, Map conditions) {
        String prop;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            prop = names.nextElement();
            conditions.put(prop, request.getParameter(prop));
        }
    }

}
