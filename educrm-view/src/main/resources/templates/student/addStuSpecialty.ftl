


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title"  id="myModalLabel">
                特长爱好
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin form-ajax" method="post" action="/student/studentSpecialty/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">
                <input type="hidden" name="stu.pkDomain" value="${(student.pkDomain)!}">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">学生姓名</label>
                        <div class="col-lg-9">
                            <input class="form-control" name="stu.pkStudent" value="${(student.pkStudent)!}" title="" type="hidden">
                            <input class="form-control" readonly value="${(student.caption)!}" title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">学习时长</label>
                        <div class="col-lg-9">
                            <input class="form-control" name="stu.studyTime" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">特长爱好</label>
                        <div class="col-lg-9">
                            <input  class="form-control" name="stu.specialty" data-bv-notempty data-bv-notempty-message="请录入特长" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">奖项成就</label>
                        <div class="col-lg-9">
                            <input  class="form-control" name="stu.awardsAchievements" title="" type="text">
                        </div><!-- /.col -->
                    </div>



                </div>




                    <div class="form-group row">
                        <label class="control-label col-lg-2">备注</label>
                        <div class="col-lg-10">
                            <textarea class="form-control" name="stu.notes" rows="4" va>${(specialty.notes)!}</textarea>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-2">创建人</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(specialty.map.creatorEntity.caption)!}" type="text"/>
                        </div><!-- /.col -->
                        <label class="control-label col-lg-2">创建时间</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(specialty.creationDate? string("yyyy-MM-dd HH:mm:ss"))!}"   type="text"/>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-2">修改人</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(specialty.map.modifierEntity.caption)!}" type="text"/>
                        </div><!-- /.col -->
                        <label class="control-label col-lg-2">修改时间</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(specialty.lasteditDate? string("yyyy-MM-dd HH:mm:ss"))!}"  type="text"/>
                        </div><!-- /.col -->
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
                    setTimeout("window.location.href='"+$form.data('target')+"'",1000);
                }
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });

</script>