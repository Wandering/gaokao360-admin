<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var keywordSearch = $('#keywordSearch').val();
        var getEduLevel = $('#getEduLevel').val();
        var rules = [];
        if (keywordSearch != '' && keywordSearch != null && keywordSearch != undefined ) {
            var rule = {
                'field': 'keywordSearch',
                'op': 'lk',
                'data': keywordSearch
            }
            rules.push(rule);
        }
        if (getEduLevel != '' && getEduLevel != null && getEduLevel != undefined && getEduLevel != '00') {
            var rule = {
                'field': 'educationLevel',
                'op': 'eq',
                'data': getEduLevel
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

        // 专业
        var specialityData = CommonFn.getSpeciality();

        // 开设院校
        var universityNameListData = CommonFn.getUniversityNameList();

        // 学历层次
        var edulevelData = CommonFn.getEdulevel();
        $('#getEduLevel').html(edulevelData);
//        console.log(edulevelData)

        // 学位
        var degreeData = CommonFn.getDegree();
//        console.log(degreeData)

        // 科类
        var subjectTypeData = CommonFn.getSubjectType();
        console.log(subjectTypeData)
        // 专业类型
        var majoredTypeData = CommonFn.getMajoredType();
        console.log(majoredTypeData)


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
            // 获取当前行数据
            var rowData = CommonFn.getRowData(rowId);
            console.log(rowData);
            $('#universityName').val(rowData[0].universityName);
            $('#majorName').val(rowData[0].majorName);
            $('#majorRank').val(rowData[0].majorRank);
            $('#salaryRank').val(rowData[0].salaryRank);
            $('#jobRank').val(rowData[0].jobRank);
            $('#majorFeature').val(rowData[0].majorFeature);
            $('#eduLevel').find('option[value="' + rowData[0].educationLevel + '"]').attr('selected', 'selected');
        });


//        删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//          添加修改方法
        function addAndEditFun() {
//            var MajoredNameList = $('#MajoredNameList').find('option:selected').attr('value');
//            var MajoredNameListText = $('#MajoredNameList').find('option:selected').text();
//            var UniversityNameList = $('#UniversityNameList').find('option:selected').attr('value');
//            var UniversityNameListText = $('#UniversityNameList').find('option:selected').text();
//            var MAJOR_TYPE = $('#MAJOR_TYPE').find('option:selected').attr('value');
//            var UNIVERSITY_MAJOR_TYPE = $('#UNIVERSITY_MAJOR_TYPE').find('option:selected').attr('value');

            var eduLevel = $('#eduLevel').find('option:selected').attr('value');

//            var GAIN_DEGREE = $('#GAIN_DEGREE').find('option:selected').attr('value');
//            if (MajoredNameList == '00') {
//                CommonFn.tipsDialog('温馨提示', '专业名称没有选择');
//                return false;
//            }
//            if (UniversityNameList == '00') {
//                CommonFn.tipsDialog('温馨提示', '开设院校没有选择');
//                return false;
//            }
//            if (MAJOR_TYPE == '00') {
//                CommonFn.tipsDialog('温馨提示', '专业类型没有选择');
//                return false;
//            }
//            if ($.trim($('#majoredrank').val()) == '') {
//                CommonFn.tipsDialog('温馨提示', '请输入院校排名');
//                return false;
//            }
//            if (UNIVERSITY_MAJOR_TYPE == '00') {
//                CommonFn.tipsDialog('温馨提示', '科类没有选择');
//                return false;
//            }
            if (eduLevel == '00') {
                CommonFn.tipsDialog('温馨提示', '学历层次没有选择');
                return false;
            }
//            if (GAIN_DEGREE == '00') {
//                CommonFn.tipsDialog('温馨提示', '学位没有选择');
//                return false;
//            }

            var data = {
                oper: typeStr,
                universityName: $('#universityName').val(),
                majorName: $('#majorName').val(),
                educationLevel: eduLevel,
                isDelete: 0,
                majorRank:$('#majorRank').val(),
                salaryRank:$('#salaryRank').val(),
                jobRank:$('#jobRank').val(),
                majorFeature:$('#majorFeature').val()
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
                + '<input type="text" id="majorName" placeholder="请输入专业名称" class="" />'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 开设院校名称：</label>'
                + '<div class="col-sm-5">'
                + '<input type="text" id="universityName" placeholder="请输入开设院校名称" class="" />'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 学历层次：</label>'
                + '<div class="col-sm-5">'
                + '<select name="" id="eduLevel"  class="col-sm-10"> '+ edulevelData +' </select>'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 专业排名：</label>'
                + '<div class="col-sm-5">'
                + '<input type="text" id="majorRank" placeholder="请输入专业排名" class="" />'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 薪资排名：</label>'
                + '<div class="col-sm-5">'
                + '<input type="text" id="salaryRank" placeholder="请输入专业排名" class="" />'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 就业排名：</label>'
                + '<div class="col-sm-5">'
                + '<input type="text" id="jobRank" placeholder="请输入就业排名" class="" />'
                + '</div>'
                + '</div>'
                + '<div class="form-group">'
                + '<label class="col-sm-2 control-label no-padding-right" for=""> 专业特征：</label>'
                + '<div class="col-sm-5">'
                + '<input type="text" id="majorFeature" placeholder="请输入专业特征" class="" />'
                + '</div>'
                + '</div>'




                + '</form>'
                + '</div>'
                + '</div>';
    });//$ end


</script>