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
<section class="class_center">
    <div class="container">
        <div class="course-header">
            <p>位置>课程中心>课程</p>

        </div>
        <div class="show-content">
            <div class="row">

                <div class="col-xs-12 col-sm-6 col-md-5">
                    <div class="look-text">
                        <div class="text-center mb4">
                            <img class="look-img" onerror="this.src='/images/img/no.jpg'" src="${staticPath}${(course1.thumb)!}.thumb.jpg" alt="Hosting icon">
                        </div>
                        <p class="number last">
                            <span class="card-left">浏览：<em>${(course1.num)!}</em></span>
                            <!--<span class="card-right"><em>200</em></span>-->
                        </p>

                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-7">
                    <div class="look-text">
                        <h3 class="card-title">${(course1.instruction)!}</h3>

                        <p><span>讲师：${(course1.map.teacherName)!}</span></p>
                        <p>
                            <span>时长：${(course1.time)!}</span>
                        </p>

                        <a href="/course/queryclass?pkCourse=${(course1.pkCourse)!}" class="look">查看</a>

                    </div>
                </div>


        </div>
        </div>
        <div class="introduce">
            <div class="row">
                <div class="col-xs-12 col-md-8">
                   <div class="left">
                       <h3 class="gray">课程大纲</h3>

                       <!-- 循环 课程目录-->
 <#assign index = 0 >
 <#if lessonchapter??>
 <#list lessonchapter as les>
     <#if les.sheetType==1>
                       <p class="chapter">第 <span class="number">${index+1}</span> 章：${(les.caption)!}</p>
 <#assign index = index + 1>
 </#if>



<#if les.sheetType==0>
                        <form>
                            <table >
                                <tbody>
                                <tr>
                                    <td align="center" width="40"><span class="readynum">${(les.sort)!}</span></td>
                                    <td width="320">${(les.caption)!}</td>
                                   <#-- <td >
                                        ${les.lasteditDate? string("yyyy-MM-dd HH:mm:ss")}
                                         ${(les.lasteditDate)!}
                                     </td>-->
                                    <td align="right"><a href="/course/queryclass?pkCourse=${(les.pkCourse)!}&&pkCourseLesson=${(les.pkCourseLesson)!}">开始上课</a></td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
 </#if>
 </#list>
 </#if>

                   </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="right">
                        <h3 class="gray">我的评论：</h3>
                        <textarea style="height: 6%;width: 100%;resize: none;" id="evaluate"></textarea>
                        <input id="submitEvaluate" type="button" value="提交" >

                        <h3 class="gray">评价</h3>
                        <div class="overcmt">
                        <#if evaluate??>
                            <#list evaluate as e>
                                <div class="u-cmt">
                                    <div class="wrp f-cb f-pr">
                                        <img class="j-info f-cb" alt="" src="${staticPath}/images/bg.jpg">
                                        <div class="info">
                                            <p class="presoninfo j-personinfo">
                                                <span class="name" >${(e.creator)!}</span>
                                            <#-- <span class="time">${(e.creationDate)!}</span>-->
                                            </p>

                                        </div>

                                    </div>
                                    <div class="cnt s-fc5 f-fs0 j-info">
                                    ${(e.cotent)!}
                                        <div class="j-reply"></div>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                        </div>


                        <h3 class="gray">学习此课程的人(<span>${(student?size)!}</span>)</h3>

                        <div class="peopele row overcmq">
                            <#if student??>
                                <#list student as s>
                            <div class="name col-xs-12 col-md-3">
                                <img src="${staticPath}/images/bg_1.jpg">
                                <p>${(s.caption)!}</p>
                            </div>
                                </#list>
                            </#if>
                        </div>


                </div>
            </div>
        </div>
    </div>
</section>
<#include  "common/footer.ftl">
</body>
</html>

<script src="${staticPath}/js/jquery.min.js"></script>
<script src="${staticPath}/js/bootstrap.min.js"></script>
<#--<script src="${staticPath}/js/main.js"></script>-->
<script>

$("#submitEvaluate").click(function () {
     var Content=$("#evaluate").val();
     if(Content==null||Content==""){
         return;
     }

    $.ajax({
        url: '/course/saveEvaluate',
        type: 'POST',
        data: {
            pkCourse: ${(course1.pkCourse)!},
            cotent :Content
        },
        dataType: "json",
        success: function (data) {
            if (data.code==0) {
                $("#evaluate").val("");
                //location.href="/login?"+new Date().getTime();
            }
        },
        error: function (data) {
        }

    })

})



</script>