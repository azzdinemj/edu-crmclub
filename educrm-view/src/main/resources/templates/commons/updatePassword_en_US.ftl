
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                edit password
            </h4>
        </div>
            <form class="form-horizontal no-margin  form-ajax" method="post" action="/savePassword"
                  data-target="/signout">
            <div class="modal-body" >
                <div class="form-group row">
                    <label class="control-label col-lg-3">New password</label>
                    <div class="col-lg-9">
                        <input type="password"  name="password" data-bv-notempty data-bv-notempty-message="Please enter the password" data-bv-identical data-bv-identical-field="passWordAffirm" data-bv-identical-message="The password should be consistent" class="form-control" title="" id="password" placeholder="at least6Character">
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-3">Confirm password</label>
                    <div class="col-lg-9">
                        <input type="password" name="passWordAffirm" data-bv-notempty data-bv-notempty-message="Please enter the password" data-bv-identical data-bv-identical-field="password" data-bv-identical-message="The password should be consistent" class="form-control" title="" id="passWordAffirm" placeholder="at least6Character">
                    </div><!-- /.col -->
                </div>
            </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-success btn-sm">Submission</button>
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