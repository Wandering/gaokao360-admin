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
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="submitForm">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="majoredName" class="col-sm-2 control-label">专业名称</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="majoredName"
                                               placeholder="专业名称名称不能超过30个字">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="majoredCode" class="col-sm-2 control-label">专业Code</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="majoredCode"
                                               placeholder="专业名称名称不能超过30个字">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="selMajored2" class="col-sm-2 control-label">学科门类</label>
                                    <div class="col-sm-6">
                                        <select name="" id="selMajored2" class="form-control"></select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="subjectType" class="col-sm-2 control-label">专业门类</label>
                                    <div class="col-sm-6">
                                        <select name="" id="subjectType" class="form-control">
                                            <option value="00">请选择专业门类</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="salaryRank" class="col-sm-2 control-label">薪资排名</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="salaryRank">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="jobsRank" class="col-sm-2 control-label">就业排名</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="jobsRank">
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#--好长的Dom-->


                        <div class="form-group">
                            <label for="sameMajored" class="col-sm-1 control-label">相近专业</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="3" class="form-control" id="sameMajored"
                                          placeholder="请输入内容不能超过120个字"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mainMajored" class="col-sm-1 control-label">主要课程</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="3" class="form-control" id="mainMajored"
                                          placeholder="请输入内容不能超过120个字"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="employDirect" class="col-sm-1 control-label">就业方向</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="3" class="form-control" id="employDirect"
                                          placeholder="请输入内容不能超过120个字"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="excellentStudent" class="col-sm-1 control-label">优秀学长</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="3" class="form-control" id="excellentStudent"
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