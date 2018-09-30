<#include "../../commons/top.ftl">
    <#include "../../commons/left.ftl">

<form class="look form-ajax" method="POST" id="formId" action="/course/courseList/save" data-target="/course/courseList/query" >
        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>添加课程<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>

                         <!-- 若是编辑，判断isissue是否为草稿，是草稿可以提交-->
                         <#--<#if course??&&course.isissue??&&course.isissue==0 >-->
                             <#--<input class="btn btn-sm btn-newblue" type="submit" value="保存">-->
                             <a id="submitCourse" class="btn btn-sm btn-newblue ">提交</a>
                         <#--</#if>-->
                        <!-- 若是新建-->
                         <#--<#if courseAdd??&&courseAdd==1 >-->
                             <input class="btn btn-sm btn-newblue" type="submit" value="保存">
                         <#--</#if>-->

                        <#--<a class="btn btn-sm btn-newblue changeemployee">教师</a>-->
                    </div>
                </div>
            </div>

            <div class="panel panel-default pl35">

                      <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">课件名称</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control"  value="${(course.caption)!}" name="caption" type="text"  placeholder="课件名称" data-bv-notempty="" data-bv-notempty-message="请录入课件名称" >

                                        <input name="pkCourse"  value="${(course.pkCourse)!}" type="hidden">
                                    </div>
                                </div>
                            </div>
                      </div>
                <!--end-->
                      <div class="row">
                              <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">课时</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input onkeyup="this.value=this.value.replace(/[^\d]/g,'');" class="form-control"  name="classes" value="${(course.classes)!}" type="text" placeholder="课时" data-bv-notempty="" data-bv-notempty-message="请录入课时">
                                    </div>
                                </div>
                              </div>
                            </div>
                <!--end-->
                      <div class="row">
                            <div class="col-xs-12 col-md-4">
                              <div class="form-group">
                              <label class="control-label col-xs-3">使用时间</label>
                              <div class="col-xs-9">
                                  <input onkeyup="this.value=this.value.replace(/[^\d]/g,'');" class="form-control" name="cycle" value="${(course.cycle)!}" type="text" placeholder="使用时间" data-bv-notempty="" data-bv-notempty-message="请录入使用时间">

                              </div>
                            </div>
                          <#--<div class="form-group">-->
                              <#--<label class="control-label col-xs-3">科目</label>-->
                              <#--<div class="col-xs-9">-->
                                  <#--<input class="form-control" name="subject" value="${(course.subject)!}" type="text">-->

                              <#--</div>-->
                          <#--</div>-->

                      </div>
                          </div>
                <!--end-->
                      <div class="row">
                          <div class="col-xs-12 col-md-4">
                          <#--<div class="form-group">-->
                          <#--<label class="control-label col-xs-3">时长</label>-->
                          <#--<div class="col-xs-9">-->
                          <#--<input class="form-control" name="time" value="${(course.time)!}" type="text">-->

                          <#--</div>-->
                          <#--</div>-->
                              <div class="form-group">
                                  <label class="control-label col-xs-3">老师</label>
                                  <div class="col-xs-9">
                                      <input type="hidden" name="teacher" id="pkParent" value="${(course.teacher)!}"  class="form-control" title="">

                                      <input  id="pkParentcaption" readonly value="" class="form-control" data-toggle="modal" placeholder="请选择老师"
                                              data-target="#modal" data-url="/teacher/teacherlist/querylist" >
                                  </div>
                              </div>

                          </div>
                      </div>
                <!--end-->
                      <div class="row">
                        <!-- col-sm-4 -->
                          <div class="col-xs-12 col-md-4">

                            <#--<div class="form-group">-->
                                <#--<label class="control-label col-xs-3 col-md-3">发布/任务</label>-->
                                <#--<div class="col-xs-9 col-md-9">-->
                                    <#--<span class="rdio rdio-warning">-->
                                        <#--<input disabled  <#if course.isissue??&& course.isissue==1 >checked="checked"</#if>  id="radio" type="radio">-->
                                        <#--<label for="radio">发布</label>-->
                                    <#--</span>-->
                                    <#--<span class="rdio rdio-warning">-->
                                        <#--<input disabled <#if course.isissue??&& course.isissue==0 >checked="checked"</#if> id="radio1"  type="radio">-->
                                        <#--<label for="radio1">存为草稿</label>-->
                                    <#--</span>-->
                                <#--</div>-->
                            <#--</div>-->

                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">课程类型</label>
                                <div class="col-xs-9 col-md-9">
                                    <select name="types" class="form-control">
                                        <#if dict??>
                                            <#list dict as v >
                                                <option  <#if course.types?? && v.pkSysDictValues??&& course.types == v.pkSysDictValues > selected</#if> value="${(v.pkSysDictValues)!}">
                                                      ${(v.caption)!}
                                                </option>
                                            </#list>
                                        </#if>

                                    </select>
                                </div>
                            </div>

                        </div>
                      </div>
                <!--end-->
                      <div class="row">
                          <div class="col-xs-12 col-md-12">
                              <div class="form-group">
                                  <label class="control-label col-xs-3 col-md-1">课程简介</label>

                                  <div class="col-xs-9 col-md-7">
                                      <input class="form-control"  name="instruction" value="${(course.instruction)!}" type="text">
                                  </div>
                              </div>
                          </div>
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-1">图片</label>
                                    <input type="hidden" id="thumb" name="thumb" value="${(course.thumb)!}"/>
                                    <div class="col-xs-9 col-md-11">
                                     <input type="file" id="file" name="file" onchange="ajaxSubmitForm('thumb')"/>
                                    </div>
                                </div>
                            </div>
                          <#--<div class="col-xs-12 col-md-12">-->
                              <#--<div class="form-group">-->
                                  <#--<label class="control-label col-xs-3 col-md-1">上传资料</label>-->
                                  <#--<input type="hidden" id="enclosure" name="enclosure" value="${(course.enclosure)!}"/>-->
                                  <#--<div class="col-xs-9 col-md-11">-->
                                      <#--<input type="file" id="file" name="file" onchange="ajaxSubmitForm('enclosure')"/>-->
                                  <#--</div>-->
                              <#--</div>-->
                          <#--</div>-->
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1 col-md-1">课程详情</label>
                                    <div class="col-xs-11 col-md-11">
                                    <textarea id="editor_id"  name="content" style="width:100%;height:300px;">${(course.content)!}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                <!--end-->
             </div>
        </div>
   </form>
