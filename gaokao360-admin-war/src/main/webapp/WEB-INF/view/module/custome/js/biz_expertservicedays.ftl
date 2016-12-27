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
        var queryExpert = function() {
            var contentArr = [];
            contentArr.push('<option value="00">请选择专家</option>');
            $.ajaxSettings.async = false;
            CommonFn.getData(CommonFn.url.queryExpert, 'GET', {}, function (result) {
                console.log(result)
                for (var i = 0; i < result.bizData.length; i++) {
                    var expertId = result.bizData[i].id;
                    var expertName = result.bizData[i].expertName;
                    contentArr.push('<option value="' + expertId + '">' + expertName + '</option>');
                }
            });
            return contentArr.join('');
        }
        var expertData = queryExpert();
        var dialogHtml = ''
                + '<div class="row" id="dialogHtml">'
                + '    <div class="col-xs-12">'
                + '      <div class="form-horizontal" role="form">'
                + '          <div class="form-group">'
                + '              <label class="col-sm-2 control-label no-padding-right"> 专家姓名：</label>'
                + '              <div class="col-sm-4">'
                + '<select class="form-control" id="expertId">'
                +expertData
                + '</select>'
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
                + '                                                 <input class="col-sm-2 bootstrap-timepicker date-picker1" placeholder="请选择开始服务时间" type="text" data-date-format="HH:mm" />'
                + '                                                 <div class="col-sm-2">——</div>'
                + '                                                 <input class="col-sm-2 bootstrap-timepicker date-picker2" placeholder="请选择结束服务时间" type="text" data-date-format="HH:mm" />'
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
                CommonFn.renderTime('.bootstrap-timepicker');
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
            // 当前行数据

            bootbox.dialog({
                title: "添加专家服务时间",
                message: dialogHtml,
                className: 'my-modal',
                buttons: {
                    "success": {
                        "label": "<i class='ace-icon fa fa-check'></i> 提交",
                        "className": "btn-sm btn-success",
                        "callback":addEditFun
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
            CommonFn.renderTime('.bootstrap-timepicker');


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
            var expertDate = [];
            console.log($('.expertDate-main').length)
            for (var i = 0; i < $('.expertDate-main .date-picker3').length; i++) {
                var picker3 = $('.expertDate-main .date-picker3').eq(i).val();
                var pickerData = {};
                var picker = picker3;
                for(var j = 0; j < $('.expertDate-main .dateDetail-main').length; j++){
                    console.log($('.dateDetail-main .date-picker1'))
                    console.log($('.dateDetail-main .date-picker2'))
                    var picker1 = $('.dateDetail-main .date-picker1').eq(i).val();
                    var picker2 = $('.dateDetail-main .date-picker2').eq(i).val();
                    var start = picker1;
                    var end = picker2;
                    pickerData.start=start;
                    pickerData.end=end;
                    pickerData.picker=picker;
                    expertDate.push(pickerData);
                }
            }
            var data = {
                expertId:expertId,
                expertTimes:expertDate
            }
            console.log(data)
            $.ajax({
                type: "POST",
                url: '/admin/zgkadmin/ex/addDate',
                data: {expertDateStr:JSON.stringify(data)},
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        searchLoad();
                    }
                }
            });

        };
    });
</script>
