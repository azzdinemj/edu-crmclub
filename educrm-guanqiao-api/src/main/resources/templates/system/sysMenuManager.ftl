<#include "../commons/top.ftl">
<#include "../commons/left.ftl">

<form class="look form-ajax" method="POST" id="formId" action="/system/sysMenuManager/save" data-target="/system/sysMenuManager/query" >
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>添加菜单<span>...</span></h2>
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


        <div class="form-group row">
            <label class="control-label col-xs-2 col-md-2">名称</label>
            <div class="col-xs-4 col-md-4">
                <input name="caption" value="${(responseSysmenu.caption)!}" class="form-control"
                       title="" type="text">

            </div><!-- /.col -->

            <label class="control-label col-xs-2 col-md-2">英文名称</label>
            <div class="col-xs-4 col-md-4">
                <input name="captionEng" value="${(responseSysmenu.captionEng)!}" class="form-control"
                       title="" type="text">

            </div><!-- /.col -->

        </div>

        <div class="form-group">
            <label class="control-label col-xs-2 col-md-2">主键</label>
            <div class="col-xs-4 col-md-4">
                <input name="pkSysMenu" readonly value="${(responseSysmenu.pkSysMenu)!}" class="form-control"
                       title="" type="text">
            </div><!-- /.col -->


            <label class="control-label col-xs-2 col-md-2">父类主键</label>
            <div class="col-xs-4 col-md-4">

                    <input type="hidden" name="pkParent" id="pkParent"  value="${(responseSysmenu.pkParent)!}" class="form-control"
                       title="">
                    <input name="pkParentcaption" id="pkParentcaption" readonly value="${(responseSysmenu.pkParent)!}" class="form-control"<#if responseSysmenu ??&& responseSysmenu.pkParent??><#else >data-toggle="modal"
                           data-target="#modal" data-url="/system/sysMenuManager/querylist"</#if> >
            </div><!-- /.col -->


        </div>

        <div class="form-group">
            <label class="control-label col-xs-2 col-md-2">排序</label>
            <div class="col-xs-4 col-md-4">
                <input name="sort" value="${(responseSysmenu.sort)!}" class="form-control"
                       title="" type="text">
            </div><!-- /.col -->
            <label class="control-label col-xs-2 col-md-2">是否显示</label>
            <div class="col-xs-4 col-md-4">
                <select name="isdisplay" class="form-control">
                <#--<option  value="1" <#if responseSysmenu.isdisplay?? && responseSysmenu.isdisplay==1 >selected="selected"</#if> >显示</option>-->
                <#--<option  value="0" <#if responseSysmenu.isdisplay?? && responseSysmenu.isdisplay==0 >selected="selected"</#if> >不显示</option>-->

                    <option <#if responseSysmenu??&& responseSysmenu.isdisplay?? && responseSysmenu.isdisplay==1> SELECTED</#if> value="1" >显示</option>
                    <option <#if responseSysmenu??&& responseSysmenu.isdisplay?? && responseSysmenu.isdisplay==0> SELECTED</#if> value="0" >不显示</option>

                </select>
            </div><!-- /.col -->

        </div>

        <div class="form-group ">

            <label class="control-label col-xs-2 col-md-2">地址</label>
            <div class="col-xs-4 col-md-4">
                <input name="url" class="form-control" value="${(responseSysmenu.url)!}"
                       title="" type="text">
            </div><!-- /.col -->

        </div>

        <div class="form-group ">

            <label class="control-label col-xs-2 col-md-2">img</label>
            <div class="col-xs-4 col-md-4">
                <input name="img" class="form-control" value="${(responseSysmenu.img)!}"
                       title="" type="text">
            </div><!-- /.col -->

        </div>
        <!---->
    </div>
</div>
</form>
<#include "../commons/footer.ftl"/>




<script type="text/javascript">


    function selectStu(pkId,caption) {
        $("#modal").modal("hide");
        $("#pkParentcaption").val(caption);
        $("#pkParent").val(pkId);
    }


</script>