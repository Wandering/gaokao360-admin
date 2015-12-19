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
//        添加院校层次信息
        UI.$addBtn.click(function () {
            typeStr = "add";
            $('#edit_answer_modal').modal('show');
            UI.$submitBtn.on(ace.click_event, function (e) {
                e.preventDefault();
                var addUniversityData = {
                    oper: typeStr,
                    code: $('#schoolCode').val()
                    , name: $('#schoolName').val()
                    , images: 'http://img0.imgtn.bdimg.com/it/u=2127500600,2612092016&fm=21&gp=0.jpg'
//                    , images: $('#schoolPic').val()
                    , sort: $('#schoolRank').val()
                    , property: $('#schoolStatic').val()
                    , educationLevel: $('#selEduLevel2').val()
                    , type: $('#schoolType').val()
                    , subjection: $('#schoolOwn').val()
                    , url: $('#schoolWeb').val()
                    , provinceId: $('#schoolInProvince').val()
                    , address: $('#schoolAddress').val()
                    , contactPhone: $('#schoolTel').val()
                    , universityIntro: $('#schoolIntroduce').val()
                    , entranceIntro: $('#schoolArticle').val()
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