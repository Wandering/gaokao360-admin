<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
//        var areaId = $('#selProvince').val();
//        var queryparam = $('#agentKeyWord').val();
        var rules = [];
//        if (areaId != '' && areaId != null && areaId != undefined && areaId != '00') {
//            var rule = {
//                'field': 'agent.areaId',
//                'op': 'eq',
//                'data': areaId
//            }
//            rules.push(rule);
//        }
//        if (queryparam != '' && queryparam != null && queryparam != undefined) {
//            var rule = {
//                'field': 'queryparam',
//                'op': 'lk',
//                'data': queryparam
//            }
//            rules.push(rule);
//        }
        return rules;
    }



    /*
    *
    * 招办电话模块
    *
    * */
    jQuery(function ($) {

        var typeStr;
        var rowId;
        var UI = {
            $agentKeyWord: $('#agentKeyWord'),
            $selMonth: $('#selMonth'),
            $selProvince: $('#selProvince'),
            $search: $('#search')
        };
//       搜索
        UI.$search.click(function () {
            searchLoad();
        });
//     获取省份
        var provinceData = CommonFn.getProvince();
        UI.$selProvince.append(provinceData);

//      添加招办联系电话
        $("#addBtn").on(ace.click_event, function () {
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
            uploadFun();
        });
//      修改高考日程
        $("#editBtn").on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改",
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
            // 当前行数据
            var rowData = CommonFn.getRowData(rowId);
            var expertName = $("#expertName").val(rowData[0].expertName);
            var expertPhone = $("#expertPhone").val(rowData[0].expertPhone);
            var expertPhotoUrl =  $('#expertPhotoUrl').val(rowData[0].expertPhotoUrl);
            var expertIntro = $("#expertIntro").val(rowData[0].expertIntro);
            var areaId = $("#areaId").find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
            var bestExpert = $("#bestExpert").val(rowData[0].bestExpert);
            var qualification = $("#qualification").val(rowData[0].qualification);
            var famousTeacher = $("#famousTeacher").val(rowData[0].famousTeacher);
            var isChecked = $("#isChecked").val(rowData[0].isChecked);
            var preAppointDays = $("#preAppointDays").val(rowData[0].preAppointDays);
            var qq = $("#qq").val(rowData[0].qq);
            var weixin = $("#weixin").val(rowData[0].weixin);
            uploadFun();
        });

        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  专家姓名：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="expertName" />'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  专家照片：</label>'
                + '<div class="col-sm-10">'
                + '<div id="uploader" class="wu-example">'
                + '<div class="uploader-tips">(只能上传一个文件,可拖拽文件)</div>'
                + '<div class="queueList">'
                + '<div id="dndArea" class="placeholder">'
                + '<div id="filePicker">点击上传</div>'
                + '</div>'
                + '</div>'
                + '<div class="statusBar" style="display:none;">'
                + '<div class="progress">'
                + '<span class="text">0%</span>'
                + '<span class="percentage"></span>'
                + '</div><div class="info"></div>'
                + '<div class="btns">'
                + '<div class="uploadBtn">开始上传</div>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  专家手机：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="expertPhone" />'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  专家简介：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="expertIntro" />'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  地区：</label>'
                + '<div class="col-sm-4">'
                + '<select type="text" class="form-control input-mask-phone" id="areaId">';
        dialogHtml += provinceData
                + '</select>'
                + '</div>'
                + '</div>'



                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  是否是本月人气专家：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="bestExpert">'
                + '<option value="1">是</option>'
                + '<option value="0">否</option>'
                + '</select>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  联盟高校认证专家：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="qualification">'
                + '<option value="1">是</option>'
                + '<option value="0">否</option>'
                + '</select>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  顶级名师：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="famousTeacher">'
                + '<option value="1">是</option>'
                + '<option value="0">否</option>'
                + '</select>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  是否审核通过 ：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="isChecked">'
                + '<option value="1">是</option>'
                + '<option value="2">否</option>'
                + '</select>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  专家设置的提前预约天数 ：</label>'
                + '<div class="col-sm-4">'
                + '<input class="form-control" id="preAppointDays"></input>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  qq ：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="qq" />'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right">  weixin ：</label>'
                + '<div class="col-sm-4">'
                + '<input type="text" class="form-control input-mask-phone" id="weixin" />'
                + '</div>'
                + '</div>'
                + '<input type="hidden" value="" id="expertPhotoUrl">'
                + '</form>'
                + '</div>'
                + '</div>';
        var addEditFun = function () {
            var expertName = $("#expertName").val();
            var expertPhone = $("#expertPhone").val();
            var expertPhotoUrl =  $('#expertPhotoUrl').val();
            var expertIntro = $("#expertIntro").val();
            var areaId = $("#areaId option:checked").val();
            var bestExpert = $("#bestExpert option:checked").val();
            var qualification = $("#qualification option:checked").val();
            var famousTeacher = $("#famousTeacher option:checked").val();
            var isChecked = $("#isChecked option:checked").val();
            var preAppointDays = $("#preAppointDays").val();
            var qq = $("#qq").val();
            var weixin = $("#weixin").val();

            var addInfoData = {
                oper: typeStr,
                expertName: expertName,
                expertPhone: expertPhone,
                expertPhotoUrl: expertPhotoUrl,
                expertIntro: expertIntro,
                areaId: areaId,
                bestExpert: bestExpert,
                qualification: qualification,
                famousTeacher: famousTeacher,
                isChecked: isChecked,
                preAppointDays: preAppointDays,
                qq: qq,
                weixin: weixin,
                password: '4297f44b13955235245b2497399d7a93'
            };

            if (typeStr == 'edit') {
                addInfoData.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/gaokao360/ex/commonsave/${mainObj}',
                data: addInfoData,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };//添加修改方法
//      删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');


        function uploadFun() {
            var $wrap = $('#uploader'),

                    // 图片容器
                    $queue = $('<ul class="filelist"></ul>').appendTo($wrap.find('.queueList')),

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
                    supportTransition = (function () {
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

            if (!WebUploader.Uploader.support()) {
                alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
                throw new Error('WebUploader does not support the browser you are using.');
            }

            // 实例化
            uploader = WebUploader.create({
                // 选择文件的按钮
                pick: '#filePicker',
                // 拖拽
                dnd: '#uploader .queueList',
                // 为通过粘贴来添加截屏的图片
                paste: document.body,
                // 上传文件的类型
//                accept: {
//                    title: 'Images',
//                    extensions: 'gif,jpg,jpeg,bmp,png',
//                    mimeTypes: 'image/*'
//                },

                // swf文件路径
                swf: '${path}/assets/js/webuploader-0.1.5/Uploader.swf',

                disableGlobalDnd: true,

                // 是否要分片处理大文件上传
                chunked: true,
                // server: 'http://webuploader.duapp.com/server/fileupload.php',
                server: 'http://cs-pro.qtonecloud.cn/rest/v1/uploadFile?userId=gk360&dirId=0&productCode=gk360&bizSystem=gk360&spaceName=gk360',
                // 验证文件总数量, 超出则不允许加入队列
                fileNumLimit: 0,
                // 验证文件总大小是否超出限制, 超出则不允许加入队列
//                fileSizeLimit: 5 * 1024 * 1024,
                // 验证单个文件大小是否超出限制, 超出则不允许加入队列
                fileSingleSizeLimit: 300 * 1024 * 1024
            });
            // 当有文件添加进来时执行，负责view的创建
            function addFile(file) {

                var $li = $('<li id="' + file.id + '">' +
                                '<p class="title">' + file.name + '</p>' +
                                '<p class="imgWrap"></p>' +
                                '<p class="progress"><span></span></p>' +
                                '</li>'),

                        $btns = $('<div class="file-panel">' +
                                '<span class="cancel">删除</span>' +
                                '<span class="rotateRight">向右旋转</span>' +
                                '<span class="rotateLeft">向左旋转</span></div>').appendTo($li),
                        $prgress = $li.find('p.progress span'),
                        $wrap = $li.find('p.imgWrap'),
                        $info = $('<p class="error"></p>'),

                        showError = function (code) {
                            switch (code) {
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

                            $info.text(text).appendTo($li);
                        };

                if (file.getStatus() === 'invalid') {
                    showError(file.statusText);
                } else {
                    // @todo lazyload
                    $wrap.text('预览中');
                    uploader.makeThumb(file, function (error, src) {
                        if (error) {
                            $wrap.text('不能预览');
                            return;
                        }

                        var img = $('<img src="' + src + '">');
                        $wrap.empty().append(img);
                    }, thumbnailWidth, thumbnailHeight);

                    percentages[file.id] = [file.size, 0];
                    file.rotation = 0;
                }

                file.on('statuschange', function (cur, prev) {
                    if (prev === 'progress') {
                        $prgress.hide().width(0);
                    } else if (prev === 'queued') {
                        $li.off('mouseenter mouseleave');
                        $btns.remove();
                    }

                    // 成功
                    if (cur === 'error' || cur === 'invalid') {
                        console.log(file.statusText);
                        showError(file.statusText);
                        percentages[file.id][1] = 1;
                    } else if (cur === 'interrupt') {
                        showError('interrupt');
                    } else if (cur === 'queued') {
                        percentages[file.id][1] = 0;
                    } else if (cur === 'progress') {
                        $info.remove();
                        $prgress.css('display', 'block');
                    } else if (cur === 'complete') {
                        $li.append('<span class="success"></span>');
                    }

                    $li.removeClass('state-' + prev).addClass('state-' + cur);
                });

                $li.on('mouseenter', function () {
                    $btns.stop().animate({height: 30});
                });

                $li.on('mouseleave', function () {
                    $btns.stop().animate({height: 0});
                });

                $btns.on('click', 'span', function () {
                    var index = $(this).index(),
                            deg;

                    switch (index) {
                        case 0:
                            uploader.removeFile(file);
                            return;

                        case 1:
                            file.rotation += 90;
                            break;

                        case 2:
                            file.rotation -= 90;
                            break;
                    }

                    if (supportTransition) {
                        deg = 'rotate(' + file.rotation + 'deg)';
                        $wrap.css({
                            '-webkit-transform': deg,
                            '-mos-transform': deg,
                            '-o-transform': deg,
                            'transform': deg
                        });
                    } else {
                        $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
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

                $li.appendTo($queue);
            }

            // 负责view的销毁
            function removeFile(file) {
                var $li = $('#' + file.id);

                delete percentages[file.id];
                updateTotalProgress();
                $li.off().find('.file-panel').off().end().remove();
            }

            function updateTotalProgress() {
                var loaded = 0,
                        total = 0,
                        spans = $progress.children(),
                        percent;

                $.each(percentages, function (k, v) {
                    total += v[0];
                    loaded += v[0] * v[1];
                });

                percent = total ? loaded / total : 0;

                spans.eq(0).text(Math.round(percent * 100) + '%');
                spans.eq(1).css('width', Math.round(percent * 100) + '%');
                updateStatus();
            }

            function updateStatus() {
                var text = '', stats;

                if (state === 'ready') {
                    text = '选中' + fileCount + '张图片，共' +
                            WebUploader.formatSize(fileSize) + '。';
                } else if (state === 'confirm') {
                    stats = uploader.getStats();
                    if (stats.uploadFailNum) {
                        text = '已成功上传' + stats.successNum + '张照片至XX相册，' +
                                stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                    }

                } else {
                    stats = uploader.getStats();
                    text = '共' + fileCount + '张（' +
                            WebUploader.formatSize(fileSize) +
                            '），已上传' + stats.successNum + '张';

                    if (stats.uploadFailNum) {
                        text += '，失败' + stats.uploadFailNum + '张';
                    }
                }

//                $info.html(text);
            }

            function setState(val) {
                var file, stats;

                if (val === state) {
                    return;
                }

                $upload.removeClass('state-' + state);
                $upload.addClass('state-' + val);
                state = val;

                switch (state) {
                    case 'pedding':
                        $placeHolder.removeClass('element-invisible');
                        $queue.parent().removeClass('filled');
                        $queue.hide();
                        $statusBar.addClass('element-invisible');
                        uploader.refresh();
                        break;

                    case 'ready':
                        $placeHolder.addClass('element-invisible');
                        $('#filePicker2').removeClass('element-invisible');
                        $queue.parent().addClass('filled');
                        $queue.show();
                        $statusBar.removeClass('element-invisible');
                        uploader.refresh();
                        break;

                    case 'uploading':
                        $('#filePicker2').addClass('element-invisible');
                        $progress.show();
                        $upload.text('暂停上传');
                        break;

                    case 'paused':
                        $progress.show();
                        $upload.text('继续上传');
                        break;

                    case 'confirm':
                        $progress.hide();
//                        $upload.text( '开始上传' ).addClass( 'disabled' );
                        $upload.text('开始上传');

                        stats = uploader.getStats();
                        if (stats.successNum && !stats.uploadFailNum) {
                            setState('finish');
                            return;
                        }
                        break;
                    case 'finish':
                        stats = uploader.getStats();
                        if (stats.successNum) {
//                            alert('上传成功');
                        } else {
                            // 没有成功的图片，重设
                            state = 'done';
                            location.reload();
                        }
                        break;
                }
                updateStatus();
            }

            uploader.onUploadProgress = function (file, percentage) {
                var $li = $('#' + file.id),
                        $percent = $li.find('.progress span');

                $percent.css('width', percentage * 100 + '%');
                percentages[file.id][1] = percentage;
                updateTotalProgress();
            };

            uploader.onFileQueued = function (file) {
                fileCount++;
                fileSize += file.size;
                if (fileCount === 1) {
                    $placeHolder.addClass('element-invisible');
                    $statusBar.show();
                }
                else if (fileCount >= 2) {
                    $('ul.filelist li:eq(0)').find('span.cancel').click();
                    if ($('.state-complete').length == 1) {
                        CommonFn.tipsDialog('温馨提示', '只能上传一个文件');
                        return false;
                    }
                }
                addFile(file);
                setState('ready');
                updateTotalProgress();
            };

            uploader.onFileDequeued = function (file) {
                fileCount--;
                fileSize -= file.size;
                if (!fileCount) {
                    setState('pedding');
                }
                removeFile(file);
                updateTotalProgress();

            };

            uploader.on('all', function (type) {
                var stats;
                switch (type) {
                    case 'uploadFinished':
                        setState('confirm');
                        break;

                    case 'startUpload':
                        setState('uploading');
                        break;

                    case 'stopUpload':
                        setState('paused');
                        break;

                }
            });

            uploader.onError = function (code) {
                alert('错误: ' + code);
            };

            uploader.onUploadSuccess = function (file, response) {
                console.log(response.bizData.file.fileUrl)
////                alert(response.bizData.file.fileUrl)
//                fileUrl = response.bizData.file.fileUrl;
                $('body').find('#expertPhotoUrl').val(response.bizData.file.fileUrl);

            };

            $upload.on('click', function () {
                if ($(this).hasClass('disabled')) {
                    return false;
                }

                if (state === 'ready') {
                    uploader.upload();
                } else if (state === 'paused') {
                    uploader.upload();
                } else if (state === 'uploading') {
                    uploader.stop();
                }
            });

            $info.on('click', '.retry', function () {
                uploader.retry();
            });

            $info.on('click', '.ignore', function () {
                alert('todo');
            });

            $upload.addClass('state-' + state);
            updateTotalProgress();
        }
    });//$结束


</script>
