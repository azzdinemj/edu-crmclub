<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />
<div class="pageheader">
    <div class="header">
        <div class="icon-stu">
            <img src="${staticPath}/images/photos/blog3.jpg">
            <p>mike</p>
            <p>四年级1002班</p>

            <div class="btn-group fr" style="margin-right: 45px;">
                <a href=""></a> <button type="button" onclick="query()" class="btn btn-success btn-sm">导出</button>
            </div>
        </div>
    </div>
</div>
<div class="contentpanel">
    <div class="panel panel-default">
        <!--  <ul id="myTab" class="nav nav-tabs">
              <li class="active"><a href="#one" data-toggle="tab">成绩</a></li>
              <li><a href="#two" data-toggle="tab">活动</a></li>
              <li><a href="#three" data-toggle="tab">综合素质</a></li>
              <li><a href="#four" data-toggle="tab">竞赛</a></li>
          </ul>-->
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="one">
                <h3 class="mb30">考试成绩</h3>
                <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>考试名称</th>
                        <th>语文</th>
                        <th>数学</th>
                        <th>英语</th>
                        <th>德育</th>
                        <th>美术</th>
                        <th>音乐</th>
                        <th>总成绩</th>
                        <th>老师评语</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>一年级入学考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>一年级期中考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>一年级期末考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>二年级期中考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>二年级期末考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>三年级期中考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>三年级期末考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>三年级期中考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>三年级期末考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>三年级期中考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>三年级期末考试</td>
                        <td>A</td>
                        <td>C</td>
                        <td>B</td>
                        <td>D</td>
                        <td>A</td>
                        <td>A</td>
                        <td>B</td>
                        <td><a title="表现很优秀,热爱班级体，成绩稳步增长，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    </tbody>
                </table>

                <h3 class="mb30">成绩曲线</h3>
                <div class="row" style="">
                    <label class="col-sm-8"></label>
                    <div class="col-sm-4">
                    <select id="cjSelect" class="selectpicker show-tick form-control"  data-live-search="true">
                        <option value="0"> 全部</option>
                        <option value="1"> 语文</option>
                        <option value="2"> 数学</option>
                        <option value="3"> 德育</option>
                        <option value="4"> 美术</option>
                        <option value="5"> 音乐</option>
                        <option value="6"> 英语</option>
                    </select>
                    </div>
                </div>
                <img id="cj3" style="display:none;width: 100%;height:500px;" src="${staticPath}/images/junhua/DEYU.png">
                <img id="cj2" style="display:none;width: 100%;height:500px;" src="${staticPath}/images/junhua/SHUXUE.png">
                <img id="cj4" style="display:none;width: 100%;height:500px;" src="${staticPath}/images/junhua/MEISHU.png">
                <img id="cj6" style="display:none;width: 100%;height:500px;" src="${staticPath}/images/junhua/YINGYU.png">
                <img id="cj1" style="display:none;width: 100%;height:500px;" src="${staticPath}/images/junhua/YUWEN.png">
                <img id="cj5" style="display:none;width: 100%;height:500px;" src="${staticPath}/images/junhua/YINGYUE.png">
                <img id="cj0" style="width: 100%;height:500px;" src="${staticPath}/images/junhua/cjqx.png">
                <#--<div id="main" style="width: 100%;height:400px;"></div>-->
                <h3 class="mb30">知识点分析（错误率）</h3>
                <div class="row" style="">
                    <label class="col-sm-8"></label>
                    <div class="col-sm-4">
                        <select  class="selectpicker show-tick form-control"  data-live-search="true">
                            <option > 语文</option>
                            <option > 数学</option>
                        </select>
                    </div>
                </div>
                <div id="main1-2" style=" display: inline-block;width: 85%;height:500px;"></div>
                <div  style=" background-color:white;display: inline-block;width: 10%;height:500px;margin-top: 0px">
                    <br><br><br>
                    95%<br><br><br>
                    90%<br><br><br>
                    86%<br><br><br>
                    82%<br><br><br>
                    80%<br><br><br>
                    82%<br><br><br><br>
                    75%<br><br><br>
                </div>
                <h3 class="mb30">活动记录</h3>
                <table   class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>活动名称</th>
                        <th>年级</th>
                        <th>活动角色</th>
                        <th>活动时间</th>
                        <th>获得荣誉</th>
                        <th>教师评语</th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>植树节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>劳动节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>端午节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>中秋节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>清明节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>清明节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>清明节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>清明节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    </tbody>
                </table>

                <h3 class="mb30">综合素质</h3>
               <img  style="display: block;margin: auto;height:500px ;width: 600px;"  src="${staticPath}/images/junhua/LEIDA.png">
            <#--<div id="main2" style="width: 100%;height:400px;"></div>-->


                <h3 class="mb30">竞赛记录</h3>
                <table   class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>活动名称</th>
                        <th>年级</th>
                        <th>活动角色</th>
                        <th>活动时间</th>
                        <th>获得荣誉</th>
                        <th>教师评语</th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>植树节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>劳动节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>植树小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>
                    </tr>
                    <tr>
                        <td>清明节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>放风筝小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>国庆节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>护旗小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>中秋节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>吃月饼小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>国庆节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>护旗小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    <tr>
                        <td>中秋节活动试</td>
                        <td>一年级</td>
                        <td>小组长</td>
                        <td>2018-02-02</td>
                        <td>吃月饼小卫士</td>
                        <td><a title="表现很优秀,热爱班级体，充分表现了竞赛精神，值得表扬再接再厉" style="color: black">查看</a></td>

                    </tr>
                    </tbody>
                </table>

            </div>

        </div>

    </div><!-- panel -->

</div>

<#include "${staticPath}/commons/footer.ftl" />
<script>
    //成绩曲线图
    $("#cjSelect").change(function () {
        var v = $("#cjSelect option:selected").val();
        for(i=0;i<=6;i++){
            if(v==i){
                $("#cj"+i).show();
            }else{
                $("#cj"+i).hide();
            }
        }
    })

</script>

<script>
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        xAxis: {
            type: 'category',
            data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

</script>
<script>
    var myChart = echarts.init(document.getElementById('main2'));
    option = {
        xAxis: {
            type: 'category',
            data: ['德', '智', '体', '美']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934],
            type: 'line',
            smooth: true
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

</script>
<script>
    var myChart = echarts.init(document.getElementById('main1-2'));
    option = {
        tooltip: {
            trigger:"axis",
            //formatter:"{a}<br/>{b}:{c}({d}%)",
            formatter:"{a}<br/>{b}:{c}({d}%)",
            axisPointer:{
                type:"shadow"
            }
        },
        legend:{
            data:["2018"]
        },
        grid:{
            left:"3%",
            right:"5%",
            bottom:"3%",
            containLabel:true
        },
        xAxis: {
            type: 'value',
            boundaryGap:[0,0.01]
        },
        yAxis: {
            type: 'category',
            data: ['一年级语文成语', '一年级语文造句', '二年级语文成语', '二年级语文诗词', '三年级语文成语', '三年级语文成语', '三年级语文成语']

        },
        series: [{
            name:"2018",
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'bar',
            smooth: true
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

</script>