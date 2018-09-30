<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>公告管理<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal"
                   data-url="/system/notice/create">新建公告</a>
                <#--<button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle"-->
                        <#--data-toggle="dropdown">-->
                    <#--<span class="caret"></span>-->
                    <#--<span class="sr-only"></span>-->
                <#--</button>-->
                <#--<ul class="dropdown-menu" role="menu">-->
                    <#--<li><a onclick="del()" data-toggle="modal">删除</a></li>-->
                <#--</ul>-->
                <#--<form action="/classinfo/classRoom/uploads" method="post" enctype="multipart/form-data">-->
                    <#--<input type="file" name="fileupload"/>-->
                    <#--<input type="submit" value="提交">-->
                <#--</form>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <#--<div class="panel  panel-alt widget-quick-status-post mb0 bor0">-->
            <#--<div class="panel-heading pd10 bor0 new">-->
                <#--<form class="form-inline search white">-->
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">日期 :</label>-->
                        <#--<input type="text" title="" name="reservation" id="datas" class="form-control form-input-time"-->
                               <#--value=""/>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">教室名称 :</label>-->
                        <#--<input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<a class="btn btn-newblue btn-sm search">搜索</a>-->
                    <#--</div>-->

                    <#--<!--   <div class="form-group">-->
                           <#--<span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>-->
                       <#--</div>&ndash;&gt;-->
                <#--</form>-->
            <#--</div>-->
        <#--</div>-->

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

</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">
//    var columns = [
//        {"sTitle":"公告编码","data":"pkNotice"},
//        {"sTitle":"公告内容","data" : "content",
//            "render": function (data, type, row){
//                return '<a data-url="/system/notice/edit?pkNotice='+row.pkNotice+'" data-toggle="modal" data-target="#modal"  class="yellow" >'+data+'</a>';
//            }},
//        {"sTitle":"状态","data" : "isdel","render": function (data){
//            if (data==1){ return '启用';}
//            else {return '禁用'; }
//        }},
//        {"sTitle":"发布人","data" : "map.userEntity.caption"},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ];
    function renderisdel(data){

        if(data==0){
            return "隐藏";
        }else if(data==1){
            return "启用";
        }
    }

    function renderCaption(data, type, row) {
        return '<a data-url="/system/notice/edit?pkNotice='+row.pkNotice+'" data-toggle="modal" data-target="#modal"  class="yellow" >'+data+'</a>';
    }

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = {};
        init(GetTableColumn("notice"),"/system/notice/queryByPaging",data,"pagingTable");
    }

//    function del() {
//        var id = $(".table-color-").attr("dataid");
//        if (id == null) {
//            alert("请先选择要删除的数据");
//            return;
//        }
//
//        var flag = confirm("确认删除吗？");
//        if (flag) {
//            $.ajax({
//                type: "POST",
//                url: "/classinfo/classRoom/delete",
//                data: {"pkClassRoom": id},
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
</script>