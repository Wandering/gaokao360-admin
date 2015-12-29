<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <form class="form-inline" role="form" action="/admin/${bizSys}/${mainObj}s">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="examKeyWord">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selCourses"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selProvince"></select>
            </div>
            <div class="form-group col-sm-2">
                <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
    </form>
    <!-- 搜索end -->
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addBtn"><i
                class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加
        </button>
        <button class="btn btn-primary" id="editBtn"><i
                class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="deleteBtn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
    <!-- 自定义模态框start-->
    <div class="modal fade in widget-box ui-widget  my-modal" id="dialogLayer" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="widget-header">
                    <h5 class="widget-title">添加</h5>
                    <#--<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span-->
                            <#--class="sr-only">Close</span></button>-->
                </div>
                <div class="modal-body">
                    <div class="row" id="dialogLayer">
                        <div class="col-xs-12">
                            <form class="form-horizontal" role="form" id="auditoriumModal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label no-padding-right"> 省份：</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="selProvince2"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label no-padding-right"> 科目类别：</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="selCourses2">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label no-padding-right" for="teacherName"> 主讲老师：</label>
                                    <div class="col-sm-10">
                                        <input type="text" id="teacherName" placeholder="请输入主讲老师" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label no-padding-right" for="expertsIntro"> 专家介绍：</label>
                                    <div class="col-sm-10">
                                        <textarea class="form-control" id="expertsIntro" placeholder="请输入专家介绍"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label no-padding-right" for="sectionTitle"> 视屏名称：</label>
                                    <div class="col-sm-10">
                                        <input type="text" id="sectionTitle" placeholder="请输视屏名称" class="form-control"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-sm-2 control-label no-padding-right" for="expertsIntro"> 视屏封面图片：</label>
                                    <div class="col-sm-10">
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
                                        <div id="imglist"></div>
                                    </div>
                                </div>
                                <input type="hidden" value="" id="imgUrlData">


                                <div class="form-group">
                                    <label class="col-sm-2 control-label no-padding-right" for="expertsIntro"> 视屏上传列表：</label>
                                    <div class="col-sm-10">

                                        <div id="uploader2" class="wu-example">
                                            <#--<div class="uploader-tips">(只能上传一个图片,可拖拽文件,大小小于3M)</div>-->
                                            <div class="queueList">
                                                <div id="dndArea" class="placeholder">
                                                    <div id="uploaderBtn2">点击上传</div>
                                                </div>
                                            </div>
                                            <div class="statusBar" style="display:none;">
                                                <div class="progress">
                                                    <span class="text">0%</span>
                                                    <span class="percentage"></span>
                                                </div>
                                                <div class="info"></div>
                                                <div class="btns">
                                                    <div class="uploadBtn2">上传视频</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="videolist"></div>
                                    </div>
                                </div>
                                <input type="hidden" value="" id="videoData" />
                            </form>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="submitBtn" class="btn btn-primary">确定</button>
                </div>
            </div>

        </div>
    </div>
    <!-- 自定义模态框end-->
    <style>
        .videoLi{
            width:300px;
            float: left;
            height:30px;
            line-height:30px;
        }
    </style>
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->