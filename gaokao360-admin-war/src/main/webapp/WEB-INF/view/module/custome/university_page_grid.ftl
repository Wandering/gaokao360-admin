<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form" action="/admin/gaokao360/ex/gkpsychologys">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch col-sm-7" placeholder="关键字查询" id="EduLevelKeyWord">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selProvince"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selEduLevel"></select>
            </div>
            <div class="form-group col-sm-2">
                <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
    </form>
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addBtn"><i class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加院校基本信息
        </button>
        <button class="btn btn-primary" id="editBtn"><i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="deleteBtn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
    <!-- 自定义模态框start-->
    <div class="modal fade widget-box ui-widget " id="edit_answer_modal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:1000px">
            <div class="modal-content">
                <div class="widget-header">
                    <h5 class="widget-title">添加院校基本信息</h5>
                <#--<button type="button" class="close" data-dismiss="modal"><span-->
                <#--aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>-->
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="submitForm">
                        <div class="form-group">
                            <label for="schoolName" class="col-sm-1 control-label">院校名称</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="schoolName" placeholder="院校名称不能超过15个字">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right" for="expertsIntro">院校图片</label>
                            <div class="col-sm-11">

                                <div id="uploader1" class="wu-example">
                                    <div class="uploader-tips">(只能上传一个图片,可拖拽文件,大小小于3M)</div>
                                    <div class="queueList">
                                        <div id="dndArea" class="placeholder">
                                            <div id="uploaderBtn1">点击上传</div>
                                        </div>
                                    </div>
                                    <div class="statusBar" style="display:none;">
                                        <div class="progress">
                                            <span class="text">0%</span>
                                            <span class="percentage"></span>
                                        </div>
                                        <div class="info"></div>
                                        <div class="btns">
                                            <div class="uploadBtn1">上传封面</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" value="" id="imgUrlData">


                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="schoolCode" class="col-sm-2 control-label">院校代码</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="schoolCode"
                                               placeholder="8位数字/符号/英文字母">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="schoolRank" class="col-sm-2 control-label">院校排名</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="schoolRank" placeholder="请输入6位数字">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="schoolStatic" class="col-sm-2 control-label">院校特征</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="schoolStatic"
                                               placeholder="请输入内容不能超过10个字">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="selEduLevel2" class="col-sm-2 control-label">学历层次</label>
                                    <div class="col-sm-7">
                                        <select class="form-control" id="selEduLevel2"></select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="schoolType" class="col-sm-2 control-label">院校类型</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="schoolType"
                                               placeholder="请输入内容不能超过10个字">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="schoolOwn" class="col-sm-2 control-label">院校隶属</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="schoolOwn"
                                               placeholder="请输入内容不能超过10个字">
                                    </div>
                                </div>
                            </div>
                        </div>
                    <#--好长的Dom-->
                        <div class="form-group">
                            <label for="schoolWeb" class="col-sm-1 control-label">院校网址</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="schoolWeb" placeholder="请输入内容不能超过30个字">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="schoolInProvince" class="col-sm-1 control-label">所在省份</label>
                            <div class="col-sm-3">
                                <select class="form-control" id="schoolInProvince2"></select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="schoolAddress" class="col-sm-1 control-label">院校地址</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="3" class="form-control" id="schoolAddress"
                                          placeholder="请输入内容不能超过120个字"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="schoolPic" class="col-sm-1 control-label">联系电话</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="schoolTel"
                                       placeholder="区号+号码，区号以0开头，3位或4位号码由7位或8位数字">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="schoolIntroduce" class="col-sm-1 control-label">院校简介</label>
                            <div class="col-sm-11">
                            <#--<input type="text" class="form-control" id="schoolIntroduce">-->
                                <div id="schoolIntroduce" class="wysiwyg-editor"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="schoolArticle" class="col-sm-1 control-label">院校章程</label>
                            <div class="col-sm-11">
                            <#--<input type="text" class="form-control" id="schoolArticle">-->
                                <div class="wysiwyg-editor" id="schoolArticle"></div>
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