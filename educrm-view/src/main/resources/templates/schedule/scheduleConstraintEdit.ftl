<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />


<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>排课约束 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                    <a class="btn btn-sm btn-newblue" onclick="sub()">保存 </a>

            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-yellow">

            </ul>
            <form class="look form-horizontal no-margin form-ajax" id="formId" method="post" action="/schedule/scheduleConstraint/save"
                  data-target="/schedule/scheduleConstraint/query">
                <div class="tab-content">
                    <div id="all" class="tab-pane active">
                        <input name="clas.pkDomain" value="${(scheduleConstraint.constraintId)!}" type="hidden">

                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select id="typeId" name="con.type" disabled="disabled" style="background-color: #EEEEEE;" onchange="getSelectData(this.value)">
                                            <option value="0" <#if scheduleConstraint ?? && scheduleConstraint.type?? &&scheduleConstraint.type ==0>selected</#if>>校区</option>
                                            <option value="1"  <#if scheduleConstraint ?? && scheduleConstraint.type?? &&scheduleConstraint.type ==1>selected</#if>>教师</option>
                                        </select>
                                    </div>

                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">约束对象</label>
                                    <div class="col-xs-9 col-md-9">
                                            <input class="form-control" readonly name="clas.targetId" id="targetId" value="${(scheduleConstraint.targetId)!}" title="" type="hidden" >
                                            <input class="form-control" readonly  id="targetName" value="${(scheduleConstraint.targetName)!}" title="" type="text">


                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">开始时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="con.startDateTime" value="${(scheduleConstraint.startDate?string("yyyy-MM-dd"))!}"  type="text" title="" class="form-control js-datetimepicker" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">结束时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="con.endDateTime" value="${(scheduleConstraint.endDate?string("yyyy-MM-dd"))!}" class="form-control js-datetimepicker" >
                                    </div>
                                </div>
                            </div>


                        </div>

                    </div>


                    <div id="teacher" class="tab-pane">

                <#--  </div>-->
                </div>

                </div>
            </form>
        </div>
    </div>

</div><!-- panel -->

<#include "../commons/footer.ftl"/>

<script type="text/javascript">
//    $(".selectpicker").selectpicker({
//        noneSelectedText : '请选择'//默认显示内容
//    });
//
//
//    function getSelectData(typeId) {
//        var url = "";
//        var op=document.getElementById("selectId");
//        op.options.length=0;
//        if (typeId ==0){
//            url="/schedule/scheduleConstraint/findDomain";
//        }else {
//            url = "/schedule/scheduleConstraint/getEmployees?jobPost=1";
//        }
//        $.ajax({
//            type: "GET",
//            url: url,
//            data: {},
//            dataType: "json",
//            success:function (data) {
//                var arr = data.data;
//                if (data.code==0){
//                    $.each(arr,function (i) {
//                        if (typeId ==0){
//                            var oOption  = new Option(arr[i].caption,arr[i].pkDomain);
//                        }else {
//                            var oOption  = new Option(arr[i].caption,arr[i].pkEmployee);
//                        }
//                        document.getElementById("selectId")[i]=oOption;
//                    });
//                    $('.selectpicker').selectpicker('refresh');
//                }
//            }
//        });
//
//    }
//    $(document).ready(function () {
//
//        var typeId = $("#typeId").val();
//       getSelectData(typeId);
//    });
//
    function sub() {
        $("#formId").submit();
    }

//    function renderType(data) {
//        if (data==0){
//            return "校区"
//        }else if(data==1){
//            return "教师";
//        }else {
//            return "";
//        }
//    }



</script>