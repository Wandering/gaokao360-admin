<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-horizontal" role="form" action="/admin/${bizSys}/${mainObj}s">
        <div class="col-xs-12">
            <div class="col-sm-2">
                <div class="form-group">
                    <input type="text" class="keywordSearch" placeholder="关键字查询" id="examKeyWord">
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <select class="form-control" id="selCourses">
                        <option value="">选择课程</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <select class="form-control" id="selYears">
                        <option value="">选择年份</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <select class="form-control" id="selProvince">
                        <option value="">选择省份</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
    <!-- 搜索end -->
    <div class="col-xs-12">
        <div class="form-group">
            <button class="btn btn-purple" id="addHotBtn"><i
                    class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加
            </button>
            <button class="btn btn-primary" id="editHotBtn"><i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
            </button>
            <button class="btn btn-danger" id="deleteHotBtn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除</button>
        </div>
    </div>
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->