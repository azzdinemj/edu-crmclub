<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />


<div class="contentpanel">
    <div class="new-pageheader" >
        <h2><i class="fa fa-bookmark"></i><#if classinfo.caption?? >${(classinfo.caption)!}<#else >New class</#if> <span>...</span></h2>

        <div class="breadcrumb-wrapper new-breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
                    <a class="btn btn-sm btn-newblue" onclick="sub()">Preservation </a>
                    <a class="btn btn-sm  btn-newblue" onclick="subissubmit()">Submission</a>
                    <a class="btn btn-sm  btn-newblue" onclick="subisaudit()">To examine</a>
                <#if classinfo.pkClassinfo??>
                    <a class="btn btn-sm  btn-newblue" href="/classinfo/classinfo/classon?pkClassinfo=${(classinfo.pkClassinfo)!}"> Class promotion</a>
                    <a class="btn btn-sm  btn-newblue" id="stuinclass" data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/studentreturn"> Student shift</a>
                </#if>
                <a class="btn btn-sm  btn-newblue" href="/classinfo/classinfo/stureport?pkClassinfo=${(classinfo.pkClassinfo)!}"> Student registration</a>
                <a class="btn btn-sm  btn-newblue" href="/finance/payables/returnpay"> Refund</a>
                <#--<a class="btn btn-sm  btn-newblue activityStudent" data-types="1"> Practice score</a>-->
                <#--<a class="btn btn-sm  btn-newblue activityStudent" data-types="2"> Teacher's comments</a>-->
                <a class="btn btn-sm btn-newblue activityStudent" data-toggle="modal"  data-target="#modal" data-types="1" data-url="/classinfo/classinfo/studentScore" >Practice score</a>
                <a class="btn btn-sm btn-newblue activityStudent" data-toggle="modal"  data-target="#modal" data-types="2" data-url="/classinfo/classinfo/studentEvaluate" >Teacher's comments</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-yellow">
                <li class="active"><a data-toggle="tab" href="#all"><strong>Class information</strong></a></li>
                <#if classinfo.pkClassinfo??>
                    <li class=""><a data-toggle="tab" href="#assigned"><strong>Student information</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#scores"><strong>Student achievement</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#teacher"><strong>Teacher information</strong></a></li>
                </#if>

            </ul>
            <form class="look form-horizontal no-margin form-ajax" id="formId" method="post" action="/classinfo/classinfo/save"
                  data-target="/classinfo/classinfo/query">
                <input name="clas.pkParent" value="${(classinfo.pkParent)!}" type="hidden">
                <div class="tab-content">
                    <div id="all" class="tab-pane active">
                        <input name="clas.pkDomain" value="${(domain.pkDomain)!}" type="hidden">
                        <input name="clas.pkClassinfo" id="classId" value="${(classinfo.pkClassinfo)!}" type="hidden">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Class number</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input title="" readonly name="clas.code" value="${(classinfo.code)!}" class="form-control" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Class name</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.caption" id="classCaption" value="${(classinfo.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Opening date</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.dateTime" value="${(classinfo.date?string("yyyy-MM-dd"))!}" id="data" type="text" title="" class="form-control " value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Headmaster</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly id="headTeachercap" value="${(headTeacher.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=5">
                                        <input readonly name="clas.headTeacher" id="headTeacherpk" value="${(headTeacher.pkEmployee)!}" type="hidden">
                                        <#--<select name="clas.headTeacher">-->
                                        <#--<#if employees ??>-->
                                            <#--<#list employees as e>-->
                                                <#--<option value="${(e.pkEmployee)!}" <#if e.pkEmployee??&& classinfo.headTeacher??&& classinfo.headTeacher==e.pkEmployee>selected="selected"</#if>>${(e.caption)!}</option>-->
                                            <#--</#list>-->
                                        <#--</#if>-->
                                        <#--</select>-->
                                        <#--<input class="form-control" name="clas.headTeacher" value="${(classinfo.headTeacher)!}" title="" type="text">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">second teacher</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly id="secondTeachercap" value="${(secondTeacher.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=6">
                                        <input readonly name="clas.secondTeacher" id="secondTeacherpk" value="${(secondTeacher.pkEmployee)!}" type="hidden">
                                        <#--<select name="clas.secondTeacher">-->
                                            <#--<#if employees ??>-->
                                                <#--<#list employees as e>-->
                                                    <#--<option value="${(e.pkEmployee)!}" <#if e.pkEmployee??&& classinfo.secondTeacher??&& classinfo.secondTeacher==e.pkEmployee>selected="selected"</#if>>${(e.caption)!}</option>-->
                                                <#--</#list>-->
                                            <#--</#if>-->
                                        <#--</select>-->
                                        <#--<input class="form-control" name="clas.secondTeacher" value="${(classinfo.secondTeacher)!}" title="" type="text">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Academic Director</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly id="directorcap" value="${(director.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=7">
                                        <input readonly name="clas.director" id="directorpk" value="${(director.pkEmployee)!}" type="hidden">
                                        <#--<select name="clas.director">-->
                                        <#--<#if employees ??>-->
                                            <#--<#list employees as e>-->
                                                <#--<option value="${(e.pkEmployee)!}" <#if e.pkEmployee??&& classinfo.secondTeacher??&& classinfo.secondTeacher==e.pkEmployee>selected="selected"</#if>>${(e.caption)!}</option>-->
                                            <#--</#list>-->
                                        <#--</#if>-->
                                        <#--</select>-->
                                        <#--<input class="form-control" name="clas.director" value="${(classinfo.director)!}" title="" type="text">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">start time</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.startDateTime" value="${(classinfo.startDate?string("yyyy-MM-dd"))!}" type="text" title="" class="form-control js-datepicker" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">End time</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.endDateTime" value="${(classinfo.endDate?string("yyyy-MM-dd"))!}" type="text" title="" class="form-control js-datepicker" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Faculty</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.division">
                                            <option value="">Please choose</option>
                                            <#if (divisions?size > 0)>
                                                <#list divisions as d>
                                                    <option value="${(d.pkSysDictValues)!}" <#if d.pkSysDictValues?? && classinfo.division ?? && classinfo.division== d.pkSysDictValues>selected</#if> >${(d.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Classroom</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.classRoom">
                                            <#if classRooms ??>
                                                <#list classRooms as room>
                                                    <option value="${(room.pkClassRoom)!}" <#if room.pkClassRoom?? && classinfo.classRoom??&& room.pkClassRoom==classinfo.classRoom >selected="selected"</#if>>${(room.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                        <#--<input class="form-control" name="clas.classCoom" value="${(classinfo.classCoom)!}" title="" type="text">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Week class</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.courseTime" value="${(classinfo.courseTime)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">project</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.program">
                                            <#if project ??>
                                                <#list project as pro>
                                                    <option value="${(pro.pkSysDictValues)!}" <#if pro.pkSysDictValues ??&& classinfo.program ?? &&classinfo.program==pro.pkSysDictValues>selected="selected"</#if>>${(pro.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                        <#--<input class="form-control" name="clas.program" value="${(classinfo.program)!}" title="" type="text">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">grade</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.grade">
                                            <#if grade ??>
                                                <#list grade as g>
                                                    <option value="${(g.pkSysDictValues)!}" <#if g.pkSysDictValues?? && classinfo.grade?? && classinfo.grade==g.pkSysDictValues>selected="selected"</#if> >${(g.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                        <#--<input class="form-control" name="clas.grade" value="${(classinfo.grade)!}" title="" type="text">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Class type</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.type">
                                            <option value="0" <#if classinfo.type?? && classinfo.type==0>selected="selected"</#if>>Formal class</option>
                                            <option value="1" <#if classinfo.type?? && classinfo.type==1>selected="selected"</#if>>Practice class</option>
                                            <option value="2" <#if classinfo.type?? && classinfo.type==2>selected="selected"</#if>>An association</option>
                                            <option value="3" <#if classinfo.type?? && classinfo.type==3>selected="selected"</#if>>Elective course</option>
                                            <option value="4" <#if classinfo.type?? && classinfo.type==4>selected="selected"</#if>>Interest class</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label  col-xs-1 col-md-1">Remarks</label>
                                    <div class="col-xs-11 col-md-11">
                                        <textarea rows="2" name="clas.notes" value="${(classinfo.notes)!}" class="form-control" title="">${(classinfo.notes)!}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">submitter</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly  class="form-control" value="${(classinfo.map.submitorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Submitted</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly type="text" title="" value="${(classinfo.submitDateTime)!}" class="form-control" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">is submit</label>

                                    <div class="col-xs-9 col-md-9 pt7">
                                       <span class="rdio rdio-success">
                                           <input name="clas.issubmit" value="1" disabled class="deldis"
                                                  <#if classinfo.issubmit??&& classinfo.issubmit ==1 >checked="checked"</#if>
                                                  id="radio1" type="radio">
                                           <label disabled="disabled" for="radio1">是</label>
                                       </span>
                                        <span class="rdio rdio-success">
                                           <input name="clas.issubmit" value="0" id="radio2" disabled class="deldis"
                                                  <#if classinfo.issubmit??&& classinfo.issubmit ==1 ><#else >checked="checked"</#if>
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
                                        <input readonly class="form-control" value="${(classinfo.map.auditorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Audit time</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(classinfo.auditDateTime)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">is review</label>

                                    <div class="col-xs-9 col-md-9 pt7">
                                       <span class="rdio rdio-success">
                                           <input name="clas.isaudit" value="1" disabled class="deldis"
                                                  <#if classinfo.isaudit??&& classinfo.isaudit ==1 >checked="checked"</#if>
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">是</label>
                                       </span>
                                        <span class="rdio rdio-success">
                                           <input name="clas.isaudit" value="0" id="radio4" disabled class="deldis"
                                                  <#if classinfo.isaudit??&& classinfo.isaudit ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio4">否</label>
                                       </span>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Founder</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="clas.creator" class="form-control" value="${(classinfo.map.creatorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Creation time</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(classinfo.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Modifier</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="clas.modifier" class="form-control" value="${(classinfo.map.modifierEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Modified</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(classinfo.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="assigned" class="tab-pane">
                        <table id="table" class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <td>number</td>
                                <td>name</td>
                                <td>Sex</td>
                                <td>birthday</td>
                                <td>Ethnic</td>
                                <td>ID number</td>
                                <td>type</td>
                                <td>Practice score</td>
                                <td>Teacher evaluation</td>


                            </tr>
                            </thead>

                            <tbody>
                            <#if students ??>
                            <#list students as s>
                            <tr dataid="${(s.pkStudent)!}" dataCaption="${(s.caption)!}" dataScore="${(s.map.studentScoreEntity.code)!}" dataEv="${(s.map.studentScoreEntity.caption)!}">

                                <td>${(s.code)!}</td>
                                <td>
                                    <a href="/student/student/edit?pkStudent=${(s.pkStudent)!}">
                                    ${(s.caption)!}
                                    </a>
                                </td>
                                <td>
                                    <#if s.sex ?? && s.sex==1>男<#else >女</#if>
                                </td>
                                <td>${(s.birthday?string("yyyy-MM-dd"))!}</td>
                                <td>${(s.nationEntity.caption)!}</td>
                                <td>${(s.idCard)!}</td>
                                <td>
                                    <#if s.istype ?? && s.istype==0>Intentional students<#elseif s.istype ?? && s.istype==1>Formal students<#elseif s.istype ?? && s.istype==3>Leave school
                                    <#elseif s.istype ?? && s.istype==4>Drop school</#if>
                                </td>
                                <td>
                                    <#if s.map.studentScoreEntity.code ??>
                                        <span class="level">
                                            <#list 0..(s.map.studentScoreEntity.code?number) as t>
                                                <i class="level_solid"></i>
                                            </#list>
                                        </span>
                                        <#else >
                                        No score
                                    </#if>
                                </td>
                                <td>${(s.map.studentScoreEntity.caption)!}</td>
                            </tr>
                            </#list>
                            </#if>

                            </tbody>
                        </table>
                    <#--  </div>-->
                    </div>

                    <div id="scores" class="tab-pane">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">Student name</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input title=""  id="caption" name="" value="" class="form-control" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="btn btn-newblue btn-sm search" onclick="query()"><i class="fa fa-search"> </i>search</span>
                            </div>
                        </div>

                        <table id="pagingTable" class="table table-color" cellspacing="0">

                            <thead>
                            </thead>

                            <tbody id="parentTab">

                            </tbody>
                        </table>
                    <#--  </div>-->
                    </div>

                    <#--<table id="tableTeacher" class="table table-color" cellspacing="0">-->

                        <#--<thead>-->
                        <#--</thead>-->

                        <#--<tbody id="parentTab">-->

                        <#--</tbody>-->
                    <#--</table>-->

                    <div id="teacher" class="tab-pane">
                        <table id="pagingTableTeacher" class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <td>number</td>
                                <td>name</td>
                                <td>Sex</td>
                                <td width="10%"><a data-toggle="modal"  data-target="#modal" data-url="/classinfo/classinfoEmployee/studentTeacherAdd?pkClassinfo=${(classinfo.pkClassinfo)!}"><i class="fa fa-plus add_icon"></i></a>
                                </td>
                            </tr>
                            </thead>

                            <tbody>
                            <#if employees ??>
                                <#list employees as es>
                                <tr dataid="${(s.pkStudent)!}">

                                    <td>${(es.code)!}</td>
                                    <td>
                                        <a href="/system/employee/edit?pkEmployee=${(es.pkEmployee)!}">
                                        ${(es.caption)!}
                                        </a>
                                    </td>
                                    <td>
                                        <#if es.sex ?? && es.sex==1>男<#else >女</#if>
                                    </td>
                                    <td><i onclick="deleteEmployee('${(es.pkEmployee)!}')" class="fa fa-remove add_icon"></i></td>
                                </tr>
                                </#list>
                            </#if>

                            </tbody>
                        </table>
                    <#--  </div>-->
                    </div>
                </div>
            </form>
        </div>
    </div>

</div><!-- panel -->

<#include "../commons/footer.ftl"/>

<script type="text/javascript">
    $(function () {

        <#if classinfo.issubmit ?? && classinfo.issubmit ==1>
            $(".isnone").attr("disabled", "disabled");
        </#if>
        <#if classinfo.isaudit??&& classinfo.isaudit==1> $(".isaudit").attr("disabled", "disabled");</#if>

        $(".activityStudent").on("click",function(){

            var types = $(this).data("types");

//            var formdata = {};
            var classesid = $("#classId").val();
            var className = $("#classCaption").val();
            var id = $(".table-color-").attr("dataid");
            var studentName = $(".table-color-").attr("dataCaption");
            var score = $(".table-color-").attr("dataScore");
            var ev = $(".table-color-").attr("dataEv");
            if (id == null) {
                Notify.danger("Please select a row of data！！！");
                return false;
            }

            if(types == 1) {
                if (score != "" && score != null && score != "null") {
                    Notify.danger("Non repeatable score");
                    return false;
                }
            }else {
                if (ev != "" && ev != null) {
                    Notify.danger("Non repeatable evaluation");
                    return false;
                }
            }

            $(this).attr("data-url",$(this).attr("data-url")+"?pkClassinfo="+classesid+"&pkStudent="+id+"&studentName="+studentName+"&className="+className);

//            formdata.pkClassinfo = classesid;
//            formdata.pkStudent = id;
//            if(types == 1){
//                formdata.score = "2";
//            }else{
//                formdata.evaluate = "121212"
//            }
//            $.ajax({
//                url:"/classinfo/classinfo/insertActivityStudent",
//                data:formdata,
//                type:"POST",
//                dataType:"json",
//                success:function (data) {
//                    if(data.code ==0){
//                        location.reload();
//                    }else {
//                        Notify.danger(data.message);
//                    }
//                }
//            });
        })
    });
//    Preservation
    function sub() {
        $("#formId").submit();

    }
    //Submission
    function subissubmit() {
        var formdata = $("#formId").serialize();
        $.ajax({
           url:"/classinfo/classinfo/submit",
            data:formdata,
            type:"POST",
            dataType:"json",
            success:function (data) {
                if(data.code ==0){
                    location.reload();
                }else {
                    Notify.danger(data.message);
                }
            }
        });
    }
    //To examine
    function subisaudit() {
        var formdata = $("#formId").serialize();
        $.ajax({
           url:"/classinfo/classinfo/audit",
            data:formdata,
            type:"POST",
            dataType:"json",
            success:function (data) {
                if(data.code ==0){
                    alert("Success")
                    Notify.success(data.message);
                    location.reload();
                }else {
                    Notify.danger(data.message);
                    location.reload();
                }
            }
        });
    }

    $("#stuinclass").on("click",function () {
        var pkStudent = $(".table-color-").attr("dataid");
        if(pkStudent==null || pkStudent==""){
            Notify.danger("Please choose the students first");
            return false;
        }
        var oldPkClassinfo = $("#classId").val();
        $(this).attr("data-url",$(this).attr("data-url")+"?pkStudent="+pkStudent+"&oldPkClassinfo="+oldPkClassinfo);
    });

    function deleteEmployee(id) {
        if(id) {
            var pkClassinfo = $("#classId").val();
            var flag = confirm("Are you sure you want to delete it？");

            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/classinfo/classinfoEmployee/delete",
                    data: {"pkClassinfo": pkClassinfo,"pkEmployee":id},
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            Notify.success(data.message);
                            setTimeout("location.reload()", 1);
                        } else {
                            Notify.danger(data.message);
                            window.location.reload();
                        }
                    }
                });
            }
        }

    }


    var columns = [
        {"sTitle":"Student name","data" : "map.studentEntity.caption",
            "render": renderStudentScoreEdit} ,
        {"sTitle":"curriculum","data" : "map.sysdicEntity.caption"},
        {"sTitle":"Teacher","data" : "map.userEntity.caption"},
        {"sTitle":"Exam results","data" : "scores"},
        {"sTitle":"examination Type","data" : "map.testTypeEntity.caption"},
        {"sTitle":"exam name","data" : "map.scoreCaptionEntity.caption"},
        {"sTitle":"weight","data" : "weight"},
        {"sTitle":"Particular year","data" : "year","render": renderDate},
        {"sTitle":"Semester","data" : "map.termEntity.caption"},
        {"sTitle":"Remarks","data" : "memo"}
    ];

    $(document).ready(function() {
        query();
//        queryTeacher();
    } );

    function query() {
        var data  = { "pkClassinfo": $("#classId").val(),"caption":$("#caption").val()};
        init(GetTableColumn("classinfoStudentScoreList"),"/classinfo/studentScoress/queryByPaging",data,"pagingTable");
    }

    <#--function queryTeacher() {-->
        <#--var data = {"pkClassinfo": $("#classId").val()};-->
        <#--init(GetTableColumn("classinfoTeacherList"), "/classinfo/classinfoEmployee/queryByPaging", data,"tableTeacher");-->
    <#--}-->

    <#--function add(obj) {-->
        <#--var url = $("#"+obj).attr("data-url");-->
        <#--$("#"+obj).attr("data-url",url + "?pkClassinfo=${(classinfo.pkClassinfo)!}");-->
        <#--return true;-->
    <#--}-->

</script>