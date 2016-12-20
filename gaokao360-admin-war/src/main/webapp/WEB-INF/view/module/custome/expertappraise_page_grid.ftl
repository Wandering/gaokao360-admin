<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <#--<form class="form-horizontal" role="form" action="/admin/${bizSys}/${mainObj}s">-->


        <#--<div class="col-xs-12">-->
            <#--<div class="col-sm-2">-->
                <#--<div class="form-group">-->
                    <#--<div class="col-sm-2">-->
                        <#--<input type="text" class="" placeholder="请输入提问者手机号" id="userPhone">-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->

            <#--<div class="col-sm-4">-->
                <#--<div class="form-group">-->
                    <#--<label class="col-sm-4 control-label">问题状态:</label>-->

                    <#--<div class="col-sm-8">-->
                        <#--<select class="form-control" id="status">-->
                            <#--<option value="">全部</option>-->
                            <#--<option value="2">显示</option>-->
                            <#--<option value="1">隐藏</option>-->
                        <#--</select>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->

            <#--<div class="col-sm-4">-->
                <#--<div class="form-group">-->
                    <#--<label class="col-sm-4 control-label">时间:</label>-->

                    <#--<div class="col-sm-8">-->
                        <#--<div class="input-daterange input-group" data-date-format="yyyy-mm-dd">-->
                            <#--<input type="text" class="input-sm form-control" name="start" id="startCommitTime"/>-->
                                <#--<span class="input-group-addon">-->
                                    <#--<i class="fa fa-exchange"></i>-->
                                <#--</span>-->
                            <#--<input type="text" class="input-sm form-control" name="end" id="endCommitTime"/>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->

            <#--<div class="col-sm-4">-->
                <#--<div class="form-group">-->
                    <#--<div class="col-sm-4" style="width:200px;">-->
                        <#--<button type="button" class="btn btn-purple btn-sm" id="search">-->
                            <#--搜索-->
                            <#--<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>-->
                        <#--</button>-->
                        <#--<#if actions?seq_contains("import")>-->
                            <#--<button type="button" class="btn btn-purple btn-sm" id="export" style="float:right;">导入</button>-->
                        <#--</#if>-->
                        <#--<#if actions?seq_contains("export")>-->
                            <#--<button type="button" class="btn btn-purple btn-sm" id="export" style="float:right;">导出</button>-->
                        <#--</#if>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->

        <#--<div class="col-xs-12">-->

            <#--<div class="col-sm-4">-->
            <#--</div>-->



        <#--</div>-->
    <#--</form>-->
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addBtn">
            <i class="ace-icon fa  align-top bigger-125"></i>添加
        </button>
        <button class="btn btn-purple" id="examineBtn">
            <i class="ace-icon fa  align-top bigger-125"></i>审核通过
        </button>
        <button class="btn btn-danger" id="deleteBtn">
            <i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>

</div><!-- /.page-content -->
