<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var subjectType = $('#selMajored').find("option:selected").val();
        var queryparam = $('#majoredKeyWord').val();
        var rules = [];
        if (subjectType != '00' && subjectType != null && subjectType != undefined && subjectType != '00' && subjectType != '请选择学科门类') {
            var rule = {
                'field': 'subjectType',
                'op': 'eq',
                'data': subjectType
            }
            rules.push(rule);
        }
        if (queryparam != '' && queryparam != null && queryparam != undefined && queryparam != '00') {
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
            , $majorName: $('#majorName')
            , $majorType2: $('#majorType2')
            , $disciplineCategories: $('#disciplineCategories')
            , $majorCategory: $('#majorCategory')
            , $employmentRate: $('#employmentRate')
            , $schoolingDuration: $('#schoolingDuration')
            , $majorCode: $('#majorCode')
            , $degreeOffered: $('#degreeOffered')
            , $offerCourses: $('#offerCourses')
            , $specialisation: $('#specialisation')
            , $majorIntroduce: $('#majorIntroduce')
            , $excellentStudent: $('#excellentStudent')
            , $submitBtn: $('#submitBtn')
            , $cancelBtn: $('#cancelBtn')
        };

//        获取学科分类
        var majored = CommonFn.getMajored();
        $('#selMajored,#disciplineCategories').append(majored);

//        添加专业基本信息
        majoredDom.$addBtn.click(function (e) {
            e.preventDefault();
            typeStr = 'add';
            $('#majoredModal').modal('show');
        });
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
            console.log(rowData)
            if (rowData[0].majorName) {
            majoredDom.$majorName.val(rowData[0].majorName);
        }
            majoredDom.$majorCode.val(rowData[0].majorCode);
            $('#majorType2').find('option[value="' + rowData[0].disciplineCategories + '"]').attr('selected', 'selected');
            $('#disciplineCategories').find('option[value="' + rowData[0].disciplineCategories + '"]').attr('selected', 'selected');
            $('#majorCategory').find('option[value="' + rowData[0].majorCategory + '"]').attr('selected', 'selected');
            $('#majorType').find('option[value="' + rowData[0].majorType + '"]').attr('selected', 'selected');
            majoredDom.$employmentRate.val(rowData[0].employmentRate);
//            $('#salaryRank').val(rowData[0].salaryRank);
            majoredDom.$specialisation.val(rowData[0].specialisation);
            majoredDom.$schoolingDuration.val(rowData[0].schoolingDuration);
            majoredDom.$degreeOffered.val(rowData[0].degreeOffered);
            majoredDom.$offerCourses.val(rowData[0].offerCourses);
            majoredDom.$majorIntroduce.val(rowData[0].majorIntroduce);

            var majorType2Id = majoredDom.$majorType2.find('option:selected').val();
            CommonFn.getData('/admin/gaokao360/ex/getMajoredCategoryByPid', 'GET', {
                id: majorType2Id
            }, function (res) {
                if (res.rtnCode == "0000000") {
                    var dataJson = res.bizData;
                    var dataHTML = [];
                    $.each(dataJson, function (i, v) {
                        dataHTML.push('<option value="' + v.id + '">' + v.name + '</option>');
                    });
                    $('#disciplineCategories').html(dataHTML);
                }
            })

            var majoredId = majoredDom.$disciplineCategories.find('option:selected').val();
            var majoredName = majoredDom.$disciplineCategories.find('option:selected').html();
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
                    $('#majorCategory').html(dataHTML);
                }
            })





            <#--majoredDom.$submitBtn.on(ace.click_event, function (e) {-->
                <#--e.preventDefault();-->
<#--//                验证-->
                <#--majoredValidate();-->
                <#--var addMajoredData = {-->
                    <#--oper: typeStr-->
                    <#--, majorName: majoredDom.$majoredName.val()-->
                    <#--, majorCode: majoredDom.$majoredCode.val()-->
                    <#--, disciplineCategories: $('#selMajored2').find('option:selected').val()-->
                    <#--, majorCategory: $('#selMajored2').find('option:selected').html()-->
                    <#--, majorCategory: $('#majorCategory').find('option:selected').val()-->
                    <#--, majoredType: $('#majorCategory').find('option:selected').html()-->
                    <#--, salaryRank: majoredDom.$salaryRank.val()-->
                    <#--, employmentRank: majoredDom.$jobsRank.val()-->
                    <#--, similarMajors: majoredDom.$sameMajored.val()-->
                    <#--, offerCourses: majoredDom.$mainMajored.val()-->
                    <#--, specialisation: majoredDom.$employDirect.val()-->
                    <#--, outstandingMentor: majoredDom.$excellentStudent.val()-->
                <#--};-->
                <#--CommonFn.getData('/admin/gaokao360/ex/commonsave/${mainObj}','post',addMajoredData,function(res){-->
                    <#--if (res.rtnCode == "0000000") {-->
                        <#--searchLoad();-->
                        <#--$('#majoredModal').modal('hide');-->
                        <#--$('#submitForm')[0].reset();-->
                        <#--$('#schoolIntroduce,#schoolArticle').html('');-->
                    <#--}-->
                <#--});-->
            <#--});-->

        });//修改end