<#include "../../commons/footer.ftl"/>
<script type="text/javascript">
    $(function(){
        //若是编辑则 根据教师编号查找教师姓名
        <#if course??>
          findTeacher();
        </#if>

    });

    function findTeacher(){
        $.ajax({
            url: '/teacher/teacherlist/findByPrimaryKey',
            type: 'POST',
            data: {
                pkEmployee :'${(course.teacher)!}'
            },
            dataType: "json",
            success: function (data) {
                if (data.code==0) {
                    $("#pkParentcaption").val(data.data.caption);
                }
            }

        });
    }

    function clear(str) {
        str = str.replace(/,/g, "");//取消字符串中出现的所有逗号
        return str;
    }

    function selectTea(pkId,caption) {
        $("#modal").modal("hide");
        $("#pkParentcaption").val(caption);
        $("#pkParent").val(pkId);
    }


    //上传图片
    function ajaxSubmitForm(id){
        var i=layer.msg('正在上传...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '0px', time:100000}) ;

        var requestData = {};
        var value = $("#file").val();
        requestData.file = value;
        var id_str = "file";
        $.ajaxFileUpload({
            url : '/file/upload',
            type : 'post',
            secureuri : false,
            data : requestData,
            fileElementId : id_str,  //文件             //普通数据
            dataType : 'json',
            success : function(result) {
                layer.close(i);
                 //hideLoading();
                if(result.code == 0){
                    document.getElementById(id).value = result.data;
                    Notify.success("success");
                }
            },
            error:function(){
                layer.close(i);
                alert("上传异常。。。");
            }

        });
    }

    $("#submitCourse").click(function () {
        <#--$.ajax({-->
            <#--url: '/course/courseList/submitCourse',-->
            <#--type: 'POST',-->
            <#--data: {-->
                <#--pkCourse :${(course.pkCourse)!}-->
            <#--},-->
            <#--dataType: "json",-->
            <#--success: function (data) {-->
                <#--if (data.code==0) {-->
                    <#--Notify.success(data.message);-->
                    <#--//提交成功跳转列表页面-->
                    <#--location.href="/course/courseList/query?"+new Date().getTime();-->
                <#--}-->
            <#--},-->

        <#--})-->

        <#--pkCourse=${(course.pkCourse)!}&&caption=${(course.caption)!}-->
        var mediaInfo = $("#formId").serialize();

        $.get("/course/courseList/submitCourse?"+ mediaInfo, function(data){
            Notify.success("提交成功！");
            //提交成功跳转列表页面
            location.href="/course/courseList/query?"+new Date().getTime();
        });


    });




</script>