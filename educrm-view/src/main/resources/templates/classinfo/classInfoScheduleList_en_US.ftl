
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Class management <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue " onclick="classinfoschedule()">Class schedule</a>
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
                            </thead>
                            <tbody>


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
        {"sTitle":"Class name","data" : "caption",
            "render": function (data, type, row){
                return '<a href="/classinfo/classinfo/edit?pkClassinfo='+row.pkClassinfo+'" class="yellow" >'+data+'</a>';
            }} ,
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
        //{"sTitle":"state","data" : "map.userEntity.caption"},
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

    function classinfoschedule() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassinfo;
        if (id == null) {
            Notify.danger("Please choose the class first");
            return false;
        }
        window.location.href="/classinfo/classinfoschedule/edit?pkClassinfo="+id;
    }

</script>
