
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">学生评分</h4>
        </div>
        <form class="form-ajax" method="post" action="/classinfo/classinfo/insertActivityStudent">
            <div class="modal-body">
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">班级</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" id="classCap" value="" class="form-control" disabled>
                    </div>
                    <input type="hidden" id="pkClassinfo" name="pkClassinfo" value="">
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">学生</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" id="studentCaption" value="" class="form-control" disabled>
                    </div>
                    <input type="hidden" id="pkStudent" name="pkStudent" value="">
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">老师评语</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" value="" name="evaluate" class="form-control">
                    </div>
                </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="submit" class="btn btn-primary">提交</button>
        </div>
        </form>
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
                window.location.reload();
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });

    $(document).ready(function() {
        parent.parentAssignment(pkClassinfo,classCap,pkStudent,studentCaption)
    } );
</script>