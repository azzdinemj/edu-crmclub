<#include "../commons/top.ftl">
<#include "../commons/left.ftl">

    <div class=""><!--mainpanel-->
        <!--添加部分===========================================-->
        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>营养分析 <span>-</span>${(studentName)!}</h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-info" href="">返回</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">时段 :</label>
                                <input type="text" name="yearAndMonth" value="${yearAndMonth}" class="form-control js-monthpicker"/>
                            </div>

                            <div class="form-group">
                                <label></label>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-info btn-sm" onclick="aa(yearAndMonth.value)">查询</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>

                <div class="panel-body pd0">
                    <#--<div class="table-responsive">-->
                        <#--<p class="tc"> 选餐情况：套餐A : 30 套餐B : 20 套餐C : 20</p>-->
                    <#--</div><!-- table-responsive &ndash;&gt;-->
                    <div id="echart" style="width: 100%;height: 400px"></div>
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div>
        <!--添加部分结束==============================================-->
    </div><!-- mainpanel -->

<#include "../commons/footer.ftl" >
<script type="text/javascript" src="${staticPath}/js/echarts.js"></script>

<script type="text/javascript">
    var path = window.document.location.href.substring(0, window.document.location.href.indexOf(window.document.location.pathname)) + window.document.location.pathname.substring(0, window.document.location.pathname.substr(1).indexOf('/') + 1);
    var url = path + '/trophicAnalysis/findList';
    var dom = document.getElementById('echart');
    var myChart = echarts.init(dom);
    $(function () {
        $.ajax({
            url: url,
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                myChart.setOption(data);
            },
            error : function() {
                alert('请求失败 ');
            }
        });
    });

    function aa(value) {
        $.ajax({
            url: url,
            type: 'post',
            async: false,
            data: {"yearAndMonth": value},
            dataType: 'json',
            success: function (data) {
                console.log(data);
                // var option = eval("(" + data + ")");
                //
                myChart.setOption(data);
            },
            error : function() {
                alert('请求失败 ');
            }
        });
    }
</script>