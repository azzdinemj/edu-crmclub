
<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<!--未使用 -->
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>题库试卷<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/question/questionPaper/edit">新建题目</a>
                <#--<a class="btn btn-sm btn-newblue" onclick="del()">删除</a>-->
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
                <table id="tables"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>title</th>
                        <th>explain</th>
                        <th>year</th>
                        <th>regdate</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if questions??>
                        <#list questions.list as que>
                        <tr>
                            <td>${(que.id)!}</td>
                            <td>
                                <a class="yellow" href="/question/questionPaper/edit?id=${(que.id)!}">
                                ${(que.title)!}
                                </a>
                            </td>
                            <td> ${(que.explain)!}</td>
                            <td> ${(que.year)!}</td>
                            <td> ${que.regdate?string("yyyy-MM-dd HH:mm:ss")} </td>

                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->



    <#if questions??>
        <ul class="pagination"    id="page">
            <li><a href="/question/questionItem/query?pageNo=${questions.prePage}">&laquo;</a></li>
            <#assign tp=questions.pages/>
            <#assign p=questions.pageNum/>
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
                    <li ><a href="/question/questionPaper/query?pageNo=${x}">${x}</a></li>
                </#if>
            </#list>
            <li ><a href="/question/questionPaper/query?pageNo=${questions.nextPage}">&raquo;</a></li>
        </ul>
    </#if>


    </div><!-- contentpanel -->


<#include "../../commons/footer.ftl"/>
    <script type="text/javascript">












    </script>

