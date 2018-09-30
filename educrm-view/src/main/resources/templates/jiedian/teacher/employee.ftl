<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />
<form class="form-horizontal look form-ajaxadd" id="formid" method="post" action="/system/employee/saveAll" data-target="/jiedian/employee/query">
    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>员工资料<span>...</span></h2>
            <div class="breadcrumb-wrapper">
                <div class="btn-group fr title-btn">
                    <a class="btn btn-sm btn-newblue" onClick="history.back(-1);">返回</a>
                    <a class="btn btn-newblue btn-sm" onclick="sub()">
                        保存
                    </a>
                    <#if employee.pkEmployee??>
                        <a class="btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/model/getCourses?pkEmployee=${(employee.pkEmployee)!}">
                            添加课程
                        </a>
                    </#if>




                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>基本信息</strong></a></li>
            </ul>
            <input type="hidden" id="pkemp" name="emp.pkEmployee" value="${(employee.pkEmployee)!}">
            <input type="hidden" name="emp.creator" value="${(employee.creator)!}">
            <div class="tab-content employee">
                <div id="all" class="tab-pane active">
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">员工编号</label>
                                <div class="col-xs-9 col-md-9">
                                    <input name="emp.code" value="${(employee.code)!}" class="form-control" type="text">
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
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">性别</label>
                                <div class="col-xs-9 col-md-9">
                                    <#--<select name="emp.sex">-->
                                        <span class="rdio rdio-warning">
                                           <input name="emp.sex" value="1"  class="deldis"
                                                  <#if employee.sex?? && employee.sex==1 >checked="checked"</#if>
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">男</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="emp.sex" value="0" id="radio4"  class="deldis"
                                                  <#if employee.sex?? && employee.sex==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio4">女</label>
                                       </span>
                                        <#--<option value="1"-->
                                                <#--<#if employee.sex?? && employee.sex==1 >selected="selected"</#if>>男-->
                                        <#--</option>-->
                                        <#--<option value="2"-->
                                                <#--<#if employee.sex?? && employee.sex==2 >selected="selected"</#if>>女-->
                                        <#--</option>-->
                                    <#--</select>-->

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">

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
                        </div>
                        <div class="col-xs-12 col-md-4">
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
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">婚姻状况</label>
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
                                <label class="control-label col-xs-3 col-md-3">入职日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control js-datepicker" name="emp.enterdates"value="${(employee.enterdate?string("yyyy-MM-dd"))!}" title="" type="text">
                                </div>

                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">离职日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control js-datepicker" name="emp.leavedates"value="${(employee.leavedate?string("yyyy-MM-dd"))!}" title="" type="text">
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">合同开始日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control js-datepicker" name="emp.contractStartDateTime"value="${(employee.contractStartDate?string("yyyy-MM-dd"))!}" title="" type="text">
                                </div>

                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">合同截止日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control js-datepicker" name="emp.contractEndDateTime"value="${(employee.contractEndDate?string("yyyy-MM-dd"))!}" title="" type="text">
                                </div>

                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">试用日期</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control js-datepicker" name="emp.probationDateTime"value="${(employee.probationDate?string("yyyy-MM-dd"))!}" title="" type="text">
                                </div>

                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">是否生育</label>
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

                                    <#--<select name="emp.isfertility"-->
                                            <#--class="form-control">-->
                                        <#--<option value="1"-->
                                                <#--<#if employee.isfertility?? && employee.isfertility==1 >selected="selected"</#if>>-->
                                            <#--否-->
                                        <#--</option>-->
                                        <#--<option value="2"-->
                                                <#--<#if employee.isfertility?? && employee.isfertility==2 >selected="selected"</#if>>-->
                                            <#--是-->
                                        <#--</option>-->

                                    <#--</select>-->
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
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">毕业院校</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control" name="emp.graduatedSchool"value="${(employee.graduatedSchool)!}" title="" type="text">
                                </div>

                            </div>
                        </div>


                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">最高学历</label>
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
                                <label class="control-label col-xs-3 col-md-3">外语程度</label>
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
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">专业</label>
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
                                <label class="control-label col-xs-4 col-md-4">是否住校</label>
                                <div class="col-xs-8 col-md-8">
                                     <span class="rdio rdio-warning">
                                           <input name="emp.isstay" value="1"  class="deldis"
                                                  <#if employee.isstay?? && employee.isstay==1 >checked="checked"</#if>
                                                  id="radio9" type="radio">
                                           <label disabled="disabled" for="radio9">是</label>
                                       </span>
                                    <span class="rdio rdio-warning">
                                           <input name="emp.isstay" value="0" id="radio10"  class="deldis"
                                                  <#if employee.isstay?? && employee.isstay==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio10">否</label>
                                       </span>
                                    <#--<select name="emp.isstay" class="form-control">-->
                                        <#--<option value="1" <#if employee.isstay?? && employee.isstay==1>selected="selected"</#if>>是</option>-->
                                        <#--<option value="2" <#if employee.isstay?? && employee.isstay==2>selected="selected"</#if>>否</option>-->
                                    <#--</select>-->
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">证件类型</label>
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
                                <label class="control-label col-xs-3 col-md-3">证件号码</label>
                                <div class="col-xs-9 col-md-9"><input name="emp.idCard" value="${(employee.idCard)!}" class="form-control" title="" type="text"></div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">证件地址</label>
                                <div class="col-xs-9 col-md-9"><input name="emp.idAddress" value="${(employee.idAddress)!}" class="form-control" title="" type="text"></div>
                            </div>
                        </div>
                    <div class="row">
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
                </div>
                </div>
                    <div class="row">
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
                                <label class="control-label col-xs-4 col-md-4">是否为会员</label>
                                <div class="col-xs-8 col-md-8">
                                     <span class="rdio rdio-warning">
                                           <input name="emp.isMember" value="1"  class="deldis"
                                                  <#if employee.isMember?? && employee.isMember==1 >checked="checked"</#if>
                                                  id="radio7" type="radio">
                                           <label disabled="disabled" for="radio7">是</label>
                                       </span>
                                    <span class="rdio rdio-warning">
                                           <input name="emp.isMember" value="0" id="radio8"  class="deldis"
                                                  <#if employee.isMember?? && employee.isMember==1 ><#else >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio8">否</label>
                                       </span>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">联系电话</label>
                                <div class="col-xs-9 col-md-9">
                                    <input name="emp.phone" value="${(employee.phone)!}"class="form-control" title="" type="text">
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3"><i class="red">*</i>移动电话</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control" name="emp.mobilePhone"value="${(employee.mobilePhone)!}" title="" type="text" data-bv-notempty data-bv-notempty-message="请输入手机号">
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
                                <label class="control-label col-xs-3 col-md-3">关联员工</label>
                                <div class="col-xs-9 col-md-9">
                                    <select name="emp.sysUser" class="form-control selectpicker" data-live-search="true">
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

                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="form-group">
                            <#--<input type="text" class="form-control input-sm" data-parsley-min="6" placeholder="">-->
                                <label class="control-label col-xs-4 col-md-1">居住地址</label>
                                <div class="col-xs-8 col-md-11">
                                    <div class="col-xs-4 col-md-2">
                                        <select title="" id="seachprov" class="province"  name="emp.province">
                                            <option value="">请选择省</option>
                                        <#if province??>
                                            <#list province as p>
                                                <option value="${(p.id?c)!}" <#if employee.province?? &&employee.province==p.id >selected</#if>>${p.name}</option>
                                            </#list>
                                        </#if>
                                        </select>
                                    </div>
                                    <div class="col-xs-4 col-md-2">
                                        <select title="" id="city" name="emp.area" class="city"  >
                                            <option value="">请选择</option>
                                        <#if areas??>
                                            <#list areas as a>
                                                <option value="${(a.id?c)!}" <#if employee.area?? &&employee.area==a.id >selected</#if>>${a.name}</option>
                                            </#list>
                                        </#if>

                                        </select>
                                    </div>
                                    <div class="col-xs-4 col-md-2">
                                        <select title="" id="town" name="emp.town" class="form-control town">
                                            <option value="">请选择</option>
                                        <#if towns??>
                                            <#list towns as t>
                                                <option value="${(t.id?c)!}" <#if employee.town?? &&employee.town==t.id >selected</#if>>${t.name}</option>
                                            </#list>
                                        </#if>
                                        </select>
                                    </div>
                                    <div class="col-xs-4 col-md-6">
                                        <label class="control-label col-xs-4 col-md-2">详细地址</label>
                                        <div class="control-label col-xs-8 col-md-10">
                                            <input class="form-control" name=""value="" title="" type="text">
                                        </div>

                                    </div>



                                </div>
                            </div>
                        </div>

                    </div>
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

                    <#--<div class="row">-->
                        <#--<div class="col-xs-12 col-md-12">-->
                            <#--<div class="form-group">-->
                            <#--&lt;#&ndash;<input type="text" class="form-control input-sm" data-parsley-min="6" placeholder="">&ndash;&gt;-->
                                <#--<label class="control-label col-xs-4 col-md-1">居住地址</label>-->
                                <#--<div class="col-xs-8 col-md-11">-->
                                    <#--<div class="col-xs-4 col-md-2">-->
                                        <#--<select title="" id="seachprov" name="seachprov" onChange="changeComplexProvince(this.value, sub_array, 'seachcity', 'seachdistrict');" ></select>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 col-md-2">-->
                                        <#--<select title="" id="seachcity" name="homecity" onChange="changeCity(this.value,'seachdistrict','seachdistrict');" ></select>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 col-md-2">-->
                                        <#--<i id="seachdistrict_div"><select title="" id="seachdistrict" name="seachdistrict" class="form-control"></select></i>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 col-md-6">-->
                                        <#--<input class="form-control" name=""value="" title="" type="text">-->
                                    <#--</div>-->



                                <#--</div>-->
                            <#--</div>-->
                        <#--</div>-->

                    <#--</div>-->
                </div>
                <div id="two" class="tab-pane">
                    <#--<input type="button" class="form-ajax btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/system/employeeJobExp/create?pkEmployee=${(employee.pkEmployee)!}" value="添加">-->
                    <div class="job">
                        <table class="table bor1  table-color table-hover mb30">
                        <#-- <input type="button"   class="btn btn-success"  value="增加" id="addTable" onclick="add_tr(this)"/>-->
                            <thead>
                            <tr>
                                <#--<th>操作</th>-->
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>工作单位</th>
                                <th>职务</th>
                                <th>证明人</th>
                                <th>证明人电话</th>
                                <th>离职原因</th>
                                <th>备注</th>
                                <th><i class="fa fa-remove op0 add_icon"></i>

                                    <a data-toggle="modal" data-target="#modal" data-url="/system/employeeJobExp/create?pkEmployee=${(employee.pkEmployee)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>

                            </tr>
                            </thead>
                            <tbody>
                            <#if employeeJobExps ??>
                            <#list employeeJobExps as ejob>
                            <tr>
                                <#--<td >
                                    <a  data-toggle="modal"data-target="#modal" data-url="/system/employeeJobExp/edit?pkEmployeeJobExp=${(ejob.pkEmployeeJobExp)!}" class="success"> 修改</a>

                                </td>-->
                                <td>
                                    <#--<input name="ejob.startdate" readonly value=" ${(ejob.startdateTime)!}" class="form-control bor0">-->
                                ${(ejob.startdateTime)!}

                                </td>
                                <td>
                                ${(ejob.enddateTime)!}
                                </td>
                                <td>
                                    <a  data-toggle="modal"data-target="#modal" data-url="/system/employeeJobExp/edit?pkEmployeeJobExp=${(ejob.pkEmployeeJobExp)!}" class="success">
                                     ${(ejob.jobUnit)!}
                                    </a>


                                </td>
                                <td>
                                ${(ejob.jobTitle)!}
                                </td>
                                <td>
                                   <#-- <input name="ejob.witness" readonly value="${(ejob.witness)!}" class="form-control bor0">-->
                                ${(ejob.witness)!}
                                </td>
                                <td>
                                    <#--<input name="ejob.witnessPhone" readonly value="${(ejob.witnessPhone)!}" class="form-control bor0">-->
                                ${(ejob.witnessPhone)!}
                                </td>
                                <td>
                                    <#--<input name="ejob.reasonLeaving" readonly value="${(ejob.reasonLeaving)!}" class="form-control bor0">-->
                                ${(ejob.reasonLeaving)!}
                                </td>
                                <td>
                                    <#--<input name="ejob.memo"  value="${(ejob.memo)!}" class="form-control bor0">-->
                                ${(ejob.memo)!}
                                </td>

                            </tr>
                            </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="four" class="tab-pane">
                    <#--<input type="button" class="form-ajax btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/system/employeeCertificate/create?pkEmployee=${(employee.pkEmployee)!}" value="添加">-->
                    <table class="table bor1 table-color table-hover mb30">
                        <thead>
                        <tr>
                            <#--<th>操作</th>-->
                            <th width="200">获得时间</th>
                            <th width="800">证书或荣誉名称</th>
                            <th width="800">备注</th>
                            <th><i class="fa fa-remove op0 add_icon"></i>

                                <a data-toggle="modal" data-target="#modal" data-url="/system/employeeCertificate/create?pkEmployee=${(employee.pkEmployee)!}">
                                    <i class="fa fa-plus add_icon"></i>
                                </a>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if certificates ??>
                        <#list certificates as ce>
                        <tr>
                            <#--<td >-->
                                <#--<a  data-toggle="modal"data-target="#modal" data-url="/system/employeeCertificate/edit?pkEmployeeCertificate=${(ce.pkEmployeeCertificate)!}" class="success"> 修改</a>-->

                            <#--</td>-->
                            <td>
                                <#--<input name="cer.dateTime"  value=" ${(ce.dateTime)!}" class="form-control bor0">-->
                            ${(ce.dateTime)!}
                            </td>
                            <td>
                                <#--<input name="cer.certificateName"  value="${(ce.certificateName)!}" class="form-control bor0">-->
                                <a  data-toggle="modal"data-target="#modal" data-url="/system/employeeCertificate/edit?pkEmployeeCertificate=${(ce.pkEmployeeCertificate)!}" class="success"> ${(ce.certificateName)!}</a>

                            </td>
                            <td>
                                <#--<input name="cer.memo"  value="${(ce.memo)!}" class="form-control bor0">-->
                            ${(ce.memo)!}
                            </td>
                        </tr>


                        </#list>
                        </#if>
                        </tbody>
                    </table>

                </div>
                <div id="five" class="tab-pane">
                    <#--<input type="button" class="form-ajax btn btn-newblue btn-sm" data-toggle="modal" data-target="#modal" data-url="/system/employeeHomeInfo/create?pkEmployee=${(employee.pkEmployee)!}" value="添加">-->
                    <table class="table bor1  table-color table-hover mb30">
                        <thead>
                        <tr>
                            <#--<th>操作</th>-->
                            <th width="200">姓名</th>
                            <th width="200">称谓</th>
                            <th width="400">工作单位</th>
                            <th width="400">岗位</th>
                            <th width="200">联系方式</th>
                            <th width="200">备注</th>
                            <th><i class="fa fa-remove op0 add_icon"></i>

                                <a data-toggle="modal" data-target="#modal" data-url="/system/employeeHomeInfo/create?pkEmployee=${(employee.pkEmployee)!}">
                                    <i class="fa fa-plus add_icon"></i>
                                </a>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if homeInfo ??>
                        <#list homeInfo as h>
                        <tr id="testId">
                            <#--<td >-->
                                <#--<a  data-toggle="modal"data-target="#modal" data-url="/system/employeeHomeInfo/edit?pkEmployeeHomeInfo=${(h.pkEmployeeHomeInfo)!}" class="success"> 修改</a>-->

                            <#--</td>-->
                            <td>
                                <#--<input name="home.name" readonly value="${(h.name)!}" class="form-control bor0">-->
                                <a  data-toggle="modal"data-target="#modal" data-url="/system/employeeHomeInfo/edit?pkEmployeeHomeInfo=${(h.pkEmployeeHomeInfo)!}" class="success"> ${(h.name)!}</a>
                            </td>
                            <td>
                                <#--<input name="home.relationship" readonly value="${(h.relationship!)}" class="form-control bor0">-->
                            ${(h.relationship!)}
                            </td>
                            <td>
                                <#--<input name="home.jobUnit" readonly value="${(h.jobUnit)!}" class="form-control bor0">-->
                            ${(h.jobUnit)!}
                            </td>
                            <td>
                                <#--<input name="home.jobTitle" readonly value="${(h.jobTitle)!}" class="form-control bor0">-->
                            ${(h.jobTitle)!}
                            </td>
                            <td>
                                <#--<input name="home.phone" readonly value="${(h.phone)!}" class="form-control bor0">-->
                            ${(h.phone)!}
                            </td>
                            <td>
                                <#--<input name="home.memo" readonly value=" ${(h.memo)!}" type="text" class="form-control bor0">-->
                            ${(h.memo)!}
                            </td>
                        </tr>
                        </#list>

                        </#if>

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
                            <th><i class="fa fa-remove op0 add_icon"></i>

                                <a data-toggle="modal" data-target="#modal" data-url="/system/employeeHomeInfo/create?pkEmployee=${(employee.pkEmployee)!}">
                                    <i class="fa fa-plus add_icon"></i>
                                </a>
                            </th>
                        </tr>
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
                                    <#--&lt;#&ndash;<input name="home.jobTitle" readonly value="${(h.jobTitle)!}" class="form-control bor0">&ndash;&gt;-->
                                <#--${(h.jobTitle)!}-->
                                    <#--</td>-->
                                   <#---->
                                <#--</tr>-->
                                <#--</#list>-->

                            <#--</#if>-->

                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</form>




