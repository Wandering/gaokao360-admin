<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="${path}/assets/css/bootstrap.min.css"/>
    <script src='${path}/assets/js/jquery.min.js'></script>
</head>
<body>

<div class="container">
    <div class="row">
        <h2 class="professionName"></h2>
        <h4>基本信息</h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>职业名称</th>
                <th>所属行业</th>
                <th>职业大类</th>
                <th>职业热度</th>
                <th>薪资排名</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="professionName"></td>
                <td class="professionType"></td>
                <td class="professionSubType"></td>
                <td class="hotDegree"></td>
                <td class="salaryRank"></td>
            </tr>
            </tbody>
        </table>
        <h4>相关专业</h4>
        <div class="relateMajor"></div>
        <h4>职业简介</h4>
        <div class="introduction"></div>
        <h4>工作内容</h4>
        <div class="workContent"></div>
        <h4>从业要求</h4>
        <div class="vocationalDemand"></div>
        <h4>就业前景</h4>
        <div class="careerProspects"></div>
    </div>
</div>


<script>
$(function(){
    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
    var detailId =  getUrlParam('id');
    $.getJSON('${path}/admin/zgk/getProfessionDetail?professionId='+detailId,function(result){
        console.log(result)
        if(result.rtnCode==="0000000"){
            var dataJson = result.bizData;
            var careerProspects = dataJson.careerProspects,
                    hotDegree = dataJson.hotDegree,
                    introduction = dataJson.introduction,
                    professionName = dataJson.professionName,
                    professionSubType = dataJson.professionSubType,
                    professionSubTypeId = dataJson.professionSubTypeId,
                    professionType = dataJson.professionType,
                    professionTypeId = dataJson.professionTypeId,
                    relateMajor = dataJson.relateMajor,
                    salaryRank = dataJson.salaryRank,
                    vocationalDemand = dataJson.vocationalDemand,
                    workContent = dataJson.workContent;
            $('.professionName').text(professionName);
            $('.professionType').text(professionType);
            $('.professionSubType').text(professionSubType);
            $('.hotDegree').text(hotDegree);
            $('.salaryRank').text(salaryRank);
            $('.relateMajor').text(relateMajor);
            $('.introduction').text(introduction);
            $('.workContent').text(workContent);
            $('.vocationalDemand').text(vocationalDemand);
            $('.careerProspects').text(careerProspects);

        };
    })
})
</script>
</body>
</html>