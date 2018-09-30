<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>退费 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
            <a class="btn btn-sm btn-newblue " id="savebtn" onClick="sub()">保存</a>
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>学生信息</strong></a></li>

            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">

                    <form class="form-horizontal no-margin form-ajax" id="formId" method="post"
                          action="/student/dropSchool/save"
                          data-target="/student/dropSchool/query">
                        <h3 style="text-align:center">
                            退学模式 :
                            <span class="rdio rdio-warning">
                                               <input name="type" value="1" class="deldis" onchange="delreadonly(this.value)"  id="radio11" type="radio">
                                               <label for="radio11">无退费退学</label>
                                           </span>
                            <span class="rdio rdio-warning">
                                               <input name="type" value="2" id="radio12" onchange="delreadonly(this.value)" class="deldis" type="radio">
                                               <label for="radio12">退费退学</label>
                                           </span>
                        </h3>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">



                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">退费学年</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select id="selectId" disabled="disabled" name="schoolYear" onchange="selectRecivable(this.value)">
                                            <option value="请选择学年"></option>
                                            <#if schoolYearList??>
                                            <#list schoolYearList as s >
                                                <option value="${(s.pkSysDictValues)!}">${(s.caption)!}</option>
                                            </#list>
                                            </#if>
                                        </select>
                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">



                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">学生姓名</label>
                                    <div class="col-xs-9 col-md-9">
                                           <input id="stuCaption" readonly value="${(student.caption)!}" class="form-control"  >
                                           <input id="pkStudent" name="dro.pkStudent" value="${(student.pkStudent)!}" type="hidden">
                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">总额</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="dro.cost" readonly id="costId" class="form-control" value=""
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">优惠额度</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="dro.discount" readonly id="discountId" readonly class="form-control sum deldou" value=""
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">应收总额</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="dro.receivableCost" readonly id="receivableCostId" readonly class="form-control sum deldou" value=""
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">已收款</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="dro.receipts" readonly id="receiptsId" readonly class="form-control sum deldou" value=""
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">退费比例(%)</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="dro.proportionId" readonly id="proportionId" onchange="assignment(this.value)" class="form-control"
                                               value="" title="" type="number">
                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label id="lableId" class="control-label col-xs-3 col-md-3">应退金额</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="dro.businessCost" readonly id="businessCostId" class="form-control" value="" title="" type="text">
                                        <input name="dro.businessType" id="businessTypeId" class="form-control" value="" title="" type="hidden">
                                    </div><!-- /.col -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                            </div>
                        </div>


                        <h3>费用明细</h3>

                    <#-- <div class="table-responsive">-->


                        <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0" cellpadding="0">
                            <thead>

                            </thead>
                            <tbody id="parentTab">
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

    //保存
    function sub() {
        $("#formId").submit();
    }
    function renderCaption(data, type, row) {
        if (data == 0){
            return "否";
        }else if(data ==1){
            return "是";
        }else {
            return "未定义";
        }
    }

    function selectRecivable(obj) {
        var id = $("#pkStudent").val();


            var data = { "pkStudent": id,"schoolYear":obj};
        init(GetTableColumn("receiptModel"), "/finance/studentCharge/queryByPagingForDrop", data,"pagingTable");

        if (obj != null && obj !=""){
            $.ajax({
                type: "POST",
                url: "/student/studentReport/getCost",
                data: {"pkStudent": id,"schoolYear":obj},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $("#costId").val(data.data.mny);
                        $("#discountId").val(data.data.discount);
                        $("#receiptsId").val(data.data.receipts);
                        $("#receivableCostId").val(data.data.money);
                    } else {
                        alert(data.message);
                        window.location.reload();
                    }
                },
                error: function () {

                }
            });
        }
    }
    $(function () {
        $("#radio12").attr("checked","checked");
        delreadonly(2);
    });
    function assignment(obj) {
        if (obj != null && obj !=""){
            var num = 0.00;  //不计入退费的金额
            $('#parentTab tr').each(function(i){
//                $(this).children('td').each(function(j){  // 遍历 tr 的各个 td
//
//                    alert("第"+(i+1)+"行，第"+(j+1)+"个td的值："+$(this).text()+"。");
//                          });
                if ($(this).find("td").eq(0).text() =="否"){
                    num = num +parseFloat($(this).find("td").eq(2).text())
                }
            });

            var cost =parseFloat($("#costId").val());//总额
            var receipts = parseFloat($("#receiptsId").val()); //已收款
            var businessCostAll= parseFloat(((cost-num )* obj /100).toFixed(2));//保留两位小数 满额应退金额
            var businessCost = businessCostAll + receipts -cost + num;
            if (businessCost <0){
               $("#lableId").html("应加收") ;
               businessCost = Math.abs(businessCost);
               $("#businessTypeId").val(0);
            }else {
                $("#businessTypeId").val(1);
            }
            $("#businessCostId").val(businessCost);
        }
    }

    function delreadonly(obj) {
        if (obj ==2){
            $("#selectId").removeAttr("disabled");
            $("#proportionId").removeAttr("readOnly");
            $("#businessCostId").removeAttr("readOnly");
        }
        if (obj ==1){
            $("#selectId").attr("disabled","disabled");
            $("#proportionId").attr("readOnly","readOnly");
            $("#businessCostId").attr("readOnly","readOnly");

            $("#costId").val("");
            $("#discountId").val("");
            $("#receiptsId").val("");
            $("#receivableCostId").val("");
            $("#proportionId").val("");
            $("#businessCostId").val("");
            $("#selectId").find("option").eq(0).attr("selected","selected");
        }
    }
</script>




