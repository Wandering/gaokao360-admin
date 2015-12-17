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
    jQuery(function ($) {
        var typeStr;
        var rowId;
        // 搜索
        $("#search").click(function () {
            searchLoad();
        });
        // 课程
        var subjectData = CommonFn.getSubject();
        $('#selCourses').append(subjectData);
        // 年份
        var yearsData = CommonFn.getYear();
        $('#selYears').append(yearsData);
        // 省份
        var provinceData = CommonFn.getProvince();
        $('#selProvince').html(provinceData);
        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right"> 省份：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="selProvince2">';
        dialogHtml += provinceData
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right"> 科目类别：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="selCourses2">';
        dialogHtml += subjectData
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right"> 年份：</label>'
                + '<div class="col-sm-4">'
                + '<select class="form-control" id="selYears2">';
        dialogHtml += yearsData
                + '</select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for="examName"> 标题：</label>'
                + '<div class="col-sm-10">'
                + '<input type="text" id="examName" placeholder="输入真题密卷标题（同一年份，同一课程，真题密卷名称不能重复）" class="form-control" />'
                + '</div>'
                + '</div>'


//                + '<div class="form-group" style="position:relative">'
//                + '<label class="col-sm-2 control-label no-padding-right" for="hotTitle">上传文件：</label>'
//                + '<div class="col-sm-10">'
//                + '<div id="uploader" class="wu-example">'
//                + '<div class="queueList">'
//                + '<div id="dndArea" class="placeholder">'
//                + '<div id="filePicker" class="btn btn-primary"></div>'
//                + '</div>'
//                + '</div>'
//                + '<div class="statusBar" style="display:none;">'
//                + '<div class="progress">'
//                + '<span class="text">0%</span>'
//                + '<span class="percentage"></span>'
//                + '</div><div class="info"></div>'
//                + '<div class="btns">'
//                + '<div id="filePicker2"></div><div class="uploadBtn">开始上传</div>'
//                + '</div>'
//                + '</div>'
//                + '</div>'
//                + '（只能上传一个）'
//                + '</div>'
//                + '</div>'


                + '</form>'
                + '</div>'
                + '</div>';
        // 添加
        $("#addBtn").on(ace.click_event, function (e) {
            typeStr = "add";
            bootbox.dialog({
                title: "添加真题密卷",
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
            // 当前行数据
            var rowData = CommonFn.getRowData(rowId)
            console.log(rowData)
            $('#selProvince2').find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
            $('#selCourses2').find('option[value="' + rowData[0].subjectId + '"]').attr('selected', 'selected');
            $('#selYears2').find('option[value="' + rowData[0].years + '"]').attr('selected', 'selected');
            $('#examName').val(rowData[0].paperName);
        });
        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
        var addEditFun = function () {
            var selProvinceV = $('#selProvince2 option:checked').val();
            var selSubjectV = $("#selCourses2").find('option:selected').val();
            var selYearsV = $("#selYears2").find('option:selected').val();
            var examTitle = $('#examName').val().trim();
            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if (selSubjectV == '00') {
                CommonFn.tipsDialog('温馨提示', '请选择科目类别');
                return false;
            }
            if (selYearsV == '00') {
                CommonFn.tipsDialog('年份没有选择,请重新输入');
                return false;
            }
            if (examTitle.length > 10 || examTitle.length == 0) {
                CommonFn.tipsDialog('温馨提示', '真题密卷标题不符合要求,请重新输入');
                return false;
            }
            var addExamData = {
                oper: typeStr,
                years: selYearsV,
                subjectId: selSubjectV,//课程名称
                mbeikaochongcitype: '真题密卷',
                mbeikaochongcitypeid: 2,
                paperName: "低调低调测试测试",
                price: 0,
                isAccept: 0,
                resources: '/Public/Uploads/examination_paper/20150407/1428375993.swf',//url地址
                resourcesExt: 'swf',
                resourcesFilesize: '1200',//
                downloadsManual: 0,
                downloadsAutomatic: 0,
                sort: 0,
                areaId:selProvinceV
            };

            if (typeStr == 'edit') {
                addExamData.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/gaokao360/ex/commonsave/${mainObj}',
                data: addExamData,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };
    });
</script>