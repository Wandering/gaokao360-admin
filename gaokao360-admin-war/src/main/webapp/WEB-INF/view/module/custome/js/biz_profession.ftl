<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var keywords = $('#keywords').val();
        var selProfession = $('#selProfession option:checked').val();
        var rules = [];
        if (keywords != '' && keywords != null && keywords != undefined) {
            var rule = {
                'field': 'queryparam',
                'op': 'lk',
                'data': keywords
            }
            rules.push(rule);
        }
        if (selProfession != '00' && selProfession != null && selProfession != undefined) {
            var rule = {
                'field': 'professionType',
                'op': 'eq',
                'data': selProfession
            }
            rules.push(rule);
        }
        return rules;
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
                + '                  <input id="salary-ranking" type="text" class="form-control" placeholder="请输入薪资排名"/>'
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
                        "callback": editFun
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
            $.ajax({
                type: "GET",
                url: '/admin/zgk/getProfessionDetail',
                data: {
                    "professionId" : rowId
                },
                success: function (result) {
                    console.log(result.professionName)
                    if (result.rtnCode == "0000000") {
                        var dataJson = result.bizData;
                        $('#hotTitle').val(dataJson.professionName);
                        $('#selProfession2 option[value="'+ dataJson.professionType +'"]').attr('selected','true');
                        $('#selProfession3 option[value="'+ dataJson.hotDegree +'"]').attr('selected','true');
                        $('#selProfessionHot option[value="1"]').attr('selected','true');
                        $('#salary-ranking').val(dataJson.salaryRank);
                        $('#hotContent-profession').html(dataJson.relateMajor);
                        $('#hotContent-intro').html(dataJson.introduction);
                        $('#hotContent3-content').html(dataJson.workContent);
                        $('#hotContent-requirements').html(dataJson.vocationalDemand);
                        $('#hotContent-prospects').html(dataJson.careerProspects);
                    }
                }
            });
        });
        //删除

        $('#deleteBtn').on(ace.click_event, function () {
            var rowId = $('tr.ui-state-highlight[role="row"]').attr('id');
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后在删除');
                return false;
            }
            bootbox.dialog({
                title: "删除",
                message: "确定删除该条数据",
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 确定",
                        "className": "btn-sm btn-success",
                        "callback": function () {
                            $.ajax({
                                type: "POST",
                                url: '/admin/zgk/deleteProfession?professionId='+rowId,
                                success: function (result) {
                                    console.log(result);
                                    if (result.rtnCode == "0000000") {
                                        searchLoad();
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        label: "关闭",
                        className: "btn-sm"
                    }
                }
            });
        });




        var validationFun =function(){
            var hotTitleV = $.trim($('#hotTitle').val());
            var selProfession2V = $('#selProfession2 option:checked').val();
            var selProfession3V = $('#selProfession3 option:checked').val();
            var selProfessionHotV = $('#selProfessionHot option:checked').val();
            var salaryRankingV = $.trim($('#salary-ranking').val());
            var hotContentProfessionV = $('#hotContent-profession').html();
            var hotContentIntroV = $('#hotContent-intro').html();
            var hotContentContentV = $('#hotContent3-content').html();
            var hotContentRequirementsV = $('#hotContent-requirements').html();
            var hotContentProspectsV = $('#hotContent-prospects').html();

            if (hotTitleV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入职业名称');
                return false;
            }
            if (selProfession2V == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择行业分类');
                return false;
            }
            if (selProfession3V == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择职业分类');
                return false;
            }
            if (selProfessionHotV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择职业热度');
                return false;
            }
            if (salaryRankingV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入薪资排名');
                return false;
            }
            if (hotContentProfessionV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入相关专业');
                return false;
            }
            if (hotContentIntroV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入职业简介');
                return false;
            }
            if (hotContentContentV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入工作内容');
                return false;
            }
            if (hotContentRequirementsV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入从业要求');
                return false;
            }
            if (hotContentProspectsV == "") {
                CommonFn.tipsDialog('温馨提示', '请输入从业前景');
                return false;
            }
            var Datas = {
                "professionType": selProfession2V,
                professionSubType: selProfession3V,
                professionName: hotTitleV,
                hotDegree: selProfessionHotV,
                salaryRank: salaryRankingV,
                relateMajor: hotContentProfessionV,
                introduction: hotContentIntroV,
                workContent: hotContentContentV,
                vocationalDemand: hotContentRequirementsV,
                careerProspects: hotContentProspectsV,
            };
            return Datas;
        };

//        添加
        var addEditFun = function () {
            var Datas = validationFun();
            console.log(Datas)
            $.ajax({
                type: "POST",
                url: '/admin/zgk/addProfession',
                data: Datas,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };
        var editFun = function () {



//            $.ajax({
//                type: "POST",
//                url: '/admin/zgk/editProfession',
//                data: Datas,
//                success: function (result) {
//                    if (result.rtnCode == "0000000") {
////                        searchLoad();
//                    }
//                }
//            });
        };
    });


</script>