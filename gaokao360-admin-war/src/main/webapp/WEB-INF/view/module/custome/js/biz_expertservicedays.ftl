<script>
    <!-- 自定义js请写在这个文件  以下这个查询方法只是个例子，请按照业务需求修改 -->
    function buildRules() {
        var rules = [];
        return rules;
    }
    $("#search").click(function () {
        searchLoad();
    });
    jQuery(function ($) {
        var typeStr;
        var rowId;

        var dialogHtml = ''
                + '<div class="row" id="dialogHtml">'
                + '    <div class="col-xs-12">'
                + '      <div class="form-horizontal" role="form">'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 专家姓名：</label>'
                + '              <div class="col-sm-4">'
                + '                     <span>张专家</span>'
                + '                  <input class="form-control" style="hidden" type="hidden" value="2" id="expertId" />'
                + '              </div>'
                + '          </div>'
                + '          <div class="form-group" id="expertDate">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 服务日期：</label>'
                + '              <div class="col-sm-9 expertDate-main">'
                + '                  <div class="col-sm-11 expertDateList" dataId="1">'
                + '                     <div>'
                + '                      <div class="col-sm-3">'
                + '                         <div class="input-group">'
                + '                             <input class="form-control date-picker date-picker3" placeholder="请选择服务日期" type="text" data-date-format="yyyy-mm-dd" />'
                + '                             <span class="input-group-addon">'
                + '                             <i class="fa fa-calendar bigger-110"></i>'
                + '                             </span>'
                + '                         </div>'
                + '                      </div>'
                + '                      <div class="col-sm-9">'
                + '                             <div class="col-sm-1">'
                + '                                <button class="btn btn-minier btn-pink deleteExpertDateBtn">删除</button>'
                + '                                <div class="line"></div>'
                + '                             </div>'
                + '                      </div>';
    dialogHtml += '                      <div class="form-group col-sm-12 expertDateDetail">'
//                + '                           <div class="col-sm-12">'
//                + '                                 <div class="col-sm-11"></div>'
//                + '                           </div>'
                + '                           <div class="form-group dateDetail">'
                + '                             <div>'
                + '                               <div class="col-sm-11 dateDetail-main">'
                + '                                   <div class="dateDetailList col-sm-12" dataId="1">'
                + '                                       <div class="form-group col-sm-11 dateDetailDetail">'
                + '                                         <div class="form-group col-sm-12">'
                + '                                            <div class="col-sm-11">'
                + '                                                 <label class="col-sm-3 control-label no-padding-right"> 服务时间：</label>'
                + '                                                 <input class="form-control date-picker data-picker1" placeholder="请选择开始服务时间" type="text" data-date-format="hh:MM:ss" />'
                + '                                                 <div class="col-sm-1">  ————  </div>'
                + '                                                 <input class="form-control date-picker data-picker2" placeholder="请选择结束服务时间" type="text" data-date-format="hh:MM:ss" />'
                + '                                            </div>';
    dialogHtml+=  '                                           <div class="col-sm-1">'
                + '                                               <button class="btn btn-minier btn-pink deleteDateDetailBtn">删除</button>'
                + '                                               <div class="line"></div>'
                + '                                            </div>'
                + '                                         </div>'
                + '                                       </div>'
                + '                                   </div>'
                + '                               </div>'
                + '                               <div class="col-sm-1">'
                + '                                   <button class="btn btn-sm btn-primary dateDetailBtn">增加</button>'
                + '                               </div>'
                + '                              </div>'
                + '                           </div>'
                + '                      </div>'
                + '                    </div>'
                + '                  </div>'
                + '                  <div class="col-sm-1">'
                + '                      <button class="btn btn-sm btn-primary expertDateBtn" >增加</button>'
                + '                  </div>'
                + '              </div>'
                + '          </div>'
                + '      </div>'
                + '  </div>'
                + '</div>';

//        日期增删
        function expertDateFn(name, delName) {
            var list = $('.' + name + '-main').find('.' + name + 'List').html();
            // 增加招生批次明细
            console.info(name);
            $('.expertDate-main').on('click','.' + name + 'Btn', function (event) {
                event.stopPropagation();
                event.preventDefault();
                $(this).parent().prev().append('<div class="' + name + 'List" dataId="' + name + '">' + list + '</div>');
//                $('.' + name + '-main').append('<div class="' + name + 'List" dataId="' + name + '">' + list + '</div>');
                CommonFn.renderDate('.date-picker');
//                $(document).find('.delete' + delName + 'Btn:eq(last-child)').hide();
            });
            $('.expertDate-main').find('.delete' + delName + 'Btn:eq(0)').hide();

            // 删除批次
            $('.expertDate-main').on('click', '.delete' + delName + 'Btn', function (event) {
                event.stopPropagation();
                event.preventDefault();
                $(this).parents('.' + name + 'List').remove();
            });
        }

        // 添加
        $("#addBtn").on(ace.click_event, function (e) {
            typeStr = "add";
            bootbox.dialog({
                title: "添加专家服务时间",
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
                        "callback": function () {

                        }
                    }

                }
            });
            expertDateFn("expertDate", "ExpertDate");
            expertDateFn("dateDetail", "DateDetail");
            CommonFn.renderDate('.date-picker');
        });
        //删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
