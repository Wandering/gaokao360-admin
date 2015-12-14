<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var papersubjectId = $('#courseName').val();
        var paperyears = $('#status').val();
        var paperareaId = $('#classfyId').val();
        var paperpaperName = $('#classfyId').val();
        var rules = [];
        if (papersubjectId != ''&&papersubjectId!=null&&papersubjectId!=undefined&&papersubjectId!="00") {
            var rule = {
                'field': 'paper.subjectId',
                'op': 'eq',
                'data': papersubjectId
            }
            rules.push(rule);
        }
        if (paperyears != ''&&paperyears!=null&&paperyears!=undefined&&paperyears!="00") {
            var rule = {
                'field': 'paper.years',
                'op': 'eq',
                'data': paperyears
            }
            rules.push(rule);
        }
        if (paperareaId != ''&&paperareaId!=null&&paperareaId!=undefined&&paperyears!="00") {
            var rule = {
                'field': 'paper.areaId',
                'op': 'eq',
                'data': paperareaId
            }
            rules.push(rule);
        }
        if (paperpaperName != ''&&paperpaperName!=null&&paperpaperName!=undefined&&paperpaperName!="00") {
            var rule = {
                'field': 'paperpaperName',
                'op': 'eq',
                'data': paper.paperName
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

    jQuery(function ($) {
        $("#search").click(function () {
            searchLoad();

        });

        var Hot = {
            getProvince: function (ajaxUrl) {
                var returnStr = "";
                returnStr += '<option value="00">请选择省份</option>';
                $.ajaxSettings.async = false;
                $.getJSON(ajaxUrl, function (result) {
                    console.log(result);
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
            }
        };
        $('#areaId').html(Hot.getProvince('/admin/gaokao360/ex/getProvince'));

        function dynGetData(ajaxUrl, contentData) {
            var returnStr = "";
            $.ajaxSettings.async = false;
            $.ajax({
                type: 'POST',
                url: ajaxUrl,
                data: {
                    content: contentData
                },
                success: function (result) {
                    console.log(result)
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
        }

        // 添加高考热点
        $("#addBtn").on(ace.click_event, function () {
            var dialogHtml = ''
                    + '<div class="row">'
                    + '<div class="col-xs-12">'
                    + '<form class="form-horizontal" role="form">'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right"> 选择省份：</label>'
                    + '<div class="col-sm-3">'
                    + '<select class="form-control" id="selProvince">';
            dialogHtml += Hot.getProvince('/admin/gaokao360/ex/getProvince')
            + '</select>'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle"> 高考热点标题：</label>'
            + '<div class="col-sm-3">'
            + '<input type="text" id="hotTitle" placeholder="请输入高考热点标题" class="" />'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotContent"> 高考热点内容：</label>'
            + '<div class="col-sm-10">'
            + '<form method="POST" id="myform" action="wysiwyg.php">'
            + '<div id="hotContent" class="wysiwyg-editor"></div>'
            + '<input type="hidden" name="wysiwyg-value" />'
            + '<div class="form-actions align-right clearfix" style="display:none">'
            + '<button type="submit" class="btn btn-primary pull-right">'
            + '<i class="ace-icon fa fa-check bigger-110"></i>'
            + 'submit'
            + '</button>'
            + '</div>'
            + '</form>'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotContent"> 高考热点日期：</label>'
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
            bootbox.dialog({
                title: "添加高考热点",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": function () {
                            var selProvinceV = $('#selProvince option:checked').val(),
                                    hotTitleV = $('#hotTitle').val().trim(),
                                    hotContentV = $('#hotContent').html(),
                                    datePickerV = $('#date-picker').val().trim();
                            console.log(hotContentV);
                            if (selProvinceV == "00") {
                                tipsDialog('温馨提示', '请选择省份');
                                return false;
                            }
                            if (hotTitleV == "") {
                                tipsDialog('温馨提示', '请输入高考热点标题');
                                return false;
                            }
                            if (hotContentV == "") {
                                tipsDialog('温馨提示', '请输入高考热点内容');
                                return false;
                            }

                            if (datePickerV == "") {
                                tipsDialog('温馨提示', '请选择高考热点日期');
                                return false;
                            }
                            //上传高考热点内容到云存储
                            var hotContentHtml = ''
                                    + '<!DOCTYPE html>'
                                    + '<html lang="en">'
                                    + '<head>'
                                    + '<meta charset="UTF-8">'
                                    + '<title>Document</title>'
                                    + '</head>'
                                    + '<body>';
                            hotContentHtml += hotContentV
                            + '</body>'
                            + '</html>';
                            var hotContentUrl = dynGetData('/admin/${bizSys}/getContentUrl', hotContentHtml);
                            var infoData = {
                                areaId: selProvinceV,
                                hotInformation: hotTitleV,
                                informationContent: hotContentUrl,
                                hotdate: datePickerV,
                                informationSubContent: '',
                                hotCount: 0,
                                oper: 'add'
                            };
                            console.log(infoData)
                            $.ajax({
                                type: "POST",
                                url: '/admin/${bizSys}/commonsave/${mainObj}',
                                data: infoData,
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

            $('#date-picker').datepicker({
                autoclose: true,
                todayHighlight: true
            });
            $('#hotContent').ace_wysiwyg({
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

        //修改高考热点
        $("#editBtn").on(ace.click_event, function () {
            var rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            var dialogHtml = ''
                    + '<div class="row">'
                    + '<div class="col-xs-12">'
                    + '<form class="form-horizontal" role="form">'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right"> 选择省份：</label>'
                    + '<div class="col-sm-3">'
                    + '<select class="form-control" id="selProvince">';
            dialogHtml += Hot.getProvince('/admin/gaokao360/ex/getProvince')
            + '</select>'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle"> 高考热点标题：</label>'
            + '<div class="col-sm-3">'
            + '<input type="text" id="hotTitle" placeholder="请输入高考热点标题" class="" />'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotContent"> 高考热点内容：</label>'
            + '<div class="col-sm-10">'
            + '<form method="POST" id="myform" action="wysiwyg.php">'
            + '<div id="hotContent" class="wysiwyg-editor"></div>'
            + '<input type="hidden" name="wysiwyg-value" />'
            + '<div class="form-actions align-right clearfix" style="display:none">'
            + '<button type="submit" class="btn btn-primary pull-right">'
            + '<i class="ace-icon fa fa-check bigger-110"></i>'
            + 'submit'
            + '</button>'
            + '</div>'
            + '</form>'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotContent"> 高考热点日期：</label>'
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
            $.getJSON('/admin/${bizSys}/${mainObj}queryone?id=' + rowId, function (result) {
                console.log(result)
                if (result.rtnCode == "0000000") {
                    var dataInfo = result.bizData;
                    var areaId = dataInfo.areaId,
                            hotInformation = dataInfo.hotInformation,
                            hotdate = dataInfo.hotdate,
                            informationContent = dataInfo.informationContent;
                    $.ajax({
                        type:'POST',
                        url:'/admin/${bizSys}/getHTMLContent',
                        data: {
                            htmlurl:informationContent
                        },
                        success: function (res) {
                            if(res.rtnCode == "0000000"){
                                $('#hotContent').html(res.bizData);
                            }
                        }
                    });
                    $('#selProvince').find('option[value="' + areaId + '"]').attr('selected', 'selected');
                    $('#hotTitle').val(hotInformation);
                    $('#date-picker').val(hotdate);
                }
            });
            bootbox.dialog({
                title: "修改高考热点",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": function () {
                            var selProvinceV = $('#selProvince option:checked').val(),
                                    hotTitleV = $('#hotTitle').val().trim(),
                                    hotContentV = $('#hotContent').html(),
                                    datePickerV = $('#date-picker').val().trim();

                            console.log(hotContentV)
                            if (selProvinceV == "00") {
                                tipsDialog('温馨提示', '请选择省份');
                                return false;
                            }
                            if (hotTitleV == "") {
                                tipsDialog('温馨提示', '请输入高考热点标题');
                                return false;
                            }
                            if (hotContentV == "") {
                                tipsDialog('温馨提示', '请输入高考热点内容');
                                return false;
                            }
                            if (datePickerV == "") {
                                tipsDialog('温馨提示', '请选择高考热点日期');
                                return false;
                            }
                            //上传高考热点内容到云存储
                            var hotContentHtml = ''
                                    + '<!DOCTYPE html>'
                                    + '<html lang="en">'
                                    + '<head>'
                                    + '<meta charset="UTF-8">'
                                    + '<title>Document</title>'
                                    + '</head>'
                                    + '<body>';
                            hotContentHtml += hotContentV
                            + '</body>'
                            + '</html>';
                            var hotContentUrl = dynGetData('/admin/${bizSys}/getContentUrl', hotContentHtml);
                            var infoData = {
                                id: rowId,
                                areaId: selProvinceV,
                                hotInformation: hotTitleV,
                                informationContent: hotContentUrl,
                                hotdate: datePickerV,
                                informationSubContent: '',
                                hotCount: 0,
                                oper: 'edit'
                            };
                            console.log(infoData)
                            $.ajax({
                                type: "POST",
                                url: '/admin/${bizSys}/commonsave/${mainObj}',
                                data: infoData,
                                success: function (result) {
                                    console.log(result)
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

            $('#date-picker').datepicker({
                autoclose: true,
                todayHighlight: true
            });
            $('#hotContent').ace_wysiwyg({
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
        //删除
        $("#deleteBtn").on(ace.click_event, function () {
            var rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                tipsDialog('温馨提示', '请选中一行后在删除');
                return false;
            }
            bootbox.dialog({
                title: "添加高考热点",
                message: "确定删除该条数据",
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 确定",
                        "className": "btn-sm btn-success",
                        "callback": function () {
                            $.ajax({
                                type: "POST",
                                url: '/admin/${bizSys}/commonsave/${mainObj}',
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


        });


        function tipsDialog(title, message) {
            bootbox.dialog({
                title: title,
                message: '<span class="bigger-110 center">' + message + '</span>',
                buttons: {
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
        }
    });

    /*
    *
    * 真题密卷
    * 增删改差同意使用commonExamPaper接口
    * {oper:add/del/edit,id:''}
    *
    * */
    var examPaperObj = {
        url: {
            commonExamPaper: '/admin/${bizSys}/commonsave/${mainObj}',
            getProvinceUrl: '/admin/${bizSys}/getProvince',
            getSubjectUrl: '/admin/${bizSys}/getSubject',
            getYear: '/admin/${bizSys}/${mainObj}/getYears'
        },
        getData: function (url, data, callback) {
            $.ajax({
                type: "POST",
                url: url,
                dataType: 'json',
                data: data,
                success: callback
            });
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
        tipsDialog: function (title, message) {
            bootbox.dialog({
                title: title,
                message: '<span class="bigger-110 center">' + message + '</span>',
                buttons: {
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
        },
        tips: function (message) {
            var str = '<div class="alert alert-danger alert-dismissible well-sm pull-right text-center" role="alert" style="margin-right:44px;"> ' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button> ' +
                    '<strong>温馨提示：</strong><span>' + message + '</span>' +
                    '</div>';
            $('#tips').html(str).fadeIn(3000);
        }
    };
    jQuery(function ($) {
        var UI = {
            $selCourses: $('#selCourses'),
            $selYears: $('#selYears'),
            $selProvince: $('#selProvince'),

            $addExamBtn: $('#addExamBtn'),
            $editExamBtn: $('#editExamHotBtn'),
            $deleteHotBtn: $('#deleteHotBtn')
        };
//        获取科目
        examPaperObj.getData(examPaperObj.url.getSubjectUrl, {}, function (res) {
            if (res.rtnCode == '0000000') {
                var dataSubject = res.bizData;
                var subjectHtml = '';
                $.each(dataSubject, function (i, v) {
                    subjectHtml += '<option value="' + v.id + '">' + v.subjectName + '</option>';
                });
                UI.$selCourses.append(subjectHtml);
            }
        });
//        获取年份
        examPaperObj.getData(examPaperObj.url.getProvinceUrl, {}, function (res) {
            if (res.rtnCode == '0000000') {
                var dataProvince = res.bizData;
                var provinceHtml = '';
                $.each(dataProvince,function(i,v){
                    provinceHtml += '<option value="' + v.id + '">' + v.name + '</option>';
                });
                UI.$selProvince.append(provinceHtml);
            }
        });


        //添加正题密卷
        UI.$addExamBtn(ace.click_event, function () {

        });
    });


</script>