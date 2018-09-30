<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>答题人详情<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/junhua/jhExamination/query">返回</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <#--<div class="panel  panel-alt widget-quick-status-post mb0 bor0">-->
            <#--<div class="panel-heading pd10 bor0 new">-->
                <#--<form class="form-inline search white">-->
                    <#--&lt;#&ndash;<div class="form-group">&ndash;&gt;-->
                        <#--&lt;#&ndash;<label class="control-label">日期 :</label>&ndash;&gt;-->
                        <#--&lt;#&ndash;<input type="text" title="" name="reservation" id="datas" class="form-control form-input-time"value=""/>&ndash;&gt;-->
                    <#--&lt;#&ndash;</div>&ndash;&gt;-->
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">问卷名称 :</label>-->
                        <#--<input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>-->
                    <#--</div>-->

                    <#--<!--   <div class="form-group">-->
                           <#--<span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>-->
                       <#--</div>&ndash;&gt;-->
                <#--</form>-->
            <#--</div>-->
        <#--</div>-->

            <input type="hidden" id="pkExamination" value="${(pkExamination)!}">
        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered sm-table" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--<th>教室编号</th>-->
                        <#--<th>教室名称</th>-->
                        <#--<th>容纳人数</th>-->
                        <#--<th>状态</th>-->
                        <#--<th>创建人</th>-->
                        <#--<th>创建时间</th>-->
                        <#--<th>修改人</th>-->
                        <#--<th>修改时间</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#list list as v>-->
                    <#--<tr dataid="${(v.pkClassRoom)!}">-->
                        <#--<td align="left">-->
                            <#--<a class="yellow" data-toggle="modal" data-target="#modal"-->
                               <#--data-url="/classinfo/classRoom/edit?pkClassRoom=${(v.pkClassRoom)!}">${(v.code)!}</a>-->
                        <#--</td>-->
                        <#--<td align="left">${(v.caption)!}</td>-->
                        <#--<td>${(v.num)!}</td>-->
                        <#--<td><#if v.isvalid?? && v.isvalid ==1>启用<#else >禁用</#if></td>-->
                        <#--<td align="left">${(v.map.creatorEntity.caption)!}</td>-->
                        <#--<td align="right">${v.creationDate ? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                        <#--<td align="left">${(v.map.modifierEntity.caption)!}</td>-->
                        <#--<td align="right">${v.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                    <#--</tr>-->
                    <#--</#list>-->

                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">


//    var columns = [
//        {"sTitle":"教室编号","data":"code"},
//        {"sTitle":"教室名称","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/classinfo/classRoom/edit?pkClassRoom='+row.pkClassRoom+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"容纳人数","data" : "num"},
//        {"sTitle":"状态","data" : "isvalid","render": function (data){
//            if (data==1){ return '启用';}
//            else {return '禁用'; }
//        }},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ];

$(document).ready(function() {
        query();

    } );

    function query() {
        var data  = {"pkExamination":$("#pkExamination").val()};
        init(GetTableColumn("answer"),"/junhua/jhExamination/findAnswerList",data,"pagingTable");
    }
</script>