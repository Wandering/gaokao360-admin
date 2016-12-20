<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索end -->
    <div class="form-group hr10">
        <button id="importFile" class="btn btn-import">导入
        </button>
        <button id="deleteByDataBtn" class="btn btn-import">删除数据
        </button>
        <button id="deleteByDataBtn" class="btn btn-import"><a href="/temp/taskFile.json" target="_blank">查看导入进度</a>
        </button>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>

</div><!-- /.page-content -->

<link rel="stylesheet" href="${path}/assets/css/jquery-ui.min.css" />
<script src='${path}/assets/js/jquery.min.js'></script>
<script src="${path}/assets/js/jquery-ui.min.js"></script>