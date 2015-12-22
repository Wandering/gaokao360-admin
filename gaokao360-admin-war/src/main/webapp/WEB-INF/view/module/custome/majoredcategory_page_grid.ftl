<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="agentKeyWord">
            </div>

            <div class="form-group col-sm-2">
                <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
        <!-- 搜索end -->
    </form>
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addBtn">
            <i class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加专业门类
        </button>
        <button class="btn btn-primary" id="editBtn">
            <i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="deleteBtn">
            <i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>

    <div class="modal fade widget-box ui-widget " id="majoredCategoryModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:1000px">
            <div class="modal-content">
                <div class="widget-header">
                    <h5 class="widget-title">添加学科门类</h5>
                <#--<button type="button" class="close" data-dismiss="modal"><span-->
                <#--aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>-->
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="submitForm">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="majoredCategoryName" class="col-sm-2 control-label">学科门类</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="majoredCategoryName"
                                               placeholder="学科门类不能超过8个字">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="majoredCategoryType" class="col-sm-1 control-label">专业类别</label>
                            <div class="col-sm-11">
                                <textarea cols="30" rows="4" class="form-control" id="majoredCategoryType"
                                          placeholder="注意:专业类别之间必须要以短号分割(、)"></textarea>
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


<#include 'page_grid.ftl'>
</div><!-- /.page-content -->