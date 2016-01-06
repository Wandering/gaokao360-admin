<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var courseName = $('#courseName').val();
        var status = $('#status').val();
        var classfyId = $('#classfyId').val();
        var rules = [];
        if (courseName != '' && courseName != null && courseName != undefined) {
            var rule = {
                'field': 'courseName',
                'op': 'eq',
                'data': courseName
            }
            rules.push(rule);
        }
        if (status != '' && status != null && status != undefined) {
            var rule = {
                'field': 'status',
                'op': 'eq',
                'data': status
            }
            rules.push(rule);
        }
        if (classfyId != '' && classfyId != null && classfyId != undefined) {
            var rule = {
                'field': 'classfyId',
                'op': 'eq',
                'data': classfyId
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

    $("#search").click(function () {
        searchLoad();

    });

    /*
    *
    * 院校专业信息模块
    *
    * */

    jQuery(function ($) {

        var UI = {
            $addBtn: $('#addBtn')
            , $editBtn: $('#editBtn')
            , $deleteBtn: $('#deleteBtn')
        };
        var typeStr, rowId;
        $('#getEduLevel').html(CommonFn.getEduLevel());
//        添加院校专业信息
        UI.$addBtn.on(ace.click_event, function () {
            typeStr = 'add';
            bootbox.dialog({
                title: "添加院校专业信息",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addAndEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
            selOptions();
        });
//        修改院校专业信息
        UI.$editBtn.on(ace.click_event, function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改院校专业信息",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback": addAndEditFun
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
            selOptions();
        });
//        当前行数据
        var rowData = CommonFn.getRowData(rowId);


//        删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//          添加修改方法
        function addAndEditFun() {
            var MajoredNameList = $('#MajoredNameList').find('option:selected').attr('value');
            var MajoredNameListText = $('#MajoredNameList').find('option:selected').text();
            var UniversityNameList = $('#UniversityNameList').find('option:selected').attr('value');
            var UniversityNameListText = $('#UniversityNameList').find('option:selected').text();
            var MAJOR_TYPE = $('#MAJOR_TYPE').find('option:selected').attr('value');
            var UNIVERSITY_MAJOR_TYPE = $('#UNIVERSITY_MAJOR_TYPE').find('option:selected').attr('value');

            var eduLevel = $('#eduLevel').find('option:selected').attr('value');

            var GAIN_DEGREE = $('#GAIN_DEGREE').find('option:selected').attr('value');
            if (MajoredNameList == '00') {
                CommonFn.tipsDialog('温馨提示', '专业名称没有选择');
                return false;
            }
            if (UniversityNameList == '00') {
                CommonFn.tipsDialog('温馨提示', '开设院校没有选择');
                return false;
            }
            if (MAJOR_TYPE == '00') {
                CommonFn.tipsDialog('温馨提示', '专业类型没有选择');
                return false;
            }
            if (UNIVERSITY_MAJOR_TYPE == '00') {
                CommonFn.tipsDialog('温馨提示', '科类没有选择');
                return false;
            }
            if (eduLevel == '00') {
                CommonFn.tipsDialog('温馨提示', '学历层次没有选择');
                return false;
            }
            if (GAIN_DEGREE == '00') {
                CommonFn.tipsDialog('温馨提示', '学位没有选择');
                return false;
            }

            var data = {
                oper: 'add',
                universityId: UniversityNameList,
                universityName: UniversityNameListText,
                majorId: MajoredNameList,
                majorName: MajoredNameListText,
                majorType: MAJOR_TYPE,
                majorSubject: UNIVERSITY_MAJOR_TYPE,
                educationLevel: eduLevel,
                gainDegree: GAIN_DEGREE,
                isDelete: 0
            };
            if (typeStr == 'edit') {
                data.id = rowId;
            }
            CommonFn.getData('/admin/${bizSys}/commonsave/${mainObj}', 'post', data, function (res) {
                console.info(res);
                searchLoad();
            })

        }

//        dialog弹框
        var dialogHtml = ''
                + '<div class="row">'
                + '<div class="col-xs-12">'
                + '<form class="form-horizontal" role="form">'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 专业名称：</label>'
                + '<div class="col-sm-5">'
                + '<select name="" id="MajoredNameList" class="col-sm-10"></select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 开设院校名称：</label>'
                + '<div class="col-sm-5">'
                + '<select name="" id="UniversityNameList"  class="col-sm-10"></select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 专业类型：</label>'
                + '<div class="col-sm-5">'
                + '<select name="" id="MAJOR_TYPE"  class="col-sm-10"></select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 科类：</label>'
                + '<div class="col-sm-5">'
                + '<select name="" id="UNIVERSITY_MAJOR_TYPE"  class="col-sm-10"></select>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 学历层次：</label>'
                + '<div class="col-sm-5">'
                + '<select name="" id="eduLevel"  class="col-sm-10"></select>'
                + '</div>'
                + '</div>'

                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 学位：</label>'
                + '<div class="col-sm-5">'
                + '<select name="" id="GAIN_DEGREE"  class="col-sm-10"></select>'
                + '</div>'
                + '</div>'

                + '</form>'
                + '</div>'
                + '</div>';

        function selOptions() {
            //        专业名称
            CommonFn.getData('/admin/gaokao360/ex/getMajoredNameList', 'get', {}, function (res) {
                var list = res.bizData;
                var options = ['<option value="00">请选择专业名称</option>'];
                $.each(list, function (i, v) {
                    options.push('<option value="' + v.id + '">' + v.majorName + '</option>');
                });
                $('#MajoredNameList').html(options);
            });

//        开设院校名称
            CommonFn.getData('/admin/gaokao360/ex/getUniversityNameList', 'get', {}, function (res) {
                var list = res.bizData;
                var options = ['<option value="00">请选择开设院校名称</option>'];
                $.each(list, function (i, v) {
                    options.push('<option value="' + v.id + '">' + v.name + '</option>');
                });
                $('#UniversityNameList').html(options);
            });
//        专业类型
            CommonFn.getData('/admin/gaokao360/ex/getDataDictList?type=MAJOR_TYPE', 'get', {}, function (res) {
                var list = res.bizData;
                var options = ['<option value="00">请选择专业类型</option>'];
                $.each(list, function (i, v) {
                    options.push('<option value="' + v.dictId + '">' + v.name + '</option>');
                });
                $('#MAJOR_TYPE').html(options);
            });
//        科类
            CommonFn.getData('/admin/gaokao360/ex/getDataDictList?type=UNIVERSITY_MAJOR_TYPE', 'get', {}, function (res) {
                var list = res.bizData;
                var options = ['<option value="00">请选择科类</option>'];
                $.each(list, function (i, v) {
                    options.push('<option value="' + v.dictId + '">' + v.name + '</option>');
                });
                $('#UNIVERSITY_MAJOR_TYPE').html(options);
            });
//        学位
            CommonFn.getData('/admin/gaokao360/ex/getDataDictList?type=GAIN_DEGREE', 'get', {}, function (res) {
                var list = res.bizData;
                var options = ['<option value="00">请选择学位</option>'];
                $.each(list, function (i, v) {
                    options.push('<option value="' + v.dictId + '">' + v.name + '</option>');
                });
                $('#GAIN_DEGREE').html(options);
            });
//        学历层次
            $('#eduLevel').html(CommonFn.getEduLevel());
        }


    });//$ end


</script>