<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="agentKeyWord">
            </div>
            <div class="form-group col-sm-2">
                <div class="input-group">
                    <input class="form-control date-picker" placeholder="高考日程日期" id="date-picker" type="text"
                           data-date-format="yyyy-mm-dd">
                    <span class="input-group-addon"><i class="fa fa-calendar bigger-110"></i></span>
                </div>
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
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
