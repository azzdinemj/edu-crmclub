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
    <div class="container">

        <div class="row">
            <div class="col-xs-12 col-sm-3 col-md-3">
                <div class="left-nav">
                    <h3 class="class-title">个人中心</h3>
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active"><a href="#one" data-toggle="tab">我的课程</a></li>


                    </ul>
                </div>
            </div>


            <div class="col-xs-12 col-sm-9 col-md-9">
                <div id="myTabContent" class="tab-content center-content">
                    <div class="tab-pane active" id="one">
                        <h4>我的课程</h4>
                        <div class="row">

                        <#if courseList??>
                            <#list courseList as c>
                            <div class="col-xs-12 col-sm-6 col-md-4">
                                <div class="cardn">
                                    <a href="/course/query?pkCourse=${(c.pkCourse)!}" class="">
                                        <div class="card-content">
                                            <div class="text-center mb4">

                                                <img class="bg-light-purple circle card__icon" alt='test' onerror="this.src='/images/img/no.jpg'" src="${staticPath}${(c.thumb)!}.thumb.jpg" alt="Hosting icon">

                                            </div>
                                            <div class="card-text">
                                                <h3 class="card-title">${(c.caption)!}</h3>
                                                <div class="item-list">
                                                    <span class="card-left">讲师：<em>${(c.map.teacherName)!}</em></span>
                                                    <span class="card-right">时长：<em>${(c.classes)!}</em></span>
                                                </div>
                                                <div class="item-list last">
                                                    <span class="card-left icon ion-ios-eye"><em>${(c.num)!}</em></span>
                                                  <#--  <span class="card-right icon ion-play"><em>200</em></span>-->
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            </#list>
                        </#if>

                        </div>
                    </div>

                    <#--<div class="tab-pane" id="two">-->
                        <#--<h3>第二章节</h3>-->
                    <#--</div>-->
                    <#--<div class="tab-pane" id="three">-->
                        <#--<h3>第三章节</h3>-->
                    <#--</div>-->

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