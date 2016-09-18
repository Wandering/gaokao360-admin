<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var subjectType = $('#selMajored').find("option:selected").val();
        var rules = [];
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
            majoredDom.$specialisation.val(rowData[0].specialisation);
            majoredDom.$schoolingDuration.val(rowData[0].schoolingDuration);
            majoredDom.$degreeOffered.val(rowData[0].degreeOffered);
            majoredDom.$offerCourses.val(rowData[0].offerCourses);
            majoredDom.$majorIntroduce.val(rowData[0].majorIntroduce);

            var majorType2Id = majoredDom.$majorType2.find('option:selected').val();


            var majoredId = majoredDom.$disciplineCategories.find('option:selected').val();
            var majoredName = majoredDom.$disciplineCategories.find('option:selected').html();


        });//修改end

//        删除专业基本信息
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//        关闭清空form表单内容



        majoredDom.$submitBtn.click(function (e) {
            e.preventDefault();
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