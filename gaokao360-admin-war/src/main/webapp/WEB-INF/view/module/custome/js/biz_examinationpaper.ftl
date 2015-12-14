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