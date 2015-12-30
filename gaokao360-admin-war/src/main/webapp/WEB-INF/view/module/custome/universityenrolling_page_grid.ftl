<!-- 增加过滤div + 主体表格 -->
<div class="page-content">
    <!-- 搜索start-->
    <form class="form-inline" role="form" action="/admin/${bizSys}/${mainObj}s">
        <div class="row">
            <div class="form-group col-sm-2">
                <input type="text" class="keywordSearch" placeholder="关键字查询" id="examKeyWord">
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selCourses"></select>
            </div>
            <div class="form-group col-sm-2">
                <select class="form-control" id="selYears"></select>
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
    </div>


    <div class="row" id="dialogHtml" style="display: block;">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right"> 省份：</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="selProvince2"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right"> 院校名称：</label>
                    <div class="col-sm-4">
                        <input id="autoSearch" type="text" class="form-control" placeholder="请输入学校查询"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right"> 年份：</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="selYears2"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right"> 文史类招生：</label>
                    <div class="col-sm-8">
                        <div class="subjectTypeList">
                            <select class="form-control subjectType-wenshi" style="width:50%"></select>
                            <div class="form-group" style="display: none;">
                                <div class="col-sm-10">
                                    计划数：<input type="text" class="input-small" placeholder="计划数"/>
                                    录取数：<input type="text" class="input-small" placeholder="录取数"/>
                                    最高分：<input type="text" class="input-small" placeholder="最高分"/>
                                    最高位次：<input type="text" class="input-small" placeholder="最高位次"/>
                                    <br /><br />
                                    最低分：<input type="text" class="input-small" placeholder="最低分"/>
                                    最低位次：<input type="text" class="input-small" placeholder="最低位次"/>
                                    平均分：<input type="text" class="input-small" placeholder="平均分"/>
                                    平均位次：<input type="text" class="input-small" placeholder="平均位次"/>
                                    <button class="btn btn-minier btn-pink" class="deleteSubjectTypeBtn">删除</button>
                                </div>
                            </div>
                        </div>
                        <div class="">
                            <select class="form-control" style="width:50%" class="subjectType-wenshi"></select>
                            <br/>
                            <div class="form-group">
                                <div class="col-sm-10">
                                    计划数：<input type="text" class="input-small" placeholder="计划数"/>
                                    录取数：<input type="text" class="input-small" placeholder="录取数"/>
                                    最高分：<input type="text" class="input-small" placeholder="最高分"/>
                                    最高位次：<input type="text" class="input-small" placeholder="最高位次"/>
                                    <br /><br />
                                    最低分：<input type="text" class="input-small" placeholder="最低分"/>
                                    最低位次：<input type="text" class="input-small" placeholder="最低位次"/>
                                    平均分：<input type="text" class="input-small" placeholder="平均分"/>
                                    平均位次：<input type="text" class="input-small" placeholder="平均位次"/>
                                    <button class="btn btn-minier btn-pink" class="deleteSubjectTypeBtn">删除</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-sm btn-primary" id="subjectTypeBtn1">增加
                        </button>
                    </div>
                </div>
                <div id="subjectTypeForm1"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right"> 理工类招生：</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="subjectType-ligong"></select>
                    </div>
                    <div class="col-sm-4">
                        <button class="btn btn-sm btn-primary" id="subjectTypeBtn2">增加
                        </button>
                    </div>
                </div>
                <div class="form-group" id="subjectTypeForm1"></div>
            </form>
        </div>
    </div>


<#include 'page_grid.ftl'>
</div><!-- /.page-content -->
