<#include "../commons/top.ftl">
<#include "../commons/left.ftl">

<form class="look form-ajax" method="POST" id="formId" action="/system/sysButtonManager/save" data-target="/system/sysButtonManager/query" >
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>add button<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
                <#--<a class="btn btn-sm btn-newblue" onclick="sub()">Preservation</a>-->
                <input class="btn btn-sm btn-newblue" type="submit" value="Preservation">
            </div>
        </div>
    </div>

    <!--content-->
    <div class="panel panel-default pl35">


        <div class="form-group row">
            <label class="control-label col-xs-2 col-md-2">pk_sys_menu</label>
            <div class="col-xs-4 col-md-4">

                <input id="pkSysMenu" name="pkSysMenu" type="hidden"  value="${(responseSysmenu.pkSysMenu)!}" class="form-control"
                       title="" >
                <input name="pkSysMenucaption" id="pkSysMenucaption" readonly value="${(responseSysmenu.pkSysMenu)!}"
                       class="form-control"<#if responseSysmenu ??&& responseSysmenu.pkSysMenu??><#else >data-toggle="modal"
                       data-target="#modal" data-url="/system/sysButtonManager/querylist"</#if> >

            </div><!-- /.col -->
        </div>


        <div class="form-group">
            <label class="control-label col-xs-2 col-md-2">pk_sys_button</label>
            <div class="col-xs-4 col-md-4">
                <input name="pkSysButton"  value="${(responseSysmenu.pkSysButton)!}" class="form-control"
                       title="" type="text">
            </div><!-- /.col -->
        </div>

        <div class="form-group">
            <label class="control-label col-xs-2 col-md-2">caption</label>
            <div class="col-xs-4 col-md-4">
                <input name="caption"  value="${(responseSysmenu.caption)!}" class="form-control"
                       title="" type="text">
            </div><!-- /.col -->

        </div>

        <div class="form-group">
            <label class="control-label col-xs-2 col-md-2">caption_eng</label>
            <div class="col-xs-4 col-md-4">
                <input name="captionEng"  value="${(responseSysmenu.captionEng)!}" class="form-control"
                       title="" type="text">
            </div><!-- /.col -->

        </div>

        <div class="form-group">
        <label class="control-label col-xs-2 col-md-2">Whether show</label>
        <div class="col-xs-4 col-md-4">
            <select name="display" class="form-control">
                <option <#if responseSysmenu??&& responseSysmenu.display?? && responseSysmenu.display==1> SELECTED</#if> value="1" >display</option>
                <option <#if responseSysmenu??&& responseSysmenu.display?? && responseSysmenu.display==0> SELECTED</#if> value="0" >not display</option>
            </select>
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
        $("#pkSysMenu").val(pkId);
        $("#pkSysMenucaption").val(caption);
    }

</script>