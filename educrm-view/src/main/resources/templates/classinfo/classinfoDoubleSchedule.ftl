
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>课程表 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <#--<a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfoRoom/create" >配置班级班级</a>-->
                        <#--<a class="btn btn-sm btn-newblue getEmployeeList"  data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/getEmployeeList">班级权限</a>-->
                        <#--<a class="btn btn-sm btn-newblue" id="entryscores" >成绩输入</a>-->
                        <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal"> 删除 </a>-->
                     <#-- <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                     <span class="caret"></span>
                     <span class="sr-only"></span>
                 </button>
                 <ul class="dropdown-menu" role="menu">
                     <li><a onclick="del()" data-toggle="modal"> 删除 </a></li>
                     <li class="divider"></li>
                 </ul>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <#--<div class="panel-btns">-->
                            <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                        <#--</div><!-- panel-btns &ndash;&gt;-->
                        <!--高显搜索-->
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">日期 :</label>-->
                                <#--<i class="fa fa-calendar" ></i>-->
                                <#--<input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">班级名称 :</label>-->
                                <#--<input type="text" title="" id="caption"  name="reservation"  class="form-control form-input-lg" value="" />-->
                            <#--</div>-->

                            <#--<div class="form-group">-->
                                   <#--<span class="btn btn-newblue btn-sm search" onclick="query()"><i class="fa fa-search"> </i>搜索</span>-->
                               <#--</div>-->
                        <#--</form>-->
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
                        <table  class="table table-striped table-bordered nosort" cellspacing="0">
                            <thead>
                            <tr>
                                <th>班级</th>
                                <th>周一</th>
                                <th>周二</th>
                                <th>周三</th>
                                <th>周四</th>
                                <th>周五</th>
                                <th>周六</th>
                                <th>周日</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if list ??>
                            <#list list as v>
                            <tr >
                                <td>${(v.caption)!}</td>
                                <#if v.map.courseEntity??>
                                <#list v.map.courseEntity as c>
                                <td class="replaceDou">${(c.caption)!}</td>

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
//        {"sTitle":"校区","data":"map.classInfoEntity.caption"},
        {"sTitle":"班级","data":"map.studentobj"},
        {"sTitle":"课程","data":"pkSchedule"},
        {"sTitle":"老师","data":"map.employeeobj"},
        {"sTitle":"教室","data":"map.classroomobj"},
        {"sTitle":"开始时间","data":"startTime","render":renderDateHM},
        {"sTitle":"结束时间","data":"endTime","render":renderDateHM}
//        {"sTitle":"课程","data" : "map.sysdicEntity.caption"
//           /* "render": function (data, type, row){
//                return '<a href="/classinfo/courseTeacher/edit?pkEmployee='+row.pkEmployee+'pkSysDictValues='+row.pkSysDictValues+'"  >'+data+'</a>';
//            }*/},
//        {"sTitle":"状态","data" : "kind","render": function (data){
//            if (data==0){ return '启用';}
//            else {return '未分配'; }
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
            alert("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
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

    $(function () {
        $.each($('.replaceDou'),function(){
            var s = $(this).text();
            var ts = "";
            if(s != null && s != ""){
                ts = s.replace(",","<br><br>");
                ts = ts.replace("0","双周");
                ts = ts.replace("1","单周");
            }
            $(this).html(ts);
        });
    });

</script>
