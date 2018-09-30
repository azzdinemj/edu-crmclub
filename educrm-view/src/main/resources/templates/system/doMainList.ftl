
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>校区管理 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue"  data-toggle="modal" data-target="#modal" data-url="/system/domain/create">新建校区</a>
                <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <#--<ul class="dropdown-menu" role="menu">-->
                    <#--<li><a onclick="del()">删除</a></li>-->
                <#--</ul>-->
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <!--高显搜索-->
                <#--<form class="form-inline search white">-->
                    <#--<div class="form-group">-->
                        <#--<label class="control-label col-xs-5">所属部门 :</label>-->
                        <#--<div class="col-xs-7">-->
                            <#--<select class="selectpicker form-control">-->
                                <#--<optgroup label="总校">-->
                                    <#--<option>财务部门</option>-->
                                    <#--<option>财务部门</option>-->
                                    <#--<option>财务部门</option>-->
                                <#--</optgroup>-->
                                <#--<option>财务部门</option>-->
                                <#--<option>财务部门</option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->

                <#--</form>-->
            </div>
        </div>


   <#-- <div id="div1"></div>-->
    <div class="panel-body pd0">

        <div id="div1"></div>

    </div>
    </div>
</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>

<script type="text/javascript" >

//    function del(id) {
//
//
//        var flag = confirm("确认删除吗？");
//        if (flag) {
//            $.ajax({
//                type: "POST",
//                url: "/system/domain/delete",
//                data: {"pkDomain": id},
//                dataType: "json",
//                success: function (data) {
//                    if (data.code == 0) {
//                        Notify.success(data.message);
//                        setTimeout("location.reload()", 1);
//                    } else {
//                        alert(data.message)
//                    }
//                },
//                error: function () {
//
//                }
//            });
//        }
//
//
//    }

    window.onload = function() {
        $.ajax({
            type: "GET",
            url: "/system/domain/getData",
            data: {},
            dataType: "json",
            success: function (data) {
                var treeGrid = new TreeGrid(data);
                treeGrid.show()
            },
            error: function () {

            }
        });
       /* var node = $('#datatreegrid').treegrid('getSelected');
        alert(node);*/
    }

    /*function () {
        var node = $('#datatreegrid').treegrid('getSelected');
        alert(1);

    }*/

</script>
