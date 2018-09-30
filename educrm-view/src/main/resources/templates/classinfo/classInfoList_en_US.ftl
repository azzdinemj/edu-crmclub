
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Class management <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" href="/classinfo/classinfo/create" >New class</a>
                        <a class="btn btn-sm btn-newblue getEmployeeList"  data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/getEmployeeList">Class authority</a>
                        <a class="btn btn-sm btn-newblue addSchedule" >Class schedule</a>
                        <a class="btn btn-sm btn-newblue" id="entryscores" >Achievement input</a>
                        <a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal"> delete </a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <div class="panel-btns">
                            <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                        </div><!-- panel-btns -->
                        <!--High search-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">Date, :</label>
                                <i class="fa fa-calendar" ></i>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">Class name :</label>
                                <input type="text" title="" id="caption"  name="reservation"  class="form-control form-input-lg" value="" />
                            </div>

                            <div class="form-group">
                                   <span class="btn btn-newblue btn-sm search" onclick="query()"><i class="fa fa-search"> </i>search</span>
                               </div>
                        </form>
                        <!--Hide search-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">
                                <form class="form-inline search white">
                                    <div class="form-group">
                                        <label>grade</label>
                                        <div class="btn-group">
                                            <select class="form-control strw" id="grade">
                                                <option value="">Please choose</option>
                                            <#if grade??>
                                                <#list grade as g>
                                                    <option value="${(g.pkSysDictValues)!}">${(g.caption)!}</option>
                                                </#list>
                                            </#if>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">project :</label>
                                        <div class="btn-group">
                                            <select class="form-control strw" id="project">
                                                <option value="">Please choose</option>
                                            <#if project??>
                                                <#list project as p>
                                                    <option value="${(p.pkSysDictValues)!}">${(p.caption)!}</option>
                                                </#list>
                                            </#if>
                                            </select>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered lg-table" cellspacing="0">
                            <thead>
                            <#--<tr>
                                <th>Class number</th>
                                <th>Class name</th>
                                <th>Opening date</th>
                                <th>Academic Director</th>
                                <th>Headmaster</th>
                                <th>second teacher</th>
                                <th>start time</th>
                                <th>End time</th>
                                <th>Classroom</th>
                                <th>Total Hours</th>
                                <th>project</th>
                                <th>grade</th>
                                <th >state</th>

                            </tr>-->
                            </thead>
                            <tbody>
                           <#-- <#list list as cla>
                            <tr dataid = ${(cla.pkClassinfo)!}>
                                <td>${(cla.code)!}</td>
                                <td>
                                    <a class="yellow" href="/classinfo/classinfo/edit?pkClassinfo=${(cla.pkClassinfo)!}">
                                    ${(cla.caption)!}
                                    </a>
                                </td>
                                <td>${(cla.dateTime)!}</td>
                                <td>${(cla.director)!}</td>
                                <td>${(cla.headTeacher)!}</td>
                                <td>${(cla.secondTeacher)!}</td>
                                <td>${(cla.startDateTime)!}</td>
                                <td>${(cla.endDateTime)!}</td>
                                <td>${(cla.classRoom)!}</td>
                                <td>${(cla.courseTime)!}</td>
                                <td>${(cla.program)!}</td>
                                <td>${(cla.grade)!}</td>
                                <td>
                                    <select >
                                        <option <#if cla.isaudit?? && cla.isaudit==1>Audited<#else >Unaudited</#if>></option>
                                    </select>

                                </td>
                            </tr>
                            </#list>-->

                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">

    var columns = [
        {"sTitle":"Class number","data":"code"},
        {"sTitle":"Class name","data" : "caption","render": renderClassinfoEdit} ,
        {"sTitle":"Opening date","data" : "date","render": renderDateYMD},
        {"sTitle":"Academic Director","data" : "map.directorEntity.caption"},
        {"sTitle":"Headmaster","data" : "map.headTeacherEntity.caption"},
        {"sTitle":"second teacher","data" : "map.secondTeacherEntity.caption"},
        {"sTitle":"start time","data" : "startDate","render": renderDateYMD},
        {"sTitle":"End time","data" : "endDate","render": renderDateYMD},
        {"sTitle":"Classroom","data" : "map.classroomEntity.caption"},
        {"sTitle":"Total Hours","data" : "courseTime"},
        {"sTitle":"project","data" : "map.sysdicEntity.caption"},
        {"sTitle":"grade","data" : "map.gradeEntity.caption"},
        {"sTitle":"Whether there is a timetable","data" : "map.classinfoScheduleStatus.code","render": renderScheduleStatus},
        {"sTitle":"Founder","data" : "map.creatorEntity.caption"},
        {"sTitle":"Creation time","data" : "creationDate","render": renderDate},
        {"sTitle":"Modifier","data" : "map.modifierEntity.caption"},
        {"sTitle":"Modified","data" : "lasteditDate","render": renderDate}
    ];
//    var data  = [];
//    init(columns,"/classinfo/classinfo/queryByPaging",data)

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dateTime": $("#datas").val(),"caption":$("#caption").val(),"grade":$("#grade").val(),"program":$("#project").val()};
        init(GetTableColumn("classinfo"),"/classinfo/classinfo/queryByPaging",data,"pagingTable");
    }

    function del() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassinfo;

        if (id == null) {
            Notify.danger("Please select the data to delete first");
            return;
        }

        var flag = confirm("Do you confirm the deletion？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/classinfo/classinfo/delete",
                data: {"pkClassinfo": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(data.message)
                    }
                },
                error: function () {

                }
            });
        }

    }

    $(".addSchedule").on("click",function(){
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassinfo;
        var className = getTableSelectRow("pagingTable",index).caption;
        if (id == null) {
            Notify.danger("Please choose the class first");
            return false;
        }
        var status = getTableSelectRow("pagingTable",index).map.classinfoScheduleStatus.code;
        if (status == "1") {
            Notify.danger("The class has already existed，Non repeatable addition");
            return false;
        }
        window.location.href="/classinfo/classinfo/addSchedule?pkClassinfo="+id+"&caption="+className;
        return true;
    });
    $("#entryscores").on("click",function () {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassinfo;
        if (id == null) {
            Notify.danger("Please choose the class first");
            return false;
        }
//        $("#entryscores").attr("data-url","/classinfo/classinfo/entryscores?pkClassinfo="+id);
        window.location.href="/classinfo/classinfo/entryscores?pkClassinfo="+id;
        return true;
    })

</script>
