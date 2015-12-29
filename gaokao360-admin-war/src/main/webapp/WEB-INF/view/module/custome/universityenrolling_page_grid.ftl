<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form" action="/admin/${bizSys}/${mainObj}s">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="examKeyWord">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selCourses"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selYears"></select>
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




    <div class="row" id="dialogHtml" style="display: block;">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right"> 省份：</label>
                <div class="col-sm-4">
                    <select class="form-control" id="selProvince2"></select>
                    </div>
                </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right"> 院校名称：</label>
                <div class="col-sm-4">
                    <input id="autoSearch" type="text" class="form-control" placeholder="请输入学校查询" />

                    </div>
                </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right"> 年份：</label>
                <div class="col-sm-4">
                    <select class="form-control" id="selYears2"></select>
                    </div>
                </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="examName"> 标题：</label>
                <div class="col-sm-10">
                    <input type="text" id="examName" placeholder="输入真题密卷标题（同一年份，同一课程，真题密卷名称不能重复）" class="form-control" />
                    </div>
                </div>


            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-right" for="expertsIntro">上传文件：</label>
                <div class="col-sm-10">


                    <div id="uploader" class="wu-example">
                        <div class="uploader-tips">(只能上传一个文件,可拖拽文件)</div>
                        <div class="queueList">
                            <div id="dndArea" class="placeholder">
                                <div id="filePicker">点击上传</div>
                                </div>
                            </div>
                        <div class="statusBar" style="display:none;">
                            <div class="progress">
                                <span class="text">0%</span>
                                <span class="percentage"></span>
                                </div><div class="info"></div>
                            <div class="btns">
                                <div class="uploadBtn">开始上传</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <input type="hidden" value="" id="swfUrl">
            </form>
        </div>
    </div>
    
    
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->