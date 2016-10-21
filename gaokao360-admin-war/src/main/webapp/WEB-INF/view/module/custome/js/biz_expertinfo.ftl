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
                }else {
                    CommonFn.tipsDialog('温馨提示', '选中行没有需要下载的文件!');
                }
            }else{
                CommonFn.tipsDialog('温馨提示', '请选中一行再下载!');
            }


        });

        var downFile = function(url) {
            var content="下载"
            var aLink = document.createElement('a');
            var blob = new Blob([content]);
            var evt = document.createEvent("HTMLEvents");
            evt.initEvent("click", false, false);//initEvent 不加后两个参数在FF下会报错, 感谢 Barret Lee 的反馈
            aLink.download = url;
            aLink.href = URL.createObjectURL(blob);
            aLink.dispatchEvent(evt);
        }
//      删除
        CommonFn.deleteFun('#deleteBtn', '${mainObj}');

    });//$结束
</script>
