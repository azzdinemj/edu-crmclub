
<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>题库题目<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/question/questionItem/edit">新建题目</a>
                <#--<a class="btn btn-sm btn-newblue" onclick="del()">删除</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <a id="queryItem"></a>
            <#--<ul id="treeDemo" class="ztree">tree</ul>-->
                <form class="form-inline search white">
                    <#--<div class="form-group">-->
                    <#--<label class="control-label">日期 :</label>-->
                    <#--<input type="text" title="" id="timeid" name="datetime" class="form-control js-datepicker"/>-->
                    <#--</div>-->

                    <div class="form-group">
                        <label class="control-label">题干 :</label>
                        <input placeholder="题干" type="text"  id="caption" name="caption" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>
                </form>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <#--<table id="tables"  class="table table-striped table-bordered" cellspacing="0">-->
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--<th>编号</th>-->
                        <#--<th>题干</th>-->
                        <#--<th>难易度</th>-->
                        <#--<th>类型</th>-->

                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#if optionList??>-->
                        <#--<#list optionList.list as que>-->
                        <#--<tr>-->
                            <#--<td>${(que.id)!}</td>-->
                            <#--<td>-->
                                <#--<a class="yellow" href="/question/questionItem/edit?id=${(que.id)!}">-->
                                <#--<#if que??&&que.title??&&que.title=="">未命名<#else> ${(que.title)!}</#if>-->
                                <#--</a>-->
                            <#--</td>-->
                            <#--<td>-->
                                <#--<#if que??&& que.rank??&&que.rank==1>-->
                                     <#--难-->
                                <#--</#if>-->
                                <#--<#if que??&& que.rank??&&que.rank==2>-->
                                     <#--中-->
                                <#--</#if>-->
                                <#--<#if que??&& que.rank??&&que.rank==3>-->
                                     <#--易-->
                                <#--</#if>-->
                            <#--</td>-->
                            <#--<td>-->
                                <#--<#if que??&& que.type??&&que.type==1>-->
                                   <#--单选题-->
                                <#--</#if>-->
                                <#--<#if que??&& que.type??&&que.type==2>-->
                                    <#--多选题-->
                                <#--</#if>-->
                                <#--<#if que??&& que.type??&&que.type==3>-->
                                    <#--判断题-->
                                <#--</#if>-->
                                <#--<#if que??&& que.type??&&que.type==4>-->
                                    <#--填空题-->
                                <#--</#if>-->
                                <#--<#if que??&& que.type??&&que.type==5>-->
                                    <#--问答题-->
                                <#--</#if>-->
                                <#--<#if que??&& que.type??&&que.type==6>-->
                                    <#--题帽题-->
                                <#--</#if>-->

                            <#--</td>-->
                        <#--</tr>-->
                        <#--</#list>-->
                    <#--</#if>-->
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- contentpanel -->
<#include "../../commons/footer.ftl"/>
<script src="${staticPath}/js/jquery.ztree.core.js">
</script><link rel="stylesheet" href="${staticPath}/css/zTreeCss/zTreeStyle.css" type="text/css">

<script type="text/javascript">
        var zTreeObj;
        function zTreeOnClick(event, treeId, treeNode) {
            $("#queryItem").attr("href","/question/questionItem/query?questionsSubjectId="+treeNode.id)[0].click();
        };
        var setting = {
            data: {
                key : {
                        id:"id",
                        title : "name", //鼠标悬停显示的信息
                        name : "name" //网页上显示出节点的名称
                    }
            },
            callback: {
                onClick: zTreeOnClick
            }
        };
        var zNodes;
        //初始化加载nodes数据
        $(document).ready(function(){
            $.ajax({
                type: "GET",
                url: "/question/questionItem/getData",
                data: {},
                dataType: "json",
                async:false,
                success: function (data) {
                    zNodes=data;
                },
                error: function () {

                }
            });
        });

        $(document).ready(function(){
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });

        $(document).ready(function() {
            query();
        });

        function  renderTitle(data, type, row){
            if(data==""){
                return '<a href="/question/questionItem/edit?id='+row.id+'" class="yellow" >'+"null"+'</a>';
            }else{
                return '<a href="/question/questionItem/edit?id='+row.id+'" class="yellow" >'+data+'</a>';
            }
        }

//        function renderRank(data) {
//            if (data==1){ return '难';}
//            else if(data==2){return '中'; }
//            else if(data==3){return '易'; }
//        }

        function renderType(data) {
            if (data==1){ return '单选题';}
            else if(data==3){return '判断题'; }
        }

        function query() {
            var data  = {"title":$("#caption").val()};
            //init(columns,"/question/questionItem/queryByPaging",data,"pagingTable");
            init(GetTableColumn("questionitemlist"), "/question/questionItem/queryByPaging", data,"pagingTable");
        }
</script>

