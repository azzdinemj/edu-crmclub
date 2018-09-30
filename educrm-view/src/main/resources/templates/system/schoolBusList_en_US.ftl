
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>School bus maintenance<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" href="/system/schoolBus/create">add bus</a>
                        <a class="btn btn-sm btn-newblue" id="checkInId">Ride bus</a>
                        <a class="btn btn-sm btn-newblue" onclick="del()">Disable</a>
                        <#--<a class="btn btn-sm btn-newblue" onclick="sub()">New dormitory111</a>-->
                        <#--<a class="btn btn-sm btn-danger" data-toggle="modal" data-target="myModal" data-url="">delete</a>-->

                          <#--<button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">-->
                        <#--<span class="caret"></span>-->
                        <#--<span class="sr-only"></span>-->
                    <#--</button>-->
                    <#--<ul class="dropdown-menu" role="menu">-->
                        <#--&lt;#&ndash;<li><a href="" data-toggle="modal">delete</a></li>&ndash;&gt;-->
                        <#--<li><a onclick="del()">delete</a></li>-->
                        <#--<li class="divider"></li>-->
                    <#--</ul>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <div class="panel-btns">
                            <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                        </div><!-- panel-btns -->
                        <!--High search-->
                        <form class="form-inline search white">
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">Date, :</label>-->
                                <#--<input type="text" title="" id="timeid"  name="datetime"  class="form-control form-input-lg js-datepicker"  />-->
                            <#--</div>-->
                            <div class="form-group">
                                <label class="control-label">name :</label>
                                <input type="text" title="" id="nameid"  name="name"  class="form-control form-input-lg"  />
                            </div>
                            <div class="form-group">
                                <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
                            </div>

                        </form>
                        <!--Hide search-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">
                                <#--<form class="form-inline search white">-->
                                    <#--<div class="form-group">-->
                                        <#--<label>Faculty</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>whole</option>-->
                                                <#--<option>One</option>-->
                                                <#--<option>Two</option>-->
                                            <#--</select>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">Parent name :</label>-->
                                        <#--<input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">School year :</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>whole</option>-->
                                                <#--<option>first grade</option>-->
                                                <#--<option>second grade</option>-->
                                                <#--<option>Grade three</option>-->
                                                <#--<option>fourth grade</option>-->
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
//        {"sTitle":"number","data" : "code",
//            "render": function (data, type, row){
//                return '<a href="/system/schoolBus/edit?pkSchoolBus='+row.pkSchoolBus+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"Vehicle number","data":"busCode"},
//        {"sTitle":"Vehicle name","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/system/schoolBus/edit?pkSchoolBus='+row.pkSchoolBus+'" class="yellow" >'+data+'</a>';
//            }}  ,
//        {"sTitle":"number plate","data" : "plateNumber"},
//        {"sTitle":"Full load","data" : "num"},
//        {"sTitle":"take NO.","data" : "currentNum"},
//        {"sTitle": "state", "data": "isvalid", "render": function (data) {
//            if (data == 0) {
//                return 'Enable';
//            }
//            else {
//                return 'Disable';
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
            alert("Please select the data to delete first");
            return;
        }
        var flag = confirm("Do you confirm the deletion？");
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
            alert("Please choose the school bus to take first");
            return false;
        }
//        $("#checkInId").attr("href","/system/dormRoom/checkIn?pkDormRoom="+id);
        window.location.href="/system/schoolBus/checkIn?pkSchoolBus="+id;
//        return true;
    })



</script>
<script type="text/javascript">
    <#--Search function-->
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
