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
        };
//        获取省份
        UI.$selProvince.append(CommonFn.getProvince());
        $('#selEduLevel2').append(CommonFn.getEduLevel());
//        获取学历层次
        UI.$selEduLevel.append(CommonFn.getEduLevel());
        $('#schoolInProvince2').append(CommonFn.getProvince());
//        添加院校层次信息
        UI.$addBtn.click(function () {
            typeStr = "add";
            $('#edit_answer_modal').modal('show');
            UI.$submitBtn.on(ace.click_event, function (e) {
                e.preventDefault();
//                院校名称
                var schoolName = $('#schoolName').val().trim();
                if (schoolName.length == 0) {
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
                if(schoolRank.length >6 || !regNum.test(schoolRank)){
                    CommonFn.tipsDialog('温馨提示','院校排名输入只能位数字,且不能大于6位数字');
                    return false;
                }
//                院校特征
                var schoolStatic = $('#schoolStatic').val().trim();
                if(schoolStatic.length > 10){
                    CommonFn.tipsDialog('温馨提示','院校特征输入不能大于10个字');
                    return false;
                }
//                学历层次
                var selEduLevel2 = $('#selEduLevel2').val().trim();
                var selEduLevel2 = $('#selEduLevel2').find('option:selected').val();
                if(schoolStatic.length > 10){
                    CommonFn.tipsDialog('温馨提示','学历层次必须选择');
                    return false;
                }
//                院校类型
                var schoolType = $('#schoolType').val().trim();
                if(schoolType.length > 10){
                    CommonFn.tipsDialog('温馨提示','院校类型输入不能大于10个字');
                    return false;
                }
//                院校隶属
                var schoolOwn = $('#schoolOwn').val().trim();
                if(schoolOwn.length > 10){
                    CommonFn.tipsDialog('温馨提示','院校类型输入不能大于10个字');
                    return false;
                }
//                院校网址
                var schoolWeb = $('#schoolWeb').val().trim();
                if(schoolWeb.length > 10){
                    CommonFn.tipsDialog('温馨提示','院校类型输入不能大于30个字');
                    return false;
                }
//                所在省份
                var schoolInProvince2 = $("#schoolInProvince2").find('option:selected').val();
                if(schoolInProvince2 == '00'){
                    CommonFn.tipsDialog('温馨提示','所在省份必须选择');
                    return false;
                }
//                院校地址
                var schoolAddress = $('#schoolAddress').val().trim();
                if(schoolAddress.length == ''){
                    CommonFn.tipsDialog('温馨提示','输入院校地址不能为空');
                    return false;
                }
                if(schoolAddress.length > 120){
                    CommonFn.tipsDialog('温馨提示','输入院校地址内容不能超过120个字');
                    return false;
                }
//                联系电话
                var schoolTel = $('#schoolTel').val().trim();
                if(schoolTel.length == ''){
                    CommonFn.tipsDialog('温馨提示','电话号码不能为空');
                    return false;
                }
//                院校简介
                var schoolIntroduce = $('#schoolIntroduce').val().trim();
                if(schoolTel.length == ''){
                    CommonFn.tipsDialog('温馨提示','院校简介输入不能为空');
                    return false;
                }
//                招生章程
                var schoolArticle = $('#schoolArticle').val().trim();
                if(schoolArticle.length == ''){
                    CommonFn.tipsDialog('温馨提示','招生章程输入不能为空');
                    return false;
                }

                var addUniversityData = {
                    oper: typeStr,
                    code: schoolCode
                    , name: schoolName
                    , images: 'http://img0.imgtn.bdimg.com/it/u=2127500600,2612092016&fm=21&gp=0.jpg'
//                    , images: schoolPic
                    , sort: schoolRank
                    , property: schoolStatic
                    , educationLevel: selEduLevel2
                    , type: schoolType
                    , subjection: schoolOwn
                    , url: schoolWeb
                    , provinceId: schoolInProvince2
                    , provinceName: $('#schoolInProvince2').html()
                    , address: schoolAddress
                    , contactPhone: schoolTel
                    , universityIntro: schoolIntroduce
                    , entranceIntro: schoolArticle
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
        });


    });//$ end
</script>