


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title"  id="myModalLabel">
                教育经历
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin form-ajax" method="post" action="/student/studentEduExperience/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">
                <input type="hidden" name="stu.pkDomain" value="${(student.pkDomain)!}">
                <div class="form-group">
                    <label class="control-label col-md-2">学校名称</label>
                    <div class="col-md-4">
                        <input class="form-control" data-bv-notempty data-bv-notempty-message="请录入学校名称" name="stu.schoolName" title="" type="text">
                    </div><!-- /.col -->

                    <label class="control-label col-md-2">学生姓名</label>
                    <div class="col-md-4">
                        <input class="form-control" readonly value="${(student.caption)!}" title="" type="text">
                        <input class="form-control" name="stu.pkStudent" value="${(student.pkStudent)!}" title="" type="hidden">
                    </div><!-- /.col -->

                    <div class="clearfix"></div>
                    <label class="control-label col-md-2">开始时间</label>
                    <div class="col-md-4">
                            <input class="form-control js-datepicker"  name="stu.startDateTime" data-bv-notempty data-bv-notempty-message="请录入开始时间" title="" type="text" >
                </div><!-- /.col -->
                <label class="control-label col-md-2">结束时间</label>
                <div class="col-md-4">
                    <input required="" class="form-control js-datepicker" name="stu.endDateTime" data-bv-notempty data-bv-notempty-message="请录入结束时间" title="" type="text">
                </div><!-- /.col -->

                <div class="clearfix"></div>
                <label class="control-label col-md-2">学校地址</label>
                <div class="col-md-4">
                    <input class="form-control" name="stu.schoolAddress" data-bv-notempty data-bv-notempty-message="请录入学校地址" title="" type="text">
                </div><!-- /.col -->
                <label class="control-label col-md-2">成绩</label>
                <div class="col-md-4">
                    <input required="" class="form-control" name="stu.academicRecord" title="" type="number">
                </div><!-- /.col -->

                 </div>
                <div class="clearfix"></div>
                <div class="form-group" style="height: auto!important;">
                        <label class="control-label col-md-2">备注</label>
                        <div class="col-md-10">
                            <textarea class="form-control" name="stu.notes" rows="4" va>${(experience.notes)!}</textarea>
                        </div><!-- /.col -->

                    </div>
                <div class="clearfix"></div>
                    <div class="form-group">
                        <label class="control-label col-md-2">创建人</label>
                        <div class="col-md-4">
                            <input readonly class="form-control" value="${(experience.map.creatorEntity.caption)!}" type="text"/>
                        </div><!-- /.col -->
                        <label class="control-label col-md-2">创建时间</label>
                        <div class="col-md-4">
                            <input readonly class="form-control" value="${(experience.creationDate? string("yyyy-MM-dd HH:mm:ss"))!}"   type="text"/>
                        </div><!-- /.col -->
                    </div>
                <div class="clearfix"></div>
                    <div class="form-group">
                        <label class="control-label col-md-2">修改人</label>
                        <div class="col-md-4">
                            <input readonly class="form-control" value="${(experience.map.modifierEntity.caption)!}" type="text"/>
                        </div><!-- /.col -->
                        <label class="control-label col-md-2">修改时间</label>
                        <div class="col-md-4">
                            <input readonly class="form-control" value="${(experience.lasteditDate? string("yyyy-MM-dd HH:mm:ss"))!}"  type="text"/>
                        </div><!-- /.col -->
                    </div>
                        <div class="clearfix"></div>
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