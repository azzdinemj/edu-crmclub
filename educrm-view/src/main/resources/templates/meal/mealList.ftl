
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>套餐管理 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" href="/setMeal/setMeal/create" >添加</a>
                        <#--<a class="btn btn-sm btn-info" href="">修改</a>-->
                        <a href="" class="btn btn-sm btn-newblue" onclick="del()"> 删除 </a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <form class="form-inline search white">
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">日期 :</label>-->
                                <#--<input type="text" title=""  name="reservation"  class="form-control" value="" />-->
                            <#--</div>-->
                            <div class="form-group">
                                <label class="control-label">套餐 :</label>
                                <#--<select class="form-control strw" id="code">-->
                                    <#--<option value="">全部</option>-->
                                <#--<#if (englishCode?size > 0)>-->
                                    <#--<#list englishCode as d>-->
                                        <#--<option value="${(d.caption)!}">${(d.caption)!}</option>-->
                                    <#--</#list>-->
                                <#--</#if>-->
                                <#--</select>-->
                                <input type="text" oninput="if(value.length>2)value=value.slice(0,2)" onKeyUp="value=value.replace(/[\W]/g,'')" title="" id="code" class="form-control" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">类别 :</label>
                                <select class="form-control strw" id="type">
                                    <option value="">全部</option>
                                    <option value="0">早餐</option>
                                    <option value="1">午餐</option>
                                    <option value="2">晚餐</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label></label>
                                <div class="btn-group">
                                    <button type="button" onclick="query()"  class="btn btn-newblue btn-sm">查询</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <#--<tr>-->
                                <#--<th>套餐</th>-->
                                <#--<th>套餐名称</th>-->
                                <#--<th>套餐费用（元）</th>-->
                                <#--<th>日期</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<tr>-->
                                <#--<td><a href="" class="colorinfo" >A</a></td>-->
                                <#--<td>芹菜鱿鱼套餐（紫菜汤、米饭）</td>-->
                                <#--<td>25</td>-->
                                <#--<td>2018-06-21 08:30</td>-->
                            <#--</tr>-->
                            <#--<tr>-->
                                <#--<td><a href="" class="colorinfo" >A</a></td>-->
                                <#--<td>芹菜鱿鱼套餐（紫菜汤、米饭）</td>-->
                                <#--<td>25</td>-->
                                <#--<td>2018-06-21 08:30</td>-->
                            <#--</tr>-->
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div>
        <!--添加部分结束==============================================-->


    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script>
    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "code": $("#code").val(),"type":$("#type").val()};
        init(GetTableColumn("setMeal"),"/setMeal/setMeal/queryByPaging",data,"pagingTable");
    }

    function renderMealEdit(data, type, row) {
        return '<a href="/setMeal/setMeal/edit?pkSetMeal='+row.pkSetMeal+'" class="yellow" >'+data+'</a>';
    }

    function del() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkSetMeal;

        if (id == null) {
            Notify.danger("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/setMeal/setMeal/delete",
                data: {"pkSetMeal": id},
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
</script>
