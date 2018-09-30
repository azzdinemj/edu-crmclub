
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">

                生成单据
            </h4>
        </div>
            <form class="form-horizontal no-margin  form-ajax" method="post" action="/student/studentShift/saveFinancial"
                  data-target="/student/studentShift/query">
            <div class="modal-body" >
                <div class="form-group row">
                    <label class="control-label col-lg-3">原总额</label>
                    <div class="col-lg-9">
                        <input id="oldMoney" name="oldMoney" class="form-control" title="" type="number">
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-3">现总额</label>
                    <div class="col-lg-9">
                        <input id="money" name="money" class="form-control" title="" type="number">
                    </div><!-- /.col -->
                </div>
            </div>
                <div class="modal-footer">
                    <input type="hidden" name="pkStudentShift" value="${(pkStudentShift)!}">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-success btn-sm">提交</button>
                </div>
            </form>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script>
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
                window.location.reload();
            }
        }, 'json');
    });
</script>