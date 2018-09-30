Ser<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />


<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i><#if classinfo.caption?? >${(classinfo.caption)!}<#else >新建班级</#if> <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                    <a class="btn btn-sm btn-newblue" onclick="sub()">保存 </a>
                    <a class="btn btn-sm  btn-newblue" onclick="subissubmit()">提交</a>
                    <a class="btn btn-sm  btn-newblue" onclick="subisaudit()">审核</a>
                <#if classinfo.pkClassinfo??>
                    <#--<a class="btn btn-sm  btn-newblue" id="classon"> 班级升班</a>-->
                    <a class="btn btn-sm  btn-newblue" id="stuinclass" data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/studentreturn"> 学生转班</a>
                </#if>
                <a class="btn btn-sm  btn-newblue" href="/classinfo/classinfo/stureport?pkClassinfo=${(classinfo.pkClassinfo)!}"> 学生报名</a>
                <#--<a class="btn btn-sm  btn-newblue" href="/finance/payables/returnpay"> 退费</a>-->
                <#--<a class="btn btn-sm  btn-newblue activityStudent" data-types="1"> 实践评分</a>-->
                <#--<a class="btn btn-sm  btn-newblue activityStudent" data-types="2"> 老师评语</a>-->
                <a class="btn btn-sm btn-newblue activityStudent" data-toggle="modal"  data-target="#modal" data-types="1" data-url="/classinfo/classinfo/studentScore" >实践评分</a>
                <a class="btn btn-sm btn-newblue activityStudent" data-toggle="modal"  data-target="#modal" data-types="2" data-url="/classinfo/classinfo/studentEvaluate" >老师评语</a>
                <a class="btn btn-sm btn-newblue activityStudent" data-toggle="modal" style="display: none"  data-target="#modal" data-url="/classinfo/classinfo/studentEvaluate" >老师评语</a>
                <a class="btn btn-sm btn-newblue " href="/classinfo/classActivity/create?pkClassinfo=${(classinfo.pkClassinfo)!}" >添加班级活动</a>
                <a class="btn btn-sm btn-newblue " href="/classinfo/raceRecord/create?pkClassinfo=${(classinfo.pkClassinfo)!}" >添加竞赛活动</a>

            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-yellow">
                <li class="active"><a data-toggle="tab" href="#all"><strong>班级信息</strong></a></li>
                <#if classinfo.pkClassinfo??>
                    <li class=""><a data-toggle="tab" href="#assigned"><strong>学生信息</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#scores"><strong>学生成绩</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#teacher"><strong>老师信息</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#linkMan"><strong>账号信息</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#activity"><strong>班级活动</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#racerecord"><strong>竞赛记录</strong></a></li>
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
                                    <label class="control-label col-xs-3 col-md-3">班级编号</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input title="" readonly name="clas.code" value="${(classinfo.code)!}" class="form-control" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">班级名称</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.caption" id="classCaption" value="${(classinfo.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">开班日期</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.dateTime" value="${(classinfo.date?string("yyyy-MM-dd"))!}" id="data" type="text" title="" class="form-control " value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">班主任</label>
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
                                    <label class="control-label col-xs-3 col-md-3">副班主任</label>
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
                                    <label class="control-label col-xs-3 col-md-3">学部主任</label>
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
                                    <label class="control-label col-xs-3 col-md-3">开始时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.startDateTime" value="${(classinfo.startDate?string("yyyy-MM-dd"))!}" type="text" title="" class="form-control js-datepicker" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">结束时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.endDateTime" value="${(classinfo.endDate?string("yyyy-MM-dd"))!}" type="text" title="" class="form-control js-datepicker" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">教室</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.classRoom">
                                            <option value="">请选择教室</option>
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
                                    <label class="control-label col-xs-3 col-md-3">学部</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.division" onchange="getGrade(this.value)">
                                            <option value="">请选择</option>
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
                                    <label class="control-label col-xs-3 col-md-3">年级</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.grade" id="selectgtadeId">

                                        <#if grade ??>
                                            <option value="">请选择</option>
                                            <#list grade as g>
                                                <option value="${(g.pkDivisionGrade)!}" <#if g.pkDivisionGrade?? && classinfo.grade?? && (classinfo.grade==g.pkDivisionGrade?string)>selected="selected"</#if> >${(g.gradeName)!}</option>
                                            </#list>
                                        </#if>
                                        </select>
                                    <#--<input class="form-control" name="clas.grade" value="${(classinfo.grade)!}" title="" type="text">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">周课时</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.courseTime" value="${(classinfo.courseTime)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">项目</label>
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
                                    <label class="control-label col-xs-3 col-md-3">班级类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.type">
                                            <option value="0" <#if classinfo.type?? && classinfo.type==0>selected="selected"</#if>>正式班级</option>
                                            <option value="1" <#if classinfo.type?? && classinfo.type==1>selected="selected"</#if>>实践活动班</option>
                                            <option value="2" <#if classinfo.type?? && classinfo.type==2>selected="selected"</#if>>社团</option>
                                            <option value="3" <#if classinfo.type?? && classinfo.type==3>selected="selected"</#if>>选修课</option>
                                            <option value="4" <#if classinfo.type?? && classinfo.type==4>selected="selected"</#if>>兴趣班</option>
                                            <option value="5" <#if classinfo.type?? && classinfo.type==5>selected="selected"</#if>>已毕业班级</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">容纳人数</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.num" value="${(classinfo.num)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label  col-xs-1 col-md-1">备注</label>
                                    <div class="col-xs-11 col-md-11">
                                        <textarea rows="2" name="clas.notes" value="${(classinfo.notes)!}" class="form-control" title="">${(classinfo.notes)!}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">提交人</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly  class="form-control" value="${(classinfo.map.submitorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">提交时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly type="text" title="" value="${(classinfo.submitDateTime)!}" class="form-control" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">是否提交</label>

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
                                    <label class="control-label col-xs-3 col-md-3">审核人</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(classinfo.map.auditorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">审核时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(classinfo.auditDateTime)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">是否审核</label>

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
                                    <label class="control-label col-xs-3 col-md-3">创建人</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="clas.creator" class="form-control" value="${(classinfo.map.creatorEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">创建时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(classinfo.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">修改人</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="clas.modifier" class="form-control" value="${(classinfo.map.modifierEntity.caption)!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">修改时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control" value="${(classinfo.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}" title="" type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="assigned" class="tab-pane">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <label class="control-label col-xs-3 col-md-3">学期</label>
                                <div class="col-xs-9 col-md-9">
                                    <select id="termId">
                                        <option value="">请选择学期</option>
                                        <#if terms?? >
                                            <#list terms as t>
                                                <option value="${(t.pkSysDictValues)!}">${(t.caption)!}</option>
                                            </#list>
                                        </#if>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <label class="control-label col-xs-3 col-md-3">年份</label>
                                <div class="col-xs-9 col-md-9">
                                   <input id="schoolYear" class="form-control js-datepickerYYYY" >
                                </div>
                            </div>
                            <a class="btn btn-newblue btn-sm right" onclick="schoolReport()" id="schoolReportId" target="_blank">成绩单</a>
                            <a class="btn btn-newblue btn-sm right" data-toggle="modal" data-target="#modal" id="scoreComment" target="_blank" data-url="/classinfo/classinfo/scoreEvaluate">成绩评语</a>
                        </div>
                        <table id="table" class="table table-striped table-bordered" cellspacing="0">

                            <thead>
                            <tr>
                                <td>编号</td>
                                <td>姓名</td>
                                <td>性别</td>
                                <td>出生日期</td>
                                <td>民族</td>
                                <td>身份证号</td>
                                <td>有无学籍</td>
                                <td>费用状态</td>
                                <td>再校状态</td>
                                <td>实践评分</td>
                                <td>实践评价</td>


                            </tr>
                            </thead>

                            <tbody>
                            <#if students ??>
                            <#list students as s>
                            <tr dataid="${(s.pkStudent)!}" dataCaption="${(s.caption)!}" <#if s.istype?? &&s.istype==3>bgcolor="#696969"</#if> dataScore="${(s.map.studentScoreEntity.code)!}" dataEv="${(s.map.studentScoreEntity.caption)!}">

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
                                    <#if s.isSchoolrool?? && s.isSchoolrool==1>有<#else >无</#if>
                                </td>
                                <td>
                                    <#if s.map.costType ?? && s.map.costType=='costFalse'>未缴清<#elseif s.map.costType ?? && s.map.costType=='costTrue'>已缴清</#if>
                                </td>
                                <td>
                                    <#if s.state?? && s.state ==0>转出<#elseif s.state?? && s.state ==1>在校<#else ></#if>
                                </td>
                                <td>
                                    <#if s.map.studentScoreEntity.code ??>
                                        <span class="level">
                                            <#list 0..(s.map.studentScoreEntity.code?number) as t>
                                                <i class="level_solid"></i>
                                            </#list>
                                        </span>
                                        <#else >
                                        暂无评分
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
                                    <label class="control-label col-xs-3 col-md-3">学生姓名</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input title=""  id="caption" name="" value="" class="form-control" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="btn btn-newblue btn-sm search" onclick="query()"><i class="fa fa-search"> </i>搜索</span>
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
                            <td>编号</td>
                            <td>姓名</td>
                            <td>性别</td>
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
                    <div id="linkMan" class="tab-pane">
                        <table id="pagingTableTeacher" class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <td>姓名</td>
                                <td>性别</td>
                                <td>账号</td>
                            </tr>
                            </thead>

                            <tbody>
                            <#if students ??>
                                <#list students as t>
                                <tr >

                                    <td>${(t.caption)!}</td>

                                    <td>
                                        <#if t.sex ?? && t.sex==1>男<#else >女</#if>
                                    </td>
                                    <td>
                                    ${(t.pkStudent)!}
                                    </td>
                                </tr>
                                </#list>
                            </#if>

                            </tbody>
                        </table>
                    <#--  </div>-->
                    </div>
                    <#--班级活动-->
                    <div id="activity" class="tab-pane">
                        <table id="pagingTableActivity" class="table table-striped table-bordered" cellspacing="0">
                            <thead>

                            </thead>

                            <tbody>


                            </tbody>
                        </table>
                    <#--  </div>-->
                    </div>
                <#--竞赛记录-->
                    <div id="racerecord" class="tab-pane">
                        <table id="pagingTableRacerecord" class="table table-striped table-bordered" cellspacing="0">
                            <thead>

                            </thead>

                            <tbody>


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
    });

    var classesid="";
    var className="";
    var id="";
    var studentName="";

    function parentAssignment(pkClassinfo,classCap,pkStudent,studentCaption){
        $("#pkClassinfo").val(classesid);
        $("#classCap").val(className);
        $("#pkStudent").val(id);
        $("#studentCaption").val(studentName);

    }

    $(".activityStudent").on("click",function(){

        var types = $(this).data("types");

//            var formdata = {};
        classesid = $("#classId").val();
        className = $("#classCaption").val();
        id = $(".table-color-").attr("dataid");
        studentName = $(".table-color-").attr("dataCaption");
        var score = $(".table-color-").attr("dataScore");
        var ev = $(".table-color-").attr("dataEv");
        if (id == null) {
            Notify.danger("请选择一行数据！！！");
            return false;
        }

        if(types == 1) {
            if (score != "" && score != null && score != "null") {
                Notify.danger("不可重复打分");
                return false;
            }
        }else {
            if (ev != "" && ev != null) {
                Notify.danger("不可重复评价");
                return false;
            }
        }

        $(this).attr("data-url",$(this).attr("data-url")+"?pkClassinfo="+classesid/*+"&pkStudent="+id+"&studentName="+studentName+"&className="+className*/);

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

//    保存
    function sub() {
        $("#formId").submit();

    }
    //提交
    function subissubmit() {
        var formdata = $("#formId").serialize();
        $.ajax({
           url:"/classinfo/classinfo/submit",
            data:formdata,
            type:"POST",
            dataType:"json",
            success:function (data) {
                if(data.code ==0){
                    Notify.success(data.message);
                    window.location.reload();
                }else {
                    Notify.danger(data.message);
                }
            }
        });
    }
    //审核
    function subisaudit() {
        var formdata = $("#formId").serialize();
        $.ajax({
           url:"/classinfo/classinfo/audit",
            data:formdata,
            type:"POST",
            dataType:"json",
            success:function (data) {
                if(data.code ==0){
                    location.reload();
                    Notify.success(data.message);
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
            Notify.danger("请先选择学生");
            return false;
        }
        var oldPkClassinfo = $("#classId").val();
        $(this).attr("data-url",$(this).attr("data-url")+"?pkStudent="+pkStudent+"&oldPkClassinfo="+oldPkClassinfo);
    });

    $("#scoreComment").on("click",function(){
        var pkStudent = $(".table-color-").attr("dataid");
        var studentName = $(".table-color-").attr("dataCaption");
        var schoolYear = $("#schoolYear").val();
        var term = $("#termId").val();
        var termName = $("#termId").find("option:selected").text();
        var pkClassinfo = $("#classId").val();
        if(pkStudent ==null || pkStudent =='' ){
            Notify.danger("请选择学生");
            return false;
        }
        if(schoolYear ==null || schoolYear ==''){
            Notify.danger("请选择年份");
            return false;
        }
        if(term ==null || term ==''){
            Notify.danger("学期");
            return false;
        }
        $(this).attr("data-url",$(this).attr("data-url")+"?pkStudent="+pkStudent+"&schoolYear="+schoolYear+"&term="+term+"&studentName="+studentName+"&termName="+termName);

    });

    function deleteEmployee(id) {
        if(id) {
            var pkClassinfo = $("#classId").val();
            var flag = confirm("确定要删除吗？");

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
        {"sTitle":"学生姓名","data" : "map.studentEntity.caption",
            "render": renderStudentScoreEdit} ,
        {"sTitle":"课程","data" : "map.sysdicEntity.caption"},
        {"sTitle":"老师","data" : "map.userEntity.caption"},
        {"sTitle":"考试成绩","data" : "scores"},
        {"sTitle":"考试类型","data" : "map.testTypeEntity.caption"},
        {"sTitle":"考试名称","data" : "map.scoreCaptionEntity.caption"},
        {"sTitle":"权重","data" : "weight"},
        {"sTitle":"年份","data" : "year","render": renderDate},
        {"sTitle":"学期","data" : "map.termEntity.caption"},
        {"sTitle":"备注","data" : "memo"}
    ];

    $(document).ready(function() {
        query();
        queryActivity();
        queryRacerecord();
//        queryTeacher();
    } );

    function query() {
        var data  = { "pkClassinfo": $("#classId").val(),"caption":$("#caption").val()};
        init(GetTableColumn("classinfoStudentScoreList"),"/classinfo/studentScoress/queryByPaging",data,"pagingTable");
    }
    function queryActivity() {
        var data  = { "pkClassinfo": $("#classId").val(),"caption":$("#caption").val()};
        init(GetTableColumn("classActivitylist"),"/classinfo/classActivity/queryByPaging",data,"pagingTableActivity");
    }
    function queryRacerecord() {
        var data  = { "pkClassinfo": $("#classId").val()};
        init(GetTableColumn("racerecordlist"),"/classinfo/raceRecord/queryByPaging",data,"pagingTableRacerecord");
    }
    
    function renderActivityCaption(data, type, row){
        return '<a href="/classinfo/classActivity/edit?activityId='+row.activityId+'" class="yellow" >'+"查看"+'</a>';
    }
    function renderRaceCaption(data, type, row){
        return '<a href="/classinfo/raceRecord/edit?raceId='+row.raceId+'" class="yellow" >'+"查看"+'</a>';
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

    function schoolReport() {

        var pkStudent = $(".table-color-").attr("dataid");
        var schoolYear = $("#schoolYear").val();
        var term = $("#termId").val();
        var pkClassinfo = $("#classId").val();
        if(pkStudent ==null || pkStudent =='' ){
            Notify.danger("请选择学生");
            return false;
        }
        if(schoolYear ==null || schoolYear ==''){
            Notify.danger("请选择年份");
            return false;
        }
        if(term ==null || term ==''){
            Notify.danger("学期");
            return false;
        }

        $("#schoolReportId").attr("href","/classinfo/classinfo/schoolReport?pkStudent="+pkStudent+"&schoolYear="+schoolYear+"&term="+term + "&classinfo="+pkClassinfo);

    }

    function getGrade(divisionId) {
        var op=document.getElementById("selectgtadeId");
        op.options.length=0;
        if (divisionId != null && divisionId != ''){
            $.ajax({
                type: "GET",
                url: "/classinfo/classinfo/getDivisionGrade",
                data: {"divisionId": divisionId},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        var arr = data.data;
                        if (arr !=null){
                            $.each(arr,function (i) {
                                var oOption  = new Option(arr[i].gradeName,arr[i].pkDivisionGrade);
                                document.getElementById("selectgtadeId")[i]=oOption;
                            });
                        }

                    } else {

                    }
                },
                error: function () {

                }
            });
        }
    }

</script>