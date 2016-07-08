<script>
    function buildRules() {
        var queryparam = $('#examKeyWord').val();
        var selYears = $('#selYears').val();
        var selProvince = $('#selProvince').val();
        var rules = [];
        if (queryparam != '' && queryparam != null && queryparam != undefined) {
            var rule = {
                'field': 'queryparam',
                'op': 'lk',
                'data': queryparam
            };
            rules.push(rule);
        }
        if (selYears != '00' && selYears != null && selYears != undefined) {
            var rule = {
                'field': 'enrolling.year',
                'op': 'eq',
                'data': selYears
            };
            rules.push(rule);
        }
        if (selProvince != '00' && selProvince != null && selProvince != undefined) {
            var rule = {
                'field': 'university.areaid',
                'op': 'eq',
                'data': selProvince
            };
            rules.push(rule);
        }
        return rules;
    }
    $("#search").click(function () {
        searchLoad();
    });



    jQuery(function ($) {
        var dialogHtml_import = ''
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
                + '</div>';

        $("#importFile").on(ace.click_event, function (e) {
            bootbox.dialog({
                title: "添加高考头条",
                message: dialogHtml_import,
                className: 'my-modal',
                buttons: {
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
            uploadFun();
        });

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
//                server: 'http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile?userId=gk360&dirId=0&productCode=gk360&bizSystem=gk360&spaceName=gk360',
                server: '/admin/gaokao360/ex/import/importUniversityData.do',
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
                $('body').find('#swfUrl').val(response.bizData.file.fileUrl);

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
        var typeStr;
        var rowId;

        // 课程
        var subjectData = CommonFn.getSubject();
        $('#selCourses').append(subjectData);
        // 年份
        var yearsData = CommonFn.getYear();
        $('#selYears').append(yearsData);
        // 省份
        var provinceData = CommonFn.getProvince();
        $('#selProvince').html(provinceData);
        var getBatchData = CommonFn.getBatch();
        var dialogHtml = ''
                + '<div class="row" id="dialogHtml">'
                + '    <div class="col-xs-12">'
                + '      <form class="form-horizontal" role="form">'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 省份：</label>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" id="selProvince2">' + provinceData + '</select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 院校名称：</label>'
                + '              <div class="col-sm-4">'
                + '                  <input id="autoSearch" type="text" class="form-control" placeholder="请输入学校查询"/>'
                + '          </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 年份：</label>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" id="selYears2">' + yearsData + '</select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group" id="subjectType1">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 文史类招生：</label>'
                + '              <div class="col-sm-9" id="subjectType-main1">'
                + '                  <div class="subjectTypeList" dataId="1">'
                + '                      <select class="form-control subjectType"  style="width:50%">' + getBatchData + '</select>'
                + '                      <div class="form-group subjectTypeDetail">'
                + '                          <div class="col-sm-12">'
                + '                              计划数：<input type="text" class="input-small planEnrollingNumber" placeholder="计划数"/>'
                + '                              录取数：<input type="text" class="input-small realEnrollingNumber" placeholder="录取数"/>'
                + '                              最高分：<input type="text" class="input-small highestScore" placeholder="最高分"/>'
                + '                              最高位次：<input type="text" class="input-small highestPrecedence" placeholder="最高位次"/>'
                + '                              <br /><br />'
                + '                              最低分：<input type="text" class="input-small lowestScore" placeholder="最低分"/>'
                + '                              最低位次：<input type="text" class="input-small lowestPrecedence" placeholder="最低位次"/>'
                + '                              平均分：<input type="text" class="input-small averageScore" placeholder="平均分"/>'
                + '                              平均位次：<input type="text" class="input-small averagePrecedence" placeholder="平均位次"/>'
                + '                              <button class="btn btn-minier btn-pink deleteSubjectTypeBtn">删除</button>'
                + '                              <div class="line"></div>'
                + '                          </div>'
                + '                      </div>'
                + '                  </div>'
                + '              </div>'
                + '              <div class="col-sm-1">'
                + '                  <button class="btn btn-sm btn-primary" id="subjectTypeBtn1">增加</button>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group" id="subjectType2">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 理工类招生：</label>'
                + '              <div class="col-sm-9" id="subjectType-main2">'
                + '                  <div class="subjectTypeList" dataId="2" >'
                + '                      <select class="form-control subjectType"  style="width:50%">' + getBatchData + '</select>'
                + '                      <div class="form-group subjectTypeDetail">'
                + '                          <div class="col-sm-12">'
                + '                              计划数：<input type="text" class="input-small planEnrollingNumber" placeholder="计划数"/>'
                + '                              录取数：<input type="text" class="input-small realEnrollingNumber" placeholder="录取数"/>'
                + '                              最高分：<input type="text" class="input-small highestScore" placeholder="最高分"/>'
                + '                              最高位次：<input type="text" class="input-small highestPrecedence" placeholder="最高位次"/>'
                + '                              <br /><br />'
                + '                              最低分：<input type="text" class="input-small lowestScore" placeholder="最低分"/>'
                + '                              最低位次：<input type="text" class="input-small lowestPrecedence" placeholder="最低位次"/>'
                + '                              平均分：<input type="text" class="input-small averageScore" placeholder="平均分"/>'
                + '                              平均位次：<input type="text" class="input-small averagePrecedence" placeholder="平均位次"/>'
                + '                              <button class="btn btn-minier btn-pink deleteSubjectTypeBtn">删除</button>'
                + '                              <div class="line"></div>'
                + '                          </div>'
                + '                      </div>'
                + '                  </div>'
                + '              </div>'
                + '              <div class="col-sm-1">'
                + '                  <button class="btn btn-sm btn-primary" id="subjectTypeBtn2">增加</button>'
                + '              </div>'
                + '          </div>'
                + '      </form>'
                + '  </div>'
                + '</div>';

        function catcompleteFn() {
            $.widget("custom.catcomplete", $.ui.autocomplete, {
                _renderMenu: function (ul, items) {
                    var that = this,
                            currentCategory = "";
                    $.each(items, function (index, item) {
                        if (item.category != currentCategory) {
                            ul.append("<li class='ui-autocomplete-category'>" + item.category + "</li>");
                            currentCategory = item.category;
                        }
                        that._renderItemData(ul, item);
                    });
                }
            });
            var dataJson = CommonFn.getAllSchool('');
            var dataArr = [];
            var obj = {};
            for (var i = 0; i < dataJson.length; i++) {
                obj = {
                    id: dataJson[i].id,
                    label: dataJson[i].label,
                    category: dataJson[i].category
                };
                dataArr.push(obj);
            }
            $("#autoSearch").catcomplete({
                delay: 0,
                source: dataArr,
                select: function (event, ui) {
                    $('#autoSearch').attr('dataId', ui.item.id)
                }
            });
        }


        function subjectTypeFn(n) {
            var subjectTypeList = $('#subjectType-main' + n).find('.subjectTypeList').html();
            // 增加招生批次明细
            $('#subjectTypeBtn'+n).on('click', function (event) {
                event.stopPropagation();
                event.preventDefault();
                $('#subjectType-main' + n).append('<div class="subjectTypeList" dataId="'+ n +'">' + subjectTypeList + '</div>');
            });
            $('#subjectType-main' + n).find('.deleteSubjectTypeBtn:eq(0)').hide();

            // 选择批次
            $('#subjectType-main' + n).on('change', '.subjectType', function (event) {
                event.stopPropagation();
                event.preventDefault();
                var optionV = $(this).find('option:checked').val();
                optionV == '00' ? $(this).next().hide() : $(this).next().show();
            });
            // 删除批次
            $('#subjectType-main' + n).on('click', '.deleteSubjectTypeBtn', function (event) {
                event.stopPropagation();
                event.preventDefault();
                $(this).parents('.subjectTypeList').remove();
            });
        }


        // 添加
        $("#addBtn").on(ace.click_event, function (e) {
            typeStr = "add";
            bootbox.dialog({
                title: "添加院校招生信息",
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
            catcompleteFn();
            subjectTypeFn(1);
            subjectTypeFn(2);

        });
        //修改
        $("#editBtn").on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            console.log(rowId)
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改院校招生信息",
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
            var rowData = CommonFn.getRowData(rowId)
            console.log(rowData)
            $('#autoSearch').val(rowData[0].name).attr('dataId',rowData[0].universityId)
            $('#selProvince2').find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
            $('#selYears2').find('option[value="' + rowData[0].year + '"]').attr('selected', 'selected');
            if(rowData[0].majorType=="1"){
                $('#subjectType1').show().attr('majorType','1');
                $('#subjectType2').hide();
                var oParent = $('#subjectType-main1');
                oParent.find('.subjectType option[value="' + rowData[0].batch + '"]').attr('selected', 'selected');
                oParent.find('.subjectTypeDetail').show();
                $('.planEnrollingNumber').val(rowData[0].planEnrollingNumber);
                $('.realEnrollingNumber').val(rowData[0].realEnrollingNumber);
                $('.highestScore').val(rowData[0].highestScore);
                $('.highestPrecedence').val(rowData[0].highestPrecedence);
                $('.lowestScore').val(rowData[0].lowestScore);
                $('.lowestPrecedence').val(rowData[0].lowestPrecedence);
                $('.averageScore').val(rowData[0].averageScore);
                $('.averagePrecedence').val(rowData[0].averagePrecedence);
                subjectTypeFn(1);

            }else if(rowData[0].majorType=="2"){
                $('#subjectType1').hide();
                $('#subjectType2').show().attr('majorType','2');;
                var oParent = $('#subjectType-main2');
                oParent.find('.subjectType option[value="' + rowData[0].batch + '"]').attr('selected', 'selected');
                oParent.find('.subjectTypeDetail').show();
                $('.planEnrollingNumber').val(rowData[0].planEnrollingNumber);
                $('.realEnrollingNumber').val(rowData[0].realEnrollingNumber);
                $('.highestScore').val(rowData[0].highestScore);
                $('.highestPrecedence').val(rowData[0].highestPrecedence);
                $('.lowestScore').val(rowData[0].lowestScore);
                $('.lowestPrecedence').val(rowData[0].lowestPrecedence);
                $('.averageScore').val(rowData[0].averageScore);
                $('.averagePrecedence').val(rowData[0].averagePrecedence);
                subjectTypeFn(2);
            }
            catcompleteFn();
            var dataJson = CommonFn.getAllSchool('');
            for(var a in dataJson) {
                if(rowData[0].universityId == dataJson[a].id){
                    $('#autoSearch').val(dataJson[a].label).attr('dataId',dataJson[a].id);
                }
            }

        });
        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//        添加
        var addEditFun = function () {
            var selProvinceV = $('#selProvince2 option:checked').val();
            var selYearsV = $("#selYears2").find('option:selected').val();
            var autoSearchId = $('#autoSearch').attr('dataId');

            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if ( autoSearchId=="" || autoSearchId==null) {
                CommonFn.tipsDialog('温馨提示', '请输入正确的院校名称');
                return false;
            }
            if (selYearsV == '00') {
                CommonFn.tipsDialog('温馨提示', '年份没有选择,请重新输入');
                return false;
            }


            if($('#subjectType1').attr('majorType')=='1' ){
                for(var i=0;i<$('#subjectType-main1 .subjectType').length;i++){
                    var values = $('#subjectType-main1 .subjectType:eq('+ i +')').find('option:selected').val();
                    var $parentDetail = $('#subjectType-main1 .subjectTypeDetail:eq('+ i +')');
                    var planEnrollingNumberV = $.trim($parentDetail.find('.planEnrollingNumber').val());
                    var realEnrollingNumberV = $.trim($parentDetail.find('.realEnrollingNumber').val());
                    var highestScoreV = $.trim($parentDetail.find('.highestScore').val());
                    var highestPrecedenceV = $.trim($parentDetail.find('.highestPrecedence').val());
                    var lowestScoreV = $.trim($parentDetail.find('.lowestScore').val());
                    var lowestPrecedenceV = $.trim($parentDetail.find('.lowestPrecedence').val());
                    var averageScoreV = $.trim($parentDetail.find('.averageScore').val());
                    var averagePrecedenceV = $.trim($parentDetail.find('.averagePrecedence').val());

                    if(values=="00" && $('#subjectType1').is(':visible')){
                        CommonFn.tipsDialog('温馨提示', '请选择文史类招生批次');
                        return false;
                    }
                    if(planEnrollingNumberV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写文史类计划数');
                        return false;
                    }
                    if(realEnrollingNumberV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写文史类录取数');
                        return false;
                    }
                    if(highestScoreV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写文史类最高分');
                        return false;
                    }
                    if(highestPrecedenceV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写文史类最高位次');
                        return false;
                    }
                    if(lowestScoreV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写文史类最低分');
                        return false;
                    }
                    if(lowestPrecedenceV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写文史类最低位次');
                        return false;
                    }
                    if(averageScoreV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写文史类平均分');
                        return false;
                    }
                    if(averagePrecedenceV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写文史类平均位次');
                        return false;
                    }
                }
            }


            if($('#subjectType2').attr('majorType')=='2'){
                for(var i=0;i<$('#subjectType-main2 .subjectType').length;i++){
                    var values = $('#subjectType-main2 .subjectType:eq('+ i +')').find('option:selected').val();
                    var $parentDetail = $('#subjectType-main2 .subjectTypeDetail:eq('+ i +')');
                    var planEnrollingNumberV = $.trim($parentDetail.find('.planEnrollingNumber').val());
                    var realEnrollingNumberV = $.trim($parentDetail.find('.realEnrollingNumber').val());
                    var highestScoreV = $.trim($parentDetail.find('.highestScore').val());
                    var highestPrecedenceV = $.trim($parentDetail.find('.highestPrecedence').val());
                    var lowestScoreV = $.trim($parentDetail.find('.lowestScore').val());
                    var lowestPrecedenceV = $.trim($parentDetail.find('.lowestPrecedence').val());
                    var averageScoreV = $.trim($parentDetail.find('.averageScore').val());
                    var averagePrecedenceV = $.trim($parentDetail.find('.averagePrecedence').val());

                    if(values=="00"  && $('#subjectType2').is(':visible')){
                        CommonFn.tipsDialog('温馨提示', '请选择理工类招生批次');
                        return false;
                    }
                    if(planEnrollingNumberV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写理工类计划数');
                        return false;
                    }
                    if(realEnrollingNumberV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写理工类录取数');
                        return false;
                    }
                    if(highestScoreV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写理工类最高分');
                        return false;
                    }
                    if(highestPrecedenceV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写理工类最高位次');
                        return false;
                    }
                    if(lowestScoreV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写理工类最低分');
                        return false;
                    }
                    if(lowestPrecedenceV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写理工类最低位次');
                        return false;
                    }
                    if(averageScoreV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写理工类平均分');
                        return false;
                    }
                    if(averagePrecedenceV==""){
                        CommonFn.tipsDialog('温馨提示', '请填写理工类平均位次');
                        return false;
                    }
                }
            }





            var batchData = [];
            var batchType = {}
            for(var i=0;i<$('.subjectTypeList').length;i++){
                    var universityMajorTypeV = $('.subjectTypeList:eq('+ i +')').attr('dataid');
                    var batchV = $('.subjectTypeList:eq('+ i +')').find('.subjectType option:checked').val();
                    var planEnrollingNumberV = $('.subjectTypeList:eq('+ i +')').find('.planEnrollingNumber').val();
                    var realEnrollingNumberV = $('.subjectTypeList:eq('+ i +')').find('.realEnrollingNumber').val();
                    var highestScoreV = $('.subjectTypeList:eq('+ i +')').find('.highestScore').val();
                    var highestPrecedenceV = $('.subjectTypeList:eq('+ i +')').find('.highestPrecedence').val();
                    var lowestScoreV = $('.subjectTypeList:eq('+ i +')').find('.lowestScore').val();
                    var lowestPrecedenceV = $('.subjectTypeList:eq('+ i +')').find('.lowestPrecedence').val();
                    var averageScoreV = $('.subjectTypeList:eq('+ i +')').find('.averageScore').val();
                    var averagePrecedenceV = $('.subjectTypeList:eq('+ i +')').find('.averagePrecedence').val();
                if(batchV!=="00"){
                    batchType = {
                        "majorType":universityMajorTypeV,
                        "batch": batchV,
                        "planEnrollingNumber": planEnrollingNumberV,
                        "realEnrollingNumber": realEnrollingNumberV,
                        "highestScore": highestScoreV,
                        "highestPrecedence": highestPrecedenceV,
                        "lowestScore": lowestScoreV,
                        "lowestPrecedence": lowestPrecedenceV,
                        "averageScore": averageScoreV,
                        "averagePrecedence": averagePrecedenceV
                    };
                    batchData.push(batchType);
                }
            };

            batchData = JSON.stringify(batchData)


            if(batchData.length==0){
                CommonFn.tipsDialog('温馨提示', '至少选择一类招生信息');
                return false;
            }



            var Datas = {
                "areaId": selProvinceV,
                "universityId": autoSearchId,
                "year": selYearsV,
                "batchContent":batchData ,
                "oper": typeStr
            };
            console.log(Datas);
            if (typeStr == 'edit') {
                Datas.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/${bizSys}/commonsave/${mainObj}',
                data: Datas,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };

    });
</script>
