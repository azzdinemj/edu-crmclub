<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>Student registration <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
            <a class="btn btn-sm btn-newblue " id="savebtn" onClick="sub()">Preservation</a>
            <a class="btn btn-sm btn-newblue " id="submitbtn" onClick="issubmit()">Submission</a>
            <#--<a class="btn btn-sm btn-newblue " id="auditbtn" onClick="isaudit()">To examine</a>-->
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>Student information</strong></a></li>

            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">

                    <form class="form-horizontal no-margin form-ajax" id="formId" method="post"
                          action="/student/studentReport/saveall"
                          data-target="/student/studentSignup/query">
                        <input type="hidden" id="pkStudentSignup" name="stu.pkStudentSignup" value="${(student.pkStudentSignup)!}">
                        <input type="hidden" name="userKey"  value="${(userKey)!}">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Registration Campus</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.pkDomain" value="${(domain.pkDomain)!}" type="hidden">
                                        <input  class="form-control" value="${(domain.caption)!}"
                                               readonly title="" type="text">
                                    </div><!-- /.col -->
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Student name</label>
                                    <div class="col-xs-9 col-md-9">
                                       <#-- <input class="form-control" value="${(student.caption)!}" readonly title=""
                                               type="text">-->
                                           <i class="fa fa-user c-icon"></i>
                                           <input id="stuCaption" readonly value="${(student.caption)!}" class="form-control" <#if student ??&& student.pkStudent??><#else >data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/findstu?pkClassinfo=${(classinfo.pkClassinfo)!}"</#if> >
                                           <input id="pkStudent" name="stu.pkStudent" value="${(student.pkStudent)!}" type="hidden">
                                       <#--<#if students??>-->
                                        <#--<select name="stu.pkStudent">-->
                                            <#--<#list students as st>-->
                                                <#--<option value="${(st.pkStudent)!}">${(st.caption)!}</option>-->
                                            <#--</#list>-->
                                        <#--</select>-->
                                       <#--<#else >-->
                                           <#--<input name="stu.pkStudent" class="form-control" value="${(student.pkStudent)!}"title="" >-->
                                       <#--</#if>-->
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Origin school</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.oldSchool" class="form-control" value="${(student.oldSchool)!}"
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">RMB sum</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.mny" readonly class="form-control sum deldou" value="${(student.mny)!0}"
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">RMB Receivables</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.money" id="money" class="form-control deldou"
                                               value="${(student.money)!0}" title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Payment method</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.paymentMethod" class="form-control"
                                                class="selectpicker bla bla bli" data-live-search="true">
                                            <#if paymethod ??>
                                                <option value="">请选择</option>
                                                <#list paymethod as p>
                                                    <option value="${(p.pkSysDictValues)!}" <#if p.pkSysDictValues ?? && student.paymentMethod ?? && p.pkSysDictValues==student.paymentMethod>selected</#if>>${(p.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Bank name</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.bankCaption" class="form-control"
                                               value="${(student.bankCaption)!}" title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                            </div>
                            <div class="col-xs-12 col-md-4">


                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">number</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.code" readonly class="form-control" value="${(student.code)!}"
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group ">

                                    <label class="control-label col-xs-3 col-md-3">SignUp grade</label>
                                    <div class="col-xs-9 col-md-9">
                                        <#--<input value="" readonly class="form-control">-->
                                        <#--<input name="stu.grade" readonly class="form-control" value="${(classinfo.grade)!}"-->
                                               <#--title="" type="text">-->
                                        <select name="stu.grade">
                                        <#if grade ??>
                                            <#list grade as gra>
                                                <option value="${(gra.pkSysDictValues)!}"
                                                        <#if reportkey?? && reportkey==2&& gra.pkSysDictValues ?? && classinfo.grade??&& gra.pkSysDictValues==classinfo.grade> disabled selected="selected"</#if>
                                                        <#if reportkey?? && reportkey==1 && gra.pkSysDictValues ?? && student.grade??&& gra.pkSysDictValues==student.grade>selected</#if>
                                                > ${(gra.caption)!}</option>

                                            </#list>
                                        </#if>
                                        </select>


                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">old grade</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.oldGrade" class="form-control" value="${(student.oldGrade)!}"
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Dollar amount</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.mny" class="form-control mnyusd deldou" value="${(student.mnyUsd)!0}"
                                               title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">USD receivable</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.money" id="moneyUsd" class="form-control deldou"
                                               value="${(student.moneyUsd)!0}" title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Account number</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.bankAccount" class="form-control"
                                               value="${(student.bankAccount)!}" title="" type="text">
                                    </div><!-- /.col -->
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Bank address</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.bankAddress" class="form-control"
                                               value="${(student.bankAddress)!}" title="" type="text">
                                    </div><!-- /.col -->
                                </div>


                            </div>
                            <div class="col-xs-12 col-md-4">

                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Registration project</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.program">
                                        <#if project??>
                                            <#list project as pro>
                                                <option value="${(pro.pkSysDictValues)!}" <#if pro.pkSysDictValues?? && student ?? && student.program ?? && student.program==pro.pkSysDictValues>selected</#if>>${(pro.caption)!}</option>
                                            </#list>
                                        </#if>
                                        </select>
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3"><#if classinfo?? && classinfo.caption??>Enrolment class</#if></label>
                                    <div class="col-xs-9 col-md-9">
                                    <#if classinfo?? && classinfo.caption??>
                                        <input value="${(classinfo.caption)!}" class="form-control">
                                        <input name="stu.pkClassinfo"  class="form-control" id="pkClassinfo"
                                               value="${(classinfo.pkClassinfo)!}" title="" type="hidden">
                                    </#if>
                                    </div>
                                </div><!-- /.col -->
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">sign date</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.dateTime" id="data" class="form-control"
                                               value="${(student.date?string("yyyy-MM-dd"))!}" title="" type="text">
                                    </div>
                                </div><!-- /.col -->

                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Admissions teacher</label>
                                    <div class="col-xs-9 col-md-9">

                                        <input readonly id="pkempcapid" value="${(employee.caption)!}" <#--onclick="selEmp()"--> class="form-control" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=2">
                                        <input readonly name="stu.pkEmployee" id="pkemppkid" value="${(employee.pkEmployee)!}" type="hidden">

                                        <#--<select name="stu.pkEmployee">-->
                                        <#--<#if employees ??>-->
                                            <#--<#list employees as fin>-->
                                                <#--<option value="${(fin.pkEmployee)!}"-->
                                                        <#--<#if fin.pkEmployee?? && student??&& student.pkEmployee??&&student.pkEmployee==fin.pkEmployee>selected="selected"</#if>>${(fin.caption)!}</option>-->
                                            <#--</#list>-->

                                        <#--</#if>-->
                                        <#--</select>-->
                                        <#--<input readonly class="form-control" value="${(employee.caption)!}" title=""-->
                                               <#--type="text">-->
                                        <#--<input name="stu.pkEmployee" class="form-control"-->
                                               <#--value="${(employee.pkEmployee)!}" title="" type="hidden">-->
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Preferential</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.discount" id="discount" class="form-control deldou"
                                               value="${(student.discount)!0}" title="" type="text">
                                    </div><!-- /.col -->
                                </div>


                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">account title</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.bankAccountCaption" class="form-control"
                                               value="${(student.bankAccountCaption)!}" title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Agent</label>
                                    <div class="col-xs-9 col-md-9">

                                        <input readonly id="empcapid" value="${(responsiblePerson.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=4">
                                        <input readonly name="stu.responsiblePerson" id="emppkid" value="${(responsiblePerson.pkEmployee)!}" type="hidden">
                                        <#--<select name="stu.responsiblePerson">-->
                                        <#--<#if employees ??>-->
                                            <#--<#list employees as fin>-->
                                                <#--<option value="${(fin.pkEmployee)!}"-->
                                                        <#--<#if fin.pkEmployee?? && student??&& student.responsiblePerso??&&student.responsiblePerso==fin.pkEmployee>selected="selected"</#if>>${(fin.caption)!}</option>-->
                                            <#--</#list>-->

                                        <#--</#if>-->
                                        <#--</select>-->
                                    <#--<input name="stu.responsiblePerson"  class="form-control" value="${(student.responsiblePerson)!}" title="" type="text">-->
                                    </div><!-- /.col -->
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1">Remarks</label>
                                    <div class=" col-xs-11">
                                        <textarea rows="1" name="stu.memo" value="${(student.memo)!}"
                                                  class="form-control" title=""></textarea>
                                    </div><!-- /.col -->
                                </div>
                            </div>
                        </div>

                        <h3>Charges Details</h3>

                    <#-- <div class="table-responsive">-->


                        <table id="tab" class="table table-striped table-bordered" cellspacing="0" cellpadding="0">
                            <thead>
                            <tr>
                                <td>item</td>
                                <td>money Type</td>
                                <#--<td>Cost unit</td>-->
                                <#--<td>Unit quantity</td>-->
                                <td>Actual amount</td>
                                <td>Remarks</td>

                                <td width="10%"><i class="fa fa-remove op0 add_icon"></i><i onclick="addTr2('tab', -1)"
                                                                                            class="fa fa-plus add_icon"></i>
                                </td>


                            </tr>
                            </thead>
                            <tbody id="parentTab"<#if detailsList??>onload="attClass(${detailsList?size})" </#if>>
                            <#--<#if (signup.pkStudentSignup ??&& signup.pkStudentSignup.length>0)>-->
                            <#if detailsList??>
                                <#assign  index = 0 >
                                <#list detailsList as exp>
                                <tr id="Tr1" align="center">
                                    <td>
                                    <#-- <select name="details[${index}].pkExpenseItem">
                                         <#if expenseItems??><#list expenseItems as exp><option value="${(exp.pkExpenseItem)!}">${(exp.caption)!}</option></#list></#if>
                                     </select>-->

                                        <#--<input type="text" value="${(exp.pkExpenseItem)!}" class="form-control bor0 bg0"
                                               readonly placeholder="item">-->
                                        <input  name="details[${index}].pkExpenseItem"
                                               value="${(exp.itemCaption)!}" class="form-control bor0 bg0" readonly>
                                    </td>
                                    <td>
                                        <select name="details[${index}].currency" id="pkSysDictValues${index}" class="form-control" onchange="setMoner(${index})">
                                            <#if currency??>
                                                <#list currency as cur>
                                                    <option value="${(cur.pkSysDictValues)!}" <#if exp.currency?? && cur.pkSysDictValues??&& cur.pkSysDictValues==exp.currency>selected</#if>>${(cur.caption)!}</option>
                                                </#list>

                                            </#if>
                                        </select>
                                        <#--<input type="text" name="details[${index}].currency" value="${(exp.currency)!}" class="form-control bor0 bg0" readonly>-->

                                    </td>
                                    <#--<td>-->
                                        <#--<input type="text" name="details[${index}].unit" value="${(exp.unit)!}" class="form-control bor0 bg0" readonly>-->

                                    <#--</td>-->
                                    <#--<td>-->
                                        <#--<input type="text" name="details[${index}].num" value="${(exp.num)!}" class="form-control bor0 bg0" readonly>-->

                                    <#--</td>-->
                                    <td>
                                        <input type="text" id="money${index}" name="details[${index}].money" value="${(exp.money)!0.00}">
                                    </td>


                                    <td><input type="text" name="details[${index}].memo" value="${(exp.details.memo)!}"
                                               class="form-control bor0 bg0" ></td>
                                <#--<td width="10%"><i class="fa fa-remove op0 add_icon"></i><i onclick="addTr2('tab', -1)" class="fa fa-plus add_icon"></i></td>-->

                                </tr>
                                    <#assign index = index+1>  <!--listCycle once，variable+1-->
                                </#list>
                            </#if>
                            <#--</#if>-->
                            </tbody>
                        <#-- <tr id="Tr1" align="center">
                             <td ><input type="text" class="form-control bor0 bg0" disabled placeholder="item"></td>
                             <td ><input type="text" class="form-control bor0 bg0" disabled placeholder="Actual amount"></td>



                             <td ><input type="text" class="form-control bor0 bg0" disabled placeholder="Remarks"></td>
                             <td width="10%"><i class="fa fa-remove op0 add_icon"></i><i onclick="addTr2('tab', -1)" class="fa fa-plus add_icon"></i></td>

                         </tr>-->
                        </table>


                    <#-- </div>-->


                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">submitter</label>
                                    <div class="col-lg-9 ">
                                        <input readonly class="form-control" value="${(student.submitor)!}"
                                               type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Submitted</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control"
                                               value="${(student.submitDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                               type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">is submit</label>

                                    <div class="col-xs-9 col-md-9 pt7">
                                       <span class="rdio rdio-success">
                                           <input name="stu.issubmit" value="1" disabled class="deldis"
                                                  <#if student.issubmit??&& student.issubmit ==1 >checked="checked"</#if>
                                                  id="radio1" type="radio">
                                           <label disabled="disabled" for="radio1">是</label>
                                       </span>
                                        <span class="rdio rdio-success">
                                           <input name="stu.issubmit" value="0" id="radio2" disabled class="deldis"
                                                  <#if student.issubmit??&& student.issubmit ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio2">否</label>
                                       </span>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Auditor</label>
                                    <div class="col-lg-9 ">
                                        <input readonly class="form-control" value="${(student.auditor)!}"
                                               type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Audit time</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control"
                                               value="${(student.auditDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                               type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">is review</label>

                                    <div class="col-xs-9 col-md-9 pt7">
                                       <span class="rdio rdio-success">
                                           <input name="stu.issubmit" value="1" disabled class="deldis"
                                                  <#if student.isaudit??&& student.isaudit ==1 >checked="checked"</#if>
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">是</label>
                                       </span>
                                        <span class="rdio rdio-success">
                                           <input name="stu.issubmit" value="0" id="radio4" disabled class="deldis"
                                                  <#if student.isaudit??&& student.isaudit ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio4">否</label>
                                       </span>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label  col-xs-3 col-md-3">Founder</label>
                                    <div class="col-xs-9 col-md-9 ">
                                        <input  readonly class="form-control" value="${(student.map.creatorEntity.caption)!}"
                                               type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Creation time</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control"
                                               value="${(student.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                               type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">Modifier</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input  readonly class="form-control" value="${(student.map.modifierEntity.caption)!}"
                                               type="text">
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Modified</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control"
                                               value="${(student.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                               type="text">
                                    </div>
                                </div>
                            </div>
                        </div>


                    <#--<div class="modal-footer">
                        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success btn-sm">Determine</button>
                    </div>-->
                    </form>

                </div><!-- tab-content -->


            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->
