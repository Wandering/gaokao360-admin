<div class="page-content">
    <!-- 搜索start-->
    <form class="form-horizontal" role="form" action="/admin/${bizSys}/${mainObj}s">
        <div class="col-xs-12">
            <div class="col-sm-2">
                <div class="form-group">
                    <div class="col-sm-2">
                        <input type="text" class="" placeholder="关键字查询" id="keywords">
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="col-sm-4 control-label">所属行业:</label>

                    <div class="col-sm-8">
                        <select class="form-control" id="selProfession"></select>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="form-group hr10">
        <button class="btn btn-purple" id="addBtn"><i
                class="ace-icon fa fa-cloud-upload align-top bigger-125"></i>添加
        </button>
        <button class="btn btn-primary" id="editBtn"><i
                class="ace-icon fa fa-pencil-square-o align-top bigger-125"></i>修改
        </button>
        <button class="btn btn-danger" id="deleteBtn"><i class="ace-icon fa fa-trash-o align-top bigger-125"></i>删除
        </button>
    </div>
    <!-- 搜索end -->
<#include 'page_grid.ftl'>
</div><!-- /.page-content -->