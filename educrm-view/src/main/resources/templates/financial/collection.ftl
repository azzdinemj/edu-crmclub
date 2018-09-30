<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>收款单<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
            <#if receivable.pkReceipt??>
                <#else >
                    <a class="btn btn-sm btn-newblue" id="saveBtn">保存</a>
            </#if>
            <#--<#if receivable.pkReceipt??>-->
                    <#--<#if receivable.issubmit?? &&  receivable.issubmit == 1>-->
                        <#--<a class="btn btn-sm btn-newblue" id="submitBtn" disabled="disabled">提交</a>-->
                    <#--<#else >-->
                        <#--<a class="btn btn-sm btn-newblue" id="submitBtn">提交</a>-->
                    <#--</#if>-->
                    <#--<#if receivable.isaudit?? &&  receivable.isaudit == 1>-->
                        <#--<a class="btn btn-sm btn-newblue" id="auditBtn" disabled="disabled">审核</a>-->
                        <#--<a class="btn btn-sm btn-newblue" id="retreatBtn" disabled="disabled">驳回</a>-->
                    <#--<#else >-->
                        <#--<a class="btn btn-sm btn-newblue" id="auditBtn">审核</a>-->
                        <#--<a class="btn btn-sm btn-newblue" id="retreatBtn">驳回</a>-->
                    <#--</#if>-->
                <#--<#else >-->
                    <#--<#if receivable.issubmit?? &&  receivable.issubmit == 1>-->
                    <#--<#else >-->
                        <#--<a class="btn btn-sm btn-newblue" id="submitBtn" >提交</a>-->
                    <#--</#if>-->
                    <#--<#if receivable.isaudit?? &&  receivable.isaudit == 1>-->
                    <#--<#else >-->
                        <#--<a class="btn btn-sm btn-newblue" id="auditBtn" >审核</a>-->
                    <#--</#if>-->
                <#--</#if>-->


            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <form class="look form-horizontal" id="formSumbit" data-validate="parsley" novalidate="">
                <input type="hidden" name="recp.pkReceipt" class="form-control" value="${(receivable.pkReceipt)!}">
                <input type="hidden" name="recp.pkDomain" class="form-control" value="${(receivable.pkDomain)!}">
                <#if receivable.pkReceipt??>
                <input type="hidden" name="recp.pkParent" class="form-control" value="${(receivable.pkParent)!}">
                <#else >
                <input type="hidden" name="recp.pkParent" class="form-control" value="${(receivable.pkReceivable)!}">
                </#if >
                <#--<input type="hidden" name="recp.pkExpenseItem" class="form-control" value="${(studentSign.pkStudentSignup)!}">-->
                <div class="white">
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">收款单号</label>
                                <div class="col-xs-9 col-md-9">
                                    <input type="text" name="recp.code" class="form-control" value="${(receivable.code)!}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">收款日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input type="text" name="recp.dateTime" class="form-control" value="${(receivable.date?string("yyyy-MM-dd"))!}"></div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label  col-xs-3 col-md-3">应收单号</label>
                                <div class="col-xs-9  col-md-9">
                                    <#if receivable.pkReceipt??>
                                        <input readonly name="recp.pkParent" class="form-control" value="${(receivable.pkParent)!}" type="text">
                                    <#else >
                                        <input readonly name="recp.pkParent" class="form-control" value="${(receivable.pkReceivable)!}" type="text">
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">学员编号</label>
                                <div class="col-xs-9 col-md-9">
                                    <input type="text" class="form-control" value="${(student.code)!}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">经办人</label>
                                <div class="col-xs-9 col-md-9">
                                    <input readonly id="empcapid" value="${(employee.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=4">
                                    <input readonly name="recp.pkSysUser" id="emppkid" value="${(employee.pkEmployee)!}" type="hidden">
                                <#--<select name="recp.pkSysUser">-->
                                <#--<#list userList as us>-->
                                <#--<option value="${(us.pkSysUser)!}" <#if receivable.pkSysUser ?? && receivable.pkSysUser == us.pkSysUser>selected="selected"</#if>>${(us.caption)!}</option>-->
                                <#--</#list>-->
                                <#--</select>-->
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">学员姓名
                                </label>
                                <div class="col-xs-9 col-md-9">
                                    <input type="text" class="form-control" value="${(student.caption)!}">
                                    <input type="hidden" name="recp.pkStudent" class="form-control" value="${(student.pkStudent)!}">
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3 ">学生性别</label>
                                <div class="col-xs-9 col-md-9">
                                    <select class="form-control"
                                            class="selectpicker bla bla bli" data-live-search="true">
                                        <option <#if student.sex?? && student.sex==0>selected="selected"</#if>>女</option>
                                        <option <#if student.sex?? && student.sex==1>selected="selected"</#if>>男</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3 ">入学年级</label>
                                <div class="col-xs-9 col-md-9">
                                    <select class="form-control"
                                            class="selectpicker bla bla bli" data-live-search="true">
                                    <#if grade ??>
                                        <#list grade as p>
                                            <option value="${(p.pkSysDictValues)!}" <#if p.pkSysDictValues ?? && student.grade ?? && p.pkSysDictValues==student.grade>selected</#if>>${(p.caption)!}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3 ">选择项目</label>
                                <div class="col-xs-9 col-md-9">
                                    <select class="form-control"
                                            class="selectpicker bla bla bli" data-live-search="true">
                                    <#if program ??>
                                        <#list program as p>
                                            <option value="${(p.pkSysDictValues)!}" <#if p.pkSysDictValues ?? && student.program ?? && p.pkSysDictValues==student.program>selected</#if>>${(p.caption)!}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">应收金额</label>
                                <div class="col-xs-9 col-md-9">
                                    <input readonly name="recp.cost" id="reCost" class="form-control jine" value="${(receivable.cost?string("#.##"))!}" type="text">
                                </div>
                            </div>
                        </div>
                        <#if receivable.pkReceipt??>
                        <#else >
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">本次收费</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input  name="recp.money" id="reMoney" class="form-control jine" value="${(receivable.map.receiptMoney.code)!}" type="text">
                                        <input  id="oldReMoney" value="${(receivable.money?string("#.##"))!}" type="hidden">
                                    </div>
                                </div>
                            </div>
                        </#if>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">收费项目</label>
                                <div class="col-xs-9 col-md-9">
                                    <input id="pkExpenseItem" name="recp.pkExpenseItem" class="form-control" value="" type="hidden">
                                    <select id="selectId"  class="selectpicker show-tick form-control" <#if receiptList??>multiple</#if> data-live-search="true" >

                                        <#if expenseItem??>
                                            <#list expenseItem as e>
                                                <option value="${(e.pkExpenseItem)!}" <#if e.pkExpenseItem?? &&receivable.pkExpenseItem?? && receivable.pkExpenseItem==e.pkExpenseItem>selected</#if>>${(e.caption)!}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">缴费人</label>
                                <div class="col-xs-9 col-md-9">
                                    <input  name="recp.payer" class="form-control" value="${(receivable.payer)!}" type="text">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">学年</label>
                                <div class="col-xs-9 col-md-9">
                                    <select name="recp.schoolYear" class="form-control" class="selectpicker bla bla bli" data-live-search="true">
                                    <#if schoolYear ??>
                                        <option value="">请选择</option>
                                        <#list schoolYear as p>
                                            <option value="${(p.pkSysDictValues)!}" <#if p.pkSysDictValues ?? && receivable.schoolYear ?? && p.pkSysDictValues==receivable.schoolYear>selected</#if>>${(p.caption)!}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div><!-- /.col -->
                            </div>
                        </div>

                        <div class="col-xs-12 col-md-12">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-1">备注</label>
                                <div class="col-xs-9 col-md-11">
                                    <input class="form-control" name="recp.notes" value="${(receivable.notes)!}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <table id="tab" class="table table-striped table-bordered" cellspacing="0"
                               cellpadding="0">
                            <tr id="Tr1" align="center">
                                <td>
                                    <input type="text" class="form-control bor0 bg0" disabled placeholder="费用名称">
                                </td>
                                <td>
                                    <input type="text"  class="form-control bor0 bg0 jine" disabled placeholder="金额">
                                </td>
                                <td>
                                    <input type="text" class="form-control bor0 bg0" disabled placeholder="币种">
                                </td>
                                <td>
                                    <input type="text" class="form-control bor0 bg0" disabled placeholder="备注">
                                </td>
                            </tr>
                        <#if receiptList??>
                            <#list receiptList as receipt>
                                <tr>
                                    <td>${(receipt.itemCaption)!}</td>
                                    <td id="${(receipt.pkExpenseItem)!}">${(receipt.money?c)!}</td>
                                    <td>${(receipt.map.currencyEntity.caption)!}

                                    </td>
                                    <td>${(receipt.memo)!}</td>
                                </tr>
                            </#list>
                        </#if>
                        </table>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3 ">收费方式</label>
                            <div class="col-xs-9 col-md-9">
                                <select name="recp.paymentMethod" class="form-control"
                                        class="selectpicker bla bla bli" data-live-search="true">
                                <#if paymethod ??>
                                    <#list paymethod as p>
                                        <option value="${(p.pkSysDictValues)!}" <#if p.pkSysDictValues ?? && receivable.paymentMethod ?? && p.pkSysDictValues==receivable.paymentMethod>selected</#if>>${(p.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">账号名称</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="recp.bankAccountCaption" class="form-control input-sm money-in"
                                       <#if studentSign??>value="${(studentSign.bankAccountCaption)!}"<#else >value="${(receivable.bankAccountCaption)!}"</#if> type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">账号</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="recp.bankAccount" class="form-control input-sm money-in"
                                       <#if studentSign??>value="${(studentSign.bankAccountCaption)!}"<#else >value="${(receivable.bankAccount)!}"</#if> type="text">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-1">银行名称</label>
                                <div class="col-xs-9 col-md-3">
                                    <input name="recp.bankCaption" class="form-control input-sm money-in"
                                           <#if studentSign??>value="${(studentSign.bankCaption)!}"<#else >value="${(receivable.bankCaption)!}"</#if> type="text">
                                </div>
                                <label class="control-label col-xs-3 col-md-1">银行地址</label>
                                <div class="col-xs-9 col-md-7">
                                    <input name="recp.bankAddress" class="form-control input-sm money-in"
                                           <#if studentSign??>value="${(studentSign.bankAddress)!}"<#else >value="${(receivable.bankAddress)!}"</#if> type="text">
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </form>

        </div>
    </div>
