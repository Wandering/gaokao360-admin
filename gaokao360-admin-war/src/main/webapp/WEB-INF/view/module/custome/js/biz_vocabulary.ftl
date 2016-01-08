<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var title = $('#title').val();
        var status = $('#status').val();
        var classfyId = $('#classfyId').val();
        var rules = [];
        if (title != '' && title != null && title != undefined) {
            var rule = {
                'field': 'title',
                'op': 'lk',
                'data': title
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


    $("#search").click(function () {
        searchLoad();

    });
    /*
    *
    * 高考词条模块
    *
    * */

    jQuery(function ($) {
        var UI = {
            $addBtn: $('#addBtn')
            , $editBtn: $('#editBtn')
            , $deleteBtn: $('#deleteBtn')
            , $province: $('#voaProvince')
            , $voaTitle: $('#voaTitle')
            , $voaSummary: $('#voaSummary')
            , $voaContent: $('#voaContent')
        };
        var typeStr, rowId;
        var provinceData = CommonFn.getProvince();
//        添加词条
        UI.$addBtn.on(ace.click_event, function () {
            typeStr = 'add';
            bootbox.dialog({
                title: "添加高考词条",
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
            CommonFn.renderTextarea('#voaContent');
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
                title: "修改高考词条",
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
            // 当前行数据
            var rowData = CommonFn.getRowData(rowId);
            // 富媒体赋值
//            var voaContent = CommonFn.getContentHtml(rowData[0].content).join('');
            $.trim($('#voaTitle').val(rowData[0].title));
            $('#voaSummary').val(rowData[0].summary);
            $('#voaContent').html(CommonFn.getContentHtml(rowData[0].content).join(''));
            $('#voaProvince').find('option[value="'+rowData[0].areaId+'"]').attr('selected','selected');
            CommonFn.renderTextarea('#voaContent');
        });


//        添加和修改方法
        function addAndEditFun() {
            var title = $.trim($('#voaTitle').val())
                    , summary = $('#voaSummary').val()
                    , content = $('#voaContent').html()
                    , areaId = $('#voaProvince').find('option:selected').attr('value');

            if (areaId == '00') {
                CommonFn.tipsDialog('温馨提示', '省份没有选择');
                return false;
            }
            if (title.length == '') {
                CommonFn.tipsDialog('温馨提示', '标题不能为空');
                return false;
            }
            if (title.length > 120) {
                CommonFn.tipsDialog('温馨提示', '标题字数不能大于120字');
                return false;
            }
            if (summary.length == '') {
                CommonFn.tipsDialog('温馨提示', '摘要不能为空');
                return false;
            }
            if (summary.length == '') {
                CommonFn.tipsDialog('温馨提示', '摘要不能为空');
                return false;
            }
            var voaContentHtml = CommonFn.getTextareaData(content).join(''),
                    voaContentUrl = CommonFn.getTextareaUrlData(voaContentHtml),
                    htmlUrl = voaContentUrl[0],
                    htmlId = voaContentUrl[1],
                    voaData = {
                        oper: typeStr
                        , htmlId: htmlId
                        , title: title
                        , summary: summary
                        , content: htmlUrl
                        , areaId: areaId
                        , categoryId: 2//必填字段
                        , sortId: 0//词条排序字段
                    };
            if (typeStr == 'edit') {
                voaData.id = rowId;
            }
            CommonFn.getData('/admin/${bizSys}/commonsave/${mainObj}', 'post', voaData, function (res) {
                console.info(res);
                searchLoad();
            })


        }//添加修改 end

        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//        dialog弹框
        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right"> 选择省份：</label>'
                + '<div class="col-sm-3">'
                + '<select class="form-control" id="voaProvince">';
        dialogHtml += provinceData
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="voaTitle"> 标题：</label>'
                + '<div class="col-sm-6">'
                + '<input type="text" id="voaTitle" placeholder="请输入高考词条标题" class="col-sm-6"/>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="hotContent"> 摘要：</label>'
                + '<div class="col-sm-10">'
                + '<textarea id="voaSummary" cols="30" rows="4" class="col-sm-10" placeholder="摘要字数限制不能大于120字"></textarea>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="hotContent"> 内容：</label>'
                + '<div class="col-sm-10">'
                + '<div id="voaContent" class="wysiwyg-editor"></div>'
                + '</div>'
                + '</div>'

                + '</div>'
                + '</form>'
                + '</div>'
                + '</div>';


    });//$ end
</script>