
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Dormitory maintenance<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" href="/system/dormRoom/create">New dormitory</a>
                        <a class="btn btn-sm btn-newblue" id="checkInId">Check in</a>
                        <#--<a class="btn btn-sm btn-newblue" id="checkOutId">out dorm</a>-->
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
                            <#--<tr>-->
                                <#--<th>code</th>-->
                                <#--<th>name</th>-->
                                <#--<th>type</th>-->
                                <#--<th>Capacity</th>-->
                                <#--<th>Resident number</th>-->
                                <#--<th>Sex</th>-->
                                <#--<th>Remarks</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if dormlist ??>-->
                            <#--<#list dormlist as v>-->
                            <#--<tr dataid="${v.pkDormRoom}">-->
                                <#--<td>-->
                                    <#--${(v.code)!}-->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<a class="yellow" href="/system/dormRoom/edit?pkDormRoom=${(v.pkDormRoom)!}">-->
                                        <#--${(v.caption)!}-->
                                    <#--</a>-->
                                <#--</td>-->
                                <#--<td>-->
                                <#--&lt;#&ndash;${(v.kind)!}&ndash;&gt;-->
                                    <#--<#if v.kind?? && v.kind==0>Student dorm<#else >Teacher dorm</#if>-->
                                <#--</td>-->
                                <#--<td>${(v.num)!}</td>-->
                                <#--<td>${(v.currentNum)!}</td>-->
                                <#--<td>-->
                                    <#--<#if v.sex?? && v.sex==1>男<#else >女</#if>-->
                                <#--</td>-->
                                <#--<td>${(v.memo)!}</td>-->
                            <#--</tr>-->
                            <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>


<script type="text/javascript">

    $(document).ready(function() {
        query();
    } );

    function renderCaption(data, type, row) {

        return '<a href="/system/dormRoom/edit?pkDormRoom=' + row.pkDormRoom + '" class="yellow" >' + data + '</a>';
    }

    function renderRoomKind(data, type, row) {
        if (data==0){
            return "Student dorm";
        }else if (data==1){
            return "Teacher dorm";
        }
    }

    function query() {
        var data  = { "caption":$("#nameid").val()};
        init(GetTableColumn("dormRoom"),"/system/dormRoom/queryByPaging",data,"pagingTable");
    }


    function del() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkDormRoom;

        if(id ==null){
            alert("Please select the data to delete first");
            return;
        }
        var flag = confirm("Do you confirm the deletion？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/dormRoom/delete",
                data: {"pkDormRoom": id},
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
    //Check in
//    function checin() {
    $("#checkInId").on("click",function () {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkDormRoom;
        if(id == null){
            alert("Please choose to stay in the dorm");
            return false;
        }
//        $("#checkInId").attr("href","/system/dormRoom/checkIn?pkDormRoom="+id);
        window.location.href="/system/dormRoom/checkIn?pkDormRoom="+id;
//        return true;
    });

    //out dorm
    $("#checkOutId").on("click",function () {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkDormRoom;
        if(id == null){
            alert("Please choose the dorm first");
            return false;
        }
//        $("#checkInId").attr("href","/system/dormRoom/checkIn?pkDormRoom="+id);
        window.location.href="/system/dormRoom/checkOut?pkDormRoom="+id;
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
