<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                add
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin look form-ajax"  method="post" action="/student/studentWorksPortfolio/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">school</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="${(domain.caption)!}" readonly title="" type="text">
                            <input class="form-control" name="wpor.pkDomain" value="${(domain.pkDomain)!}" title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">name</label>
                        <div class="col-lg-9">
                            <input class="form-control"  value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="wpor.pkStudent" value="${(student.pkStudent)!}" readonly title="" type="hidden">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">AwardTime</label>
                        <div class="col-lg-9">
                            <input required="" name="wpor.dateTime" class="form-control js-datepicker"  title="" type="text">
                        </div><!-- /.col -->
                    </div>


                </div>
                <div class="col-sm-6">

                    <div class="form-group">
                        <label class="control-label col-lg-3">work Type</label>
                        <div class="col-lg-9">
                            <select  name="wpor.typeWork">
                            <#if sysDictValue ??>
                                <#list sysDictValue as sys>
                                    <option value="${sys.pkSysDictValues}">${sys.caption}</option>
                                </#list>

                            </#if>
                            </select>
                           <#-- <input required="" name="wpor.activityType" class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the type of work" title="" type="text">-->
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">name</label>
                        <div class="col-lg-9">
                            <input name="wpor.activityName" class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the name of the work" title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">grade</label>
                        <div class="col-lg-9">
                            <select  name="wpor.grade">
                            <#if grade ??>
                                <#list grade as g>
                                    <option value="${g.pkSysDictValues}">${g.caption}</option>
                                </#list>

                            </#if>
                            </select>
                            <#--<input name="wpor.position" class="form-control"  title="" type="text">-->
                        </div><!-- /.col -->
                    </div>




                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2">Remarks</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="wpor.notes" rows="4" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Founder</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(portfolio.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Creation time</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${portfolio.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Modifier</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(portfolio.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Modified</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${portfolio.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
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