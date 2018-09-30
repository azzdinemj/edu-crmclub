
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Student fees <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

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
                            <#--<div class="form-group">-->
                                <#--<label>nationality</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw">-->
                                        <#--<option>whole</option>-->
                                        <#--<option>China</option>-->
                                        <#--<option>U.S.A</option>-->
                                        <#--<option>Britain</option>-->
                                        <#--<option>France</option>-->
                                        <#--<option></option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->

                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>search</span>
                               </div>-->
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
//        {"sTitle":"code","data" : "code","render": renderReceiptEdit},
//        {"sTitle":"Agent","data" : "map.employeeEntity.caption"},
//        {"sTitle":"Date,","data" : "date","render": renderDate},
//        {"sTitle":"Cost","data" : "cost"},
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
        init(GetTableColumn("receipt"),"/finance/studentCharge/queryByPaging",data,"pagingTable");
    }
</script>