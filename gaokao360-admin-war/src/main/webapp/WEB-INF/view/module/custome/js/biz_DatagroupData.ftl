<script>
    <!-- �Զ���js��д������ļ�  ���������ѯ����ֻ�Ǹ����ӣ��밴��ҵ�������޸� -->
    function buildRules() {
        var courseName = $('#courseName').val();
        var status = $('#status').val();
        var classfyId = $('#classfyId').val();
        var rules = [];
        if (courseName != ''&&courseName!=null&&courseName!=undefined) {
            var rule = {
                'field': 'courseName',
                'op': 'eq',
                'data': courseName
            }
            rules.push(rule);
        }
        if (status != ''&&status!=null&&status!=undefined) {
            var rule = {
                'field': 'status',
                'op': 'eq',
                'data': status
            }
            rules.push(rule);
        }
        if (classfyId != ''&&classfyId!=null&&classfyId!=undefined) {
            var rule = {
                'field': 'classfyId',
                'op': 'eq',
                'data': classfyId
            }
            rules.push(rule);
        }
        return rules;
    }
    function searchLoad(flag) {
        var url = "/admin/${bizSys}/${mainObj}s";
        var page = $('#grid-table').getGridParam('page'); // current page
        var rows = $('#grid-table').getGridParam('rows'); // rows
        var sidx = $('#grid-table').getGridParam('sidx'); // sidx
        var sord = $('#grid-table').getGridParam('sord'); // sord


        if (page == null || page == "") {
            page = '1';
        }

        if (flag == 1 || typeof flag == "undefined") {
            page = '1';
        }

        if (rows == null || rows == "") {
            rows = '10';
        }

        var rules = buildRules();

        var filters = {
            'groupOp': 'AND',
            "rules": rules
        };

        $("#grid-table").jqGrid('setGridParam', {
            url: url + "?filters=" + JSON.stringify(filters),
            page: page,
            rows: rows,
            sidx: sidx,
            sord: sord}).trigger("reloadGrid");
    }

    $("#search").click(function () {
        searchLoad();

    });
</script>