<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var areaId = $('#selProvince').find("option:selected").val();
        var coursesubjectId = $('#selCourses').find("option:selected").val();
        var queryparam = $('#examKeyWord').val();
        var rules = [];
        if (areaId != '' && areaId != null && areaId != undefined && areaId != "00") {
            var rule = {
                'field': 'course.areaId',
                'op': 'eq',
                'data': areaId
            };
            rules.push(rule);
        }
        if (coursesubjectId != '' && coursesubjectId != null && coursesubjectId != undefined && coursesubjectId != '00') {
            var rule = {
                'field': 'course.subjectId',
                'op': 'eq',
                'data': coursesubjectId
            };
            rules.push(rule);
        }

        if (queryparam != '' && queryparam != null && queryparam != undefined && queryparam != '00') {
            var rule = {
                'field': 'queryparam',
                'op': 'eq',
                'data': queryparam
            };
            rules.push(rule);
        }
//        console.log(rules);
        return rules;
    }
    function searchLoad(flag) {
        var url = "/admin/${bizSys}/${mainObj}s";
        var page = $('#grid-table').getGridParam('page'); // current page
        var rows = $('#grid-table').getGridParam('rows'); // rows
        var sidx = $('#grid-table').getGridParam('sidx'); // sidx
        var sord = $('#grid-table').getGridParam('sord'); // sord


        if (page == null || page == "") {
            page = '1';
        }

        if (flag == 1 || typeof flag == "undefined") {
            page = '1';
        }

        if (rows == null || rows == "") {
            rows = '10';
        }

        var rules = buildRules();

        var filters = {
            'groupOp': 'AND',
            "rules": rules
        };

        $("#grid-table").jqGrid('setGridParam', {
            mtype: "POST",
            postData: "filters=" + JSON.stringify(filters),
            page: page,
            rows: rows,
            sidx: sidx,
            sord: sord}).trigger("reloadGrid");
    }
    // 搜索
    $("#search").click(function () {
        searchLoad();
    });
    /*
    *
    * 名师讲堂模块
    *
    * */







    var dialogLayer = ''
            +'<div class="modal fade in widget-box ui-widget  my-modal" id="dialogLayer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">'
            +'<div class="modal-dialog">'
            +'<div class="modal-content">'
            +'<div class="widget-header">'
            +'<h5 class="widget-title">添加</h5>'
            <#--<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span-->
            <#--class="sr-only">Close</span></button>-->
            +'</div>'
            +'<div class="modal-body">'
            +'<div class="row" >'
            +'<div class="col-xs-12">'
            +'<form class="form-horizontal" role="form" id="auditoriumModal">'
            +'<div class="form-group">'
            +'<label class="col-sm-2 control-label no-padding-right"> 省份：</label>'
            +'<div class="col-sm-4">'
            +'<select class="form-control" id="selProvince2"></select>'
            +'</div>'
            +'</div>'
            +'<div class="form-group">'
            +'<label class="col-sm-2 control-label no-padding-right"> 科目类别：</label>'
            +'<div class="col-sm-4">'
            +'<select class="form-control" id="selCourses2">'
            +'</select>'
            +'</div>'
            +'</div>'
            +'<div class="form-group">'
            +'<label class="col-sm-2 control-label no-padding-right" for="teacherName"> 主讲老师：</label>'
            +'<div class="col-sm-10">'
            +'<input type="text" id="teacherName" placeholder="请输入主讲老师" class="form-control"/>'
            +'</div>'
            +'</div>'
            +'<div class="form-group">'
            +'<label class="col-sm-2 control-label no-padding-right" for="expertsIntro"> 专家介绍：</label>'
            +'<div class="col-sm-10">'
            +'<textarea class="form-control" id="expertsIntro" placeholder="请输入专家介绍"></textarea>'
            +'</div>'
            +'</div>'
            +'<div class="form-group">'
            +'<label class="col-sm-2 control-label no-padding-right" for="sectionTitle"> 视屏名称：</label>'
            +'<div class="col-sm-10">'
            +'<input type="text" id="sectionTitle" placeholder="请输视屏名称" class="form-control"/>'
            +'</div>'
            +'</div>'


            +'<div class="form-group">'
            +'<label class="col-sm-2 control-label no-padding-right" for="expertsIntro"> 视屏封面图片：</label>'
            +'<div class="col-sm-10">'

            +'<div id="uploader1" class="wu-example">'
            +'<div class="uploader-tips">(只能上传一个图片,可拖拽文件,大小小于3M)</div>'
            +'<div class="queueList">'
            +'<div id="dndArea" class="placeholder">'
            +'<div id="uploaderBtn1">点击上传</div>'
            +'</div>'
            +'</div>'
            +'<div class="statusBar" style="display:none;">'
            +'<div class="progress">'
            +'<span class="text">0%</span>'
            +'<span class="percentage"></span>'
            +'</div>'
            +'<div class="info"></div>'
            +'<div class="btns">'
            +'<div class="uploadBtn1">上传封面</div>'
            +'</div>'
            +'</div>'
            +'</div>'
            +'</div>'
            +'</div>'
            +'<input type="hidden" value="" id="imgUrlData">'


            +'<div class="form-group">'
            +'<label class="col-sm-2 control-label no-padding-right" for="expertsIntro"> 视屏上传列表：</label>'
            +'<div class="col-sm-10">'

            +'<div id="uploader2" class="wu-example">'
            <#--<div class="uploader-tips">(只能上传一个图片,可拖拽文件,大小小于3M)</div>-->
            +'<div class="queueList">'
            +'<div id="dndArea" class="placeholder">'
            +'<div id="uploaderBtn2">点击上传</div>'
            +'</div>'
            +'</div>'
            +'<div class="statusBar" style="display:none;">'
            +'<div class="progress">'
            +'<span class="text">0%</span>'
            +'<span class="percentage"></span>'
            +'</div>'
            +'<div class="info"></div>'
            +'<div class="btns">'
            +'<div class="uploadBtn2">上传视频</div>'
            +'</div>'
            +'</div>'
            +'</div>'
            +'</div>'
            +'</div>'
            +'<input type="hidden" value="" id="videoData" />'
            +'</form>'
            +'</div>'
            +'</div>'
            +'</div>'

            +'<div class="modal-footer">'
            +'<button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal">取消</button>'
            +'<button type="button" id="submitBtn" class="btn btn-primary">确定</button>'
            +'</div>'
            +'</div>'

            +'</div>'
            +'</div>'
















    jQuery(function ($) {
        var typeStr;
        var rowId;
        var UI = {
            $addBtn: $('#addBtn')
            , $submitBtn: $('#submitBtn')
            , $editBtn: $('#editBtn')
            , $selProvince2: $('#selProvince2')
            , $selCourses2: $('#selCourses2')
            , $teacherName: $('#teacherName')
            , $expertsIntro: $('#expertsIntro')
            , $sectionTitle: $('#sectionTitle')

        };
//        课程,年份,省份
        $('#selCourses,#selCourses2').append(CommonFn.getSubject());
        $('#selYears').append(CommonFn.getYear());
        $('#selProvince,#selProvince2').html(CommonFn.getProvince());



        uploadFun1();
        uploadFun2();
        var addEditFun = function () {
            var selProvinceV = $('#selProvince2 option:checked').val(),
                    selCourses2V = $('#selCourses2 option:checked').val(),
                    teacherNameV = $.trim($('#teacherName').val()),
                    expertsIntroV = $('#expertsIntro').val(),
                    sectionTitleV = $('#sectionTitle').val(),
                    imgUrlDataV = $('#imgUrlData').val(),
                    videoDataV = $('#videoData').val();

            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if (selCourses2V == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择科目');
                return false;
            }
            if (teacherNameV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择主讲老师');
                return false;
            }
            if (expertsIntroV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择专家介绍');
                return false;
            }
            if (sectionTitleV == "") {
                CommonFn.tipsDialog('温馨提示', '请选择视频名称');
                return false;
            }
            if (imgUrlDataV == "") {
                CommonFn.tipsDialog('温馨提示', '请上传视频封面');
                return false;
            }
            if (videoDataV == "") {
                CommonFn.tipsDialog('温馨提示', '请上传视频');
                return false;
            }


            alert(videoDataV)
            alert(imgUrlDataV)

            var addData = {
                oper: typeStr,
                classifyId: '${mainObj}' == 'auditorium' ? '1' : '0',
                managerId: '${mainObj}' == 'auditorium' ? '1' : '0',
                subjectId: selCourses2V,
                teacher: teacherNameV,
                title: sectionTitleV,
                frontCover: imgUrlDataV,
                subcontent: expertsIntroV,
                areaId: selProvinceV,
                videoSectionDTOs:videoDataV
            };
            console.log(addData)

            $.ajax({
                type: "POST",
                url: '/admin/${bizSys}/commonsave/${mainObj}',
                data: addData,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                        $('#dialogLayer').modal("hide");
                    }
                }
            });
        };


        // 添加
