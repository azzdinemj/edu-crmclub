<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<form class="look form-ajax" method="POST" id="formId" action="/student/student/saveall"
      data-target="${(URL)!}?pkStudent=${(student.pkStudent)!}"  >
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>Student files <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
            <button class="btn btn-sm btn-newblue" type="submit">Preservation</button>
        <#--<#if student.istype?? && student.istype ==1>-->
            <#--<#if  mentalitykey ?? && mentalitykey ==1>-->
            <#--<#else >-->
                <#--<a class="btn btn-sm btn-newblue" data-toggle="modal"  data-target="#modal" data-url="/student/student/refund?pkStudent=${(student.pkStudent)!}">Refund </a>-->
            <#--</#if>-->

        <#--</#if>-->
        <#if student.istype?? && student.istype ==0 >
            <a class="btn btn-sm btn-newblue" href="/student/studentInterview/create?pkStudent=${(student.pkStudent)!}">Interview</a><!-- Interview -->
            <a class="btn btn-sm btn-newblue"
               href="/student/studentReport/create?pkStudent=${(student.pkStudent)!}">sign</a><!-- sign -->
        </#if>
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">

                <li class="active"><a data-toggle="tab" href="#all"><strong>Student information</strong></a></li>
            <#--<#if interviewkey?? && interviewkey==2>-->

            <#--</#if>-->

            <#if student.istype?? && student.istype ==1 >

            <#if  mentalitykey ?? && mentalitykey ==1>
                <li class=""><a data-toggle="tab" href="#added"><strong>Consulting record</strong></a></li>

                <#else>
                    <li><a data-toggle="tab" href="#hobby"><strong>special hobby</strong></a></li>
                    <li><a data-toggle="tab" href="#plans"><strong>Student plan</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#added">Interview records</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#discipline"><strong>Disciplinary record</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#assigned"><strong>Educational experience </strong></a></li>
                    <li class=""><a data-toggle="tab" href="#unresolved"><strong> Activity experience </strong></a></li>
                    <li class=""><a data-toggle="tab" href="#sampleReels"><strong> Sample reels </strong></a></li>
                    <li class=""><a data-toggle="tab" href="#prize"><strong> Winning experience</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#transcript"><strong>School report</strong></a></li>
                    <#--<li class=""><a data-toggle="tab" href="#additional"><strong>Additional information</strong></a></li>-->
            </#if>
            <#else >
                <li class=""><a data-toggle="tab" href="#interview"><strong>Interview information</strong></a></li>
            </#if>
            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">


                    <#--<h3>Student information</h3>-->
                        <input id="pkStudent" name="stu.pkStudent" value="${(student.pkStudent)!}" type="hidden">
                        <input name="stu.creator" value="${(student.creator)!}" type="hidden">
                        <div class="row">

                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;margin-bottom: 15px;">Essential information</h3>
                            </div>

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">campus</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" readonly value="${(domain.caption)!}"
                                               type="text">
                                        <input name="stu.pkDomain" value="${(domain.pkDomain)!}" type="hidden">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">number</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" readonly name="stu.code" value="${(student.code)!}"
                                               type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">alias</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" name="stu.shortCaption"
                                               value="${(student.shortCaption)!}"
                                               type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">School number</label>
                                    <div class="col-xs-9"><input name="stu.studentId" value="${(student.studentId)!}"
                                                                 type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">classification</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.studentClass">
                                            <option value="">Please choose</option>
                                        <#if studentclass ??>
                                            <#list studentclass as s>
                                                <option value="${(s.pkSysDictValues)!}"
                                                        <#if s.pkSysDictValues ?? && student.studentClass??&& s.pkSysDictValues==student.studentClass>selected="selected"</#if>> ${(s.caption)!}</option>
                                            </#list>
                                        </#if>

                                        </select>
                                    <#--<input class="form-control" name="stu.studentClass"
                                           value="${(student.studentClass)!}"
                                           type="text">-->

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>name</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="txtChinese" name="stu.caption" onblur="getmakePy()" value="${(student.caption)!}" type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>Sex</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.sex" value="1" class="deldis"
                                                  <#if student.sex??&& student.sex ==0 ><#else >checked="checked"</#if>
                                                  id="radio11" type="radio">
                                           <label for="radio11">男</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.sex" value="0" id="radio12" class="deldis"
                                                  <#if student.sex??&& student.sex ==0 >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio12">女</label>
                                       </span>
                                    <#--<select name="stu.sex">-->
                                    <#--<option value="1" <#if student.sex??&& student.sex==1>selected="selected"</#if>>-->
                                    <#--男-->
                                    <#--</option>-->
                                    <#--<option value="2" <#if student.sex??&& student.sex==2>selected="selected"</#if>>-->
                                    <#--女-->
                                    <#--</option>-->
                                    <#--</select>-->
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>Mnemonic code</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" name="stu.shortCode" id="shortCodeId" value="${(student.shortCode)!}"
                                               type="text">

                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="student-img upload text-center">
                                    <input type="file" name="file" id="doc" style="width:80px;"
                                           onchange="ajaxSubmitForm()" />
                                    <div id="dd">
                                        <#if student.img??>
                                            <img src="${(student.img)!}">
                                        <#else >
                                            <img src="${staticPath}/images/photos/icon-h.jpg">
                                        </#if>
                                    </div>
                                    <p class="gray">Upload head</p>
                                    <input type="hidden" name="stu.img" id="studentImg" value="${(student.img)!}">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>birthday</label>
                                    <div class="col-xs-9">
                                        <input name="stu.birthdayTime"
                                               value="${(student.birthday?string("yyyy-MM-dd"))!}" id="data" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">type</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.istype">
                                            <option value=0
                                                    <#if student.istype?? && student.istype==0>selected="selected"</#if>>
                                                Intentional students
                                            </option>
                                            <option value=1
                                                    <#if student.istype?? && student.istype==1>selected="selected"</#if>>
                                                Formal students
                                            </option>
                                            <option value=2
                                                    <#if student.istype?? && student.istype==2>selected="selected"</#if>>
                                                Ineffective students
                                            </option>
                                        <#if empkey ?? && empkey==3>
                                            <option value=3
                                                    <#if student.istype?? && student.istype==2>selected="selected"</#if>>
                                                Drop out school
                                            </option>
                                            <option value=4
                                                    <#if student.istype?? && student.istype==2>selected="selected"</#if>>
                                                Refund application
                                            </option>
                                            <option value=5
                                                    <#if student.istype?? && student.istype==2>selected="selected"</#if>>
                                                graduation
                                            </option>
                                        </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        <#if student.istype?? && student.istype ==1 >
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">status</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.state">
                                            <option value=-1
                                                    <#if student.state?? && student.state==-1>selected="selected"</#if>>

                                            </option>
                                            <option value=0
                                                    <#if student.state?? && student.state==0>selected="selected"</#if>>
                                                Turn out
                                            </option>
                                            <option value=1
                                                    <#if student.state?? && student.state==1>selected="selected"</#if>>
                                                school
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </#if>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">phone</label>
                                    <div class="col-xs-9">
                                        <input name="stu.phone" value="${(student.phone)!}"  type="text" class="form-control"
                                               data-bv-phone="true" data-bv-phone-country="ZHCN" data-bv-phone-message="Please enter the correct cell phone number" >
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>address</label>
                                    <div class="col-xs-9">
                                        <input name="stu.address" value="${(student.address)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>grade</label>
                                    <div class="col-xs-9">
                                        <select name="stu.grade">
                                            <option value="">Please choose</option>
                                        <#if grade ??>
                                            <#list grade as gra>
                                                <option value="${(gra.pkSysDictValues)!}"
                                                        <#if gra.pkSysDictValues ?? && student.grade??&& gra.pkSysDictValues==student.grade>selected="selected"</#if>> ${(gra.caption)!}</option>

                                            </#list>
                                        </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>ID type</label>
                                    <div class="col-xs-9">

                                    <#if idkind??>
                                        <select name="stu.idkind" id="idkindId">
                                            <option value="">Please choose</option>
                                            <#list idkind as idkind>
                                                <option value="${(idkind.pkSysDictValues)!}"
                                                        <#if idkind.pkSysDictValues?? && student.idKind ?? && student.idKind==idkind.pkSysDictValues>selected="selected"</#if>>${(idkind.caption)!}</option>
                                            </#list>
                                        </select>
                                    </#if>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">ID address</label>
                                    <div class="col-xs-9">
                                        <input name="stu.idAddress" value="${(student.idAddress)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>ID number</label>
                                    <div class="col-xs-9">
                                        <input name="stu.idCard" value="${(student.idCard)!}" id="idCradId" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Certificate maturity</label>
                                    <div class="col-xs-9">
                                        <input name="stu.passportDateTime" value="${(student.passportDate?string("yyyy-MM-dd"))!}" type="text" class="form-control js-datepicker">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>

                        <div class="row">
                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;margin-bottom: 15px;"> Registration information</h3>
                            </div>

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Cluesource</label>
                                    <div class="col-xs-9">
                                        <select name="stu.tracksource">
                                            <option value="">Please choose</option>
                                        <#if channel ??>
                                            <#list channel as ch>
                                                <option value="${(ch.pkSysDictValues)!}"
                                                        <#if ch.pkSysDictValues?? && student.tracksource ?? && student.tracksource ==ch.pkSysDictValues>selected</#if>>${(ch.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Personal</label>
                                    <div class="col-xs-9">
                                        <input readonly  id="empcapid" value="${(employee.caption)!}" class="form-control" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=2">
                                        <input readonly name="stu.pkSysUser" id="pkempid" value="${(employee.pkEmployee)!}" type="hidden">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">project</label>
                                    <div class="col-xs-9">
                                        <select name="stu.program">
                                            <option value="">Please choose</option>
                                        <#if project ??>
                                            <#list project as pro>
                                                <option value="${(pro.pkSysDictValues)!}" <#if pro.pkSysDictValues ??&& student.program ?? &&student.program==pro.pkSysDictValues>selected="selected"</#if>>${(pro.caption)!}</option>
                                            </#list>
                                        </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Student status</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.isSchoolrool" value="1" class="deldis"
                                                  <#if student.isSchoolrool??&& student.isSchoolrool ==1 >checked="checked"</#if>
                                                  id="radio15" type="radio">
                                           <label disabled="disabled" for="radio15">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isSchoolrool" value="0" id="radio16" class="deldis"
                                                  <#if student.isSchoolrool??&& student.isSchoolrool ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio16">否</label>
                                       </span>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">School roll</label>
                                    <div class="col-xs-9">
                                        <input name="stu.schoolrool" value="${(student.schoolrool?c)!}" type="number" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">School address</label>
                                    <div class="col-xs-9"><input name="stu.schoolroolAddress"  value="${(student.schoolroolAddress)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Mental health</label>
                                    <div class="col-xs-9">
                                        <select name="stu.mentalHealth">
                                            <option value="">Please choose</option>
                                        <#if mentalhealth ??>
                                            <#list mentalhealth as h>
                                                <option value="${(h.pkSysDictValues)!}"
                                                        <#if h.pkSysDictValues?? && student.mentalHealth ??&& student.mentalHealth ==h.pkSysDictValues>selected</#if>>${(h.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    <#-- <input name="stu.mentalHealth" value="${(student.mentalHealth)!}" type="text"
                                            class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>Whether stay</label>
                                    <div class="col-xs-9">

                                         <span class="rdio rdio-warning">
                                           <input name="stu.isboarding" value="1" class="deldis"
                                                  <#if student.isboarding??&& student.isboarding ==1 >checked="checked"</#if>
                                                  id="radio1" type="radio">
                                           <label disabled="disabled" for="radio1">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isboarding" value="0" id="radio2" class="deldis"
                                                  <#if student.isboarding??&& student.isboarding ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio2">否</label>
                                       </span>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Ride bus</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.isByschoolbus" value="1" class="deldis"
                                                  <#if student.isByschoolbus??&& student.isByschoolbus ==1 >checked="checked"</#if>
                                                  id="radio13" type="radio">
                                           <label disabled="disabled" for="radio13">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isByschoolbus" value="0" id="radio14" class="deldis"
                                                  <#if student.isByschoolbus??&& student.isByschoolbus ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio14">否</label>
                                       </span>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->


                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Study cost</label>
                                    <div class="col-xs-9">
                                    <#--  <input value="val..replace(/,/g,''); " onkeyup="this.value=this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3')">-->
                                        <input name="stu.studyAbroadFee" id="studyAbroadFee"
                                               value="${(student.studyAbroadFee?string("#.##"))!}" class="form-control"
                                               onkeypress="if((event.keyCode<48 || event.keyCode>57) &amp; event.keyCode!=46 || /\.\d\d$/.test(value))event.returnValue=false">
                                    <#--<input name="stu.studyAbroadFee" value="${(student.studyAbroadFee)!}" class="form-control" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Origin school</label>
                                    <div class="col-xs-9">
                                        <input name="stu.oldSclool" value="${(student.oldSclool)!}" type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Student dream</label>
                                    <div class="col-xs-9">
                                        <input name="stu.dream" value="${(student.dream)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Enrolment</label>
                                    <div class="col-xs-9">
                                        <input name="stu.inState" value="${(student.inState)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Status analysis</label>
                                    <div class="col-xs-9"><input name="stu.situationAnalysis" value="${(student.situationAnalysis)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Graduating</label>
                                    <div class="col-xs-9">
                                        <input name="stu.graduationGo" value="${(student.graduationGo)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>


                        <div class="row">

                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;margin-bottom: 15px;"> Other information</h3>
                            </div>

                        </div>


                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">nationality</label>
                                    <div class="col-xs-9">
                                        <select name="stu.nationality">
                                            <option value="">Please choose</option>
                                        <#if country ??>
                                            <#list country as co>
                                                <option value="${(co.pkSysDictValues)!}"
                                                        <#if co.pkSysDictValues?? && student.nationality ?? && student.nationality ==co.pkSysDictValues>selected</#if>>${(co.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    <#--<input name="stu.nationality" value="${(student.nationality)!}"-->
                                    <#--type="text" class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Mother tongue</label>
                                    <div class="col-xs-9">
                                        <select name="stu.motherLan">
                                            <option value="">Please choose</option>
                                        <#if motherlan ??>
                                            <#list motherlan as mo>
                                                <option value="${(mo.pkSysDictValues)!}"
                                                        <#if mo.pkSysDictValues?? && student.motherLan ?? && student.motherLan ==mo.pkSysDictValues>selected</#if>>${(mo.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    <#-- <input name="stu.motherLan" value="${(student.motherLan)!}"
                                                              type="text" class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Origin</label>
                                    <div class="col-xs-9">
                                        <input name="stu.oldPlace" value="${(student.oldPlace)!}" type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">health</label>
                                    <div class="col-xs-9">
                                        <select name="stu.healthyBody">
                                            <option value="">Please choose</option>
                                        <#if health ??>
                                            <#list health as ch>
                                                <option value="${(ch.pkSysDictValues)!}"
                                                        <#if ch.pkSysDictValues?? && student.healthyBody ??&& student.healthyBody ==ch.pkSysDictValues>selected</#if>>${(ch.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    <#-- <input name="stu.healthyBody" value="${(student.healthyBody)!}" type="text"
                                            class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">green card</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.greenCard" value="1" class="deldis"
                                                  <#if student.greenCard??&& student.greenCard ==1 >checked="checked"</#if>
                                                  id="radio7" type="radio">
                                           <label disabled="disabled" for="radio7">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.greenCard" value="0" id="radio8" class="deldis"
                                                  <#if student.greenCard??&& student.greenCard ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio8">否</label>
                                       </span>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Green country</label>
                                    <div class="col-xs-9">
                                        <select name="stu.greenCardCountry">
                                            <option value="">Please choose</option>
                                        <#if country ??>
                                            <#list country as co>
                                                <option value="${(co.pkSysDictValues)!}"
                                                        <#if co.pkSysDictValues?? && student.greenCardCountry ?? && student.greenCardCountry ==co.pkSysDictValues>selected</#if>>${(co.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>
                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Zip code</label>
                                    <div class="col-xs-9">
                                        <input name="stu.zip" value="${(student.zip)!}" type="text" class="form-control" maxlength="6">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">mailbox</label>
                                    <div class="col-xs-9">
                                        <input name="stu.email" value="${(student.email)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Parental feedback</label>
                                    <div class="col-xs-9">
                                        <input name="stu.parentFeedback" value="${(student.parentFeedback)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>


                        <div class="row">


                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">minority</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.isminority" value="1" class="deldis"
                                                  <#if student.isminority??&& student.isminority ==1 >checked="checked"</#if>
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isminority" value="0" id="radio4" class="deldis"
                                                  <#if student.isminority??&& student.isminority ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio4">否</label>
                                       </span>

                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Ethnic</label>
                                    <div class="col-xs-9">
                                        <select name="stu.nation">
                                            <option value="">Please choose</option>
                                        <#if nation ??>
                                            <#list nation as co>
                                                <option value="${(co.pkSysDictValues)!}"
                                                        <#if co.pkSysDictValues?? && student.nation ?? && student.nation ==co.pkSysDictValues>selected</#if>>${(co.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    <#-- <input name="stu.nation" value="${(student.nation)!}" type="text"
                                            class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Parent promise</label>
                                    <div class="col-xs-9">
                                        <input name="stu.parentsExpect" value="${(student.parentsExpect)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>

                        <div class="row">



                        </div>
                        <div class="row">




                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Religious belief</label>
                                    <div class="col-xs-9">
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isreligion" value="1" class="deldis"
                                                  <#if student.isreligion??&& student.isreligion ==1 >checked="checked"</#if>
                                                  id="radio5" type="radio">
                                           <label disabled="disabled" for="radio5">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isreligion" value="0" id="radio6" class="deldis"
                                                  <#if student.isreligion??&& student.isreligion ==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio6">否</label>
                                       </span>

                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">Religious name</label>
                                    <div class="col-xs-9">
                                        <input name="stu.religion" value="${(student.religion)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->


                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-1">Teacher evaluation</label>
                                    <div class="col-xs-9 col-md-11">
                                        <input name="stu.teacherAppraisal" value="${(student.teacherAppraisal)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->


                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1 col-md-1">Remarks</label>
                                    <div class="col-xs-11 col-md-11">
                                    <#--<input name="stu.memo" value="${(student.memo)!}" type="text" class="form-control">-->
                                        <textarea class="form-control" name="stu.memo" rows="3" va>${(student.memo)!}</textarea>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div><!-- col-sm-4 -->

                        <div class="col-sm-12">
                            <h3>Family information</h3>
                        </div>
                        <div class="">
                            <table id="tab" class="table bor1 table-color  mb30">
                                <thead>
                                <tr>
                                    <td>name</td>
                                    <td>relationship</td>
                                    <td width="80px">Sex</td>
                                    <td>Occupation</td>
                                    <td>post</td>
                                    <td>Work unit</td>
                                    <td>Education</td>
                                    <td>Telephone</td>
                                    <td>address</td>
                                    <td width="5%"><i class="fa fa-remove op0 add_icon"></i><i
                                            onclick="addTr2('tab', -1)" class="fa fa-plus add_icon"></i>
                                    </td>


                                </tr>
                                </thead>
                                <tbody id="parentTab">
                                <#if linkMans??>
                                    <#assign  index = 0 >
                                    <#list linkMans as man>
                                    <tr>
                                        <td align="left">
                                            <input name="man[${index}].caption"  value=" ${(man.caption)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                            <input name="man[${index}].pkDomain" value="${(man.pkDomain)!}"
                                                   type="hidden">
                                            <input name="man[${index}].pkLinkman" value="${(man.pkLinkman)!}"
                                                   type="hidden">
                                            <input name="man[${index}].pkStudent" value="${(man.pkStudent)!}"
                                                   type="hidden">
                                            <input name="man[${index}].code" value="${(man.code)!}" type="hidden">
                                            <input name="man[${index}].creator" value="${(man.creator)!}" type="hidden">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].relationship"
                                                   value=" ${(man.relationship)!}"
                                                   type="text" class="form-control bor0">
                                        </td>
                                        <td>
                                        <#-- <input name="man.sex" readonly value=" ${(man.sex)!}" type="text" class="form-control bor0">-->
                                            <select name="man[${index}].sex" class="form-control bor0">
                                                <option value="1"
                                                        <#if man.sex?? && man.sex==1>selected="selected"</#if>>男
                                                </option>
                                                <option value="2"
                                                        <#if man.sex?? && man.sex==2>selected="selected"</#if>>女
                                                </option>
                                            </select>
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].occupation"
                                                   value=" ${(man.occupation)!}"
                                                   type="text" class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].duty"  value=" ${(man.duty)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].workUnit"  value=" ${(man.workUnit)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].degreeCompleted"
                                                   value=" ${(man.degreeCompleted)!}" type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].phone"  value=" ${(man.phone)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].address"  value=" ${(man.address)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                    </tr>
                                        <#assign index = index+1> <!--listCycle once，variable+1-->

                                    </#list>
                                </#if>

                                </tbody>
                            </table>
                        </div>



                </div><!-- tab-content -->

                <!--Interview information-->
            <#--<#if interviewkey?? && interviewkey==2 >-->
                <div id="interview" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTableInterview" class="table bor1 table-color table-hover mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>campus</th>-->
                                <#--<th>Record number</th>-->

                                <#--<th>Interview topics</th>-->
                                <#--<th>interview teacher</th>-->
                                <#--<th>Interview time</th>-->
                                <#--<th>Interview situation</th>-->
                                <#--<th>Remarks</th>-->

                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if interviews??>-->
                                <#--<#list interviews as inter>-->
                                <#--<tr>-->
                                    <#--<td>${(inter.pkDomain)!}</td>-->
                                    <#--<td>${(inter.code)!}</td>-->
                                    <#--<td>${(inter.caption)!}</td>-->
                                    <#--<td>${(inter.pkEmployee)!}</td>-->
                                    <#--<td>${(inter.dateTime)!}</td>-->
                                    <#--<td>${(inter.remark)!}</td>-->
                                    <#--<td>${(inter.memo)!}</td>-->
                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                    </div>
                </div>
            <#--</#if>-->

                <!--Student plan-->
                <div id="plans" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTablePlans" class="table bor1 table-color table-hover mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>application Country</th>-->
                                <#--<th>Choose university factors</th>-->
                                <#--<th>favorite subject</th>-->
                                <#--<th>good subject</th>-->
                                <#--<th>Program major</th>-->
                                <#--<th>Planning work</th>-->
                                <#--<th>Graduation planning</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th><i class="fa fa-remove op0 add_icon"></i>-->

                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentPlans/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if studentPlans??>-->
                                <#--<#list studentPlans as plans>-->
                                <#--<tr>-->
                                    <#--<td>${(plans.targetCountries)!}</td>-->
                                    <#--<td>${(plans.collegeChoosing)!}</td>-->
                                    <#--<td>${(plans.subjectsLike)!}</td>-->
                                    <#--<td>${(plans.subjectsGood)!}</td>-->
                                    <#--<td>${(plans.majorsWant)!}</td>-->
                                    <#--<td>${(plans.workWant)!}</td>-->
                                    <#--<td>${(plans.ofterCollegeGraduation)!}</td>-->
                                    <#--<td>${(plans.creator)!}</td>-->
                                    <#--<td>${(plans.creationDate?string("yyyy-MM-dd"))!}</td>-->

                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->

                <!--Educational experience-->
                <div id="assigned" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTableEdu" class="table bor1 table-color table-hover mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>name</th>-->
                                <#--<th>School address</th>-->
                                <#--<th>start time</th>-->
                                <#--<th>End time</th>-->
                                <#--<th>achievement</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>-->
                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentEduExperience/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if exp??>-->
                                <#--<#list exp as exp>-->
                                <#--<tr>-->
                                    <#--<td>${(exp.schoolName)!}</td>-->
                                    <#--<td>${(exp.schoolAddress)!}</td>-->
                                    <#--<td>${(exp.startDateTime)!}</td>-->
                                    <#--<td>${(exp.endDateTime)!}</td>-->
                                    <#--<td>${(exp.academicRecord)!}</td>-->
                                    <#--<td>${(exp.creator)!}</td>-->
                                    <#--<td>${(exp.creationDate?string("yyyy-MM-dd"))!}</td>-->
                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->

                <!--Winning experience-->
                <div id="prize" class="tab-pane">
                    <div class="col-sm-12">
                        <table id="pagingTableAwards" class="table bor1 table-color table-hover mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>name</th>-->
                                <#--<th>Award Name</th>-->
                                <#--<th>Award level</th>-->
                                <#--<th>grade</th>-->
                                <#--<th>Award time</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>-->
                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentAwards/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->

                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if studentAwards??>-->
                                <#--<#list  studentAwards as awards>-->
                                <#--<tr>-->
                                    <#--<td>${(domain.caption)!}</td>-->
                                    <#--<td>${(awards.activityName)!}</td>-->
                                    <#--<td>${(awards.activityLevel)!}</td>-->
                                    <#--<td>${(awards.grade)!}</td>-->
                                    <#--<td>${(awards.dateTime)!}</td>-->
                                    <#--<td>${(awards.creator)!}</td>-->
                                    <#--<td>${(awards.creationDate?string("yyyy-MM-dd"))!}</td>-->
                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->
                <!--Sample reels-->
                <div id="sampleReels" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTableWorks" class="table bor1 table-color table-hover mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>name</th>-->
                                <#--<th>work Type</th>-->
                                <#--<th>grade</th>-->
                                <#--<th>Award time</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>-->
                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentWorksPortfolio/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if worksPortfolios??>-->
                                <#--<#list worksPortfolios as folios>-->
                                <#--<tr>-->
                                    <#--<td>${(domain.caption)!}</td>-->
                                    <#--<td>${(folios.map.typeWorkEntity.caption)!}</td>-->
                                    <#--<td>${(folios.map.gradeEntity.caption)!}</td>-->
                                    <#--<td>${(folios.date?string("yyyy-MM-dd"))!}</td>-->
                                    <#--<td>${(folios.creator)!}</td>-->
                                    <#--<td>${(folios.creationDate?string("yyyy-MM-dd"))!}</td>-->

                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->

                <!--special hobby-->
                <div id="hobby" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTableSpecialty" class="table bor1  table-color table-hover mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th align="left">special hobby</th>-->
                                <#--<th align="left">Duration study</th>-->
                                <#--<th align="left"></th>-->
                                <#--<th align="left">Founder</th>-->
                                <#--<th align="left">Creation time</th>-->
                                <#--<th align="left">Modifier</th>-->
                                <#--<th align="left">Modified</th>-->
                                <#--<th align="left">-->
                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentSpecialty/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if specialty??>-->
                                <#--<#list specialty as spe>-->
                                <#--<tr>-->
                                    <#--<td align="left">-->
                                        <#--<input name="spe.specialty" readonly value="${(spe.specialty)!}" type="text"-->
                                               <#--class="form-control bor0">-->
                                    <#--</td>-->
                                    <#--<td align="left">-->
                                        <#--<input name="spe.studyTime" readonly value="${(spe.studyTime)!}" type="text"-->
                                               <#--class="form-control bor0">-->
                                    <#--</td>-->
                                    <#--<td align="left">-->
                                        <#--<input name="spe.awardsAchievements" readonly-->
                                               <#--value="${(spe.awardsAchievements)!}"-->
                                               <#--type="text" class="form-control bor0">-->
                                    <#--</td>-->
                                    <#--<td align="left">-->
                                        <#--<input name="spe.creator" readonly value="${(spe.creator)!}" type="text"-->
                                               <#--class="form-control bor0">-->
                                    <#--</td>-->
                                    <#--<td align="left">-->
                                        <#--<input name="spe.creationDate" readonly-->
                                               <#--value="${(spe.creationDate?string("yyyy-MM-dd"))!}" type="text"-->
                                               <#--class="form-control bor0">-->
                                    <#--</td>-->
                                    <#--<td align="left">-->
                                        <#--<input name="spe.modifier" readonly value="${(spe.modifier)!}" type="text"-->
                                               <#--class="form-control bor0">-->
                                    <#--</td>-->
                                    <#--<td align="left">-->
                                        <#--<input name="spe.lasteditDate" readonly-->
                                               <#--value="${(spe.lasteditDate?string("yyyy-MM-dd"))!}" type="text"-->
                                               <#--class="form-control bor0">-->
                                    <#--</td>-->
                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->
                <!--Activity experience-->
                <div id="unresolved" class="tab-pane">
                    <div class="col-sm-12">
                        <table id="pagingTableActives" class="table bor1 table-color table-hover mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>name</th>-->
                                <#--<th>Activity name</th>-->
                                <#--<th>activity type</th>-->
                                <#--<th>Active address</th>-->
                                <#--<th>start time</th>-->
                                <#--<th>End time</th>-->
                                <#--<th>role</th>-->
                                <#--<th>achievement</th>-->
                                <#--<th>Cost</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>-->
                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentActivityExp/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if act??>-->
                                <#--<#list act as act>-->
                                <#--<tr>-->
                                    <#--<td>${(domain.caption)!}</td>-->
                                    <#--<td>${(act.activityName)!}</td>-->
                                    <#--<td>${(act.activityType)!}</td>-->
                                    <#--<td>${(act.activityType)!}</td>-->
                                    <#--<td>${(act.startDateTime)!}</td>-->
                                    <#--<td>${(act.endDateTime)!}</td>-->
                                    <#--<td>${(act.position)!}</td>-->
                                    <#--<td>${(act.score)!}</td>-->
                                    <#--<td>${(act.cost)!}</td>-->
                                    <#--<td>${(act.creator)!}</td>-->
                                    <#--<td>${(act.creationDate?string("yyyy-MM-dd"))!}</td>-->
                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                    </div>
                <#-- <div class="col-sm-12">
                     <h3>
                         Grade Awards
                     </h3>

                 </div>-->
                <#-- <div class="col-sm-12">
                     <h3>
                         Grade works
                     </h3>
                     <table class="table bor1 table-color table-hover mb30">
                         <thead>
                         <tr>
                             <th>name</th>
                             <th>work Type</th>
                             <th>grade</th>
                             <th>Award time</th>
                             <th>Founder</th>
                             <th>Creation time</th>
                         </tr>
                         </thead>
                         <tbody>
                         <#list worksPortfolios as folios>
                         <tr>
                             <td>${(domain.caption)!}</td>
                             <td>${(folios.typeWork)!}</td>
                             <td>${(folios.grade)!}</td>
                             <td>${(folios.dateTime)!}</td>
                             <td>${(folios.creator)!}</td>
                             <td>${(folios.creationDate?string("yyyy-MM-dd"))!}</td>

                         </tr>
                         </#list>

                         </tbody>
                     </table>
                 </div>-->

                </div><!-- tab-pane -->


                <!--Examination plan-->
                <div id="testplan" class="tab-pane">
                    <div class="col-sm-12">
                        <table class="table table-color bor-1 mb30">
                            <thead>
                            <tr>
                                <td>name</td>
                                <td>grade</td>
                                <td>Test time</td>
                                <td>examination Type</td>
                                <td>Whether training</td>
                                <td>Remarks</td>
                                <td>Founder</td>
                                <td>Creation time</td>
                                <td>
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentInterviewRecord/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <#-- <#list ></#list>-->
                            <tr>
                            <#-- <td>${(domain.caption)!}</td>
                                <td>${(domain.grade)!}</td>
                                <td>${(domain.dateTime)!}</td>
                                <td>${(domain.subjectType)!}</td>
                                <td>${(domain.istoeflorsat)!}</td>
                                <td>${(domain.notes)!}</td>
                                <td>${(domain.creator)!}</td>
                                <td>${(domain.creationDate)!}</td>-->

                            </tr>

                            </tbody>
                        </table>
                    <#--
                                            <table class="table table-color bor-1 mb30">
                                                <thead>
                                                <tr>
                                                    <th colspan="2">TOEFL/IETLS</th>
                                                    <th colspan="2">SAT/ACT</th>
                                                    <th colspan="2">AP/SAT2</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>Test time</td>
                                                    <td>Exam results</td>
                                                    <td>Test time</td>
                                                    <td>Exam results</td>
                                                    <td>Test time</td>
                                                    <td>Exam results</td>
                                                </tr>
                                                <tr>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82 hearing 18 Oral language 18 Read 20 writing 16</td>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82 hearing 18 Oral language 18 Read 20 writing 16</td>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82 hearing 18 Oral language 18 Read 20 writing 16</td>
                                                </tr>
                                                <tr>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82
                                                        hearing 18 Oral language 18 Read 20 writing 16
                                                    </td>
                                                    <td>无</td>
                                                    <td class="text-left">无</td>
                                                    <td>无</td>
                                                    <td class="text-left">无</td>
                                                </tr>
                                                <tr>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82
                                                        hearing 18 Oral language 18 Read 20 writing 16
                                                    </td>
                                                    <td>无</td>
                                                    <td class="text-left">无</td>
                                                    <td>无</td>
                                                    <td class="text-left">无</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">Do you take part in the schoolTOEFL或SATTrain</td>
                                                    <td colspan="4">否</td>
                                                </tr>
                                                </tbody>
                                            </table>
                    -->

                    </div>

                </div><!-- tab-pane -->
                <!--Interview records-->
                <div id="added" class="tab-pane">
                    <div  class="col-sm-12">

                        <table id="pagingTableRecords" class="table table-color bor-1 mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>name</th>-->
                                <#--<th>grade</th>-->
                                <#--<th>Interview time</th>-->
                                <#--<th>Interview content</th>-->
                                <#--<th>proposal</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>Remarks</th>-->
                                <#--<th><a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentInterviewRecord/create?pkStudent=${(student.pkStudent)!}<#if mentalitykey?? && mentalitykey==1>&mentalitykey=1</#if>">-->
                                    <#--<i class="fa fa-plus add_icon"></i>-->
                                <#--</a>-->
                                <#--</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if records??>-->
                                <#--<#list records as reco>-->
                                <#--<tr>-->
                                    <#--<td>${(domain.caption)!}</td>-->
                                    <#--<td>${(reco.grade)!}</td>-->
                                    <#--<td>${(reco.dateTime)!}</td>-->
                                    <#--<td>${(reco.what)!}</td>-->
                                    <#--<td>${(reco.advices)!}</td>-->
                                    <#--<td>${(reco.notes)!}</td>-->
                                    <#--<td>${(reco.creator)!}</td>-->
                                    <#--<td>${(reco.creationDate?string("yyyy-MM-dd"))!}</td>-->
                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->
                <!--Record of disciplinary action-->
                <div id="discipline" class="tab-pane">
                    <div class="col-sm-12">
                        <table id="pagingTableBehRecords" class="table table-color bor-1 mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>name</th>-->
                                <#--<th>grade</th>-->
                                <#--<th>time</th>-->
                                <#--<th>Event situation</th>-->
                                <#--<th>Punishment</th>-->
                                <#--<th>Remarks</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>-->
                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentBehaviorRecord/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if behRecords??>-->
                                <#--<#list behRecords as ber>-->
                                <#--<tr>-->
                                    <#--<td>${(domain.caption)!}</td>-->
                                    <#--<td>${(ber.grade)!}</td>-->
                                    <#--<td>${(ber.dateTime)!}</td>-->
                                    <#--<td>${(ber.details)!}</td>-->
                                    <#--<td>${(ber.disciplinaryAction)!}</td>-->
                                    <#--<td>${(ber.notes)!}</td>-->
                                    <#--<td>${(ber.creator)!}</td>-->
                                    <#--<td>${(ber.creationDate?string("yyyy-MM-dd"))!}</td>-->
                                <#--</tr>-->
                                <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>

                    </div>
                </div><!-- tab-pane -->
                <!--School report-->
                <div id="transcript" class="tab-pane">
                    <div class="col-sm-12">
                        <table id="pagingTableScores" class="table table-color bor-1 mb30">
                            <thead>
                            <#--<tr>-->
                                <#--<th>name</th>-->
                                <#--<th>Examination project</th>-->
                                <#--<th>Exam results</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>Remarks</th>-->
                                <#--<th>-->
                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentTestPlansScores/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->

                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#-- <#list testPlansScores as tes>
                            <tr>
                                <td>${(domain.caption)!}</td>
                                <td>${(tes.subjectName)!}</td>
                                <td>${(tes.scores)!}</td>
                                <td>${(tes.creator)!}</td>
                                <td>${(tes.creationDate)!}</td>
                                <td>${(tes.notes)!}</td>

                            </tr>
                            </#list>-->
                            </tbody>
                        </table>

                    </div>
                </div><!-- tab-pane -->


            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->

</form>

<#include "../commons/footer.ftl" >

<script type="text/javascript">



    <#--function sub() {-->

        <#--var pkStudent = $("#pkStudent").val();-->
        <#--var mediaInfo = $("#formId").serialize();-->

        <#--var  empkey = ${(empkey)!};-->
        <#--var url = "${(URL)!}";-->
        <#--$.ajax({-->
            <#--url: "/student/student/saveall",-->
            <#--data: mediaInfo,-->
            <#--type: "POST",-->
            <#--sync: false,-->
            <#--dataType: "json",-->
            <#--success: function (data) {-->
                <#--if (data.code == 0) {-->
                    <#--Notify.success(data.message);-->
                    <#--if(empkey ==1 || empkey == 2){-->
                        <#--window.location.href = "/student/studentSignup/edit?pkStudent=" + pkStudent;-->
                    <#--}-->
                   <#--else if(empkey == 3){-->
                       <#--window.location.href = "/student/student/edit?pkStudent=" + pkStudent;-->
                   <#--}-->
                <#--} else {-->
                    <#--Notify.danger(data.message);-->
                    <#--location.reload();-->
                <#--}-->
            <#--}-->
        <#--});-->
    <#--}-->


</script>

<script type="text/javascript">


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
    function addTr3(tab, row) {
        var trHtml = '<tr align="center"><td><input name="spe.specialty" type="text" class="form-control bor0 bg0" placeholder="special hobby"></td>' +
                '<td><input name="spe.studyTime" type="text"  class="form-control bor0 bg0" placeholder="Duration study"></td>' +
                '<td><input name="spe.awardsAchievements" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="spe.creator" type="text"  class="form-control bor0 bg0" placeholder="Founder"></td>' +
                '<td><input name="spe.creationDate" type="text"  class="form-control bor0 bg0" placeholder="Creation time"></td>' +
                '<td><input name="spe.modifier" type="text"  class="form-control bor0 bg0" placeholder="Modifier"></td>' +
                '<td><input name="spe.lasteditDate" type="text"  class="form-control bor0 bg0" placeholder="Modified"></td>' +
                '<td ><i  onclick="deleteRow(tab,this.parentElement.parentElement.rowIndex)" class="fa fa-remove add_icon"></i>' +
                '</td></tr>';
        addTr(tab, row, trHtml);
    }
    <#if linkMans??>
    var parentNumber =${linkMans?size};
    <#else >
    var parentNumber = 0;
    </#if>


    function addTr2(tab, row) {
        var trHtml = '<tr align="center"><td><input name="man[' + parentNumber + '].caption" type="text" class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + parentNumber + '].relationship" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><select name="man[' + parentNumber + '].sex"><option value="">Please choose</option><option value="1" >男</option><option value="2" >女</option></select></td>' +
                '<td><input name="man[' + parentNumber + '].occupation" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + parentNumber + '].duty" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + parentNumber + '].workUnit" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td>' +
                '<input name="man[' + parentNumber + '].degreeCompleted" type="text"  class="form-control bor0 bg0" placeholder="">' +
                '</td>' +
                '<td><input name="man[' + parentNumber + '].phone" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + parentNumber + '].address" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td ><i  onclick="deleteRow(tab,this.parentElement.parentElement.rowIndex)" class="fa fa-remove add_icon"></i>' +
                '</td></tr>';
        addTr(tab, row, trHtml);
        parentNumber++;
    }

    //Delete a line
    function deleteRow(tableID, rowIndex) {
        //var table = document.getElementById(tableID);
        tableID.deleteRow(rowIndex);

        //newRowIndex--;//Maintaining global variables
    }

    function deleteRow1(tableID, rowIndex) {
        //var table = document.getElementById(tableID);
        tableID.deleteRow(rowIndex);

        // newRowIndex1--;//Maintaining global variables
    }

