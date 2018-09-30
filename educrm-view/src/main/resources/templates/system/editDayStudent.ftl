
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               走读生配置信息
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-horizontal no-margin form-ajax" method="post" action="/system/dayStudent/save" data-target="/system/dayStudent/query">
                <input type="hidden" name="dayStudentId" value="${(dayStudent.dayStudentId)!}" />
                <#--<input type="hidden" name="dep.pkDomain" value="${(dayStudent.pkDomain)!}">-->

                <div class="row">
                    <div class="col-md-6 col-xs-12">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">学生姓名</label>
                            <div class="col-md-9 col-xs-9">
                                <input readonly  class="form-control"  value="${(dayStudent.map.studentEntity.caption)!}" type="text">
                                <input readonly name="pkStudent" class="form-control"  value="${(dayStudent.pkStudent)!}" type="hidden">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">时间</label>
                            <div class="col-md-9 col-xs-9">
                                <input class="form-control js-datepickertime" name="deliveryDateTime" value="${(dayStudent.deliveryDate?string("hh:mm"))!}" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group row">
                            <label class="control-label col-md-3 col-xs-3">班级名称</label>
                            <div class="col-md-9 col-xs-9">
                                <input readonly  class="form-control"  value="${(dayStudent.map.classInfoEntity.caption)!}" type="text">
                                <input readonly name="pkClassinfo" class="form-control"  value="${(dayStudent.pkClassinfo)!}" type="hidden">
                            </div><!-- /.col -->
                        </div>

                    </div>

                    <div class="col-md-6 col-xs-12">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">是否有效</label>
                            <div class="col-md-9 col-xs-9 pt7">
                                    <span class="rdio rdio-warning">
                                        <input name="isvalid" type="radio" id="radio3" value="1" <#if dayStudent.isvalid?? && dayStudent.isvalid ==1>checked="checked"</#if>>
                                        <label for="radio3">是&emsp;</label>
                                    </span>
                                <span class="rdio rdio-warning">
                                        <input name="isvalid" type="radio" id="radio4" value="0" <#if dayStudent.isvalid?? && dayStudent.isvalid ==0>checked="checked"</#if>>
                                        <label for="radio4">否&emsp;</label>
                                    </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">类型</label>
                            <div class="col-md-9 col-xs-9 pt7">
                                    <span class="rdio rdio-warning">
                                        <input name="type" type="radio" id="radio1" value="0" <#if dayStudent.type?? && dayStudent.type ==0>checked="checked"</#if>>
                                        <label for="radio1">上午</label>
                                    </span>
                                <span class="rdio rdio-warning">
                                        <input name="type" type="radio" id="radio2" value="1" <#if dayStudent.type?? && dayStudent.type ==1>checked="checked"</#if>>
                                        <label for="radio2">下午</label>
                                    </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">是否用餐</label>
                            <div class="col-md-9 col-xs-9 pt7">
                                <span class="rdio rdio-warning">
                                    <input name="eatType" type="radio" id="radio6" value="1" <#if dayStudent.eatType?? && dayStudent.eatType ==1>checked="checked"</#if>>
                                    <label for="radio6">是</label>
                                </span>
                                <span class="rdio rdio-warning">
                                    <input name="eatType" type="radio" id="radio5" value="0" <#if dayStudent.eatType?? && dayStudent.eatType ==0>checked="checked"</#if>>
                                    <label for="radio5">否</label>
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


    $('.js-datepickertime').datetimepicker({//时间
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
