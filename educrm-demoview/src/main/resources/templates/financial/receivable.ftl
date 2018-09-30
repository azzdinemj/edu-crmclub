<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>应收单<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <a class="btn btn-sm btn-newblue" id="saveBtn">保存</a>
                <#--<#if receivable.issubmit?? &&  receivable.issubmit == 1>-->
                <#--<#else >-->
                 <#--<a class="btn btn-sm btn-newblue" id="submitBtn">提交</a>-->
                <#--</#if>-->
                <#--<#if receivable.isaudit?? &&  receivable.isaudit == 1>-->
                <#--<#else >-->
                <#--<a class="btn btn-sm btn-newblue" id="auditBtn">审核</a>-->
                <#--</#if>-->

            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <form class="look form-ajax form-horizontal" id="formSumbit" method="POST" action="/finance/receivable/save"
                  data-target="/finance/receivable/query">
                <div class="panel panel-default">
                    <div class="tab-content">
                        <div class="tab-pane active">
                            <div class="row">
                                <input type="hidden" name="rec.pkDomain" value="${(receivable.pkDomain)!}">
                                <input type="hidden" name="rec.code" value="${(receivable.code)!}">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">应收单号</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="rec.pkReceivable" value="${(receivable.pkReceivable)!}" readonly class="form-control"  title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">日期</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<input name="rec.dateTime" value="${(receivable.date?string("yyyy-MM-dd"))!}"  class="form-control" title="" type="text">-->
                                        <#--</div><!-- /.col &ndash;&gt;-->
                                    <#--</div>-->
                                <#--</div>-->
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">关连单号</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="rec.pkParent" value="${(receivable.pkParent)!}" disabled  class="form-control" title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">学生姓名</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly id="directorcap2" value="${(student.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/findstu2?pkStudent=4">
                                            <input readonly name="rec.pkStudent" id="directorpk2" value="${(student.caption)!}" type="hidden">
                                            <#--<input value="${(student.code)!}" class="form-control" title="" type="text">-->
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">学生名称</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<input value="${(student.caption)!}" disabled  class="form-control" title="" type="text">-->
                                        <#--</div><!-- /.col &ndash;&gt;-->
                                    <#--</div>-->
                                <#--</div>-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">关连类型</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<select class="form-control">-->
                                                <#--<option>报名</option>-->
                                            <#--</select>-->
                                        <#--</div><!-- /.col &ndash;&gt;-->
                                    <#--</div>-->
                                <#--</div>-->
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">总费用</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input value="${(receivable.cost?c)!}" name="rec.cost" id="cost" class="form-control" title="" type="number">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">费用项目</label>
                                        <div class="col-xs-9 col-md-9">
                                            <select name="rec.pkExpenseItem">
                                            <#if expenseItem ??>
                                                <#list expenseItem as ex>
                                                    <option value="${(ex.pkExpenseItem)!}"
                                                            <#if ex.pkExpenseItem ?? && receivable.pkExpenseItem??&& ex.pkExpenseItem==receivable.pkExpenseItem>selected="selected"</#if>> ${(ex.caption)!}</option>
                                                </#list>
                                            </#if>

                                            </select>
                                            <#--<input value="${(receivable.cost?c)!}" id="cost" class="form-control" title="" type="number">-->
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">币种</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<select name="receivable.currency" class="form-control">-->
                                                <#--<option value=0-->
                                                        <#--<#if receivable.currency?? && receivable.currency=="CNY">selected="selected"</#if>>-->
                                                    <#--人民币-->
                                                <#--</option>-->
                                                <#--<option value=1-->
                                                        <#--<#if receivable.currency?? && receivable.currency=="USD">selected="selected"</#if>>-->
                                                    <#--美元-->
                                                <#--</option>-->
                                            <#--</select>-->
                                        <#--</div><!-- /.col &ndash;&gt;-->
                                    <#--</div>-->
                                <#--</div>-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">已收费用</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<input name="rec.money" value="${(receivable.money)!}" disabled onchange="updateMoney()" id="money"  class="form-control" title="" type="number">-->
                                        <#--</div><!-- /.col &ndash;&gt;-->
                                    <#--</div>-->
                                <#--</div>-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">剩余费用</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<input value="" id="surplusMoney"  class="form-control" title="" disabled type="number">-->
                                        <#--</div><!-- /.col &ndash;&gt;-->
                                    <#--</div>-->
                                <#--</div>-->
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">经办人</label>
                                        <div class="col-xs-9 col-md-9">
                                            <#--<select name="rec.pkSysUser">-->
                                            <#--<#list userList as us>-->
                                                <#--<option value="${(us.pkSysUser)!}" <#if receivable.pkSysUser ?? && receivable.pkSysUser == us.pkSysUser>selected="selected"</#if>>${(us.caption)!}</option>-->
                                            <#--</#list>-->
                                            <#--</select>-->
                                            <input readonly id="directorcap" value="${(employee.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=4">
                                            <input readonly name="rec.pkSysUser" id="directorpk" value="${(employee.pkEmployee)!}" type="hidden">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-1">备注</label>
                                        <div class="col-xs-9 col-md-11">
                                            <input name="rec.notes" value="${(receivable.notes)!}"  class="form-control" title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>


                            </div>
                            <#--<div class="row">-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">提交人</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<input readonly name="rec.submitor" class="form-control input-sm money-in" value="${(receivable.map.submitorEntity.caption)!}" type="text">-->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</div>-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">提交时间</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<input readonly name="rec.submitDateTime" class="form-control input-sm money-in" value="${(receivable.submitDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">-->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</div>-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">是否提交</label>-->
                                        <#--<div class="col-xs-9 col-md-9 pt7">-->
                                            <#--<span class="rdio rdio-warning">-->
                                                <#--<input name="issubmit" type="radio" id="radio1" value="1" <#if receivable.issubmit?? && receivable.issubmit == 1>checked</#if>>-->
                                                <#--<label for="radio3">是&emsp;</label>-->
                                            <#--</span>-->
                                            <#--<span class="rdio rdio-warning">-->
                                                <#--<input name="issubmit" type="radio" id="radio2" value="0" <#if receivable.issubmit?? && receivable.issubmit == 0>checked</#if>>-->
                                                <#--<label for="radio4">否&emsp;</label>-->
                                            <#--</span>-->
                                        <#--</div>-->
                                        <#--<input type="hidden" name="rec.issubmit" id="issubmitHidden">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="row">-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">审核人</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<input readonly name="rec.auditor" class="form-control input-sm money-in" value="${(receivable.map.auditorEntity.caption)!}" type="text">-->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</div>-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">审核时间</label>-->
                                        <#--<div class="col-xs-9 col-md-9">-->
                                            <#--<input readonly name="rec.auditDateTime" class="form-control input-sm money-in" value="${(receivable.auditDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">-->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</div>-->
                                <#--<div class="col-xs-12 col-md-4">-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label col-xs-3 col-md-3">是否审核</label>-->
                                        <#--<div class="col-xs-9 col-md-9 pt7">-->
                                            <#--<span class="rdio rdio-warning">-->
                                                <#--<input name="isaudit" type="radio" id="radio3" value="1" <#if receivable.isaudit?? && receivable.isaudit == 1>checked</#if>>-->
                                                <#--<label for="radio1">是&emsp;</label>-->
                                            <#--</span>-->
                                            <#--<span class="rdio rdio-warning">-->
                                                <#--<input name="isaudit" type="radio" id="radio4" value="0" <#if receivable.isaudit?? && receivable.isaudit == 0>checked</#if>>-->
                                                <#--<label for="radio2">否&emsp;</label>-->
                                            <#--</span>-->
                                        <#--</div>-->
                                        <#--<input type="hidden" name="rec.isaudit" id="isauditHidden">-->
                                    <#--</div>-->
                                <#--</div>-->

                            <#--</div>-->
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label  col-xs-3 col-md-3">创建人</label>
                                        <div class="col-xs-9  col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(receivable.map.creatorEntity.caption)!}" type="text" >
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">创建时间</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(receivable.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">修改人</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(receivable.map.modifierEntity.caption)!}" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">修改时间</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(receivable.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div><!-- tab-content -->
                    </div>


                </div><!-- contentpanel -->
            </form>

        </div>

    </div>
</div>


<#include "../commons/footer.ftl"/>
<script>
    $(function(){
//        updateMoney();

        $("#submitBtn").on("click",function(){
            $("#issubmitHidden").val($("input[name='issubmit']:checked").val());
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                type:"POST",
                url:"/finance/receivable/submit",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("提交成功");
                        setTimeout("window.location.href='/finance/receivable/query'",1);
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })

        })

        $("#auditBtn").on("click",function(){
            $("#isauditHidden").val($("input[name='isaudit']:checked").val());
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                type:"POST",
                url:"/finance/receivable/audit",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("审核成功");
                        setTimeout("window.location.href='/finance/receivable/query'",1);
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })
        })

        $("#saveBtn").on("click",function(){
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                type:"POST",
                url:"/finance/receivable/save",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("保存成功");
                        setTimeout("window.location.href='/finance/receivable/query'",1);
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })
        })
    });
//
//    function updateMoney(){
//        var cost = $("#cost").val();
//        var money = $("#money").val();
//        $("#surplusMoney").val(Number(cost-money));
//        var surplusMoney = $("#surplusMoney").val();
//        if(Number(Math.abs(surplusMoney) + Math.abs(money)) != cost){
//            Notify.danger("收款的金额不得超于总费用!!!");
//        }
//    }
</script>
