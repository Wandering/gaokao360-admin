<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var keywordSearch = $('#keywordSearch').val();
        var selCourses = $('#selCourses').val();
        var selYears = $('#selYears').val();
        var selEduLevel = $('#selEduLevel').val();
        var selProvince = $('#selProvince').val();
        var rules = [];
        if (keywordSearch != ''&&keywordSearch!=null&&keywordSearch!=undefined) {
            var rule = {
                'field': 'universityName',
                'op': 'lk',
                'data': keywordSearch
            }
            rules.push(rule);
        }
        if (selCourses != ''&&selCourses!=null&&selCourses!=undefined&&selCourses!='00') {
            var rule = {
                'field': 'batch',
                'op': 'eq',
                'data': selCourses
            }
            rules.push(rule);
        }
        if (selYears != ''&&selYears!=null&&selYears!=undefined&&selYears!='00') {
            var rule = {
                'field': 'year',
                'op': 'eq',
                'data': selYears
            }
            rules.push(rule);
        }
        if (selEduLevel != ''&&selEduLevel!=null&&selEduLevel!=undefined&&selEduLevel!='00') {
            var rule = {
                'field': 'universityMajorType',
                'op': 'eq',
                'data': selEduLevel
            }
            rules.push(rule);
        }
        if (selProvince != ''&&selProvince!=null&&selProvince!=undefined&&selProvince!='00') {
            var rule = {
                'field': 'areaId',
                'op': 'eq',
                'data': selProvince
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

        // 批次
        var BatchData = CommonFn.getBatch();
        $('#selCourses').append(BatchData);
        // 年份
        var yearsData = CommonFn.getYear();
        $('#selYears').append(yearsData);


        // 科类
        $('#selEduLevel').append(CommonFn.getSubjectType());

        // 专业
        var specialityData = CommonFn.getSpeciality();

        // 招生性质
        var natureData = CommonFn.getNature();
        // 学制
        var schoolIngData = CommonFn.getSchoolIng();

        console.log(schoolIngData)

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
                + '              <label class="col-sm-2 control-label no-padding-right"> 专业名称：</label>'
                + '              <div class="col-sm-4">'
                + '                 <select class="form-control" id="selSpecialty">' + specialityData + '</select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 院校名称：</label>'
                + '              <div class="col-sm-4">'
                + '                  <input id="autoSearch" type="text" class="form-control" placeholder="请输入学校查询"/>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 招生性质：</label>'
                + '              <div class="col-sm-4">'
                + '                 <select class="form-control" id="nature">' + natureData + '</select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 年份：</label>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" id="selYears2">' + yearsData + '</select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 学制：</label>'
                + '              <div class="col-sm-4">'
                + '                  <select class="form-control" id="schoolIng">' + schoolIngData + '</select>'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 学费：</label>'
                + '              <div class="col-sm-4">'
                + '                  <input type="text" id="tuition" placeholder="请输入学费" class="" />'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group" id="subjectType1">'
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
                + '          <div class="form-group" id="subjectType2">'
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
                    $('#autoSearch').attr('dataId', ui.item.id)
                }
            });
        }




        function subjectTypeFn(n) {
            var subjectTypeList = $('#subjectType-main' + n).find('.subjectTypeList').html();
            // 增加招生批次明细
            $('#subjectTypeBtn'+n).on('click', function (event) {
                event.stopPropagation();
                event.preventDefault();
                $('#subjectType-main' + n).append('<div class="subjectTypeList" dataId="'+ n +'">' + subjectTypeList + '</div>');
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
                title: "添加专业招生信息",
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
                        className: "btn-sm",
                        "callback": function(){

                        }
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
            console.log(rowId)
            var selTrN = $('tr.ui-state-highlight[role="row"]').length;
            if (selTrN != 1) {
                CommonFn.tipsDialog('温馨提示', '请选中一行后修改');
                return false;
            }
            bootbox.dialog({
                title: "修改专业招生信息",
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
            console.log(rowData);
            $('#selProvince2').find('option[value="' + rowData[0].areaId + '"]').attr('selected', 'selected');
            $('#selYears2').find('option[value="' + rowData[0].year + '"]').attr('selected', 'selected');
            $('#selSpecialty').find('option[value="' + rowData[0].majorId + '"]').attr('selected', 'selected');
            $('#nature').find('option[value="' + rowData[0].admissionBatchId + '"]').attr('selected', 'selected');
            $('#schoolIng').find('option[value="' + rowData[0].lengthOfSchooling + '"]').attr('selected', 'selected');
            $('#tuition').val(rowData[0].schoolFee);

            $('#autoSearch').attr('dataId', rowData[0].universityId)
            $('#autoSearch').val(rowData[0].universityName)




            if(rowData[0].universityMajorType=="1"){
                $('#subjectType1').show();
                $('#subjectType2').hide();
                var oParent = $('#subjectType-main1');
                oParent.find('.subjectType option[value="' + rowData[0].batch + '"]').attr('selected', 'selected');
                oParent.find('.subjectTypeDetail').show();
                $('.planEnrollingNumber').val(rowData[0].planEnrollingNumber);
                $('.realEnrollingNumber').val(rowData[0].realEnrollingNumber);
                $('.highestScore').val(rowData[0].highestScore);
                $('.highestPrecedence').val(rowData[0].highestPrecedence);
                $('.lowestScore').val(rowData[0].lowestScore);
                $('.lowestPrecedence').val(rowData[0].lowestPrecedence);
                $('.averageScore').val(rowData[0].averageScore);
                $('.averagePrecedence').val(rowData[0].averagePrecedence);

            }else if(rowData[0].universityMajorType=="2"){
                $('#subjectType1').hide();
                $('#subjectType2').show();
                var oParent = $('#subjectType-main2');
                oParent.find('.subjectType option[value="' + rowData[0].batch + '"]').attr('selected', 'selected');
                oParent.find('.subjectTypeDetail').show();
                $('.planEnrollingNumber').val(rowData[0].planEnrollingNumber);
                $('.realEnrollingNumber').val(rowData[0].realEnrollingNumber);
                $('.highestScore').val(rowData[0].highestScore);
                $('.highestPrecedence').val(rowData[0].highestPrecedence);
                $('.lowestScore').val(rowData[0].lowestScore);
                $('.lowestPrecedence').val(rowData[0].lowestPrecedence);
                $('.averageScore').val(rowData[0].averageScore);
                $('.averagePrecedence').val(rowData[0].averagePrecedence);
            }
            catcompleteFn();
            subjectTypeFn(1);
            subjectTypeFn(2);
        });
        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//        添加
        var addEditFun = function () {
            var selProvinceV = $('#selProvince2 option:checked').val();
            var selSpecialtyV = $('#selSpecialty option:checked').val();
            var natureV = $("#nature").find('option:selected').val();
            var selYearsV = $("#selYears2").find('option:selected').val();
            var schoolIngV = $("#schoolIng").find('option:selected').val();
            var tuitionV = $("#tuition").val();
            var autoSearchId = $('.ui-autocomplete-input').attr('dataId');
            if (selProvinceV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择省份');
                return false;
            }
            if (selSpecialtyV == "00") {
                CommonFn.tipsDialog('温馨提示', '请选择专业');
                return false;
            }
            if ( autoSearchId=="") {
                CommonFn.tipsDialog('温馨提示', '请输入正确的院校名称');
                return false;
            }
            if (natureV == '00') {
                CommonFn.tipsDialog('温馨提示', '请选择招生性质');
                return false;
            }
            if (selYearsV == '00') {
                CommonFn.tipsDialog('温馨提示', '请选择年份');
                return false;
            }
            if (schoolIngV == '00') {
                CommonFn.tipsDialog('温馨提示', '请选择学制');
                return false;
            }
            if (tuitionV == '00') {
                CommonFn.tipsDialog('温馨提示', '请选择学费');
                return false;
            }

            for(var i=0;i<$('#subjectType-main1 .subjectType').length;i++){
                var values = $('#subjectType-main1 .subjectType:eq('+ i +')').find('option:selected').val();
                var $parentDetail = $('#subjectType-main1 .subjectTypeDetail:eq('+ i +')');
                var planEnrollingNumberV = $.trim($parentDetail.find('.planEnrollingNumber').val());
                var realEnrollingNumberV = $.trim($parentDetail.find('.realEnrollingNumber').val());
                var highestScoreV = $.trim($parentDetail.find('.highestScore').val());
                var highestPrecedenceV = $.trim($parentDetail.find('.highestPrecedence').val());
                var lowestScoreV = $.trim($parentDetail.find('.lowestScore').val());
                var lowestPrecedenceV = $.trim($parentDetail.find('.lowestPrecedence').val());
                var averageScoreV = $.trim($parentDetail.find('.averageScore').val());
                var averagePrecedenceV = $.trim($parentDetail.find('.averagePrecedence').val());

                if(values=="00" && $('#subjectType1').is(':visible')){
                    CommonFn.tipsDialog('温馨提示', '请选择文史类招生批次');
                    return false;
                }
                if(planEnrollingNumberV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写文史类计划数');
                    return false;
                }
                if(realEnrollingNumberV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写文史类录取数');
                    return false;
                }
                if(highestScoreV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写文史类最高分');
                    return false;
                }
                if(highestPrecedenceV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写文史类最高位次');
                    return false;
                }
                if(lowestScoreV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写文史类最低分');
                    return false;
                }
                if(lowestPrecedenceV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写文史类最低位次');
                    return false;
                }
                if(averageScoreV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写文史类平均分');
                    return false;
                }
                if(averagePrecedenceV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写文史类平均位次');
                    return false;
                }
            }

            for(var i=0;i<$('#subjectType-main2 .subjectType').length;i++){
                var values = $('#subjectType-main2 .subjectType:eq('+ i +')').find('option:selected').val();
                var $parentDetail = $('#subjectType-main2 .subjectTypeDetail:eq('+ i +')');
                var planEnrollingNumberV = $.trim($parentDetail.find('.planEnrollingNumber').val());
                var realEnrollingNumberV = $.trim($parentDetail.find('.realEnrollingNumber').val());
                var highestScoreV = $.trim($parentDetail.find('.highestScore').val());
                var highestPrecedenceV = $.trim($parentDetail.find('.highestPrecedence').val());
                var lowestScoreV = $.trim($parentDetail.find('.lowestScore').val());
                var lowestPrecedenceV = $.trim($parentDetail.find('.lowestPrecedence').val());
                var averageScoreV = $.trim($parentDetail.find('.averageScore').val());
                var averagePrecedenceV = $.trim($parentDetail.find('.averagePrecedence').val());

                if(values=="00" && $('#subjectType2').is(':visible')){
                    CommonFn.tipsDialog('温馨提示', '请选择理工类招生批次');
                    return false;
                }
                if(planEnrollingNumberV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写理工类计划数');
                    return false;
                }
                if(realEnrollingNumberV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写理工类录取数');
                    return false;
                }
                if(highestScoreV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写理工类最高分');
                    return false;
                }
                if(highestPrecedenceV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写理工类最高位次');
                    return false;
                }
                if(lowestScoreV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写理工类最低分');
                    return false;
                }
                if(lowestPrecedenceV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写理工类最低位次');
                    return false;
                }
                if(averageScoreV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写理工类平均分');
                    return false;
                }
                if(averagePrecedenceV==""){
                    CommonFn.tipsDialog('温馨提示', '请填写理工类平均位次');
                    return false;
                }
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
                if(batchV!=="00"){
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
                }

            };

            batchData = JSON.stringify(batchData)



            var Datas = {
                "areaId": selProvinceV,
                "universityId": autoSearchId,
                "majorId":selSpecialtyV,
                "admissionBatchId":natureV,
                "year": selYearsV,
                "batchContent":batchData ,
                "oper": typeStr,
                "lengthOfSchooling":schoolIngV,
                "schoolFee":tuitionV
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
                    console.log(result)
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });
        };
    });
</script>