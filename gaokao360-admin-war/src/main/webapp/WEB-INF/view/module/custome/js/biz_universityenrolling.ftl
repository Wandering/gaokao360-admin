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
            };
            rules.push(rule);
        }
        if (status != '' && status != null && status != undefined) {
            var rule = {
                'field': 'status',
                'op': 'eq',
                'data': status
            };
            rules.push(rule);
        }
        if (classfyId != '' && classfyId != null && classfyId != undefined) {
            var rule = {
                'field': 'classfyId',
                'op': 'eq',
                'data': classfyId
            };
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
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 省份：</label>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" id="selProvince2">' + provinceData + '</select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 院校名称：</label>'
                + '              <div class="col-sm-4">'
                + '                  <input id="autoSearch" type="text" class="form-control" placeholder="请输入学校查询"/>'
                + '          </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 年份：</label>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" id="selYears2">' + yearsData + '</select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 文史类招生：</label>'
                + '              <div class="col-sm-9" id="subjectType-main1">'
                + '                  <div class="subjectTypeList" dataId="1">'
                + '                      <select class="form-control subjectType"  style="width:50%">' + getBatchData + '</select>'
                + '                      <div class="form-group subjectTypeDetail">'
                + '                          <div class="col-sm-12">'
                + '                              计划数：<input type="text" class="input-small planEnrollingNumber" placeholder="计划数"/>'
                + '                              录取数：<input type="text" class="input-small realEnrollingNumber" placeholder="录取数"/>'
                + '                              最高分：<input type="text" class="input-small highestScore" placeholder="最高分"/>'
                + '                              最高位次：<input type="text" class="input-small highestPrecedence" placeholder="最高位次"/>'
                + '                              <br /><br />'
                + '                              最低分：<input type="text" class="input-small lowestScore" placeholder="最低分"/>'
                + '                              最低位次：<input type="text" class="input-small lowestPrecedence" placeholder="最低位次"/>'
                + '                              平均分：<input type="text" class="input-small averageScore" placeholder="平均分"/>'
                + '                              平均位次：<input type="text" class="input-small averagePrecedence" placeholder="平均位次"/>'
                + '                              <button class="btn btn-minier btn-pink deleteSubjectTypeBtn">删除</button>'
                + '                              <div class="line"></div>'
                + '                          </div>'
                + '                      </div>'
                + '                  </div>'
                + '              </div>'
                + '              <div class="col-sm-1">'
                + '                  <button class="btn btn-sm btn-primary" id="subjectTypeBtn1">增加</button>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 理工类招生：</label>'
                + '              <div class="col-sm-9" id="subjectType-main2">'
                + '                  <div class="subjectTypeList" dataId="2" >'
                + '                      <select class="form-control subjectType"  style="width:50%">' + getBatchData + '</select>'
                + '                      <div class="form-group subjectTypeDetail">'
                + '                          <div class="col-sm-12">'
                + '                              计划数：<input type="text" class="input-small planEnrollingNumber" placeholder="计划数"/>'
                + '                              录取数：<input type="text" class="input-small realEnrollingNumber" placeholder="录取数"/>'
                + '                              最高分：<input type="text" class="input-small highestScore" placeholder="最高分"/>'
                + '                              最高位次：<input type="text" class="input-small highestPrecedence" placeholder="最高位次"/>'
                + '                              <br /><br />'
                + '                              最低分：<input type="text" class="input-small lowestScore" placeholder="最低分"/>'
                + '                              最低位次：<input type="text" class="input-small lowestPrecedence" placeholder="最低位次"/>'
                + '                              平均分：<input type="text" class="input-small averageScore" placeholder="平均分"/>'
                + '                              平均位次：<input type="text" class="input-small averagePrecedence" placeholder="平均位次"/>'
                + '                              <button class="btn btn-minier btn-pink deleteSubjectTypeBtn">删除</button>'
                + '                              <div class="line"></div>'
                + '                          </div>'
                + '                      </div>'
                + '                  </div>'
                + '              </div>'
                + '              <div class="col-sm-1">'
                + '                  <button class="btn btn-sm btn-primary" id="subjectTypeBtn2">增加</button>'
                + '              </div>'
                + '          </div>'
                + '      </form>'
                + '  </div>'
                + '</div>';

        function catcompleteFn() {
            $.widget("custom.catcomplete", $.ui.autocomplete, {
                _renderMenu: function (ul, items) {
                    var that = this,
                            currentCategory = "";
                    $.each(items, function (index, item) {
                        if (item.category != currentCategory) {
                            ul.append("<li class='ui-autocomplete-category'>" + item.category + "</li>");
                            currentCategory = item.category;
                        }
                        that._renderItemData(ul, item);
                    });
                }
            });
            var dataJson = CommonFn.getAllSchool('');
            var dataArr = [];
            var obj = {};
            for (var i = 0; i < dataJson.length; i++) {
                console.log(dataJson[i].label)
                obj = {
                    id: dataJson[i].id,
                    label: dataJson[i].label,
                    category: dataJson[i].category
                };
                dataArr.push(obj);
            }
            $("#autoSearch").catcomplete({
                delay: 0,
                source: dataArr,
                select: function (event, ui) {
                    $('#autoSearch').attr('id', ui.item.id)
                }
            });
        }


        function subjectTypeFn(n) {
            var subjectTypeList = $('#subjectType-main' + n).find('.subjectTypeList').html();
            // 增加招生批次明细
            $('body').on('click', '#subjectTypeBtn' + n, function (event) {
                event.stopPropagation();
                event.preventDefault();
                $('#subjectType-main' + n).append('<div class="subjectTypeList">' + subjectTypeList + '</div>');
            });
            $('#subjectType-main' + n).find('.deleteSubjectTypeBtn:eq(0)').hide();

            // 选择批次
            $('#subjectType-main' + n).on('change', '.subjectType', function (event) {
                event.stopPropagation();
                event.preventDefault();
                var optionV = $(this).find('option:checked').val();
                optionV == '00' ? $(this).next().hide() : $(this).next().show();
            });
            // 删除批次
            $('#subjectType-main' + n).on('click', '.deleteSubjectTypeBtn', function (event) {
                event.stopPropagation();
                event.preventDefault();
                $(this).parents('.subjectTypeList').remove();
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
            catcompleteFn();
            subjectTypeFn(1);
            subjectTypeFn(2);


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
            for(var i=0;i<$('.subjectTypeList').length;i++){
                var universityMajorTypeV = $('.subjectTypeList:eq('+ i +')').attr('dataid');
                var batchV = $('.subjectTypeList:eq('+ i +')').find('.subjectType option:checked').val();
                var planEnrollingNumberV = $('.subjectTypeList:eq('+ i +')').find('.planEnrollingNumber').val();
                var realEnrollingNumberV = $('.subjectTypeList:eq('+ i +')').find('.realEnrollingNumber').val();
                var highestScoreV = $('.subjectTypeList:eq('+ i +')').find('.highestScore').val();
                var highestPrecedenceV = $('.subjectTypeList:eq('+ i +')').find('.highestPrecedence').val();
                var lowestScoreV = $('.subjectTypeList:eq('+ i +')').find('.lowestScore').val();
                var lowestPrecedenceV = $('.subjectTypeList:eq('+ i +')').find('.lowestPrecedence').val();
                var averageScoreV = $('.subjectTypeList:eq('+ i +')').find('.averageScore').val();
                var averagePrecedenceV = $('.subjectTypeList:eq('+ i +')').find('.averagePrecedence').val();
                batchType = {
                    "universityMajorType":universityMajorTypeV,
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
            };

            var Datas = {
                "areaId": selProvinceV,
                "universityId": autoSearchV,
                "year": selYearsV,
                "batchContent":batchData,
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
