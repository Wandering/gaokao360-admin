<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var universityeducationLevel = $('#selEduLevel').val();
        var universityprovinceName = $('#selProvince').find('option:selected').val();
        var universityprovinceId = $('#selProvince').val();
        var queryparam = $('#EduLevelKeyWord').val();
        var rules = [];
        if (universityeducationLevel != '' && universityeducationLevel != null && universityeducationLevel != undefined&&universityeducationLevel!=='00') {
            var rule = {
                'field': 'university.educationLevel',
                'op': 'eq',
                'data': universityeducationLevel
            }
            rules.push(rule);
        }
        if (universityprovinceName != '' && universityprovinceName != null && universityprovinceName != undefined&&universityprovinceName!=='请选择省份' && universityprovinceName!=='00') {
            var rule = {
                'field': 'university.provinceName',
                'op': 'eq',
                'data': universityprovinceName
            }
            rules.push(rule);
        }
        if (queryparam != '' && queryparam != null && queryparam != undefined&&queryparam!=='00') {
            var rule = {
                'field': 'queryparam',
                'op': 'lk',
                'data': queryparam
            }
            rules.push(rule);
        }
        return rules;
    }


    $("#search").click(function () {
        searchLoad();

    });

    /*
    *
    * 院校基本信息模块
    *
    * */

    jQuery(function () {
        var typeStr;
        var rowId;












        var UI = {
            $selProvince: $('#selProvince')
            , $selEduLevel: $('#selEduLevel')
            , $addBtn: $('#addBtn')
            , $submitBtn: $('#submitBtn')
            , $editBtn: $('#editBtn')
        };
        var schoolInfoDOM = {
            $schoolName: $('#schoolName')
            , $schoolCode: $('#schoolCode')
            , $schoolRank: $('#schoolRank')
            , $schoolStatic: $('#schoolStatic')
            , $selEduLevel2: $('#selEduLevel2')
            , $schoolType: $('#schoolType')
            , $schoolOwn: $('#schoolOwn')
            , $schoolWeb: $('#schoolWeb')
            , $schoolInProvince2: $('#schoolInProvince2')
            , $schoolAddress: $('#schoolAddress')
            , $schoolTel: $('#schoolTel')
            , $schoolIntroduce: $('#schoolIntroduce')
            , $schoolArticle: $('#schoolArticle')
        };
//        获取省份
        var  provinceData = CommonFn.getProvince();
        $('#selProvince,#schoolInProvince2').html(provinceData);
//        获取学历层次
        var eduLevelData = CommonFn.getEdulevel();
        $('#selEduLevel,#selEduLevel2').html(eduLevelData);

        var universityTypeData = CommonFn.getuniversityType();




//        实例化院校简介,院校章程富文本编辑器

//        添加院校层次信息
//        UI.$addBtn.click(function () {
//            $('#filelist').html();
//            typeStr = "add";
//            $('#edit_answer_modal').modal('show');
//        });//添加end


        var  dialogHtml = ''
                + '<div class="row" id="dialogHtml">'
                + '    <div class="col-xs-12">'
                + '      <form class="form-horizontal" role="form">'
                    +'       <div class="form-group"> '
                    +'          <label for="schoolName" class="col-sm-2 control-label">院校名称</label>'
                    +'          <div class="col-sm-6">'
                    +'              <input type="text" class="form-control" id="schoolName" placeholder="院校名称不能超过15个字">'
                    +'          </div>'
                    +'       </div>'
                    +'       <div class="form-group">'
                    +'          <label class="col-sm-2 control-label no-padding-right" for="expertsIntro">院校图片</label>'
                    +'          <div class="col-sm-9">'
                        +'        <div id="uploader1" class="wu-example">'
                        +'          <div class="uploader-tips">(只能上传一个图片,可拖拽文件,大小小于3M)</div>'
                            +'        <div class="queueList">'
                                +'        <div id="dndArea" class="placeholder">'
                                +'        <div id="uploaderBtn1">点击上传</div>'
                                +'        </div>'
                                +'        </div>'
                                +'        <div class="statusBar" style="display:none;">'
                                    +'        <div class="progress">'
                                    +'        <span class="text">0%</span>'
                                    +'        <span class="percentage"></span>'
                                    +'        </div>'
                                    +'          <div class="info"></div>'
                                        +'        <div class="btns">'
                                        +'            <div class="uploadBtn1">上传封面</div>'
                                        +'        </div>'
                                    +'          </div>'
                                +'        </div>'
                            +'        </div>'
                        +'        </div>'
                    +'          <input type="hidden" value="" id="imgUrlData">'
                +'                    <div class="form-group">'
                +'                        <label for="schoolCode" class="col-sm-2 control-label">院校代码</label>'
                +'                        <div class="col-sm-6">'
                +'                            <input type="text" class="form-control" id="schoolCode"'
                +'                                   placeholder="8位数字/符号/英文字母">'
                +'                        </div>'
                +'                    </div>'
                +'                    <div class="form-group">'
                +'                        <label for="schoolRank" class="col-sm-2 control-label">院校排名</label>'
                +'                        <div class="col-sm-6">'
                +'                            <input type="text" class="form-control" id="schoolRank" placeholder="请输入6位数字">'
                +'                        </div>'
                +'                        </div>'
                +'                        <div class="form-group">'
                +'                        <label for="schoolStatic" class="col-sm-2 control-label">院校特征</label>'
                +'                        <div class="col-sm-6">'
                +'                        <input type="text" class="form-control" id="schoolStatic"'
                +'                        placeholder="请输入内容不能超过10个字">'
                +'                        </div>'
                +'                        </div>'
                +'                        <div class="form-group">'
                +'                        <label for="selEduLevel2" class="col-sm-2 control-label">学历层次</label>'
                +'                        <div class="col-sm-6">'
                +'                        <select class="form-control" id="selEduLevel2">'+ eduLevelData +'</select>'
                +'                        </div>'
                +'                        </div>'
                +'                        <div class="form-group">'
                +'                        <label for="schoolType" class="col-sm-2 control-label">院校类型</label>'
                +'                        <div class="col-sm-6">'
                +'                        <select class="form-control" id="universityType">'+ universityTypeData +'</select>'
                +'                        </div>'
                +'                        </div>'
                +'                        <div class="form-group">'
                +'                        <label for="schoolOwn" class="col-sm-2 control-label">院校隶属</label>'
                +'                        <div class="col-sm-6">'
                +'                        <input type="text" class="form-control" id="schoolOwn"'
                +'                        placeholder="请输入内容不能超过10个字">'
                +'                        </div>'
                +'                        </div>'
                +'                        <div class="form-group">'
                +'                        <label for="schoolWeb" class="col-sm-2 control-label">院校网址</label>'
                +'                        <div class="col-sm-6">'
                +'                        <input type="text" class="form-control" id="schoolWeb" placeholder="如:http://www.baidu.com">'
                +'                        </div>'
                +'                        </div>'
                +'                        <div class="form-group">'
                +'                        <label for="schoolInProvince" class="col-sm-2 control-label">所在省份</label>'
                +'                        <div class="col-sm-6">'
                +'                        <select class="form-control" id="schoolInProvince2">'+ provinceData +'</select>'
                +'                        </div>'
                +'                        </div>'
                +'                        <div class="form-group">'
                +'                        <label for="schoolAddress" class="col-sm-2 control-label">院校地址</label>'
                +'                        <div class="col-sm-6">'
                +'                        <textarea cols="30" rows="3" class="form-control" id="schoolAddress"'
                +'                        placeholder="请输入内容不能超过120个字"></textarea>'
                +'                        </div>'
                +'                        </div>'
                +'                        <div class="form-group">'
                +'                        <label for="schoolPic" class="col-sm-2 control-label">联系电话</label>'
                +'                        <div class="col-sm-6">'
                +'                        <input type="text" class="form-control" id="schoolTel"'
                +'                        placeholder="区号+号码，如:029-6666666">'
                +'                        </div>'
                +'                        </div>'
//                +'                        <div class="form-group">'
//                +'                        <label for="schoolIntroduce" class="col-sm-2 control-label">院校简介</label>'
//                +'                        <div class="col-sm-10">'
//                +'                        <div id="schoolIntroduce" class="wysiwyg-editor"></div>'
//                +'                        </div>'
//                +'                        </div>'
//                +''
//                +'                        <div class="form-group">'
//                +'                        <label for="schoolArticle" class="col-sm-2 control-label">院校章程</label>'
//                +'                        <div class="col-sm-10">'
//                +'                        <div class="wysiwyg-editor" id="schoolArticle"></div>'
//                +'                        </div>'
//                +'                        </div>'
                +'                        </form>'
                +'                        </div>'
                +'                        </div>';

        $("#addBtn").on(ace.click_event, function (e) {
            typeStr = "add";
            bootbox.dialog({
                title: "添加院校基本信息",
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
            uploadFun1();
            CommonFn.renderTextarea('#schoolIntroduce');
            CommonFn.renderTextarea('#schoolArticle');
        });

        //修改
        $("#editBtn").on(ace.click_event, function () {
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
                        "callback": addEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
            uploadFun1();
            CommonFn.renderTextarea('#schoolIntroduce');
            CommonFn.renderTextarea('#schoolArticle');
            var rowData = CommonFn.getRowData(rowId);
            console.log(rowData)
            $('#schoolName').val(rowData[0].name);
            $('#imgUrlData').val(rowData[0].photoUrl);
            $('#schoolCode').val(rowData[0].code);
            $('#schoolRank').val(rowData[0].rank);
            $('#schoolStatic').val(rowData[0].property);
            $('#selEduLevel2').find('option[value="' + rowData[0].educationLevel + '"]').attr('selected', 'selected');
            $('#universityType').find('option[value="' + rowData[0].type + '"]').attr('selected', 'selected');
            $('#schoolOwn').val(rowData[0].subjection);
            $('#schoolWeb').val(rowData[0].url);
            $('#schoolInProvince2').find('option[value="' + rowData[0].areaid + '"]').attr('selected', 'selected');
            $('#schoolAddress').val(rowData[0].address);
            $('#schoolTel').val(rowData[0].contactPhone);
//            var universityIntroText = CommonFn.getContentHtml(rowData[0].universityIntro).join('');
//            $('#schoolIntroduce').html(universityIntroText);
//            var entranceIntroText = CommonFn.getContentHtml(rowData[0].entranceIntro).join('');
//            $('#schoolArticle').html(entranceIntroText);
        });
        //删除院校基本信息
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
        //取消提交
        //验证函数
        function addEditFun() {
        //院校名称
            var schoolName = $.trim($('#schoolName').val());
            if (schoolName == "") {
                CommonFn.tipsDialog('温馨提示', '院校名称输入不能为空');
                return false;
            }
            if (schoolName.length > 15) {
                CommonFn.tipsDialog('温馨提示', '院校名称输入不能大于15个字');
                return false;
            }
            if($('#imgUrlData').val()==""){
                CommonFn.tipsDialog('温馨提示', '请上传院校图片');
                return false;
            }
        //院校代码
            var schoolCode = $('#schoolCode').val().trim();
            var regCode = /^[0-9a-zA-Z]*$/g;
            if (schoolCode.length == '') {
                CommonFn.tipsDialog('温馨提示', '院校代码不能为空');
                return false;
            }
            if (schoolCode.length > 8 || !regCode.test(schoolCode)) {
                CommonFn.tipsDialog('温馨提示', '院校代码只能为8位数字/符号/英文字母');
                return false;
            }
//                院校排名
            var regNum = /^[0-9]*$/;
            var schoolRank = $('#schoolRank').val().trim();
            if (schoolRank.length == '') {
                CommonFn.tipsDialog('温馨提示', '院校排名不能为空');
                return false;
            }
            if (schoolRank.length > 6 || !regNum.test(schoolRank)) {
                CommonFn.tipsDialog('温馨提示', '院校排名输入只能位数字,且不能大于6位数字');
                return false;
            }
//                院校特征
            var schoolStatic = $('#schoolStatic').val();
            if (schoolStatic.length == '') {
                CommonFn.tipsDialog('温馨提示', '院校特征输入不能为空');
                return false;
            }
            if (schoolStatic.length > 10) {
                CommonFn.tipsDialog('温馨提示', '院校特征输入不能大于10个字');
                return false;
            }

            var selEduLevel2V = $('#selEduLevel2').find('option:selected').val();

            if(selEduLevel2V=="00"){
                CommonFn.tipsDialog('温馨提示', '请选择学历层次');
                return false;
            }
//                院校类型

            var universityTypeV = $('#universityType').find('option:selected').val();
            if (universityTypeV == '00') {
                CommonFn.tipsDialog('温馨提示', '请选择院校类型');
                return false;
            }
//                院校隶属
            var schoolOwn = $('#schoolOwn').val().trim();
            if (schoolOwn.length == '') {
                CommonFn.tipsDialog('温馨提示', '院校隶属输入不能为空');
                return false;
            }
            if (schoolOwn.length > 10) {
                CommonFn.tipsDialog('温馨提示', '院校隶属输入不能大于10个字');
                return false;
            }
//                院校网址
            var regWeb = /((https|http|ftp|rtsp|mms):\/\/)?(([0-9a-z_!~*'().&=+$%-]+:)?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)/g;
            var schoolWeb = $('#schoolWeb').val().trim();
            if (schoolWeb.length == '') {
                CommonFn.tipsDialog('温馨提示', '院校网址输入不能为空');
                return false;
            }
            if (!regWeb.test(schoolWeb)) {
                CommonFn.tipsDialog('温馨提示', '院校网址输入不符合网络地址规范');
                return false;
            }
            if (schoolWeb.length > 30) {
                CommonFn.tipsDialog('温馨提示', '院校网址输入不能大于30个字');
                return false;
            }
//                所在省份
            var schoolInProvince2 = $("#schoolInProvince2").find('option:selected').val();
            if (schoolInProvince2 == '00') {
                CommonFn.tipsDialog('温馨提示', '所在省份必须选择');
                return false;
            }
//                院校地址
            var schoolAddress = $('#schoolAddress').val().trim();
            if (schoolAddress.length == '') {
                CommonFn.tipsDialog('温馨提示', '输入院校地址不能为空');
                return false;
            }
            if (schoolAddress.length > 120) {
                CommonFn.tipsDialog('温馨提示', '输入院校地址内容不能超过120个字');
                return false;
            }
//                联系电话
            var regPhone = /^0\d{2,3}-?\d{7,8}$/
            var schoolTel = $('#schoolTel').val().trim();
            if (schoolTel.length == '') {
                CommonFn.tipsDialog('温馨提示', '电话号码不能为空');
                return false;
            }
            if (!regPhone.test(schoolTel)) {
                CommonFn.tipsDialog('温馨提示', '电话号码输入有误');
                return false;
            }

//                院校简介
//            var schoolIntroduce = $('#schoolIntroduce').html().trim();
//            if (schoolTel.length == '') {
//                CommonFn.tipsDialog('温馨提示', '院校简介输入不能为空');
//                return false;
//            }
////                院校章程
//            var schoolArticle = $('#schoolArticle').html().trim();
//            if (schoolArticle.length == '') {
//                CommonFn.tipsDialog('温馨提示', '院校章程输入不能为空');
//                return false;
//            }
//            var schoolIntroduceV = $('#schoolIntroduce').html();
//
//            var schoolIntroduceHtml = CommonFn.getTextareaData(schoolIntroduceV).join(''),
//                    schoolIntroduceHtmlUrl = CommonFn.getTextareaUrlData(schoolIntroduceHtml),
//                    schoolIntroduceUrl = schoolIntroduceHtmlUrl[0],
//                    schoolIntroducelId = schoolIntroduceHtmlUrl[1];
//
//            var schoolArticleV = $('#schoolArticle').html();
//            var schoolArticleHtml = CommonFn.getTextareaData(schoolArticleV).join(''),
//                    schoolArticleHtmlUrl = CommonFn.getTextareaUrlData(schoolArticleHtml),
//                    schoolArticleUrl = schoolArticleHtmlUrl[0],
//                    schoolArticleId = schoolArticleHtmlUrl[1];

            var addUniversityData = {
                oper: typeStr
                , code: $.trim($('#schoolCode').val())
                , name: $.trim($('#schoolName').val())
                , photoUrl: $('#imgUrlData').val()
                , rank: $.trim($('#schoolRank').val())
                , property: $.trim($('#schoolStatic').val())
                , educationLevel: selEduLevel2V
                , type: universityTypeV
                , subjection: $.trim($('#schoolOwn').val())
                , url: $.trim($('#schoolWeb').val())
                , areaid: schoolInProvince2
                , provinceName: $('#schoolInProvince2 option:checked').text()
                , address: $.trim($('#schoolAddress').val())
                , contactPhone: $.trim($('#schoolTel').val())
//                , universityIntro:schoolIntroduceUrl
//                , entranceIntro:schoolArticleUrl
            };


            if (typeStr == 'edit') {
                addUniversityData.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/gaokao360/ex/commonsave/${mainObj}',
                data: addUniversityData,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });



        }//验证函数end



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





    });//$ end

</script>