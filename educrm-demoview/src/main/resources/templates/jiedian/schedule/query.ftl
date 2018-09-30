<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />


<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>产品列表 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" href="/jiedian/schedule/create?key=1">新建循环排课</a>
            <a class="btn btn-sm btn-newblue" href="/jiedian/schedule/create<#if pkStudent??>?pkStudent=${(pkStudent)!}</#if>
                <#if pkEmployee??>?pkEmployee=${(pkEmployee)!}</#if>">新建</a>
            <a href="" class="btn btn-sm btn-newblue" onclick="del()"> 删除 </a>
            <#-- <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                 <span class="caret"></span>
                 <span class="sr-only"></span>
             </button>
             <ul class="dropdown-menu" role="menu">
                 <li><a href="" data-toggle="modal" onclick="del()"> 删除 </a></li>
             </ul>-->
            </div>
        </div>
    </div>
   
        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered min-table " cellspacing="0">
                    <thead>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->
</div><!-- contentpanel -->

<#include "../../commons/footer.ftl" />
<script type="text/javascript">
    $(document).ready(function () {
        query();
    });
    function renderCaption(data, type, row) {
        if (row.istype == 0) {
            return '<a href="/jiedian/schedule/edit?pkSchedule=' + row.pkSchedule + '" class="yellow" >' + data + '</a>';
        } else {
            return '<a href="/jiedian/schedule/edit?pkSchedule=' + row.pkSchedule + '" class="yellow" >' + data + '</a>';
        }
    }
    function query() {
        var data = {
            "code": "",
            "caption": $("#caption").val(),
            "istype":${isType},
            "isminority": $("#isminority").val(),
            "grade": $("#grade").val(),
            "phone": $("#phone").val()
        };
        init(GetTableColumn("schedule"), "/jiedian/schedule/queryByPaging", data,"pagingTable");
    }
    function del() {
        var index = $(".table-color-").index();
//        alert(getTableSelectValue("pkStudent"));
//        alert(getTableSelectRow(3).pkStudent);
        var id = getTableSelectRow("pagingTable",index).pkStudent;

        if (id == null) {
            Notify.danger("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/jiedian/schedule/delete",
                data: {"pkStudent": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(data.message)
                    }
                },
                error: function () {

                }
            });
        }


    }

   

      

   
</script>