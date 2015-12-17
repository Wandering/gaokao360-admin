<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var areaId = $('#courseName').val();
        var queryparam = $('#status').val();
        var rules = [];
        if (areaId != '' && areaId != null && areaId != undefined &&areaId!='00') {
            var rule = {
                'field': 'agent.areaId',
                'op': 'eq',
                'data': areaId
            }
            rules.push(rule);
        }
        if (queryparam != '' && queryparam != null && queryparam != undefined) {
            var rule = {
                'field': 'queryparam',
                'op': 'lk',
                'data': queryparam
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
    * 招办电话模块
    *
    * */
    jQuery(function ($) {

        var typeStr;
        var rowId;
        var UI = {
            $agentKeyWord: $('#agentKeyWord'),
            $selMonth: $('#selMonth'),
            $selProvince: $('#selProvince'),
            $search: $('#search')
        };
//       搜索
        UI.$search.click(function () {
            searchLoad();
        });
//     获取省份
        var provinceData = CommonFn.getProvince();
        UI.$selProvince.append(provinceData);

//      添加招办联系电话
        $("#addBtn").on(ace.click_event, function () {
            typeStr = "add";
            bootbox.dialog({
                title: "添加招办联系电话",
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
////      修改高考日程
//        $("#editBtn").on(ace.click_event, function () {
//            typeStr = "edit";
//            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
//            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
//            if (selTrN != 1) {
//                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
//                return false;
//            }
//            bootbox.dialog({
//                title: "修改招办联系电话",
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
//            CommonFn.renderDate('#date-picker');
//            CommonFn.renderTextarea('#hotContent');
//        });

        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  省份：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="selProvince2">';
        dialogHtml += provinceData
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  地区：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" id="areaName" placeholder="请输入地区名称" class="form-control">'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  招办联系电话：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="agentTel" placeholder="请输入招办电话,格式:0917-3262380">'
                + '</div>'
                + '</div>'
                + '</form>'
                + '</div>'
                + '</div>';
        var addEditFun = function () {
            var selProvinceV = $('#selProvince2 option:checked').val();
            var tel = /^((0\d{2,3}-\d{7,8})|(1[35874]\d{9}))$/;
            var agentTel = $.trim($('#agentTel').val());
            var area = $('#areaName').val();
            var areaName = $('#selProvince2 option:checked').html()+area;
            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if (areaName == "") {
                CommonFn.tipsDialog('温馨提示', '请输入招办地区名称');
                return false;
            }
            if (agentTel == "") {
                CommonFn.tipsDialog('温馨提示', '请输入招办联系电话');
                return false;
            }
            if (!tel.test(agentTel)) {
                CommonFn.tipsDialog('温馨提示', '招办电话填写有误');
                return false;
            }
            var addAgentData = {
                oper: typeStr,
                areaId: selProvinceV,
                telphone:agentTel,
                name:areaName,
                adress:areaName
            };

            if (typeStr == 'edit') {
                addExamData.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/gaokao360/ex/commonsave/${mainObj}',
                data: addAgentData,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };//添加修改方法


    });//$结束


</script>