//        uploadFun1();
//        uploadFun2();
        UI.$addBtn.click(function () {
            $('#imglist').html('').hide();
            $('#dialogLayer').modal('show')
                    .find('input').val('')
                    .end()
                    .find('select option[value="00"]').attr('selected','selected')
                    .end()
                    .find('.filelist').html('')
                    .end()
                    .find('textarea').val('')
                    .end()
                    .find('.statusBar').hide();
            typeStr = 'add';
            uploadFun1();
            uploadFun2();
        });

//        var editVideoList = [];
//        var editListJSON = {};
        UI.$editBtn.click(function (e) {
            e.preventDefault();
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            // 当前行数据
            var rowData = CommonFn.getRowData(rowId);
            $('#dialogLayer').modal('show')
                    .find('input').val('')
                    .end()
                    .find('select option[value="00"]').attr('selected','selected')
                    .end()
                    .find('.filelist').html('')
                    .end()
                    .find('textarea').val('')
                    .end()
                    .find('.statusBar').hide();

            console.log(rowData);
            $('#selProvince2').find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
            $('#selCourses2').find('option[value="' + rowData[0].subjectId + '"]').attr('selected', 'selected');
            $('#teacherName').val(rowData[0].teacher);
            $('#sectionTitle').val(rowData[0].title);
            $('#expertsIntro').val(rowData[0].subcontent);
            $('#uploader1').hide();
            $('#imglist').html('<img width="110" height="100" src="'+ rowData[0].frontCover +'"/><a href="javascript:;" id="updateImg">修改</a>');

            $('#imglist').show();
            $('#updateImg').on('click',function(){
                $(this).parent().hide();
                $('#uploader1').show();
            });
            $('#imgUrlData').val(rowData[0].frontCover);

            // 视频
            var videoData = rowData[0].videoSectionDTO;
            var videoArr = [];
            for(var i=0;i<videoData.length;i++){
                videoArr.push('<div class="videoLi"><span class="vName" dataUrl="'+ videoData[i].fileUrl +'">'+ videoData[i].sectionName +'</span><a target="_blank" class="vUrl" href="'+ videoData[i].fileUrl +'">点击查看</a><a href="javascript:;" class="close-btn" dataUrl="'+ videoData[i].fileUrl +'">删除</a></div>')
                console.log(videoArr.join(''));
                $('#videolist').html(videoArr);
            }

            cx();
            $('#videolist').on('click','.close-btn',function(){
                $(this).parent().remove();
                var name = $(this).attr('dataUrl');
                var arrs = JSON.parse($('#videoData').val());
                for (var i = 0; i < arrs.length; i++) {
                    var cur_person = arrs[i];
                    if (cur_person.fileUrl == name) {
                        arrs.splice(i, 1);
                    }
                };
                $('#videoData').val(JSON.stringify(arrs))
            });
            uploadFun1();
            uploadFun2();

        });

        UI.$submitBtn.click(function (e) {
            e.stopPropagation();
            e.preventDefault();
            addEditFun();
        });




        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
    });//$ end




    function cx(){
        $('#videoData').val('');
        var newBoj = {};
        var vArr = [];
        var videoLiLen = $('.videoLi').length;
        if(videoLiLen > 0){
            for(var j=0;j< videoLiLen;j++){
                var nName = $('.videoLi:eq('+ [j] +')').find('.vName').text();
                var nUrl = $('.videoLi:eq('+ [j] +')').find('.vName').attr('dataUrl');
                newBoj = {
                    sectionName: nName,
                    fileUrl: nUrl
                };
                vArr.push(newBoj);
            }
            $('#videoData').val(JSON.stringify(vArr))
        }
    }









    function auditoriumValidate() {
        var selProvinceV = $('#selProvince2 option:checked').val(),
                selCourses2V = $('#selCourses2 option:checked').val(),
                teacherNameV = $.trim($('#teacherName').val()),
                expertsIntroV = $('#expertsIntro').val(),
                sectionTitleV = $('#sectionTitle').val(),
                imgUrlDataV = $('#imgUrlData').val(),
                videoDataV = $('#videoData').val();

        if (selProvinceV == "00") {
            CommonFn.tipsDialog('温馨提示', '请选择省份');
            return false;
        }
        if (selCourses2V == "00") {
            CommonFn.tipsDialog('温馨提示', '请选择科目');
            return false;
        }
        if (teacherNameV == "") {
            CommonFn.tipsDialog('温馨提示', '请选择主讲老师');
            return false;
        }
        if (expertsIntroV == "") {
            CommonFn.tipsDialog('温馨提示', '请选择专家介绍');
            return false;
        }
        if (sectionTitleV == "") {
            CommonFn.tipsDialog('温馨提示', '请选择视频名称');
            return false;
        }
        if (imgUrlDataV == "") {
            CommonFn.tipsDialog('温馨提示', '请上传视频封面');
            return false;
        }
        if (videoDataV == "") {
            CommonFn.tipsDialog('温馨提示', '请上传视频');
            return false;
        }
    }
    function uploadFun1() {
        var $wrap = $('#uploader1'),

        // 图片容器
                $queue = $('<ul class="filelist"></ul>').appendTo($wrap.find('.queueList')),

        // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find('.statusBar'),

        // 文件总体选择信息。
                $info = $statusBar.find('.info'),

        // 上传按钮
                $upload = $wrap.find('.uploadBtn1'),

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
            pick: '#uploaderBtn1',
            // 拖拽
            dnd: '#uploader1 .queueList',
            // 为通过粘贴来添加截屏的图片
            paste: document.body,
            // 上传文件的类型
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            },

            // swf文件路径
            swf: '${path}/assets/js/webuploader-0.1.5/Uploader.swf',

            disableGlobalDnd: true,

            // 是否要分片处理大文件上传
            chunked: true,
            // server: 'http://webuploader.duapp.com/server/fileupload.php',
            server: 'http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile?userId=gk360&dirId=0&productCode=gk360&bizSystem=gk360&spaceName=gk360',
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
//                        $('#filePicker2').removeClass('element-invisible');
                    $queue.parent().addClass('filled');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
