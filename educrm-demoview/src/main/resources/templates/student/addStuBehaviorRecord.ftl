<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                新建学生处分
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin form-ajax look" method="post" action="/student/studentBehaviorRecord/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">

                <div class="col-sm-6">
                    <div class="form-group row">
                        <label class="control-label col-lg-3">校区名称</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="${(domain.caption)!}" readonly title="" type="text">
                            <input class="form-control" name="bere.pkDomain" value="${(domain.pkDomain)!}" title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">学生姓名</label>
                        <div class="col-lg-9">
                            <input class="form-control"  value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="bere.pkStudent" value="${(student.pkStudent)!}" readonly title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">事件情形</label>
                        <div class="col-lg-9">
                            <input data-bv-notempty data-bv-notempty-message="请录入时间情形" class="form-control" name="bere.details" title="" type="text">
                        </div><!-- /.col -->
                    </div>

                </div>
                <div class="col-sm-6">

                    <div class="form-group row">
                        <label class="control-label col-lg-3">年级</label>
                        <div class="col-lg-9">
                            <input data-bv-notempty data-bv-notempty-message="请录入年级" name="bere.grade" class="form-control" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">时间</label>
                        <div class="col-lg-9">
                            <input data-bv-notempty data-bv-notempty-message="请录入时间" name="bere.dateTime" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group row">
                        <label class="control-label col-lg-3">处分</label>
                        <div class="col-lg-9">
                            <input data-bv-notempty data-bv-notempty-message="请录入处分内容" name="bere.disciplinaryAction" class="form-control" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                </div>

                <div class="form-group row">
                    <label class="control-label col-sm-2">备&emsp;&emsp;注</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="bere.notes" rows="4" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">创建人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(behare.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">创建时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${behare.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">修改人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(behare.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">修改时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${behare.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-success btn-sm">确定</button>
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
    $('.form-ajax').bootstrapValidator().on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function(data) {

            if(data.code==0){
                Notify.success(data.message);
                if($form.data('target')){
                    setTimeout("window.location.href='"+$form.data('target')+"'",3000);
                }
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });
</script>