
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>总校报表<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" data-toggle="tab">总校</a>
                <a class="btn btn-sm btn-newblue" href="/table/tableschool/query2" >直营</a>
                <a class="btn btn-sm btn-newblue" href="/table/tableschool/query3" >加盟</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">名称 :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>
                </form>
            </div>
            <div class="charts">
                <div class="data student-charts" id="main-market" style="height: 400px; width: 100%">
                </div>
            </div>
            <div class="charts">
                <div class="data student-charts" id="main-market_" style="height: 400px; width: 100%"">
            </div>
        </div>
    </div>

<#include "../commons/footer.ftl"/>


<script type="text/javascript">

    function del() {
        var id = $(".table-color-").attr("dataid");

        if(id ==null){
            Notify.danger("请先选择要删除的数据");
            return;
        }
        var flag = confirm("确认删除吗？");
//        if (flag) {
//            $.ajax({
//                type: "POST",
//                url: "/system/dormRoom/delete",
//                data: {"pkDormRoom": id},
//                dataType: "json",
//                success: function (data) {
//                    if (data.code == 0) {
//                        Notify.success(data.message);
//                        setTimeout("location.reload()", 1);
//                    } else {
//                        Notify.danger(data.message);
//                        window.location.reload();
//                    }
//                },
//                error: function () {
//
//                }
//            });
//        }


    }

    function query() {
        var caption=$("#captionQuestions").val();
        var type=$("#typeQuestions option:selected").val();
        $("#queryQuestions").attr("href","/question/questionlist/query?status="+type+"&&title="+caption)[0].click();
    }
    /**
     * Created by Administrator on 2017/8/29 0029.
     */

    /*1*/
    $(document).ready(function(){


        var myChart = echarts.init(document.getElementById('main-market'));

option = {
/* title : {
text: '市场'

},*/
tooltip : {
trigger: 'axis'
},
legend: {
data:['总收入']
},
    itemStyle:{
        normal:{
            color:'#4ad2ff'
        }
    },
toolbox: {
show : true,
feature : {
dataView : {show: true, readOnly: false},
magicType : {show: true, type: ['line', 'bar']},
restore : {show: true},
saveAsImage : {show: true}
}
},
calculable : true,
xAxis : [
{
type : 'category',
data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
}
],
yAxis : [
{
type : 'value'
}
],
series : [
{
name:'总收入',
type:'bar',
data:[11, 13, 15, 14,12, 10, 14, 15, 8, 3,9, 5]
}
]
};

        myChart.setOption(option);
        myChart.resize();
});
    $(document).ready(function(){
        var myChart = echarts.init(document.getElementById('main-market_'));
        option = {
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['报名学生']
            },

            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'报名学生',
                    type:'bar',
                    data:[11, 13, 15, 14,12, 10, 14, 15, 8, 3,9, 5]
                }
            ]
        };

        myChart.setOption(option);
        myChart.resize();
    });

</script>

