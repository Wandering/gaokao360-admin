<script>
    var policyInterpretation = {
        getEduLevel: function () {

        },
        getSelOption: function (ajaxUrl,str) {
            var returnStr = "";
            returnStr += '<option value="00">请选择'+str+'</option>';
            $.ajaxSettings.async = false;
            $.getJSON(ajaxUrl, function (result) {
                console.log(result + "==");
                if (result.rtnCode == "0000000") {
                    for (var i = 0; i < result.bizData.length; i++) {
                        var provinceId = result.bizData[i].id;
                        var provinceName = result.bizData[i].name;
                        returnStr += '<option value="' + provinceId + '">' + provinceName + '</option>';
                    }
                }
                console.log(returnStr);
            });
            $.ajaxSettings.async = true;
            return returnStr;
        },
        getInterfaceUrl: {
            getProvinceUrl: '/admin/${bizSys}/getProvince',
            getEduLevel:'/admin/${bizSys}/getAdmissionBatch'
        }
    };


    //政策解读模块
    jQuery(function ($) {
        /*
        * 获取省份，学历层次
        * */
        var province = policyInterpretation.getSelOption(policyInterpretation.getInterfaceUrl.getProvinceUrl,'省份');
        $('#province').append(province);
        var policyInterGroup = '';
        var eduLevel = policyInterpretation.getSelOption(policyInterpretation.getInterfaceUrl.getEduLevel,'学历层次');
        $('#eduLevel').html(eduLevel);

        $("#addPolicyInterpretation").on(ace.click_event, function () {
            //添加政策解读
            var dialogHtml = ''
                    + '<div class="bootbox-body">'
                    + '<div class="row">'
                    + '<div class="col-xs-12">'
                    + '<div class="form-horizontal" role="form">'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right">选择省份：</label>'
                    + '<div class="col-sm-2">'
                    + '<select class="form-control" id="province">'+province+'</select>'
                    + '</div>'
                    + '</div>'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right" for="policyInterOne">'
                    + '政策一级分类：</label>'
                    + '<div class="col-sm-6">'
                    + '<input type="text" id="policyInterOne" placeholder="政策解读一级分类，限制字数10个字" class="col-sm-5">'
                    + '</div>'
                    + '<div class="col-sm-2 col-sm-pull-3">'
                    + '<select class="form-control" id="policyInterGroup">'+policyInterGroup+'</select>'
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
                    + '</div>'
                    + '</div>'
                    + '</div>'
                    + '</div>';
            bootbox.dialog({
                title: "添加政策解读",
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
                            var infoData = {};
                            $.ajax({
                                type: "POST",
                                url: '/admin/${bizSys}/commonsave/{mainObj}',
                                data: infoData,
                                success: function (result) {
                                    alert(result);
                                }
                            });


                        }
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
//        //初始化富文本编辑器
//        $('#policyInterDetail').ace_wysiwyg({
//            toolbar: [
//                {
//                    name: 'font',
//                    title: 'Custom tooltip',
//                    values: ['Some Font!', 'Arial', 'Verdana', 'Comic Sans MS', 'Custom Font!']
//                },
//                null,
//                {
//                    name: 'fontSize',
//                    title: 'Custom tooltip',
//                    values: {
//                        1: 'Size#1 Text',
//                        2: 'Size#1 Text',
//                        3: 'Size#3 Text',
//                        4: 'Size#4 Text',
//                        5: 'Size#5 Text'
//                    }
//                },
//                null,
//                {name: 'bold', title: 'Custom tooltip'},
//                {name: 'italic', title: 'Custom tooltip'},
//                {name: 'strikethrough', title: 'Custom tooltip'},
//                {name: 'underline', title: 'Custom tooltip'},
//                null,
//                'insertunorderedlist',
//                'insertorderedlist',
//                'outdent',
//                'indent',
//                null,
//                {name: 'justifyleft'},
//                {name: 'justifycenter'},
//                {name: 'justifyright'},
//                {name: 'justifyfull'},
//                null,
//                {
//                    name: 'createLink',
//                    placeholder: 'Custom PlaceHolder Text',
//                    button_class: 'btn-purple',
//                    button_text: 'Custom TEXT'
//                },
//                {name: 'unlink'},
//                null,
//                {
//                    name: 'insertImage',
//                    placeholder: 'Custom PlaceHolder Text',
//                    button_class: 'btn-inverse',
//                    //choose_file:false,//hide choose file button
//                    button_text: 'Set choose_file:false to hide this',
//                    button_insert_class: 'btn-pink',
//                    button_insert: 'Insert Image'
//                },
//                null,
//                {
//                    name: 'foreColor',
//                    title: 'Custom Colors',
//                    values: ['red', 'green', 'blue', 'navy', 'orange'],
//                    /**
//                     You change colors as well
//                     */
//                },
//            /**null,
//             {
//             name:'backColor'
//             },*/
//                null,
//                {name: 'undo'},
//                {name: 'redo'},
//                null,
//                'viewSource'
//            ],
//            //speech_button:false,//hide speech button on chrome
//            'wysiwyg': {
//                hotKeys: {} //disable hotkeys
//            }
//        }).prev().addClass('wysiwyg-style2');
    });
</script>