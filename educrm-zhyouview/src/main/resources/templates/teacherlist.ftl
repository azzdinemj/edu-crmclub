<!DOCTYPE html>
<#assign staticPath=''>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${staticPath}/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${staticPath}/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css">

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<#include "common/container.ftl">
</nav>
<section class="class_center">
    <div class="teacher">
        <div class="container">
            <div class="left col-md-4 col-xs-12">

                <img class="img-tim" src="${staticPath}${(employee.address)!}.thumb.jpg" onerror="this.src='/images/img/no2.jpg.thumb.jpg'">

            </div>
            <div class="right col-xs-12 col-md-8">
                <h3>${(employee.caption)!}</h3>
                <p>性别：${(employee.sex)!}</p>
                <p>年龄：${(employee.code)!}</p>
                <p>座右铭：${(employee.memo)!}</p>
            </div>

        </div>
    </div>
    <div class="container">
        <div class="class">
            <h3>全部课程</h3>
            <div class="row">
                <#if courseList?? &&courseList.list??>
                    <#list courseList.list as v >

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
                                             <span class="card-right">时长：<em>${(v.time)!}</em></span>
                                         </div>
                                         <div class="item-list last">
                                             <span class="card-left icon ion-ios-eye"><em>${(v.num)!}</em></span>
                                             <#--<span class="card-right icon ion-play"><em>200</em></span>-->
                                         </div>
                                     </div>
                                 </div>
                             </a>
                         </div>
                     </div>

                    </#list>
                </#if>

                <#--<div class="col-xs-12 col-sm-6 col-md-3">-->
                    <#--<div class="cardn">-->
                        <#--<a href="class_details.html">-->
                            <#--<div class="card-content">-->
                                <#--<div class="text-center mb4">-->
                                    <#--<img class="bg-light-purple circle card__icon" src="${staticPath}/images/class.jpg" alt="Hosting icon">-->
                                <#--</div>-->
                                <#--<div class="card-text">-->
                                    <#--<h3 class="card-title">课程课程课程课程课课程课程课程课程</h3>-->
                                    <#--<div class="item-list">-->
                                        <#--<span class="card-left">讲师：<em>老师</em></span>-->
                                        <#--<span class="card-right">时长：<em>1:05:30</em></span>-->
                                    <#--</div>-->
                                    <#--<div class="item-list last">-->
                                        <#--<span class="card-left icon ion-ios-eye"><em>200</em></span>-->
                                        <#--<span class="card-right icon ion-play"><em>200</em></span>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</a>-->
                    <#--</div>-->
                <#--</div>-->
            </div>
        </div>
    </div>
</section>
<!--course为pageinfo对象-->
<!--分页页码-->
<#if courseList??&&courseList.list??>
<ul class="pagination"  style="margin-left: 12%"  id="page">
    <li><a href="/teacher/edit?pageNo=${courseList.prePage}&&pkEmployee=${employee.pkEmployee}">&laquo;</a></li>
<#assign tp=courseList.pages/>
<#assign p=courseList.pageNum/>
<#assign sp=p-3/>
<#assign ep=p+4/>
<#assign eoff=ep-tp/>
<#if (eoff>0)>
    <#assign sp = sp - eoff/>
</#if>
<#if (sp<=0)>
    <#assign ep = ep - sp+1/>
</#if>
<#list sp..ep as x>
    <#if (x>0 && x<=tp)>
     <li ><a href="/teacher/edit?pageNo=${x}&&pkEmployee=${employee.pkEmployee}">${x}</a></li>
    </#if>
</#list>
    <li ><a href="/teacher/edit?pageNo=${courseList.nextPage}&&pkEmployee=${employee.pkEmployee}">&raquo;</a></li>
</ul>

</#if>

<#include  "common/footer.ftl">

<script src="${staticPath}/js/jquery.min.js"></script>
<script src="${staticPath}/js/bootstrap.min.js"></script>
<script src="${staticPath}/js/over.js"></script>



</body>
</html>