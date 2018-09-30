<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>receipt<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
                <a class="btn btn-sm btn-newblue" id="saveBtn">Preservation</a>
            <#if receivable.pkReceipt??>
                    <#if receivable.issubmit?? &&  receivable.issubmit == 1>
                        <a class="btn btn-sm btn-newblue" id="submitBtn" disabled="disabled">Submission</a>
                    <#else >
                        <a class="btn btn-sm btn-newblue" id="submitBtn">Submission</a>
                    </#if>
                    <#if receivable.isaudit?? &&  receivable.isaudit == 1>
                        <a class="btn btn-sm btn-newblue" id="auditBtn" disabled="disabled">To examine</a>
                        <a class="btn btn-sm btn-newblue" id="retreatBtn" disabled="disabled">Reject</a>
                    <#else >
                        <a class="btn btn-sm btn-newblue" id="auditBtn">To examine</a>
                        <a class="btn btn-sm btn-newblue" id="retreatBtn">Reject</a>
                    </#if>
                <#else >
                    <#if receivable.issubmit?? &&  receivable.issubmit == 1>
                    <#else >
                        <a class="btn btn-sm btn-newblue" id="submitBtn" >Submission</a>
                    </#if>
                    <#if receivable.isaudit?? &&  receivable.isaudit == 1>
                    <#else >
                        <a class="btn btn-sm btn-newblue" id="auditBtn" >To examine</a>
                    </#if>
                </#if>


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
                                <label class="control-label col-xs-3 col-md-3">Receipt code</label>
                                <div class="col-xs-9 col-md-9">
                                    <input type="text" name="recp.code" class="form-control" value="${(receivable.code)!}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">Receipt time</label>
                                <div class="col-xs-9 col-md-9">
                                    <input type="text" name="recp.dateTime" class="form-control" value="${(receivable.date?string("yyyy-MM-dd"))!}"></div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label  col-xs-3 col-md-3">Receivables No.</label>
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
                                <label class="control-label col-xs-3 col-md-3">Student no.</label>
                                <div class="col-xs-9 col-md-9">
                                    <input type="text" class="form-control" value="${(student.code)!}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">Agent</label>
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
                                <label class="control-label col-xs-3 col-md-3">Student name</label>
                                <div class="col-xs-9 col-md-9">
                                    <input type="text" class="form-control" value="${(student.caption)!}">
                                    <input type="hidden" name="recp.pkStudent" class="form-control" value="${(student.pkStudent)!}">
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3 ">Student sex</label>
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
                                <label class="control-label col-xs-3 col-md-3 ">grade</label>
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
                                <label class="control-label col-xs-3 col-md-3 ">program</label>
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

                        <#--<div class="clearfix"></div>-->

                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">Amount receivable</label>
                                <div class="col-xs-9 col-md-9">
                                    <input readonly name="recp.cost" id="reCost" class="form-control jine" value="${(receivable.cost?string("#.##"))!}" type="text">
                                </div>
                            </div>
                        </div>
                        <#if receivable.pkReceipt??>
                        <#else >
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">charge</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input  name="recp.money" id="reMoney" class="form-control jine" value="${(receivable.map.receiptMoney.code)!}" type="text">
                                        <input  id="oldReMoney" value="${(receivable.map.receiptMoney.code)!}" type="hidden">
                                    </div>
                                </div>
                            </div>
                        </#if>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">payer</label>
                                <div class="col-xs-9 col-md-9">
                                    <input  name="recp.payer" class="form-control" value="${(receivable.payer)!}" type="text">
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-12 col-md-12">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-1">Remarks</label>
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
                                    <input type="text" class="form-control bor0 bg0" disabled placeholder="Cost name">
                                </td>
                                <td>
                                    <input type="text" class="form-control bor0 bg0 jine" disabled placeholder="money">
                                </td>
                                <td>
                                    <input type="text" class="form-control bor0 bg0" disabled placeholder="币种">
                                </td>
                                <td>
                                    <input type="text" class="form-control bor0 bg0" disabled placeholder="Remarks">
                                </td>
                            </tr>
                        <#if receiptList??>
                            <#list receiptList as receipt>
                                <tr>
                                    <td>${(receipt.itemCaption)!}</td>
                                    <td>${(receipt.money)!}</td>
                                    <td>${(receipt.map.currencyEntity.caption)!}

                                    </td>
                                    <td>${(receipt.memo)!}</td>
                                </tr>
                            </#list>
                        </#if>
                        </table>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3 ">Charge mode</label>
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
                                <label class="control-label col-xs-3 col-md-3">Account name</label>
                                <div class="col-xs-9 col-md-9">
                                    <input name="recp.bankAccountCaption" class="form-control input-sm money-in"
                                           <#if studentSign??>value="${(studentSign.bankAccountCaption)!}"<#else >value="${(receivable.bankAccountCaption)!}"</#if> type="text">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">Account number</label>
                                <div class="col-xs-9 col-md-9">
                                    <input name="recp.bankAccount" class="form-control input-sm money-in"
                                           <#if studentSign??>value="${(studentSign.bankAccountCaption)!}"<#else >value="${(receivable.bankAccount)!}"</#if> type="text">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-1">Bank name</label>
                                <div class="col-xs-9 col-md-3">
                                    <input name="recp.bankCaption" class="form-control input-sm money-in"
                                           <#if studentSign??>value="${(studentSign.bankCaption)!}"<#else >value="${(receivable.bankCaption)!}"</#if> type="text">
                                </div>
                                <label class="control-label col-xs-3 col-md-1">Bank address</label>
                                <div class="col-xs-9 col-md-7">
                                    <input name="recp.bankAddress" class="form-control input-sm money-in"
                                           <#if studentSign??>value="${(studentSign.bankAddress)!}"<#else >value="${(receivable.bankAddress)!}"</#if> type="text">
                                </div>
                            </div>
                        </div>
                    </div>
                    <#--<div class="row">-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">submitter</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly name="recp.submitor" class="form-control input-sm money-in" value="${(receivable.map.submitorEntity.caption)!}" type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Submitted</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly name="recp.submitDateTime" class="form-control input-sm money-in" value="${(receivable.submitDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">is submit</label>-->
                                    <#--<div class="col-xs-9  col-md-9 pt7">-->
                                            <#--<span class="rdio rdio-warning">-->
                                                <#--<input name="issubmit" type="radio" id="radio1" value="1" <#if receivable.issubmit?? && receivable.issubmit == 1>checked="checked"</#if>>-->
                                                <#--<label for="radio1">是&emsp;</label>-->
                                            <#--</span>-->
                                        <#--<span class="rdio rdio-warning">-->
                                                <#--<input name="issubmit" type="radio" id="radio2" value="0" <#if receivable.issubmit?? && receivable.issubmit == 0>checked="checked"</#if>>-->
                                                <#--<label for="radio2">否&emsp;</label>-->
                                            <#--</span>-->
                                    <#--</div>-->
                                    <#--<input type="hidden" name="recp.issubmit" id="issubmitHidden">-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Auditor</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly name="recp.auditor" class="form-control input-sm money-in" value="${(receivable.map.auditorEntity.caption)!}" type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">Audit time</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly name="recp.auditDateTime" class="form-control input-sm money-in" value="${(receivable.auditDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">is review</label>-->
                                    <#--<div class="col-xs-9 col-md-9 pt7">-->
                                            <#--<span class="rdio rdio-warning">-->
                                                <#--<input name="isaudit" type="radio" id="radio3" value="1" <#if receivable.isaudit?? && receivable.isaudit == 1>checked="checked"</#if>>-->
                                                <#--<label for="radio3">是&emsp;</label>-->
                                            <#--</span>-->
                                        <#--<span class="rdio rdio-warning">-->
                                                <#--<input name="isaudit" type="radio" id="radio4" value="0" <#if receivable.isaudit?? && receivable.isaudit == 0>checked="checked"</#if>>-->
                                                <#--<label for="radio4">否&emsp;</label>-->
                                            <#--</span>-->
                                    <#--</div>-->
                                    <#--<input type="hidden" name="recp.isaudit" id="isauditHidden">-->
                                <#--</div>-->
                            <#--</div>-->
                    <#--</div>-->
                    <#--<div class="row">-->
                        <#--<div class="col-xs-12 col-md-4">-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label  col-xs-3 col-md-3">Founder</label>-->
                                <#--<div class="col-xs-9  col-md-9">-->
                                    <#--<input readonly class="form-control input-sm money-in" value="${(receivable.map.creatorEntity.caption)!}" type="text">-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                        <#--<div class="col-xs-12 col-md-4">-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label col-xs-3 col-md-3">Creation time</label>-->
                                <#--<div class="col-xs-9 col-md-9">-->
                                    <#--<input readonly class="form-control input-sm money-in"-->
                                           <#--value="${(receivable.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="row">-->
                        <#--<div class="col-xs-12 col-md-4">-->
                             <#--<div class="form-group">-->
                                 <#--<label class="control-label col-xs-3 col-md-3">Modifier</label>-->
                                 <#--<div class="col-xs-9 col-md-9">-->
                                     <#--<input readonly class="form-control input-sm money-in" value="${(receivable.map.modifierEntity.caption)!}" type="text">-->
                                 <#--</div>-->
                             <#--</div>-->
                         <#--</div>-->
                        <#--<div class="col-xs-12 col-md-4">-->
                             <#--<div class="form-group">-->
                                 <#--<label class="control-label col-xs-3 col-md-3">Modified</label>-->
                                 <#--<div class="col-xs-9 col-md-9">-->
                                     <#--<input readonly class="form-control input-sm money-in"-->
                                            <#--value="${(receivable.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">-->
                                 <#--</div>-->
                             <#--</div>-->
                         <#--</div>-->
                    <#--</div>-->
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

        //Submission
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
                        Notify.success("Submit success");
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })

        });


        //To examine
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
                        Notify.success("Dismiss success");
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

        //To examine
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
                        Notify.success("success");
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

        //Preservation
        $("#saveBtn").on("click",function(){
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            var oldMoney = Number($("#oldReMoney").val());
            var money = Number($("#reMoney").val());
            var cost = Number($("#reCost").val());
            if(cost-oldMoney-money < 0){
                Notify.danger("The total amount of the receivables shall not exceed the amount receivable,Remaining"+(cost-oldMoney));
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
                        Notify.success("Save success");
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
