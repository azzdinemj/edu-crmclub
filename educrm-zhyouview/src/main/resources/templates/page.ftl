<!--course为pageinfo对象-->
<!--分页页码-->
<#if course??&&course.list??>
<ul class="pagination"  style="margin-left: 12%"  id="page">
    <li><a href="/course/query?pageNo=${course.prePage}">&laquo;</a></li>
<#assign tp=course.pages/>
<#assign p=course.pageNum/>
<#assign sp=p-3/>
<#assign ep=p+4/>
<#assign eoff=ep-tp/>
<#if (eoff>0)>
    <#assign sp = sp - eoff/>
</#if>
<#if (sp<=0)>
    <#assign ep = ep - sp+1/>
</#if>
<#list sp..ep as x>
    <#if (x>0 && x<=tp)>
     <li ><a href="/course/query?pageNo=${x}">${x}</a></li>
    </#if>
</#list>
    <li ><a href="/course/query?pageNo=${course.nextPage}">&raquo;</a></li>
</ul>

</#if>