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
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">名称 :</label>
                        <input id="caption" type="text"   name="caption" placeholder="名称" class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <button type="button" onclick="query()" class="btn btn-newblue btn-sm search">搜索</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <!--table-->
                <#--<table id="tablezhy" class="table table-striped table-bordered" cellspacing="0">-->
                <table id="pagingTable"  class="table table-striped table-bordered  " cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--<th>ID</th>-->
                        <#--<th>名称</th>-->
                        <#--<th>审核内容</th>-->
                        <#--<th>类型</th>-->
                        <#--<th>状态</th>-->
                        <#--<th>创建人</th>-->
                        <#--<th>创建时间</th>-->
                        <#--<th>修改人</th>-->
                        <#--<th>修改状态</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                      <#--<#if review??>-->
                    <#--<#if review.list??>-->
                        <#--<#list review.list as v>-->
                        <#--<tr dataid="${(v.pkReview)!}" datastatus="${(v.status)!}">-->
                            <#--<td>-->
                                <#--<#if v.type??&&v.type==0 >-->
                                    <#--<a class="yellow" href="/course/courseList/edit?pkCourse=${(v.pkData)!}">-->
                                    <#--${(v.pkReview)!}-->
                                    <#--</a>-->
                                <#--</#if>-->
                                <#--<#if v.type??&&v.type==1 >-->
                                    <#--<a class="yellow" href="/question/questionlist/edit?pkQuestions=${(v.pkData)!}">-->
                                    <#--${(v.pkReview)!}-->
                                    <#--</a>-->
                                <#--</#if>-->
                            <#--</td>-->
                            <#--<td>${(v.pkDataCaption)!}</td>-->
                            <#--<td>${(v.cotent)!}</td>-->
                            <#--<td><#if v.type?? && v.type==0>课程<#else >题库</#if></td>-->
                            <#--<td><#if v.status?? && v.status==0>未审核<#else >-->
                                <#--<#if v.status==1>二次审核<#else >-->
                                    <#--<#if v.status==2>审核成功<#else >-->
                                        <#--审核失败-->
                                <#--</#if></#if></#if></td>-->
                            <#--<td align="left">${(v.creatorEntity.caption)!}</td>-->
                            <#--<td align="right">${v.creationDate ? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                            <#--<td align="left">${(v.modifierEntity.caption)!}</td>-->
                            <#--<td align="right">${v.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                        <#--</tr>-->
                        <#--</#list>-->
                    <#--</#if>-->
<#--</#if>-->
                    </tbody>
                </table>
                <!--table-->

            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

<#--<#if review??>-->
    <#--<ul class="pagination"    id="page">-->
        <#--<li><a href="/question/questionlist/query?pageNo=${review.prePage}">&laquo;</a></li>-->
        <#--<#assign tp=review.pages/>-->
        <#--<#assign p=review.pageNum/>-->
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
                <#--<li ><a href="/question/questionlist/query?pageNo=${x}">${x}</a></li>-->
            <#--</#if>-->
        <#--</#list>-->
        <#--<li ><a href="/question/questionlist/query?pageNo=${review.nextPage}">&raquo;</a></li>-->
    <#--</ul>-->
<#--</#if>-->


</div><!-- contentpanel -->




<#include "../../commons/footer.ftl"/>

<script>
    $(".updateReview").on("click",function(){
        var index = $(".table-color-").index();

        var id = getTableSelectRow("pagingTable",index).pkReview; //选中行的主键
        if (id == null) {
            Notify.danger("请选择一行数据！！！");
            return;
        }
        var status = $(this).data("id");//判断是审核还是驳回

        var reviewStatus = getTableSelectRow("pagingTable",index).status;//选中行的状态
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

//       var columns = [
//        {"sTitle":"编号","data":"pkReview"},
//        {"sTitle":"名称","data" : "pkDataCaption",
//            "render": function (data, type, row){
//                return '<a href="/course/courseList/edit?pkCourse='+row.pkData+'" class="yellow" >'+data+'</a>';
//            }},
//        {"sTitle":"审核内容","data":"cotent"},
//        {"sTitle":"类型","data" : "type","render":function (data) {
//            if (data==1){ return '题库';}
//            else if(data==0){return '课程'; }
//        }},
//        {"sTitle":"状态","data" : "status","render":function (data) {
//            if (data==0){ return '未审核';}
//            else if(data==1){return '二次审核'; }
//            else if(data==2){return '审核成功';}
//            else if(data==3){return '审核失败';}
//        }},
//        {"sTitle":"创建人","data":"creator"},
//        {"sTitle":"创建时间","data":"creationDate","render":renderDate}
//    ];

    $(document).ready(function() {
        query();
    });

    function pkDataCaption(data, type, row){
        return '<a href="/zhyou/review/courseReview?pkCourse='+row.pkData+'" class="yellow" >'+data+'</a>';
    }

    function renderType(data) {
        if (data==1){ return '题库';}
        else if(data==0){return '课程'; }
    }

    function renderStatus (data) {
        if (data==0){ return '未审核';}
        else if(data==1){return '二次审核'; }
        else if(data==2){return '审核成功';}
        else if(data==3){return '审核失败';}
    }

    function query() {
        var data  = {"pkDataCaption":$("#caption").val()};
       //init(columns,"/zhyou/review/queryByPaging",data,"pagingTable");
        init(GetTableColumn("reviewlist"), "/zhyou/review/queryByPaging", data,"pagingTable");
    }

</script>
