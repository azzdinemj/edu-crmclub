
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>refund <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" id="auditBtn">To examine</a>
                        <a class="btn btn-sm btn-newblue" id="retreatBtn">Reject</a>

                        <!--     <button title="operations" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="" data-toggle="modal">delete</a></li>
                        <li class="divider"></li>
                    </ul>-->
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
                            <div class="form-group">
                                <label class="control-label">Date, :</label>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">code :</label>
                                <input type="text" title="" id="code" name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <a onclick="query()" class="btn btn-newblue btn-sm search">search</a>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <#--<tr>-->
                                <#--<th>Student</th>-->
                                <#--&lt;#&ndash;<th>item</th>&ndash;&gt;-->
                                <#--<th>Agent</th>-->
                                <#--<th>code</th>-->
                                <#--<th>Date,</th>-->
                                <#--<th>Cost</th>-->
                                <#--<th>Receivables</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if receipt ??>-->
                                <#--<#list receipt as rec>-->
                            <#--<tr>-->
                               <#--&lt;#&ndash; <td>-->
                                    <#--<!--The three buttons are not displayed at the same time。。In accordance with the order，The detail button appears after the end of the payment&ndash;&gt;-->
                                    <#--<a class="btn btn-success btn-sm"  data-toggle="modal" data-target="#myModal" data-url="collection.html">-->
                                        <#--Receivables-->
                                    <#--</a>-->

                                    <#--<a class="hide btn btn-sm btn-info" data-toggle="modal" data-target="#myModal" data-url="pay.html">details</a>-->

                                    <#--<a class="hide btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">delete</a>-->

                                <#--</td>&ndash;&gt;-->
                                <#--<td> <a class="yellow" href="/finance/studentCharge/edit?pkReceipt=${(rec.pkReceipt)!}">${(rec.pkStudent)!} </a></td>-->
                                <#--<td>-->
                                <#--${(rec.pkExpenseItem)!}-->
                                <#--</td>-->
                                <#--<td>${(rec.pkSysUser)!}</td>-->
                                <#--<td>${(rec.code)!}</td>-->
                                <#--<td>${(rec.date?string("yyyy-MM-dd"))!}</td>-->
                                <#--<td>${(rec.cost)!}</td>-->
                                <#--<td>${(rec.money)!}</td>-->
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
//    var columns = [
//        {"sTitle":"Student name","data":"map.studentEntity.caption"},
//        {"sTitle":"code","data" : "pkPayment"} ,
//        {"sTitle":"Agent","data" : "map.employeeEntity.caption"},
//        {"sTitle":"Date,","data" : "date","render": renderDate},
//        {"sTitle":"Cost","data" : "money"},
////        {"sTitle":"Receivables","data" : "money"},
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
        init(GetTableColumn("payment"),"/finance/payment/queryByPaging",data,"pagingTable");
    }

    //To examine
    $("#auditBtn").on("click",function(){
        var index = $(".table-color-").index();
        if(index == null){
            Notify.danger("Please select a row of data!!!");
        }
        var pkPayment = getTableSelectRow("pagingTable",index).pkPayment;
//        $("#isauditHidden").val($("input[name='isaudit']:checked").val());
//        var d = {};
//        var t = $("#formSumbit").serializeArray();
//        $.each(t, function() {
//            d[this.name] = this.value;
//        });

        $.ajax({
            type:"POST",
            url:"/finance/payment/audit",
            data:{"pkPayment":pkPayment},
            dataType:"json",
            success:function(data) {
                console.log(JSON.stringify(data));
                if(data.code == 0){
                    Notify.success("success");
                    setTimeout("window.location.href='/finance/payment/query'",1);
                }else{
                    Notify.danger(data.message);
                }
            }
        })
    });

    //Reject
    $("#retreatBtn").on("click",function(){
//        var d = {};
//        var t = $("#formSumbit").serializeArray();
//        $.each(t, function() {
//            d[this.name] = this.value;
//        });

        var index = $(".table-color-").index();
        if(index == null){
            Notify.danger("Please select a row of data!!!");
        }
        var pkPayment = getTableSelectRow("pagingTable",index).pkPayment;
        $.ajax({
            type:"POST",
            url:"/finance/payment/retreat",
            data:{"pkPayment":pkPayment},
            dataType:"json",
            success:function(data) {
                console.log(JSON.stringify(data));
                if(data.code == 0){
                    Notify.success("Dismiss success");
                    setTimeout("window.location.href='/finance/payment/query'",1);
                }else{
                    Notify.danger(data.message);
                }
            }
        })
    });
</script>