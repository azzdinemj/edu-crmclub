<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2>问卷调查统计-结果统计</h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-info" href="/junhua/jhExamination/query" >返回</a>
                    </div>
                </div>
            </div>
            <#--<style>-->
                <#--.progress{-->
                    <#--width: 100%;-->
                <#--}-->
                <#--.num{-->
                    <#--line-height: 24px;-->

                <#--}-->
                <#--.se{-->
                    <#--width: 500px;-->
                <#--}-->
                <#--.progress-bar-info{-->
                 <#--background-color:  #2191FE-->
                <#--}-->

            <#--</style>-->
            <div class="panel panel-default">
                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                <a style="display: none" id="getAnswerList" data-toggle="modal" data-target="#modal" data-url="" >配置课程</a>
                <#list jhQuestionList as question>
                    <div class="panel-heading pd10 bor0 new">
                        <h3>${(question.caption)!}</h3>
                            <div class="se">
                                <#if (question.jhOptionList?size > 0)>
                                    <#list question.jhOptionList as options>
                                        <h4>${(options.mark)!}丶${(options.caption)!}</h4>
                                        <#--<#if question.pkQuestion?? &&  options.pkQuestion?? && options.pkQuestion == question.pkQuestion>-->
                                            <div class="progress getAnswerList" style="width: 50%;" data-question="${(options.pkQuestion)!}" data-examin="${(question.pkExamination)!}" data-options="${(options.mark)!}">
                                                <span class="progressnum" >${(options.peopleNum)!}</span>
                                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="${(options.num)!}" aria-valuemin="0" aria-valuemax="100" style="width: ${(options.num)!}%">

                                                </div>
                                            </div>
                                        <#--</#if>-->
                                    </#list>
                                </#if>
                            </div>
                        <#if question.other?? && question.other == 1 >
                            <h4 class="panel-title text-center">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseOne">
                                    其他
                                </a>
                            </h4>
                            <div id="collapseOne" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <#if (question.otherList?size > 0)>
                                        <#list question.otherList as others>
                                            <#--<#if others.pkQuestion?? &&  question.pkQuestion?? &&  others.pkQuestion == question.pkQuestion>-->
                                                <h4 style="color: #209ae8;">${(others.name)!}</h4>
                                                <p>内容：${(others.others)!}</p>
                                            <#--</#if>-->
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                        </#if>
                    </div>
                </#list>
                </div>


            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->
<#include "../commons/footer.ftl"/>

<script>
    $(function(){
        $(".getAnswerList").on("click",function(){
            var question = $(this).data("question");
            var examin = $(this).data("examin");
            var options = $(this).data("options");
//            $("#getAnswerList").attr("data-url","");
            $("#getAnswerList").attr("data-url","/junhua/jhExamination/getAnswerList?pkExamination="+examin+"&pkQuestion="+question+"&option="+options);
            $("#getAnswerList").click();
        })
    })
</script>