<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />

<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>一年级01班<span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-success" href="">导出</a>
        </div>
    </div>
</div>
<div class="contentpanel">
    <div class="panel panel-default">
        <div class="panel-body pd0">
            <div class="p15">
                <div class="col-md-6">
                    <div class="leftchearts">
                        <div class="row mt30 bor">
                            <div class="col-md-6">
                            <#--<h3>上学情况</h3>-->
                                <select id="fs">
                                    <option value="1"> 上学方式</option>
                                    <option value="2"> 放学方式</option>
                                </select>

                            </div>
                            <div class="col-md-6 text-right">
                                <h3>总人数：120</h3>
                            </div>
                        </div>
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="2018-05-16" />
                            </div>

                            <div class="form-group">
                                <label></label>
                                <div class="btn-group">
                                    <a id="queryStudent" href=""></a> <button type="button" onclick="query()" class="btn btn-success btn-sm">搜索</button>
                                </div>
                            </div>
                        </form>
                        <#--<div id="main" style="width: 100%;height:400px;"></div>-->
                        <img id="fs1" style="margin-left:50px" src="${staticPath}/images/junhua/sxfs.png">
                        <img id="fs2" style="display:none;margin-left:50px" src="${staticPath}/images/junhua/fxfs.png">

                    </div>
                </div>
                <div class="col-md-6">
                    <div class="rightechearts">
                        <div class="row mt30 bor">
                            <div class="col-md-6">
                                <#--<h3>上学情况</h3>-->
                                <select id="sd">
                                    <option value="1" > 上学时段</option>
                                    <option value="2"> 放学时段</option>
                                </select>
                            </div>
                            <div class="col-md-6 text-right">
                                <h3>总人数：120</h3>
                            </div>
                        </div>
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <input type="text" title=""  name="reservation" id="datas2" class="form-control form-input-lg" value="2018-05-16" />
                            </div>

                            <div class="form-group">
                                <label></label>
                                <div class="btn-group">
                                    <a id="Student" href=""></a> <button type="button" onclick="query()" class="btn btn-success btn-sm">搜索</button>
                                </div>
                            </div>
                        </form>
                        <#--<div id="main2" style="width: 100%;height:400px;"></div>-->
                        <img id="sd1" style="margin-left:50px" src="${staticPath}/images/junhua/sxsd.png">
                        <img id="sd2" style="display:none;margin-left:50px" src="${staticPath}/images/junhua/fxsd.png">

                    </div>
                </div>
            </div>
        </div><!-- panel-body -->

    </div><!-- panel -->

</div>

<#include "${staticPath}/commons/footer.ftl" />


<script>
    //方式
    $("#fs").change(function () {
        var v = $("#fs option:selected").val();
        for(i=1;i<=2;i++){
            if(v==i){
                $("#fs"+i).show();
            }else{
                $("#fs"+i).hide();
            }
        }
    })

    //时段
    $("#sd").change(function () {
        var v = $("#sd option:selected").val();
        for(i=1;i<=2;i++){
            if(v==i){
                $("#sd"+i).show();
            }else{
                $("#sd"+i).hide();
            }
        }
    })


</script>


<script>
    var myChart = echarts.init(document.getElementById('main'));

    option = {
        tooltip: {
            trigger:"item",
            formatter:"{a}<br/>{b}:{c}({d}%)"
        },
        legend:{
            orient:"vertical",
            y:"bottom",
            data:["家长接","校车接"]
        },
        series:[{
            name:"接送统计",
            type:"pie",
            radius:['50%','70%'],
            avoidLabelOverlap:false,
            label:{
                normal:{
                    show:false,
                    position:"center"
                },
                emphasis:{
                    show:true,
                    textStyle:{
                        fontSize:"30",
                        fontWeight:'bold'
                    }
                }
            },
            labelLine:{
                normal:{
                    show:false
                }
            },
            data:[
                {value:100,name:'家长接'},
                {value:335,name:'校车接'}
            ]
        }]

    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

</script>
<script>
    var myChart = echarts.init(document.getElementById('main2'));
    option = {
        tooltip: {
            trigger:"item",
            formatter:"{a}<br/>{b}:{c}({d}%)"
        },
        legend:{
            orient:"vertical",
            y:"bottom",
            data:["正常","异常"]
        },
        series:[{
            name:"",
            type:"pie",
            radius:['50%','70%'],
            avoidLabelOverlap:false,
            label:{
                normal:{
                    show:false,
                    position:"center"
                },
                emphasis:{
                    show:true,
                    textStyle:{
                        fontSize:"30",
                        fontWeight:'bold'
                    }
                }
            },
            labelLine:{
                normal:{
                    show:false
                }
            },
            data:[
                {value:100,name:'正常',itemStyle:{color:"#2898E0"}},
                {value:335,name:'异常',itemStyle:{color:"orange"}}
            ]
        }]

    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

</script>