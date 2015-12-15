;
var CommonFn;
CommonFn = {
    /**
     *
     * @param ajaxUrl
     * @returns {string}
     */
    getData: function (ajaxUrl, type, data) {
        var returnStr = [];
        $.ajaxSettings.async = false;
        $.getJSON(ajaxUrl, function (result) {
            if (result.rtnCode == "0000000") {
                returnStr.push(result);
            }
        });
        $.ajaxSettings.async = true;
        return returnStr;
    },
    // textarea
    getTextareaUrlData: function (ajaxUrl, textareaHtml) {
        var returnStr = [];
        $.ajaxSettings.async = false;
        $.ajax({
            type: 'POST',
            url: ajaxUrl,
            data: {
                content: textareaHtml
            },
            success: function (result) {
                if (result.rtnCode == '0000000') {
                    var jsonData = JSON.parse(result.bizData);
                    if (jsonData.rtnCode == '0000000') {
                        returnStr.push(jsonData.bizData.file.fileUrl);
                    }
                }
            }
        });
        $.ajaxSettings.async = true;
        return returnStr;
    },
    // 省份
    getProvince: function () {
        var provinceData = CommonFn.getData('/admin/gaokao360/ex/getProvince');
        var options = [];
        for (var i = 0; i < provinceData[0].bizData.length; i++) {
            var provinceId = provinceData[0].bizData[i].id;
            var provinceName = provinceData[0].bizData[i].name;
            options.push('<option value="00">请选择省份</option>');
            options.push('<option value="' + provinceId + '">' + provinceName + '</option>');
        }
        return options;
    },
    // 富媒体返回值html
    getTextareaData: function (textareaV) {
        var hotContentHtml = [];
        hotContentHtml.push('<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Document</title></head><body>');
        hotContentHtml.push(textareaV);
        hotContentHtml.push('</body></html>');
        return hotContentHtml;
    }
    // 年份
    // 课程
    // 批次

};