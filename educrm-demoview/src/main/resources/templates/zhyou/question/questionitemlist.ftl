
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
                <ul id="treeDemo" class="ztree">tree</ul>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="tables"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>title</th>
                        <th>rank</th>
                        <th>type</th>

                    </tr>
                    </thead>
                    <tbody>
                    <#if optionList??>
                        <#list optionList.list as que>
                        <tr>
                            <td>${(que.id)!}</td>
                            <td>
                                <a class="yellow" href="/question/questionItem/edit?id=${(que.id)!}">
                                ${(que.title)!}
                                </a>
                            </td>
                            <td>
                                <#if que??&& que.rank??&&que.rank==1>
                                     难
                                </#if>
                                <#if que??&& que.rank??&&que.rank==2>
                                     中
                                </#if>
                                <#if que??&& que.rank??&&que.rank==3>
                                     易
                                </#if>
                            </td>
                            <td>
                                <#if que??&& que.type??&&que.type==1>
                                   单选题
                                </#if>
                                <#if que??&& que.type??&&que.type==2>
                                    多选题
                                </#if>
                                <#if que??&& que.type??&&que.type==3>
                                    判断题
                                </#if>
                                <#if que??&& que.type??&&que.type==4>
                                    填空题
                                </#if>
                                <#if que??&& que.type??&&que.type==5>
                                    问答题
                                </#if>
                                <#if que??&& que.type??&&que.type==6>
                                    题帽题
                                </#if>

                            </td>
                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->



    <#if optionList??>
        <ul class="pagination"    id="page">
            <li><a href="/question/questionItem/query?pageNo=${optionList.prePage}">&laquo;</a></li>
            <#assign tp=optionList.pages/>
            <#assign p=optionList.pageNum/>
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
                    <li ><a href="/question/questionItem/query?pageNo=${x}">${x}</a></li>
                </#if>
            </#list>
            <li ><a href="/question/questionItem/query?pageNo=${optionList.nextPage}">&raquo;</a></li>
        </ul>
    </#if>


    </div><!-- contentpanel -->




<#include "../../commons/footer.ftl"/>
<#--<script src="${staticPath}/js/zTree/jquery.ztree.core.js">-->
<script src="${staticPath}/js/jquery.ztree.core.js">
    </script><link rel="stylesheet" href="${staticPath}/css/zTreeCss/zTreeStyle.css" type="text/css">

    <script type="text/javascript">

        var zTreeObj;

        function zTreeBeforeClick(treeId, treeNode, clickFlag) {
            return (treeNode.id !== 1);
        };

        function zTreeOnClick(event, treeId, treeNode) {
            $("#queryItem").attr("href","/question/questionItem/query?questionsSubjectId="+treeNode.id)[0].click();

            // $.get("/question/questionItem/query?questionsSubjectId="+treeNode.id);
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


    </script>

