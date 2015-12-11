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
    var policyInterpretation = {
        getSelOption: function (ajaxUrl, str) {
            var returnStr = "";
            returnStr += '<option value="00">请选择' + str + '</option>';
            $.ajaxSettings.async = false;
            $.getJSON(ajaxUrl, function (result) {
                if (result.rtnCode == "0000000") {
                    for (var i = 0; i < result.bizData.length; i++) {
                        var provinceId = result.bizData[i].id;
                        var provinceName = result.bizData[i].name;
                        returnStr += '<option value="' + provinceId + '">' + provinceName + '</option>';
                    }
                }
            });
            $.ajaxSettings.async = true;
            return returnStr;
        },
        tips: function (message) {
            var str = '<div class="alert alert-danger alert-dismissible well-sm pull-right text-center" role="alert" style="margin-right:44px;"> ' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button> ' +
                    '<strong>温馨提示：</strong><span>' + message + '</span> ' +
                    '</div>';
            $('#tips').html(str).fadeIn(3000);
        },
        tipsDialog: function (title, message) {
            bootbox.dialog({
                title: title,
                message: '<span class="bigger-110 center">' + message + '</span>',
                buttons: {
                    cancel: {
                        label: "关闭",
                        className: "btn-sm",
                    }
                }
            });
        },
        getInterfaceUrl: {
            getProvinceUrl: '/admin/${bizSys}/getProvince',
            getEduLevel: '/admin/${bizSys}/getAdmissionBatch',
            addPolicy: '/admin/${bizSys}/commonsave/${mainObj}',
            addGroup: '/admin/${bizSys}/commonsave/admissionbatch',
            getGroup: '/admin/${bizSys}/getAdmissionBatch',
            getDynGetData: '/admin/${bizSys}/getContentUrl'
        },
        dynGetData: function (ajaxUrl, contentData) {
            var returnStr = "";
            $.ajaxSettings.async = false;
            $.ajax({
                type: 'POST',
                url: ajaxUrl,
                data: {
                    content: contentData
                },
                success: function (result) {
                    if (result.rtnCode == '0000000') {
                        var jsonData = JSON.parse(result.bizData);
                        console.log(jsonData);
                        if (jsonData.rtnCode == '0000000') {
                            returnStr += jsonData.bizData.file.fileUrl;
                        } else {

                        }

                    }
                }
            });
            $.ajaxSettings.async = true;
            return returnStr;
        },
        getData: function (url, data, callback) {
            $.ajax({
                type: "POST",
                url: url,
                dataType: 'json',
                data: data,
                success: callback
            });
        }

    };

    jQuery(function ($) {
        //获取省份，学历层次
        var province = policyInterpretation.getSelOption(policyInterpretation.getInterfaceUrl.getProvinceUrl, '省份');
        $('#province').append(province);
        var eduLevel = policyInterpretation.getSelOption(policyInterpretation.getInterfaceUrl.getEduLevel, '学历层次');
        $('#eduLevel').html(eduLevel);
        $("#addPolicyInterpretation").on(ace.click_event, function () {
            //拉取：一级政策分类
            policyInterpretation.getData(policyInterpretation.getInterfaceUrl.getGroup, {}, function (res) {
                var strOption = '';
                if (res.rtnCode == '0000000') {
                    $.each(res.bizData, function (i, v) {
                        strOption += '<option value="' + v.id + '">' + v.name + '</option>';
                    });
                    $('#policyInterGroup').append(strOption);
                }
            });
            //添加：一级政策分类输入框
            $(document).on('change', '#policyInterGroup', function () {
                if ($(this).val() == '000') {
                    $('#newPolicy').removeClass('hide');
                }
                else {
                    $('#newPolicy').addClass('hide');
                }
            });
            var nweGroupName = '';
            $(document).on('click', '#add-group', function () {
                nweGroupName = $('#policyInterNew').val();
                if (nweGroupName == '') {
                    policyInterpretation.tips('新建政策一级分类不能为空');
                    return false;
                }
                policyInterpretation.getData(policyInterpretation.getInterfaceUrl.addGroup, {
                    name: nweGroupName,
                    oper: 'add',
                    areaId: 0
                }, function (res) {
                    if (res.rtnCode == '0000000') {
                        console.info(res);
                        $('#newPolicy').hide();
                        policyInterpretation.getData(policyInterpretation.getInterfaceUrl.getGroup, {}, function (res) {
                            if (res.rtnCode == '0000000') {
                                var strOption = '';
                                $.each(res.bizData, function (i, v) {
                                    if (v.name == nweGroupName) {
                                        strOption += '<option value="' + v.id + '">' + nweGroupName + '</option>';
                                        $('#policyInterGroup').append(strOption).find('option[value="' + v.id + '"]').attr('selected', 'selected')
                                    }
                                });
                            }
                        })
                    }
                })
            });
            //添加政策解读
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
                    + '<select class="form-control" id="policyInterGroup">' + '<option value="00">选择政策一级分类</option><option value="000">+新建政策分类+</option>'
                    + '</select>'
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
                    + '<div class="col-xs-6 col-sm-4">'
                    + '<div id="policyInterDetail" class="wysiwyg-editor" style="width: 740px">'
                    + '<input type="hidden" name="wysiwyg-value"/>'
                    + '</div>'
                    + '</div>'
                    + '</div>'
                    + '<div id="tips"></div>'
                    + '</div>'
                    + '</div>'
                    + '</div>'
                    + '</div>';

            var addPolicyInterpretationFun = function () {
                var provinceV = $("#province2").find("option:selected").attr('value');
                var policyInterTwoV = $.trim($('#policyInterTwo').val());
                var policyInterDetailV = $('#policyInterDetail').html();
                if (provinceV == "00") {
                    policyInterpretation.tips('请选择省份');
                    return false;
                }
                var policyInterGroupV = '';
                var selectPolicy = $("#policyInterGroup").find("option:selected").attr('value');
                if (selectPolicy == '000') {
                    policyInterGroupV = $('#policyInterNew').val();
                    if (policyInterGroupV == '') {
                        policyInterpretation.tips('新建政策一级分类不能为空');
                        return false;
                    }
                    policyInterpretation.getData(policyInterpretation.getInterfaceUrl.addGroup, {
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
                    policyInterpretation.tips('请选择政策一级分类');
                    return false;
                }
                if (policyInterTwoV == "") {
                    policyInterpretation.tips('政策二级分类不能为空');
                    return false;
                }
                if (policyInterDetailV == "") {
                    policyInterpretation.tips('请输入政策解读详情内容');
                    return false;
                }
                //上传高考热点内容到云存储
                var policyContentHtml = ''
                        + '<!DOCTYPE html>'
                        + '<html lang="en">'
                        + '<head>'
                        + '<meta charset="UTF-8">'
                        + '<title>Document</title>'
                        + '</head>'
                        + '<body>';
                policyContentHtml += policyInterDetailV + '</body>' + '</html>';
                var policyContentUrl = policyInterpretation.dynGetData(policyInterpretation.getInterfaceUrl.getDynGetData, policyContentHtml);
                //添加政策解读
                var infoData = {
                    provinceId: provinceV,
                    admissionBatchId: policyInterGroupV,
                    content: policyContentUrl,
                    categoryName: policyInterTwoV,
                    oper: 'add',
                    areaId: 0
                };
                policyInterpretation.getData(policyInterpretation.getInterfaceUrl.addPolicy, infoData, function (res) {
                    console.info(res);
                    if (res.rtnCode == "0000000") {
                        searchLoad();
                    }
                });
            };
            bootbox.dialog({
                title: "添加政策解读",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addPolicyInterpretationFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm",
                    }
                }
            });
            //初始化富文本编辑器
            $('#policyInterDetail').ace_wysiwyg({
                toolbar: [
                    {
                        name: 'font',
                        title: 'Custom tooltip',
                        values: ['Some Font!', 'Arial', 'Verdana', 'Comic Sans MS', 'Custom Font!']
                    },
                    null,
                    {
                        name: 'fontSize',
                        title: 'Custom tooltip',
                        values: {
                            1: 'Size#1 Text',
                            2: 'Size#1 Text',
                            3: 'Size#3 Text',
                            4: 'Size#4 Text',
                            5: 'Size#5 Text'
                        }
                    },
                    null,
                    {name: 'bold', title: 'Custom tooltip'},
                    {name: 'italic', title: 'Custom tooltip'},
                    {name: 'strikethrough', title: 'Custom tooltip'},
                    {name: 'underline', title: 'Custom tooltip'},
                    null,
                    'insertunorderedlist',
                    'insertorderedlist',
                    'outdent',
                    'indent',
                    null,
                    {name: 'justifyleft'},
                    {name: 'justifycenter'},
                    {name: 'justifyright'},
                    {name: 'justifyfull'},
                    null,
                    {
                        name: 'createLink',
                        placeholder: 'Custom PlaceHolder Text',
                        button_class: 'btn-purple',
                        button_text: 'Custom TEXT'
                    },
                    {name: 'unlink'},
                    null,
                    {
                        name: 'insertImage',
                        placeholder: 'Custom PlaceHolder Text',
                        button_class: 'btn-inverse',
                        //choose_file:false,//hide choose file button
                        button_text: 'Set choose_file:false to hide this',
                        button_insert_class: 'btn-pink',
                        button_insert: 'Insert Image'
                    },
                    null,
                    {
                        name: 'foreColor',
                        title: 'Custom Colors',
                        values: ['red', 'green', 'blue', 'navy', 'orange'],
                        /**
                         You change colors as well
                         */
                    },
                /**null,
                 {
                 name:'backColor'
                 },*/
                    null,
                    {name: 'undo'},
                    {name: 'redo'},
                    null,
                    'viewSource'
                ],
                //speech_button:false,//hide speech button on chrome
                'wysiwyg': {
                    hotKeys: {} //disable hotkeys
                }
            }).prev().addClass('wysiwyg-style2');
        });

        /*
        *
        * 删除
        *
        * */
        $("#delete-btn").on(ace.click_event, function () {
            var rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                policyInterpretation.tipsDialog('温馨提示', '请选中一行后在删除');
                return false;
            }
            console.info(rowId, selTrN);
            bootbox.dialog({
                title: "删除政策解读详情",
                message: "确定删除该条数据",
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 确定",
                        "className": "btn-sm btn-success",
                        "callback": function () {
                            $.ajax({
                                type: "POST",
                                url: '/admin/${bizSys}/commonsave/admissionbatch',
                                data: {
                                    oper: 'del',
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
                        className: "btn-sm",
                    }
                }
            });



        })


    });
</script>