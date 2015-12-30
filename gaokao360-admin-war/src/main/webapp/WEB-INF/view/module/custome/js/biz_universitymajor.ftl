<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var courseName = $('#courseName').val();
        var status = $('#status').val();
        var classfyId = $('#classfyId').val();
        var rules = [];
        if (courseName != '' && courseName != null && courseName != undefined) {
            var rule = {
                'field': 'courseName',
                'op': 'eq',
                'data': courseName
            }
            rules.push(rule);
        }
        if (status != '' && status != null && status != undefined) {
            var rule = {
                'field': 'status',
                'op': 'eq',
                'data': status
            }
            rules.push(rule);
        }
        if (classfyId != '' && classfyId != null && classfyId != undefined) {
            var rule = {
                'field': 'classfyId',
                'op': 'eq',
                'data': classfyId
            }
            rules.push(rule);
        }
        return rules;
    }
    function searchLoad() {
        var url = "/admin/${bizSys}/${mainObj}s";

        var rules = buildRules();

        var filters = {
            'groupOp': 'AND',
            "rules": rules
        };

        $("#grid-table").jqGrid('setGridParam', {
            url: url,
            mtype: "POST",
            postData: "filters=" + JSON.stringify(filters),
            page: 1
        }).trigger("reloadGrid");


    }

    $("#search").click(function () {
        searchLoad();

    });

    /*
    *
    * 院校专业信息模块
    *
    * */

    jQuery(function ($) {

        var UI = {
            $addBtn: $('#addBtn')
            , $editBtn: $('#editBtn')
            , $deleteBtn: $('#deleteBtn')
        };
        var typeStr, rowId;

//        添加词条
        UI.$addBtn.on(ace.click_event, function () {
            typeStr = 'add';
            bootbox.dialog({
                title: "添加院校专业信息",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addAndEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
        });
//        修改词条
        UI.$editBtn.on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改院校专业信息",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addAndEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
        });
//        当前行数据
        var rowData = CommonFn.getRowData(rowId);


//        删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//          添加修改方法
        function addAndEditFun() {


        }

//        dialog弹框
        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 专业名称：</label>'
                + '<div class="col-sm-3">'
                + '<select name="" id=""></select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 开设院校名称：</label>'
                + '<div class="col-sm-3">'
                + '<select name="" id=""></select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 专业类型：</label>'
                + '<div class="col-sm-3">'
                + '<select name="" id=""></select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 科类：</label>'
                + '<div class="col-sm-3">'
                + '<select name="" id=""></select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 学制：</label>'
                + '<div class="col-sm-6">'
                + '<input type="text" id="" placeholder="学制不能大于6个字">'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 学历层次：</label>'
                + '<div class="col-sm-3">'
                + '<select name="" id=""></select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 学费：</label>'
                + '<div class="col-sm-6">'
                + '<input type="text" id="" placeholder="学费不能大于10个字">'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 学位：</label>'
                + '<div class="col-sm-3">'
                + '<select name="" id=""></select>'
                + '</div>'
                + '</div>'

                + '</form>'
                + '</div>'
                + '</div>';


    });//$ end


</script>