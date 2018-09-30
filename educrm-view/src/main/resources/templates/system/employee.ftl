<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<form class="look form-ajax" id="formid" method="post" action="/system/employee/saveAll" data-target="/system/employee/query">
    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>员工资料<span>...</span></h2>
            <div class="breadcrumb-wrapper">
                <div class="btn-group fr title-btn">
                    <a class="btn btn-sm btn-newblue" onClick="history.back(-1);">返回</a>
                    <a class="btn btn-newblue btn-sm" onclick="sub()">保存</a>
                    <#if employee.pkEmployee??>
                        <#--<a class="btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/model/getCourses?pkEmployee=${(employee.pkEmployee)!}">-->
                            <#--<@spring.message "personnel.empaddcourse"/>-->
                        <#--</a>-->
                    </#if>

                    <#--<a class="btn btn-newblue btn-sm">入职</a>-->
                     <#--<a class="btn btn-newblue btn-sm">离职</a>-->
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>基本信息</strong></a></li><!--基本信息-->
                <#if employee.pkEmployee??>
                <li class=""><a data-toggle="tab" href="#two"><strong>工作经历</strong></a></li><!--工作经历-->
            <#--<li class=""><a data-toggle="tab" href="#three"><strong>职业规划</strong></a></li>-->
                <li class=""><a data-toggle="tab" href="#four"><strong>专业证书或荣誉 </strong></a></li><!--专业证书或荣誉-->
                <li class=""><a data-toggle="tab" href="#five"><strong>家庭主要成员 </strong></a></li><!--家庭主要成员-->
                <#--<li class=""><a data-toggle="tab" href="#six"><strong><@spring.message "personnel.empcurricula"/> </strong></a></li><!--任教课程&ndash;&gt;-->
                </#if>
            </ul>
            <input type="hidden" id="pkEmployeeId" name="emp.pkEmployee" value="${(employee.pkEmployee)!}">
            <input type="hidden" name="emp.creator" value="${(employee.creator)!}">
            <div class="tab-content employee">
                <div id="all" class="tab-pane active">
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">员工编号</label>
                                <div class="col-xs-9 col-md-9">
                                    <input name="emp.code" value="${(employee.code)!}" class="form-control" <#if codeKey?? && codeKey !="1">onkeyup="this.value=this.value.replace(/\D/g,'')"</#if>  type="text">
                                    <#--<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')">-->
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>性别</label>
                                <div class="col-xs-9 col-md-9">
                                <#--<select name="emp.sex">-->
                                    <span class="rdio rdio-warning">
                                           <input name="emp.sex" value="1"  class="deldis"
                                                  <#if employee.sex?? && employee.sex==1 >checked="checked"</#if>
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">男</label>
                                       </span>
                                    <span class="rdio rdio-warning">
                                           <input name="emp.sex" value="2" id="radio4"  class="deldis"
                                                  <#if employee.sex?? && employee.sex==2 >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio4">女</label>
                                       </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">所属校区</label>
                                <div class="col-xs-9 col-md-9">
                                    <select name="emp.pkDomain">
                                    <#if domains ??>
                                        <#list domains as domain>
                                            <option value="${(domain.pkDomain)!}" <#if domain.pkDomain ?? && employee.pkDomain ?? && employee.pkDomain == domain.pkDomain >selected="selected"</#if>>${(domain.caption)!}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3" >所属部门</label>
                                <div class="col-xs-9 col-md-9">
                                    <select name="emp.pkDepartment">
                                    <#if departments ??>
                                        <#list departments as depart>
                                            <option value="${(depart.pkDepartment)!}" <#if depart.pkDepartment ?? && employee.pkDepartment?? && employee.pkDepartment == depart.pkDepartment>selected="selected"</#if> >${(depart.caption)!}</option>

                                        </#list>
                                    </#if>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label  col-xs-3 col-md-3"><i class="red">*</i>员工名称</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control" name="emp.caption" value="${(employee.caption)!}" title="" type="text" data-bv-notempty data-bv-notempty-message="请录入姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">健康状况</label>
                                <div class="col-xs-9 col-md-9">
                                    <select name="emp.healthStatus">
                                    <#if health ??>
                                        <#list health as h>
                                            <option value="${(h.pkSysDictValues)!}" <#if employee.healthStatus?? && h.pkSysDictValues?? && h.pkSysDictValues ==employee.healthStatus>selected</#if> >${(h.caption)!}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">离职日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control js-datepicker" name="emp.leavedates"value="${(employee.leavedate?string("yyyy-MM-dd"))!}" title="" type="text">
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">入职日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control js-datepicker" name="emp.enterdates"value="${(employee.enterdate?string("yyyy-MM-dd"))!}" title="" type="text">
                                </div>

                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="student-img upload text-center">
                                <input type="file" name="file" id="doc" style="width:80px;"
                                       onchange="ajaxSubmitForm()" />
                                <div id="dd">
                                <#if employee.img??>
                                    <img src="${(employee.img)!}">
                                <#else >
                                    <img src="${staticPath}/images/photos/icon-h.jpg">
                                </#if>
                                </div>
                                <p class="gray">上传头像</p>
                                <input type="hidden" name="emp.img" id="empImg" value="${(employee.img)!}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>婚姻状况</label>
                                <div class="col-xs-9">
                                    <select name="emp.maritalStatus">
                                    <#if marital ??>
                                        <#list marital as m >
                                            <option value="${(m.pkSysDictValues)!}" <#if employee.maritalStatus?? && m.pkSysDictValues?? && employee.maritalStatus==m.pkSysDictValues>selected</#if>>${(m.caption)!}</option>
                                        </#list>
                                    </#if>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>是否生育</label>
                                <div class="col-xs-9 col-md-9">

                                    <span class="rdio rdio-warning">
                                           <input name="emp.isfertility" value="1"  class="deldis"
                                                  <#if employee.isfertility?? && employee.isfertility==1 >checked="checked"</#if>
                                                  id="radio1" type="radio">
                                           <label disabled="disabled" for="radio1">是</label>
                                       </span>
                                    <span class="rdio rdio-warning">
                                           <input name="emp.isfertility" value="0" id="radio2"  class="deldis"
                                                  <#if employee.isfertility?? && employee.isfertility==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio2">否</label>
                                       </span>
                                </div>

                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">出生日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control js-datepicker" name="emp.birthTime"value="${(employee.birth?string("yyyy-MM-dd"))!}" title="" type="text">
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">毕业院校</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control" name="emp.graduatedSchool"value="${(employee.graduatedSchool)!}" title="" type="text">
                                </div>

                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>最高学历</label>
                                <div class="col-xs-9 col-md-9">
                                    <select name="emp.education">
                                        <#if educations??>
                                    <#list educations as e>
                                        <option value="${(e.pkSysDictValues)!}"
                                                <#if employee.education?? && employee.education ==e.pkSysDictValues >selected="selected"</#if>>${(e.caption)!}</option>
                                    </#list>
                                        </#if>
                                    </select>

                                </div>

                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>外语程度</label>
                                <div class="col-xs-9 col-md-9">
                                    <select  name="emp.foreignLanguage" class="form-control">
                                        <#if foreignLanguages??>
                                    <#list foreignLanguages as f>
                                        <option value="${(f.pkSysDictValues)!}"
                                                <#if employee.foreignLanguage?? && employee.foreignLanguage ==f.pkSysDictValues >selected="selected"</#if>>${(f.caption)!}</option>
                                    </#list>

                                        </#if>
                                    </select>
                                </div>

                            </div>
                        </div>

                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">岗位</label>
                            <div class="col-xs-9 col-md-9">
                                <select id="selectjobid" class="selectpicker show-tick form-control" multiple data-live-search="true">
                                <#if jobPosts??>
                                    <#list jobPosts as j>
                                        <option value="${(j.pkSysDictValues)!}"
                                                <#if employee.jobPost?? && employee.jobPost ==j.pkSysDictValues >selected="selected"</#if>>${(j.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                                <input  name="emp.jobPost" type="hidden" id="jobPostId" value="${(employee.jobPost)!}" class="jobpost" />
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>专业</label>
                            <div class="col-xs-9 col-md-9">
                                <input class="form-control" name="emp.theProfession"value="${(employee.theProfession)!}" title="" type="text">
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">状态</label>
                            <div class="col-xs-9 col-md-9">
                                     <span class="rdio rdio-warning">
                                           <input name="emp.isleave" value="1"  class="deldis"
                                                  <#if employee??&&employee.isleave?? && employee.isleave==1 >checked="checked"</#if>
                                                  id="radio13" type="radio">
                                           <label disabled="disabled" for="radio13">在职</label>
                                       </span>
                                <span class="rdio rdio-warning">
                                           <input name="emp.isleave" value="0" id="radio14"  class="deldis"
                                                  <#if employee??&&employee.isleave?? && employee.isleave==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio14">离职</label>
                                       </span>
                            <#--<input class="form-control" name="emp.isleave"value="${(employee.isleave)!}" title="" type="text">-->
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4 col-md-4"><i class="red">*</i>是否住校</label>
                            <div class="col-xs-8 col-md-8">
                                     <span class="rdio rdio-warning">
                                           <input name="emp.isstay" value="1"  class="deldis"
                                                  <#if employee.isstay?? && employee.isstay==1 >checked="checked"</#if>
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">是</label>
                                       </span>
                                <span class="rdio rdio-warning">
                                           <input name="emp.isstay" value="0" id="radio4"  class="deldis"
                                                  <#if employee.isstay?? && employee.isstay==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio4">否</label>
                                       </span>
                            <#--<select name="emp.isstay" class="form-control">-->
                            <#--<option value="1" <#if employee.isstay?? && employee.isstay==1>selected="selected"</#if>>是</option>-->
                            <#--<option value="2" <#if employee.isstay?? && employee.isstay==2>selected="selected"</#if>>否</option>-->
                            <#--</select>-->
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>证件类型</label>
                            <div class="col-xs-9 col-md-9">
                                <select name="emp.idKind" class="form-control">
                                <#if idkind??>
                                    <#list idkind as k>
                                        <option value="${(k.pkSysDictValues)!}" <#if employee.idKind??&& k.pkSysDictValues?? && k.pkSysDictValues ==employee.idKind >selected</#if>>${(k.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                            <#--<input name="emp.idKind" value="${(employee.idKind)!}" class="form-control" title="" type="text">-->
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>证件号码</label>
                            <div class="col-xs-9 col-md-9"><input name="emp.idCard" value="${(employee.idCard)!}" class="form-control" title="" type="text"></div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">证件地址</label>
                            <div class="col-xs-9 col-md-9"><input name="emp.idAddress" value="${(employee.idAddress)!}" class="form-control" title="" type="text"></div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4 col-md-4">担任班主任</label>
                            <div class="col-xs-8 col-md-8">
                                     <span class="rdio rdio-warning">
                                           <input name="emp.canheadteacher" value="1"  class="deldis"
                                                  <#if employee.canheadteacher?? && employee.canheadteacher==1 >checked="checked"</#if>
                                                  id="radio5" type="radio">
                                           <label disabled="disabled" for="radio5">能</label>
                                       </span>
                                <span class="rdio rdio-warning">
                                           <input name="emp.canheadteacher" value="0" id="radio6"  class="deldis"
                                                  <#if employee.canheadteacher?? && employee.canheadteacher==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio6">否</label>
                                       </span>


                            <#--<select name="emp.canheadteacher"class="form-control">-->
                            <#--<option value="1" <#if employee.canheadteacher ?? && employee.canheadteacher ==1>selected="selected"</#if>>是</option>-->
                            <#--<option value="2" <#if employee.canheadteacher ?? && employee.canheadteacher ==2>selected="selected"</#if>>否</option>-->
                            <#--</select>-->
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">职称</label>
                            <div class="col-xs-9 col-md-9"><input name="emp.jobTitle" value="${(employee.jobTitle)!}" class="form-control" title="" type="text"></div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3">档案所在地</label>
                            <div class="col-xs-9 col-md-9"><input name="emp.fileLocation" value="${(employee.fileLocation)!}" class="form-control" title="" type="text"></div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">计算机水平</label>
                            <div class="col-xs-9 col-md-9">
                                <select name="emp.computerSkill">
                                <#if computerskill ??>
                                    <#list computerskill as c >
                                        <option value="${(c.pkSysDictValues)!}" <#if employee.computerSkill?? &&c.pkSysDictValues??&& employee.computerSkill==c.pkSysDictValues>selected</#if> >${(c.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>现住址</label>
                            <div class="col-xs-9 col-md-9"><input name="emp.address" value="${(employee.address)!}" class="form-control" title="" type="text"></div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>联系电话</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="emp.phone" value="${(employee.phone)!}"class="form-control" title="" type="text">
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">移动电话</label>
                            <div class="col-xs-9 col-md-9">
                                <input class="form-control" name="emp.mobilePhone"value="${(employee.mobilePhone)!}" title="" type="text">
                            </div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">户籍</label>
                            <div class="col-xs-9 col-md-9"><input class="form-control" name="emp.accountLoction" value="${(employee.accountLoction)!}" title="" type="text"></div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">社保所在地</label>
                            <div class="col-xs-9 col-md-9"><input name="emp.socialSecurityLocation" value="${(employee.socialSecurityLocation)!}"class="form-control" title="" type="text"></div>

                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">政治面貌</label>
                            <div class="col-xs-9 col-md-9">
                                <select name="emp.politicalStatus" class="form-control">
                                    <option value="">请选择</option>
                                <#if politicalStatus??>
                                    <#list politicalStatus as p>
                                        <option value="${(p.pkSysDictValues)!}" <#if p.pkSysDictValues?? && employee.politicalStatus??&& p.pkSysDictValues==employee.politicalStatus>selected</#if>>${(p.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                            <#--<input name="emp.politicalStatus" value="${(employee.politicalStatus)!}" class="form-control" type="text">-->
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">关联账号</label>
                            <div class="col-xs-9 col-md-9">
                                <select name="emp.sysUser" class="selectpicker form-control" data-live-search="true">
                                    <option value="">请选择</option>
                                <#if sysUser??>
                                    <#list sysUser as p>
                                        <option value="${(p.pkSysUser)!}" <#if p.pkSysUser?? && employee.sysUser??&& p.pkSysUser==employee.sysUser>selected</#if>>${(p.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>

                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3">民族</label>
                            <div class="col-xs-9">
                                <select name="emp.nation">
                                    <option value="">请选择</option>
                                <#if nation ??>
                                    <#list nation as co>
                                        <option value="${(co.pkSysDictValues)!}"
                                                <#if co.pkSysDictValues?? && employee.nation ?? && employee.nation ==co.pkSysDictValues>selected</#if>>${(co.caption)!}</option>
                                    </#list>

                                </#if>
                                </select>
                            <#-- <input name="stu.nation" value="${(student.nation)!}" type="text"
                                    class="form-control">-->
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-1">3年职业规划</label>
                                <div class="col-xs-9 col-md-11">
                                    <textarea placeholder="3年职业规划" rows="3" name="careerPlanning" class="form-control" type="text"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="two" class="tab-pane">
                    <#--<input type="button" class="form-ajax btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/system/employeeJobExp/create?pkEmployee=${(employee.pkEmployee)!}" value="添加">-->
                    <div class="job">
                        <table id="pagingTableJobExp" class="table bor1  table-color table-hover mb30">
                        <#-- <input type="button"   class="btn btn-success"  value="增加" id="addTable" onclick="add_tr(this)"/>-->
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="four" class="tab-pane">
                    <#--<input type="button" class="form-ajax btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/system/employeeCertificate/create?pkEmployee=${(employee.pkEmployee)!}" value="添加">-->
                    <table id="pagingTableCertificate" class="table bor1 table-color table-hover mb30">
                        <thead>
                        <#--<tr>-->
                            <#--&lt;#&ndash;<th>操作</th>&ndash;&gt;-->
                            <#--<th width="200">获得时间</th>-->
                            <#--<th width="800">证书或荣誉名称</th>-->
                            <#--<th width="800">备注</th>-->
                            <#--<th><i class="fa fa-remove op0 add_icon"></i>-->

                                <#--<a data-toggle="modal" data-target="#modal" data-url="/system/employeeCertificate/create?pkEmployee=${(employee.pkEmployee)!}">-->
                                    <#--<i class="fa fa-plus add_icon"></i>-->
                                <#--</a>-->
                            <#--</th>-->
                        <#--</tr>-->
                        </thead>
                        <tbody>
                        <#--<#if certificates ??>-->
                        <#--<#list certificates as ce>-->
                        <#--<tr>-->
                            <#--&lt;#&ndash;<td >&ndash;&gt;-->
                                <#--&lt;#&ndash;<a  data-toggle="modal"data-target="#modal" data-url="/system/employeeCertificate/edit?pkEmployeeCertificate=${(ce.pkEmployeeCertificate)!}" class="success"> 修改</a>&ndash;&gt;-->

                            <#--&lt;#&ndash;</td>&ndash;&gt;-->
                            <#--<td>-->
                                <#--&lt;#&ndash;<input name="cer.dateTime"  value=" ${(ce.dateTime)!}" class="form-control bor0">&ndash;&gt;-->
                            <#--${(ce.date?string("yyyy-MM-dd"))!}-->
                            <#--</td>-->
                            <#--<td>-->
                                <#--&lt;#&ndash;<input name="cer.certificateName"  value="${(ce.certificateName)!}" class="form-control bor0">&ndash;&gt;-->
                                <#--<a  data-toggle="modal"data-target="#modal" data-url="/system/employeeCertificate/edit?pkEmployeeCertificate=${(ce.pkEmployeeCertificate)!}" class="success"> ${(ce.certificateName)!}</a>-->

                            <#--</td>-->
                            <#--<td>-->
                                <#--&lt;#&ndash;<input name="cer.memo"  value="${(ce.memo)!}" class="form-control bor0">&ndash;&gt;-->
                            <#--${(ce.memo)!}-->
                            <#--</td>-->
                        <#--</tr>-->


                        <#--</#list>-->
                        <#--</#if>-->
                        </tbody>
                    </table>

                </div>
                <div id="five" class="tab-pane">
                    <#--<input type="button" class="form-ajax btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/system/employeeHomeInfo/create?pkEmployee=${(employee.pkEmployee)!}" value="添加">-->
                    <table id="pagingTableHome" class="table bor1  table-color table-hover mb30">
                        <thead>
                        <#--<tr>-->
                            <#--&lt;#&ndash;<th>操作</th>&ndash;&gt;-->
                            <#--<th width="200">姓名</th>-->
                            <#--<th width="200">称谓</th>-->
                            <#--<th width="400">工作单位</th>-->
                            <#--<th width="400">岗位</th>-->
                            <#--<th width="200">联系方式</th>-->
                            <#--<th width="200">备注</th>-->
                            <#--<th><i class="fa fa-remove op0 add_icon"></i>-->

                                <#--<a data-toggle="modal" data-target="#modal" data-url="/system/employeeHomeInfo/create?pkEmployee=${(employee.pkEmployee)!}">-->
                                    <#--<i class="fa fa-plus add_icon"></i>-->
                                <#--</a>-->
                            <#--</th>-->
                        <#--</tr>-->
                        </thead>
                        <tbody>
                        <#--<#if homeInfo ??>-->
                        <#--<#list homeInfo as h>-->
                        <#--<tr id="testId">-->
                            <#--<td>-->
                                <#--<a  data-toggle="modal"data-target="#modal" data-url="/system/employeeHomeInfo/edit?pkEmployeeHomeInfo=${(h.pkEmployeeHomeInfo)!}" class="success"> ${(h.name)!}</a>-->
                            <#--</td>-->
                            <#--<td>-->
                            <#--${(h.relationship!)}-->
                            <#--</td>-->
                            <#--<td>-->
                            <#--${(h.jobUnit)!}-->
                            <#--</td>-->
                            <#--<td>-->
                            <#--${(h.jobTitle)!}-->
                            <#--</td>-->
                            <#--<td>-->
                            <#--${(h.phone)!}-->
                            <#--</td>-->
                            <#--<td>-->
                                <#--&lt;#&ndash;<input name="home.memo" readonly value=" ${(h.memo)!}" type="text" class="form-control bor0">&ndash;&gt;-->
                            <#--${(h.memo)!}-->
                            <#--</td>-->
                        <#--</tr>-->
                        <#--</#list>-->

                        <#--</#if>-->

                        </tbody>
                    </table>
                </div>
                <#-- 任教课程-->
                <div id="six" class="tab-pane">
                <#--<input type="button" class="form-ajax btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/system/employeeCertificate/create?pkEmployee=${(employee.pkEmployee)!}" value="添加">-->
                    <table class="table bor1 table-color table-hover mb30">
                        <thead>
                        <tr>
                        <#--<th>操作</th>-->
                            <th width="200">老师编号</th>
                            <th width="200">姓名</th>
                            <th width="200">课程名称</th>
                            <th width="200">备注</th>
                            <th width="200">操作</th>
                            <#--<th><i class="fa fa-remove op0 add_icon"></i>-->

                                <#--<a data-toggle="modal" data-target="#modal" data-url="/system/employeeHomeInfo/create?pkEmployee=${(employee.pkEmployee)!}">-->
                                    <#--<i class="fa fa-plus add_icon"></i>-->
                                <#--</a>-->
                            <#--</th>-->
                        </tr>
                        </thead>
                        <tbody>
                            <#if employeeCourse ??>
                                <#list employeeCourse as c>
                                <tr id="testId">
                                    <td>
                                         ${(employee.code)!}
                                    </td>
                                    <td>
                                ${(employee.caption!)}
                                    </td>
                                    <td>
                                ${(c.caption)!}
                                    </td>
                                    <td>
                                    <#--<input name="home.jobTitle" readonly value="${(h.jobTitle)!}" class="form-control bor0">-->
                                ${(c.memo)!}
                                    </td>
                                    <td>
                                        <a class="danger" onclick="delCourse('${(c.pkSysDictValues)!}')">删除</a>
                                    </td>
                                </tr>
                                </#list>

                            </#if>

                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</form>




