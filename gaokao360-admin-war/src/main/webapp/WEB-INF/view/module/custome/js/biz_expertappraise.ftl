<script>
    function buildRules() {

        var rules = [];

        return rules;
    }
    <!-- 自定义js请写在这个文件 -->
    jQuery(function ($) {
        var typeStr;
        var rowId;

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
        var queryServiceTypeByExpert = function(expert) {
            var contentArr = [];
            contentArr.push('<option value="00">请选择服务类型</option>');
            $.ajaxSettings.async = false;
            CommonFn.getData(CommonFn.url.queryServiceTypeByExpert, 'GET', {expertId:expert}, function (result) {
                console.log(result)
                for (var i = 0; i < result.bizData.length; i++) {
                    var serviceId = result.bizData[i].id;
                    var serviceName = result.bizData[i].name;
                    contentArr.push('<option value="' + serviceId + '">' + serviceName + '</option>');
                }
            });
            return contentArr.join('');
        }
        // 获取省份数据
        var expertData = queryExpert();

        var dialogHtml = ''


                + '<div class="row">'

                + '<div class="col-xs-12" style="margin-bottom: 20px">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="userName"> 用户昵称：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" id="userName" placeholder="用户昵称" class="form-control" />'
                + '</div>'
                + '</div>'
                + '</div>'

                + '<div class="col-xs-12" style="margin-bottom: 20px">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="selExpert"> 评价对象：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="selExpert">';
                    dialogHtml +=expertData
                + '</select>'
                + '</div>'
                + '</div>'
                + '</div>'

                + '<div class="col-xs-12" style="margin-bottom: 20px">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="selServiceType"> 服务内容：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="selServiceType">'
                 +'<option value="">请选择评价对象</option>  '
                + '</select>'
                + '</div>'
                + '</div>'
                + '</div>'



                + '<div class="col-xs-12" style="margin-bottom: 20px">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="date-picker"> 评价时间：</label>'
                + '<div class="col-sm-4">'
                + '<div class="input-group">'
                + '<input class="form-control date-picker" placeholder="请选择评价时间" id="date-picker" type="text" data-date-format="yyyy-mm-dd" />'
                + '<span class="input-group-addon">'
                + '<i class="fa fa-calendar bigger-110"></i>'
                + '</span>'
                + '</div>'
                + '</div>'
                + '</div>'


                + '<div class="col-xs-12" style="margin-top: 20px">'
                 + '<div class="form-group">'
                 + '<label class="col-sm-2 control-label no-padding-right" for="contentDetail">'
                 + '评价内容：</label>'
                 + '<div class="col-sm-10">'
                 + '<div id="contentDetail" class="wysiwyg-editor" style="width: 740px">'
                 + '</div>'
                 + '</div>'
                 + '</div>'




                + '</div>'

                + '</form>'
                + '</div>'
                + '</div>';
        // 添加高考头条
        $("#addBtn").on(ace.click_event, function (e) {
            typeStr = "add";
            bootbox.dialog({
                title: "添加",
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
            CommonFn.renderTextarea('#contentDetail');

            $("#selExpert").change(function(){
                var selExpertV = $('#selExpert option:checked').val();
                if (selExpertV&&selExpertV!=''){
                    $("#selServiceType").empty();
                    $("#selServiceType").append(queryServiceTypeByExpert(selExpertV))
                }
            });

        });
        var timeToTimestamp = function (stringTime) {
            var timestamp2 = Date.parse(new Date(stringTime));
            timestamp2 = timestamp2 / 1000;
            //2014-07-10 10:21:12的时间戳为：1404958872
            timestamp2+="000"
            return timestamp2;
        }

        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');


        var addEditFun = function () {
            var expertV = $('#selExpert option:checked').val(),
                    serviceTypeV = $('#selExpert option:checked').val(),
                    userNameV = $.trim($('#userName').val()),
                    contentDetailV = $('#contentDetail').html(),
                    datePickerV = $.trim($('#date-picker').val());

            if (userNameV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入用户昵称');
                return false;
            }
            if (expertV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择专家');
                return false;
            }
            if (serviceTypeV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择服务类型');
                return false;
            }
            if (contentDetailV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入评价内容');
                return false;
            }
            if (datePickerV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择时间');
                return false;
            }
            console.log(contentDetailV)

            var infoData = {
                expertId: expertV,
                userName: userNameV,
                serverType: serviceTypeV,
                userComments: contentDetailV,
                isChecked: 2,
                appDate: timeToTimestamp(datePickerV),
                oper: typeStr,
            };
            if (typeStr == 'edit') {
                infoData.id = rowId;
            }
            console.log(infoData);
            $.ajax({
                type: "POST",
                url: '/admin/gaokao360/commonsave/${mainObj}',
                data: infoData,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        }

        var auditPassFun = function (obj) {
            $(obj).on(ace.click_event, function () {
                var rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
                var selTrN = $('tr.ui-state-highlight[role="row"]').length;
                if (selTrN != 1) {
                    CommonFn.tipsDialog('温馨提示', '请选中一行后再审核通过');
                    return false;
                }
                bootbox.dialog({
                    title: "审核",
                    message: "确定将该条信息展示到网站",
                    buttons: {
                        "success": {
                            "label": "<i class='ace-icon fa fa-check'></i> 确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                                $.ajax({
                                    type: "POST",
                                    url: CommonFn.url.auditPass,
                                    data: {
                                        id: rowId
                                    },
                                    success: function (result) {
                                        console.log(result);
                                        if (result.rtnCode == "0000000") {
                                            searchLoad();
                                        }
                                    }
                                });
                            }
                        },
                        cancel: {
                            label: "关闭",
                            className: "btn-sm"
                        }
                    }
                });
            });
        };
        auditPassFun('#examineBtn')

    });
</script>
