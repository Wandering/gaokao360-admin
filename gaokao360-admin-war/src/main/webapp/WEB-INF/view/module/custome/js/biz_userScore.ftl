<script>
        $("#search").click(function () {
            searchFun();
        })
        $("#reset").click(function () {
            resetFun();
        })
        var searchFun = function () {
            var searchInput = $("#searchInput").val();
            if (searchInput == '' || searchInput==undefined) {
                alert("用户手机不能为空！")
                return
            }
            $.ajax({
                type: "GET",
                url: '/admin/gaokao360/ex/getUserScore',
                data: {"account": searchInput},
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                            $("#uid").html(result.bizData.id)
                            $("#score").html(result.bizData.score)
                            $("#precedence").html(result.bizData.precedence)
                    }else{
                        alert("无查询结果2！")
                    }
                }
            });
        }

        var resetFun = function(){
            var uid= $("#uid").html();
            var data={
                "id":uid
            }
            $.ajax({
                type: "post",
                url: '/admin/gaokao360/ex/resetUserScore',
                data: data,
                success: function (result) {
                    if (result.rtnCode == "0000000") {
                        if(result.bizData){
                            alert("清除成功！")
                            clearFun();
                        }else{
                            alert("清除失败！")
                        }
                    }
                }
            });
        }

        var clearFun = function(){
            $("#uid").html("");
            $("#score").html("");
            $("#precedence").html("");
            $("#searchInput").val("");
        }
</script>