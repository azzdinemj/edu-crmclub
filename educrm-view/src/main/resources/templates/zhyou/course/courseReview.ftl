<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />
<section class="class_center zhsh">
            <div class="show-content">
                <div class="row">

                    <p class="number last">
                        <a class="card-left" href="/zhyou/review/query">返回</a>
                        <!--<span class="card-right"><em>200</em></span>-->
                    </p>
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="look-text">
                            <div class="text-center mb4">
                                <img class="look-img" onerror="this.src='/images/no.jpg'" src="${staticPath}${(course1.thumb)!}.thumb.jpg" alt="Hosting icon">
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-9">
                        <div class="look-text">
                            <h3 class="card-title">${(course1.caption)!}</h3>

                            <p><span>讲师：${(course1.map.teacherName)!}</span></p>
                            <p>
                                <span>课时：${(course1.classes)!}</span>
                            </p>
                            <p>
                                <span>使用时间：${(course1.cycle)!}</span>
                            </p>
                            <p>
                                <span>简介：${(course1.instruction)!}</span>
                            </p>
                            <p>
                                <span>详情：${(course1.content)!}</span>
                            </p>
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
                                    <td align="right"><a href="/zhyou/review/queryclass?pkCourse=${(les.pkCourse)!}&&pkCourseLesson=${(les.pkCourseLesson)!}">审核课程</a></td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
 </#if>
 </#list>
 </#if>

                   </div>
                </div>
        </div>
          </div>
</section>
<#include "../../commons/footer.ftl"/>

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