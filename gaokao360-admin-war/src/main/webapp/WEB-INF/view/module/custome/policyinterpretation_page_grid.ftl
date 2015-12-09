<!-- 增加过滤div + 主体表格 -->
<style>
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
                        <option value="1">一级分类</option>
                        <option value="2">专科</option>
                        <option value="3">本科</option>
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

    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="bootbox-close-button close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">添加政策解读</h4></div>
        <div class="modal-body">
            <div class="bootbox-body">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">选择省份：</label>

                                <div class="col-sm-2">
                                    <select class="form-control" id="selProvince">
                                        <option value="00">请选择省份</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right" for="policyInterOne">
                                    政策一级分类：</label>

                                <div class="col-sm-6">
                                    <input type="text" id="policyInterOne" placeholder="政策解读一级分类，限制字数10个字"
                                           class="col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right" for="policyInterTwo">
                                    政策二级分类：</label>

                                <div class="col-sm-6">
                                    <input type="text" id="policyInterTwo" placeholder="政策解读二级分类，限制字数10个字"
                                           class="col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right" for="policyInterDetail">
                                    政策解读详情：</label>
                                <div class="col-xs-6 col-sm-4">
                                    <div id="policyInterDetail" class="wysiwyg-editor" style="width: 740px">
                                        <input type="hidden" name="wysiwyg-value"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer text-center">
            <button data-bb-handler="success" type="button" class="btn btn-sm btn-primary">
                <i class="ace-icon fa fa-check"></i> 提交
            </button>
            <button data-bb-handler="success" type="button" class="btn btn-sm btn-default">
                <i class="ace-icon fa fa-check"></i> 取消
            </button>
        </div>
    </div>


    <!-- 搜索end -->
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->