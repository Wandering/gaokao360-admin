<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var areaId = $('#areaId').val();
        var hotInformation = $('#hotInformation').val();
//        var hotInformation = "0";
        var rules = [];
        if (areaId != '' && areaId != null && areaId != undefined && areaId != "00") {
            var rule = {
                'field': 'gkhot.areaId',
                'op': 'eq',
                'data': areaId
            };
            rules.push(rule);
        }
        if (hotInformation != '' && hotInformation != null && hotInformation != undefined) {
            var rule = {
                'field': 'gkhot.hotInformation',
                'op': 'lk',
                'data': hotInformation
            };
            rules.push(rule);
        }
//        console.log(rules);
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
    jQuery(function ($) {
        var typeStr;
        var rowId;
        $("#search").click(function () {
            searchLoad();
        });
        // 获取省份数据
        var provinceData = CommonFn.getProvince();
        $('#areaId').html(provinceData);
        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right"> 选择省份：</label>'
                + '<div class="col-sm-3">'
                + '<select class="form-control" id="selProvince">';
        dialogHtml += provinceData
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle"> 标题：</label>'
                + '<div class="col-sm-3">'
                + '<input type="text" id="hotTitle" placeholder="请输入高考热点标题" class="" />'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="hotContent"> 内容：</label>'
                + '<div class="col-sm-10">'
                + '<div id="hotContent" class="wysiwyg-editor"></div>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="hotContent"> 日期：</label>'
                + '<div class="col-sm-4">'
                + '<div class="input-group">'
                + '<input class="form-control date-picker" placeholder="请选择高考热点日期" id="date-picker" type="text" data-date-format="yyyy-mm-dd" />'
                + '<span class="input-group-addon">'
                + '<i class="fa fa-calendar bigger-110"></i>'
                + '</span>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '</form>'
                + '</div>'
                + '</div>';
        // 添加高考热点
        $("#addBtn").on(ace.click_event, function (e) {
            typeStr = "add";
            bootbox.dialog({
                title: "添加高考热点",
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
            CommonFn.renderDate('#date-picker');
            CommonFn.renderTextarea('#hotContent');
        });


        //修改高考热点
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
            var rowData = CommonFn.getRowData(rowId),
            // 富媒体赋值
                    infoContet = CommonFn.getContentHtml(rowData[0].informationContent).join('');
            $('#selProvince').find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
            $('#hotTitle').val(rowData[0].hotInformation);
            $('#hotContent').html(infoContet);
            $('#date-picker').val(rowData[0].hotdate);
            CommonFn.renderDate('#date-picker');
            CommonFn.renderTextarea('#hotContent');
        });
        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
        var addEditFun = function () {
            var selProvinceV = $('#selProvince option:checked').val(),
                    hotTitleV = $.trim($('#hotTitle').val()),
                    hotContentV = $('#hotContent').html(),
                    datePickerV = $.trim($('#date-picker').val());

            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if (hotTitleV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入高考热点标题');
                return false;
            }
            if(hotTitleV.length>30){
                CommonFn.tipsDialog('温馨提示', '请输入高考热点标题字数不能大于30个字');
                return false;
            }
            if (hotContentV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入高考热点内容');
                return false;
            }
            if (datePickerV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择高考热点日期');
                return false;
            }
            var hotContentHtml = CommonFn.getTextareaData(hotContentV).join(''),
                    hotContentUrl = CommonFn.getTextareaUrlData(hotContentHtml),
                    htmlUrl = hotContentUrl[0],
                    htmlId = hotContentUrl[1],
                    infoData = {
                        htmlId: htmlId,
                        areaId: selProvinceV,
                        hotInformation: hotTitleV,
                        informationContent: htmlUrl,
                        hotdate: datePickerV,
                        informationSubContent: '',
                        hotCount: 0,
                        oper: typeStr
                    };
            if (typeStr == 'edit') {
                infoData.id = rowId;
            }
            console.log(infoData);
            $.ajax({
                type: "POST",
                url: '/admin/${bizSys}/commonsave/${mainObj}',
                data: infoData,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        }













    });



</script>