//                        $('#filePicker2').addClass('element-invisible');
                    $progress.show();
                    $upload.text('暂停上传');
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text('继续上传');
                    break;

                case 'confirm':
                    $progress.hide();
//                    $upload.text('开始上传').addClass('disabled');
//                        $upload.text('开始上传');

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
            console.log(response.bizData.file.fileUrl);
            $('#imgUrlData').val(response.bizData.file.fileUrl);

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
    function uploadFun2() {
        var $wrap = $('#uploader2'),

        // 图片容器
                $queue = $('<ul class="filelist"></ul>').appendTo($wrap.find('.queueList')),

        // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find('.statusBar'),

        // 文件总体选择信息。
                $info = $statusBar.find('.info'),

        // 上传按钮
                $upload = $wrap.find('.uploadBtn2'),

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
            pick: '#uploaderBtn2',
            // 拖拽
            dnd: '#uploader2 .queueList',
            // 为通过粘贴来添加截屏的图片
            paste: document.body,
            // 上传文件的类型
            accept: {
                title: 'Video'
//                ,extensions:'mp4,rmvb,swf,avi,3gp'
//                ,mineTypes:'video/*'
            },

            // swf文件路径
            swf: '${path}/assets/js/webuploader-0.1.5/Uploader.swf',

            disableGlobalDnd: true,

            // 是否要分片处理大文件上传
            chunked: true,
            // server: 'http://webuploader.duapp.com/server/fileupload.php',
            server: 'http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile?userId=gk360&dirId=0&productCode=gk360&bizSystem=gk360&spaceName=gk360',
            // 验证文件总数量, 超出则不允许加入队列
            fileNumLimit: 10,
            // 验证文件总大小是否超出限制, 超出则不允许加入队列
//                fileSizeLimit: 5 * 1024 * 1024,
            // 验证单个文件大小是否超出限制, 超出则不允许加入队列
            fileSingleSizeLimit: 300 * 1024 * 1024
        });
        // 当有文件添加进来时执行，负责view的创建



        function addFile(file) {

            var $li = $('<li id="' + file.id + '">' +
                            '<p class="title">名称:'+ file.name.substring(0,(file.name.length-4)) + '</p>' +
//                            '<p class="imgWrap"></p>' +
                            '第<input type="text" value="" class="numberTxt" />节' +
                            '<p class="progress"><span></span></p>' +
                            '</li>'),
                    $btns = $('<div class="file-panel">' +
                            '<span class="cancel">删除</span>' +
//                            '<span class="rotateRight">向右旋转</span>' +
//                            '<span class="rotateLeft">向左旋转</span>' +
                            '</div>').appendTo($li),


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

//            $li.on('mouseenter', function () {
//                $btns.stop().animate({height: 30});
//            });
//
//            $li.on('mouseleave', function () {
//                $btns.stop().animate({height: 0});
//            });

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
//                        $('#filePicker2').removeClass('element-invisible');
                    $queue.parent().addClass('filled');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
//                        $('#filePicker2').addClass('element-invisible');
                    $progress.show();
                    $upload.text('暂停上传');
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text('继续上传');
                    break;

                case 'confirm':
                    $progress.hide();
                    $upload.text('开始上传');
//                    $upload.text('开始上传').addClass('disabled');

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
                if ($('.state-complete').length == 10) {
                    CommonFn.tipsDialog('温馨提示', '只能上传10个文件');
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
//        定义一个数组用来存放视屏list
        var videoList = [];
        var listJSON = {};
        uploader.onUploadSuccess = function (file, response) {
            var fileUrl = response.bizData.file.fileUrl;
            listJSON = {
                sectionName: file.name.substring(0,(file.name.length-4)),
                fileUrl: fileUrl
            };

            videoList.push(listJSON);

            var newBoj = {};
            var videoLiLen = $('.videoLi').length;
            if(videoLiLen > 0){
                for(var j=0;j< videoLiLen;j++){
                    var nName = $('.videoLi:eq('+ [j] +')').find('.vName').text();
                    var nUrl = $('.videoLi:eq('+ [j] +')').find('.vName').attr('dataUrl');
                    newBoj = {
                        sectionName: nName,
                        fileUrl: nUrl
                    };
                    videoList.push(newBoj);
                }
            }
            $('#videoData').val(JSON.stringify(videoList));
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

</script>
