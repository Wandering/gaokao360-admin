<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="title">
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


    <!-- 搜索end -->
<#include 'page_grid.ftl'>



</div><!-- /.page-content -->