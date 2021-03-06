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
            <form class="form-horizontal no-margin look " id="interformId" method="post" <#--action="/student/studentInterviewRecord/save"
                  data-target="${(URLKEY)!}?pkStudent=${(student.pkStudent)!}<#if mentalitykey ??>&mentalitykey=1</#if>"-->>
                <input class="form-control" name="intre.isType" value="${(interRec.isType)!}"  title="" type="hidden">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">Campus name</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="${(domain.caption)!}" readonly title="" type="text">
                            <input class="form-control" name="intre.pkDomain" value="${(domain.pkDomain)!}" title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">name</label>
                        <div class="col-lg-9">
                            <input class="form-control"  value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="intre.pkStudent" value="${(student.pkStudent)!}" readonly title="" type="hidden">
                        </div><!-- /.col -->
                    </div>

                </div>
                <div class="col-sm-6">

                    <div class="form-group">
                        <label class="control-label col-lg-3">Interview time</label>
                        <div class="col-lg-9">
                            <input  name="intre.dateTime" class="form-control js-datepicker" data-bv-notempty data-bv-notempty-message="Please enter the time" title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">grade</label>
                        <div class="col-lg-9">
                            <select  name="intre.grade">
                                <#if grade ??>
                                    <#list grade as g>
                                        <option value="${g.pkSysDictValues}">${g.caption}</option>
                                    </#list>

                                </#if>
                            </select>
                            <#--<input name="intre.grade"  class="form-control"  title="" type="text">-->
                        </div><!-- /.col -->
                    </div>


                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2">proposal</label>
                    <div class="col-lg-10">
                        <textarea name="intre.advices" rows="3" class="form-control"></textarea>
                    </div><!-- /.col -->
                </div>

                <div class="form-group row">
                    <label class="control-label col-lg-2">Interview content</label>
                    <div class="col-lg-10">
                        <textarea name="intre.what" data-bv-notempty data-bv-notempty-message="Please enter the interview too" rows="3" class="form-control"></textarea>
                    </div><!-- /.col -->
                </div>

                <div class="form-group row">
                    <label class="control-label col-sm-2">notes</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="intre.notes" rows="3" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Founder</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(interRec.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Creation time</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${interRec.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Modifier</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(interRec.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Modified</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${interRec.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success btn-sm" id="submitId">Determine</button>
                </div>

            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
//    $('.form-ajax').bootstrapValidator().on('success.form.bv', function(e) {
//        // Prevent form submission
//        e.preventDefault();
//
//        // Get the form instance
//        var $form = $(e.target);
//
//        // Get the BootstrapValidator instance
//        var bv = $form.data('bootstrapValidator');
//
//        // Use Ajax to submit form data
//
//        $.post($form.attr('action'), $form.serialize(), function(data) {
//
//            if(data.code==0){
//                Notify.success(data.message);
//                if($form.data('target')){
//                    setTimeout("window.location.href='"+$form.data('target')+"'",3000);
//                }
//            }else{
//                Notify.danger(data.message);
//            }
//        }, 'json');
//    });

    $("#submitId").on("click",function () {
        var formData = $("#interformId").serialize();
        $.ajax({
            type: "POST",
            url: "/student/studentInterviewRecord/save",
            data: formData,
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    window.location.reload();
                } else {
                    Notify.danger(data.message);
                    window.location.reload();
                }
            }
        });
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