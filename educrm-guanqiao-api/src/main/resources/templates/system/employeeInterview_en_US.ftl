
<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<form class="form-horizontal no-margin look form-ajax" id="formId" method="post" action="/system/employeeInterview/save"
      data-target="/system/employeeInterview/edit?pkStudentInterview=${(students.pkStudentInterview)!}">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>Interview<span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue"  onClick="javascript:history.back(-1);">Return</a>
            <button class="btn btn-sm btn-newblue isnone" type="submit">Preservation</button>
            <a class="btn btn-sm btn-newblue isubmit" onclick="isubmit()">Submission</a>
            <a class="btn btn-sm btn-newblue isaudit" onclick="isaudit(2)">success</a>
            <a class="btn btn-sm btn-newblue isaudit" onclick="isaudit(1)">Reject</a>
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>Essential information</strong></a></li>
            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">

                    <div class="form-group">
                        <label class="control-label col-xs-1 col-md-1">School area</label>
                        <div class="col-xs-3 col-md-3">
                            <input class="form-control" value="${(students.domain.caption)!}" readonly type="text">
                            <input name="stu.pkDomain" value="${(students.employee.pkDomain)!}"  type="hidden">
                            <input name="stu.pkStudentInterview" id="pkStudentInterview" value="${(students.pkStudentInterview)!}"  type="hidden">
                            <input id="userKey" value="${(userKey)!}"  type="hidden">
                        </div><!-- /.col -->

                        <label class="control-label col-xs-1 col-md-1">Record number</label>
                        <div class="col-xs-3 col-md-3">
                            <input name="stu.code" readonly class="form-control" value="${(students.code)!}" title="" type="text">
                        </div><!-- /.col -->

                        <label class="control-label  col-xs-1 col-md-1">Job interview</label>
                        <div class="col-xs-3 col-md-3">
                            <input  placeholder="Please enter the job interview"  data-bv-notempty data-bv-notempty-message="Please enter the job interview" class="form-control" name="stu.caption" value="${(students.caption)!}"  type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-1 col-md-1">Employee name</label>
                        <div class="col-xs-3 col-md-3">
                            <input readonly value="${(students.employee.caption)!}" class="form-control" title="" type="text">
                            <input name="stu.pkStudent" value="${(students.pkStudent)!}"  type="hidden">
                            <#--<input readonly value="${(students.employee.caption)!}" class="form-control" title="" type="text">-->
                            <#--<input name="stu.pkStudent" value="${(students.pkStudent)!}"  type="hidden">-->
                        </div><!-- /.col -->

                        <label class="control-label col-xs-1 col-md-1">Interviewhr</label>
                        <div class="col-xs-3 col-md-3">
                                <input id="stuCaption" readonly    value="${(students.employeeAdmin.caption)!}" class="form-control" <#if students ??&& students.employeeAdmin??><#else >data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=1" </#if>
                                       placeholder="Please choosehr"  >
                                <input  data-bv-notempty data-bv-notempty-message="Please choosehr"  id="pkStudent" name="stu.pkEmployee" value="${(students.pkEmployee)!}" type="hidden">
                        </div><!-- /.col -->

                        <label class="control-label  col-xs-1 col-md-1">Interview time</label>
                        <div class="col-xs-3 col-md-3" id="daterangepicker">
                            <input name="stu.dateTime" class="form-control js-datepicker" value="${(students.date?string("yyyy-MM-dd"))!}" title="" type="text">
                        </div><!-- /.col -->

                    </div>


                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">Remarks</label>
                            <div class="col-xs-11 col-md-11">
                                <textarea class="form-control" name="stu.memo" rows="3" va>${(students.memo)!}</textarea>
                            </div><!-- /.col -->
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">submitter</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="stu.submitor" class="form-control" value="${(students.map.submitorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Submitted</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly type="text" title="" value="${(students.submitDate?string("yyyy-MM-dd HH:mm:ss"))!} " class="form-control" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">is submit</label>

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
                                    <label class="control-label col-xs-3 col-md-3">Auditor</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="stu.auditor"  class="form-control" value="${(students.map.auditorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Audit time</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(students.auditDate?string("yyyy-MM-dd HH:mm:ss"))!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">is review</label>

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
                            <label class="control-label col-xs-1 col-md-1">Founder</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly  class="form-control" value="${(students.map.creatorEntity.caption)!}" type="text"/>
                            </div><!-- /.col -->
                            <label class="control-label col-xs-1 col-md-1">Creation time</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly class="form-control" value="${students.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">Modifier</label>
                            <div class="col-xs-3 col-md-3">
                                <input readonly  class="form-control" value="${(students.map.modifierEntity.caption)!}" type="text"/>
                            </div><!-- /.col -->
                            <label class="control-label  col-xs-1 col-md-1">Modified</label>
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



    //Preservation
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


    //Submission
    function isubmit() {
        var flag = confirm("Do you confirm the submission？");
        if (flag){
            var formdata = $("#formId").serialize();
            $.ajax({
                url:"/system/employeeInterview/submit",
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
            flag = confirm("Confirm that the employee is not audited？");

        }
        if(num ==2){
            flag = confirm("Confirm that the employee is not audited？");
        }
        var formdata = $("#formId").serialize();
        $.ajax({
            url:"/system/employeeInterview/audit?isaudit="+num,
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
        $("#stuCaption").val(caption);
        $("#pkStudent").val(pkId);
    }
</script>
