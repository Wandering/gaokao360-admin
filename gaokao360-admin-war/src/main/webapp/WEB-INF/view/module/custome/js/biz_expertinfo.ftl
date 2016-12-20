<script>
    function buildRules() {

        var rules = [];

        return rules;
    }



    /*
   *
   * 个人简历下载
   *
   * */
    jQuery(function ($) {

        //      添加招办联系电话
        $("#downBtn").on(ace.click_event, function () {
            var ids=$("#grid-table").jqGrid("getGridParam","selarrrow");
            if(ids.length==1){
                var rowId=$("#grid-table").jqGrid("getGridParam","selrow");
                var rowData = $("#grid-table").jqGrid('getRowData',rowId);
                if (rowData.expertProfile&&rowData.expertProfile!=null&&rowData.expertProfile!=''){
                    downFile(rowData.expertProfile);
                    console.info($(this));
                }else {
                    CommonFn.tipsDialog('温馨提示', '选中行没有需要下载的文件!');
                }
            }else{
                CommonFn.tipsDialog('温馨提示', '请选中一行再下载!');
            }


        });

        var downFile = function(url) {
            try{
                var elemIF = document.createElement("iframe");
                elemIF.src = url;
                elemIF.style.display = "none";
                document.body.appendChild(elemIF);
            }catch(e){

            }
        };

//      删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');
    });//$结束
</script>
