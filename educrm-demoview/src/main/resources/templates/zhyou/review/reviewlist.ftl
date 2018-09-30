<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>审核中心<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <button class="btn btn-sm btn-newblue updateReview" data-id="0">审核</button>
                <button class="btn btn-sm btn-danger updateReview" data-id="1">驳回</button>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <!--table-->
                <table id="table" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>审核内容</th>
                        <th>类型</th>
                        <th>状态</th>
                        <th>创建人</th>
                        <th>创建时间</th>
                        <th>修改人</th>
                        <th>修改状态</th>
                    </tr>
                    </thead>
                    <tbody>

                    <#if review.list??>
                        <#list review.list as v>
                        <tr dataid="${(v.pkReview)!}" datastatus="${(v.status)!}">
                            <td>
                                <#if v.type??&&v.type==0 >
                                    <a class="yellow" href="/course/courseList/edit?pkCourse=${(v.pkData)!}">
                                    ${(v.pkReview)!}
                                    </a>
                                </#if>
                                <#if v.type??&&v.type==1 >
                                    <a class="yellow" href="/question/questionlist/edit?pkQuestions=${(v.pkData)!}">
                                    ${(v.pkReview)!}
                                    </a>
                                </#if>
                            </td>
                            <td>${(v.cotent)!}</td>
                            <td><#if v.type?? && v.type==0>课程<#else >题库</#if></td>
                            <td><#if v.status?? && v.status==0>未审核<#else >
                                <#if v.status==1>二次审核<#else >
                                    <#if v.status==2>审核成功<#else >
                                        审核失败
                                </#if></#if></#if></td>
                            <td align="left">${(v.creatorEntity.caption)!}</td>
                            <td align="right">${v.creationDate ? string("yyyy-MM-dd HH:mm:ss")!}</td>
                            <td align="left">${(v.modifierEntity.caption)!}</td>
                            <td align="right">${v.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}</td>
                        </tr>
                        </#list>
                    </#if>

                    </tbody>
                </table>
                <!--table-->

            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->


</div><!-- contentpanel -->




<#include "../../commons/footer.ftl"/>

<script>
    $(".updateReview").on("click",function(){
        var id = $(".table-color-").attr("dataid");
        if (id == null) {
            Notify.danger("清选择一行数据！！！");
            return;
        }

        var status = $(this).data("id");
        var reviewStatus = $(".table-color-").attr("datastatus");
        if(reviewStatus == 2 || reviewStatus ==3){
            Notify.danger("已审核，不可重复审核");
            return;
        }
        var flag = confirm("审核之后就不能修改了，确认审核吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/zhyou/review/updateReview",
                data: {"pkReview": id,"status":status},
                dataType: "json",
                success: function (data) {
                    console.log(data);
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
    })
</script>
