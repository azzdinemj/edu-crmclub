C<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>退费<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <#--<a class="btn btn-sm btn-newblue" id="saveBtn">保存</a>-->
            <#--<#if payables?? &&payables.issubmit?? &&  payables.issubmit == 1>-->
                    <#--<a class="btn btn-sm btn-newblue" id="submitBtn" disabled>提交</a>-->
                <#--<#else >-->
                    <#--<a class="btn btn-sm btn-newblue" id="submitBtn">提交</a>-->
                <#--</#if>-->
                <#--<#if payables?? &&payables.isaudit?? &&  payables.isaudit == 1>-->
                    <#--<a class="btn btn-sm btn-newblue" id="auditBtn" disabled>审核</a>-->
                <#--<#else >-->
                    <#--<a class="btn btn-sm btn-newblue" id="auditBtn">审核</a>-->
                <#--</#if>-->

            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <form class="look form-ajax form-horizontal" id="formSumbit" method="POST" action="/finance/payables/save"
                  data-target="/finance/payables/query">
                <div class="panel panel-default">
                    <div class="tab-content">
                        <div class="tab-pane active">
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">应付单号</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="pkPayables" value="${(payables.pkPayables)!}" readonly class="form-control"  title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">应付日期</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="dateTime" value="${(payables.date?string("yyyy-MM-dd"))!}"  class="form-control" title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">关连单号</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="pkParent" value="${(payables.pkParent)!}" class="form-control" title="" type="text" readonly>
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">学生编号</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input value="${(student.code)!}" disabled class="form-control" title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">学生名称</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input value="${(student.caption)!}" disabled  class="form-control" title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
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
                                            <input value="${(payables.cost?c)!}" id="cost" disabled class="form-control" title="" type="number">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">经办人</label>
                                        <div class="col-xs-9 col-md-9">

                                            <#--<input readonly id="empcapid" value="${(employee.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=4">-->
                                            <#--<input readonly name="recp.pkSysUser" id="emppkid" value="${(employee.pkEmployee)!}" type="hidden">-->
                                            <#--<select name="recp.pkSysUser">-->
                                            <#--<#list userList as us>-->
                                            <#--<option value="${(us.pkSysUser)!}" <#if payables.pkSysUser ?? && payables.pkSysUser == us.pkSysUser>selected="selected"</#if>>${(us.caption)!}</option>-->
                                            <#--</#list>-->
                                            <#--</select>-->

                                            <select name="pkSysUser">
                                                <#if userList??>
                                            <#list userList as us>
                                                <option value="${(us.pkSysUser)!}" <#if payables.pkSysUser ?? && payables.pkSysUser == us.pkSysUser>selected="selected"</#if>>${(us.caption)!}</option>
                                            </#list>
                                                </#if>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-8">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-1">备注</label>
                                        <div class="col-xs-9 col-md-11">
                                            <input name="notes" value="${(payables.notes)!}"  class="form-control" title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>


                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">提交人</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly name="submitor" class="form-control input-sm money-in" value="${(payables.submitor)!}" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">提交时间</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly name="submitDateTime" class="form-control input-sm money-in" value="${(payables.submitDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">是否提交</label>
                                        <div class="col-xs-9 col-md-9 pt7">
                                            <span class="rdio rdio-warning">
                                                <input name="issubmit" type="radio" id="radio1" value="1" <#if payables?? &&payables.issubmit?? && payables.issubmit == 1>checked</#if>>
                                                <label disabled="disabled" for="radio3">是&emsp;</label>
                                            </span>
                                            <span class="rdio rdio-warning">
                                                <input name="issubmit" type="radio" id="radio2" value="0" <#if payables?? && payables.issubmit?? && payables.issubmit == 0>checked</#if>>
                                                <label disabled="disabled" for="radio4">否&emsp;</label>
                                            </span>
                                        </div>
                                        <input type="hidden" name="issubmit" id="issubmitHidden">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">审核人</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly name="auditor" class="form-control input-sm money-in" value="${(payables.auditor)!}" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">审核时间</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly name="auditDateTime" class="form-control input-sm money-in" value="${(payables.auditDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">是否审核</label>
                                        <div class="col-xs-9 col-md-9 pt7">
                                            <span class="rdio rdio-warning">
                                                <input name="isaudit" type="radio" id="radio3" value="1" <#if payables?? &&payables.isaudit?? && payables.isaudit == 1>checked</#if>>
                                                <label disabled="disabled" for="radio3">是&emsp;</label>
                                            </span>
                                            <span class="rdio rdio-warning">
                                                <input name="isaudit" type="radio" id="radio4" value="0" <#if payables?? &&payables.isaudit?? && payables.isaudit == 0>checked</#if>>
                                                <label disabled="disabled" for="radio4">否&emsp;</label>
                                            </span>
                                        </div>
                                        <input type="hidden" name="isaudit" id="isauditHidden">
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label  col-xs-3 col-md-3">创建人</label>
                                        <div class="col-xs-9  col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(payables.creator)!}" type="text" >
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">创建时间</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(payables.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">修改人</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(payables.modifier)!}" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">修改时间</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(payables.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">
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
        $("#submitBtn").on("click",function(){
            $("#issubmitHidden").val($("input[name='issubmit']:checked").val());
            var d = {};
            var t = $("#formSumbit").serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                type:"POST",
                url:"/finance/payables/submit",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("提交成功");
                        setTimeout("window.location.href='/finance/payables/query'",1);
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
                url:"/finance/payables/audit",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("审核成功");
                        setTimeout("window.location.href='/finance/payables/query'",1);
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
                url:"/finance/payables/save",
                data:d,
                dataType:"json",
                success:function(data) {
                    console.log(JSON.stringify(data));
                    if(data.code == 0){
                        Notify.success("保存成功");
                        setTimeout("window.location.href='/finance/payables/query'",1);
                    }else{
                        Notify.danger(data.message);
                    }
                }
            })
        })
    });
</script>
