


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title"  id="myModalLabel">
                退费
            </h4>
        </div>
        <form class="form-horizontal form-ajax" method="POST" action="/finance/payables/refund"
              data-target="/finance/payables/query">
            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label col-lg-3">总费用</label>
                    <div class="col-lg-9">
                        <input class="form-control"  value="${(payables.cost?c)!}" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3">退费金额</label>
                    <div class="col-lg-9">
                        <input class="form-control" name="cost" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-3">备注</label>
                    <div class="col-lg-9">
                        <input class="form-control" name="notes" value="${(payables.notes)!}" title="" type="text">
                    </div><!-- /.col -->
                </div>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="pkPayables" value="${(payables.pkPayables)!}">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                <button type="submit" class="btn btn-success btn-sm">确定</button>
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
                window.location.reload();
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });
</script>

