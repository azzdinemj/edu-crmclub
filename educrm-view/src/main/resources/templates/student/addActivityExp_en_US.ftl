<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Achievements in new social practice
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin look form-ajax"  method="post" action="/student/studentActivityExp/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">
                <input class="form-control" name="exp.pkDomain" value="${(domain.pkDomain)!}" title="" type="hidden">
                <div class="col-sm-6">


                    <div class="form-group">
                        <label class="control-label col-lg-3">activity type</label>
                        <div class="col-lg-9">
                            <select name="exp.activityType">
                            <#if type??>
                                <#list type as t>
                                    <option value="${(t.pkSysDictValues)!}">${(t.caption)!}</option>
                                </#list>
                            </#if>
                            </select>
                           <#-- <input name="exp.activityType" class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the activity type" title="" type="text">-->
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">Student name</label>
                        <div class="col-lg-9">
                            <input class="form-control" readonly value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="exp.pkStudent" value="${(student.pkStudent)!}" readonly title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">start time</label>
                        <div class="col-lg-9">
                            <input name="exp.startDateTime" class="form-control js-datepicker" data-bv-notempty data-bv-notempty-message="Please enter the start time" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">Activity performance</label>
                        <div class="col-lg-9">
                            <input  name="exp.score" class="form-control" data-bv-notempty data-bv-notempty-message="Please record the results" title="" type="text">
                        </div><!-- /.col -->
                    </div>


                </div>
                <div class="col-sm-6">


                    <div class="form-group">
                        <label class="control-label col-lg-3">Activity name</label>
                        <div class="col-lg-9">
                            <select name="exp.activityName">
                                <#if sysDictValue??>
                                    <#list sysDictValue as sys>
                                        <option value="${(sys.pkSysDictValues)!}">${(sys.caption)!}</option>
                                    </#list>
                                </#if>
                            </select>
                           <#-- <input required="" name="exp.activityName" class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the name of the activity" title="" type="text">-->
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">role</label>
                        <div class="col-lg-9">
                            <input name="exp.position" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">End time</label>
                        <div class="col-lg-9">
                            <input name="exp.endDateTime" class="form-control js-datepicker" data-bv-notempty data-bv-notempty-message="Please enter the end time" title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">Cost</label>
                        <div class="col-lg-9">
                            <input name="exp.cost" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>


                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2">Remarks</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="exp.notes" rows="4" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Founder</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(activity.creator)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Creation time</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${activity.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Modifier</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(activity.modifier)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Modified</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${activity.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
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