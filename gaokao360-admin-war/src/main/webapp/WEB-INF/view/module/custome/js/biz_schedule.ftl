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

    /*
    *
    * 高考日程模块
    *
    * */
    jQuery(function ($) {
        var typeStr;
        var rowId;
        var UI = {
            $scheduleKeyWord: $('#scheduleKeyWord'),
            $selMonth: $('#selMonth'),
            $selProvince: $('#selProvince'),
            $search: $('#search'),
            $datePicker: $('#date-picker')
        };
//       获取年月份
        CommonFn.renderDateYear(UI.$datePicker);
//       搜索
        UI.$search.click(function () {
            var data = UI.$datePicker.val().split('-');
            var year = data[0];
            var month = data[1];
            console.info(year, month);
            searchLoad();
        });
//     获取省份
        var provinceData = CommonFn.getProvince();
        UI.$selProvince.append(provinceData);
//     添加高考日程
        $("#addBtn").on(ace.click_event, function () {
            typeStr = "add";
            bootbox.dialog({
                title: "添加高考日程",
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
            CommonFn.renderDateYear('#date-picker2');
            CommonFn.renderTextarea('#hotContent');
        });
//      修改高考日程
//        $("#editBtn").on(ace.click_event, function () {
//            typeStr = "edit";
//            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
//            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
//            if (selTrN != 1) {
//                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
//                return false;
//            }
//            bootbox.dialog({
//                title: "修改高考日程",
//                message: dialogHtml,
//                className: 'my-modal',
//                buttons: {
//                    "success": {
//                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
//                        "className": "btn-sm btn-success",
//                        "callback": addEditFun
//                    },
//                    cancel: {
//                        label: "关闭",
//                        className: "btn-sm"
//                    }
//                }
//            });
//            // 当前行数据
//            var rowData = CommonFn.getRowData(rowId),
//            // 富媒体赋值
//                    infoContet = CommonFn.getContentHtml(rowData[0].informationContent).join('');
//            $('#selProvince').find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
//            $('#hotTitle').val(rowData[0].hotInformation);
//            $('#hotContent').html(infoContet);
//            $('#date-picker').val(rowData[0].hotdate);
//            CommonFn.renderDate('#date-picker2');
//
//
//            CommonFn.renderTextarea('#hotContent');
//        });
        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right"> 省份：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="selProvince2">';
        dialogHtml += provinceData
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="hotContent">高考日程：</label>'
                + '<div class="col-sm-10">'
                + '<div id="hotContent" class="wysiwyg-editor"></div>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="hotContent">' + ' 日期：</label>'
                + '<div class="col-sm-4"><div class="input-group">'
                + '<input class="form-control date-picker" placeholder="高考日程日期" id="date-picker2" type="text">'
                + '<span class="input-group-addon"><i class="fa fa-calendar bigger-110"></i></span>'
                + '</div></div></div>'
                + '</form>'
                + '</div>'
                + '</div>';
        var addEditFun = function () {
            var selProvinceV = $('#selProvince2 option:checked').val();
            var datePickerV = $.trim($('#date-picker2').val());
            var hotContentV = $('#hotContent').html();

            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if (hotContentV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入高考日程内容');
                return false;
            }
            if (hotContentV.length > 120) {
                CommonFn.tipsDialog('温馨提示', '高考日程内容字数不能大于120字');
                return false;
            }
            if (datePickerV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择高考日程日期');
                return false;
            }
            var datePicker = datePickerV.split('-');
            var years = datePicker[0];
            var month = datePicker[1];
            console.info(years, month);
            var addscheduleData = {
                oper: typeStr,
                month: month,
                years: years,
                content: hotContentV,
                areaId: selProvinceV
            };

            if (typeStr == 'edit') {
                addExamData.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/gaokao360/ex/commonsave/${mainObj}',
                data: addscheduleData,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };//添加修改方法
//      删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//      修改
        $("#editBtn").on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改高考热点",
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
            console.log(rowData)
            $('#selProvince2').find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
            $('#hotContent').find('option[value="' + rowData[0].content + '"]').attr('selected', 'selected');
            $('#selYears2').find('option[value="' + rowData[0].years + '"]').attr('selected', 'selected');
            $('#selYears2').find('option[value="' + rowData[0].month + '"]').attr('selected', 'selected');
            $('#examName').val(rowData[0].paperName);
        });




    });//$结束
</script>