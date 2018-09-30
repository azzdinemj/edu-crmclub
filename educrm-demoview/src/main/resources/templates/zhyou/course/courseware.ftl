<#include "../../commons/top.ftl">
<#include "../../commons/left.ftl">

<div style="height: 100%" class="contentpanel" >
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>课件<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>

                <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal" data-backdrop="static" data-keyboard="false" data-url="/course/courseList/chaptername?pkCourse=${(pkCourse)!}">添加章节</a>

            </div>
        </div>
    </div>

    <div style="height: 100%"  class="panel panel-default">
        <div style="height: 100%" class="clearfix sortbale"  >
            <ol style="height: 100%" class="lesson-list sortablelist   " id="lesson" >

            <#assign index = 0 >
            <#if lescha??>
                <#list lescha as v >
                     <input type="hidden" id="orderlist" value="${(v.pkCourseLesson)!}" />
                    <!--章-->
                        <#if v.sheetType??&& v.sheetType==1>
                         <li class="item-chapter  clearfix dd-item lesson" title="${(v.pkCourseLesson)!}"  id="chapter" style="word-break: break-all;">

                            <div class="item-content">第 <span class="number">${index+1}</span> 章：${(v.caption)!}</div>
                            <div class="item-actions prs">
                                <div class="btn-group">
                                    <button class="btn btn-link dropdown-toggle" title="" data-toggle="dropdown" data-original-title="添加"><i class="glyphicon glyphicon-plus-sign"></i></button>
                                    <ul class="dropdown-menu" role="menu">

                                        <li>
                                            <a href="#" id="chapter-create-btn" data-toggle="modal" data-target="#modal" data-backdrop="static" data-keyboard="false" data-url="/course/courseList/addware?pkCourse=${(pkCourse)!}&&pkChapter=${(v.pkChapter)!}"><i class="glyphicon glyphicon-plus"></i>  添加 课时  </a>
                                        </li>


                                    </ul>

                                </div>

                                <button class="btn btn-link" title="" data-toggle="modal" data-target="#modal" data-keyboard="false" data-url="/course/courseList/chaptername?pkCourseLesson=${(v.pkCourseLesson)!}" data-original-title="编辑"><i class="glyphicon glyphicon-edit"></i></button>

                                <button class="btn btn-link delete-chapter-btn" title="" data-url="{:U('Course/delete" data-original-title="删除"><i class="glyphicon glyphicon-trash"></i></button>

                            </div>
                        </li>
                            <#assign index = index + 1>
                      </#if>

                    <!--节-->
                       <#if v.sheetType?? && v.sheetType==0>
                      <li class="item-lesson clearfix dd-item lesson" title="${(v.pkCourseLesson)!}" id="lesson" style="word-break: break-all;">
                            <div class="item-line"></div>
                            <div class="item-content">

                                <span class="glyphicon glyphicon-check text-success"></span>
                                课时 <span class="number"></span>：${(v.caption)!}

                            </div>

                            <div class="item-actions">

                                <a class="btn btn-link" data-toggle="modal" data-target="#modal" data-backdrop="static" data-keyboard="false" data-url="/course/courseList/addware?pkCourseLesson=${(v.pkCourseLesson)!}" data-original-title="" title=""><span class="glyphicon glyphicon-edit prs"></span>编辑</a>
                                <a class="btn btn-link" data-toggle="modal" data-target="#modal" data-backdrop="static" data-keyboard="false" data-url="/course/courseList/addware" data-original-title="" title=""><span class="glyphicon glyphicon-eye-open prs"></span>预览</a>
                                <button class="btn btn-link delete-lesson-btn" data-confirm-title="您确定要删除该课时吗？" data-url="{:U('Course/delete"  data-original-title="" title=""><span class="glyphicon glyphicon-eye-open prs"></span>删除</button>

                            </div>
                        </li>
                        </#if>

                    </#list>
                </#if>

            </ol>
        </div>
    </div>
</div>
<#include "../../commons/footer.ftl"/>
<script>

//    KindEditor.ready(function(K) {
//        window.editor = K.create('#editor_id');
//    });
</script>
<script type="text/javascript">

    function clear(str) {
        str = str.replace(/,/g, "");//取消字符串中出现的所有逗号
        return str;
    }

    var $list=$("#lesson");

    $("#lesson").sortable({
        delay : 1,
        serialize: function(parent, children, isContainer) {
            return isContainer ? children : parent.attr('id');
        },
        update : function(){
            //sortList($('#lesson'));

            var new_order = [];
            $list.children(".lesson").each(function() {
                new_order.push(this.title);
            });
            var newid = new_order.join(',');

            // var oldid = $orderlist.val();
            $.ajax({
                        type: "post",
                        url: "/course/courseList/sortlist", //服务端处理程序
                        data: { id: newid },   //id:新的排列对应的ID,order：原排列顺序
                        dataType: "json",
                       /* beforeSend: function() {
                            alert(newid);

                        },*/
                        success: function(data) {

                        }
            });


        }
    });

</script>