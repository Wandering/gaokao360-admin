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
        getMajored:'/admin/gaokao360/ex/getMajoredCategoryList',//获取学科分类
        saveData: '/admin/gaokao360/ex/commonsave/' + mainObj,
        flashSwfUrl: '/assets/js/webuploader-0.1.5/Uploader.swf', // 上传选择按钮渲染
        uploaderUrl: 'http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile?userId=gk360&dirId=0&productCode=gk360&bizSystem=gk360&spaceName=gk360',
        eduLevel: '/admin/gaokao360/ex/eduLevel',
        AllSchoolUrl: '/admin/gaokao360/ex/getUniversityByName',
        getBatchUrl: '/admin/gaokao360/geBatch',
        professionCategoryUrl: '/admin/zgk/getProfessionCategory',
        dictListUrl: '/admin/gaokao360/ex/getDataDictList?type=UNIVERSITY_MAJOR_TYPE', // 科类
        majoredUrl: '/admin/gaokao360/ex/getMajoredNameList', // 所有专业
        getNature: '/admin/gaokao360/ex/getDataDictList?type=ADMISSION_BATCH_TYPE', // 招生性质
        getUniversityNameList: '/admin/gaokao360/ex/getUniversityNameList', // 开设院校
        getMajoredTypeUrl: '/admin/gaokao360/ex/getDataDictList?type=MAJOR_TYPE', // 专业类型
        getEdulevelUrl: '/admin/gaokao360/ex/getDataDictList?type=EDULEVEL', // 学历层次
        getDegreeUrl: '/admin/gaokao360/ex/getDataDictList?type=GAIN_DEGREE', // 学位
        getuniversityTypeUrl: '/admin/gaokao360/ex/getDataDictList?type=UNIVERSITY_TYPE', // 院校类型
        getSchoolIngUrl: '/admin/gaokao360/ex/getDataDictList?type=LENGTH_OF_SCHOOLING', // 学制
        getModuleUrl: '/admin/gaokao360/ex/queryVideoType' // 视频模块
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
    //获取学历层次
    getEduLevel: function () {
        var contentArr = ['<option value="00">请选择学历层次</option>'];
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.eduLevel, 'get', {}, function (res) {
            if (res.rtnCode == '0000000') {
                $.each(res.bizData, function (i, v) {
                    var id = res.bizData[i].dictId;
                    var name = res.bizData[i].name;
                    contentArr.push('<option value="' + id + '">' + name + '</option>');
                });
            }
        });
        $.ajaxSettings.async = true;
        return contentArr;
    },
    //获取政策一级分类
    getpolicy: function () {
        var contentArr = ['<option value="00">选择政策一级分类</option><option value="000">+新建政策分类+</option>'];
        $.ajaxSettings.async = false;
        CommonFn.getData('/admin/gaokao360/ex/getAdmissionBatch', 'get', {}, function (res) {
            if (res.rtnCode == '0000000') {
                $.each(res.bizData, function (i, v) {
                    var id = res.bizData[i].id;
                    var name = res.bizData[i].name;
                    contentArr.push('<option value="' + id + '">' + name + '</option>');
                });
            }
        });
        $.ajaxSettings.async = true;
        return contentArr.join('');
    },
    // 视频模块选择
    getModule: function () {
        var contentArr = ['<option value="00">选择视频模块</option>'];
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getModuleUrl, 'get', {}, function (res) {
            if (res.rtnCode == '0000000') {
                $.each(res.bizData, function (i, v) {
                    var id = res.bizData[i].id;
                    var name = res.bizData[i].classifyName;
                    contentArr.push('<option value="' + id + '">' + name + '</option>');
                });
            }
        });
        $.ajaxSettings.async = true;
        return contentArr.join('');
    },
    //获取学科分类
    getMajored: function () {
        var contentArr = ['<option value="00">请选择学科门类</option>'];
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getMajored, 'get', {}, function (res) {
            if (res.rtnCode == '0000000') {
                $.each(res.bizData, function (i, v) {
                    var id = v.id;
                    var name = v.name;
                    contentArr.push('<option value="' + id + '">' + name + '</option>');
                });
            }
        });
        $.ajaxSettings.async = true;
        return contentArr;
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
                    button_text: '选择上传图片',
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
    // 富媒体返回值htmlTable
    getTextareaDataTable: function (textareaV) {
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
        return contentArr.join('');
    },
    // 所属行业
    getProfessionCategory: function () {
        var contentArr = [];
        contentArr.push('<option value="00">请选择行业分类</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.professionCategoryUrl, 'GET', {}, function (result) {
            console.log(result)
            for (var i = 0; i < result.bizData.length; i++) {
                var provinceId = result.bizData[i].id;
                var provinceName = result.bizData[i].Type;
                contentArr.push('<option value="' + provinceId + '">' + provinceName + '</option>');
            }
        });
        return contentArr.join('');
    },
    // 学制
    getSchoolIng: function () {
        var contentArr = [];
        contentArr.push('<option value="00">请选择学制</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getSchoolIngUrl, 'GET', {}, function (result) {
            for (var i = 0; i < result.bizData.length; i++) {
                var SchoolIngId = result.bizData[i].dictId;
                var SchoolIngName = result.bizData[i].name;

                contentArr.push('<option value="' + SchoolIngId + '">' + SchoolIngName + '</option>');
            }
        });
        return contentArr.join('');
    },
    getProfession: function (id) {
        var contentArr = [];
        contentArr.push('<option value="00">请选择行业分类</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.professionCategoryUrl+'?pid='+id, 'GET', {}, function (result) {
            console.log(result);
            for (var i = 0; i < result.bizData.length; i++) {
                var provinceId = result.bizData[i].id;
                var provinceName = result.bizData[i].Type;
                contentArr.push('<option value="' + provinceId + '">' + provinceName + '</option>');
            }
        });
        return contentArr.join('');
    },
    getProfessionHot: function (id) {
        var contentArr = [];
        contentArr.push('<option value="00">请选择职业分类</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.professionCategoryUrl+'?pid='+id, 'GET', {}, function (result) {
            console.log(result);
            for (var i = 0; i < result.bizData.length; i++) {
                var provinceId = result.bizData[i].id;
                var provinceName = result.bizData[i].Type;
                contentArr.push('<option value="' + provinceId + '">' + provinceName + '</option>');
            }
        });
        return contentArr.join('');
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
            data: false,
            autoclose: true,
            format: 'yyyy-mm'
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
        return contentArr.join('');
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
        return contentArr.join('');
    },
    // 获取全国学校
    getAllSchool: function (name) {
        var contentArr = null;
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.AllSchoolUrl, 'Post', {name:name}, function (result) {
            if (result.rtnCode == "0000000") {
                contentArr = result.bizData;
            }
        });
        return contentArr;
    },
    // 获取开设院校
    getUniversityNameList: function () {
        var contentArr = [];
        contentArr.push('<option value="00">请选择开设院校</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getUniversityNameList, 'GET', {}, function (result) {
            console.log(result)
            for (var i = 0; i < result.bizData.length; i++) {
                var specialityId = result.bizData[i].id;
                var specialityName = result.bizData[i].name;
                contentArr.push('<option value="' + specialityId + '">' + specialityName + '</option>');
            }
        });
        return contentArr.join('');
    },
    // 获取所有专业
    getSpeciality: function () {
        var contentArr = [];
        contentArr.push('<option value="00">请选择专业</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.majoredUrl, 'GET', {}, function (result) {
            for (var i = 0; i < result.bizData.length; i++) {
                var specialityId = result.bizData[i].id;
                var specialityName = result.bizData[i].majorName;
                contentArr.push('<option value="' + specialityId + '">' + specialityName + '</option>');
            }
        });
        return contentArr.join('');
    },
    // 获取院校类型
    getuniversityType: function () {
        var contentArr = [];
        contentArr.push('<option value="00">请选择院校类型</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getuniversityTypeUrl, 'GET', {}, function (result) {
            console.log(result)
            for (var i = 0; i < result.bizData.length; i++) {
                var universityTypeId = result.bizData[i].dictId;
                var universityTypeName = result.bizData[i].name;
                contentArr.push('<option value="' + universityTypeId + '">' + universityTypeName + '</option>');
            }
        });
        return contentArr.join('');
    },
    // 批次
    getBatch:function(){
        var contentArr = [];
        contentArr.push('<option value="00">请选择批次</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getBatchUrl, 'GET', {}, function (result) {
            if (result.rtnCode == "0000000") {
                for (var i=0;i<result.bizData.length;i++) {
                    contentArr.push('<option value="' +result.bizData[i].dictId + '">' + result.bizData[i].name + '</option>');
                }
            }

        });
        return contentArr.join('');
    },
    // 学历层次
    getEdulevel:function(){
        var contentArr = [];
        contentArr.push('<option value="00">请选择学历层次</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getEdulevelUrl, 'GET', {}, function (result) {
            console.log(result)
            if (result.rtnCode == "0000000") {
                for (var i=0;i<result.bizData.length;i++) {
                    contentArr.push('<option value="' +result.bizData[i].dictId + '">' + result.bizData[i].name + '</option>');
                }
            }

        });
        return contentArr.join('');
    },
    // 学位
    getDegree:function(){
        var contentArr = [];
        contentArr.push('<option value="00">请选择学历层次</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getDegreeUrl, 'GET', {}, function (result) {
            console.log(result)
            if (result.rtnCode == "0000000") {
                for (var i=0;i<result.bizData.length;i++) {
                    contentArr.push('<option value="' +result.bizData[i].dictId + '">' + result.bizData[i].name + '</option>');
                }
            }

        });
        return contentArr.join('');
    },
    // 科类
    getSubjectType:function(){
        var contentArr = [];
        contentArr.push('<option value="00">请选择科类</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.dictListUrl, 'GET', {}, function (result) {
            if (result.rtnCode == "0000000") {
                for (var i=0;i<result.bizData.length;i++) {
                    contentArr.push('<option value="' +result.bizData[i].dictId + '">' + result.bizData[i].name + '</option>');
                }
            }

        });
        return contentArr.join('');
    },
    // 专业类型
    getMajoredType:function(){
        var contentArr = [];
        contentArr.push('<option value="00">请选择专业类型</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getMajoredTypeUrl, 'GET', {}, function (result) {
            if (result.rtnCode == "0000000") {
                for (var i=0;i<result.bizData.length;i++) {
                    contentArr.push('<option value="' +result.bizData[i].dictId + '">' + result.bizData[i].name + '</option>');
                }
            }

        });
        return contentArr.join('');
    },
    // 招生性质
    getNature:function(){
        var contentArr = [];
        contentArr.push('<option value="00">请选择招生性质</option>');
        $.ajaxSettings.async = false;
        CommonFn.getData(CommonFn.url.getNature, 'GET', {}, function (result) {
            if (result.rtnCode == "0000000") {
                for (var i=0;i<result.bizData.length;i++) {
                    contentArr.push('<option value="' +result.bizData[i].dictId + '">' + result.bizData[i].name + '</option>');
                }
            }

        });
        return contentArr.join('');
    }






// 批次

};