<#include "../commons/footer.ftl"/>

<script type="text/javascript">

    $(function(){
        var job =$("#jobPostId").val();
        var   mySelect = document.getElementById("selectjobid");
        if(job != null || !job.equals("") ){
            for(var i = 0;i<job.length;i++){
                if(job.charAt(i)=="1"){
                    mySelect.options[i].selected=true;//添加选中状态
                }

            }
        }

    });

    

    function sub() {

//        var   mySelect = document.getElementById("selectjobid");
//        var se = document.getElementsByName("emp.jobPost");
//
//        alert(se);
//        var   index =mySelect.selectedIndex;
//        var   text =mySelect.options[index].text;
//        alert(text);
//        alert(index);
//        var   value =mySelect.options[index].value;
//        alert(value);
//        mySelect.options[index].selected
        var job = "";
        for(i=0;i<document.getElementById('selectjobid').length;i++){
            if(document.getElementById('selectjobid')[i].selected==true){
                job = job+"1";
            }else {
                job = job+"0";
            }
        }
        //alert(job);

        $(".jobpost").val(job);


        $("#formid").submit();
    }


    function add_tr(obj) {
        var tr = $(obj).parent().parent();
        tr.after(tr.clone());
    }

   /* var boarddiv = "<div  style='background:white;width:100%;height:100%;z-index:999;position:absolute;top:0;margin-top:100px;'>加载中...</div>";

    $(window).load(function(){
        $(document.body).append(boarddiv);
    });*/

    $("#btn").click(function(){
        $("#testId").append(" <tr>3333333333</tr>");
        //或者 $("<div>XXXXXXXXXXXXX</div>").appendTo("#test");
    });

    //删除课程
    function delCourse(id) {
        if (confirm("确定删除吗？")){
            var pkEmployee = "${(employee.pkEmployee)!}";
            $.ajax({
                type: "POST",
                url: "/classinfo/courseTeacher/delete",
                data: {"pkEmployee": pkEmployee,"pkSysDictValues":id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        alert(data.message)
                    }
                },
                error: function () {

                }
            });

        }


        
    }


    $(document).ready(function () {
        queryCertificate();
        queryHome();
        queryJobExp();
    });

