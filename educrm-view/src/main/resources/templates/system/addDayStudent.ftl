<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>设置走读学生 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
            <a class="btn btn-sm btn-newblue " id="savebtn" onClick="submit()">保存</a>
            <#--<a class="btn btn-sm btn-newblue " id="submitbtn" onClick="issubmit()">提交</a>-->
            <#--<a class="btn btn-sm btn-newblue " id="auditbtn" onClick="isaudit()">审核</a>-->
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>配置信息</strong></a></li>

            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">
                    <form class="form-inline search white">
                        <div class="form-group">
                            <label class="control-label col-xs-4">班级名称 :</label>
                            <div class="col-xs-8">
                            <#--<input id="code" name="name" class="form-control" type="text">-->
                                <select id="pkClassinfoId" class="selectpicker show-tick form-control"  data-live-search="true">
                                    <option value="">请选择</option>
                                <#if classinfoList??>
                                    <#list classinfoList as c>
                                        <option value="${(c.id)!}">${(c.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">学生姓名 :</label>
                            <input type="text" title="" id="caption"  name="caption"  class="form-control form-input-lg" />
                        </div>
                        <div class="form-group">
                            <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                        </div>

                    </form>
                    <form class="form-horizontal no-margin form-ajax" id="formId" method="post"
                          action="/system/dayStudent/saveAll"
                          data-target="/system/dayStudent/query">
                        <input class="form-control" name="pkStudents" id="pkStudentsId"  value="" type="hidden">
                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">走读时间</label>
                                    <div class="col-xs-9 col-md-9">

                                        <input id="deliveryDateTimeId" class="form-control js-datepickertime" name="deliveryDateTime"  value="" type="text">

                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="type"  class="form-control" id="typeId">
                                            <option value="">请选择</option>
                                            <option value="0">上午</option>
                                            <option value="1">下午</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">是否用餐</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="eatType" class="form-control" id="eatTypeId">
                                            <option value="">请选择</option>
                                            <option value="0">否</option>
                                            <option value="1">是</option>
                                        </select>
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


    function renderCaption(data, type, row) {

        return '<input class="checkboxAll" type="checkbox" value="'+data+'" caption="'+row.caption+'" class="px">';
    }

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "pkClassinfo": $("#pkClassinfoId").val(),"caption":$("#caption").val()};
        init(GetTableColumn("dormStudentIn"),"/student/studentSignup/queryByPaging",data,"pagingTable");
    }


    function submit() {

        var deliveryDateTimeId = $("#deliveryDateTimeId").val();
        var typeId = $("#typeId").val();
        var eatTypeId = $("#eatTypeId").val();

        if (deliveryDateTimeId == ""){
            Notify.danger("请选择时间");
            return;
        }
        if(typeId== ""){
            Notify.danger("请选择时间类型");
            return;
        }
        if (eatTypeId ==""){
            Notify.danger("请选择是否用餐");
            return;
        }
        var result = new Array();

        $.each($('input:checkbox:checked'),function(){

            result.push($(this).attr("value"));
        });
        pkStudents = JSON.stringify(result);

        $("#pkStudentsId").val(pkStudents);

        $("#formId").submit();

        <#--$.ajax( {-->
            <#--type: "POST",-->
            <#--url: "/system/dormRoomStudent/save",-->
            <#--data: {"pkStudents": pkStudents,"pkDormRoom":"${(dormRoom.pkDormRoom)!}"},-->
            <#--dataType: "json",-->
            <#--traditional: true,-->
            <#--success: function (data) {-->
                <#--if (data.code == 0) {-->
                    <#--Notify.success(data.message);-->
                    <#--setTimeout("location.reload()", 1);-->
                <#--} else {-->
                    <#--Notify.danger(data.message);-->
                    <#--window.location.reload();-->
                <#--}-->
            <#--}-->
        <#--})-->


    }

    $("#pkClassinfoId").change(function () {
        query();
    })

</script>


