
<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<form class="form-horizontal no-margin look form-ajax" id="formId" method="post" action="/student/studentInterview/save"
      data-target="/student/studentInterview/edit?pkStudentInterview=${(students.pkStudentInterview)!}">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>面试<span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue"  onClick="javascript:history.back(-1);">返回</a>
            <button class="btn btn-sm btn-newblue isnone" type="submit">保存</button>
            <a class="btn btn-sm btn-newblue isubmit" onclick="isubmit()">提交</a>
            <a class="btn btn-sm btn-newblue isaudit" onclick="isaudit(2)">审核成功</a>
            <a class="btn btn-sm btn-newblue isaudit" onclick="isaudit(1)">驳回</a>
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>基本信息</strong></a></li>
            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">




                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">所属校区</label>
                            <div class="col-xs-3 col-md-3">
                                <input class="form-control" value="${(domain.caption)!}" readonly type="text">
                                <input name="stu.pkDomain" value="${(domain.pkDomain)!}"  type="hidden">
                                <input name="stu.pkStudentInterview" id="pkStudentInterview" value="${(students.pkStudentInterview)!}"  type="hidden">
                                <input id="userKey" value="${(userKey)!}"  type="hidden">
                            </div><!-- /.col -->


                            <label class="control-label col-xs-1 col-md-1">记录编号</label>
                            <div class="col-xs-3 col-md-3">
                                <input name="stu.code" readonly class="form-control" value="${(students.code)!}" title="" type="text">
                            </div><!-- /.col -->
                            <label class="control-label  col-xs-1 col-md-1">面试科目</label>
                            <div class="col-xs-3 col-md-3">
                                <select name="stu.caption">
                                <#list project as pro>
                                    <option value="${(pro.pkSysDictValues)!}" <#if pro.pkSysDictValues?? && students.caption ?? &&pro.pkSysDictValues ==students.caption >selected="selected"</#if>>${(pro.caption)!}</option>
                                </#list>
                                </select>
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">学生姓名</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly value="${(students.student.caption)!}" class="form-control" title="" type="text">
                                <input name="stu.pkStudent" value="${(students.pkStudent)!}"  type="hidden">
                            </div><!-- /.col -->

                            <label class="control-label col-xs-1 col-md-1">面试老师</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly id="pkempcapid" value="${(employee.caption)!}" class="form-control" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=3">
                                <input readonly name="stu.pkEmployee" id="pkemppkid" value="${(employee.pkEmployee)!}" type="hidden">
                                <#--<select name="stu.pkEmployee">-->
                                <#--<#list employees as emp>-->
                                    <#--<option value="${(emp.pkEmployee)!}" <#if emp.pkEmployee?? &&students.pkEmployee??&& emp.pkEmployee == students.pkEmployee>selected</#if>>${(emp.caption)!}</option>-->
                                <#--</#list>-->
                                <#--</select>-->
                            </div><!-- /.col -->
                            <label class="control-label  col-xs-1 col-md-1">面试时间</label>
                            <div class="col-xs-3 col-md-3" id="daterangepicker">
                                <input name="stu.dateTime" class="form-control js-datepicker" value="${(students.date?string("yyyy-MM-dd"))!}" title="" type="text">
                            </div><!-- /.col -->

                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">面试情况</label>
                            <div class="col-xs-11 col-md-11">
                                <textarea class="form-control" name="stu.remark" rows="3" va>${(students.remark)!}</textarea>
                            </div><!-- /.col -->
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">提交人</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="stu.submitor" class="form-control" value="${(students.map.submitorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">提交时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly type="text" title="" value="${(students.submitDateTime)!}" class="form-control" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">是否提交</label>

                                    <div class="col-xs-9 col-md-9 pt7">
                                       <span class="rdio rdio-success">
                                           <input name="stu.issubmit" value="1" disabled class="deldis"
                                                  <#if students.issubmit??&& students.issubmit ==1 >checked="checked"</#if>
                                                  id="radio1" type="radio">
                                           <label disabled="disabled" for="radio1">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.issubmit" value="0" id="radio2" disabled class="deldis"
                                                  <#if students.issubmit??&& students.issubmit ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio2">否</label>
                                       </span>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">审核人</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="stu.auditor"  class="form-control" value="${(students.map.auditorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">审核时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(students.auditDateTime)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">是否审核</label>

                                    <div class="col-xs-9 col-md-9 pt7">
                                       <span class="rdio rdio-warning">
                                           <input name="stu.isaudit" value="1" disabled class="deldis"
                                                  <#if students.isaudit??&& students.isaudit ==2 >checked="checked"</#if>
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isaudit" value="0" id="radio4" disabled class="deldis"
                                                  <#if students.isaudit??&& students.isaudit ==2 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio4">否</label>
                                       </span>
                                    </div>

                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">创建人</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly  class="form-control" value="${(students.map.creatorEntity.caption)!}" type="text"/>
                            </div><!-- /.col -->
                            <label class="control-label col-xs-1 col-md-1">创建时间</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly class="form-control" value="${students.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">修改人</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly  class="form-control" value="${(students.map.modifierEntity.caption)!}" type="text"/>
                            </div><!-- /.col -->
                            <label class="control-label  col-xs-1 col-md-1">修改时间</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly class="form-control" value="${students.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
                            </div><!-- /.col -->
                        </div>



                </div><!-- tab-content -->





            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->
</div>
</form>

<#include "../commons/footer.ftl" >

<script type="text/javascript">


    $(function () {
        <#if students.issubmit?? && students.issubmit ==1>$(".isnone").attr("disabled", "disabled");</#if>
        <#if students.issubmit?? && students.issubmit ==1>$(".isubmit").attr("disabled", "disabled");</#if>
        <#if students.isaudit?? && students.isaudit ==2>$(".isaudit").attr("disabled", "disabled");</#if>
    });



    //保存
    <#--function sub() {-->
        <#--var formdata = $("#formId").serialize();-->
        <#--var pkStudentInterview = "${(students.pkStudentInterview)!}";-->
        <#--$.ajax({-->
            <#--url:"/student/studentInterview/save",-->
            <#--type:"POST",-->
            <#--data:formdata,-->
            <#--dataType:"json",-->
            <#--success:function (data) {-->
                <#--if(data.code ==0){-->
                   <#--window.location.href="/student/studentInterview/edit?pkStudentInterview="+pkStudentInterview;-->
                   <#--window.location.href="/student/studentInterview/edit?pkStudentInterview=${(students.pkStudentInterview)!}";-->
                <#--}else {-->
                    <#--Notify.danger(data.message);-->
                <#--}-->
            <#--}-->
        <#--});-->
    <#--}-->


    //提交
    function isubmit() {
        var flag = confirm("确认提交吗？");
        if (flag){
            var formdata = $("#formId").serialize();
            $.ajax({
                url:"/student/studentInterview/submit",
                type:"POST",
                data:formdata,
                dataType:"json",
                success:function (data) {
                    if(data.code ==0){
                        Notify.success(data.message);
                        location.reload();
                    }else {
                        Notify.danger(data.message);
                        location.reload();
                    }
                }
            });
        }

    }
    function isaudit(num) {
        var flag;
        if (num==1){
            flag = confirm("确认该学员未通过审核？");

        }
        if(num ==2){
            flag = confirm("确认该学员通过审核？");
        }
        var formdata = $("#formId").serialize();
        $.ajax({
            url:"/student/studentInterview/audit?isaudit="+num,
            type:"POST",
            data:formdata,
            dataType:"json",
            success:function (data) {

                    if(data.code ==0){
                        Notify.success(data.message);
                        window.location.reload();
                    }else {
                        Notify.danger(data.message);
                        location.reload();
                    }
            }
        });
    }

    function selectEmp(pkId,caption,id1,id2) {
        $("#modal").modal("hide");
        $("#pkempcapid").val(caption);
        $("#pkemppkid").val(pkId);
    }
</script>
