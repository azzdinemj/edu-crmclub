<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<form class="look form-ajax" method="POST" id="formId" action="/question/questionProject/save" data-target="/question/questionProject/query" >
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>添加题库科目<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <#--<a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>-->
                <input class="btn btn-sm btn-newblue" type="submit" value="保存">
            </div>
        </div>
    </div>

    <!--content-->
    <div class="panel panel-default pl35">

        <div class="form-group">

             <input name="id"  type="hidden" value="${(questions.id)!}">

            <label class="control-label col-xs-2 col-md-2">父类主键</label>
            <div class="col-xs-4 col-md-4">

                <input type="hidden" name="pid" id="pkParent"  value="${(questions.pid)!}" class="form-control"
                       title="">
                <input  id="pkParentcaption" readonly value="${(questions.pid)!}" class="form-control"<#if questions ??&& questions.pid??><#else >data-toggle="modal"
                        data-target="#modal" data-url="/question/questionProject/querylist?"</#if> >
            </div><!-- /.col -->


        </div>


        <div class="form-group row">
            <label class="control-label col-xs-2 col-md-2">名称</label>
            <div class="col-xs-4 col-md-4">
                <input name="name" value="${(questions.name)!}" class="form-control"
                       title="" type="text">

            </div><!-- /.col -->


        </div>


        <div class="form-group">
            <label class="control-label col-xs-2 col-md-2">排序</label>
            <div class="col-xs-4 col-md-4">
                <input name="sortid" value="${(questions.sortid)!}" class="form-control"
                       title="" type="text">
            </div><!-- /.col -->
        </div>

        <div class="form-group">
            <label class="control-label col-xs-2 col-md-2">类型</label>
            <div class="col-xs-4 col-md-4">
                <select name="type" class="form-control">

                    <option <#if questions??&& questions.type?? && questions.type==1> SELECTED</#if> value="1" >科目</option>
                    <option <#if questions??&& questions.type?? && questions.type==2> SELECTED</#if> value="2" >学段</option>

                </select>
            </div><!-- /.col -->

        </div>


        <div class="form-group">
            <label class="control-label col-xs-2 col-md-2">是否显示</label>
            <div class="col-xs-4 col-md-4">
                <select name="status" class="form-control">

                    <option <#if questions??&& questions.status?? && questions.status==1> SELECTED</#if> value="0" >显示</option>
                    <option <#if questions??&& questions.status?? && questions.status==0> SELECTED</#if> value="1" >不显示</option>

                </select>
            </div><!-- /.col -->

        </div>





        <!---->
    </div>
</div>
</form>
<#include "../../commons/footer.ftl"/>




<script type="text/javascript">


    function selectStu(pkId,caption) {
        $("#modal").modal("hide");
        $("#pkParentcaption").val(caption);
        $("#pkParent").val(pkId);
    }


</script>