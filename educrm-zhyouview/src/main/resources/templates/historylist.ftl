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

<section class="class_center">
    <div class="text-nav">
        <div class="container">
            <div class="row">
                <div class="col-xs-6">
                    <h3></h3>
                </div>
                <div class="col-xs-6">
                    <div class="thispage-nav-item">
                        <a href="/questions/question_">练习</a>
                        <a  class="item-active" href="/questionsTest/history">历史练习</a>
                        <a  href="/questionsTest/historyerror">错题本</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="container">

        <div class="row">
            <div class="col-xs-12 ">
                <div id="myTabContent" class="tab-content center-content">
                    <div class="tab-pane active" id="two">

                    <#if qTesthistorylist??  &&(qTesthistorylist?size > 0)  >

                        <div class="two-title">
                            ${qTesthistorylist?size}条记录
                        </div>
                        <#list qTesthistorylist as v>
                        <div class="lx form-group">
                            <a href="/questionsTest/history?testid=${(v.testid)!}">
                                <div class="title">
                                    <div class="col-xs-8"> ${v.createDate?string("yyyy-MM-dd HH:mm:ss ")}练习</div>
                                    <div class="col-xs-4">
                                        <span>${(v.map.QtSize)!}道</span>
                                        <#--<span class="poright">2018-08-08</span>-->
                                    </div>
                                </div>
                            </a>
                        </div>
                        </#list>
                    <#else>
                        <div class="two-title">
                            暂无测试记录
                        </div>
                    </#if>



                    </div>


                </div>
            </div>


        </div>

    </div>
</section>
<#include  "common/footer.ftl">
<script src="${staticPath}/js/jquery.min.js"></script>
<script src="${staticPath}/js/bootstrap.min.js"></script>
<script src="${staticPath}/js/over.js"></script>



</body>
</html>