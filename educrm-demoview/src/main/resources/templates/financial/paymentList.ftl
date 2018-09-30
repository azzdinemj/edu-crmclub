
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>学生退费 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" id="auditBtn">审核</a>
                        <a class="btn btn-sm btn-newblue" id="retreatBtn">驳回</a>

                        <!--     <button title="更多操作" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="" data-toggle="modal">删除</a></li>
                        <li class="divider"></li>
                    </ul>-->
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
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />
                            </div>
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">单号 :</label>-->
                                <#--<input type="text" title="" id="code" name="reservation"  class="form-control form-input-lg" value="" />-->
                            <#--</div>-->
                            <div class="form-group">
                                <a class="btn btn-newblue btn-sm search">搜索</a>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <#--<tr>-->
                                <#--<th>学生</th>-->
                                <#--&lt;#&ndash;<th>费用项目</th>&ndash;&gt;-->
                                <#--<th>经办人</th>-->
                                <#--<th>单号</th>-->
                                <#--<th>日期</th>-->
                                <#--<th>费用</th>-->
                                <#--<th>已收款</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if receipt ??>-->
                                <#--<#list receipt as rec>-->
                            <#--<tr>-->
                               <#--&lt;#&ndash; <td>-->
                                    <#--<!--三个按钮不同时显示。。按照顺序，收款结束后出现详情按钮&ndash;&gt;-->
                                    <#--<a class="btn btn-success btn-sm"  data-toggle="modal" data-target="#myModal" data-url="collection.html">-->
                                        <#--收款-->
                                    <#--</a>-->

                                    <#--<a class="hide btn btn-sm btn-info" data-toggle="modal" data-target="#myModal" data-url="pay.html">详情</a>-->

                                    <#--<a class="hide btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">删除</a>-->

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
//        {"sTitle":"学生姓名","data":"map.studentEntity.caption"},
//        {"sTitle":"单号","data" : "pkPayment"} ,
//        {"sTitle":"经办人","data" : "map.employeeEntity.caption"},
//        {"sTitle":"日期","data" : "date","render": renderDate},
//        {"sTitle":"费用","data" : "money"},
////        {"sTitle":"已收款","data" : "money"},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dataTime": $("#datas").val()};
        init(GetTableColumn("payment"),"/finance/payment/queryByPaging",data,"pagingTable");
    }

    //审核
    $("#auditBtn").on("click",function(){
        var index = $(".table-color-").index();
        if(index == null){
            Notify.danger("请选择一行数据!!!");
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
                    Notify.success("审核成功");
                    setTimeout("window.location.href='/finance/payment/query'",1);
                }else{
                    Notify.danger(data.message);
                }
            }
        })
    });

    //驳回
    $("#retreatBtn").on("click",function(){
//        var d = {};
//        var t = $("#formSumbit").serializeArray();
//        $.each(t, function() {
//            d[this.name] = this.value;
//        });

        var index = $(".table-color-").index();
        if(index == null){
            Notify.danger("请选择一行数据!!!");
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
                    Notify.success("驳回成功");
                    setTimeout("window.location.href='/finance/payment/query'",1);
                }else{
                    Notify.danger(data.message);
                }
            }
        })
    });
</script>