
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>校车维护<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" href="/system/schoolBus/create">添加校车</a>
                        <a class="btn btn-sm btn-newblue" id="checkInId">乘坐校车</a>
                        <a class="btn btn-sm btn-newblue" onclick="del()">禁用</a>
                        <#--<a class="btn btn-sm btn-newblue" onclick="sub()">新建宿舍111</a>-->
                        <#--<a class="btn btn-sm btn-danger" data-toggle="modal" data-target="myModal" data-url="">删除</a>-->

                          <#--<button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">-->
                        <#--<span class="caret"></span>-->
                        <#--<span class="sr-only"></span>-->
                    <#--</button>-->
                    <#--<ul class="dropdown-menu" role="menu">-->
                        <#--&lt;#&ndash;<li><a href="" data-toggle="modal">删除</a></li>&ndash;&gt;-->
                        <#--<li><a onclick="del()">删除</a></li>-->
                        <#--<li class="divider"></li>-->
                    <#--</ul>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <div class="panel-btns">
                            <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                        </div><!-- panel-btns -->
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">日期 :</label>-->
                                <#--<input type="text" title="" id="timeid"  name="datetime"  class="form-control form-input-lg js-datepicker"  />-->
                            <#--</div>-->
                            <div class="form-group">
                                <label class="control-label">校车名称 :</label>
                                <input type="text" title="" id="nameid"  name="name"  class="form-control form-input-lg"  />
                            </div>
                            <div class="form-group">
                                <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                            </div>

                        </form>
                        <!--隐藏搜索-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">
                                <#--<form class="form-inline search white">-->
                                    <#--<div class="form-group">-->
                                        <#--<label>学部</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>全部</option>-->
                                                <#--<option>一部</option>-->
                                                <#--<option>二部</option>-->
                                            <#--</select>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">家长姓名 :</label>-->
                                        <#--<input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">入学年级 :</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>全部</option>-->
                                                <#--<option>一年级</option>-->
                                                <#--<option>二年级</option>-->
                                                <#--<option>三年级</option>-->
                                                <#--<option>四年级</option>-->
                                                <#--<option></option>-->
                                            <#--</select>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</form>-->
                            </div>

                        </div>
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

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>


<script type="text/javascript">


//    var columns = [
//        {"sTitle":"编号","data" : "code",
//            "render": function (data, type, row){
//                return '<a href="/system/schoolBus/edit?pkSchoolBus='+row.pkSchoolBus+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"车辆编号","data":"busCode"},
//        {"sTitle":"车辆名称","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/system/schoolBus/edit?pkSchoolBus='+row.pkSchoolBus+'" class="yellow" >'+data+'</a>';
//            }}  ,
//        {"sTitle":"车牌号","data" : "plateNumber"},
//        {"sTitle":"满载人数","data" : "num"},
//        {"sTitle":"已做人数","data" : "currentNum"},
//        {"sTitle": "状态", "data": "isvalid", "render": function (data) {
//            if (data == 0) {
//                return '启用';
//            }
//            else {
//                return '禁用';
//            }
//        }
//        }
//    ];

    function renderCaption(data, type, row) {
        return '<a href="/system/schoolBus/edit?pkSchoolBus='+row.pkSchoolBus+'" class="yellow" >'+data+'</a>';
    }

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "caption":$("#nameid").val()};
        init(GetTableColumn("schoolBus"),"/system/schoolBus/queryByPaging",data,"pagingTable");
    }


    function del() {
        var index = $(".table-color-").index();

        var id = getTableSelectRow("pagingTable",index).pkSchoolBus;

        if(id ==null){
            alert("请先选择要删除的数据");
            return;
        }
        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/schoolBus/delete",
                data: {"pkSchoolBus": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        alert(data.message);
                        window.location.reload();
                    }
                },
                error: function () {

                }
            });
        }


    }
    
//    function checin() {
    $("#checkInId").on("click",function () {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkSchoolBus;
        if(id == null){
            alert("请先选择要乘坐的校车");
            return false;
        }
//        $("#checkInId").attr("href","/system/dormRoom/checkIn?pkDormRoom="+id);
        window.location.href="/system/schoolBus/checkIn?pkSchoolBus="+id;
//        return true;
    })



</script>
<script type="text/javascript">
    <#--搜索功能-->
    <#--function sub() {-->
        <#--var arr = new Array();-->
        <#--var caption = $("#nameid").val();-->
        <#--var time = $("#timeid").val();-->
        <#--&lt;#&ndash;arr = ${list};&ndash;&gt;-->
        <#--var ar = ${(dorm)!};-->
        <#--if(ar.length >0){-->
            <#--$.each(ar,function (i) {-->

            <#--});-->
        <#--}-->



    <#--}-->


</script>
