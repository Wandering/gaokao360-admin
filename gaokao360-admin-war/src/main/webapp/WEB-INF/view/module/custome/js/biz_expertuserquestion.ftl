<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var courseName = $('#courseName').val();
        var status = $('#status').val();
        var classfyId = $('#classfyId').val();
        var rules = [];
        if (courseName != ''&&courseName!=null&&courseName!=undefined) {
            var rule = {
                'field': 'courseName',
                'op': 'eq',
                'data': courseName
            }
            rules.push(rule);
        }
        if (status != ''&&status!=null&&status!=undefined) {
            var rule = {
                'field': 'status',
                'op': 'eq',
                'data': status
            }
            rules.push(rule);
        }
        if (classfyId != ''&&classfyId!=null&&classfyId!=undefined) {
            var rule = {
                'field': 'classfyId',
                'op': 'eq',
                'data': classfyId
            }
            rules.push(rule);
        }
        return rules;
    }
    function searchLoad(){
        var url = "/admin/${bizSys}/${mainObj}s";

        var rules = buildRules();

        var filters = {
            'groupOp': 'AND',
            "rules": rules
        };

        $("#grid-table").jqGrid('setGridParam', {url:url,mtype:"POST",postData:"filters="+JSON.stringify(filters),page: 1}).trigger("reloadGrid");


    }

    $("#search").click(function () {
        searchLoad();

    });


    jQuery(function ($) {

        var typeStr;
        var rowId;
        var UI = {
            $agentKeyWord: $('#agentKeyWord'),
            $selMonth: $('#selMonth'),
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

//      添加招办联系电话
        $("#addBtn").on(ace.click_event, function () {
            typeStr = "add";
            bootbox.dialog({
                title: "添加专家问答",
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

            $("#expertId").change(function(){
                var selExpertV = $('#expertId option:checked').val();
                if (selExpertV&&selExpertV!=''){
                    $("#serviceId").empty();
                    $("#serviceId").append(queryServiceTypeByExpert(selExpertV))
                }
            });
            CommonFn.renderTextarea('#userQuestion');
            CommonFn.renderTextarea('#userAnswer');
        });
        $("#editBtn").on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改专家问答",
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

        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  专家：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="expertId">'
                +   queryExpert()
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  用户名称：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" id="userName" placeholder="用户名称" class="form-control">'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  用户提问：</label>'
                + '<div class="col-sm-10">'
                + '<div id="userQuestion" class="wysiwyg-editor"></div>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  用户问题回答：</label>'
                + '<div class="col-sm-10">'
                + '<div id="userAnswer" class="wysiwyg-editor"></div>'
                + '</div>'
                + '</div>'

                + '</form>'
                + '</div>'
                + '</div>';
        var addEditFun = function () {
            var expertId = $('#expertId option:checked').val();
            var userName = $.trim($('#userName').val());
            var userQuestion = $('#userQuestion').html();
            var userAnswer = $('#userAnswer').html();

            if (expertId == "00"){
                CommonFn.tipsDialog('温馨提示', '请选择专家');
                return false;
            }
            if (userName == "") {
                CommonFn.tipsDialog('温馨提示', '请输入用户名');
                return false;
            }
            if (userQuestion == "") {
                CommonFn.tipsDialog('温馨提示', '请输入用户问题');
                return false;
            }
            if (userAnswer == "") {
                CommonFn.tipsDialog('温馨提示', '请输入用户问题回答');
                return false;
            }

            var addExpertUserQuestionData = {
                oper: typeStr,
                expertId: expertId,
                userName: userName,
                userQuestion:userQuestion,
                userAnswer:userAnswer
            };

            if (typeStr == 'edit') {
                addAgentData.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/gaokao360/ex/commonsave/${mainObj}',
                data: addExpertUserQuestionData,
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