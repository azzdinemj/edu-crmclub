<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Payable <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" id="chargeBtn">Settlement</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">


                <!--High search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">Date, :</label>
                        <input type="text" title="" readonly name="reservation" id="datas" class="form-control form-input-time" value="" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">code :</label>
                        <input type="text" title="" id="code" name="reservation"  class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
                    </div>

                </form>

            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable"  class="table table-striped table-bordered min-table" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                                <#--<th>Student</th>-->
                                <#--<th>item</th>-->
                                <#--<th>Agent</th>-->
                                <#--<th>code</th>-->
                                <#--<th>Date,</th>-->
                                <#--<th>Cost</th>-->
                                <#--<th>Receivables</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>Modifier</th>-->
                                <#--<th>Modified</th>-->
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
//        {"sTitle":"Student name","data":"map.studentEntity.caption"},
//        {"sTitle":"code","data" : "code","render": renderPayablesEdit},
//        {"sTitle":"Agent","data" : "map.employeeEntity.caption"},
//        {"sTitle":"Date,","data" : "date","render": renderDate},
//        {"sTitle":"Cost","data" : "cost"},
////        {"sTitle":"Already paid","data" : "money"},
////        {"sTitle":"status","data" : "status","render" : function (data){
////            if (data == 0){ return 'Unpaid';}
////            else {return 'Already paid'; }
////        }},
//        {"sTitle":"Founder","data" : "map.creatorEntity.caption"},
//        {"sTitle":"Creation time","data" : "creationDate","render": renderDate},
//        {"sTitle":"Modifier","data" : "map.modifierEntity.caption"},
//        {"sTitle":"Modified","data" : "lasteditDate","render": renderDate}
//    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dateTime": $("#datas").val(),"code":$("#code").val()};
        init(GetTableColumn("payables"),"/finance/payables/queryByPaging",data,"pagingTable");
    }


    $("#chargeBtn").on("click",function(){
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkPayables;
//        var status = getTableSelectRow(index).status;
        if (id == undefined || id == null || id == "") {
            Notify.danger("Please select a row of data!!!");
        } else {
            var flag = confirm("Do you confirm the settlement？");
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/finance/payables/refund",
                    data: {"pkPayables": id},
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