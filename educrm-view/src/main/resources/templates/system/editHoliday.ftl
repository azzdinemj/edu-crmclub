
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               修改节假日配置
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-horizontal no-margin form-ajax" method="post" action="/system/holiday/save" data-target="/system/holiday/query">
                <input type="hidden" name="id" value="${(holiday.id)!}" />
                <#--<input type="hidden" name="dep.pkDomain" value="${(holiday.pkDomain)!}">-->

                <div class="row">
                    <div class="col-md-6 col-xs-12">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">节假日名称</label>
                            <div class="col-md-9 col-xs-9">
                                <select name="holidayId" >
                                    <option value="">请选择</option>
                                    <#if holidayList ??>
                                        <#list holidayList as h>
                                            <option value="${(h.pkSysDictValues)}" <#if holiday?? &&holiday.holidayId??&& h.pkSysDictValues ?? &&holiday.holidayId==h.pkSysDictValues>selected</#if>>${(h.caption)}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">时间</label>
                            <div class="col-md-9 col-xs-9">
                                <input class="form-control js-datepicker" name="holidayDayTime" value="${(holiday.holidayDay?string("yyyy-MM-dd"))}" type="text">
                            </div><!-- /.col -->
                        </div>



                    </div>

                    <div class="col-md-6 col-xs-12">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">类型</label>
                            <div class="col-md-9 col-xs-9 pt7">
                                    <span class="rdio rdio-warning">
                                        <input name="type" type="radio" id="radio1" value="0" <#if holiday.type?? && holiday.type==0>checked</#if>>
                                        <label for="radio1">上班</label>
                                    </span>
                                <span class="rdio rdio-warning">
                                        <input name="type" type="radio" id="radio2" value="1" <#if holiday.type?? && holiday.type==1>checked</#if>>
                                        <label for="radio2">放假</label>
                                    </span>
                            </div>
                        </div>

                    </div>
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


    $(document).ready(function() {
        $('.demo i').click(function () {
            $(this).parent().find('input').focus();

        });

        $('#datas').daterangepicker(null, function(start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
        $('#data').daterangepicker({
            singleDatePicker: true,
            startDate: moment().subtract(6, 'days')
        });

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
