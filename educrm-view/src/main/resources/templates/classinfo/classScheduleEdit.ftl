<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                班级排课修改
            </h4>
        </div>
        <div class="modal-body">
            <form id="formid" class="look form-ajax form-horizontal" method="post" action="/jiedian/schedule/checkschedule"
                  data-target="">
                <input type="hidden" name="pkSchedule" value="${(schedule.pkSchedule)!}">
                <input type="hidden" name="pkStudent" id="pkClassinfo" value="${(schedule.pkStudent)!}">
                <input type="hidden" name="startTimes" value="${(schedule.startTime? string("yyyy-MM-dd HH:mm"))!}">
                <input type="hidden" name="endTimes" value="${(schedule.endTime? string("yyyy-MM-dd HH:mm"))!}">
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">任课老师</label>
                    <div class="col-xs-10 col-md-10">
                        <select class="classTeacherChange" name="pkEmployee">
                            <#list classTeacherList as ct>
                                <option value="${(ct.pkEmployee)!}" <#if ct.pkEmployee?? && schedule.pkEmployee?? && ct.pkEmployee == schedule.pkEmployee>selected="selected"</#if>>${(ct.map.employeeEntity.caption)!}</option>
                            </#list>
                        </select>
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">所教课程</label>
                    <div class="col-xs-4 col-md-4">
                        <select name="pkProduct" id="teacherCourseList">
                            <#list courseTeacherList as ctl>
                                <option value="${(ctl.pkSysDictValues)!}" <#if ctl.pkSysDictValues?? && schedule.pkProduct?? && ctl.pkSysDictValues == schedule.pkProduct>selected="selected"</#if>>${(ctl.map.courseEntity.caption)!}</option>
                            </#list>
                        </select>
                    </div><!-- /.col -->
                    <label class="control-label  col-xs-2 col-md-2">上课教室</label>
                    <div class="col-xs-4 col-md-4">
                        <select name="pkClassRoom">
                        <#list classinfoRoomList as cr>
                            <option value="${(cr.pkClassinfoRoom)!}" <#if cr.pkClassinfoRoom?? && schedule.pkClassRoom?? && cr.pkClassinfoRoom == schedule.pkClassRoom>selected="selected"</#if>>${(cr.map.classroomEntity.caption)!}</option>
                        </#list>
                        </select>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-newblue btn-sm" id="saveBtn">确定</button>
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
            if (data.code==0 || data.code==1){
                if (confirm(data.message)){
                    var formData1 = $("#formid").serialize();
                    $.ajax({
                        url: "/jiedian/schedule/save",
                        type: "POST",
                        data: formData1,
                        dataType: "json",
                        success: function (da) {
                            if(da.code==0) {
                                Notify.success(da.message);
                                setTimeout("window.location.href='/classinfo/classinfoschedule/edit?pkClassinfo='"+ $("#pkClassinfo").val(), 3000);
                            }else {
                                Notify.danger(da.message);
                            }
                        }


                    });
                }
            }
            if(data.code==2){
                $('#saveBtn').attr("disabled",false);
                Notify.danger(data.message);
            }

//            if (data.code == 0) {
//                Notify.success(data.message);
//                if ($form.data('target')) {
//                    setTimeout("window.location.href='" + $form.data('target') + "'", 3000);
//                }
//            } else {
//                Notify.danger(data.message);
//            }
        }, 'json');

    });

    $(".classTeacherChange").on("change",function(){
        var id = $(this).val();
        $.ajax({
            url:"/classinfo/courseTeacher/getTeacherCourse",
            data:{"pkEmployee":id},
            type:"POST",
            sync:false,
            dataType:"json",
            traditional: true,
            success:function (data) {
                $("#teacherCourseList").empty();
                if(data.code ==0){
                    var html = "";
//                        html += "<option></option>";
                    for (var i = 0;i < data.data.length;i++){
                        if(data.data[i].map.courseEntity != null) {
                            html += "<option value='" + data.data[i].pkSysDictValues + "'>" + data.data[i].map.courseEntity.caption + "</option>";
                        }
                    }
                    $("#teacherCourseList").append(html);
                }
            }
        })
    });
</script>