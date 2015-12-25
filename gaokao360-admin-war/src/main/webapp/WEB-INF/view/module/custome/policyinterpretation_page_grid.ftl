<#--<!-- 增加过滤div + 主体表格 &ndash;&gt;-->
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
    <form class="form-inline" role="form" action="/admin/${bizSys}/${mainObj}s">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="" placeholder="关键字查询" id="keyWord">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="eduLevel"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="province"></select>
            </div>
            <div class="form-group col-sm-2">
                <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
    </form>
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addPolicyInterpretation"><i
                class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加
        </button>
        <button class="btn btn-primary" id="modify-btn"><i
                class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="delete-btn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->