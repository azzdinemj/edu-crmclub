<#include "../../commons/top.ftl">
<#include "../../commons/left.ftl">

<form class="look form-ajax" method="POST" id="formId" action="/question/questionlist/save" data-target="/question/questionlist/query" >
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>添加题目<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <#--<a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>-->

                <!-- 若是编辑，判断isissue是否为草稿，是草稿可以提交-->
                <#if questions??&&questions.status??&&questions.status==0 >
                    <input class="btn btn-sm btn-newblue" type="submit" value="保存">
                    <a id="submitQuestions" class="btn btn-sm btn-newblue ">提交</a>
                </#if>
                    <!-- 若是新建-->
                <#if questionsAdd??&&questionsAdd==1 >
                    <input class="btn btn-sm btn-newblue" type="submit" value="保存">
                </#if>
            </div>
        </div>
    </div>

    <!--content-->
    <div class="panel panel-default pl35">

        <div class="row">
            <div class="col-xs-12 col-md-4">
                <div class="form-group">
                    <label class="control-label col-xs-3 col-md-3">课件名称</label>
                    <div class="col-xs-9 col-md-9">
                        <input class="form-control"  value="${(questions.title)!}" name="title" type="text">
                        <input name="pkQuestions"  value="${(questions.pkQuestions)!}" type="hidden">
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12 col-md-12">
                <div class="form-group">
                    <label class="control-label col-xs-3 col-md-1">图片</label>
                    <div class="col-xs-9 col-md-11">
                        <input type="file" id="file1" name="file" onchange="ajaxSubmitForm(this.id)"/>
                       <input type="hidden" id="picImg" name="img">
                    </div>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-xs-12 col-md-12">
                <div class="form-group">
                    <label class="control-label col-xs-3 col-md-1">试题</label>
                    <div class="col-xs-9 col-md-11">
                        <input type="file" id="file2" name="file" onchange="ajaxSubmitForm(this.id)"/>
                        <input type="hidden" id="picUrl" name="Url" value="">
                    </div>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-xs-12 col-md-4">
            <div class="form-group">
                <label class="control-label col-xs-3 col-md-2">试题类型</label>
                <div class="col-xs-9 col-md-10">
                    <select name="type" class="form-control">
                        <#--<option <#if questions.type??&&questions.type=='0' > selected </#if> value="0">-->
                            <#--经济-->
                        <#--</option>-->
                        <#--<option <#if questions.type??&& questions.type=='1' > selected</#if> value="1">-->
                           <#--历史-->
                        <#--</option>-->

                    <#if dict??>
                        <#list dict as v >
                            <option  <#if questions.type?? && v.pkSysDictValues??&& questions.type == v.pkSysDictValues > selected</#if> value="${(v.pkSysDictValues)!}">
                            ${(v.caption)!}
                            </option>
                        </#list>
                    </#if>

                    </select>
                </div><!-- /.col -->
            </div>
        </div>
        </div>

        <#--<div class="row">-->
            <#--<div class="col-xs-12 col-md-4">-->
            <#--<div class="form-group">-->
                <#--<label class="control-label col-xs-3 col-md-2">试题状态</label>-->
                <#--<div class="col-xs-9 col-md-10">-->
                    <#--<select name="status" class="form-control">-->
                        <#--<option <#if questions.status??&&questions.status==1 > selected </#if> value="0">-->
                           <#--启用-->
                        <#--</option>-->
                        <#--<option <#if questions.status??&& questions.status==0 > selected</#if> value="1">-->
                           <#--不启用-->
                        <#--</option>-->
                    <#--</select>-->
                <#--</div><!-- /.col &ndash;&gt;-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
    <!---->
    </div>
</div>
</form>
<#include "../../commons/footer.ftl"/>




<script type="text/javascript">

    //试题 、 图片
    function ajaxSubmitForm(ids){
        var requestData = {};
        var value= document.getElementById(ids).value;
        requestData.file = value;
        var id_str=ids;
        $.ajaxFileUpload({
            url : '/file/upload',
            type : 'post',
            secureuri : false,
            data : requestData,
            fileElementId : id_str,  //文件             //普通数据
            dataType : 'json',
            success : function(result) {
                // hideLoading();
                if(result.code == 0){
                    document.getElementById("picUrl").value = result.data;
                    Notify.success("success");
                }
            }
        });
    }


    $("#submitQuestions").click(function () {

        $.get("/question/questionlist/submitQuestions?pkQuestions=${(questions.pkQuestions)!}", function(data){
            Notify.success("提交成功！");
            //提交成功跳转列表页面
            location.href="/question/questionlist/query?"+new Date().getTime();
        });


    });


</script>