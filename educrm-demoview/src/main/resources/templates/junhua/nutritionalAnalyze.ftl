<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />

<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>营养分析 <span>...</span></h2>

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
            <div class="p15">
                <h3 class="mb30 ml15">李卫国同学  <span style="margin-left: 20px;">选餐情况：套餐A : 30   套餐B : 20   套餐C : 20</span></h3>
                <div id="main" style="width: 100%;height:400px;"></div>
                <h4>
                    营养建议：蛋白质摄入量过低，能量热摄入量过多，建议减少套餐中减少米饭用量，增加鸡肉、牛肉等高蛋白质低脂肪的食物。
                </h4>
            </div>
        </div><!-- panel-body -->

    </div><!-- panel -->

</div>

<#include "${staticPath}/commons/footer.ftl" />
<script>
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        xAxis: {
            type: 'category',
            data: ['能量', '蛋白质', '钙', '磷', '钾', '钠', '镁','va','vy','铁']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320,4534,555,2555],
            type: 'bar',
            smooth: true
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

</script>