<script>
    //    搜索相关代码-start
    function buildRules() {
        var keyWord = $('#keyWord').val();
        var eduLevel = $('#eduLevel').val();
        var province = $('#province').val();
        var rules = [];
        if (keyWord != '' && keyWord != null && keyWord != undefined) {
            var rule = {
                'field': 'queryparam',
                'op': 'lk',
                'data': keyWord
            }
            rules.push(rule);
        }
        if (eduLevel != '' && eduLevel != null && eduLevel != undefined && eduLevel != "00") {
            var rule = {
                'field': 'interpretation.admissionBatchId',
                'op': 'eq',
                'data': eduLevel
            }
            rules.push(rule);
        }
        if (province != '' && province != null && province != undefined && province != "00") {
            var rule = {
                'field': 'interpretation.provinceId',
                'op': 'eq',
                'data': province
            }
            rules.push(rule);
        }
        return rules;
    }

    $("#search").click(function () {
        searchLoad();

    });
    //    搜索相关代码-end

    var select = document.getElementById('province');
    /*
    *
    * 政策解读模块
    *
    * */
    jQuery(function ($) {
        var typeStr;
        var rowId;
        var UI = {
            $addBtn: $('#addBtn')
            , $submitBtn: $('#submitBtn')
            , $editBtn: $('#editBtn')
            , $eduLevel: $('#eduLevel')
            , $province: $('#province')
            , $policyInterGroup: $('#policyInterGroup')
            , $newPolicy: $('#newPolicy')
            , $addGroup: $('#add-group')
        };
//       获取政策一级分类和省份信息
        var policyData = CommonFn.getpolicy();
//        UI.$eduLevel.append(policyData);
        var province = CommonFn.getProvince();
        UI.$province.append(province);
//        添加政策解读
        UI.$addBtn.on(ace.click_event, function () {
            typeStr = "add";
            bootbox.dialog({
                title: "添加政策解读",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addAndEditFn
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
            CommonFn.renderTextarea('#policyInterDetail');
        });

        //修改高考热点
        UI.$editBtn.on(ace.click_event, function () {
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
                        "callback": addAndEditFn()
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
//            $('#policyInterGroup').append(policyData);
//            实例化编辑器
            CommonFn.renderTextarea('#policyInterDetail');
            <#--$(document).on('click', '#add-group', function () {-->
                <#--var newGroupName = $('#policyInterNew').val().trim();-->
                <#--var dataGroup = {-->
                    <#--name: newGroupName-->
                    <#--, oper: 'add'-->
                    <#--, areaId: 0-->
                <#--};-->
                <#--CommonFn.getData('/admin/${bizSys}/commonsave/admissionbatch', 'post', dataGroup, function (res) {-->
                    <#--if (res.rtnCode == '0000000') {-->
                        <#--$('#newPolicy').hide();-->
                        <#--CommonFn.getData('/admin/${bizSys}/getAdmissionBatch', 'post', {}, function (res) {-->
                            <#--var options = '';-->
                            <#--$.each(res.bizData, function (i, v) {-->
                                <#--if (v.name == newGroupName) {-->
                                    <#--options += '<option value="' + v.id + '">' + newGroupName + '</option>';-->
                                    <#--$('#policyInterGroup').append(options).find('option[value="' + v.id + '"]').attr('selected', 'selected')-->
                                <#--}-->
                            <#--})-->
                        <#--})-->
                    <#--}-->
                <#--});-->
            <#--});-->
            // 当前行数据
            var rowData = CommonFn.getRowData(rowId);
            $('#province2').find('option[value="' + rowData[0].provinceId + '"]').attr('selected', 'selected');
            $('#policyInterGroup').find('option[value="' + rowData[0].admissionBatchId + '"]').attr('selected', 'selected');
            $('#policyInterTwo').val(rowData[0].categoryName);
            // 富媒体赋值
            $('#policyInterDetail').html(CommonFn.getContentHtml(rowData[0].content).join(''));
        });



        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');


        // 显示隐藏创建
        $(document).on('change', '#policyInterGroup', function () {
            var groupV = $('#policyInterGroup').find('option:selected').attr('value');
            groupV == '000' ? $('#newPolicy').removeClass('hide') : $('#newPolicy').addClass('hide');
        });

        // 创建提交
        $(document).on('click', '#add-group', function () {
            var newGroupName = $.trim($('#policyInterNew').val());
            $.ajax({
                url:'/admin/gaokao360/ex/addAdmissionBatch?name='+newGroupName,
                type:'POST',
                dataType:'json',
                success:function(rest){
                    console.log(rest)
                }
            })

        });










        var dialogHtml = ''
                + '<div class="bootbox-body">'
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<div class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">选择省份：</label>'
                + '<div class="col-sm-2">'
                + '<select class="form-control" id="province2">' + province + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="policyInterOne">'
                + '政策一级分类：</label>'
                + '<div class="col-sm-2">'
//                + '<select class="form-control" id="policyInterGroup">' + '<option value="00">选择政策一级分类</option>' + '<option value="000">+新建政策分类+</option>'
                + '<select class="form-control" id="policyInterGroup">' + policyData + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group hide" id="newPolicy">'
                + '<label class="col-sm-2 control-label no-padding-right" for="policyInterOne">'
                + '新建政策一级分类：</label>'
                + '<div class="col-sm-6">'
                + '<input type="text" id="policyInterNew" placeholder="政策解读一级分类，限制字数10个字"'
                + 'class="col-sm-5">'
                + '<div class="btn btn-success btn-sm col-sm-push-1" id="add-group">添加</div>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="policyInterTwo">'
                + '政策二级分类：</label>'
                + '<div class="col-sm-6">'
                + '<input type="text" id="policyInterTwo" placeholder="政策解读二级分类，限制字数10个字"'
                + 'class="col-sm-5">'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="policyInterDetail">'
                + '政策解读详情：</label>'
                + '<div class="col-sm-10">'
                + '<div id="policyInterDetail" class="wysiwyg-editor" style="width: 740px">'
                + '</div>'
                + '</div>'
                + '</div>'
                + '<div id="tips"></div>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '</div>';

        function addAndEditFn() {
            var provinceV = $("#province2").find("option:selected").attr('value');
            var policyInterTwoV = $('#policyInterTwo').val();
            var policyInterDetailV = $('#policyInterDetail').html();
            if (provinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            var policyInterGroupV = '';
            var selectPolicy = $("#policyInterGroup").find("option:selected").attr('value');
            if (selectPolicy == '000') {
                policyInterGroupV = $('#policyInterNew').val().trim();
                if (policyInterGroupV == '') {
                    CommonFn.tipsDialog('温馨提示', '新建政策一级分类不能为空');
                    return false;
                }
                CommonFn.getData('policyInterpretation.getInterfaceUrl.addGroup', {
                            name: policyInterGroupV,
                            oper: 'add',
                            areaId: 0
                        },
                        function (res) {
                            if (res.rtnCode == '0000000') {
                                console.info(res);
                            }
                        })
            } else {
                policyInterGroupV = selectPolicy;
            }
            if (selectPolicy == "000" || selectPolicy == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择政策一级分类');
                return false;
            }
            if (policyInterTwoV == "") {
                CommonFn.tipsDialog('温馨提示', '政策二级分类不能为空');
                return false;
            }
            if (policyInterDetailV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入政策解读详情内容');
                return false;
            }
            var policyContentHtml = CommonFn.getTextareaData(policyInterDetailV).join(''),
                    policyContentUrl = CommonFn.getTextareaUrlData(policyContentHtml),
                    htmlUrl = policyContentUrl[0],
                    htmlId = policyContentUrl[1],
                    infoData = {
                        htmlId: htmlId,
                        provinceId: provinceV,
                        admissionBatchId: policyInterGroupV,
                        content: htmlUrl,
                        categoryName: policyInterTwoV,
                        oper: typeStr,
                        areaId: 0
                    };
            if (typeStr == 'edit') {
                infoData.id = rowId;
            }
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

    })


</script>