</div><!-- panel -->
<#include "../commons/footer.ftl"/>
<script>
    $(function(){

        $("input.jine").each(function (i) {

            var str = $("input.jine").eq(i).val().replace(/,/g, "");
            $("input.jine").eq(i).val("");
            $("input.jine").eq(i).val(str);

        });

        //提交
        $("#submitBtn").on("click",function(){
            $("#issubmitHidden").val($("input[name='issubmit']:checked").val());
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                type:"POST",
                url:"/finance/studentCharge/submit",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                    <#if type?? && type==0>
                        setTimeout("window.location.href='/finance/receivable/query'",1);
                    <#else >
                        setTimeout("window.location.href='/finance/studentCharge/query'",1);
                    </#if>
                        Notify.success("提交成功");
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })

        });


        //审核
        $("#retreatBtn").on("click",function(){
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                type:"POST",
                url:"/finance/studentCharge/retreat",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("驳回成功");
                    <#if type?? && type==0>
                        setTimeout("window.location.href='/finance/receivable/query'",1);
                    <#else >
                        setTimeout("window.location.href='/finance/studentCharge/query'",1);
                    </#if>
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })
        });

        //审核
        $("#auditBtn").on("click",function(){
            $("#isauditHidden").val($("input[name='isaudit']:checked").val());
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                type:"POST",
                url:"/finance/studentCharge/audit",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("审核成功");
                        <#if type?? && type==0>
                            setTimeout("window.location.href='/finance/receivable/query'",1);
                        <#else >
                            setTimeout("window.location.href='/finance/studentCharge/query'",1);
                        </#if>
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })
        });

        //保存
        $("#saveBtn").on("click",function(){


            var costMoney=0;
            var j =0;
            var pkExpenseItems="";
            //判断费用项目
            //判断费用明细是否为空
            <#--<#if (receiptList?size>0)>-->
            var len =${(receiptList?size)!0};
            if(len >0){
                for(i=0;i<document.getElementById('selectId').length;i++){
                    if(document.getElementById('selectId')[i].selected==true) {
                        j++;
                        var id = document.getElementById('selectId')[i].value;
                        costMoney = costMoney + Number(document.getElementById(id).innerHTML);
                        if(pkExpenseItems.length>0){
                            pkExpenseItems =pkExpenseItems+","+id+"="+Number(document.getElementById(id).innerHTML);
                        }else {
                            pkExpenseItems =pkExpenseItems+id +"="+Number(document.getElementById(id).innerHTML);
                        }
                    }
                }
                if(j ==0){
                    Notify.danger("请选择收费项目");
                    return false;
                }
                if(j==1){
                    if($("#reMoney").val() > costMoney){
                       Notify.danger("本次收费高于该收费项目费用");
                       return false;
                    }
                }
                if(j>1){
                    if ($("#reMoney").val() != costMoney){
                        Notify.danger("本次收费和费用项目不符");
                        return false;
                    }
                }
            }else {
                var s = $("#selectId option:selected").val();
                pkExpenseItems=s +"="+ $("#reMoney").val();
            }

            $("#pkExpenseItem").val(pkExpenseItems);
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            var oldMoney = Number($("#oldReMoney").val());
            var money = Number($("#reMoney").val());
            var cost = Number($("#reCost").val());
            if(cost-oldMoney-money < 0){
                Notify.danger("收款总金额不得超过应收金额,还剩余"+(cost-oldMoney));
                return false;
            }
            $.ajax({
                type:"POST",
                url:"/finance/studentCharge/save",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("保存成功");
                        <#if type?? && type==0>
                            setTimeout("window.location.href='/finance/receivable/query'",1);
                        <#else >
                            setTimeout("window.location.href='/finance/studentCharge/query'",1);
                        </#if>
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })
        })
    });

</script>
