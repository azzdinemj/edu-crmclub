<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>员工资料 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/teacher/teacherlist/edit">新建员工</a>
                <a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">删除</a>
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
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">教师名称 :</label>
                        <input type="text"  id="caption" name="reservation" class="form-control form-input-lg"
                               placeholder="教师名称" />
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>

                </form>
                <!--隐藏搜索-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">

                    </div>

                </div>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--<th>教师编号</th>-->
                        <#--<th>教师名称</th>-->
                        <#--<th>性别</th>-->
                        <#--<th>年龄</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
<#--<#if employeelist??>-->
<#--<#if employeelist.list??>-->
                    <#--<#list employeelist.list as v>-->
                    <#--<tr dataid="${(v.pkEmployee)!}">-->
                        <#--<td>-->
                            <#--${(v.pkEmployee)!}-->
                        <#--</td>-->
                        <#--<td>-->
                            <#--<a class="yellow" href="/teacher/teacherlist/edit?pkEmployee=${(v.pkEmployee)!}">-->
                                <#--${(v.caption)!}-->
                            <#--</a>-->
                        <#--</td>-->
                        <#--<td>-->
                          <#--<#if v??&&v.sex?? &&v.sex==0>女<#else>男</#if>-->
                        <#--</td>-->
                        <#--<td>${(v.code)!}</td>-->

                    <#--</tr>-->
                    <#--</#list>-->
<#--</#if>-->
<#--</#if>-->
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

        <!--分页页码-->
    <#--<#if employeelist??>-->
        <#--<span >第 ${(employeelist.pageNum)!} 页 ( 总共 ${(employeelist.pages)!} 页,总共 ${(employeelist.total)!} 条 )</span>-->

        <#--<ul class="pagination"    id="page">-->
            <#--<li><a href="/teacher/teacherlist/query?pageNo=${employeelist.prePage}">&laquo;</a></li>-->
            <#--<#assign tp=employeelist.pages/>-->
            <#--<#assign p=employeelist.pageNum/>-->
            <#--<#assign sp=p-3/>-->
            <#--<#assign ep=p+3/>-->
            <#--<#assign eoff=ep-tp/>-->
            <#--<#if (eoff>0)>-->
                <#--<#assign sp = sp - eoff/>-->
            <#--</#if>-->
            <#--<#if (sp<=0)>-->
                <#--<#assign ep = ep - sp+1/>-->
            <#--</#if>-->
            <#--<#list sp..ep as x>-->
                <#--<#if (x>0 && x<=tp)>-->
                    <#--<li ><a href="/teacher/teacherlist/query?pageNo=${x}">${x}</a></li>-->
                <#--</#if>-->
            <#--</#list>-->
            <#--<li ><a href="/teacher/teacherlist/query?pageNo=${employeelist.nextPage}">&raquo;</a></li>-->
        <#--</ul>-->
    <#--</#if>-->

    </div><!-- panel -->





</div><!-- contentpanel -->

</div><!-- mainpanel -->



<#include "../../commons/footer.ftl"/>
<script type="text/javascript">

    function del() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkEmployee;
        if(id == null){
            Notify.danger("请选择一条数据");
            return;
        }
        var flag = confirm("确认删除吗？");
         if (flag) {
             $.ajax({
                 type: "POST",
                 url: "/system/employee/delete",
                 data: {"pkEmployee": id},
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

//    var columns = [
//        {"sTitle":"编号","data":"pkEmployee"},
//        {"sTitle":"员工名称","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/teacher/teacherlist/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
//            }},
//        {"sTitle":"年龄","data" : "code"},
//        {"sTitle":"性别","data" : "sex","render":renderSex},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate}
//    ];

    $(document).ready(function() {
        query();
    } );

    function renderCaption(data, type, row){
        return '<a href="/teacher/teacherlist/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
    }

    function query() {
        var data  = {"caption":$("input[name='reservation']").val()};
       // init(columns,"/teacher/teacherlist/queryByPaging",data,"pagingTable");
        init(GetTableColumn("teacherlist"), "/teacher/teacherlist/queryByPaging", data,"pagingTable");
    }

</script>