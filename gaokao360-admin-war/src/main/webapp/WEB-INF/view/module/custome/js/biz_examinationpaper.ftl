<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var papersubjectId = $('#courseName').val();
        var paperyears = $('#status').val();
        var paperareaId = $('#classfyId').val();
        var paperpaperName = $('#classfyId').val();
        var rules = [];
        if (papersubjectId != '' && papersubjectId != null && papersubjectId != undefined && papersubjectId != "00") {
            var rule = {
                'field': 'paper.subjectId',
                'op': 'eq',
                'data': papersubjectId
            }
            rules.push(rule);
        }
        if (paperyears != '' && paperyears != null && paperyears != undefined && paperyears != "00") {
            var rule = {
                'field': 'paper.years',
                'op': 'eq',
                'data': paperyears
            }
            rules.push(rule);
        }
        if (paperareaId != '' && paperareaId != null && paperareaId != undefined && paperyears != "00") {
            var rule = {
                'field': 'paper.areaId',
                'op': 'eq',
                'data': paperareaId
            }
            rules.push(rule);
        }
        if (paperpaperName != '' && paperpaperName != null && paperpaperName != undefined && paperpaperName != "00") {
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
//        搜索
        $("#search").click(function () {
            searchLoad();

        });
//        dom对象
        var UI = {
            $selCourses: $('#selCourses'),
            $selYears: $('#selYears'),
            $selProvince: $('#selProvince'),

            $addExamBtn: $('#addExamBtn'),
            $editExamBtn: $('#editExamHotBtn'),
            $deleteHotBtn: $('#deleteHotBtn'),

            $province2: $('#province2'),
            $subjectName: $('#subjectName'),
            $examName: $('#examName'),
            $examYear: $('#examYear'),
            $uploaderSWF: $('#uploaderSWF')
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
//        获取省份
        var provinceHtml = '';
        examPaperObj.getData(examPaperObj.url.getProvinceUrl, {}, function (res) {
            if (res.rtnCode == '0000000') {
                var dataProvince = res.bizData;
                $.each(dataProvince, function (i, v) {
                    provinceHtml += '<option value="' + v.id + '">' + v.name + '</option>';
                });
                UI.$selProvince.append(provinceHtml);
            }
        });
//        获取年份
        examPaperObj.getData(examPaperObj.url.getYear, {}, function (res) {
            console.info(res);
            if (res.rtnCode == '0000000') {
                var dataYear = res.bizData;
                var yearHtml = '';
                for (var i in dataYear) {
                    yearHtml += '<option>' + dataYear[i] + '</option>';
                }
                UI.$selYears.append(yearHtml);
            }
        });
//        添加密卷
        UI.$addExamBtn.on(ace.click_event, function () {
            var year = new Date().getFullYear();
            var rangeYearHtml = '';
            for (var i = 1990; i <= year; i++) {
                rangeYearHtml += '<option>' + i + '</option>'
            }
            var dialogHtml = ''
                    + '<div class="bootbox-body">'
                    + '<div class="row">'
                    + '<div class="col-xs-12">'
                    + '<div class="form-horizontal" role="form">'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right">选择省份：</label>'
                    + '<div class="col-sm-2">'
                    + '<select class="form-control" id="province2">' + provinceHtml + '</select>'
                    + '</div>'
                    + '</div>'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right" for="subjectName">'
                    + '课程名称：</label>'
                    + '<div class="col-sm-3">'
                    + '<input type="text" id="subjectName" placeholder="输入课程名称不超过10个字" class="col-sm-10"/ >'
                    + '</div>'
                    + '</div>'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right" for="examName">'
                    + '真题密卷：</label>'
                    + '<div class="col-sm-3">'
                    + '<input type="text" id="examName" placeholder="请输入真题密卷的标题" class="col-sm-10"/ >'
                    + '</div>'
                    + '</div>'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right" for="examYear">年份：</label>'
                    + '<div class="col-sm-2">'
                    + '<select class="form-control" id="examYear">' + rangeYearHtml + '</select>'
                    + '</div>'
                    + '</div>'
                    + '<div class="form-group">'
                    + '<label class="col-sm-2 control-label no-padding-right" for="policyInterTwo">'
                    + '上传文件：</label>'
                    + '<div class="col-sm-6">'
                    + '<input type="file" id="uploaderSWF" class="col-sm-5" >'
                    + '</div>'
                    + '</div>'
                    + '<div id="tips"></div>'
                    + '</div>'
                    + '</div>'
                    + '</div>'
                    + '</div>';
            var addExamFun = function () {
                var addExamData = {
                    oper:add,
                    areaId:'',
                    paperName:'',//课程名称
                    mbeikaochongcitype:'真题密卷'
                };
                examPaperObj.getData(examPaperObj.url.commonExamPaper,addExamData,function(res){
                    console.info(res);
                })



            };//addExamFun
            bootbox.dialog({
                title: "添加真题密卷",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success submitExamBtn",
                        "callback": addExamFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });


        }); //ace.click_event结束


    });//$结束


</script>