<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form" action="/admin/gaokao360/ex/gkpsychologys">
        <div class="row">
            <div class="form-group col-sm-3">
                <input type="text" class="keywordSearch col-sm-8" placeholder="专业基本信息关键字查询" id="majoredKeyWord">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selMajored"></select>
            </div>
            <div class="form-group col-sm-2">
                <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
    </form>
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addBtn"><i class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加专业基本信息
        </button>
        <button class="btn btn-primary" id="editBtn"><i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="deleteBtn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
    <!-- 自定义模态框start-->
    <div class="modal fade widget-box ui-widget " id="majoredModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:1000px">
            <div class="modal-content">
                <div class="widget-header">
                    <h5 class="widget-title">添加专业基本信息</h5>
                <#--<button type="button" class="close" data-dismiss="modal"><span-->
                <#--aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>-->
                    <#--专业代码-->
                    <#--专业类型-->
                    <#--专业类型-->
                    <#--学科门类-->
                    <#--学科门类-->
                    <#--专业门类-->
                    <#--专业门类-->
                    <#--专业名称-->
                    <#--授予学位-->
                    <#--院校简介-->
                    <#--就业方向-->
                    <#--修学年限-->
                    <#--主要课程-->
                    <#--就业率-->
                    <#--平均收入-->
                    <#--工作和专业相关度-->
                    <#--薪资排名-->
                    <#--就业排名-->
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="submitForm">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="majorName" class="col-sm-2 control-label">专业名称</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="majorName"
                                               placeholder="不能超过30个字">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="majorCode" class="col-sm-2 control-label">专业Code</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="majorCode"
                                               placeholder="不能超过30个字">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="employmentRate" class="col-sm-2 control-label">就业率</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="employmentRate"
                                               placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="schoolingDuration" class="col-sm-2 control-label">修学年限</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="schoolingDuration"
                                               placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="degreeOffered" class="col-sm-2 control-label">授予学位</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="degreeOffered"
                                               placeholder="">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="disciplineCategories" class="col-sm-2 control-label">学科门类</label>
                                    <div class="col-sm-6">
                                        <select name="" id="disciplineCategories" class="form-control">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="majorCategory" class="col-sm-2 control-label">专业门类</label>
                                    <div class="col-sm-6">
                                        <select name="" id="majorCategory" class="form-control">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="majorType" class="col-sm-2 control-label">专业门类</label>
                                    <div class="col-sm-6">
                                        <select name="" id="majorType" class="form-control">
                                            <option value="0">普通专业</option>
                                            <option value="1">重点专业</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#--好长的Dom-->



                        <div class="form-group">
                            <label for="offerCourses" class="col-sm-1 control-label">主要课程</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="3" class="form-control" id="offerCourses"
                                          placeholder="请输入内容不能超过120个字"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="specialisation" class="col-sm-1 control-label">就业方向</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="3" class="form-control" id="specialisation"
                                          placeholder="请输入内容不能超过120个字"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="majorIntroduce" class="col-sm-1 control-label">专业简介</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="3" class="form-control" id="majorIntroduce"
                                          placeholder="请输入内容不能超过120个字"></textarea>
                            </div>
                        </div>


                    </form>
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-default " id="cancelBtn" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="submitBtn">确定</button>
                </div>
            </div>

        </div>
    </div>
    <!-- 自定义模态框end-->
</div><!-- /.page-content -->