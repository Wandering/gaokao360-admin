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


    $("#search").click(function () {
        searchLoad();

    });
</script>