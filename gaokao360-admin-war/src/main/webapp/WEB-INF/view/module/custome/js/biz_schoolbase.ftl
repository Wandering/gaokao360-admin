<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var rules = [];
        var schoolKeyWord = $('#schoolKeyWord').val();
        if (schoolKeyWord != ''&&schoolKeyWord!=null&&schoolKeyWord!=undefined) {
            var rule = {
                'field': 'schoolName',
                'op': 'eq',
                'data': schoolKeyWord
            }
            rules.push(rule);
        }
        return rules;
    }



    /*
   *
   * 招办电话模块
   *
   * */
    jQuery(function ($) {

        var typeStr;
        var rowId;
        var UI = {
            $schoolKeyWord: $('#schoolKeyWord'),
            $search: $('#search')
        };
//       搜索
        UI.$search.click(function () {
            searchLoad();
        });

//      添加招办联系电话
        $("#addBtn").on(ace.click_event, function () {

            typeStr = "add";
            bootbox.dialog({
                title: "添加学校基本信息",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
        });
//      修改高考日程
        $("#editBtn").on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改学校基本信息",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
            // 当前行数据
            var rowData = CommonFn.getRowData(rowId);
            $('#schoolCode').val(rowData[0].schoolCode);
            $('#schoolName').val(rowData[0].schoolName);
            $('#areaCode').val(rowData[0].areaCode);
            $('#schoolType').val(rowData[0].schoolType);
        });

        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  院校代码：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" id="schoolCode" placeholder="请输入院校代码" class="form-control">'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  院校名称：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="schoolName" placeholder="请输入院校名称">'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  区域代码：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="areaCode" placeholder="请输入区域代码">'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  学校类型：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="schoolType" placeholder="请输入学校类型">'
                + '</div>'
                + '</div>'

                + '</form>'
                + '</div>'
                + '</div>';
        var addEditFun = function () {
            var schoolCode = $('#schoolCode').val();
            var schoolName = $('#schoolName').val();
            var areaCode = $('#areaCode').val();
            var schoolType = $('#schoolType').val();
            if (schoolCode == "") {
                CommonFn.tipsDialog('温馨提示', '请输入院校代码');
                return false;
            }

            if (schoolName == "") {
                CommonFn.tipsDialog('温馨提示', '请输入院校代码');
                return false;
            }

            if (areaCode == "") {
                CommonFn.tipsDialog('温馨提示', '请输入院校代码');
                return false;
            }

            if (schoolType == "") {
                CommonFn.tipsDialog('温馨提示', '请输入院校代码');
                return false;
            }

            var addSchoolBase = {
                oper: typeStr,
                schoolCode: schoolCode,
                schoolName: schoolName,
                areaCode: areaCode,
                schoolType: schoolType
            };

            if (typeStr == 'edit') {
                addSchoolBase.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/gaokao360/ex/commonsave/${mainObj}',
                data: addSchoolBase,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };//添加修改方法
//      删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');

    });//$结束
</script>