<#include "../commons/footer.ftl" >

<script type="text/javascript">

//    function attClass(size) {
//        for (var i = 0; i<size;i++){
//            var id = $("#pkSysDictValues" + i).val();
//            if (id == "USD") {
//                $("#money" + i).attr("class","form-control bor0 bg0 usd ");
//            } else {
//                $("#money" + i).attr("class","form-control bor0 bg0 jine ");
//            }
//
//        }
//    }


    $(function () {

        <#if student?? && student.issubmit?? && student.issubmit ==1>$("#savebtn").attr("disabled", "disabled");</#if>
        <#if student?? && student.issubmit?? && student.issubmit ==1>$("#submitbtn").attr("disabled", "disabled");</#if>
        <#if student?? && student.isaudit?? && student.isaudit ==1>$("#auditbtn").attr("disabled", "disabled");</#if>

        <#if detailsList?? &&(detailsList?size>0)>
    //     Number numb1 =;
            for (var i = 0; i<${detailsList?size};i++){
                var id = $("#pkSysDictValues" + i).val();
                if (id == "USD") {
                    $("#money" + i).attr("class","form-control bor0 bg0 usd deldou mon");
                } else {
                    $("#money" + i).attr("class","form-control bor0 bg0 jine deldou mon");
                }

            }
        </#if >

        $(".deldou").each(function (i) {

            var str = $(".deldou").eq(i).val().replace(/,/g, "");
            $(".deldou").eq(i).val("");
            $(".deldou").eq(i).val(str);

        });
    });

    $(".mon").live("blur",function (event) {
        //Calculate RMB
        var sum = 0;
        $(".jine").each(function () {
            v = parseFloat($(this).val());
            v = isNaN(v) ? 0 : v;
            sum += v;
        });
        $(".sum").val(sum);
        //dollar
        var sumusd = 0;
        $(".usd").each(function () {

            v = parseFloat($(this).val());
            v = isNaN(v) ? 0 : v;
            sumusd += v;
        });
        $(".mnyusd").val(sumusd);
        $("#moneyUsd").val(sumusd);

        //Calculate the amount receivable
        var sum1 = $(".sum").val();
        var dis = $("#discount").val();
        dis = sum1 - dis;
        $("#money").val(dis);

//        Calculation of preferential amount
        var sum2 = $(".sum").val();
        var dis2 = $("#money").val();
        dis2 = sum2 - dis2;
        $("#discount").val(dis2);




    });

