<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var universityeducationLevel = $('#selEduLevel').val();
        var universityprovinceName = $('#selProvince').find('option:selected').html();
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
        if (universityprovinceName != '' && universityprovinceName != null && universityprovinceName != undefined&&universityprovinceName!=='请选择省份') {
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
        UI.$selProvince.append(CommonFn.getProvince());
        $('#selEduLevel2').append(CommonFn.getEduLevel());
//        获取学历层次
        UI.$selEduLevel.append(CommonFn.getEduLevel());
        $('#schoolInProvince2').append(CommonFn.getProvince());
//        实例化院校简介,院校章程富文本编辑器
        CommonFn.renderTextarea('#schoolIntroduce');
        CommonFn.renderTextarea('#schoolArticle');
//        添加院校层次信息
        UI.$addBtn.click(function () {
            typeStr = "add";
            $('#edit_answer_modal').modal('show');
            UI.$submitBtn.on(ace.click_event, function (e) {
                e.preventDefault();
                universityValidate();
                var addUniversityData = {
                    oper: typeStr
                    , code: schoolInfoDOM.$schoolCode.val()
                    , name: schoolInfoDOM.$schoolName.val()
                    , photoUrl: 'http://img0.imgtn.bdimg.com/it/u=2127500600,2612092016&fm=21&gp=0.jpg'
//                    , photoUrl: schoolInfoDOM.$schoolPic.val()
                    , rank: schoolInfoDOM.$schoolRank.val()
                    , property: schoolInfoDOM.$schoolStatic.val()
                    , educationLevel: $('#selEduLevel2').find('option:selected').val()
                    , type: schoolInfoDOM.$schoolType.val()
                    , subjection: schoolInfoDOM.$schoolOwn.val()
                    , url: schoolInfoDOM.$schoolWeb.val()
                    , areaid: $('#schoolInProvince2 option:checked').val()
                    , provinceName: $('#schoolInProvince2 option:checked').html()
                    , address: schoolInfoDOM.$schoolAddress.val()
                    , contactPhone: schoolInfoDOM.$schoolTel.val()
                    , universityIntro: schoolInfoDOM.$schoolIntroduce.html()
                    , entranceIntro: schoolInfoDOM.$schoolArticle.html()
                };
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
                $('#edit_answer_modal').modal('hide');
                $('#submitForm')[0].reset();
                $('#schoolIntroduce,#schoolArticle').html('');
            });
        });//添加end
//      删除院校基本信息
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//      取消提交
        $(document).on('click', '#cancelBtn', function (e) {
            e.preventDefault();
//          重置表单信息
            $('#submitForm')[0].reset();
            $('#schoolIntroduce,#schoolArticle').html('');
        });
//      修改院校基本信息
        UI.$editBtn.click(function () {
            typeStr = "edit";
            rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            $('#edit_answer_modal').modal('show');
            // 获取当前行数据
            var rowData = CommonFn.getRowData(rowId);
            schoolInfoDOM.$schoolName.val(rowData[0].name);
            schoolInfoDOM.$schoolCode.val(rowData[0].code);
            schoolInfoDOM.$schoolRank.val(rowData[0].rank);
            schoolInfoDOM.$schoolStatic.val(rowData[0].property);
            $('#selEduLevel2').find('option[value="' + rowData[0].educationLevel + '"]').attr('selected', 'selected');
            schoolInfoDOM.$schoolType.val(rowData[0].type);
            schoolInfoDOM.$schoolOwn.val(rowData[0].subjection);
            schoolInfoDOM.$schoolWeb.val(rowData[0].url);
            $('#schoolInProvince2').find('option[value="' + rowData[0].areaid + '"]').attr('selected', 'selected');
            schoolInfoDOM.$schoolAddress.val(rowData[0].address);
            schoolInfoDOM.$schoolTel.val(rowData[0].contactPhone);
            schoolInfoDOM.$schoolIntroduce.html(rowData[0].universityIntro);
            schoolInfoDOM.$schoolArticle.html(rowData[0].entranceIntro);
            UI.$submitBtn.on(ace.click_event, function (e) {
                e.preventDefault();
                universityValidate();
                var addUniversityData = {
                    oper: typeStr
                    , code: schoolInfoDOM.$schoolCode.val()
                    , name: schoolInfoDOM.$schoolName.val()
                    , photoUrl: 'http://img0.imgtn.bdimg.com/it/u=2127500600,2612092016&fm=21&gp=0.jpg'
//                    , photoUrl: schoolInfoDOM.$schoolPic.val()
                    , rank: schoolInfoDOM.$schoolRank.val()
                    , property: schoolInfoDOM.$schoolStatic.val()
                    , educationLevel: $('#selEduLevel2 option:checked').val()
                    , type: schoolInfoDOM.$schoolType.val()
                    , subjection: schoolInfoDOM.$schoolOwn.val()
                    , url: schoolInfoDOM.$schoolWeb.val()
                    , areaid: $('#schoolInProvince2 option:checked').val()
                    , provinceName: $('#schoolInProvince2 option:checked').html()
                    , address: schoolInfoDOM.$schoolAddress.val()
                    , contactPhone: schoolInfoDOM.$schoolTel.val()
                    , universityIntro: schoolInfoDOM.$schoolIntroduce.html()
                    , entranceIntro: schoolInfoDOM.$schoolArticle.html()
                };
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
            });

        });//修改end
//      验证函数
        function universityValidate() {
//                院校名称
            var schoolName = $('#schoolName').val();
            if (schoolName.trim().length == 0) {
                CommonFn.tipsDialog('温馨提示', '院校名称输入不能为空');
                return false;
            }
            if (schoolName.length > 15) {
                CommonFn.tipsDialog('温馨提示', '院校名称输入不能大于15个字');
                return false;
            }
//                院校头像
            var schoolPic = $('#schoolPic').attr('src');
            if (schoolPic == '') {
                CommonFn.tipsDialog('温馨提示', '院校头像不能为空');
                return false;
            }
//                院校代码
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
//                学历层次
            var selEduLevel2 = $('#selEduLevel2').val().trim();
            var selEduLevel2 = $('#selEduLevel2').find('option:selected').val();
            if (schoolStatic.length > 10) {
                CommonFn.tipsDialog('温馨提示', '学历层次必须选择');
                return false;
            }
//                院校类型
            var schoolType = $('#schoolType').val().trim();

            if (schoolType.length == '') {
                CommonFn.tipsDialog('温馨提示', '院校类型输入不能为空');
                return false;
            }
            if (schoolType.length > 10) {
                CommonFn.tipsDialog('温馨提示', '院校类型输入不能大于10个字');
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
            var schoolIntroduce = $('#schoolIntroduce').html().trim();
            if (schoolTel.length == '') {
                CommonFn.tipsDialog('温馨提示', '院校简介输入不能为空');
                return false;
            }
//                院校章程
            var schoolArticle = $('#schoolArticle').html().trim();
            if (schoolArticle.length == '') {
                CommonFn.tipsDialog('温馨提示', '院校章程输入不能为空');
                return false;
            }
        }//验证函数end
    });//$ end
</script>