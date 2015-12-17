<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="agentKeyWord">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selMonth">
                    <option value="00">请选择月份</option>
                </select>
            </div>

            <div class="form-group col-sm-2">
                <select class="form-control" id="selProvince">
                </select>
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
            <i class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加高考日程
        </button>
        <button class="btn btn-primary" id="editBtn">
            <i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="deleteBtn">
            <i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->