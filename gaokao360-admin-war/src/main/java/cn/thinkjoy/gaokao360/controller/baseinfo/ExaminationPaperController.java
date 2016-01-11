/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: gaokao360
 * $Id:  ExaminationPaperController.java 2015-12-03 18:35:56 $
 */

package cn.thinkjoy.gaokao360.controller.baseinfo;

import cn.thinkjoy.gaokao360.common.DomainReflex;
import cn.thinkjoy.gaokao360.controller.BaseController;
import cn.thinkjoy.gaokao360.dao.IAdmissionBatchDAO;
import cn.thinkjoy.gaokao360.domain.AdmissionBatch;
import cn.thinkjoy.gaokao360.service.differentiation.IAdmissionBatchService;
import cn.thinkjoy.gaokao360.service.differentiation.IExaminationPaperService;
import cn.thinkjoy.common.domain.view.BizData4Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value="/admin/gaokao360")
public class ExaminationPaperController extends BaseController<IExaminationPaperService> {


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

    @Override
    protected IExaminationPaperService getMainService() {
        return examinationPaperService;
    }

    @Override
    protected String getBizSys() {
        return "gaokao360";
    }

    @Override
    protected String getMainObjName() {
        return "examinationpaper";
    }

    @Override
    protected String getViewTitle() {
        return "密卷管理";
    }

    @Override
    protected String getParentTitle() {
        return "权限管理";
    }

    @Override
    public boolean getEnableDataPerm() {
        return true;
    }
}
