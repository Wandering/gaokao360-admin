<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-horizontal" role="form" action="/admin/${bizSys}/${mainObj}s">


        <div class="col-xs-12">
            <div class="col-sm-2">
                <div class="form-group">
                    <div class="col-sm-2">
                        <input type="text" class="" placeholder="请输入提问者手机号" id="userPhone">
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="col-sm-4 control-label">问题状态:</label>

                    <div class="col-sm-8">
                        <select class="form-control" id="status">
                            <option value="">全部</option>
                            <option value="2">显示</option>
                            <option value="1">隐藏</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="col-sm-4 control-label">时间:</label>

                    <div class="col-sm-8">
                        <div class="input-daterange input-group" data-date-format="yyyy-mm-dd">
                            <input type="text" class="input-sm form-control" name="start" id="startCommitTime"/>
                                <span class="input-group-addon">
                                    <i class="fa fa-exchange"></i>
                                </span>
                            <input type="text" class="input-sm form-control" name="end" id="endCommitTime"/>
                        </div>
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
                        <#if actions?seq_contains("import")>
                            <button type="button" class="btn btn-purple btn-sm" id="export" style="float:right;">导入</button>
                        </#if>
                        <#if actions?seq_contains("export")>
                            <button type="button" class="btn btn-purple btn-sm" id="export" style="float:right;">导出</button>
                        </#if>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12">

            <div class="col-sm-4">
            </div>



        </div>
    </form>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
    <div class="treeFixed" id="treeFixed-01">
        <div class="widget-header"><span class="ui-jqdialog-title" style="float: left; line-height: 38px;">分配角色</span><a
                class="ui-jqdialog-titlebar-close ui-corner-all" style="right: 0.3em;"><span
                class="ui-icon ui-icon-closethick"></span></a></div>
        <ul id="tree1" class="ztree "></ul>
        <div style="clear:both"></div>
        <div class="EditButton">
            <a id="sData" class="fm-button ui-state-default ui-corner-all fm-button-icon-left btn btn-sm btn-primary"><i
                    class="ace-icon fa fa-check"></i>提交<span class="ui-icon ui-icon-disk" style="display: none;"></span></a>
            <a id="cData" class="fm-button  close-btn ui-state-default ui-corner-all fm-button-icon-left btn btn-sm"><i
                    class="ace-icon fa fa-times"></i>关闭<span class="ui-icon ui-icon-close"
                                                             style="display: none;"></span></a>
        </div>
    </div>

    <!-- 自定义模态框start-->
    <#--<div class="modal fade widget-box ui-widget " id="edit_answer_modal" tabindex="-1" role="dialog"-->
         <#--aria-labelledby="myModalLabel" aria-hidden="true">-->
        <#--<div class="modal-dialog" style="width:500px">-->
            <#--<div class="modal-content">-->
                <#--<div class="widget-header">-->
                    <#--<h5 class="widget-title">回答答案</h5>-->
                    <#--<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span-->
                            <#--class="sr-only">Close</span></button>-->
                <#--</div>-->
                <#--<div class="modal-body" style="">-->

                    <#--<form class="form-horizontal" role="form" style="width:100%">-->

                        <#--<div class="form-group">-->

                            <#--<div class="col-sm-9">-->
                                <#--<input type="text" id="editId" placeholder="" class="col-xs-10 col-sm-8" hidden="hidden"/>-->
                                <#--<textarea class="form-control" id="expertAnswer.answer" name="answer" placeholder="请输入答案"-->
                                          <#--style="margin: 0px -0.015625px 0px 0px; height: 72px; width: 363px;"></textarea>-->
                            <#--</div>-->
                        <#--</div>-->

                    <#--</form>-->
                <#--</div>-->
                <#--<div class="modal-footer">-->
                    <#--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
                    <#--<button type="button" class="btn btn-primary" id="answer_submit">确定</button>-->
                <#--</div>-->
            <#--</div>-->

        <#--</div>-->
    <#--</div>-->
    <!-- 自定义模态框end-->

</div><!-- /.page-content -->