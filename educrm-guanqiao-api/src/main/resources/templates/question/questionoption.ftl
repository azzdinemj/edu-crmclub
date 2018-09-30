<#include "../../commons/top.ftl">
<#include "../../commons/left.ftl">

<!--未使用 -->
<form class="look form-ajax" method="POST" id="formId" action="/question/questionOption/save" data-target="/question/questionOption/query" >
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>题库选项<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <input class="btn btn-sm btn-newblue" type="submit" value="保存">
            </div>
        </div>
    </div>

    <!--content-->
    <div class="panel panel-default pl35">

        <div class="row">
            <div class="col-xs-12 col-md-4">
                <div class="form-group">
                    <label class="control-label col-xs-3 col-md-3">名称</label>
                    <div class="col-xs-9 col-md-9">
                        <input class="form-control"  value="${(questions.title)!}" name="title" type="text">
                        <input name="id"  value="${(questions.id)!}" type="hidden">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-md-4">
                <div class="form-group">
                    <label class="control-label col-xs-3 col-md-3">详情</label>
                    <div class="col-xs-9 col-md-9">
                        <textarea id="editor_id"  name="content" style="width:100%;height:300px;">${(questions.content)!}</textarea>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-md-4">
                <div class="form-group">
                    <label class="control-label col-xs-3 col-md-3">排序</label>
                    <div class="col-xs-9 col-md-9">
                        <input class="form-control"  value="${(questions.sort)!}" name="sort" type="text">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-md-4">
            <div class="form-group">
                <label class="control-label col-xs-3 col-md-2">是否显示</label>
                <div class="col-xs-9 col-md-10">
                    <select name="status" class="form-control">
                        <option <#if questions??&& questions.status??&&questions.status==1 > selected </#if> value=1>
                          显示
                        </option>
                        <option <#if  questions??&&questions.status??&& questions.status==0 > selected</#if> value=0>
                           不显示
                        </option>
                    </select>
                </div><!-- /.col -->
            </div>
        </div>
        </div>


    </div>
</div>
</form>
<#include "../../commons/footer.ftl"/>




<script type="text/javascript">




    $("#submitQuestions").click(function () {

        $.get("/question/questionlist/submitQuestions?pkQuestions=${(questions.pkQuestions)!}", function(data){
            Notify.success("提交成功！");
            //提交成功跳转列表页面
            location.href="/question/questionlist/query?"+new Date().getTime();
        });


    });


</script>