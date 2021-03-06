
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>课程表 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <!--data-toggle="modal" data-target="#myModal" data-url=""-->
                <a class="btn btn-sm btn-success" onclick="saveAll()">保存</a>
            <#--<a class="btn btn-sm btn-danger" >删除</a>-->

                <!--     <button title="更多操作" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
                <span class="sr-only"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="" data-toggle="modal">删除</a></li>
                <li class="divider"></li>
            </ul>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body pd0">
            <div class="">
                <form id="formId" class="look form-ajax" method="POST" id="formId" >
                <#if (classTeacherList?size > 0)>
                    <table class="table" >
                        <thead>
                        <tr>
                            <th width="300"><b>${(classinfo.caption)!}</b></th>
                            <th width="250">星期一</th>
                            <th width="200">星期二</th>
                            <th width="200">星期三</th>
                            <th width="200">星期四</th>
                            <th width="200">星期五</th>
                        </tr>
                        </thead>
                    <tbody>
                        <input type="hidden" value="${(classinfo.pkClassinfo)!}" id="pkClassinfo">
                        <input type="hidden" value="${(classinfo.division)!}" id="division">
                        <#if (classTimeList?size > 0)>
                            <#assign  index = 1 >
                            <#list classTimeList as cl>

                            <tr>
                                <td>${(cl.caption)!}</td>

                                    <#list 1..5 as t>
                                    <td>
                                        <select class="classTeacherChange" onchange="selectOnchange(this,0)" id="classTeacher${(cl.pkClassTime)!}${(index)!}" name="getClassTeacher"  data-room="" data-data="${(cl.pkClassTime)!}" data-index="${(index)!}">
                                                <option></option>
                                                <#list classTeacherList as ct>
                                                    <option value="${(ct.pkEmployee)!}">${(ct.map.employeeEntity.caption)!}</option>
                                                </#list>
                                        </select>
                                        <select id="classinfoRoom${(cl.pkClassTime)!}${(index)!}" onchange="setRoom('${(cl.pkClassTime)!}',${(index)!})">
                                            <option></option>
                                            <#if (classinfoRoomList?size > 0)>
                                                <#list classinfoRoomList as cr>
                                                    <option value="${(cr.pkClassroom)!}">${(cr.map.classroomEntity.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                        <select id="teacherCourseList${(cl.pkClassTime)!}${(index)!}" onchange="setCourse('${(cl.pkClassTime)!}',${(index)!})">
                                        </select>
                                    </td>

                                    <#assign index = index+1>
                                </#list>

                            </tr>

                                <#assign  index = 1 >
                            </#list>
                        </#if>
                        </tbody>
                    </table>
                    <#else >
                        班级老师无数据，无法进行排课，请配置完之后再来。
                    </#if>

                </form>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">
//    $(".classTeacherChange").on("change",function(){
//        var id = $(this).val();
//        var classTime = $(this).data("data");
//        var index = $(this).data("index");
//        $.ajax({
//            url:"/classinfo/courseTeacher/getTeacherCourse",
//            data:{"pkEmployee":id},
//            type:"POST",
//            sync:false,
//            dataType:"json",
//            traditional: true,
//            success:function (data) {
//                $("#teacherCourseList"+classTime+index).empty();
//                if(data.code ==0){
//                    var html = "";
//                    html += "<option></option>";
//                    for (var i = 0;i < data.data.length;i++){
//                        if(data.data[i].map.courseEntity != null) {
//                            html += "<option value='" + data.data[i].pkSysDictValues + "'>" + data.data[i].map.courseEntity.caption + "</option>";
//                        }
//                    }
//                    $("#teacherCourseList"+classTime+index).append(html);
//                }
//            }
//        })
//    });

    function selectOnchange(obj,pkProduct) {
        var id = $(obj).val();
        var classTime = $(obj).data("data");
        var index = $(obj).data("index");
        $.ajax({
            url:"/classinfo/courseTeacher/getTeacherCourse",
            data:{"pkEmployee":id},
            type:"POST",
            sync:false,
            dataType:"json",
            traditional: true,
            success:function (data) {
                $("#teacherCourseList"+classTime+index).empty();
                if(data.code ==0){
                    var html = "";
                    html += "<option></option>";
                    for (var i = 0;i < data.data.length;i++){
                        if(data.data[i].map.courseEntity != null) {
                            if(pkProduct == data.data[i].pkSysDictValues){
                                html += "<option selected value='" + data.data[i].pkSysDictValues + "'>" + data.data[i].map.courseEntity.caption + "</option>";
                            }
                            else{
                                html += "<option value='" + data.data[i].pkSysDictValues + "'>" + data.data[i].map.courseEntity.caption + "</option>";
                            }
                        }
                    }
                    $("#teacherCourseList"+classTime+index).append(html);
                }
            }
        })
    }

    function setCourse(classTime,index){
        var courseId = $("#teacherCourseList"+classTime+index).val();
        $("#classTeacher"+classTime+index).attr("data-course",courseId);
    }

    function setRoom(classTime,index){
        var classinfoRoom = $("#classinfoRoom"+classTime+index).val();
        $("#classTeacher"+classTime+index).attr("data-room",classinfoRoom);
    }

    function saveAll(){
        var requestData = {};
        var list = "";
        $('select[name="getClassTeacher"] :selected').each(function () {
//        $("select[name=getClassTeacher] option[selected]").each(function () {
            var values = $(this).val();
            var scheduleList = {};
            if(values != ""){
                var courseId = $(this).parent().data("course");
                if(courseId != "" && courseId != undefined && courseId != null) {
                    var roomId = $(this).parent().data("room");
                    if(roomId != "" && roomId != undefined && roomId != null) {
                        var classTime = $(this).parent().data("data");
                        var index = $(this).parent().data("index");
                        scheduleList.pkProduct = courseId;
                        scheduleList.pkStudent = $("#pkClassinfo").val();
                        scheduleList.pkClassinfo = $("#pkClassinfo").val();
                        scheduleList.pkEmployee = values;
                        scheduleList.pkClassRoom = roomId;
                        scheduleList.pkClassTime = classTime;
                        scheduleList.notes = index;
                        scheduleList.type = 2;
//                    alert(JSON.stringify(scheduleList))
                        list = list + JSON.stringify(scheduleList) + "-----";
                    }else{
                        Notify.danger("教室没选择");
                        return false;
                    }
                }else{
                    Notify.danger("课程没选择");
                    return false;
                }
            }
        });

        requestData.scheduleList = list;
        $.ajax({
            url:"/schedule/schedule/saveAll",
            data:requestData,
            type:"POST",
            sync:false,
            dataType:"json",
            traditional: true,
            success:function (data) {
                if(data.code ==0){
                    window.location.href="/schedule/classinfoSchedule/query";
                }else if(data.code == 500){
                    Notify.danger("冲突！");
                }else{
                    Notify.danger(data.message);
                }
            }
        })
    }


    $(function () {

        var pkClassinfo = $("#pkClassinfo").val();
        $.ajax({
            url:"/classinfo/classinfoschedule/findSchedule",
            type:"GET",
            data:{"pkClassinfo":pkClassinfo},
            dataType:"json",
            success:function (data) {

                if (data.code ==0){
                    var arr = data.data;
                    if (arr != null ){
                        $.each(arr,function (i) {
                            var schedulelist = arr[i].map.courseLessonChapter;
                            $.each(schedulelist,function (j) {
                                //var pkclassroom =
//                                $("#formId tbody tr").eq(i).find("td").eq(j+1).find("select").eq(0).val();
//                                alert(schedulelist[j].pkClassRoom + " " + schedulelist[j].pkEmployee + schedulelist[j]. pkProduct);

                                if (schedulelist[j].pkEmployee != null && schedulelist[j].pkEmployee != '' && schedulelist[j].pkEmployee != undefined){
                                    $("#formId tbody tr").eq(i).find("td").eq(j+1).find("select").eq(0).val(schedulelist[j].pkEmployee);
                                    selectOnchange($("#formId tbody tr").eq(i).find("td").eq(j+1).find("select").eq(0),schedulelist[j].pkProduct);
//                                    setCourse(schedulelist[j].pkEmployee,)
                                    if (schedulelist[j].pkProduct != null && schedulelist[j].pkProduct != '' && schedulelist[j].pkProduct != undefined){
//                                    alert(schedulelist[j].pkProduct);
                                        $("#formId tbody tr").eq(i).find("td").eq(j+1).find("select").eq(0).attr("data-course",schedulelist[j].pkProduct);
                                    }
                                }
                                if (schedulelist[j].pkClassRoom != null && schedulelist[j].pkClassRoom != '' && schedulelist[j].pkClassRoom != undefined){
                                    $("#formId tbody tr").eq(i).find("td").eq(j+1).find("select").eq(1).val(schedulelist[j].pkClassRoom);
                                    $("#formId tbody tr").eq(i).find("td").eq(j+1).find("select").eq(0).attr("data-room",schedulelist[j].pkClassRoom);
                                }

                            })
                        });
                    }
                }
            }
        })
    });
</script>