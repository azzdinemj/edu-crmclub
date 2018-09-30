
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">成绩评语</h4>
        </div>
        <form class="form-ajax" method="post" action="/classinfo/teacherComment/save" data-target="/classinfo/teacherComment/query">
            <div class="modal-body">
                <#--<div class="form-group row">-->
                    <#--<label class="control-label col-xs-3 col-md-3">班级</label>-->
                    <#--<div class="col-xs-9 col-md-9">-->
                        <#--<input type="text" value="${(activityStudent.className)!}" class="form-control" disabled>-->
                    <#--</div>-->
                    <#--<input type="hidden" name="pkClassinfo" value="${(activityStudent.pkClassinfo)!}">-->
                <#--</div>-->
                <input type="hidden" name="pkTeacherComment" value="${(teacherComment.pkTeacherComment)!}">
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">学生</label>
                    <div class="col-xs-9 col-md-9">
                        <#if teacherComment?? && teacherComment.pkTeacherComment??>${(teacherComment.map.studentEntity.caption)!}<#else >${(studentName)!}</#if>
                    </div>
                    <input type="hidden" name="pkStudent" value="${(teacherComment.pkStudent)!}">
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">年份</label>
                    <div class="col-xs-9 col-md-9">
                        <#if teacherComment?? && teacherComment.pkTeacherComment??>${(teacherComment.year?string("yyyy"))!}<#else >${(schoolYear)!}</#if>
                    </div>
                    <input type="hidden" name="years" value="${(teacherComment.year?string("yyyy"))!}">
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">学期</label>
                    <div class="col-xs-9 col-md-9">
                        <#if teacherComment?? && teacherComment.pkTeacherComment??>${(teacherComment.map.semesterEntity.caption)!}<#else >${(termName)!}</#if>
                    </div>
                    <input type="hidden" name="semester" value="${(teacherComment.semester)!}">
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">老师类型</label>
                    <div class="col-xs-9 col-md-9">
                        <select id="teacherType" onchange="teacherTypeChange()" name="type" <#if teacherComment?? && teacherComment.pkTeacherComment??>readonly<#else></#if>>
                            <option value="0" <#if teacherComment?? && teacherComment.type?? && teacherComment.type == 0>selected="selected"</#if>>班主任</option>
                            <option value="1" <#if teacherComment?? && teacherComment.type?? && teacherComment.type == 1>selected="selected"</#if>>任课老师</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row" style="display: none" id="courseStatus">
                    <label class="control-label col-xs-3 col-md-3">科目</label>
                    <div class="col-xs-9 col-md-9">
                        <select name="discipline">
                        <#if course?? >
                            <option value="0">请选择</option>
                            <#list course as t>
                                <option value="${(t.pkSysDictValues)!}" <#if teacherComment.discipline?? && t.pkSysDictValues?? && teacherComment.discipline == t.pkSysDictValues>selected="selected"</#if>>${(t.caption)!}</option>
                            </#list>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">老师评语</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" value="${(teacherComment.content)!}" name="content" class="form-control">
                    </div>
                </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="submit" class="btn btn-primary">提交</button>
        </div>
        </form>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
    $(function(){
        teacherTypeChange();

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
    });

    function teacherTypeChange(){
        var type = Number($("#teacherType").val());
        if(type == 1){
            $("#courseStatus").show();
        }else if(type == 0){
            $("#courseStatus").hide();
        }
    };
</script>