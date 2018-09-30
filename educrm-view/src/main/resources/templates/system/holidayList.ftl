
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>节假日列表 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-newblue btn-sm search" data-toggle="modal" data-target="#modal" data-url="/system/holiday/create" >设置</a>
                        <a class="btn btn-newblue btn-sm search" onclick="del()" >删除</a>
                        <#--<a class="btn btn-sm btn-info" href="">修改</a>-->
                        <#--<a class="btn btn-sm btn-info" href="">查看</a>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <form class="form-inline search white">
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">日期 :</label>-->
                                <#--<input type="text" title=""  name="reservation"  class="form-control" value="" />-->
                            <#--</div>-->
                            <div class="form-group">
                                <label class="control-label">名称 :</label>
                                <input type="text" title=""  name="reservation"  class="form-control" value="" />
                            </div>
                            <div class="form-group">
                                <label></label>
                                <div class="btn-group">
                                    <button type="button" onclick="query()"  class="btn btn-newblue btn-sm search">查询</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div>
        <!--添加部分结束==============================================-->


    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script>
    
    
    function rederCaption(data, type, row) {
        return '<a data-toggle="modal" data-target="#modal" data-url="/system/holiday/edit?id=' + row.id+ '" class="yellow" >' + data + '</a>';
    }
    function rederType(data, type, row) {
        if (data == 0){
            return "工作日";
        }else if (data == 1){
            return "假日";
        }else {
            return "";
        }
    }
//    function rederMeal(data, type, row) {
//        if (data == 1){
//            return "是";
//        }else {
//            return "否";
//        }
//    }




    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "caption":$("#caption").val()};
        init(GetTableColumn("holiday"),"/system/holiday/queryByPaging",data,"pagingTable");
    }




    function del() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).id;

        if (id == null) {
            Notify.danger("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/holiday/delete",
                data: {"id": id},
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
