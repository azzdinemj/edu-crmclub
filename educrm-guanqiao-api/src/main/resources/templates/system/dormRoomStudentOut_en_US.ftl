<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>Tuisu <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
            <a class="btn btn-sm btn-newblue " id="savebtn" onClick="submit()">Check in</a>
            <#--<a class="btn btn-sm btn-newblue " id="submitbtn" onClick="issubmit()">Submission</a>-->
            <#--<a class="btn btn-sm btn-newblue " id="auditbtn" onClick="isaudit()">To examine</a>-->
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>Dormitory information</strong></a></li>

            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">
                    <#--<form class="form-inline search white">-->
                        <#--<div class="form-group">-->
                            <#--<label class="control-label col-xs-4">Class name :</label>-->
                            <#--<div class="col-xs-8">-->
                            <#--&lt;#&ndash;<input id="code" name="name" class="form-control" type="text">&ndash;&gt;-->
                                <#--<select id="pkClassinfoId" class="selectpicker show-tick form-control"  data-live-search="true">-->
                                    <#--<option value="">Please choose</option>-->
                                <#--<#if classinfo??>-->
                                    <#--<#list classinfo as c>-->
                                        <#--<option value="${(c.pkClassinfo)!}">${(c.caption)!}</option>-->
                                    <#--</#list>-->
                                <#--</#if>-->
                                <#--</select>-->
                            <#--</div>-->
                        <#--</div>-->
                        <#--<div class="form-group">-->
                            <#--<label class="control-label">Student name :</label>-->
                            <#--<input type="text" title="" id="caption"  name="caption"  class="form-control form-input-lg" />-->
                        <#--</div>-->
                        <#--<div class="form-group">-->
                            <#--<a class="btn btn-newblue btn-sm search" onclick="query()">search</a>-->
                        <#--</div>-->

                    <#--</form>-->
                    <form class="form-horizontal no-margin form-ajax" id="formId" method="post"
                          action="/student/studentReport/saveall"
                          data-target="/student/studentSignup/query">
                        <input type="hidden" id="pkStudentSignup" name="dorm.dormRoom" value="${(dormRoom.pkDormRoom)!}">
                        <input type="hidden" name="dorm.pkDomain"  value="${(dormRoom.pkDomain)!}">
                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">name</label>
                                    <div class="col-xs-9 col-md-9">

                                        <input class="form-control" readonly name="dorm.caption"  value="${(dormRoom.caption)!}" type="text">

                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Can live</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="dorm.num"  class="form-control" value="${(dormRoom.num)!0}" title="">
                                    </div>
                                </div>

                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Resident number</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="dorm.currentNum" class="form-control"
                                               value="${(dormRoom.currentNum)!0}" title="" readonly>
                                    </div><!-- /.col -->
                                </div>



                            </div>
                        </div>

                        <table id="pagingTable"  class="table table1-striped table-bordered" cellspacing="0">
                            <thead>

                            </thead>
                            <tbody>
                            </tbody>
                        </table>

                        <#--<div class="row">-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group ">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">submitter</label>-->
                                    <#--<div class="col-lg-9 ">-->
                                        <#--<input readonly class="form-control" value="${(dormRoom.submitor)!}"-->
                                               <#--type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Submitted</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly class="form-control"-->
                                               <#--value="${(student.submitDate?string("yyyy-MM-dd HH:mm:ss"))!}"-->
                                               <#--type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">is submit</label>-->

                                    <#--<div class="col-xs-9 col-md-9 pt7">-->
                                       <#--<span class="rdio rdio-success">-->
                                           <#--<input name="stu.issubmit" value="1" disabled class="deldis"-->
                                                  <#--&lt;#&ndash;<#if student.issubmit??&& student.issubmit ==1 >checked="checked"</#if>&ndash;&gt;-->
                                                  <#--id="radio1" type="radio">-->
                                           <#--<label disabled="disabled" for="radio1">是</label>-->
                                       <#--</span>-->
                                        <#--<span class="rdio rdio-success">-->
                                           <#--<input name="stu.issubmit" value="0" id="radio2" disabled class="deldis"-->
                                                  <#--&lt;#&ndash;<#if student.issubmit??&& student.issubmit ==1 ><#else >checked="checked"</#if>&ndash;&gt;-->
                                                  <#--type="radio">-->
                                           <#--<label for="radio2">否</label>-->
                                       <#--</span>-->
                                    <#--</div>-->

                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                        <#--<div class="row">-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group ">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Auditor</label>-->
                                    <#--<div class="col-lg-9 ">-->
                                        <#--<input readonly class="form-control" value="${(student.auditor)!}"-->
                                               <#--type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Audit time</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly class="form-control"-->
                                               <#--value="${(student.auditDate?string("yyyy-MM-dd HH:mm:ss"))!}"-->
                                               <#--type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">is review</label>-->

                                    <#--<div class="col-xs-9 col-md-9 pt7">-->
                                       <#--<span class="rdio rdio-success">-->
                                           <#--<input name="stu.issubmit" value="1" disabled class="deldis"-->
                                                  <#--&lt;#&ndash;<#if student.isaudit??&& student.isaudit ==1 >checked="checked"</#if>&ndash;&gt;-->
                                                  <#--id="radio3" type="radio">-->
                                           <#--<label disabled="disabled" for="radio3">是</label>-->
                                       <#--</span>-->
                                        <#--<span class="rdio rdio-success">-->
                                           <#--<input name="stu.issubmit" value="0" id="radio4" disabled class="deldis"-->
                                                  <#--&lt;#&ndash;<#if student.isaudit??&& student.isaudit ==1 ><#else >checked="checked"</#if>&ndash;&gt;-->
                                                  <#--type="radio">-->
                                           <#--<label for="radio4">否</label>-->
                                       <#--</span>-->
                                    <#--</div>-->

                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                        <#--<div class="row">-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group ">-->
                                    <#--<label class="control-label  col-xs-3 col-md-3">Founder</label>-->
                                    <#--<div class="col-xs-9 col-md-9 ">-->
                                        <#--<input  readonly class="form-control" value="${(student.map.creatorEntity.caption)!}"-->
                                               <#--type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Creation time</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly class="form-control"-->
                                               <#--value="${(student.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}"-->
                                               <#--type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                        <#--<div class="row">-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group ">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Modifier</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input  readonly class="form-control" value="${(student.map.modifierEntity.caption)!}"-->
                                               <#--type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->

                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Modified</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly class="form-control"-->
                                               <#--value="${(student.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}"-->
                                               <#--type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->



                    </form>

                </div><!-- tab-content -->


            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->
