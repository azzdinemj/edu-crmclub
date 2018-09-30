<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Receivables<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
                <a class="btn btn-sm btn-newblue" id="saveBtn">Preservation</a>

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
                                <input type="hidden" name="recType" value="${(type)!}">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">Receivables No.</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="rec.pkReceivable" value="${(receivable.pkReceivable)!}" readonly class="form-control"  title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">Single number</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="rec.pkParent" value="${(receivable.pkParent)!}" disabled  class="form-control" title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">Student name</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly id="directorcap2" value="${(student.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/findstu2?pkStudent=4">
                                            <input readonly name="rec.pkStudent" id="directorpk2" value="${(student.caption)!}" type="hidden">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">cost</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input value="${(receivable.cost?c)!}" name="rec.cost" id="cost" class="form-control" title="" type="number">
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">item</label>
                                        <div class="col-xs-9 col-md-9">
                                            <select name="rec.pkExpenseItem">
                                            <#if expenseItem ??>
                                                <#list expenseItem as ex>
                                                    <option value="${(ex.pkExpenseItem)!}"
                                                            <#if ex.pkExpenseItem ?? && receivable.pkExpenseItem??&& ex.pkExpenseItem==receivable.pkExpenseItem>selected="selected"</#if>> ${(ex.caption)!}</option>
                                                </#list>
                                            </#if>

                                            </select>
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">Agent</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly id="directorcap" value="${(employee.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=4">
                                            <input readonly name="rec.pkSysUser" id="directorpk" value="${(employee.pkEmployee)!}" type="hidden">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-1">Remarks</label>
                                        <div class="col-xs-9 col-md-11">
                                            <input name="rec.notes" value="${(receivable.notes)!}"  class="form-control" title="" type="text">
                                        </div><!-- /.col -->
                                    </div>
                                </div>


                            </div>

                            <h3>费用明细</h3>

                        <#-- <div class="table-responsive">-->


                            <table id="tab" class="table table-striped table-bordered" cellspacing="0" cellpadding="0">
                                <thead>
                                <tr>
                                    <td>费用项目</td>
                                    <td>货币种类</td>
                                    <td>实际金额</td>
                                    <td>备注</td>

                                    <td width="10%"><i class="fa fa-remove op0 add_icon"></i><i onclick="addTr2('tab', -1)"
                                                                                                class="fa fa-plus add_icon"></i>
                                    </td>


                                </tr>
                                </thead>
                                <tbody id="parentTab"<#if detailsList??>onload="attClass(${detailsList?size})" </#if>>
                                <#if detailsList??>
                                    <#assign  index = 0 >
                                    <#list detailsList as exp>
                                    <tr id="Tr1" align="center">
                                        <td>
                                            <input  name="details[${index}].pkExpenseItem"
                                                    value="${(exp.itemCaption)!}" class="form-control bor0 bg0" readonly>
                                        </td>
                                        <td>
                                            <select name="details[${index}].currency" id="pkSysDictValues${index}" class="form-control" onchange="setMoner(${index})">
                                                <#if currency??>
                                                    <#list currency as cur>
                                                        <option value="${(cur.pkSysDictValues)!}" <#if exp.currency?? && cur.pkSysDictValues??&& cur.pkSysDictValues==exp.currency>selected</#if>>${(cur.caption)!}</option>
                                                    </#list>

                                                </#if>
                                            </select>

                                        </td>
                                        <td>
                                            <input type="text" id="money${index}" name="details[${index}].money" value="${(exp.money)!0.00}">
                                        </td>


                                        <td><input type="text" name="details[${index}].memo" value="${(exp.details.memo)!}"
                                                   class="form-control bor0 bg0" ></td>

                                    </tr>
                                        <#assign index = index+1>  <!--list循环一次，变量+1-->
                                    </#list>
                                </#if>
                                <#--</#if>-->
                                </tbody>
                            </table>


                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label  col-xs-3 col-md-3">Founder</label>
                                        <div class="col-xs-9  col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(receivable.map.creatorEntity.caption)!}" type="text" >
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">Creation time</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(receivable.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}" type="text">
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">Modifier</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control input-sm money-in" value="${(receivable.map.modifierEntity.caption)!}" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">Modified</label>
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
                        Notify.success("Submit success");
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
                        Notify.success("success");
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

            var type = $("#recType").val();
            if(type == 0) {
                $.ajax({
                    type: "POST",
                    url: "/finance/receivable/save",
                    data: d,
                    dataType: "json",
                    success: function (data) {
                        console.log(JSON.stringify(data));
                        if (data.code == 0) {
                            Notify.success("保存成功");
                            setTimeout("window.location.href='/finance/receivable/query'", 1);
                        } else {
                            Notify.danger(data.message);
                        }
                    }
                })
            }else{
                $.ajax({
                    type: "POST",
                    url: "/finance/receivable/saveAudit",
                    data: d,
                    dataType: "json",
                    success: function (data) {
                        console.log(JSON.stringify(data));
                        if (data.code == 0) {
                            Notify.success("保存成功");
                            setTimeout("window.location.href='/finance/receivable/query'", 1);
                        } else {
                            Notify.danger(data.message);
                        }
                    }
                })
            }
        })
    });


    //被插入的行索引
    function addTr(tab, row, trHtml) {
        //获取table最后一行 $("#tab tr:last")
        //获取table第一行 $("#tab tr").eq(0)
        //获取table倒数第二行 $("#tab tr").eq(-2)
        var $tr = $("#" + tab + " tr").eq(row);
        if ($tr.size() == 0) {
            alert("指定的table id或行数不存在！");
            return;
        }
        $tr.after(trHtml);
    }

    //插入一行

    <#if detailsList??>
    var numb =${detailsList?size};
    <#else >
    var numb = 0;
    </#if>

    <#---->
    function addTr2(tab, row) {
        var trHtml = '' +
                '<tr align="center"><td>' +
                '<select name="details[' + numb + '].pkExpenseItem" id="pkExpenseItem' + numb + '" ><option value="">请选择</option><#if expenseItems??><#list expenseItems as exp><option value="${(exp.pkExpenseItem)!}">${(exp.caption)!}</option></#list></#if></select>' +
                //            '<input type="text" name="details['+numb+'].pkExpenseItem" class="form-control bor0 bg0" placeholder="费用项目">' +
                '</td>' +

                //                '<td><input type="text" name="details[' + numb + '].unit" id="money' + numb + '"  ></td>' +
                '<td><select name="details[' + numb + '].currency" id="pkSysDictValues' + numb + '" class="form-control bor0 bg0" onchange="setMoner(' + numb + ')">><option value="">请选择币种</option><#if currency??><#list currency as cur><option value="${(cur.pkSysDictValues)!}">${(cur.caption)!}</option></#list></#if></select></td>' +
                //                '<td><input type="text" name="details[' + numb + '].unit"  class="form-control bor0 bg0" ></td>' +
                //                '<td><input type="text" name="details[' + numb + '].num"   class="form-control bor0 bg0"></td>' +
                '<td><input type="text" name="details[' + numb + '].money" id="money' + numb + '" value="${(ex.details.money)!0.00}" class="form-control bor0 bg0"></td>' +/*placeholder="实际金额"*/


                '<td><input type="text" name="details[' + numb + '].memo" class="form-control bor0 bg0" ></td>' +
                '<td ><i  onclick="deleteRow(tab,this.parentElement.parentElement.rowIndex,'+ numb +')" class="fa fa-remove add_icon"></i>' +
                '</td></tr>';
        numb++;
        addTr(tab, row, trHtml);

    }

    //删除一行
    function deleteRow(tableID, rowIndex,num) {
        //var table = document.getElementById(tableID);

        var money = Number($("#money"+num).val());
        var type = $("#money"+num).attr("class");
        if(type.indexOf("jine")!=-1) {
            var sum = Number($(".sum").val());
            var discount = Number($("#discount").val());
            sum = sum - money;
            $(".sum").val(sum);
            $("#money").val(sum - discount);
        }else if(type.indexOf("usd")!=-1){
            var sum = Number($(".mnyusd").val());
            sum = sum - money;
            $(".mnyusd").val(sum);
            $("#moneyUsd").val(sum);
        }

        tableID.deleteRow(rowIndex);

//    newRowIndex--;//维护全局变量
    }
</script>
