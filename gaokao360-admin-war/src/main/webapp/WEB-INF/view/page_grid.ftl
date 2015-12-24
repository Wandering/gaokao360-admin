<!-- 页面主体内容 -->
<div class="row">
    <style>
        .ui-jqgrid .ui-jqgrid-labels th{
            text-align: center!important;
        }
        .ui-jqgrid tr.ui-row-ltr td{
            text-align: center!important;
        }
        #grid-pager{
            width: 1193px!important;
            padding-right: 0!important;
            padding-left: 0!important;
            background-color: #F8F8F8 !important;
        }
        #gbox_grid-table{
            max-width: 1193px!important;
            overflow: hidden!important;
        }
    </style>
    <div class="col-xs-12">
        <table id="grid-table"></table>
        <div id="grid-pager"></div>
        <script type="text/javascript">
            var $path_base = "..";//this will be used for editurl parameter
        </script>
        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
</div><!-- /.row -->