<#include "../commons/footer.ftl" >

<script type="text/javascript">

    var columns = [
        {"sTitle":"Choice","data" : "pkStudent",
            "render": function (data, type, row){
                return '<input class="checkboxAll" type="checkbox" value="'+data+'" caption="'+row.caption+'" class="px">';
            }} ,
        {"sTitle":"number","data":"code"},
        {"sTitle":"name","data" : "caption"} ,
        {"sTitle":"Sex","data" : "sex","render": function (data, type, row){
            if (data==1){ return '男';}
            else {return '女'; }
        }} ,
//        {"sTitle":"class","data" : "sex"},
        {"sTitle":"grade","data" : "map.gradeEntity.caption"},
        {"sTitle":"Ethnic","data" : "map.nationEntity.caption"}
    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "pkClassinfo": $("#pkClassinfoId").val(),"caption":$("#caption").val(),"dormRoom":"false"};
        init(columns,"/system/dormRoomStudent/edit",data,"pagingTable");
    }


    function submit() {

        var result = new Array();

        $.each($('input:checkbox:checked'),function(){
//            window.alert("You chose it："+
//                    $('input[type=checkbox]:checked').length+"个，One of them is："+$(this).val());
            result.push($(this).attr("value"));
        });
        pkStudents = JSON.stringify(result);
        var num = ${(dormRoom.num)!0};
        var currentNum = ${(dormRoom.currentNum)!0};
        var s = num - currentNum;
    <#--number num = ${(dormRoom.num)!0}-${(dormRoom.currentNum)!0};-->
//        alert(s);
        if(result.length > s){
            alert("The dorm can only live"+num+"人");
            return;
        }
        $.ajax( {
            type: "POST",
            url: "/system/dormRoomStudent/save",
            data: {"pkStudents": pkStudents,"pkDormRoom":"${(dormRoom.pkDormRoom)!}"},
            dataType: "json",
            traditional: true,
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    setTimeout("location.reload()", 1);
                } else {
                    Notify.danger(data.message);
                    window.location.reload();
                }
            }
        })


    }

    $("#pkClassinfoId").change(function () {
        query();
    })

</script>


