<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Add classTime
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" method="post" action="/classinfo/classTime/save"
                  data-target="/classinfo/classTime/query">
                <input type="hidden" name="time.pkDomain" value="${(classTime.pkDomain)!}">
                <input type="hidden" name="time.pkClassTime" value="${(classTime.pkClassTime)!}">
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">Class name</label>
                    <div class="col-xs-10 col-md-10">
                        <input name="time.caption" class="form-control" value="${(classTime.caption)!}" data-bv-notempty data-bv-notempty-message="Please enter the name" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">start time</label>
                    <div class="col-xs-4 col-md-4">
                        <input name="time.startTime" value="${(classTime.startTime)!}" class="form-control js-datepickertime" type="text">
                    </div><!-- /.col -->
                    <label class="control-label  col-xs-2 col-md-2">End time</label>
                    <div class="col-xs-4 col-md-4">
                        <input name="time.endTime" value="${(classTime.endTime)!}" class="form-control js-datepickertime" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">Type of work and interest</label>
                    <div class="col-xs-4 col-md-4">
                        <select name="time.type">
                        <#if division ??>
                            <#list division as division>
                                <option value="${(division.pkSysDictValues)!}" <#if division.pkSysDictValues?? && classTime.type?? && classTime.type==division.pkSysDictValues>selected="selected"</#if> >${(division.caption)!}</option>
                            </#list>
                        </#if>
                        </select>
                    </div><!-- /.col -->
                    <label class="control-label col-xs-2 col-md-2">sorting</label>
                    <div class="col-xs-4 col-md-4">
                        <input name="time.sort" value="${(classTime.sort)!}" class="form-control" type="text">
                    </div><!-- /.col -->
                </div>
                <#--<div class="form-group">-->
                    <#--<label class="control-label col-xs-2 col-md-2">Remarks</label>-->
                    <#--<div class="col-xs-10 col-md-10">-->
                        <#--<textarea class="form-control" name="time.memo" rows="4" va>${(classTime.memo)!}</textarea>-->
                    <#--</div><!-- /.col &ndash;&gt;-->
                <#--</div>-->
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">Founder</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(classTime.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-xs-2 col-md-2">Creation time</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${classTime.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">Modifier</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(classTime.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-xs-2 col-md-2">Modified</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${classTime.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-newblue btn-sm">Determine</button>
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->


<script type="text/javascript">
    $('.form-ajax').bootstrapValidator().on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function (data) {

            if (data.code == 0) {
                Notify.success(data.message);
                if ($form.data('target')) {
                    setTimeout("window.location.href='" + $form.data('target') + "'", 3000);
                }
            } else {
                Notify.danger(data.message);
            }
        }, 'json');
    });
</script>