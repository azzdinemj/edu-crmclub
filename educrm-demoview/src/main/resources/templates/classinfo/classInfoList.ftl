
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>班级管理 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" href="/classinfo/classinfo/create" >新建班级</a>
                        <a class="btn btn-sm btn-newblue getEmployeeList"  data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/getEmployeeList">班级权限</a>
                        <a class="btn btn-sm btn-newblue addSchedule" >班级课表</a>
                        <a class="btn btn-sm btn-newblue" id="entryscores" >成绩输入</a>
                        <a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal"> 删除 </a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <div class="panel-btns">
                            <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                        </div><!-- panel-btns -->
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <i class="fa fa-calendar" ></i>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">班级名称 :</label>
                                <input type="text" title="" id="caption"  name="reservation"  class="form-control form-input-lg" value="" />
                            </div>

                            <div class="form-group">
                                   <span class="btn btn-newblue btn-sm search" onclick="query()"><i class="fa fa-search"> </i>搜索</span>
                               </div>
                        </form>
                        <!--隐藏搜索-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">
                                <form class="form-inline search white">
                                    <div class="form-group">
                                        <label>年级</label>
                                        <div class="btn-group">
                                            <select class="form-control strw" id="grade">
                                                <option value="">请选择</option>
                                            <#if grade??>
                                                <#list grade as g>
                                                    <option value="${(g.pkSysDictValues)!}">${(g.caption)!}</option>
                                                </#list>
                                            </#if>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">项目 :</label>
                                        <div class="btn-group">
                                            <select class="form-control strw" id="project">
                                                <option value="">请选择</option>
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
                                <th>班级编号</th>
                                <th>班级名称</th>
                                <th>开班日期</th>
                                <th>学部主任</th>
                                <th>班主任</th>
                                <th>副班主任</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>教室</th>
                                <th>总课时</th>
                                <th>项目</th>
                                <th>年级</th>
                                <th >状态</th>

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
                                        <option <#if cla.isaudit?? && cla.isaudit==1>已审核<#else >未审核</#if>></option>
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
        {"sTitle":"班级编号","data":"code"},
        {"sTitle":"班级名称","data" : "caption","render": renderClassinfoEdit} ,
        {"sTitle":"开班日期","data" : "date","render": renderDateYMD},
        {"sTitle":"学部主任","data" : "map.directorEntity.caption"},
        {"sTitle":"班主任","data" : "map.headTeacherEntity.caption"},
        {"sTitle":"副班主任","data" : "map.secondTeacherEntity.caption"},
        {"sTitle":"开始时间","data" : "startDate","render": renderDateYMD},
        {"sTitle":"结束时间","data" : "endDate","render": renderDateYMD},
        {"sTitle":"教室","data" : "map.classroomEntity.caption"},
        {"sTitle":"总课时","data" : "courseTime"},
        {"sTitle":"项目","data" : "map.sysdicEntity.caption"},
        {"sTitle":"年级","data" : "map.gradeEntity.caption"},
        {"sTitle":"是否存在课表","data" : "map.classinfoScheduleStatus.code","render": renderScheduleStatus},
        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
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
            Notify.danger("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
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
            Notify.danger("请先选择班级");
            return false;
        }
        var status = getTableSelectRow("pagingTable",index).map.classinfoScheduleStatus.code;
        if (status == "1") {
            Notify.danger("班级已存在课表，不可重复添加");
            return false;
        }
        window.location.href="/classinfo/classinfo/addSchedule?pkClassinfo="+id+"&caption="+className;
        return true;
    });
    $("#entryscores").on("click",function () {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassinfo;
        if (id == null) {
            Notify.danger("请先选择班级");
            return false;
        }
//        $("#entryscores").attr("data-url","/classinfo/classinfo/entryscores?pkClassinfo="+id);
        window.location.href="/classinfo/classinfo/entryscores?pkClassinfo="+id;
        return true;
    })

</script>