//    function renderHomeCaption(data, type, row) {
//        return '<i class="fa fa-remove op0 add_icon"></i><a data-toggle="modal" data-target="#modal" data-url="/system/employeeHomeInfo/create?pkEmployee='+row.pkEmployee+'"><i class="fa fa-plus add_icon"></i></a>';
//    }
//    function renderJpbCaption(data, type, row) {
//
//        return '<i class="fa fa-remove op0 add_icon"></i><a data-toggle="modal" data-target="#modal" data-url="/system/employeeJobExp/create?pkEmployee='+row.pkEmployee+'"><i class="fa fa-plus add_icon"></i></a>';
//    }
//    function renderCertificateCaption(data, type, row) {
//        return '<i class="fa fa-remove op0 add_icon"></i><a data-toggle="modal" data-target="#modal" data-url="/system/employeeCertificate/create?pkEmployee='+row.pkEmployee+'"><i class="fa fa-plus add_icon"></i></a>';
//    }

    function queryJobExp() {
        var data = {"pkEmployee":$("#pkEmployeeId").val()};
        init(GetTableColumn("employeeJobExp"), "/system/employeeJobExp/queryByPaging", data,"pagingTableJobExp");
    }
    function queryHome() {
        var data = {"pkEmployee":$("#pkEmployeeId").val()};
        init(GetTableColumn("employeeHomeInfo"), "/system/employeeHomeInfo/queryByPaging", data,"pagingTableHome");
    }
    function queryCertificate() {
        var data = {"pkEmployee":$("#pkEmployeeId").val()};
        init(GetTableColumn("employeeCertificate"), "/system/employeeCertificate/queryByPaging", data,"pagingTableCertificate");
    }
    
    function rederHomeCaption(data, type, row) {
        return '<a  data-toggle="modal" data-target="#modal" data-url="/system/employeeHomeInfo/edit?pkEmployeeHomeInfo='+row.pkEmployeeHomeInfo+'" class="success"> '+data+'</a>';
    }
    function rederJobCaption(data, type, row) {
        return '<a  data-toggle="modal" data-target="#modal" data-url="/system/employeeJobExp/edit?pkEmployeeJobExp='+row.pkEmployeeJobExp+'" class="success">'+data+'</a>';
    }
    function rederCerCaption(data, type, row) {
        return '<a  data-toggle="modal" data-target="#modal" data-url="/system/employeeCertificate/edit?pkEmployeeCertificate='+row.pkEmployeeCertificate+'" class="success">'+data+'</a>';
    }
//    function renderCaption (data, type, row){
//        return '<a href="/system/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
//    }

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
                    $("#empImg").val(result.data);
//                    alert(result.data);
//                    Notify.success("上传成功");
                }
            }
        });
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
    function addHome() {
        var id = $("#pkEmployeeId").val();
        $("#homeid").attr("data-url","/system/employeeHomeInfo/create?pkEmployee="+id);
        return true;
    }
    function addJobExp() {
        var id = $("#pkEmployeeId").val();
        $("#jobid").attr("data-url","/system/employeeJobExp/create?pkEmployee="+id);
        return true;
    }
    function addCertificate() {
        var id = $("#pkEmployeeId").val();
        $("#cerid").attr("data-url","/system/employeeCertificate/create?pkEmployee="+id);
        return true;
    }

    function renderAdd(data, type, row) {
        return  "";
    }

</script>