</script>

<script type="text/javascript">

    function clear(str) {
        str = str.replace(/,/g, "");//Cancel all commas that appear in the string
        return str;
    }


    function setImagePreviews() {
        var docObj = document.getElementById("doc");
        var dd = document.getElementById("dd");
        dd.innerHTML = "";
        var fileList = docObj.files;
        for (var i = 0; i < fileList.length; i++) {

            dd.innerHTML += "<div> <img id='img" + i + "'  /> </div>";
            var imgObjPreview = document.getElementById("img" + i);
            if (docObj.files && docObj.files[i]) {
                //Under Firefox，Direct settingimgattribute
                imgObjPreview.style.display = 'block';
                imgObjPreview.style.width = '112px';
                imgObjPreview.style.height = '152px';
                //imgObjPreview.src = docObj.files[0].getAsDataURL();
                //Firefox7The above version can't be used on the abovegetAsDataURL()Mode acquisition，Need a way
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
            }
            else {
                //IE下，Use filter
                docObj.select();
                var imgSrc = document.selection.createRange().text;
                alert(imgSrc)
                var localImagId = document.getElementById("img" + i);
                //Initial size must be set
                localImagId.style.width = "112px";
                localImagId.style.height = "152px";
                //Capture of image anomalies，Prevent users to modify suffixes to forge pictures
                try {
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                }
                catch (e) {
                    alert("The format of the picture you uploaded is not correct，Please rechoose!");
                    return false;
                }
                imgObjPreview.style.display = 'none';
                document.selection.empty();
            }
        }

        return true;
    }



    function ajaxSubmitForm(){
        var requestData = {};
        var value = $("#file").val();
        requestData.file = value;
        var id_str = "doc";
        $.ajaxFileUpload({
            url : '/file/upload',
            type : 'post',
            secureuri : false,
            data : requestData,
            fileElementId : id_str,  //file             //Common data
            dataType : 'json',
            success : function(result) {
                // hideLoading();
                if(result.code == 0){
                    setImagePreviews();
                    $("#studentImg").val(result.data);
//                    alert(result.data);
//                    Notify.success("Upload success");
                }
            }
        });
    }

