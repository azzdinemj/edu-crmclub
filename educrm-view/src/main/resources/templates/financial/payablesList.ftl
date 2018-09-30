<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>应付款 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" id="chargeBtn"  data-toggle="modal" data-target="#modal"  >结算</a>
                <a class="btn btn-sm btn-newblue" id="retreatBtn">驳回</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">


                <!--高显搜索-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title="" readonly name="reservation" id="datas" class="form-control form-input-time" value="" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">单号 :</label>
                        <input type="text" title="" id="code" name="reservation"  class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">学生姓名 :</label>
                        <input type="text" title="" id="caption" name="reservation"  class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>

                </form>

            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable"  class="table table-striped table-bordered min-table" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                                <#--<th>学生</th>-->
                                <#--<th>费用项目</th>-->
                                <#--<th>经办人</th>-->
                                <#--<th>单号</th>-->
                                <#--<th>日期</th>-->
                                <#--<th>费用</th>-->
                                <#--<th>已收款</th>-->
                                <#--<th>创建人</th>-->
                                <#--<th>创建时间</th>-->
                                <#--<th>修改人</th>-->
                                <#--<th>修改时间</th>-->
                            <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#if list??>-->
                            <#--<#list list as v>-->
                            <#--<tr data-url="${(v.pkReceivable)!}">-->
                                <#--<td align="left"> <a class="yellow" href="/finance/receivable/edit?pkReceivable=${(v.pkReceivable)!}">${(v.pkStudent)!} </a></td>-->
                                <#--<td align="left">-->
                                <#--${(v.pkExpenseItem)!}-->
                                <#--</td>-->
                                <#--<td align="left">${(v.pkSysUser)!}</td>-->
                                <#--<td align="left">${(v.code)!}</td>-->
                                <#--<td align="left">${(v.date?string("yyyy-MM-dd"))!}</td>-->
                                <#--<td align="right">${(v.cost)!}</td>-->
                                <#--<td align="right">${(v.money)!}</td>-->
                                <#--<td align="left">${(v.creator)!}</td>-->
                                <#--<td align="right">${v.creationDate ? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                                <#--<td align="left">${(v.modifier)!}</td>-->
                                <#--<td align="right">${v.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
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
<script>

//    var columns = [
//        {"sTitle":"学生姓名","data":"map.studentEntity.caption"},
//        {"sTitle":"单号","data" : "code","render": renderPayablesEdit},
//        {"sTitle":"经办人","data" : "map.employeeEntity.caption"},
//        {"sTitle":"日期","data" : "date","render": renderDate},
//        {"sTitle":"费用","data" : "cost"},
////        {"sTitle":"已付款","data" : "money"},
////        {"sTitle":"付款状态","data" : "status","render" : function (data){
////            if (data == 0){ return '未支付';}
////            else {return '已支付'; }
////        }},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dateTime": $("#datas").val(),"code":$("#code").val(),"caption":$("#caption").val()};
        init(GetTableColumn("payables"),"/finance/payables/queryByPaging",data,"pagingTable");
    }


    $("#chargeBtn").on("click",function(){
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkPayables;
        var status = getTableSelectRow("pagingTable",index).status;
        if (id == undefined || id == null || id == "") {
            Notify.danger("请选择一行数据!!!");
            return false;
        }else if(status == 1){
            Notify.danger("已驳回的不可结算！！！");
            return false;
        } else if(status == 2){
            Notify.danger("不可重复结算！！！");
            return false;
        } else {
            var flag = confirm("确认结算吗？");

//            data-url="/classinfo/classinfo/getEmployeeList";

            if (flag) {
                $("#chargeBtn").attr("data-url","/finance/payables/stuRefund?pkPayables="+id);
                return true;

//                $.ajax({
//                    type: "POST",
//                    url: "/finance/payables/refund",
//                    data: {"pkPayables": id},
//                    dataType: "json",
//                    success: function (data) {
//                        if (data.code == 0) {
//                            Notify.success(data.message);
//                            setTimeout("location.reload()", 1);
//                        } else {
//                            Notify.danger(data.message)
//                        }
//                    }
//                });
            }else {
                return false;
            }
        }
    })

    $("#retreatBtn").on("click",function(){
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkPayables;
        var status = getTableSelectRow("pagingTable",index).status;
        if (id == undefined || id == null || id == "") {
            Notify.danger("请选择一行数据!!!");
        }else if(status == 1){
            Notify.danger("不可重复驳回！！！");
        } else if(status == 2){
            Notify.danger("已退费不可驳回！！！");
        } else {
            var flag = confirm("确认驳回吗？");
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/finance/payables/save",
                    data: {"pkPayables": id,"status":1},
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            Notify.success(data.message);
                            setTimeout("location.reload()", 1);
                        } else {
                            Notify.danger(data.message)
                        }
                    }
                });
            }
        }
    })
</script>