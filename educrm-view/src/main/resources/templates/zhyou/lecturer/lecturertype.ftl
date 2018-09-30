<#include "../../commons/top.ftl">
<#include "../../commons/left.ftl">

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>添加类别<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>
                <#--<a class="btn btn-sm btn-newblue changeemployee">教师</a>-->
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <form class="look form-ajax" method="POST" id="formId" action="" data-target="" >
            <div class="row">
                <div class="col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-1 col-md-1">组别名称</label>
                        <div class="col-xs-11 col-md-11">
                            <input class="form-control"  value="" type="text">
                            <input name="" value="" type="hidden">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-1 col-md-1">备注</label>
                        <div class="col-xs-11 col-md-11">
                        <#--<input name="stu.memo" value="${(student.memo)!}" type="text" class="form-control">-->
                            <textarea class="form-control" name="stu.memo" rows="3"
                                      va>${(stuint.student)!}</textarea>
                        </div>
                    </div>
                </div><!-- col-sm-4 -->
            </div><!-- col-sm-4 -->
        </form>
    </div>
</div>
<#include "../../commons/footer.ftl"/>

<script type="text/javascript">

    function clear(str) {
        str = str.replace(/,/g, "");//取消字符串中出现的所有逗号
        return str;
    }

</script>