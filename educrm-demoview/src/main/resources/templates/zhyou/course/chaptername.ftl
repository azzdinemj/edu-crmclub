<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>

             <#if isNot?? && isNot==0>
                 <h4 class="modal-title" id="myModalLabel">
                     修改章节
                 </h4>
             </#if>
             <#if isNot?? && isNot==1>
                 <h4 class="modal-title" id="myModalLabel">
                     添加章节
                 </h4>
             </#if>

        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" id="formSumbit" method="POST" action="/course/courseList/editOrAddChapter" data-target="/course/courseList/courseware?pkCourse=${(chaptername.pkCourse)!}">
<#--
                <form class="look form-ajax" method="POST" id="formId" action="/course/courselist/save" data-target="/course/courselist/query" >
-->
                <div class="panel panel-default">
                    <div class="form-group row">
                        <label class="control-label col-xs-3 col-md-2">章节标题</label>
                        <div class="col-xs-9 col-md-10">
                            <input name="sheetType" value="1" type="hidden">
                            <input name="pkChapter" value="${(chaptername.pkChapter)!}" type="hidden">
                            <input name="pkCourseLesson" value="${(chaptername.pkCourseLesson)!}" type="hidden">
                            <input name="pkCourse" value="${(chaptername.pkCourse)!}" type="hidden">
                            <input name="caption" value="${(chaptername.caption)!}" class="form-control" title="" type="text">
                        </div><!-- /.col -->
                    </div>

                </div>
                <div class="modal-footer">
                    <input type="hidden" id="menuList" name="menuList">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
                    <input class="btn btn-newblue btn-sm saveButton" id="dosubmit" value="保存" type="submit">
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->
<script>
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
                    setTimeout("window.location.href='" + $form.data('target') + "'", 1);
                }
            } else {
                Notify.danger(data.message);
            }
        }, 'json');
    });

</script>
