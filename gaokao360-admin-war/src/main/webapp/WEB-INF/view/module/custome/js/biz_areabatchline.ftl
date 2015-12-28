<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var areaId = $('#areaId').val();
        var queryparam = $('#keywordSearch').val();
        var rules = [];
        if (areaId != '' && areaId != null && areaId != undefined && areaId != '00') {
            var rule = {
                'field': 'line.areaId',
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
    function searchLoad(flag) {
        var url = "/admin/${bizSys}/${mainObj}s";
        var page = $('#grid-table').getGridParam('page'); // current page
        var rows = $('#grid-table').getGridParam('rows'); // rows
        var sidx = $('#grid-table').getGridParam('sidx'); // sidx
        var sord = $('#grid-table').getGridParam('sord'); // sord


        if (page == null || page == "") {
            page = '1';
        }

        if (flag == 1 || typeof flag == "undefined") {
            page = '1';
        }

        if (rows == null || rows == "") {
            rows = '10';
        }

        var rules = buildRules();

        var filters = {
            'groupOp': 'AND',
            "rules": rules
        };

        $("#grid-table").jqGrid('setGridParam', {
            mtype: "POST",
            postData: "filters=" + JSON.stringify(filters),
            page: page,
            rows: rows,
            sidx: sidx,
            sord: sord}).trigger("reloadGrid");
    }
    $("#search").click(function () {
        searchLoad();
    });


    /*
    *
    * 地区批次模块
    *
    * */
    jQuery(function ($) {
        var rowId = '';
        var typeStr = '';
        var UI = {
            $areaId: $('#areaId')
            , $search: $('#search')
            , $addBtn: $('#addBtn')
        };
//        获取省份
        var province = CommonFn.getProvince();
        UI.$areaId.append(province);
//        添加地区批次
        var ue = '';
        UI.$addBtn.on(ace.click_event, function () {
            //            百度编辑器
            typeStr = "add";
            bootbox.dialog({
                title: "添加地区批次线",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i>提交",
                        "className": "btn-sm btn-success",
                        "callback": addEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm",
                        "callback": function () {
                            ue.destroy();
                        }
                    }
                }
            }).on("click", ".bootbox-close-button", function () {
                ue.destroy();
            });
            ue = UE.getEditor('editor');
//            $('#getContent').click(function () {
//                console.info(ue.hasContents());
//                console.info(ue.getContent());
//            });
        });//添加地区批次end


//        删除批次
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//        修改批次
        $("#editBtn").on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改地区批次",
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
                        className: "btn-sm",
                        "callback": function () {
                            ue.destroy();
                        }
                    }
                }
            }).on("click", ".bootbox-close-button", function () {
                ue.destroy();
            });
            // 当前行数据
            var rowData = CommonFn.getRowData(rowId);
            // 富媒体赋值
            var areaVal = CommonFn.getContentHtml(rowData[0].content);
            console.info(areaVal);
            $('#selProvince2').find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
            ue = UE.getEditor('editor');
            ue.addListener("ready", function () {
                // editor准备好之后才可以使用
                ue.execCommand('insertHtml', areaVal);
//                ue.setContent(areaVal)
            });

        });


        var addEditFun = function () {
            var selProvinceV = $('#selProvince2 option:checked').val();
            var content = CommonFn.getTextareaDataTable(ue.getContent()).join('');
            var contentURL = CommonFn.getTextareaUrlData(content);
            var htmlUrl = contentURL[0];
            var htmlId = contentURL[1];


            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if (contentURL.length == "") {
                CommonFn.tipsDialog('温馨提示', '请录入地区招生批次线');
                return false;
            }

            var addAgentData = {
                oper: typeStr,
                areaId: selProvinceV,
                content: htmlUrl,
                htmlId: htmlId
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
            ue.destroy();
        };
        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  省份：</label>'
                + '<div class="col-sm-2">'
                + '<select class="form-control" id="selProvince2">';
        dialogHtml += province
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  地区批次分数线录入：</label>'
                + '<div class="col-sm-10">'
                + '<script id="editor" name="content" type="text/plain"><\/script>'
                + '</div>'
                + '</div>'
                + '</form>'
                + '</div>'
                + '<button onclick="insertHtml()">插入给定的内容</button>'

                + '</div>';
    });//$ end


</script>