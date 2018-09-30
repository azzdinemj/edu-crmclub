<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

<#assign staticPath=''>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${staticPath}/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css">

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<#include "common/container.ftl">
</nav>
<section class="class_center" style="min-font-size: 650px">
    <div class="container">
        <div class="course-filter">
            <div class="search-by by-category relative">
                <dl class="relative clearfix">
                    <dt class="floatLeft">分类：</dt>
                    <dd class="floatLeft con2 show-con">
                        <a href="">生产技能</a>
                        <a href="">安全管理</a>
                        <a href="">企划运营</a>
                        <a href="">工程建设</a>
                        <a href="">人力资源</a>
                        <a href="">党政管理</a>
                    </dd>
                </dl>
                <dl class="relative clearfix">
                    <dt class="floatLeft">分类：</dt>
                    <dd class="floatLeft con1 show-con">
                        <a href="/course/query" class="all active">全部</a>
                    <#if dict??>
                        <#list dict as d>
                            <a href="/course/query?types=${(d.pkSysDictValues)!}" class=" ${(d.pkSysDictValues)!}">${(d.caption)!}</a>
                        </#list>
                    </#if>
                    </dd>
                </dl>
                <dl class="relative clearfix">
                    <dt class="floatLeft">排序：</dt>
                    <dd class="floatLeft con2 show-con">
                        <a href="/course/query?date=desc" class="date">最新</a>
                        <a href="/course/query?date2=desc" class="hot">最热</a>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="row">

        <#if course??>
            <#if course.list??>
                <#list course.list as v>

                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="cardn">
                            <a href="/course/query?pkCourse=${(v.pkCourse)!}">
                                <div class="card-content">
                                    <div class="text-center mb4">
                                        <img class="bg-light-purple circle card__icon" onerror="this.src='/images/img/no.jpg'" src="${staticPath}${(v.thumb)!}.thumb.jpg" alt="Hosting icon">
                                    </div>
                                    <div class="card-text">
                                        <h3 class="card-title">${(v.caption)!}</h3>
                                        <div class="item-list">
                                            <span class="card-left">讲师：<em>${(v.map.teacherName)!}</em></span>
                                            <span class="card-right">课时：<em>${(v.classes)!}</em></span>
                                        </div>
                                        <div class="item-list last">
                                            <span class="card-left icon ion-ios-eye"><em>${(v.num)!}</em></span>
                                        <#--  <span class="card-right icon ion-play"><em>200</em></span>-->
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>

                </#list>
            </#if>
        </#if>


        </div>
    </div>
</section>
<#include "page.ftl" />
<#include  "common/footer.ftl">
<script src="${staticPath}/js/jquery-1.10.2.js"></script>

<script src="${staticPath}/js/bootstrap.min.js"></script>

<script src="${staticPath}/js/over.js"></script>

</body>
</html>
<script>


    $(function () {
        <#if type??>
             $(".all").removeClass("active");
             $(".${(type)!}").addClass("active");//类型
        </#if>
        <#if hot??>
            $(".all").removeClass("active");
            $(".${(hot)!}").addClass("active");// 热度
        </#if>
        <#if date??>
            $(".all").removeClass("active");
            $(".${(date)!}").addClass("active");//最新
        </#if>
    })



</script>

