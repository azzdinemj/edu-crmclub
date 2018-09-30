
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                学生转班
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax form-horizontal" method="post" action="/system/sysRole/save"
                  data-target="/system/sysRole/query">
                <input type="hidden" name="role.pkDomain" value="${(sysRole.pkDomain)!}">

                <div class="form-group">
                    <label class="control-label col-lg-3"><i class="red">*</i>单据编号</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3"><i class="red">*</i>学生编号</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3">学生姓名</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3">班主任</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3"><i class="red">*</i>转入班级</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
                    <input class="btn	btn-success btn-sm"  name="dosubmit" value="确定" type="submit">
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->