//    //Calculate the amount of RMB
//    $(".jine").live("blur",function (event) {
//        var sum = 0;
//        $(".jine").each(function () {
//            v = parseFloat($(this).val());
//            v = isNaN(v) ? 0 : v;
//            sum += v;
//        });
//        $(".sum").val(sum);
//
//    });
//    //Calculate the dollar amount
//    $(".usd").live("blur",function (event) {
//        var sumusd = 0;
//        $(".usd").each(function () {
//
//            v = parseFloat($(this).val());
//            v = isNaN(v) ? 0 : v;
//            sumusd += v;
//        });
//        $(".mnyusd").val(sumusd);
//        $("#moneyUsd").val(sumusd);
//    });
//
//    //Calculating the actual amount
    $("#discount").live("blur",function (event) {

        var sum = $(".sum").val();
        var dis = $("#discount").val();
        dis = sum - dis;
        $("#money").val(dis);
    });
//    //Calculation of preferential amount
    $("#money").live("blur",function (event) {
        var sumusd = 0;
        var sum = $(".sum").val();
        var dis = $("#money").val();
        dis = sum - dis;
        $("#discount").val(dis);
    });

    //Preservation
    function sub() {

        $("input.deldis").each(function (i) {
            $("input.deldis").eq(i).removeAttr("disabled");
        });
        var pkstudent = $("#pkStudent").val();
//        if(pkstudent == null || pkstudent==""){
//            alert("Please choose the students first")
//            return;
//        }
        var pkStudentSignups = $("#pkStudentSignup").val();
        var mediaInfo = $("#formId").serialize();
        var pkClassinfo=$("#pkClassinfo").val();
        var url = "${URL}";
        var reportkey = ${reportkey};

        $.ajax({
            url: "/student/oldstudentReport/saveall",
            type: "POST",
            data: mediaInfo,
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
//                    window.location.href = "/classinfo/classinfo/stureport?pkStudentSignup=" + pkStudentSignup;
                    if(reportkey == 1){
                        window.location.href = "/student/studentReport/edit?pkStudentSignup=" + pkStudentSignups;
                    }
                    if(reportkey == 2){
                        window.location.href ="/student/oldstudentReport/edit?pkStudentSignup=" + pkStudentSignups +"&pkClassinfo=" +pkClassinfo;
                    }

                } else {
                    Notify.danger(data.message);
                    location.reload();
                }
            },
        });
    }
    //Submission
    function issubmit() {

        $("input.deldis").each(function (i) {
            $("input.deldis").eq(i).removeAttr("disabled");
        });
        var pkStudentSignup = $("#pkStudentSignup").val();
        var mediaInfo = $("#formId").serialize();
        var url = "${URL}";
        var reportkey = ${reportkey};

        $.ajax({
            url: "/student/oldstudentReport/submit",
            type: "POST",
            data: mediaInfo,
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    if(reportkey == 1){
                        window.location.href = url+"?pkStudentSignup=" + pkStudentSignup;
                    }
                    if(reportkey == 2){
                        window.location.href = url+"?pkStudentSignup=" + pkStudentSignup +"&pkClassinfo=" +pkClassinfo;
                    }
                } else {
                    Notify.danger(data.message);
                    location.reload();
                }
            },
        });
    }

    //To examine
    function isaudit() {

        $("input.deldis").each(function (i) {
            $("input.deldis").eq(i).removeAttr("disabled");
        });
        var pkStudentSignup = $("#pkStudentSignup").val();
        var mediaInfo = $("#formId").serialize();
        var url = "${URL}";
        var reportkey = ${reportkey};
        $.ajax({
            url: "/student/oldstudentReport/audit",
            type: "POST",
            data: mediaInfo,
            dataType: "json",
            success: function (data) {
                Notify.success(data.message);
                if (data.code == 0) {
                    Notify.success(data.message);
                    if(reportkey == 1){
                        window.location.href = url+"?pkStudentSignup=" + pkStudentSignup;
                    }
                    if(reportkey == 2){
                        window.location.href = url+"?pkStudentSignup=" + pkStudentSignup +"&pkClassinfo=" +pkClassinfo;
                    }
                } else {
                    Notify.danger(data.message);
                    location.reload();
                }
            },
        });
    }


    //Inserted row index
    function addTr(tab, row, trHtml) {
        //ObtaintableLast line $("#tab tr:last")
        //Obtaintablefirst line $("#tab tr").eq(0)
        //ObtaintableThe countdown second lines $("#tab tr").eq(-2)
        var $tr = $("#" + tab + " tr").eq(row);
        if ($tr.size() == 0) {
            alert("Designatedtable idOr the number of rows does not exist！");
            return;
        }
        $tr.after(trHtml);
    }

    //Insert a line

    <#if detailsList??>
    var numb =${detailsList?size};
    <#else >
    var numb = 0;
    </#if>

    <#---->
    function addTr2(tab, row) {
        var trHtml = '' +
                '<tr align="center"><td>' +
                '<select name="details[' + numb + '].pkExpenseItem" id="pkExpenseItem' + numb + '" ><option value="">Please choose</option><#if expenseItems??><#list expenseItems as exp><option value="${(exp.pkExpenseItem)!}">${(exp.caption)!}</option></#list></#if></select>' +
                //            '<input type="text" name="details['+numb+'].pkExpenseItem" class="form-control bor0 bg0" placeholder="item">' +
                '</td>' +

//                '<td><input type="text" name="details[' + numb + '].unit" id="money' + numb + '"  ></td>' +
                '<td><select name="details[' + numb + '].currency" id="pkSysDictValues' + numb + '" class="form-control bor0 bg0" onchange="setMoner(' + numb + ')">><option value="">Please choose the currency</option><#if currency??><#list currency as cur><option value="${(cur.pkSysDictValues)!}">${(cur.caption)!}</option></#list></#if></select></td>' +
//                '<td><input type="text" name="details[' + numb + '].unit"  class="form-control bor0 bg0" ></td>' +
//                '<td><input type="text" name="details[' + numb + '].num"   class="form-control bor0 bg0"></td>' +
                '<td><input type="text" name="details[' + numb + '].money" id="money' + numb + '" value="${(ex.details.money)!0.00}" class="form-control bor0 bg0"></td>' +/*placeholder="Actual amount"*/


                '<td><input type="text" name="details[' + numb + '].memo" class="form-control bor0 bg0" ></td>' +
                '<td ><i  onclick="deleteRow(tab,this.parentElement.parentElement.rowIndex,'+ numb +')" class="fa fa-remove add_icon"></i>' +
                '</td></tr>';
                numb++;
        addTr(tab, row, trHtml);

    }

    //Delete a line
    function deleteRow(tableID, rowIndex,num) {
        //var table = document.getElementById(tableID);

        var money = Number($("#money"+num).val());
        var type = $("#money"+num).attr("class");
        if(type.indexOf("jine")!=-1) {
            var sum = Number($(".sum").val());
            var discount = Number($("#discount").val());
            sum = sum - money;
            $(".sum").val(sum);
            $("#money").val(sum - discount);
        }else if(type.indexOf("usd")!=-1){
            var sum = Number($(".mnyusd").val());
            sum = sum - money;
            $(".mnyusd").val(sum);
            $("#moneyUsd").val(sum);
        }

        tableID.deleteRow(rowIndex);

//    newRowIndex--;//Maintaining global variables
    }
    function selectStu(pkId,caption) {
        $("#modal").modal("hide");
        $("#stuCaption").val(caption);
        $("#pkStudent").val(pkId);
    }
    

</script>

<script type="text/javascript">


//    Inquire students
//    function findStu() {
//        $.ajax({
//            url: "/student/classinfo/findstu",
//            type: "POST",
//            data: mediaInfo,
//            dataType: "json",
//            success: function (data) {
//                if (data.code == 0) {
//                    history.back(-1);
//                } else {
//                    Notify.danger(data.message);
//                    location.reload();
//                }
//            },
//        });
//    }


    function setMoner(obj) {
        var id = $("#pkSysDictValues" + obj).val();
        if (id == "USD") {
            $("#money" + obj).attr("class","form-control bor0 bg0 usd mon");
        } else {
            $("#money" + obj).attr("class","form-control bor0 bg0 jine mon");
        }
    }

//function selectEmp(pkId,caption,id1,id2) {
//
//    $("#modal").modal("hide");
//    $("#"+id1).val(caption);
//    $("#"+id2).val(pkId);
//}

$("#pkempcapid").on("click",function () {
    var id = $(this).attr("id");
    var id2 = $(this).next().attr("id");
    $(this).attr("data-url","/student/studentSignup/getEmployees?employeekey=2&id1="+id+"&id2="+id2);
    return true;
})

</script>


