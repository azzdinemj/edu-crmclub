


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title"  id="myModalLabel">
                Educational experience
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin form-ajax" method="post" action="/student/studentEduExperience/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">
                <input type="hidden" name="stu.pkDomain" value="${(student.pkDomain)!}">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">school name</label>
                        <div class="col-lg-9">
                            <input class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the school name" name="stu.schoolName" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">name</label>
                        <div class="col-lg-9">
                            <input class="form-control" readonly value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="stu.pkStudent" value="${(student.pkStudent)!}" title="" type="hidden">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">startTime</label>
                        <div class="col-lg-9">
                            <input class="form-control js-datepicker" name="stu.startDateTime" data-bv-notempty data-bv-notempty-message="Please enter the start time" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">School address</label>
                        <div class="col-lg-9">
                            <input class="form-control" name="stu.schoolAddress" data-bv-notempty data-bv-notempty-message="Please enter the school address" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">End time</label>
                        <div class="col-lg-9">
                            <input required="" class="form-control js-datepicker" name="stu.endDateTime" data-bv-notempty data-bv-notempty-message="Please enter the end time" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">achievement</label>
                        <div class="col-lg-9">
                            <input required="" class="form-control" name="stu.academicRecord" title="" type="number">
                        </div><!-- /.col -->
                    </div>


                </div>




                    <div class="form-group row">
                        <label class="control-label col-lg-2">Remarks</label>
                        <div class="col-lg-10">
                            <textarea class="form-control" name="stu.notes" rows="4" va>${(experience.notes)!}</textarea>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-2">Founder</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(experience.map.creatorEntity.caption)!}" type="text"/>
                        </div><!-- /.col -->
                        <label class="control-label col-lg-2">Creation time</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(experience.creationDate? string("yyyy-MM-dd HH:mm:ss"))!}"   type="text"/>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-2">Modifier</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(experience.map.modifierEntity.caption)!}" type="text"/>
                        </div><!-- /.col -->
                        <label class="control-label col-lg-2">Modified</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(experience.lasteditDate? string("yyyy-MM-dd HH:mm:ss"))!}"  type="text"/>
                        </div><!-- /.col -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-success btn-sm">Determine</button>
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