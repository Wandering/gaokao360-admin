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


    <#--<div id="uploader-main">-->
        <#--<div id="uploader" class="">-->
            <#--<div id="preview-img" style="display: none;"><a href="javascript:;" target="_blank" id="imgUrl"><img src="" width="50" height="50" /> 查看图片</a></div>-->
            <#--<div id="thelist" class="uploader-list"></div>-->
            <#--<div class="btns">-->
                <#--<div id="picker">选择文件</div>-->
                <#--<button id="ctlBtn" class="btn btn-default">开始上传</button>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->



    <#--<div id="uploader" class="wu-example">-->
        <#--<div class="queueList">-->
            <#--<div id="dndArea" class="placeholder">-->
                <#--<div id="filePicker"></div>-->
                <#--<p>或将照片拖到这里，单次最多可选300张</p>-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="statusBar" style="display:none;">-->
            <#--<div class="progress">-->
                <#--<span class="text">0%</span>-->
                <#--<span class="percentage"></span>-->
            <#--</div><div class="info"></div>-->
            <#--<div class="btns">-->
                <#--<div id="filePicker2"></div><div class="uploadBtn">开始上传</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->





<#include 'page_grid.ftl'>
</div><!-- /.page-content -->