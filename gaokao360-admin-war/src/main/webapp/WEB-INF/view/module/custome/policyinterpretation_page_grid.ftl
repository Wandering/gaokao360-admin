<!-- 增加过滤div + 主体表格 -->
<style>
    <#--富文本编辑器样式调整-->
    @media (min-width: 768px) {
        .wysiwyg-style2 {
            width: 750px !important;
            resize: none;
        }
    }
    .modal-dialog{
        width: 1000px!important;
    }
</style>
<div class="page-content">
    <!-- 搜索start-->
    <div class="form-horizontal">
        <div class="col-xs-12 no-padding">
            <div class="col-sm-3 no-padding">
                <label class="col-sm-4 control-label text-right">关键字：</label>

                <div class="col-sm-8 no-padding">
                    <input type="text" class="" placeholder="关键字查询" id="keyWord">
                </div>
            </div>
            <div class="col-sm-3">
                <label class="col-sm-5 control-label text-right">学历层次：</label>

                <div class="col-sm-7 no-padding">
                    <select class="form-control" id="eduLevel">
                    </select>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="form-group">
                    <label class="col-sm-4 control-label text-right">省份：</label>

                    <div class="col-sm-8 no-padding">
                        <select class="form-control" id="province"></select>
                    </div>
                </div>
            </div>
            <div class="col-sm-3 text-center">
                <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <div class="col-sm-4">
                        <button class="btn btn-purple btn-sm" id="addPolicyInterpretation">
                            <i class="ace-icon fa fa-cloud-upload bigger-110"></i>
                            添加政策解读
                        </button>
                        <button class="btn btn-primary btn-sm">
                            <i class="ace-icon fa fa-pencil-square-o bigger-110"></i>
                            修改
                        </button>
                        <button class="btn btn-danger btn-sm">
                            <i class="ace-icon fa fa-trash-o bigger-110 "></i>
                            删除
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->