//        添加
        var addEditFun = function () {
            var expertId = $('#expertId').val();
            if (expertId == undefined || expertId == '' || expertId == null) {
                CommonFn.tipsDialog('温馨提示', '请选择一个专家');
                return false;
            }
            console.log($('.expertDate-main').length)
            for (var i = 0; i < $('.expertDate-main .date-picker3').length; i++) {
                var picker3 = $('.expertDate-main .date-picker3:eq(' + i + ')');
                console.log(picker3.val())

                for(var j = 0; j < $('.expertDate-main .dateDetail-main').length; j++){
                    var picker1 = $('.dateDetail-main .date-picker1:eq(' + i + ')').val();
                    var picker2 = $('.dateDetail-main .date-picker2:eq(' + i + ')').val();
                    console.log(picker1)
                    console.log(picker2)
                }
            }

            <#--for (var i = 0; i < $('#subjectType-main2 .subjectType').length; i++) {-->
                <#--var values = $('#subjectType-main2 .subjectType:eq(' + i + ')').find('option:selected').val();-->
                <#--var $parentDetail = $('#subjectType-main2 .subjectTypeDetail:eq(' + i + ')');-->
                <#--var planEnrollingNumberV = $.trim($parentDetail.find('.planEnrollingNumber').val());-->
                <#--var realEnrollingNumberV = $.trim($parentDetail.find('.realEnrollingNumber').val());-->
                <#--var highestScoreV = $.trim($parentDetail.find('.highestScore').val());-->
                <#--var highestPrecedenceV = $.trim($parentDetail.find('.highestPrecedence').val());-->
                <#--var lowestScoreV = $.trim($parentDetail.find('.lowestScore').val());-->
                <#--var lowestPrecedenceV = $.trim($parentDetail.find('.lowestPrecedence').val());-->
                <#--var averageScoreV = $.trim($parentDetail.find('.averageScore').val());-->
                <#--var averagePrecedenceV = $.trim($parentDetail.find('.averagePrecedence').val());-->

                <#--if (values == "00" && $('#subjectType2').is(':visible')) {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请选择理工类招生批次');-->
                    <#--return false;-->
                <#--}-->
                <#--if (planEnrollingNumberV == "") {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请填写理工类计划数');-->
                    <#--return false;-->
                <#--}-->
                <#--if (realEnrollingNumberV == "") {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请填写理工类录取数');-->
                    <#--return false;-->
                <#--}-->
                <#--if (highestScoreV == "") {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请填写理工类最高分');-->
                    <#--return false;-->
                <#--}-->
                <#--if (highestPrecedenceV == "") {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请填写理工类最高位次');-->
                    <#--return false;-->
                <#--}-->
                <#--if (lowestScoreV == "") {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请填写理工类最低分');-->
                    <#--return false;-->
                <#--}-->
                <#--if (lowestPrecedenceV == "") {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请填写理工类最低位次');-->
                    <#--return false;-->
                <#--}-->
                <#--if (averageScoreV == "") {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请填写理工类平均分');-->
                    <#--return false;-->
                <#--}-->
                <#--if (averagePrecedenceV == "") {-->
                    <#--CommonFn.tipsDialog('温馨提示', '请填写理工类平均位次');-->
                    <#--return false;-->
                <#--}-->
            <#--}-->


            <#--var batchData = [];-->
            <#--var batchType = {}-->
            <#--for (var i = 0; i < $('.subjectTypeList').length; i++) {-->
                <#--var universityMajorTypeV = $('.subjectTypeList:eq(' + i + ')').attr('dataid');-->
                <#--var batchV = $('.subjectTypeList:eq(' + i + ')').find('.subjectType option:checked').val();-->
                <#--var planEnrollingNumberV = $('.subjectTypeList:eq(' + i + ')').find('.planEnrollingNumber').val();-->
                <#--var realEnrollingNumberV = $('.subjectTypeList:eq(' + i + ')').find('.realEnrollingNumber').val();-->
                <#--var highestScoreV = $('.subjectTypeList:eq(' + i + ')').find('.highestScore').val();-->
                <#--var highestPrecedenceV = $('.subjectTypeList:eq(' + i + ')').find('.highestPrecedence').val();-->
                <#--var lowestScoreV = $('.subjectTypeList:eq(' + i + ')').find('.lowestScore').val();-->
                <#--var lowestPrecedenceV = $('.subjectTypeList:eq(' + i + ')').find('.lowestPrecedence').val();-->
                <#--var averageScoreV = $('.subjectTypeList:eq(' + i + ')').find('.averageScore').val();-->
                <#--var averagePrecedenceV = $('.subjectTypeList:eq(' + i + ')').find('.averagePrecedence').val();-->
                <#--if (batchV !== "00") {-->
                    <#--batchType = {-->
                        <#--"universityMajorType": universityMajorTypeV,-->
                        <#--"batch": batchV,-->
                        <#--"planEnrollingNumber": planEnrollingNumberV,-->
                        <#--"realEnrollingNumber": realEnrollingNumberV,-->
                        <#--"highestScore": highestScoreV,-->
                        <#--"highestPrecedence": highestPrecedenceV,-->
                        <#--"lowestScore": lowestScoreV,-->
                        <#--"lowestPrecedence": lowestPrecedenceV,-->
                        <#--"averageScore": averageScoreV,-->
                        <#--"averagePrecedence": averagePrecedenceV-->
                    <#--};-->
                    <#--batchData.push(batchType);-->
                <#--}-->

            <#--}-->
            <#--;-->

            <#--batchData = JSON.stringify(batchData)-->


            <#--var Datas = {-->
                <#--"areaId": selProvinceV,-->
                <#--"majorId": selSpecialtyV,-->
                <#--"admissionFeature": natureV,-->
                <#--"year": selYearsV,-->
                <#--"batchContent": batchData,-->
                <#--"oper": typeStr,-->
                <#--"lengthOfSchooling": schoolIngV,-->
                <#--"schoolFee": tuitionV-->
            <#--};-->
            <#--console.log(Datas);-->
            <#--if (typeStr == 'edit') {-->
                <#--Datas.id = rowId;-->
            <#--}-->
            <#--$.ajax({-->
                <#--type: "POST",-->
                <#--url: '/admin/${bizSys}/commonsave/${mainObj}',-->
                <#--data: Datas,-->
                <#--success: function (result) {-->
                    <#--console.log(result)-->
                    <#--if (result.rtnCode == "0000000") {-->
                        <#--searchLoad();-->
                    <#--}-->
                <#--}-->
            <#--});-->
        };
    });
</script>
