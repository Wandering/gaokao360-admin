/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExaminationPaperController.java 2015-12-03 18:35:56 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo.ex;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.common.DomainReflex;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.domain.AdmissionBatch;
import cn.thinkjoy.gaokao360.service.differentiation.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.differentiation.IExaminationPaperService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IExaminationPaperExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping(value="/admin/gaokao360/ex")
public class ExaminationPaperExController extends BaseController<IExaminationPaperExService> {

    @Autowired
    private IAdmissionBatchService admissionBatchService;
    @Autowired
    private IExaminationPaperExService examinationPaperExService;
    @Autowired
    private IExaminationPaperService examinationPaperService;

    /**
     * 页面主请求
     * @param request
     * @param response
     * @return  返回菜单数据、表格描述元数据、当前主描述  如本页面为org
     */
    @RequestMapping(value="/examinationpaper")
    public ModelAndView renderMainView(HttpServletRequest request,HttpServletResponse response){


        return doRenderMainView(request, response);
    }

    /**
     * 获取所有的组织信息
     * @return
     */
    @RequestMapping(value="/examinationpapers")
    @ResponseBody
    public BizData4Page findAllExaminationPapers(HttpServletRequest request,HttpServletResponse response){
        return doPage(request, response);
    }
    /**
     *
     * @return
     */
    @RequestMapping(value="/addAdmissionBatch")
    @ResponseBody
    public Object renderMainView(@RequestParam("name")String name){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        admissionBatchService.insertMap(map);
        AdmissionBatch admissionBatch=null;

        try {
           admissionBatch=(AdmissionBatch)admissionBatchService.queryOne(map);
        }catch (Exception e){
            System.out.println("出错了！");
        }
        return admissionBatch;
    }
    /**
     * 获取所有的组织信息 名称、年份、科类不能重复
     * @return
     */
    @RequestMapping(value="/paperIsExist")
    @ResponseBody
    public boolean paperIsExist(@RequestParam("paperName")String paperName,
                                @RequestParam("years")String years,
                                @RequestParam("subjectId")String subjectId){
        Map<String,Object> map = new HashMap<>();
        map.put("paperName",paperName);
        map.put("years",years);
        map.put("subjectId",subjectId);
        return examinationPaperService.queryOne(map)==null;
    }
//    /**
//     * 获取所有的组织信息
//     * @return
//     */
//    @RequestMapping(value="/${mainObj}fetch")
//    @ResponseBody
//    public ExaminationPaperDTO fetch(String id){
//        return (ExaminationPaperDTO)examinationPaperExService.fetch(id);
//    }
    @Override
    protected IExaminationPaperExService getMainService() {
        return examinationPaperExService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360/ex";
    }

    @Override
    protected String getMainObjName() {
        return "examinationpaper";
    }

    @Override
    protected String getViewTitle() {
        return "真题密卷";
    }

    @Override
    protected String getParentTitle() {
        return "数据模块";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
}