//        删除专业基本信息
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//        关闭清空form表单内容

        majoredDom.$majorType2.change(function () {
            var majorType2Id = $(this).find('option:selected').val();
            CommonFn.getData('/admin/gaokao360/ex/getMajoredCategoryByPid', 'GET', {
                id: majorType2Id
            }, function (res) {
                if (res.rtnCode == "0000000") {
                    var dataJson = res.bizData;
                    var dataHTML = [];
                    $.each(dataJson, function (i, v) {
                        dataHTML.push('<option value="' + v.id + '">' + v.name + '</option>');
                    });
                    $('#disciplineCategories').html(dataHTML);
                }
            })
        });

        majoredDom.$disciplineCategories.change(function () {
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
                    $('#majorCategory').html(dataHTML);
                }
            })
        });
        majoredDom.$submitBtn.click(function (e) {
            e.preventDefault();
//            if (majoredDom.$majoredName.val().trim() == '') {
//                CommonFn.tipsDialog('温馨提示', '专业名称不能为空');
//                return false;
//            }
//            if (majoredDom.$majoredCode.val().trim() == '') {
//                CommonFn.tipsDialog('温馨提示', '专业Code不能为空');
//                return false;
//            }
//            if ($('#selMajored2').find('option:selected').val() == '00') {
//                CommonFn.tipsDialog('温馨提示', '学科门类没有选择');
//                return false;
//            }
//            if ($('#majorCategory').find('option:selected').val() == '00') {
//                CommonFn.tipsDialog('温馨提示', '专业门类没有选择');
//                return false;
//            }
//            if (majoredDom.$salaryRank.val().trim() == '') {
//                CommonFn.tipsDialog('温馨提示', '薪资排名不能为空');
//                return false;
//            }
//            if (majoredDom.$jobsRank.val().trim() == '') {
//                CommonFn.tipsDialog('温馨提示', '就业排名不能为空');
//                return false;
//            }
//            if (majoredDom.$sameMajored.val().trim() == '') {
//                CommonFn.tipsDialog('温馨提示', '相近专业不能为空');
//                return false;
//            }
//            if (majoredDom.$mainMajored.val().trim() == '') {
//                CommonFn.tipsDialog('温馨提示', '主要课程不能为空');
//                return false;
//            }
//            if (majoredDom.$employDirect.val().trim() == '') {
//                CommonFn.tipsDialog('温馨提示', '就业方向不能为空');
//                return false;
//            }
//            if (majoredDom.$excellentStudent.val().trim() == '') {
//                CommonFn.tipsDialog('温馨提示', '优秀学长不能为空');
//                return false;
//            }
            var addMajoredData = {
                oper: typeStr
                , majorName: majoredDom.$majorName.val()
                , majorCode: majoredDom.$majorCode.val()
                , disciplineCategories: $('#disciplineCategories').find('option:selected').val()
                , majorCategory: $('#majorCategory').find('option:selected').val()
                , majorType: $('#majorType').find('option:selected').val()
                , employmentRate: majoredDom.$employmentRate.val()
                , schoolingDuration: majoredDom.$schoolingDuration.val()
                , majorIntroduce: majoredDom.$majorIntroduce.val()
                , degreeOffered: majoredDom.$degreeOffered.val()
                , offerCourses: majoredDom.$offerCourses.val()
                , specialisation: majoredDom.$specialisation.val()
            };
            if (typeStr == 'edit') {
                addMajoredData.id = rowId;
            }
            CommonFn.getData('/admin/gaokao360/ex/commonsave/${mainObj}', 'POST', addMajoredData, function (res) {
                if (res.rtnCode == "0000000") {
                    searchLoad();
                    $('#majoredModal').modal('hide');
                    $('#submitForm')[0].reset();
                    $('#schoolIntroduce,#schoolArticle').html('');
                }
            });
        });












        majoredDom.$cancelBtn.click(function(e){
            e.preventDefault();
            $('#submitForm')[0].reset();
            $('#schoolIntroduce,#schoolArticle').html('');
        });
        function majoredValidate() {

        }
    });//$ end


</script>
