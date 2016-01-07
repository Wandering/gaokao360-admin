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


    jQuery(function ($) {
        var typeStr;
        var rowId;


        //  行业分类
        var professionCategoryData = CommonFn.getProfessionCategory();
        $('#selProfession').append(professionCategoryData);

        //  职业热度
        var professionHotData = CommonFn.getProfessionHot();
        $('#selProfessionHot').append(professionHotData);


        // 课程
        var subjectData = CommonFn.getSubject();
        $('#selCourses').append(subjectData);
        // 年份
        var yearsData = CommonFn.getYear();
        $('#selYears').append(yearsData);
        // 省份
        var provinceData = CommonFn.getProvince();
        $('#selProvince').html(provinceData);
        var getBatchData = CommonFn.getBatch();
        var dialogHtml = ''
                + '<div class="row" id="dialogHtml">'
                + '    <div class="col-xs-12">'
                + '      <form class="form-horizontal" role="form">'
                + '         <div class="form-group">'
                + '             <label class="col-sm-2 control-label no-padding-right" for="hotTitle"> 职业名称：</label>'
                + '             <div class="col-sm-3">'
                + '                 <input type="text" id="hotTitle" placeholder="请输入职业名称" class="" />'
                + '             </div>'
                + '         </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 所属行业：</label>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" id="selProfession2">' + professionCategoryData + '</select>'
                + '              </div>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" style="display:none" id="selProfession3"></select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 职业热度：</label>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" id="selProfessionHot">'
                + '                           <option value="00">请选择职业热度</option>'
                + '                           <option value="1">1</option>'
                + '                           <option value="2">2</option>'
                + '                           <option value="3">3</option>'
                + '                           <option value="4">4</option>'
                + '                           <option value="5">5</option>'
                + '                  </select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 薪资排名：</label>'
                + '              <div class="col-sm-4">'
                + '                  <input id="autoSearch" type="text" class="form-control" placeholder="请输入薪资排名"/>'
                + '          </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '             <label class="col-sm-2 control-label no-padding-right" for="hotContent"> 相关专业：</label>'
                + '             <div class="col-sm-10">'
                + '                 <div id="hotContent-profession" class="wysiwyg-editor"></div>'
                + '             </div>'
                + '           </div>'
                + '          <div class="form-group">'
                + '             <label class="col-sm-2 control-label no-padding-right" for="hotContent"> 职业简介：</label>'
                + '             <div class="col-sm-10">'
                + '                 <div id="hotContent-intro" class="wysiwyg-editor"></div>'
                + '             </div>'
                + '           </div>'
                + '          <div class="form-group">'
                + '             <label class="col-sm-2 control-label no-padding-right" for="hotContent"> 工作内容：</label>'
                + '             <div class="col-sm-10">'
                + '                 <div id="hotContent3-content" class="wysiwyg-editor"></div>'
                + '             </div>'
                + '           </div>'
                + '          <div class="form-group">'
                + '             <label class="col-sm-2 control-label no-padding-right" for="hotContent"> 从业要求：</label>'
                + '             <div class="col-sm-10">'
                + '                 <div id="hotContent-requirements" class="wysiwyg-editor"></div>'
                + '             </div>'
                + '           </div>'
                + '          <div class="form-group">'
                + '             <label class="col-sm-2 control-label no-padding-right" for="hotContent"> 就业前景：</label>'
                + '             <div class="col-sm-10">'
                + '                 <div id="hotContent-prospects" class="wysiwyg-editor"></div>'
                + '             </div>'
                + '           </div>'
                + '      </form>'
                + '  </div>'
                + '</div>';

        function professionFn() {
            $('#selProfession2').change(function () {
                var id = $(this).find('option:checked').val()
                console.log(id);
                var professionData = CommonFn.getProfession(id);
                console.log(professionData)
                $('#selProfession3').show().html(professionData);
            });
        }


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

            professionFn();
            CommonFn.renderTextarea('#hotContent-profession');
            CommonFn.renderTextarea('#hotContent-intro');
            CommonFn.renderTextarea('#hotContent3-content');
            CommonFn.renderTextarea('#hotContent-requirements');
            CommonFn.renderTextarea('#hotContent-prospects');


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
//        添加
        var addEditFun = function () {
            var selProvinceV = $('#selProvince2 option:checked').val();
            var selYearsV = $("#selYears2").find('option:selected').val();
            var autoSearchV = $.trim($('.ui-autocomplete-input').attr('id'));
            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if (autoSearchV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入院校名称');
                return false;
            }
            if (selYearsV == '00') {
                CommonFn.tipsDialog('温馨提示', '年份没有选择,请重新输入');
                return false;
            }
            var batchData = [];
            var batchType = {}
            for (var i = 0; i < $('.subjectTypeList').length; i++) {
                var universityMajorTypeV = $('.subjectTypeList:eq(' + i + ')').attr('dataid');
                var batchV = $('.subjectTypeList:eq(' + i + ')').find('.subjectType option:checked').val();
                var planEnrollingNumberV = $('.subjectTypeList:eq(' + i + ')').find('.planEnrollingNumber').val();
                var realEnrollingNumberV = $('.subjectTypeList:eq(' + i + ')').find('.realEnrollingNumber').val();
                var highestScoreV = $('.subjectTypeList:eq(' + i + ')').find('.highestScore').val();
                var highestPrecedenceV = $('.subjectTypeList:eq(' + i + ')').find('.highestPrecedence').val();
                var lowestScoreV = $('.subjectTypeList:eq(' + i + ')').find('.lowestScore').val();
                var lowestPrecedenceV = $('.subjectTypeList:eq(' + i + ')').find('.lowestPrecedence').val();
                var averageScoreV = $('.subjectTypeList:eq(' + i + ')').find('.averageScore').val();
                var averagePrecedenceV = $('.subjectTypeList:eq(' + i + ')').find('.averagePrecedence').val();
                batchType = {
                    "universityMajorType": universityMajorTypeV,
                    "batch": batchV,
                    "planEnrollingNumber": planEnrollingNumberV,
                    "realEnrollingNumber": realEnrollingNumberV,
                    "highestScore": highestScoreV,
                    "highestPrecedence": highestPrecedenceV,
                    "lowestScore": lowestScoreV,
                    "lowestPrecedence": lowestPrecedenceV,
                    "averageScore": averageScoreV,
                    "averagePrecedence": averagePrecedenceV
                };
                batchData.push(batchType);
            }
            ;

            var Datas = {
                "areaId": selProvinceV,
                "universityId": autoSearchV,
                "year": selYearsV,
                "batchContent": batchData,
                "oper": typeStr
            };
            console.log(Datas);
            if (typeStr == 'edit') {
                Datas.id = rowId;
            }
            $.ajax({
                type: "POST",
                url: '/admin/${bizSys}/commonsave/${mainObj}',
                data: Datas,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };
    });


</script>