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
    * 专业门类模块
    *
    * */
    jQuery(function ($) {
        var typeStr;
        var rowId;
        var UI = {
            $addBtn: $('#addBtn')
            , $editBtn: $('#editBtn')
            , $deleteBtn: $('#deleteBtn')
            , $cancelBtn: $('#cancelBtn')
            , $submitBtn: $('#submitBtn')
            , $majoredCategoryModal: $('#majoredCategoryModal')
            , $majoredCategoryName: $('#majoredCategoryName')
            , $majoredCategoryType: $('#majoredCategoryType')
        };
        UI.$addBtn.click(function () {
            UI.$majoredCategoryModal.modal('show');
            UI.$submitBtn.click(function () {
                typeStr = 'add';
                majoredCategoryValidate();
                var addData = {
                    oper: typeStr
                    , name: UI.$majoredCategoryName.val().trim()
                    , majoredList: UI.$majoredCategoryType.val().trim()
                };
                CommonFn.getData('/admin/gaokao360/ex/commonsave/${mainObj}', 'post', addData, function (res) {
                    if (res.rtnCode == '0000000') {
                        searchLoad();
                        UI.$majoredCategoryModal.modal('hide');
                    }
                })
            });// 提交添加end
            UI.$cancelBtn.click(function(){
                UI.$majoredCategoryName.val('');
                UI.$majoredCategoryType.val('');
            });


        });
//        验证函数
        function majoredCategoryValidate() {
            var categoryName = UI.$majoredCategoryName.val().trim();
            var categoryType = UI.$majoredCategoryType.val().trim();
            if (categoryName == '') {
                CommonFn.tipsDialog('温馨提示', '学科门类不能为空');
                return false;
            }
            if (categoryName.length > 8) {
                CommonFn.tipsDialog('温馨提示', '学科门类字数不能大于8个字');
                return false;
            }
            if (categoryType == '') {
                CommonFn.tipsDialog('温馨提示', '专业类别不能为空');
                return false;
            }
        }

    });//$  end
</script>