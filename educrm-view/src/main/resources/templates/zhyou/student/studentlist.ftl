<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>学生列表 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

                        <a class="btn btn-sm btn-newblue" href="/student/studentlist/edit" >新建学生</a>
                        <#--<a class="btn btn-sm btn-newblue" onclick="stuInterview()">面试</a>-->
                        <#--<a class="btn btn-sm btn-newblue" onclick="stuReport()">报名</a>-->
                        <a href="" class="btn btn-sm btn-newblue" onclick="del()"> 删除 </a>
                        <#--<button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">-->
                            <#--<span class="caret"></span>-->
                            <#--<span class="sr-only"></span>-->
                        <#--</button>-->
                        <#--<ul class="dropdown-menu" role="menu">-->
                            <#--<li><a href="" data-toggle="modal" onclick="del()"> 删除 </a></li>-->
                        <#--</ul>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">学生姓名 :</label>
                                <input id="caption" type="text" title=""  name="caption" placeholder="学生名称" class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">状态 :</label>
                                <select id="isvalid" class="form-control strw">
                                    <option value="">全部</option>
                                    <option value="1">已激活</option>
                                    <option value="0">未激活</option>

                                </select>
                            </div>
                            <div class="form-group">
                                   <a id="queryStudent" href=""></a> <button type="button" onclick="query()" class="btn btn-newblue btn-sm search">搜索</button>
                            </div>
                        </form>

                    </div>
                </div>

                <div class="panel-body pd0">
                   <div class="table-responsive">
                       <table id="pagingTable"  class="table table-striped table-bordered  " cellspacing="0">
                           <thead>

                           </thead>
                           <tbody>

                           </tbody>
                       </table>
                   </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

<#include "${staticPath}/commons/footer.ftl" />
<script type="text/javascript">

//   var columns = [
//    {"sTitle":"编号","data":"pkStudent"},
//    {"sTitle":"姓名","data" : "caption",
//        "render": function (data, type, row){
//            return '<a href="/student/studentlist/edit?pkStudent='+row.pkStudent+'" class="yellow" >'+data+'</a>';
//        }},
//    {"sTitle":"电话","data":"phone"},
//    {"sTitle":"公司","data":"company"},
//    {"sTitle":"部门","data":"branch"},
//    {"sTitle":"状态","data" : "isvalid","render":function (data) {
//            if (data==1){ return '已激活';}
//            else if(data==0){return '未激活'; }
//        }}
//    ];

   $(document).ready(function() {
       query();
   });

    function renderCaption(data, type, row){
        return '<a href="/student/studentlist/edit?pkStudent='+row.pkStudent+'" class="yellow" >'+data+'</a>';
    }

    function renderIsvalid (data) {
        if (data==1){ return '已激活';}
        else if(data==0){return '未激活'; }
    }

   function query() {
        var data  = {"caption":$("#caption").val(),"isvalid":$("#isvalid option:selected").val()};
        //init(columns,"/student/studentlist/queryByPaging",data,"pagingTable");
       init(GetTableColumn("studentlist"), "/student/studentlist/queryByPaging", data,"pagingTable");
   }

function del() {
    var index = $(".table-color-").index();
    var id = getTableSelectRow("pagingTable",index).pkStudent;

    if(id ==null){
        Notify.danger("请先选择要删除的数据");
        return;
    }
    var flag = confirm("确认删除吗？");
    if (flag) {
        $.ajax({
            type: "POST",
            url: "/student/studentlist/delete",
            data: {"pkStudent": id},
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    window.location.reload();
                } else {
                    Notify.danger(data.message);
                    window.location.reload();
                }
            },
            error: function () {

            }
        });
    }
}
</script>