
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Student shift
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax form-horizontal" method="post" action="/system/sysRole/save"
                  data-target="/system/sysRole/query">
                <input type="hidden" name="role.pkDomain" value="${(sysRole.pkDomain)!}">

                <div class="form-group">
                    <label class="control-label col-lg-3"><i class="red">*</i>Document number</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3"><i class="red">*</i>Student number</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3">Student name</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3">Headmaster</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3"><i class="red">*</i>new class</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                <#--<button type="submit" class="btn btn-success btn-sm">Determine</button>-->
                    <input class="btn	btn-success btn-sm"  name="dosubmit" value="Determine" type="submit">
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->
