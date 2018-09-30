<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>部门资料 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue"  data-toggle="modal" data-target="#modal" data-url="/system/department/create">新建部门</a>
                <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <#--<ul class="dropdown-menu" role="menu">-->
                    <#--<li><a onclick="del()" data-toggle="modal">删除</a></li>-->
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
                        <label class="control-label col-xs-5">部门 :</label>
                        <div class="col-xs-7">
                            <#if parentDep ??>
                                <#list parentDep as p>
                                    <div>
                                        <input value="${(p.caption)!}">
                                    </div>
                                </#list>
                            </#if>

                        </div>

                <#--</form>-->
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>

                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>

        </div>

    </div>



<#include "../commons/footer.ftl"/>
<script type="text/javascript">

    $(document).ready(function() {
        query();
    });

    function renderCaption (data, type, row){
        return '<a href="/system/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
    }

    function query() {
        var data  = { "pkDepartment": $("#datas").val(),"caption":$("#caption").val(),"jobPost":job};
        //init(columns,"/system/employee/queryByPaging",data,"pagingTable");
        init(GetTableColumn("employee"), "/system/department/queryByPaging", data,"pagingTable");
    }


//    window.onload = function() {
//        $.ajax({
//            type: "GET",
//            url: "/system/department/getData",
//            data: {},
//            dataType: "json",
//            success: function (data) {
//                var treeGrid = new TreeGrid(data);
//                treeGrid.show()
//            },
//            error: function () {
//
//            }
//        });
//    }
</script>