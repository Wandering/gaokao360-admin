<script>

//    搜索相关代码-start
    function buildRules() {
        var keyWord = $('#keyWord').val();
        var eduLevel = $('#eduLevel').val();
        var province = $('#province').val();
        var rules = [];
        if (keyWord != ''&&keyWord!=null&&keyWord!=undefined) {
            var rule = {
                'field': 'queryparam',
                'op': 'lk',
                'data': keyWord
            }
            rules.push(rule);
        }
        if (eduLevel != ''&&eduLevel!=null&&eduLevel!=undefined&&eduLevel!="00") {
            var rule = {
                'field': 'interpretation.admissionBatchId',
                'op': 'eq',
                'data': eduLevel
            }
            rules.push(rule);
        }
        if (province != ''&&province!=null&&province!=undefined&&province!="00") {
            var rule = {
                'field': 'interpretation.provinceId',
                'op': 'eq',
                'data': province
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
//    搜索相关代码-end

    var select = document.getElementById('province');
    var policyInterpretation = {
        getEduLevel: function () {

        },
        getSelOption: function (ajaxUrl,str) {
            var returnStr = "";
            returnStr += '<option value="00">请选择'+str+'</option>';
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
            var addPolicyInterpretationFun = function(){
//            var provinceV = $('#province option:checked').val();
               var provinceV =   $("#province").find("option:selected").text();
//                policyInterOneV = $('#policyInterOne').val().trim(),
//                policyInterGroupV = $('#policyInterGroup option:checked').val().trim(),
//                policyInterTwoV = $('#policyInterTwo').val().trim(),
//                policyInterDetailV = $('#policyInterDetail').html();
                console.log(provinceV);
            };
            bootbox.dialog({
                title: "添加政策解读",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": function(){
                            var provinceV = $("#province").find("option:selected").text();
                            console.log(provinceV);
                        }
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