<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<form class="look form-ajax" method="POST" id="formId" action="/student/student/saveall"
      data-target="${(URL)!}?pkStudent=${(student.pkStudent)!}"  >
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>学生档案 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
            <button class="btn btn-sm btn-newblue" type="submit">保存</button>
        <#--<#if student.istype?? && student.istype ==1>-->
            <#--<#if  mentalitykey ?? && mentalitykey ==1>-->
            <#--<#else >-->
                <#--<a class="btn btn-sm btn-newblue" data-toggle="modal"  data-target="#modal" data-url="/student/student/refund?pkStudent=${(student.pkStudent)!}">退费 </a>-->
            <#--</#if>-->

        <#--</#if>-->
        <#if student.istype?? && student.istype ==0 >
            <a class="btn btn-sm btn-newblue" href="/student/studentInterview/create?pkStudent=${(student.pkStudent)!}">面试</a><!-- 面试 -->
            <a class="btn btn-sm btn-newblue"
               href="/student/studentReport/create?pkStudent=${(student.pkStudent)!}">报名</a><!-- 报名 -->
        </#if>
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">

                <li class="active"><a data-toggle="tab" href="#all"><strong>学生信息</strong></a></li>
            <#--<#if interviewkey?? && interviewkey==2>-->

            <#--</#if>-->

            <#if student.istype?? && student.istype ==1 >

            <#if  mentalitykey ?? && mentalitykey ==1>
                <li class=""><a data-toggle="tab" href="#added"><strong>咨询记录</strong></a></li>

                <#else>
                    <li><a data-toggle="tab" href="#hobby"><strong>特长爱好</strong></a></li>
                    <li><a data-toggle="tab" href="#plans"><strong>学生计划</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#added">访谈记录</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#discipline"><strong>违纪记录</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#assigned"><strong>教育经历 </strong></a></li>
                    <li class=""><a data-toggle="tab" href="#unresolved"><strong> 活动经历 </strong></a></li>
                    <li class=""><a data-toggle="tab" href="#sampleReels"><strong> 作品集 </strong></a></li>
                    <li class=""><a data-toggle="tab" href="#prize"><strong> 获奖经历</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#transcript"><strong>成绩单</strong></a></li>
                    <#--<li class=""><a data-toggle="tab" href="#additional"><strong>附加信息</strong></a></li>-->
            </#if>
            <#else >
                <li class=""><a data-toggle="tab" href="#interview"><strong>面试信息</strong></a></li>
            </#if>
            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">


                    <#--<h3>学生信息</h3>-->
                        <input id="pkStudent" name="stu.pkStudent" value="${(student.pkStudent)!}" type="hidden">
                        <input name="stu.creator" value="${(student.creator)!}" type="hidden">
                        <div class="row">

                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;margin-bottom: 15px;">基本信息</h3>
                            </div>

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">校区</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" readonly value="${(domain.caption)!}"
                                               type="text">
                                        <input name="stu.pkDomain" value="${(domain.pkDomain)!}" type="hidden">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">编号</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" readonly name="stu.code" value="${(student.code)!}"
                                               type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">别名</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" name="stu.shortCaption"
                                               value="${(student.shortCaption)!}"
                                               type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学号</label>
                                    <div class="col-xs-9">
                                        <input id="studentId" name="stu.studentId" value="${(student.studentId)!}" type="number" maxlength="10" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">班级</label>
                                    <div class="col-xs-9">
                                        <select name="stu.pkClassinfo" onchange="classinfoOnchange(this.value)">
                                            <#if classinfoList??>
                                                <option value="">请选择班级</option>
                                                <#list classinfoList as c>
                                                    <option value="${(c.id)}" <#if student?? &&c.id?? && student.pkClassinfo?? && student.pkClassinfo==c.id>selected</#if>>${(c.caption)}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">分类</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.studentClass">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3"><i class="red">*</i>姓名</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="txtChinese" name="stu.caption" onblur="getmakePy()" value="${(student.caption)!}" type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>性别</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.sex" value="1" class="deldis"
                                                  <#if student.sex??&& student.sex ==1 >checked="checked"</#if>
                                                  id="radio11" type="radio">
                                           <label for="radio11">男</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.sex" value="2" id="radio12" class="deldis"
                                                  <#if student.sex??&& student.sex ==2 >checked="checked"</#if>
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
                                    <label class="control-label col-xs-3"><i class="red">*</i>助记码</label>
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
                                    <p class="gray">上传头像</p>
                                    <input type="hidden" name="stu.img" id="studentImg" value="${(student.img)!}">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>出生日期</label>
                                    <div class="col-xs-9">
                                        <input name="stu.birthdayTime"
                                               value="${(student.birthday?string("yyyy-MM-dd"))!}" id="data" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">学生类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.istype">
                                            <option value=0
                                                    <#if student.istype?? && student.istype==0>selected="selected"</#if>>
                                                意向学生
                                            </option>
                                            <option value=1
                                                    <#if student.istype?? && student.istype==1>selected="selected"</#if>>
                                                正式学生
                                            </option>
                                            <option value=2
                                                    <#if student.istype?? && student.istype==2>selected="selected"</#if>>
                                                无效学生
                                            </option>
                                            <#if empkey ?? && empkey==3>
                                                <option value=5
                                                        <#if student.istype?? && student.istype==5>selected="selected"</#if>>
                                                    毕业
                                                </option>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        <#if student.istype?? && student.istype ==1 >
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">学生状态</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.state">
                                            <option value=-1
                                                    <#if student.state?? && student.state==-1>selected="selected"</#if>>

                                            </option>
                                            <option value=0
                                                    <#if student.state?? && student.state==0>selected="selected"</#if>>
                                                转出
                                            </option>
                                            <option value=1
                                                    <#if student.state?? && student.state==1>selected="selected"</#if>>
                                                在校
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
                                    <label class="control-label col-xs-3">个人电话</label>
                                    <div class="col-xs-9">
                                        <input name="stu.phone" value="${(student.phone)!}"  type="text" class="form-control"
                                               data-bv-phone="true" data-bv-phone-country="ZHCN" data-bv-phone-message="请输入正确手机号" >
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>居住地址</label>
                                    <div class="col-xs-9">
                                        <input name="stu.address" value="${(student.address)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>年级</label>
                                    <div class="col-xs-9">
                                        <select name="stu.grade">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3"><i class="red">*</i>证件类型</label>
                                    <div class="col-xs-9">

                                    <#if idkind??>
                                        <select name="stu.idkind" id="idkindId">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3">证件地址</label>
                                    <div class="col-xs-9">
                                        <input name="stu.idAddress" value="${(student.idAddress)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>证件编号</label>
                                    <div class="col-xs-9">
                                        <input name="stu.idCard" value="${(student.idCard)!}" id="idCradId" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">证件到期</label>
                                    <div class="col-xs-9">
                                        <input name="stu.passportDateTime" value="${(student.passportDate?string("yyyy-MM-dd"))!}" type="text" class="form-control js-datepicker">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>

                        <div class="row">
                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;margin-bottom: 15px;"> 报名信息</h3>
                            </div>

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">线索来源</label>
                                    <div class="col-xs-9">
                                        <select name="stu.tracksource">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3">所属人</label>
                                    <div class="col-xs-9">
                                        <input readonly  id="empcapid" value="${(employee.caption)!}" class="form-control" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=2">
                                        <input readonly name="stu.pkSysUser" id="pkempid" value="${(employee.pkEmployee)!}" type="hidden">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">项目</label>
                                    <div class="col-xs-9">
                                        <select name="stu.program">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3">有无学籍</label>
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
                                    <label class="control-label col-xs-3">学籍</label>
                                    <div class="col-xs-9">
                                        <input name="stu.schoolrool" value="${(student.schoolrool?c)!}" type="number" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学籍地址</label>
                                    <div class="col-xs-9"><input name="stu.schoolroolAddress"  value="${(student.schoolroolAddress)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">心里健康</label>
                                    <div class="col-xs-9">
                                        <select name="stu.mentalHealth">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3"><i class="red">*</i>是否住宿</label>
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
                                    <label class="control-label col-xs-3">乘坐校车</label>
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
                                    <label class="control-label col-xs-3">留学费用</label>
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
                                    <label class="control-label col-xs-3">原就读学校</label>
                                    <div class="col-xs-9">
                                        <input name="stu.oldSclool" value="${(student.oldSclool)!}" type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学生梦想</label>
                                    <div class="col-xs-9">
                                        <input name="stu.dream" value="${(student.dream)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">入学情况</label>
                                    <div class="col-xs-9">
                                        <input name="stu.inState" value="${(student.inState)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">现状分析</label>
                                    <div class="col-xs-9"><input name="stu.situationAnalysis" value="${(student.situationAnalysis)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">毕业去向</label>
                                    <div class="col-xs-9">
                                        <input name="stu.graduationGo" value="${(student.graduationGo)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>


                        <div class="row">

                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;margin-bottom: 15px;"> 其他信息</h3>
                            </div>

                        </div>


                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">国籍</label>
                                    <div class="col-xs-9">
                                        <select name="stu.nationality">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3">母语</label>
                                    <div class="col-xs-9">
                                        <select name="stu.motherLan">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3">原籍</label>
                                    <div class="col-xs-9">
                                        <input name="stu.oldPlace" value="${(student.oldPlace)!}" type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">身体健康</label>
                                    <div class="col-xs-9">
                                        <select name="stu.healthyBody">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3">有无绿卡</label>
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
                                    <label class="control-label col-xs-3">绿卡国家</label>
                                    <div class="col-xs-9">
                                        <select name="stu.greenCardCountry">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3">邮编</label>
                                    <div class="col-xs-9">
                                        <input name="stu.zip" value="${(student.zip)!}" type="text" class="form-control" maxlength="6">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">邮箱</label>
                                    <div class="col-xs-9">
                                        <input name="stu.email" value="${(student.email)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">家长反馈</label>
                                    <div class="col-xs-9">
                                        <input name="stu.parentFeedback" value="${(student.parentFeedback)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>


                        <div class="row">


                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">少数民族</label>
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
                                    <label class="control-label col-xs-3">民族</label>
                                    <div class="col-xs-9">
                                        <select name="stu.nation">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3">家长期许</label>
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
                                    <label class="control-label col-xs-3">宗教信仰</label>
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
                                    <label class="control-label col-xs-3">宗教名称</label>
                                    <div class="col-xs-9">
                                        <input name="stu.religion" value="${(student.religion)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->


                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-1">老师评价</label>
                                    <div class="col-xs-9 col-md-11">
                                        <input name="stu.teacherAppraisal" value="${(student.teacherAppraisal)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->


                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1 col-md-1">备注</label>
                                    <div class="col-xs-11 col-md-11">
                                    <#--<input name="stu.memo" value="${(student.memo)!}" type="text" class="form-control">-->
                                        <textarea class="form-control" name="stu.memo" rows="3" va>${(student.memo)!}</textarea>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div><!-- col-sm-4 -->

                        <div class="col-sm-12">
                            <div style="float: left">
                                <h3>家庭信息</h3>
                            </div>

                            <div style="float: right;margin-top: 35px;margin-bottom: 15px;">
                                <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal" data-url="/student/linkman/findLinkman">关联已有家长</a>
                            </div>

                        </div>
                        <div class="">
                            <table id="tab" class="table bor1 table-color  mb30">
                                <thead>
                                <tr>
                                    <td>姓名</td>
                                    <td>关系</td>
                                    <td width="80px">性别</td>
                                    <td width="80px">关键家长</td>
                                    <td>电话</td>
                                    <td>职业</td>
                                    <td>职务</td>
                                    <td>工作单位</td>
                                    <td>学历</td>

                                    <td>地址</td>
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
                                            <input name="man[${index}].caption"  value="${(man.caption)!}"
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
                                                   value="${(man.relationship)!}"
                                                   type="text" class="form-control bor0">
                                        </td>
                                        <td>
                                        <#-- <input name="man.sex" readonly value="${(man.sex)!}" type="text" class="form-control bor0">-->
                                            <select name="man[${index}].sex" class="form-control bor0">
                                                <option value="1"
                                                        <#if man.sex?? && man.sex==1>selected="selected"</#if>>男
                                                </option>
                                                <option value="2"
                                                        <#if man.sex?? && man.sex==2>selected="selected"</#if>>女
                                                </option>
                                            </select>
                                        </td>
                                        <td>
                                        <#-- <input name="man.sex" readonly value="${(man.sex)!}" type="text" class="form-control bor0">-->
                                            <select name="man[${index}].iskey" class="form-control bor0">
                                                <option value="0"
                                                        <#if man.iskey?? && man.iskey==0>selected="selected"</#if>>否
                                                </option>
                                                <option value="1"
                                                        <#if man.iskey?? && man.iskey==1>selected="selected"</#if>>是
                                                </option>
                                            </select>
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].phone"  value="${(man.phone)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].occupation"
                                                   value="${(man.occupation)!}"
                                                   type="text" class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].duty"  value="${(man.duty)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].workUnit"  value="${(man.workUnit)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].degreeCompleted"
                                                   value="${(man.degreeCompleted)!}" type="text"
                                                   class="form-control bor0">
                                        </td>

                                        <td align="left">
                                            <input name="man[${index}].address"  value="${(man.address)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                    </tr>
                                        <#assign index = index+1> <!--list循环一次，变量+1-->

                                    </#list>
                                </#if>

                                </tbody>
                            </table>
                        </div>



                </div><!-- tab-content -->

                <!--面试信息-->
            <#--<#if interviewkey?? && interviewkey==2 >-->
                <div id="interview" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTableInterview" class="table bor1 table-color table-hover mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            <#--</#if>-->

                <!--学生计划-->
                <div id="plans" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTablePlans" class="table bor1 table-color table-hover mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->

                <!--教育经历-->
                <div id="assigned" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTableEdu" class="table bor1 table-color table-hover mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->

                <!--获奖经历-->
                <div id="prize" class="tab-pane">
                    <div class="col-sm-12">
                        <table id="pagingTableAwards" class="table bor1 table-color table-hover mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->
                <!--作品集-->
                <div id="sampleReels" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTableWorks" class="table bor1 table-color table-hover mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->

                <!--特长爱好-->
                <div id="hobby" class="tab-pane">
                    <div class="col-sm-12">

                        <table id="pagingTableSpecialty" class="table bor1  table-color table-hover mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->
                <!--活动经历-->
                <div id="unresolved" class="tab-pane">
                    <div class="col-sm-12">
                        <table id="pagingTableActives" class="table bor1 table-color table-hover mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>


                </div><!-- tab-pane -->


                <!--考试计划-->
                <div id="testplan" class="tab-pane">
                    <div class="col-sm-12">
                        <table class="table table-color bor-1 mb30">
                            <thead>
                            <tr>
                                <td>学校名称</td>
                                <td>年级</td>
                                <td>考试时间</td>
                                <td>考试类型</td>
                                <td>有无参与培训</td>
                                <td>备注</td>
                                <td>创建人</td>
                                <td>创建时间</td>
                                <td>
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentInterviewRecord/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>

                            </tr>

                            </tbody>
                        </table>


                    </div>

                </div><!-- tab-pane -->
                <!--访谈记录-->
                <div id="added" class="tab-pane">
                    <div  class="col-sm-12">

                        <table id="pagingTableRecords" class="table table-color bor-1 mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->
                <!--纪律行为记录-->
                <div id="discipline" class="tab-pane">
                    <div class="col-sm-12">
                        <table id="pagingTableBehRecords" class="table table-color bor-1 mb30">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                    </div>
                </div><!-- tab-pane -->
                <!--成绩单-->
                <div id="transcript" class="tab-pane">
                    <div class="col-sm-12">
                        <table id="pagingTableScores" class="table table-color bor-1 mb30">
                            <thead>

                            </thead>
                            <tbody>

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


    //被插入的行索引
    function addTr(tab, row, trHtml) {
        //获取table最后一行 $("#tab tr:last")
        //获取table第一行 $("#tab tr").eq(0)
        //获取table倒数第二行 $("#tab tr").eq(-2)
        var $tr = $("#" + tab + " tr").eq(row);
        if ($tr.size() == 0) {
            alert("指定的table id或行数不存在！");
            return;
        }
        $tr.after(trHtml);
    }


    //插入一行
    function addTr3(tab, row) {
        var trHtml = '<tr align="center"><td><input name="spe.specialty" type="text" class="form-control bor0 bg0" placeholder="特长爱好"></td>' +
                '<td><input name="spe.studyTime" type="text"  class="form-control bor0 bg0" placeholder="学习时长"></td>' +
                '<td><input name="spe.awardsAchievements" type="text"  class="form-control bor0 bg0" placeholder="奖项&成就"></td>' +
                '<td><input name="spe.creator" type="text"  class="form-control bor0 bg0" placeholder="创建人"></td>' +
                '<td><input name="spe.creationDate" type="text"  class="form-control bor0 bg0" placeholder="创建时间"></td>' +
                '<td><input name="spe.modifier" type="text"  class="form-control bor0 bg0" placeholder="修改人"></td>' +
                '<td><input name="spe.lasteditDate" type="text"  class="form-control bor0 bg0" placeholder="修改时间"></td>' +
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
                '<td><select name="man[' + parentNumber + '].sex"><option value="">请选择</option><option value="1" >男</option><option value="2" >女</option></select></td>' +
                '<td><select name="man[' + parentNumber + '].iskey"><option value="">请选择</option><option value="0" >否</option><option value="1" >是</option></select></td>' +
                '<td><input name="man[' + parentNumber + '].phone" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + parentNumber + '].occupation" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + parentNumber + '].duty" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + parentNumber + '].workUnit" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td>' +
                '<input name="man[' + parentNumber + '].degreeCompleted" type="text"  class="form-control bor0 bg0" placeholder="">' +
                '</td>' +

                '<td><input name="man[' + parentNumber + '].address" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td ><i  onclick="deleteRow(tab,this.parentElement.parentElement.rowIndex)" class="fa fa-remove add_icon"></i>' +
                '</td></tr>';
        addTr(tab, row, trHtml);
        parentNumber++;
    }

    //删除一行
    function deleteRow(tableID, rowIndex) {
        //var table = document.getElementById(tableID);
        tableID.deleteRow(rowIndex);

        //newRowIndex--;//维护全局变量
    }

    function deleteRow1(tableID, rowIndex) {
        //var table = document.getElementById(tableID);
        tableID.deleteRow(rowIndex);

        // newRowIndex1--;//维护全局变量
    }

