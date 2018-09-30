
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>schedule <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <#--<a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfoRoom/create" >Configuring class classes</a>-->
                        <#--<a class="btn btn-sm btn-newblue getEmployeeList"  data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/getEmployeeList">Class authority</a>-->
                        <#--<a class="btn btn-sm btn-newblue" id="entryscores" >Achievement input</a>-->
                        <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal"> delete </a>-->
                     <#-- <button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                     <span class="caret"></span>
                     <span class="sr-only"></span>
                 </button>
                 <ul class="dropdown-menu" role="menu">
                     <li><a onclick="del()" data-toggle="modal"> delete </a></li>
                     <li class="divider"></li>
                 </ul>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <#--<div class="panel-btns">-->
                            <#--<a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>-->
                        <#--</div><!-- panel-btns &ndash;&gt;-->
                        <!--High search-->
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">Date, :</label>-->
                                <#--<i class="fa fa-calendar" ></i>-->
                                <#--<input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">Class name :</label>-->
                                <#--<input type="text" title="" id="caption"  name="reservation"  class="form-control form-input-lg" value="" />-->
                            <#--</div>-->

                            <#--<div class="form-group">-->
                                   <#--<span class="btn btn-newblue btn-sm search" onclick="query()"><i class="fa fa-search"> </i>search</span>-->
                               <#--</div>-->
                        <#--</form>-->
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
                        <table  class="table table-striped table-bordered nosort" cellspacing="0">
                            <thead>
                            <tr>
                                <th>class</th>
                                <th>Monday</th>
                                <th>Tuesday</th>
                                <th>Wednesday</th>
                                <th>Thursday</th>
                                <th>Friday</th>
                                <th>Saturday</th>
                                <th>Sunday</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if list ??>
                            <#list list as v>
                            <tr >
                                <td>${(v.caption)!}</td>
                                <#if v.map.courseLessonChapter??>
                                <#list v.map.courseLessonChapter as c>
                                <td>${(c.map.courseEntity.caption)!}</td>
                                <#--<td>${(c.courseEntity)!}</td>-->
                                <#--<td>${(v.map.employeeobj)!}</td>-->
                                <#--<td>${(v.map.classroomobj)!}</td>-->
                                <#--<td>${(v.map.startTime?string("hh:mm"))!}</td>-->
                                <#--<td>${(v.map.endTime?string("hh:mm"))!}</td>-->
                                </#list>
                                </#if>
                            </tr>
                            </#list>
                            </#if>
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
//        {"sTitle":"campus","data":"map.classInfoEntity.caption"},
        {"sTitle":"class","data":"map.studentobj"},
        {"sTitle":"curriculum","data":"pkSchedule"},
        {"sTitle":"Teacher","data":"map.employeeobj"},
        {"sTitle":"Classroom","data":"map.classroomobj"},
        {"sTitle":"start time","data":"startTime","render":renderDateHM},
        {"sTitle":"End time","data":"endTime","render":renderDateHM}
//        {"sTitle":"curriculum","data" : "map.sysdicEntity.caption"
//           /* "render": function (data, type, row){
//                return '<a href="/classinfo/courseTeacher/edit?pkEmployee='+row.pkEmployee+'pkSysDictValues='+row.pkSysDictValues+'"  >'+data+'</a>';
//            }*/},
//        {"sTitle":"state","data" : "kind","render": function (data){
//            if (data==0){ return 'Enable';}
//            else {return 'Unassigned'; }
//        }},
    ];

//    $(document).ready(function() {
//        query();
//    } );

//    function query() {
//        var data  = { "dateTime": $("#datas").val(),"caption":$("#caption").val(),"isvalid":0};
//        init(columns,"/classinfo/classinfoschedule/queryByPaging",data);
//    }

    function del() {

        var index = $(".table-color-").index();
        var pkClassinfoRoom = getTableSelectRow(index).pkClassinfoRoom;


        if (pkClassinfoRoom == null) {
            alert("Please select the data to delete first");
            return;
        }

        var flag = confirm("Do you confirm the deletion？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/classinfo/classinfoRoom/delete",
                data: {"pkClassinfoRoom": pkClassinfoRoom},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        alert(data.message)
                    }
                },
                error: function () {

                }
            });
        }

    }



</script>
