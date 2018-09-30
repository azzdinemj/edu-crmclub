<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>应收款 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/finance/receivable/create">新增</a>
                <a class="btn btn-sm btn-newblue" id="chargeBtn">收款</a>
                <a class="btn btn-sm btn-newblue" id="refundBtn"  data-toggle="modal" data-target="#modal" data-url="/finance/receivable/getReceiptList">退款</a>
                <input type="hidden" id="refundUrl" value="/finance/receivable/getReceiptList">
            <#--<a class="btn btn-sm btn-newblue" id="chargeBtn">删除</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <div class="panel-btns">
                    <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                </div><!-- panel-btns -->
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
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>
                <#--<div class="form-group">-->
                <#--<label>国籍</label>-->
                <#--<div class="btn-group">-->
                <#--<select class="form-control strw">-->
                <#--<option>全部</option>-->
                <#--<option>中国</option>-->
                <#--<option>美国</option>-->
                <#--<option>英国</option>-->
                <#--<option>法国</option>-->
                <#--<option></option>-->
                <#--</select>-->
                <#--</div>-->
                <#--</div>-->

                    <!--   <div class="form-group">
                           <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                       </div>-->
                </form>
                <!--隐藏搜索-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                    <#--<form class="form-inline search white">-->
                                    <#--<div class="form-group">-->
                                        <#--<label>学部</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>全部</option>-->
                                                <#--<option>一部</option>-->
                                                <#--<option>二部</option>-->
                                            <#--</select>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">家长姓名 :</label>-->
                                        <#--<input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">入学年级 :</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>全部</option>-->
                                                <#--<option>一年级</option>-->
                                                <#--<option>二年级</option>-->
                                                <#--<option>三年级</option>-->
                                                <#--<option>四年级</option>-->
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
//        {"sTitle":"单号","data" : "code"
////            ,"render": function (data, type, row){
////                return '<a href="/finance/receivable/edit?pkReceivable='+row.pkReceivable+'" class="yellow" >'+data+'</a>';
////            }
//            } ,
//        {"sTitle":"经办人","data" : "map.employeeEntity.caption"},
//        {"sTitle":"日期","data" : "date","render": renderDate},
//        {"sTitle":"费用","data" : "cost"},
//        {"sTitle":"已收款","data" : "money"},
//        {"sTitle":"收款状态","data" : "status","render" : renderReceivable},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dataTime": $("#datas").val(),"code":$("#code").val()};
        init(GetTableColumn("receivable"),"/finance/receivable/queryByPaging",data,"pagingTable");
    }


    $("#chargeBtn").on("click",function(){
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkReceivable;
        var status = getTableSelectRow("pagingTable",index).status;
        if (id == undefined || id == null || id == "") {
            Notify.danger("请选择一行数据!!!");
        } else {
            if(status == 0) {
                window.location.href = "/finance/receivable/setCollection?pkReceivable=" + id;
            }else{
                Notify.danger("已支付,不可重复支付!!!");
            }
        }
    })

    $("#refundBtn").on("click",function(){
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkReceivable;
        var studentId = getTableSelectRow("pagingTable",index).pkStudent;

        var result = "?pkReceivable="+id+"&pkStudent="+studentId;

        if (id == undefined || id == null || id == "") {
            Notify.danger("请选择一行数据!!!");
            return false;
        } else {
            $(this).attr("data-url",$("#refundUrl").val()+result);
            return true;
        }
    })
</script>