
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Student score</h4>
        </div>
        <form class="form-ajax" method="post" action="/classinfo/classinfo/insertActivityStudent">
            <div class="modal-body">
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">class</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" value="${(activityStudent.className)!}" class="form-control" disabled>
                    </div>
                    <input type="hidden" name="pkClassinfo" value="${(activityStudent.pkClassinfo)!}">
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">Student</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" value="${(activityStudent.studentName)!}" class="form-control" disabled>
                    </div>
                    <input type="hidden" name="pkStudent" value="${(activityStudent.pkStudent)!}">
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">Teacher's comments</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" value="" name="evaluate" class="form-control">
                    </div>
                </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary">Submission</button>
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
</script>