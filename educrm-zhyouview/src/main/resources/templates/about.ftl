<!DOCTYPE html>
<html lang="en">
<head>
<#assign staticPath=''>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${staticPath}/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css">

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<#include "common/container.ftl">
</nav>
<div class="banner">
    <img src="${staticPath}/images/u46.jpg">
</div>
<section class="class_center">
    <div class="container">

        <div class="row">

            <div class="col-xs-12 col-sm-12 col-md-12">
                <div id="myTabContent" class="tab-content abouttext">
                    <div class="tab-pane active" id="one">
                        <h3>关于我们</h3>
                        <section id="article" class="mod g-section">
                            <div class="bd">
                               <div class="content short about-introduce">

                                 <p>
                                     中国航油华东公司成立于1990年11月，隶属于中国航空油料有限责任公司，主要经营范围是航空油料的销售以及技术咨询、服务仓储等。华东公司地处我国经济文化最发达地区，是中国航油集团公司核心主营板块——航油板块的重要组成部分，是员工数量最多、加油量增长速度最快、生产任务最重的地区公司，是集团公司范围内点多、面广、线长特点最为突出的地区公司，也是集团公司经营保障机场数量最多的地区公司。2018年，华东公司下辖10个分公司，12个炼厂办事处，在职员工约1500人。

                                 </p>
                                   <br>
                                   <div class="row">
                                       <div class="col-md-4">
                                           <p>培训总体原则：</p>
                                           <p>统筹规划</p>
                                           <p>分级管理</p>
                                           <p>分类实施</p>
                                           <p>按需施教</p>
                                           <p>学以致用</p>
                                           <p>全员参与</p>
                                       </div>
                                       <div class="col-md-8">
                                          <img src="../images/abouts.png" width="300">
                                       </div>
                                   </div>


                               </div>

                            </div>

                        </section>
                    </div>
                   <#-- <div class="tab-pane" id="two">
                        <h3>第二章节</h3>
                    </div>
                    <div class="tab-pane" id="three">
                        <h3>第三章节</h3>
                    </div>-->

                </div>
            </div>


        </div>

    </div>
</section>
<#include  "common/footer.ftl">

<script src="${staticPath}/js/jquery.min.js"></script>
<script src="${staticPath}/js/bootstrap.js"></script>


<!--<script src="js/main.js"></script>-->

</body>
</html>