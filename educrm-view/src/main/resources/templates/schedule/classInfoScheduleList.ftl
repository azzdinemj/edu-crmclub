
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>选修课班级管理 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                            <a class="btn btn-sm btn-newblue addSchedule" >班级排课</a>
                            <a class="btn btn-sm btn-newblue" href="/classinfo/classinfo/create" >新建班级</a>
                            <#--<a class="btn btn-sm btn-newblue getEmployeeList"  data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/getEmployeeList">班级权限</a>-->
                            <#--<a class="btn btn-sm btn-newblue" id="entryscores" >成绩输入</a>-->
                            <#--<a class="btn btn-sm btn-newblue" id="gotoschool" >升学</a>-->
                            <#--<a class="btn btn-sm btn-newblue" onclick="classOn()" >班级升班</a>-->
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


    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dateTime": $("#datas").val(),"caption":$("#caption").val(),"grade":$("#grade").val(),"type":"3"};
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
//        var status = getTableSelectRow("pagingTable",index).map.classinfoScheduleStatus.code;
//        if (status == "1") {
//            Notify.danger("班级已存在课表，不可重复添加");
//            return false;
//        }
        window.location.href="/classinfo/classinfoschedule/addSchedule?pkClassinfo="+id+"&caption="+className;
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
    });

    $("#gotoschool").on("click",function () {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassinfo;
        if (id == null) {
            Notify.danger("请先选择班级");
            return false;
        }
        window.location.href="/classinfo/classinfo/goToSchool?pkClassinfo="+id;
    });

    function classOn() {
        if (confirm("确定要升所有班级吗？？？")){
            $.ajax({
                type: "GET",
                url: "/classinfo/classinfo/classOn",
                data: {},
                dataType: "json",
                success: function (da) {
                    if (da.code == 0) {
                        Notify.success(da.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(da.message)
                    }
                },
                error: function () {

                }
            });
        }
    }

</script>
