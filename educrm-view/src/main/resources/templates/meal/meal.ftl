
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />


<div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>套餐 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" id="saveBtn">保存</a>
                        <a class="btn btn-sm btn-newblue" href="/setMeal/setMeal/query">返回</a>
                        <#--<a class="btn btn-sm btn-info" href="">删除</a>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <form class="form-inline search white" id="formId">
                            <div class="row">
                                <label class="control-label col-md-1">套餐时段</label>
                                <div class="col-xs-9 col-md-3">
                                    <select name="type" id="mealType" class="form-control queryDefault">
                                        <option value="0" <#if setMeal?? &&setMeal.type?? &&setMeal.type==0>selected</#if>>早餐</option>
                                        <option value="1" <#if setMeal?? &&setMeal.type?? &&setMeal.type==1>selected</#if>>午餐</option>
                                        <option value="2" <#if setMeal?? &&setMeal.type?? &&setMeal.type==2>selected</#if>>晚餐</option>
                                    </select>
                                </div>
                                <input type="hidden" name="pkSetMeal" value="${(setMeal.pkSetMeal)!}">
                                <label class="control-label col-md-1">套餐编号</label>
                                <div class="col-xs-9 col-md-3">
                                    <#--<select name="code" id="mealCode" class="form-control">-->
                                    <#--<#if (englishCode?size > 0)>-->
                                        <#--<#list englishCode as d>-->
                                            <#--<option value="${(d.caption)!}" <#if d.caption?? && setMeal.code ?? && setMeal.code== d.caption>selected</#if> >${(d.caption)!}</option>-->
                                        <#--</#list>-->
                                    <#--</#if>-->
                                    <#--</select>-->
                                    <input type="text" oninput="if(value.length>2)value=value.slice(0,2)" onKeyUp="value=value.replace(/[\W]/g,'')" name="code" id="mealCode" class="form-control" value="${(setMeal.code)!}" />
                                </div>
                                <label class="control-label col-md-1">套餐名称</label>
                                <div class="col-xs-9 col-md-3">
                                    <input class="form-control" name="setMealName" style="width: 100%!important;" id="classCaption" value="${(setMeal.setMealName)!}" title="" type="text">
                                </div>
                            </div>
                                <div class="clearfix"></div>
                                <div class="row">
                                    <label class="control-label col-md-1">默认套餐</label>
                                    <div class="col-xs-9 col-md-3">
                                        <select name="defaults" id="queryDefaultValue" class="form-control queryDefault">
                                            <option value="0" <#if setMeal.defaults?? && setMeal.defaults == 0>selected="selected"</#if>>否</option>
                                            <option value="1" <#if setMeal.defaults?? && setMeal.defaults == 1>selected="selected"</#if>>是</option>
                                        </select>
                                    </div>
                                    <label class="control-label col-md-1">套餐日期</label>
                                    <div class="col-xs-9 col-md-3">
                                        <select name="weekDay" id="weekDayId" class="form-control queryDefault">
                                            <option value="1" <#if setMeal.weekDay?? && setMeal.weekDay == 1>selected="selected"</#if>>周一</option>
                                            <option value="2" <#if setMeal.weekDay?? && setMeal.weekDay == 2>selected="selected"</#if>>周二</option>
                                            <option value="3" <#if setMeal.weekDay?? && setMeal.weekDay == 3>selected="selected"</#if>>周三</option>
                                            <option value="4" <#if setMeal.weekDay?? && setMeal.weekDay == 4>selected="selected"</#if>>周四</option>
                                            <option value="5" <#if setMeal.weekDay?? && setMeal.weekDay == 5>selected="selected"</#if>>周五</option>
                                            <option value="6" <#if setMeal.weekDay?? && setMeal.weekDay == 5>selected="selected"</#if>>周六</option>
                                            <option value="7" <#if setMeal.weekDay?? && setMeal.weekDay == 5>selected="selected"</#if>>周日</option>
                                        </select>
                                    </div>
                                    <label class="control-label col-md-1">套餐费用</label>
                                    <div class="col-xs-9 col-md-3">
                                        <input class="form-control" style="width: 100%!important;" name="price" id="classCaption" value="${(setMeal.price)!}" title="" type="number">
                                    </div>
                                </div>
                                <div class="row">
                                    <label class="control-label col-md-1">套餐状态</label>
                                    <div class="col-xs-9 col-md-3">
                                        <select name="isvalid" class="form-control">
                                            <option value="0" <#if setMeal.isvalid?? && setMeal.isvalid == 0>selected="selected"</#if>>关闭</option>
                                            <option value="1" <#if setMeal.isvalid?? && setMeal.isvalid == 1>selected="selected"</#if>>启用</option>
                                        </select>
                                    </div>
                                </div>

                               <div class="row">
                                   <label class="control-label col-md-1">套餐备注</label>
                                   <div class="col-xs-9 col-md-11">
                                       <input class="form-control" style="width: 100%!important;" name="memo" id="classCaption" value="${(setMeal.memo)!}" title="" type="text">
                                   </div>
                                </div>

                            <div class="row">
                                <label class="col-xs-12 control-label">套餐原料</label>
                                <table id="tab"  class="table table-striped table-bordered" >
                                    <thead>
                                    <tr>
                                        <th>套餐原料</th>
                                        <th>原料含量</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if mealMaterial??>
                                        <#assign  index = 0 >
                                        <#list mealMaterial as me>
                                        <tr>
                                            <td>
                                                <input readonly id="materialCap${index}" value="${(me.materialName)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/setMeal/setMeal/getMaterial?ver=1">
                                                <input readonly name="details[${index}].materialId" id="headMeal${index}" value="${(me.materialId)!}" type="hidden">
                                                <#--<select name="details[${index}].materialId" class="form-control" name="" title="">-->
                                                    <#--<#if material??>-->
                                                        <#--<#list material as m>-->
                                                            <#--<option value="${(m.materialId)!}" <#if m.materialId?? && me.materialId?? && me.materialId == m.materialId>selected="selected"</#if>>${(m.materialName)!}</option>-->
                                                        <#--</#list>-->
                                                    <#--</#if>-->
                                                <#--</select>-->
                                            </td>
                                            <td><input type="number" title="" name="details[${index}].gram" class="form-control" value="${(me.gram)!}">&nbsp;&nbsp;g</td>
                                            <td ><a onclick="deleteRow(tab,this.parentElement.parentElement.rowIndex,${index})" class="fa add_icon">删除</a>
                                        </tr>
                                            <#assign index = index+1>  <!--list循环一次，变量+1-->
                                        </#list>
                                    </#if>
                                    </tbody>
                                </table>
                                <div class="row text-center">
                                    <input type="button" class="btn btn-sm btn-newblue" onclick="addTr('tab', -1)" value="+添加套餐原材料" />
                                </div>
                            </div>
                        </form>

                    </div>
                </div>



            </div><!-- panel -->

        </div>
        <!--添加部分结束==============================================-->


    </div><!-- mainpanel -->


