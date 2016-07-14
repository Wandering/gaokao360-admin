<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form" action="/admin/${bizSys}/${mainObj}s">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="keywordSearch">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selCourses"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selYears"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selEduLevel"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selProvince"></select>
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
        <button id="importFile" class="btn btn-import">导入
        </button>
        <button id="deleteByDataBtn" class="btn btn-import"><a href="/temp/taskFile.json" target="_blank">查看导入进度</a>
        </button>
    </div>





    <style>

        .subjectTypeDetail{
            display: none;
        }
        .subjectType{
            display: inline-block;
            margin:0 10px 10px 0;
        }
        .line{
            height:1px;
            font-size:0;
            background: #ccc;
            margin:10px 0;
        }
        .ui-front{
            z-index:9999;
        }
    </style>


<#include 'page_grid.ftl'>
</div><!-- /.page-content -->

<link rel="stylesheet" href="${path}/assets/css/jquery-ui.min.css" />
<script src='${path}/assets/js/jquery.min.js'></script>
<script src="${path}/assets/js/jquery-ui.min.js"></script>
