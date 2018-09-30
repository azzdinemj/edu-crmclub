
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Shift record <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" id="auditBtn" data-toggle="modal" data-target="#modal" data-url="/student/studentShift/setFinancial">To examine</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <#--<div class="panel-btns">-->
                            <#--<a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>-->
                        <#--</div><!-- panel-btns &ndash;&gt;-->
                        <!--High search-->
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">Date, :</label>-->
                                <#--<input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />-->
                            <#--</div>-->
                            <#--&lt;#&ndash;<div class="form-group">&ndash;&gt;-->
                                <#--&lt;#&ndash;<label class="control-label">code :</label>&ndash;&gt;-->
                                <#--&lt;#&ndash;<input type="text" title="" id="code" name="reservation"  class="form-control form-input-lg" value="" />&ndash;&gt;-->
                            <#--&lt;#&ndash;</div>&ndash;&gt;-->
                            <#--<div class="form-group">-->
                                <#--<a class="btn btn-newblue btn-sm search">search</a>-->
                            <#--</div>-->
                        <#--</form>-->
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
    var columns = [
        {"sTitle":"number","data":"pkStudentShift"},
        {"sTitle":"Student name","data":"map.studentEntity.caption"},
        {"sTitle":"old class","data" : "map.parentClassInfoEntity.caption"} ,
        {"sTitle":"new class","data" : "map.classInfoEntity.caption"},
        {"sTitle":"state","data" : "isvalid","render":renderStudentShiftStatus},
        {"sTitle":"Founder","data" : "map.creatorEntity.caption"},
        {"sTitle":"Creation time","data" : "creationDate","render": renderDate},
        {"sTitle":"Modifier","data" : "map.modifierEntity.caption"},
        {"sTitle":"Modified","data" : "lasteditDate","render": renderDate}
    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = {};
        init(GetTableColumn("studentShift"),"/student/studentShift/queryByPaging",data,"pagingTable");
    }

    //To examine
    $("#auditBtn").on("click",function(){
        var index = $(".table-color-").index();
        if(index == null){
            Notify.danger("Please select a row of data!!!");
            return false;
        }

        var pkStudentShift = getTableSelectRow("pagingTable",index).pkStudentShift;
        var status = getTableSelectRow("pagingTable",index).isvalid;
        if(status != 2){
            $("#auditBtn").attr("data-url",$("#auditBtn").attr("data-url")+"?pkStudentShift="+pkStudentShift);
        }else{
            Notify.danger("Non repeating submission!!!");
            return false;
        }

    });

</script>