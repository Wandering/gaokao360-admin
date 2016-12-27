<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var areaId = $('#selProvince').val();
        var queryparam = $('#agentKeyWord').val();
        var rules = [];
        if (areaId != '' && areaId != null && areaId != undefined && areaId != '00') {
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



    /*
    *
    * 专家服务模块
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


        var queryExpert = function() {
            var contentArr = [];
            contentArr.push('<option value="00">请选择专家</option>');
            $.ajaxSettings.async = false;
            CommonFn.getData(CommonFn.url.queryExpert, 'GET', {}, function (result) {
                console.log(result)
                for (var i = 0; i < result.bizData.length; i++) {
                    var expertId = result.bizData[i].id;
                    var expertName = result.bizData[i].expertName;
                    contentArr.push('<option value="' + expertId + '">' + expertName + '</option>');
                }
            });
            return contentArr.join('');
        }
        var queryExpertService = function() {
            var contentArr = [];
            contentArr.push('<option value="00">请选择服务类型</option>');
            $.ajaxSettings.async = false;
            CommonFn.getData(CommonFn.url.queryExpertService, 'GET', {}, function (result) {
                console.log(result)
                for (var i = 0; i < result.bizData.length; i++) {
                    var serviceId = result.bizData[i].id;
                    var serviceName = result.bizData[i].name;
                    contentArr.push('<option value="' + serviceId + '">' + serviceName + '</option>');
                }
            });
            return contentArr.join('');
        }
        // 获取专家数据
        var expertData = queryExpert();
        var expertServiceData = queryExpertService();

        // 添加专家服务
        $("#addBtn").on(ace.click_event, function () {
            typeStr = "add";
            bootbox.dialog({
                title: "添加专家服务",
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
        // 修改专家服务
        $("#editBtn").on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改专家服务",
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
            $('#expertId').find('option[value="' + rowData[0].expertId + '"]').attr('selected', 'selected');
            $('#serviceId').find('option[value="' + rowData[0].serviceTypeId + '"]').attr('selected', 'selected');
        });

        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  专家:</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="expertId">';
        dialogHtml += expertData
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  服务:</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="serviceId">';
        dialogHtml += expertServiceData
                + '</select>'
                + '</div>'
                + '</div>'
                + '</form>'
                + '</div>'
                + '</div>';
        var addEditFun = function () {
            var expertId = $('#expertId option:checked').val();
            var serviceId = $('#serviceId option:checked').val();
            if (expertId == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择专家');
                return false;
            }
            if (serviceId == "") {
                CommonFn.tipsDialog('温馨提示', '请选择专家服务');
                return false;
            }
            var addAgentData = {
                oper: typeStr,
                expertId: expertId,
                serviceTypeId: serviceId,
            };

            if (typeStr == 'edit') {
                addAgentData.id = rowId;
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
//      删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');

    });//$结束


</script>
