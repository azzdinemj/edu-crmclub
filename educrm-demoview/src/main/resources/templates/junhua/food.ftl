<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />

<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>选餐记录 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-success" href="/junhua/nutritionalAnalyze/nutritionalAnalyze">查看营养分析</a>
        </div>
    </div>
</div>
<div class="contentpanel">

    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                    </div>

                    <div class="form-group">
                        <label></label>
                        <div class="btn-group">
                            <a id="queryStudent" href=""></a> <button type="button" onclick="query()" class="btn btn-success btn-sm">搜索</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>套餐</th>
                        <th>明细</th>
                        <th>时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>套餐A</td>
                        <td>猪肉5g，白菜20g 米饭150g</td>
                        <td>2018-02-02</td>
                    </tr>
                    <tr>
                        <td>套餐B</td>
                        <td>鱼肉5g，白菜20g 米饭150g</td>
                        <td>2018-02-02</td>
                    </tr>
                    <tr>
                        <td>套餐C</td>
                        <td>鸡胸肉5g，白菜20g 米饭150g</td>
                        <td>2018-02-02</td>
                    </tr>
                    <tr>
                        <td>套餐D</td>
                        <td>牛肉5g，白菜20g 面条150g</td>
                        <td>2018-02-02</td>
                    </tr>
                    <tr>
                        <td>套餐E</td>
                        <td>猪肉5g，青菜20g 米饭150g</td>
                        <td>2018-02-02</td>
                    </tr>
                    <tr>
                        <td>套餐F</td>
                        <td>猪肉15g，白菜200g 米饭150g</td>
                        <td>2018-02-02</td>
                    </tr>
                    <tr>
                        <td>套餐G</td>
                        <td>猪肉15g，白菜200g 米饭150g</td>
                        <td>2018-02-02</td>
                    </tr>
                    <tr>
                        <td>套餐H</td>
                        <td>猪肉15g，白菜200g 米饭150g</td>
                        <td>2018-02-02</td>
                    </tr>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div>

<#include "${staticPath}/commons/footer.ftl" />
<script type="text/javascript">

</script>