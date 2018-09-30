
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>走读学生 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-info" href="/system/dayStudent/create" >设置走读学生</a>
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
                                <label class="control-label">学生 :</label>
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
        return '<a data-toggle="modal" data-target="#modal" data-url="/system/dayStudent/edit?dayStudentId=' + row.dayStudentId + '" class="yellow" >' + data + '</a>';
    }
    function rederType(data, type, row) {
        if (data == 0){
            return "上午";
        }else if (data == 1){
            return "下午";
        }else {
            return "";
        }
    }
    function rederMeal(data, type, row) {
        if (data == 1){
            return "是";
        }else {
            return "否";
        }
    }




    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "caption":$("#caption").val()};
        init(GetTableColumn("dayStudent"),"/system/dayStudent/queryByPaging",data,"pagingTable");
    }

</script>
