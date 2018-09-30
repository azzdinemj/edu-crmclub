<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>预约学生<span>...</span></h2>
    </div>
    <div class="panel panel-default">

        <div class="panel-body pd0">
            <div class="table-responsive">
                <!--table-->
                <table id="tables" class="table  table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>名称</th>
                        <th>手机号</th>
                        <th>内容</th>
                        <th>性别</th>
                        <th>备注</th>
                        <th>状态</th>
                        <th>操作</th>

                    </tr>
                    </thead>
                    <tbody>

                    <#if member.list??>
                        <#list member.list as v>
                        <tr>
                            <td>${(v.pkMember)!}</td>
                            <td>${(v.caption)!}</td>
                            <td>${(v.phone)!}</td>
                            <td>${(v.contnet)!}</td>
                            <td><#if v.sex?? && v.sex==0>女<#else >男</#if></td>
                            <td>${(v.remarks)!}</td>
                            <#if v.status?? && v.status==0><td class="yellow">旧学员</td><#else ><td class="red">新学员</td></#if>
                            <td>
                                <a onclick="del(${v.pkMember})" class="btn btn-danger btn-sm">忽略</a>
                                <#if v.status?? && v.status==1>
                                <a onclick="insert(${v.pkMember})" class="btn btn-danger btn-sm">转入</a>
                                </#if>

                            </td>
                        </tr>
                        </#list>
                    </#if>

                    </tbody>
                </table>
                <!--table-->

            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

    <!--页码-->
    <ul class="pagination"  style="margin-left: 9%" id="page">
        <li><a href="/zhyou/member/query?pageNo=${member.prePage}">&laquo;</a></li>
    <#list 1..member.pages as v>
        <li ><a href="/zhyou/member/query?pageNo=${v}">${v}</a></li>
    </#list>
        <li ><a href="/zhyou/member/query?pageNo=${member.nextPage}">&raquo;</a></li>
    </ul>
    <!--页码-->

</div><!-- contentpanel -->




<#include "../../commons/footer.ftl"/>


<script type="text/javascript">
    function del(id) {

        var flag = confirm("确认忽略吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/zhyou/member/delete",
                data: {"pkMember": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(data.message);
//                        window.location.reload();
                    }
                },
                error: function () {

                }
            });
        }


    }

    function insert(id) {

        var flag = confirm("确认转入吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/zhyou/member/audit",
                data: {"pkMember": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(data.message);
//                        window.location.reload();
                    }
                },
                error: function () {

                }
            });
        }


    }
</script>