//    makePy
    function getmakePy() {
        var str = document.getElementById("txtChinese").value.trim();
        if(str == "") return;
        var arrRslt = makePy(str);
//        alert(arrRslt);
        $("#shortCodeId").val(arrRslt);

    }

    function selectEmp(pkId,caption,id1,id2) {
        $("#modal").modal("hide");
        $("#empcapid").val(caption);
        $("#pkempid").val(pkId);
    }



    $(document).ready(function () {
//        query();
        queryInterview();
        queryPlans();
        queryEdu();
        queryAwards();
        queryWorks();
        querySpecialty();
        queryActives();
        queryRecords();
        queryBehRecords();
        queryScores();
    });
    function renderCaption(data, type, row) {

    }
    function queryParent() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("linkMan"), "/student/studentSignup/queryByPaging", data,"pagingTableParent");
    }
    function queryInterview() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("interviewModel"), "/student/studentInterview/queryByPaging", data,"pagingTableInterview");
    }
    //Student plan
    function queryPlans() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentPlans"), "/student/studentPlans/queryByPaging", data,"pagingTablePlans");
    }
    //Educational experience
    function queryEdu() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("eduExperience"), "/student/studentEduExperience/queryByPaging", data,"pagingTableEdu");
    }
    //reward
    function queryAwards() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentAwards"), "/student/studentAwards/queryByPaging", data,"pagingTableAwards");
    }
    //Sample reels
    function queryWorks() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentWorks"), "/student/studentWorksPortfolio/queryByPaging", data,"pagingTableWorks");
    }
    //Speciality
    function querySpecialty() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentSpecialty"), "/student/studentSpecialty/queryByPaging", data,"pagingTableSpecialty");
    }
    //Activity experience
    function queryActives() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentActivesExp"), "/student/studentActivityExp/queryByPaging", data,"pagingTableActives");
    }
    //Interview records
    function queryRecords() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentRecords"), "/student/studentInterviewRecord/queryByPaging", data,"pagingTableRecords");
    }
    //Disciplinary record
    function queryBehRecords() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentBehRecords"), "/student/studentBehaviorRecord/queryByPaging", data,"pagingTableBehRecords");
    }
    //School report
    function queryScores() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentScores"), "/classinfo/studentScoress/queryByPaging", data,"pagingTableScores");
    }

    function renderAdd(data, type, row) {
        return  "";
    }

    function add(obj) {
        var url = $("#"+obj).attr("data-url");
        $("#"+obj).attr("data-url",url + "?pkStudent=${(student.pkStudent)!}");
        return true;
    }



    function renderScores(data, type, row){

       if (data ==1){
            return '<span class="level"><i class="level_solid"></i></span>';

       }else if(data ==2){
           return '<span class="level"><i class="level_solid"></i><i class="level_solid"></i></span>';

       }else if(data ==3){
           return '<span class="level"><i class="level_solid"><i class="level_solid"></i><i class="level_solid"></i></i></span>';
       }
       else if(data ==4){
           return '<span class="level"><i class="level_solid"></i><i class="level_solid"></i><i class="level_solid"></i><i class="level_solid"></i></span>';

       }else if(data ==5) {
           return '<span class="level"><i class="level_solid"></i><i class="level_solid"></i><i class="level_solid"></i><i class="level_solid"></i><i class="level_solid"></i></span>';
       } else {
            return 'No score';
       }
    }


    $("#idCradId").blur(function () {
        var kind = $("#idCradId").val();
        if(kind !=""){
            var falg =  CheckIdCard(kind);
            if(falg =="true"){
                var birthday = GetBirthday(kind);
                $("#data").val(birthday);
            }
        }
    });

</script>