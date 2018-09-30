
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
            <form class="form-ajax form-horizontal" method="post" action="/system/employeeHomeInfo/save"
                  data-target="/system/employee/edit?pkEmployee=${(employee.pkEmployee)!}">
                <input type="hidden" name="home.pkEmployee" value="${(employeeHomeInfo.pkEmployee)!}">
                <input type="hidden" name="home.pkEmployeeHomeInfo" value="${(employeeHomeInfo.pkEmployeeHomeInfo)!}">
                <input type="hidden" name="home.creator" value="${(employeeHomeInfo.creator)!}">
                <div class="form-group row">
                    <label class="control-label col-lg-2">姓名</label>
                    <div class="col-lg-4">
                        <input  name="home.name" value="${(employeeHomeInfo.name)!}" class="form-control" data-bv-notempty data-bv-notempty-message="请录入家属姓名" title="" type="text">
                    </div><!-- /.col -->

                    <label class="control-label col-lg-2">关系</label>
                    <div class="col-lg-4">
                        <input name="home.relationship" value="${(employeeHomeInfo.relationship)!}" class="form-control" data-bv-notempty data-bv-notempty-message="请录入员工关系" title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">工作单位</label>
                    <div class="col-lg-4">
                        <input name="home.jobUnit" value="${(employeeHomeInfo.jobUnit)!}" class="form-control"  title="" type="text">
                    </div><!-- /.col -->

                    <label class="control-label col-lg-2">工作岗位</label>
                    <div class="col-lg-4">
                        <input name="home.jobTitle" value="${(employeeHomeInfo.jobTitle)!}" class="form-control"  title="" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">联系电话</label>
                    <div class="col-lg-4">
                        <input name="home.phone" value="${(employeeHomeInfo.phone)!}" class="form-control"  title="" type="text">
                    </div><!-- /.col -->
                </div>

                <div class="form-group row">
                    <label class="control-label col-lg-2">备注</label>
                    <div class="col-lg-10">
                        <textarea class="form-control" name="home.memo" rows="4" va>${(employeeHomeInfo.memo)!}</textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">创建人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(employeeHomeInfo.creator)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">创建时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${employeeHomeInfo.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">修改人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(employeeHomeInfo.modifier)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">修改时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${employeeHomeInfo.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
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
</script>