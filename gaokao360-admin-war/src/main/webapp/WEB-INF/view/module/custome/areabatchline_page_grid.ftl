
<#--引入百度富文本编辑器-->
<script src="${path}/assets/ueditor_1.4.3/ueditor.config.js" charset="utf-8"></script>
<script src="${path}/assets/ueditor_1.4.3/ueditor.all.js" charset="utf-8"></script>
<#--<script src="${path}/assets/ueditor_1.4.3/lang/zh-cn/zh-cn.js" ></script>-->
<!-- 增加过滤div + 主体表格 -->
<style>
    /*#edui1,#edui1_iframeholder{*/
        /*border-radius: 0!important;*/
        /*width: 750px!important;*/
    /*}*/
    .edui-default .edui-editor-toolbarbox{
        box-shadow: 0 0 0!important;
    }
    .edui-default .edui-editor-toolbarboxouter{
        background: #fff;
    }
</style>
<div class="page-content">
    <!-- 搜索start-->

    <form class="form-inline" role="form">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="keywordSearch">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="areaId"></select>
            </div>
            <div class="form-group col-sm-2">
                <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
    </form>
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addBtn"><i class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加地区批次线
        </button>
        <button class="btn btn-primary" id="editBtn"><i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="deleteBtn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
<#include 'page_grid.ftl'>
</div>
    <!-- 搜索end -->