<#include "../../commons/footer.ftl"/>
<#--<script src="${staticPath}/citys/Area.js"></script>-->
<#--<script src="${staticPath}/citys/AreaData_min.js"></script>-->
<#--<script src="${staticPath}/citys/myadrass.js"></script>-->

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

//    $("#btn").click(function(){
//        $("#testId").append(" <tr>3333333333</tr>");
//        //或者 $("<div>XXXXXXXXXXXXX</div>").appendTo("#test");
//    });

    $(".province").on("change", function () {
        var id = $(this).val();
        $("#city").empty().append("<option value=''>请选择</option>");
        $("#town").empty().append("<option value=''>请选择</option>");
        $.get("/system/tbAllarea/query",{"parent":id},function(data){
            for (var i = 0, len = data.length; i < len; i++) {
                var model = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                $("#city").append(model);
            }

        });
    });


    $(".city").on("change", function () {
        var id = $(this).val();
        $("#town").empty().append("<option value=''>请选择</option>");
        $.get("/system/tbAllarea/query",{"parent":id},function(data){
            for (var i = 0, len = data.length; i < len; i++) {
                var model = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                $("#town").append(model);
            }
        });
    });
    $('.form-ajaxadd').bootstrapValidator({
//      live: 'disabled',
      message: 'This value is not valid',
      feedbackIcons: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
          'emp.mobilePhone': {
              validators: {
              	 notEmpty: {
                       message: '请输入您的手机号码！'
                   },
					phone: {
                       message: '请输入正确的手机号码！',
                       country: 'ZHCN',
                   },
						remote: {
                          message: '手机号已经存在',
                          url: '/jiedian/employee/checkphone',
							type: 'post',
							dataType: 'json',
							data:{
								'pkEmp':function(){
									return $("#pkemp").val();
								}
							}
							
                      }
					
              }
          },
         
         
        
      }
  }).on('success.form.bv', function(e) {
  	
      // Prevent form submission
      e.preventDefault();

      // Get the form instance
      var $form = $(e.target);

      // Get the BootstrapValidator instance
      var bv = $form.data('bootstrapValidator');

      // Use Ajax to submit form data

     $.post($form.attr('action'), $form.serialize(), function(data) {

			if(data.code==0){
					Notify.success(data.message);
              if($form.data('target')){
                  setTimeout("window.location.href='"+$form.data('target')+"'",3000);
              }
			}else{
					Notify.danger(data.message);
					window.location.reload();
			}
      }, 'json');
  });
 

</script>
<#--<script type="text/javascript">-->
    <#--&lt;#&ndash;老师课程&ndash;&gt;-->
    <#--var columns = [-->
        <#--{"sTitle":"老师编号","data":"code"},-->
        <#--{"sTitle":"老师名称","data" : "caption",-->
            <#--"render": function (data, type, row){-->
                <#--return '<a href="/system/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';-->
            <#--}} ,-->
        <#--{"sTitle":"课程名称","data" : "idCard"},-->
        <#--{"sTitle":"性别","data" : "sex","render": function (data){-->
            <#--if (data==1){ return '男';}-->
            <#--else {return '女'; }-->
        <#--}}-->

    <#--];-->

    <#--$(document).ready(function() {-->
        <#--query();-->
    <#--} );-->

    <#--function query() {-->
        <#--var data  = { "pkEmployee": '${(employee.pkEmployee)!""}',"caption":$("#caption").val()};-->
        <#--init(columns,"/classinfo/courseTeacher/queryByPaging",data);-->
    <#--}-->
<#--</script>-->
