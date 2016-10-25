<script>
    function buildRules() {

        var rules = [];

        return rules;
    }

    /*
 *
 * 个人简历下载
 *
 * */
    jQuery(function ($) {
        var dialogHtml = ''
                + '<div class="row">'

                + '<div class="col-xs-12" style="margin-bottom: 20px">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="selOrderStatus"> 订单状态：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="selOrderStatus">'
                + '<option value="">请选择订单状态</option>'
                + '<option value="2">电话初步沟通</option>'
                + '<option value="3">预约成功</option>'
                + '<option value="4">面对面沟通</option>'
                + '<option value="5">结束</option>'
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="col-xs-12 showOrhide" style="margin-bottom: 20px" id="meetingAddressDiv">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="meetingAddress"> 预约地址：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" id="meetingAddress" placeholder="预约地址" class="form-control" />'
                + '</div>'
                + '</div>'
                + '</div>'
                + '</div>';
        //      添加招办联系电话


        $("#updateOrderBtn").on(ace.click_event, function () {
            var ids=$("#grid-table").jqGrid("getGridParam","selarrrow");
            if(ids.length==1){
                typeStr = "edit";
                bootbox.dialog({
                    title: "修改",
                    message: dialogHtml,
                    className: 'my-modal',
                    buttons: {
                        "success": {
                            "label": "<i class='ace-icon fa fa-check'></i> 提交",
                            "className": "btn-sm btn-success",
                            "callback": updateOrderFun
                        },
                        cancel: {
                            label: "关闭",
                            className: "btn-sm"
                        }
                    }
                });
            }else{
                CommonFn.tipsDialog('温馨提示', '请选中一行!');
            }
        });
        var updateOrderFun = function() {
            var statusV = $('#selOrderStatus option:checked').val(),
                    meetingAddressV = $.trim($('#meetingAddress').val());

            if (statusV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入用户昵称');
                return false;
            }
            if (statusV==3&&meetingAddressV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择专家');
                return false;
            }
            var rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var infoData = {
                id:rowId,
                orderStatus: statusV,
                meetingAddress: meetingAddressV,
                debug:true,
                oper: typeStr
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

        $(document).on('change',"#selOrderStatus",function(){
            var statusV = $('#selOrderStatus option:checked').val();
            if (statusV==3){
                $("#meetingAddressDiv").removeClass("showOrhide");
            }else {
                $("#meetingAddressDiv").addClass("showOrhide");
            }
        });


    });//$结束
</script>
