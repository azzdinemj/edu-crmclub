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

<section class="word-nav">

</section>
<section class="class_center">
    <div class="container">
        <div class="tz-gallery">

            <div class="row">

                <#if employeelist?? &&employeelist.list??>
                    <#list employeelist.list as v>
                          <div class="col-sm-6 col-md-4">
                              <div class="thumbnail">
                                  <a href="/teacher/edit?pkEmployee=${(v.pkEmployee)}">
                                      <img src="${staticPath}${(v.address)!}.thumb.jpg"  onerror="this.src='/images/img/no2.jpg.thumb.jpg'" alt="${(v.caption)!}">
                                  </a>
                                  <div class="caption">
                                      <h3>${(v.caption)!}</h3>
                                      <p>${(v.memo)!}</p>
                                  </div>
                              </div>

                          </div>
                    </#list>
                </#if>


                <#--<div class="col-sm-6 col-md-4">-->
                    <#--<div class="thumbnail">-->
                        <#--<a href="teacherlist.ftl">-->
                            <#--<img src="${staticPath}/images/bridge.jpg" alt="Susan 张">-->
                        <#--</a>-->
                        <#--<div class="caption">-->
                            <#--<h3>Susan 张</h3>-->
                            <#--<p>中国航空油料集团公司是以原中国航空油料总公司为基础组建础组建的国有大型航空运输服务保障企业，是国内最大的集航空油品采购、运输、储存、检测、销售、加注为一体的航油供应商，</p>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="col-sm-6 col-md-4">-->
                    <#--<div class="thumbnail">-->
                        <#--<a href="teacherlist.ftl">-->
                            <#--<img src="${staticPath}/images/tunnel.jpg" alt="Susan 张">-->
                        <#--</a>-->
                        <#--<div class="caption">-->
                            <#--<h3>Susan 张</h3>-->
                            <#--<p>中国航空油料集团公司是以原中国航空油料总公司为基础组建的国有大型航空运输服务保障企业，是国内最大的集航空油品采购、运输、储存、检测、销售、加注为一体的航油供应商，</p>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="col-sm-6 col-md-4">-->
                    <#--<div class="thumbnail">-->
                        <#--<a href="teacherlist.ftl">-->
                            <#--<img src="${staticPath}/images/coast.jpg" alt="Susan 张">-->
                        <#--</a>-->
                        <#--<div class="caption">-->
                            <#--<h3>Susan 张</h3>-->
                            <#--<p>中国航空油料集团公司是以原中国航空油料总公司为基础组建的国有大型航空运输服务保障企业，是国内最大的集航空油品采购、运输、储存、检测、销售、加注为一体的航油供应商，</p>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="col-sm-6 col-md-4">-->
                    <#--<div class="thumbnail">-->
                        <#--<a href="teacherlist.ftl">-->
                            <#--<img src="${staticPath}/images/rails.jpg" alt="Susan 张">-->
                        <#--</a>-->
                        <#--<div class="caption">-->
                            <#--<h3>Susan 张</h3>-->
                            <#--<p>中国航空油料集团公司是以原中国航空油料总公司为基础组建的国有大型航空运输服务保障企业，是国内最大的集航空油品采购、运输、储存、检测、销售、加注为一体的航油供应商，</p>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="col-sm-6 col-md-4">-->
                    <#--<div class="thumbnail">-->
                        <#--<a href="teacherlist.ftl">-->
                            <#--<img src="${staticPath}/images/traffic.jpg" alt="Susan 张">-->
                        <#--</a>-->
                        <#--<div class="caption">-->
                            <#--<h3>Susan 张</h3>-->
                            <#--<p>中国航空油料集团公司是以原中国航空油料总公司为基础组建的国有大型航空运输服务保障企业，是国内最大的集航空油品采购、运输、储存、检测、销售、加注为一体的航油供应商，</p>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            </div>

        </div>
    </div>
</section>

<!--course为pageinfo对象-->
<!--分页页码-->
<#if employeelist.list??>
<ul class="pagination"  style="margin-left: 12%"  id="page">
    <li><a href="/teacher/query?pageNo=${employeelist.prePage}">&laquo;</a></li>
<#assign tp=employeelist.pages/>
<#assign p=employeelist.pageNum/>
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
     <li ><a href="/teacher/query?pageNo=${x}">${x}</a></li>
    </#if>
</#list>
    <li ><a href="/teacher/query?pageNo=${employeelist.nextPage}">&raquo;</a></li>
</ul>

</#if>

<#include  "common/footer.ftl">
<script src="${staticPath}/js/jquery.min.js"></script>
<script src="${staticPath}/js/bootstrap.min.js"></script>
<script src="${staticPath}/js/over.js"></script>



</body>
</html>