</section>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">套餐管理-添加</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-sm btn-info">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#include "../commons/footer.ftl"/>

<script>
    $(function(){
        $("#saveBtn").on("click",function(){
            $("input.deldis").each(function (i) {
                $("input.deldis").eq(i).removeAttr("disabled");
            });
            var mediaInfo = $("#formId").serialize();

            $.ajax({
                url: "/setMeal/setMeal/saveAll",
                type: "POST",
                data: mediaInfo,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                    window.location.href = "/setMeal/setMeal/query";
                    } else {
                        Notify.danger(data.message);
                        // location.reload();
                    }
                },
            });
        });

//        $(".updateStatus").on("change",function(){
//            var status = $(".updateStatus").val();
//            if(status == 0){
//
//                $.ajax({
//                    url: "/setMeal/setMeal/queryDefault",
//                    type: "POST",
//                    data: {"isvalid":status},
//                    dataType: "json",
//                    success: function (data) {
//                        if (data.code == 0) {
//                        } else {
//                            Notify.danger("套餐已有默认，编号是："+data.message);
//                        }
//                    }
//                });
//            }
//        });

        $(".queryDefault").on("change",function(){
            var type = $("#mealType").val();
            var weekDay = $("#weekDayId").val();
            var queryDefaultValue = $("#queryDefaultValue").val();

            if(queryDefaultValue == 1) {
                $.ajax({
                    url: "/setMeal/setMeal/queryDefault",
                    type: "POST",
                    data: {"type": type, "weekDay": weekDay},
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            // Notify.success(data.message);
                            // window.location.href = "/setMeal/setMeal/query";
                        } else {
                            Notify.danger("套餐已有默认，编号是：" + data.message);
                            // location.reload();
                        }
                    },
                });
            }
        });

        $("#mealCode").on("change",function(){
            var mealCode = $("#mealCode").val();
            $.ajax({
                url: "/setMeal/setMeal/queryCode",
                type: "POST",
                data: {"code":mealCode},
                dataType: "json",
                success: function (data) {
                    if (data.code != 0) {
                        Notify.danger(data.message);
                    }
                }
            });
        });
    });
    <#if mealMaterial??>
    var parentNumber =${mealMaterial?size};
    <#else >
    var parentNumber = 0;
    </#if>

        function addTr(tab, row) {
            var trHtml = '<tr><td>'+
                    '<input readonly id="materialCap' + parentNumber + '" onclick="getMaterial('+"'"+'materialCap' + parentNumber +"'"+','+"'"+'headMeal' + parentNumber+"'"+')" value="" class="form-control" data-toggle="modal" data-target="#modal" data-url="">' +
                    '<input readonly name="details[' + parentNumber + '].materialId" id="headMeal' + parentNumber + '" value="" type="hidden">'+
                    '</td>'+
                    '<td><input type="number" title="" name="details[' + parentNumber + '].gram" class="form-control">&nbsp;&nbsp;g</td>'+
                    '<td ><a onclick="deleteRow(tab,this.parentElement.parentElement.rowIndex,'+ parentNumber +')" class="fa add_icon">删除</a></tr>';
            addTr2(tab, row, trHtml);
            parentNumber++;
        }

//
    //删除一行
    function deleteRow(tableID, rowIndex,num) {

        tableID.deleteRow(rowIndex);

    }

        //被插入的行索引
        function addTr2(tab, row, trHtml) {
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

    function del() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassinfo;

        if (id == null) {
            Notify.danger("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/classinfo/classinfo/delete",
                data: {"pkClassinfo": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(data.message)
                    }
                },
                error: function () {

                }
            });
        }

    }

    function getMaterial(id,id2){
//        var id = $(this).attr("id");
//        var id2 = $(this).next().attr("id");
        var url="/setMeal/setMeal/getMaterial?ver=1";
        $("#"+id).attr("data-url",url+"&id1="+id+"&id2="+id2);
        return true;
    }
</script>

