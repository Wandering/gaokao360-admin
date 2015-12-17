;
var CommonFn = {
    /**
     *
     * @param ajaxUrl
     * @returns {string}
     */
    url: {
        getProvince: '/admin/gaokao360/ex/getProvince',  // 获取省份
        getContentUrl: '/admin/gaokao360/ex/getContentUrl', // 保存textarea富媒体HTML
        getContentHtml: '/admin/gaokao360/ex/getHTMLContent', // 修改获取textarea富媒体内容
        getRowData: '/admin/gaokao360/ex/' + mainObj + 'queryone', // 获取行数据
        deleteData: '/admin/gaokao360/ex/commonsave/', // 删除数据
        getYear: '/admin/gaokao360/ex/' + mainObj + '/getYears', // 获取年份
        getSubjectUrl: '/admin/gaokao360/ex/getSubject', // 获取学科
        getEduLevel: '/admin/gaokao360/ex/getAdmissionBatch', // 获取政策一级分类
        saveData: '/admin/gaokao360/ex/commonsave/' + mainObj,
        flashSwfUrl: '/assets/js/webuploader-0.1.5/Uploader.swf', // 上传选择按钮渲染
        uploaderUrl: 'http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile?userId=gk360&dirId=0&productCode=gk360&bizSystem=gk360&spaceName=gk360'
    },
    getData: function (url, type, data, callback) {
        $.ajax({
            type: type,
            url: url,
            dataType: 'json',
            data: data,
            success: callback
        });
    },
    // 富媒体编辑器
    renderTextarea: function (obj) {
        $(obj).ace_wysiwyg({
            toolbar: [
                {
                    name: 'font',
                    title: 'Custom tooltip',
                    values: ['Some Font!', 'Arial', 'Verdana', 'Comic Sans MS', 'Custom Font!']
                },
                null,
                {
                    name: 'fontSize',
                    title: 'Custom tooltip',
                    values: {
                        1: 'Size#1 Text',
                        2: 'Size#1 Text',
                        3: 'Size#3 Text',
                        4: 'Size#4 Text',
                        5: 'Size#5 Text'
                    }
                },
                null,
                {name: 'bold', title: 'Custom tooltip'},
                {name: 'italic', title: 'Custom tooltip'},
                {name: 'strikethrough', title: 'Custom tooltip'},
                {name: 'underline', title: 'Custom tooltip'},
                null,
                'insertunorderedlist',
                'insertorderedlist',
                'outdent',
                'indent',
                null,
                {name: 'justifyleft'},
                {name: 'justifycenter'},
                {name: 'justifyright'},
                {name: 'justifyfull'},
                null,
                {
                    name: 'createLink',
                    placeholder: 'Custom PlaceHolder Text',
                    button_class: 'btn-purple',
                    button_text: 'Custom TEXT'
                },
                {name: 'unlink'},
                null,
                {
                    name: 'insertImage',
                    placeholder: 'Custom PlaceHolder Text',
                    button_class: 'btn-inverse',
                    //choose_file:false,//hide choose file button
                    button_text: 'Set choose_file:false to hide this',
                    button_insert_class: 'btn-pink',
                    button_insert: 'Insert Image'
                },
                null,
                {
                    name: 'foreColor',
                    title: 'Custom Colors',
                    values: ['red', 'green', 'blue', 'navy', 'orange'],
                    /**
                     You change colors as well
                     */
                },
            /**null,
             {
                 name:'backColor'
             },*/
                null,
                {name: 'undo'},
                {name: 'redo'},
                null,
                'viewSource'
            ],
            'wysiwyg': {
                hotKeys: {} //disable hotkeys
            }

        }).prev().addClass('wysiwyg-style2');
    },
    // 富媒体返回值html
    getTextareaData: function (textareaV) {
        var hotContentHtml = [];
        hotContentHtml.push('<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Document</title></head><body>');
        hotContentHtml.push(textareaV);
        hotContentHtml.push('</body></html>');
        return hotContentHtml;
    },
    // textarea
    getTextareaUrlData: function (textareaHtml) {
        var contentArr = [];
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getContentUrl, 'POST', {content: textareaHtml}, function (result) {
            if (result.rtnCode == '0000000') {
                var jsonData = JSON.parse(result.bizData);
                if (jsonData.rtnCode == '0000000') {
                    contentArr.push(jsonData.bizData.file.fileUrl);
                    contentArr.push(jsonData.bizData.file.id);
                }
            }
        });
        $.ajaxSettings.async = true;
        return contentArr;
    },
    // 修改获取textarea富媒体内容
    getContentHtml: function (informationContent) {
        var contentArr = [];
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getContentHtml, 'POST', {htmlurl: informationContent}, function (result) {
            if (result.rtnCode == "0000000") {
                contentArr.push(result.bizData);
            }
        });
        $.ajaxSettings.async = true;
        return contentArr;
    },
    // 查找当前选中行数据
    getRowData: function (rowId) {
        var contentArr = [];
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getRowData, 'GET', {id: rowId}, function (result) {
            if (result.rtnCode == "0000000") {
                contentArr.push(result.bizData);
            }
        });
        $.ajaxSettings.async = true;
        return contentArr;
    },
    // 省份
    getProvince: function () {
        var contentArr = [];
        contentArr.push('<option value="00">请选择省份</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getProvince, 'GET', {}, function (result) {
            for (var i = 0; i < result.bizData.length; i++) {
                var provinceId = result.bizData[i].id;
                var provinceName = result.bizData[i].name;
                contentArr.push('<option value="' + provinceId + '">' + provinceName + '</option>');
            }
        });
        return contentArr;
    },
    // date
    renderDate: function (obj) {
        $(obj).datepicker({
            autoclose: true,
            todayHighlight: true
        });
    },
    renderDateYear: function (obj) {
        $(obj).datepicker({
            data:false,
            autoclose: true,
            format:'yyyy-mm'
        });
    },
    // tipsDialog
    tipsDialog: function (title, message) {
        bootbox.dialog({
            title: title,
            message: '<span class="bigger-110 center">' + message + '</span>',
            buttons: {
                cancel: {
                    label: "关闭",
                    className: "btn-sm"
                }
            }
        });
    },
    deleteFun: function (obj, deleteData) {
        $(obj).on(ace.click_event, function () {
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
                                url: CommonFn.url.deleteData + deleteData,
                                data: {
                                    oper: 'del',
                                    id: rowId
                                },
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
    },
    // 科目
    getSubject: function () {
        var contentArr = [];
        contentArr.push('<option value="00">请选择科目</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getSubjectUrl, 'GET', {}, function (result) {
            for (var i = 0; i < result.bizData.length; i++) {
                var subjectId = result.bizData[i].id;
                var subjectName = result.bizData[i].subjectName;
                contentArr.push('<option value="' + subjectId + '">' + subjectName + '</option>');
            }
        });
        return contentArr;
    },
    // 年份
    getYear: function () {
        var contentArr = [];
        contentArr.push('<option value="00">请选择年份</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getYear, 'GET', {}, function (result) {
            for (var k in result.bizData) {
                var year = result.bizData[k];
                contentArr.push('<option value="' + year + '">' + year + '</option>');
            }
        });
        return contentArr;
    },
    //
    uploaderSwfFn: function () {

    }


    // 批次

};