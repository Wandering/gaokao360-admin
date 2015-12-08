<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-horizontal" role="form" action="/admin/${bizSys}/ex/${mainObj}s">
        <div class="col-xs-12">
            <div class="col-sm-2">
                <div class="form-group">
                    <input type="text" class="keywordSearch" placeholder="关键字查询" id="keywordSearch">
                </div>
            </div>

            <div class="col-sm-2">
                <div class="form-group">
                    <select class="form-control" id="status">
                        <option value="">按省份查询</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                    </select>
                </div>
            </div>


            <div class="col-sm-4">
                <div class="form-group">
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-purple btn-sm" id="search">
                            搜索
                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!-- 搜索end -->
    <div class="col-xs-12">
        <div class="form-group">
            <button class="btn btn-purple"><i class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加高考热点
            </button>
            <button class="btn btn-primary"><i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
            </button>
            <button class="btn btn-danger"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除</button>
        </div>
    </div>

<#include 'page_grid.ftl'>
</div><!-- /.page-content -->