</script>

<script type="text/javascript">

    function clear(str) {
        str = str.replace(/,/g, "");//取消字符串中出现的所有逗号
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
                //火狐下，直接设img属性
                imgObjPreview.style.display = 'block';
                imgObjPreview.style.width = '112px';
                imgObjPreview.style.height = '152px';
                //imgObjPreview.src = docObj.files[0].getAsDataURL();
                //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
            }
            else {
                //IE下，使用滤镜
                docObj.select();
                var imgSrc = document.selection.createRange().text;
                alert(imgSrc)
                var localImagId = document.getElementById("img" + i);
                //必须设置初始大小
                localImagId.style.width = "112px";
                localImagId.style.height = "152px";
                //图片异常的捕捉，防止用户修改后缀来伪造图片
                try {
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                }
                catch (e) {
                    alert("您上传的图片格式不正确，请重新选择!");
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
            fileElementId : id_str,  //文件             //普通数据
            dataType : 'json',
            success : function(result) {
                // hideLoading();
                if(result.code == 0){
                    setImagePreviews();
                    $("#studentImg").val(result.data);
//                    alert(result.data);
//                    Notify.success("上传成功");
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
    //学生计划
    function queryPlans() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentPlans"), "/student/studentPlans/queryByPaging", data,"pagingTablePlans");
    }
    //教育经历
    function queryEdu() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("eduExperience"), "/student/studentEduExperience/queryByPaging", data,"pagingTableEdu");
    }
    //奖励
    function queryAwards() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentAwards"), "/student/studentAwards/queryByPaging", data,"pagingTableAwards");
    }
    //作品集
    function queryWorks() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentWorks"), "/student/studentWorksPortfolio/queryByPaging", data,"pagingTableWorks");
    }
    //特长
    function querySpecialty() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentSpecialty"), "/student/studentSpecialty/queryByPaging", data,"pagingTableSpecialty");
    }
    //活动经历
    function queryActives() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentActivesExp"), "/student/studentActivityExp/queryByPaging", data,"pagingTableActives");
    }
    //访谈记录
    function queryRecords() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentRecords"), "/student/studentInterviewRecord/queryByPaging", data,"pagingTableRecords");
    }
    //违纪记录
    function queryBehRecords() {
        var data = {"pkStudent":$("#pkStudent").val()};
        init(GetTableColumn("studentBehRecords"), "/student/studentBehaviorRecord/queryByPaging", data,"pagingTableBehRecords");
    }
    //成绩单
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
            return '暂无评分';
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

    function classinfoOnchange(obj) {
//        studentId
        if (obj != null && obj != ""){
            $.ajax({
               url:"/student/studentSignup/getStudentId",
               type:"POST",
               data:{"pkClassinfo":obj},
               dataType:"json",
                success:function (data) {
                    if (data.code ==0){
                        $("#studentId").val(data.data);
                    }
                }
            });
        }
    }

</script>