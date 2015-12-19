<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form" action="/admin/gaokao360/ex/gkpsychologys">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch col-sm-10" placeholder="院校基本信息关键字查询" id="EduLevelKeyWord">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selProvince"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selEduLevel"></select>
            </div>
            <div class="form-group col-sm-2">
                <button type="button" class="btn btn-purple btn-sm" id="search">搜索<i
                        class="ace-icon fa fa-search icon-on-right bigger-110"></i></button>
            </div>
        </div>
    </form>
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addBtn"><i class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加院校基本信息
        </button>
        <button class="btn btn-primary" id="editBtn"><i class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="deleteBtn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
    <!-- 自定义模态框start-->
    <div class="modal fade widget-box ui-widget " id="edit_answer_modal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:500px">
            <div class="modal-content">
                <div class="widget-header">
                    <h5 class="widget-title">回答答案</h5>
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span
                            class="sr-only">Close</span></button>
                </div>
                <div class="modal-body" style="">

                    <form class="form-horizontal" role="form" style="width:100%">

                        <div class="form-group">

                            <div class="col-sm-9">
                                <input type="text" id="editId" placeholder="" class="col-xs-10 col-sm-8"
                                       hidden="hidden"/>
                                <textarea class="form-control" id="expertAnswer.answer" name="answer"
                                          placeholder="请输入答案"
                                          style="margin: 0px -0.015625px 0px 0px; height: 72px; width: 363px;"></textarea>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="answer_submit">确定</button>
                </div>
            </div>

        </div>
    </div>
    <!-- 自定义模态框end-->
</div><!-- /.page-content -->