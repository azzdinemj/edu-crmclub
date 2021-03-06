
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
            <form class="form-ajax form-horizontal" method="post" action="/system/employeeJobExp/save"
                  data-target="/system/employee/edit?pkEmployee=${(employee.pkEmployee)!}">
                <input type="hidden" name="ejob.pkEmployee" value="${(employee.pkEmployee)!}">
                <input type="hidden" name="ejob.pkEmployeeJobExp" value="${(employeeJobExp.pkEmployeeJobExp)!}">
                <input type="hidden" name="ejob.creator" value="${(employeeJobExp.creator)!}">
                <div class="form-group row">
                    <label class="control-label col-lg-2">开始时间</label>
                    <div class="col-lg-4">
                        <input  name="ejob.startdateTime" value="${(employeeJobExp.startdateTime)!}" class="form-control js-datepicker" data-bv-notempty data-bv-notempty-message="请录入开始时间" title="" type="text">
                    </div><!-- /.col -->

                    <label class="control-label col-lg-2">结束时间</label>
                    <div class="col-lg-4">
                        <input name="ejob.enddateTime" value="${(employeeJobExp.enddateTime)!}" class="form-control js-datepicker" data-bv-notempty data-bv-notempty-message="请录入结束时间" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">工作单位</label>
                    <div class="col-lg-4">
                        <input name="ejob.jobUnit" value="${(employeeJobExp.jobUnit)!}" class="form-control" data-bv-notempty data-bv-notempty-message="请录入工作单位" title="" type="text">
                    </div><!-- /.col -->

                    <label class="control-label col-lg-2">职务</label>
                    <div class="col-lg-4">
                        <input name="ejob.jobTitle" value="${(employeeJobExp.jobTitle)!}" class="form-control"  title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">证明人</label>
                    <div class="col-lg-4">
                        <input name="ejob.witness" value="${(employeeJobExp.witness)!}" class="form-control"  title="" type="text">
                    </div><!-- /.col -->

                    <label class="control-label col-lg-2">证明人电话</label>
                    <div class="col-lg-4">
                        <input name="ejob.witnessPhone" value="${(employeeJobExp.witnessPhone)!}" class="form-control"  title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group ">
                    <label class="control-label col-lg-2">离职原因</label>
                    <div class="col-lg-4">
                        <input name="ejob.reasonLeaving" value="${(employeeJobExp.reasonLeaving)!}" class="form-control"  title="" type="text">
                    </div><!-- /.col -->
                </div>


                <div class="form-group row">
                    <label class="control-label col-lg-2">备注</label>
                    <div class="col-lg-10">
                        <textarea class="form-control" name="ejob.memo" rows="4" va>${(employeeJobExp.memo)!}</textarea>
                    </div><!-- /.col -->
                </div>

                <div class="form-group row">
                    <label class="control-label col-lg-2">创建人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(employeeJobExp.creator)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">创建时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${employeeJobExp.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">修改人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(employeeJobExp.modifier)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">修改时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${employeeJobExp.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
                    </div><!-- /.col -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
                    <input class="btn	btn-success btn-sm"  name="dosubmit" value="确定" type="submit">
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


    $('.js-datepicker').datetimepicker({//年月日
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