<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var areaId = $('#areaId').val();
        var hotInformation = $('#hotInformation').val();
//        var hotInformation = "0";
        var rules = [];
        if (areaId != '' && areaId != null && areaId != undefined && areaId!="00") {
            var rule = {
                'field': 'gkhot.areaId',
                'op': 'eq',
                'data': areaId
            };
            rules.push(rule);
        }
        if (hotInformation != '' && hotInformation != null && hotInformation != undefined) {
            var rule = {
                'field': 'gkhot.hotInformation',
                'op': 'lk',
                'data': hotInformation
            };
            rules.push(rule);
        }
//        console.log(rules);
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
//                    console.log(result);
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
//                    console.log(result)
                    if (result.rtnCode == '0000000') {
                        var jsonData = JSON.parse(result.bizData);
//                        console.log(jsonData);
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

        // 添加
        $("#addBtn").on(ace.click_event, function () {
            var dialogHtml = ''
                    + '<div class="row">'
                    + '<div class="col-xs-12">'
                    + '<form class="form-horizontal" role="form">'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right"> 省份：</label>'
                    + '<div class="col-sm-3">'
                    + '<select class="form-control" id="selProvince">';
            dialogHtml += Hot.getProvince('/admin/gaokao360/ex/getProvince')
            + '</select>'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle">课程名称：</label>'
            + '<div class="col-sm-10">'
            + '<input type="text" id="hotTitle" placeholder="请输入课程名称" class="" />（最多10个字）'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle">视频分类：</label>'
            + '<div class="col-sm-10">'
            + '<input type="text" id="hotTitle" placeholder="请输入视频分类" class="" />（最多10个字）'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle">视频个数：</label>'
            + '<div class="col-sm-10">'
            + '<input type="text" id="hotTitle" placeholder="请输入视频个数" class="" />（最多10个字）'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle">主讲老师：</label>'
            + '<div class="col-sm-10">'
            + '<input type="text" id="hotTitle" placeholder="请输入主讲老师" class="" />（最多10个字）'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle">专家介绍：</label>'
            + '<div class="col-sm-10">'
            + '<input type="text" id="hotTitle" placeholder="请输入专家介绍" class="" />（最多200个字）'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle">封面图片：</label>'
            + '<div class="col-sm-10">'






//            + '<div id="uploader" class="wu-example">'
//            + '<div id="thelist" class="uploader-list"></div>'
//            + '<div class="btns">'
//            + '<div id="picker">选择文件</div>'
//            + '<button id="ctlBtn" class="btn btn-default">开始上传</button>'
//            + '</div>'
//            + '</div>'







            + '（只能一张图片,封面的大小296*167，100KB）'
            + '</div>'
            + '</div>'
            + '<div class="form-group">'
            + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle">视频上传：</label>'
            + '<div class="col-sm-10">'
            + '<div></div>'
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
//                            console.log(hotContentV);
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
//                            console.log(infoData)
                            $.ajax({
                                type: "POST",
                                url: '/admin/${bizSys}/commonsave/${mainObj}',
                                data: infoData,
                                success: function (result) {
//                                    console.log(result);
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
//                console.log(result)
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

//                            console.log(hotContentV)
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
//                            console.log(infoData)
                            $.ajax({
                                type: "POST",
                                url: '/admin/${bizSys}/commonsave/${mainObj}',
                                data: infoData,
                                success: function (result) {
//                                    console.log(result)
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
//                                    console.log(result);
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
                        className: "btn-sm",
                    }
                }
            });
        }
    });





    jQuery(function() {
        var $ = jQuery,    // just in case. Make sure it's not an other libaray.

                $wrap = $('#uploader'),

        // 图片容器
                $queue = $('<ul class="filelist"></ul>')
                        .appendTo( $wrap.find('.queueList') ),

        // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find('.statusBar'),

        // 文件总体选择信息。
                $info = $statusBar.find('.info'),

        // 上传按钮
                $upload = $wrap.find('.uploadBtn'),

        // 没选择文件之前的内容。
                $placeHolder = $wrap.find('.placeholder'),

        // 总体进度条
                $progress = $statusBar.find('.progress').hide(),

        // 添加的文件数量
                fileCount = 0,

        // 添加的文件总大小
                fileSize = 0,

        // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,

        // 缩略图大小
                thumbnailWidth = 110 * ratio,
                thumbnailHeight = 110 * ratio,

        // 可能有pedding, ready, uploading, confirm, done.
                state = 'pedding',

        // 所有文件的进度信息，key为file id
                percentages = {},

                supportTransition = (function(){
                    var s = document.createElement('p').style,
                            r = 'transition' in s ||
                                    'WebkitTransition' in s ||
                                    'MozTransition' in s ||
                                    'msTransition' in s ||
                                    'OTransition' in s;
                    s = null;
                    return r;
                })(),

        // WebUploader实例
                uploader;

        if ( !WebUploader.Uploader.support() ) {
            alert( 'Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
            throw new Error( 'WebUploader does not support the browser you are using.' );
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker',
                label: '点击选择图片'
            },
            dnd: '#uploader .queueList',
            paste: document.body,

//            accept: {
//                title: 'Video',
//                extensions: 'mp4',
//                mimeTypes: 'video/mp4'
//            },
            accept: {
                title: 'Images',
//                extensions: 'gif,jpg,jpeg,bmp,png',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            },

            // swf文件路径
            swf: '${path}/assets/js/webuploader-0.1.5/Uploader.swf',

            disableGlobalDnd: true,
            compressSize: 0,
            chunked: true,
            chunkSize:5242880,
            // server: 'http://webuploader.duapp.com/server/fileupload.php',
            server: 'http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile?userId=gk360&dirId=0&productCode=gk360&bizSystem=gk360&spaceName=gk360',
            fileNumLimit: 5,
            fileSizeLimit: 10 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 1000 * 1024 * 1024    // 50 M
        });
        // 添加“添加文件”的按钮，
        uploader.addButton({
            id: '#filePicker2',
            label: '继续添加'
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                    $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                    $prgress = $li.find('p.progress span'),
                    $wrap = $li.find( 'p.imgWrap' ),
                    $info = $('<p class="error"></p>'),

                    showError = function( code ) {
                        switch( code ) {
                            case 'exceed_size':
                                text = '文件大小超出';
                                break;

                            case 'interrupt':
                                text = '上传暂停';
                                break;

                            default:
                                text = '上传失败，请重试';
                                break;
                        }

                        $info.text( text ).appendTo( $li );
                    };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    var img = $('<img src="'+src+'">');
                    $wrap.empty().append( img );
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            }

            file.on('statuschange', function( cur, prev ) {
                if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });

            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                        deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                    // use jquery animate to rotation
                    // $({
                    //     rotation: rotation
                    // }).animate({
                    //     rotation: file.rotation
                    // }, {
                    //     easing: 'linear',
                    //     step: function( now ) {
                    //         now = now * Math.PI / 180;

                    //         var cos = Math.cos( now ),
                    //             sin = Math.sin( now );

                    //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                    //     }
                    // });
                }


            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                    total = 0,
                    spans = $progress.children(),
                    percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;

            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                    stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                WebUploader.formatSize( fileSize )  +
                '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html( text );
        }

        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.parent().removeClass('filled');
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.parent().addClass('filled');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $upload.text( '开始上传' ).addClass( 'disabled' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        alert( '上传成功' );
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

        uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                    $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };

        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }

            addFile( file );
            setState( 'ready' );
            updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;
            }
        });

        uploader.onError = function( code ) {
            alert( 'Eroor: ' + code );
        };

        uploader.onUploadSuccess = function( file ,response) {
            console.log( response );
        };


        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            alert( 'todo' );
        } );

        $upload.addClass( 'state-' + state );
        updateTotalProgress();




    });







</script>
