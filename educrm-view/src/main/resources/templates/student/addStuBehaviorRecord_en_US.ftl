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
            <form class="form-horizontal no-margin form-ajax look" method="post" action="/student/studentBehaviorRecord/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">

                <div class="col-sm-6">
                    <div class="form-group row">
                        <label class="control-label col-lg-3">Campus</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="${(domain.caption)!}" readonly title="" type="text">
                            <input class="form-control" name="bere.pkDomain" value="${(domain.pkDomain)!}" title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">name</label>
                        <div class="col-lg-9">
                            <input class="form-control"  value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="bere.pkStudent" value="${(student.pkStudent)!}" readonly title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">situation</label>
                        <div class="col-lg-9">
                            <input data-bv-notempty data-bv-notempty-message="Please enter the time situation" class="form-control" name="bere.details" title="" type="text">
                        </div><!-- /.col -->
                    </div>

                </div>
                <div class="col-sm-6">

                    <div class="form-group row">
                        <label class="control-label col-lg-3">grade</label>
                        <div class="col-lg-9">
                            <#--<input data-bv-notempty data-bv-notempty-message="Please enter grade" name="bere.grade" class="form-control" title="" type="text">-->
                            <select name="bere.grade">
                                <option value="">Please choose</option>
                            <#if grade ??>
                                <#list grade as gra>
                                    <option value="${(gra.pkSysDictValues)!}"
                                            <#if gra.pkSysDictValues ?? && behare.grade??&& gra.pkSysDictValues==behare.grade>selected="selected"</#if>> ${(gra.caption)!}</option>

                                </#list>
                            </#if>
                            </select>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">time</label>
                        <div class="col-lg-9">
                            <input data-bv-notempty data-bv-notempty-message="Please enter the time" name="bere.dateTime" class="form-control js-datepicker"  title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group row">
                        <label class="control-label col-lg-3">Punishment</label>
                        <div class="col-lg-9">
                            <input data-bv-notempty data-bv-notempty-message="Please enter the disposal content" name="bere.disciplinaryAction" class="form-control" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                </div>

                <div class="form-group row">
                    <label class="control-label col-sm-2">notes</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="bere.notes" rows="4" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Founder</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(behare.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Creation time</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${behare.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Modifier</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(behare.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Modified</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${behare.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
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