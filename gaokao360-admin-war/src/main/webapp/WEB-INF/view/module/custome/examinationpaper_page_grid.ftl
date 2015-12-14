<!-- 增加过滤div + 主体表格 -->
<style>
    <#--富文本编辑器样式调整-->
    @media (min-width: 768px) {
        .wysiwyg-style2 {
            width: 750px !important;
            resize: none;
        }
    }

    .modal-dialog {
        width: 1000px !important;
    }

    .hide {
        display: none;
    }
</style>
<div class="page-content">
    <!-- 搜索start-->
<#--<form class="form-horizontal" role="form" action="/admin/${bizSys}/${mainObj}s">-->
    <div class="col-xs-12">
        <div class="col-sm-2">
            <div class="form-group">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="examKeyWord">
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">
                <select class="form-control" id="selCourses">
                </select>
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">
                <select class="form-control" id="selYears">
                </select>
            </div>
        </div>
        <div class="col-sm-2">
            <div class="form-group">
                <select class="form-control" id="selProvince">
                </select>
            </div>
        </div>
        <div class="col-sm-3 text-center">
            <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                    class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
        </div>
    </div>
<#--</form>-->
    <!-- 搜索end -->
    <div class="col-xs-12">
        <div class="form-group">
            <button class="btn btn-purple " id="addExamBtn"><i
                    class="ace-icon fa fa-cloud-upload bigger-110"></i>添加
            </button>
            <button class="btn btn-primary" id="editExamHotBtn"><i
                    class="ace-icon fa fa-pencil-square-o bigger-110"></i>修改
            </button>
            <button class="btn btn-danger" id="deleteHotBtn"><i class="ace-icon fa fa-trash-o bigger-110"></i>删除
            </button>
        </div>
    </div>
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->