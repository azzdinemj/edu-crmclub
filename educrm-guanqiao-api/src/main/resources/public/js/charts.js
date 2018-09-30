$(document).ready(function(){
    var myChart = echarts.init(document.getElementById('charts'));
    option = {
        title : {
            text: '成交统计'

        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['新增学员','缴费学员']
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
                name:'新增学员',
                type:'bar',
                data:[11, 13, 15, 14,12, 10, 14, 15, 8, 3,9, 5]
            },
            {
                name:'缴费学员',
                type:'bar',
                data:[3, 5, 6, 8,10, 5, 7, 3, 8, 3,5, 1]
            }
        ]
    };

    myChart.setOption(option);
});
$(document).ready(function(){
    var myChart = echarts.init(document.getElementById('charts2'));
    option = {
        title : {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'},
                    {value:234, name:'联盟广告'},
                    {value:135, name:'视频广告'},
                    {value:1548, name:'搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    myChart.setOption(option);
});