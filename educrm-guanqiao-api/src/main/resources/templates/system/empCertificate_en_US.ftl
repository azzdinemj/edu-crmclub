
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                ${(employee.caption)!}
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax form-horizontal" method="post" action="/system/employeeCertificate/save"
                  data-target="/system/employee/edit?pkEmployee=${(employee.pkEmployee)!}">
                <input type="hidden" name="cer.pkEmployee" value="${(employee.pkEmployee)!}">
                <input type="hidden" name="cer.pkEmployeeCertificate" value="${(employeeCertificate.pkEmployeeCertificate)!}">
                <input type="hidden" name="cer.creator" value="${(employeeCertificate.creator)!}">
                <div class="form-group row ">
                    <label class="control-label col-lg-2">Get time</label>
                    <div class="col-lg-4">
                        <input  name="cer.dateTime" value="${(employeeCertificate.date?string("yyyy-MM-dd"))!}" class="form-control js-datepicker" data-bv-notempty data-bv-notempty-message="Please enter the time" title="" type="text">
                    </div><!-- /.col -->

                    <label class="control-label col-lg-2">Name</label>
                    <div class="col-lg-4">
                        <input name="cer.certificateName" value="${(employeeCertificate.certificateName)!}" class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the name" title="" type="text">
                    </div><!-- /.col -->
                </div>


                <div class="form-group row">
                    <label class="control-label col-lg-2">Remarks</label>
                    <div class="col-lg-10">
                        <textarea class="form-control" name="cer.memo" rows="4" va>${(employeeCertificate.memo)!}</textarea>
                    </div><!-- /.col -->
                </div>

                <div class="form-group row">
                    <label class="control-label col-lg-2">Founder</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(employeeCertificate.creator)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Creation time</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${employeeCertificate.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Modifier</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(employeeCertificate.modifier)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Modified</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${employeeCertificate.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
                    </div><!-- /.col -->


                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                <#--<button type="submit" class="btn btn-success btn-sm">Determine</button>-->
                    <input class="btn	btn-success btn-sm"  name="dosubmit" value="Determine" type="submit">
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

    $('.js-datepicker').datetimepicker({//Specific date
        format: 'yyyy-mm-dd',
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0});
</script>