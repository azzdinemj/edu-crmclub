
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>套餐统计 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <#--<a class="btn btn-sm btn-info" href="">修改</a>-->
                        <#--<a class="btn btn-sm btn-info" href="">删除</a>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <input type="text" title="" id="fromDateString"  name="reservation"  class="form-control js-datepicker" value="${.now?string["yyyy-MM-dd"]}" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">类别 :</label>
                                <select class="form-control strw" id="types">
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
        var data  = { "fromDateString": $("#fromDateString").val(),"type":$("#types").val()};
        init(GetTableColumn("setMealStatistics"),"/setMeal/mealStatistics/queryByPaging",data,"pagingTable");
    }

//    function renderMealEdit(data, type, row) {
//        return '<a href="/setMeal/setMeal/edit?pkSetMeal='+row.pkSetMeal+'" class="yellow" >'+data+'</a>';
//    }
</script>
