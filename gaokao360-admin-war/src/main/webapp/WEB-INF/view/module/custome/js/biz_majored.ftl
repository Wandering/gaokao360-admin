<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var subjectType = $('#selMajored').find("option:selected").html();
        var queryparam = $('#majoredKeyWord').val();
        var rules = [];
        if (subjectType != '' && subjectType != null && subjectType != undefined && subjectType!='00' && subjectType!='请选择学科门类') {
            var rule = {
                'field': 'subjectType',
                'op': 'eq',
                'data': subjectType
            }
            rules.push(rule);
        }
        if (queryparam != '' && queryparam != null && queryparam != undefined && queryparam!='00') {
            var rule = {
                'field': 'queryparam',
                'op': 'lk',
                'data': queryparam
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
    * 专业基本信息模块开发
    *
    * */

    jQuery(function ($) {
        var typeStr;
        var rowId;
        var majoredDom = {
            $addBtn: $('#addBtn')
            , $editBtn: $('#editBtn')
            , $deleteBtn: $('#deleteBtn')
            , $majoredName: $('#majoredName')
            , $selMajored2: $('#selMajored2')
            , $subjectType: $('#subjectType')
            , $salaryRank: $('#salaryRank')
            , $jobsRank: $('#jobsRank')
            , $majoredCode: $('#majoredCode')
            , $sameMajored: $('#sameMajored')
            , $mainMajored: $('#mainMajored')
            , $employDirect: $('#employDirect')
            , $excellentStudent: $('#excellentStudent')
            , $submitBtn: $('#submitBtn')
            , $cancelBtn: $('#cancelBtn')
        };

//        获取学科分类
        var majored = CommonFn.getMajored();
        $('#selMajored,#selMajored2').append(majored);

//        添加专业基本信息
        majoredDom.$addBtn.click(function () {
            typeStr = 'add';
            $('#majoredModal').modal('show');
            majoredDom.$selMajored2.change(function () {
                var majoredId = $(this).find('option:selected').val();
                var majoredName = $(this).find('option:selected').html();
                CommonFn.getData('/admin/gaokao360/ex/getMajoredCategoryByPid', 'GET', {
                    id: majoredId,
                    name: majoredName
                }, function (res) {
                    if (res.rtnCode == "0000000") {
                        var dataJson = res.bizData;
                        var dataHTML = [];
                        $.each(dataJson, function (i, v) {
                            dataHTML.push('<option value="' + v.id + '">' + v.name + '</option>');
                        });
                        $('#subjectType').html(dataHTML);
                    }
                })
            });
            majoredDom.$submitBtn.click(function (e) {
                e.preventDefault();
                majoredValidate();
                var addMajoredData = {
                    oper: typeStr
                    , name: majoredDom.$majoredName.val()
                    , code: majoredDom.$majoredCode.val()
                    , subjectTypeId: $('#selMajored2').find('option:selected').val()
                    , subjectType: $('#selMajored2').find('option:selected').html()
                    , majoredTypeId: $('#subjectType').find('option:selected').val()
                    , majoredType: $('#subjectType').find('option:selected').html()
                    , salaryRank: majoredDom.$salaryRank.val()
                    , employedRank: majoredDom.$jobsRank.val()
                    , similarMajored: majoredDom.$sameMajored.val()
                    , mainMajored: majoredDom.$mainMajored.val()
                    , employDirect: majoredDom.$employDirect.val()
                    , excellentStudent: majoredDom.$excellentStudent.val()
                };
                CommonFn.getData('/admin/gaokao360/ex/commonsave/${mainObj}', 'POST', addMajoredData, function (res) {
                    if (res.rtnCode == "0000000") {
                        searchLoad();
                        $('#majoredModal').modal('hide');
                        $('#submitForm')[0].reset();
                        $('#schoolIntroduce,#schoolArticle').html('');
                    }
                });
            })

        });//添加 end
//        修改专业基本信息
        majoredDom.$editBtn.click(function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            $('#majoredModal').modal('show');
            // 获取当前行数据
            var rowData = CommonFn.getRowData(rowId);
            majoredDom.$majoredName.val(rowData[0].name);
            majoredDom.$majoredCode.val(rowData[0].code);
            $('#selMajored2').find('option[value="' + rowData[0].majoredTypeId + '"]').attr('selected', 'selected');
            $('#subjectType').find('option[value="' + rowData[0].subjectTypeId + '"]').attr('selected', 'selected');
            majoredDom.$jobsRank.val(rowData[0].employedRank);
            majoredDom.$salaryRank.val(rowData[0].salaryRank);
            majoredDom.$sameMajored.val(rowData[0].similarMajored);
            majoredDom.$mainMajored.val(rowData[0].mainCourse);
            majoredDom.$employDirect.val(rowData[0].workGuide);
            majoredDom.$excellentStudent.val(rowData[0].excellentStudent);
            majoredDom.$submitBtn.on(ace.click_event, function (e) {
                e.preventDefault();
                majoredValidate();
                var addMajoredData = {
                    oper: typeStr
                    , name: majoredDom.$majoredName.val()
                    , code: majoredDom.$majoredCode.val()
                    , subjectTypeId: $('#selMajored2').find('option:selected').val()
                    , subjectType: $('#selMajored2').find('option:selected').html()
                    , majoredTypeId: $('#subjectType').find('option:selected').val()
                    , majoredType: $('#subjectType').find('option:selected').html()
                    , salaryRank: majoredDom.$salaryRank.val()
                    , employedRank: majoredDom.$jobsRank.val()
                    , similarMajored: majoredDom.$sameMajored.val()
                    , mainCourse: majoredDom.$mainMajored.val()
                    , workGuide: majoredDom.$employDirect.val()
                    , excellentStudent: majoredDom.$excellentStudent.val()
                };
                $.ajax({
                    type: "POST",
                    url: '/admin/gaokao360/ex/commonsave/${mainObj}',
                    data: addMajoredData,
                    success: function (result) {
                        if (result.rtnCode == "0000000") {
                            searchLoad();
                            $('#majoredModal').modal('hide');
                            $('#submitForm')[0].reset();
                            $('#schoolIntroduce,#schoolArticle').html('');
                        }
                    }
                });
            });

        });//修改end

//        删除专业基本信息
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
        function majoredValidate() {
            if (majoredDom.$majoredName.val().trim() == '') {
                CommonFn.tipsDialog('温馨提示', '专业名称不能为空');
                return false;
            }
            if (majoredDom.$majoredCode.val().trim() == '') {
                CommonFn.tipsDialog('温馨提示', '专业Code不能为空');
                return false;
            }
            if ($('#selMajored2').find('option:selected').val() == '00') {
                CommonFn.tipsDialog('温馨提示', '学科门类没有选择');
                return false;
            }
            if ($('#subjectType').find('option:selected').val() == '00') {
                CommonFn.tipsDialog('温馨提示', '专业门类没有选择');
                return false;
            }
            if (majoredDom.$salaryRank.val().trim() == '') {
                CommonFn.tipsDialog('温馨提示', '薪资排名不能为空');
                return false;
            }
            if (majoredDom.$jobsRank.val().trim() == '') {
                CommonFn.tipsDialog('温馨提示', '就业排名不能为空');
                return false;
            }
            if (majoredDom.$sameMajored.val().trim() == '') {
                CommonFn.tipsDialog('温馨提示', '相近专业不能为空');
                return false;
            }
            if (majoredDom.$mainMajored.val().trim() == '') {
                CommonFn.tipsDialog('温馨提示', '主要课程不能为空');
                return false;
            }
            if (majoredDom.$employDirect.val().trim() == '') {
                CommonFn.tipsDialog('温馨提示', '就业方向不能为空');
                return false;
            }
            if (majoredDom.$excellentStudent.val().trim() == '') {
                CommonFn.tipsDialog('温馨提示', '优秀学长不能为空');
                return false;
            }
        }
    });//$ end


</script>