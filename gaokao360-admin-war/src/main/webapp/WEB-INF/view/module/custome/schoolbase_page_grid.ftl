<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <div class="form-horizontal">


        <div class="col-xs-12">
            <div class="col-sm-2">
                <div class="form-group">
                    <div class="col-sm-2">
                        <input type="text" class="" placeholder="请输入学校名称" id="schoolKeyWord">
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <div class="col-sm-4" style="width:200px;">
                        <button type="button" class="btn btn-purple btn-sm" id="search">
                            搜索
                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group hr10">
            <button class="btn btn-purple" id="addBtn"><i class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加学校基本信息
            </button>
            <button class="btn btn-primary" id="editBtn"><i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
            </button>
            <button class="btn btn-danger" id="deleteBtn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
            </button>
        </div>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->