<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                添加上课时间
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" method="post" action="/classinfo/classTime/save"
                  data-target="/classinfo/classTime/query">
                <input type="hidden" name="time.pkDomain" value="${(classTime.pkDomain)!}">
                <input type="hidden" name="time.pkClassTime" value="${(classTime.pkClassTime)!}">
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">上课名称</label>
                    <div class="col-xs-10 col-md-10">
                        <input name="time.caption" class="form-control" value="${(classTime.caption)!}" data-bv-notempty data-bv-notempty-message="请录入名称" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">开始时间</label>
                    <div class="col-xs-4 col-md-4">
                        <input name="time.startTime" value="${(classTime.startTime)!}" class="form-control js-datepickertime1" type="text">
                    </div><!-- /.col -->
                    <label class="control-label  col-xs-2 col-md-2">结束时间</label>
                    <div class="col-xs-4 col-md-4">
                        <input name="time.endTime" value="${(classTime.endTime)!}" class="form-control js-datepickertime1" type="text">
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">作息类型</label>
                    <div class="col-xs-4 col-md-4">
                        <select name="time.type">
                        <#if division ??>
                            <#list division as division>
                                <option value="${(division.pkSysDictValues)!}" <#if division.pkSysDictValues?? && classTime.type?? && classTime.type==division.pkSysDictValues>selected="selected"</#if> >${(division.caption)!}</option>
                            </#list>
                        </#if>
                        </select>
                    </div><!-- /.col -->
                    <label class="control-label col-xs-2 col-md-2">课时排序</label>
                    <div class="col-xs-4 col-md-4">
                        <input name="time.sort" value="${(classTime.sort)!}" class="form-control" type="text">
                    </div><!-- /.col -->
                </div>
                <#--<div class="form-group">-->
                    <#--<label class="control-label col-xs-2 col-md-2">备注</label>-->
                    <#--<div class="col-xs-10 col-md-10">-->
                        <#--<textarea class="form-control" name="time.memo" rows="4" va>${(classTime.memo)!}</textarea>-->
                    <#--</div><!-- /.col &ndash;&gt;-->
                <#--</div>-->
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">创建人</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(classTime.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-xs-2 col-md-2">创建时间</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${classTime.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">修改人</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(classTime.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-xs-2 col-md-2">修改时间</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${classTime.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-newblue btn-sm">确定</button>
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

    $('.js-datepickertime1').datetimepicker({//时间
        format: 'hh:ii',
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        minuteStep:1,
        startView: 1,
        minView: 0,
        forceParse: 0});
</script>