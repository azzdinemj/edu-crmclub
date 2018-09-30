<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>Ride bus <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
            <a class="btn btn-sm btn-newblue " id="savebtn" onClick="submit()">ride</a>
            <#--<a class="btn btn-sm btn-newblue " id="submitbtn" onClick="issubmit()">Submission</a>-->
            <#--<a class="btn btn-sm btn-newblue " id="auditbtn" onClick="isaudit()">To examine</a>-->
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>bus information</strong></a></li>

            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">
                    <form class="form-inline search white">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Class name :</label>
                            <div class="col-xs-8">
                            <#--<input id="code" name="name" class="form-control" type="text">-->
                                <select id="pkClassinfoId" class="selectpicker show-tick form-control"  data-live-search="true">
                                    <option value="">Please choose</option>
                                <#if classinfo??>
                                    <#list classinfo as c>
                                        <option value="${(c.pkClassinfo)!}">${(c.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Student name :</label>
                            <input type="text" title="" id="caption"  name="caption"  class="form-control form-input-lg" />
                        </div>
                        <div class="form-group">
                            <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
                        </div>

                    </form>
                    <form class="form-horizontal no-margin form-ajax" id="formId" method="post"
                          action="/student/studentReport/saveall"
                          data-target="/student/studentSignup/query">
                        <input type="hidden" id="pkStudentSignup" name="dorm.dormRoom" value="${(schoolBus.pkSchoolBus)!}">
                        <input type="hidden" name="dorm.pkDomain"  value="${(schoolBus.pkDomain)!}">
                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">name</label>
                                    <div class="col-xs-9 col-md-9">

                                        <input class="form-control" readonly name="dorm.caption"  value="${(schoolBus.caption)!}" type="text">

                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Full load</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="dorm.num"  class="form-control" value="${(schoolBus.num)!0}" title="">
                                    </div>
                                </div>

                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Existing numbers</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="dorm.currentNum" class="form-control"
                                               value="${(schoolBus.currentNum)!0}" title="" readonly>
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
                    </form>

                </div><!-- tab-content -->


            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->
<#include "../commons/footer.ftl" >

<script type="text/javascript">

//    var columns = [
//        {"sTitle":"Choice","data" : "pkStudent",
//            "render": function (data, type, row){
//                return '<input class="checkboxAll" type="checkbox" value="'+data+'" caption="'+row.caption+'" class="px">';
//            }} ,
//        {"sTitle":"number","data":"code"},
//        {"sTitle":"name","data" : "caption"} ,
//        {"sTitle":"Sex","data" : "sex","render": function (data, type, row){
//            if (data==1){ return '男';}
//            else {return '女'; }
//        }} ,
////        {"sTitle":"class","data" : "sex"},
//        {"sTitle":"grade","data" : "map.gradeEntity.caption"},
//        {"sTitle":"Ethnic","data" : "map.nationEntity.caption"}
//    ];

    $(document).ready(function() {
        query();
    } );

    function renderCaption(data, type, row) {

        return '<input class="checkboxAll" type="checkbox" value="'+data+'" caption="'+row.caption+'" class="px">';
    }

    function query() {
        var data  = { "pkClassinfo": $("#pkClassinfoId").val(),"schoolBus":"false","caption":$("#caption").val()};
        init(GetTableColumn("dormStudentIn"),"/student/studentSignup/queryByPaging",data,"pagingTable");
    }


    function submit() {

        var result = new Array();

        $.each($('input:checkbox:checked'),function(){
            result.push($(this).attr("value"));
        });
        pkStudents = JSON.stringify(result);
        var num = ${(schoolBus.num)!0};
        var currentNum = ${(schoolBus.currentNum)!0};
        var s = num - currentNum;
        if(result.length > s){
            alert("The school bus can only sit"+num+"人");
            return;
        }
        $.ajax( {
            type: "POST",
            url: "/system/schoolBusStudent/save",
            data: {"pkStudents": pkStudents,"pkSchoolBus":"${(schoolBus.pkSchoolBus)!}"},
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


