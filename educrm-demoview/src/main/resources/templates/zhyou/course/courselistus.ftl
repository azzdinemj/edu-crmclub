<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>我的课程列表<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/course/courseList/edit">新建课程</a>
            <#-- <a class="btn btn-sm btn-newblue" onclick="del()">删除</a>-->

            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <form class="form-inline search white">
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">日期 :</label>-->
                        <#--<input type="text" title="" id="timeid" name="datetime" class="form-control js-datepicker"/>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="control-label">课程名称 :</label>
                        <input type="text" title="" id="captionCourse" name="caption" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">课程状态 :</label>
                        <select id="isissueCourse" class="form-control strw">
                            <option value="">全部</option>
                            <option value="0">草稿</option>
                            <option value="1">发布</option>
                            <option value="2">已审核</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <a onclick="query()" id="queryCourse" class="btn btn-newblue btn-sm search">搜索</a>
                    </div>
                </form>

            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <!--table-->
                <table id="tables" class="table  table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>课程包名称</th>
                        <th>课程简介</th>
                        <th>老师</th>
                        <th>有效期</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

<#if course??>
                    <#if course.list??>
                        <#list course.list as v>
                        <tr>
                            <td>${(v.pkCourse)!}</td>
                            <td><a class="yellow"
                                   href="/course/courseListUs/edit?pkCourse=${(v.pkCourse)!}">${(v.caption)!}</a></td>
                            <td>${(v.instruction)!}</td>
                            <td>${(v.teacher)!}</td>
                            <td>${(v.cycle)!}</td>
                            <td>
                                <#if v??&& v.isissue??&& v.isissue==0 >草稿</#if>
                                <#if v??&& v.isissue??&& v.isissue==1 >发布</#if>
                                <#if v??&& v.isissue??&& v.isissue==2 >已审核</#if>
                            </td>
                            <td>
                                <a href="/course/courseList/courseware?pkCourse=${(v.pkCourse)!}" class="btn btn-newblue btn-sm">课件</a>
                                <a onclick="del('${(v.pkCourse)!}')" class="btn btn-danger btn-sm">删除</a>

                            </td>
                        </tr>
                        </#list>
                    </#if>
</#if>

                    </tbody>
                </table>
                <!--table-->

            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->


    <!--course为pageinfo对象-->
    <!--分页页码-->
<#if course??>
    <ul class="pagination"    id="page">
        <li><a href="/course/courseList/query?pageNo=${course.prePage}">&laquo;</a></li>
        <#assign tp=course.pages/>
        <#assign p=course.pageNum/>
        <#assign sp=p-3/>
        <#assign ep=p+3/>
        <#assign eoff=ep-tp/>
        <#if (eoff>0)>
            <#assign sp = sp - eoff/>
        </#if>
        <#if (sp<=0)>
            <#assign ep = ep - sp+1/>
        </#if>
        <#list sp..ep as x>
            <#if (x>0 && x<=tp)>
                <li ><a href="/course/courseList/query?pageNo=${x}">${x}</a></li>
            </#if>
        </#list>
        <li ><a href="/course/courseList/query?pageNo=${course.nextPage}">&raquo;</a></li>
    </ul>
</#if>


</div><!-- contentpanel -->




<#include "../../commons/footer.ftl"/>


<script type="text/javascript">
    function del(id) {
        //   var id = $(".table-color-").attr("dataid");

        if (id == null) {
            alert("请先选择要删除的数据");
            return;
        }
        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/course/courseList/delete",
                data: {"pkCourse": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        alert(data.message);
                        window.location.reload();
                    }
                },
                error: function () {

                }
            });
        }


    }

    function query() {
        var caption=$("#captionCourse").val();
        var isissue=$("#isissueCourse option:selected").val();
        $("#queryCourse").attr("href","/course/courseListUs/query?isissue="+isissue+"&&caption="+caption)[0].click();
    }

</script>

