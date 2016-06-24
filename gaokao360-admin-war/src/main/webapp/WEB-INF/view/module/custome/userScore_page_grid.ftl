<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <div class="form-inline" role="form">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="用户手机号" id="searchInput">
            </div>
            <div class="form-group col-sm-2">
                <button type="button" class="btn btn-purple btn-sm" id="search">查询<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
        <!-- 搜索end -->
    </div>
    <table border="1" cellpadding="2" cellspacing="0">
        <th>编号</th>
        <th>成绩</th>
        <th>位次</th>
        <th>操作</th>
        <tr>
            <td><div  id="uid"></div></td>
            <td><div  id="score"></div></td>
            <td ><div id="precedence"></div></td>
            <td><button type="button" class="btn btn-purple btn-sm" id="reset">清除<i
                    class="ace-icon fa icon-on-right"></button></td>
        </tr>
    </table>

</div>