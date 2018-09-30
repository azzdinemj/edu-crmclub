<#include "../../commons/top.ftl">
<#include "../../commons/left.ftl">
<form class="form-horizontal look form-ajaxadd" method="POST" id="formId" action="/student/student/saveall"
      data-target="${(URL)!}?pkStudent=${(student.pkStudent)!}"  >
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>学员档案 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
            <button class="btn btn-sm btn-newblue" type="submit">保存</button>
        
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

           
            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">


                    <#--<h3>学生信息</h3>-->
                        <input id="pkStudent"  name="stu.pkStudent" value="${(student.pkStudent)!}" type="hidden">
                        <input name="stu.creator" value="${(student.creator)!}" type="hidden">
                        <input name="stu.istype" value="1" type="hidden">
                        <input name="stu.shortCode" id="shortCodeId" value="${(student.shortCode)!}" type="hidden">
                        <div class="row">
                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;margin-bottom: 15px;">基本信息</h3>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">校区</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" readonly value=" ${(domain.caption)!}"
                                               type="text">
                                        <input name="stu.pkDomain" value="${(domain.pkDomain)!}" type="hidden">

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
                                    <label class="control-label col-xs-3"><i class="red">*</i>性别</label>
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

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>个人电话</label>
                                    <div class="col-xs-9">
                                        <input name="stu.phone" value="${(student.phone)!}"  type="text" class="form-control"  >
                                    </div>
                                </div>
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">学生类型</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#---->
                                        <#--<select name="stu.istype">-->
                                            <#---->
                                            <#--<option value=0-->
                                                    <#--<#if student.istype?? && student.istype==0>selected="selected"</#if>>-->
                                                <#--意向学生-->
                                            <#--</option>-->
                                            <#--<option value=1-->
                                                    <#--<#if student.istype?? && student.istype==1>selected="selected"</#if>>-->
                                                <#--正式学生-->
                                            <#--</option>-->
                                            <#--<option value=2-->
                                                    <#--<#if student.istype?? && student.istype==2>selected="selected"</#if>>-->
                                                <#--无效学生-->
                                            <#--</option>-->
                                        <#--</select>-->
                                    <#--</div>-->
                                <#--</div>-->

                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">编号</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" readonly name="stu.code" value="${(student.code)!}"
                                               type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>姓名</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="txtChinese" name="stu.caption" onblur="getmakePy()" value="${(student.caption)!}"
                                               type="text" data-bv-notempty data-bv-notempty-message="请输入姓名" >

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>出生日期</label>
                                    <div class="col-xs-9">
                                        <input name="stu.birthdayTime"
                                               value="${(student.birthday?string("yyyy-MM-dd"))!}" id="data" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">学生状态</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.state">

                                            <option value=1
                                                    <#if student.state?? && student.state==1>selected="selected"</#if>>
                                                在校
                                            </option>
                                            <option value=0
                                                    <#if student.state?? && student.state==0>selected="selected"</#if>>
                                                转出
                                            </option>
                                        </select>
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
                                    <label class="control-label col-xs-3">原就读学校</label>
                                    <div class="col-xs-9">
                                        <input name="stu.oldSclool" value="${(student.oldSclool)!}" type="text"
                                               class="form-control"></div>
                                </div>
                            </div>

                            <#--需要修改-->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">在读年级</label>
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
                                        <#--<input name="stu.email" value="${(student.email)!}" type="text" class="form-control">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">邮箱</label>
                                    <div class="col-xs-9"><input name="stu.email" value="${(student.email)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="row">


                        </div>
						<!-- 阶点新加字段 -->
                        <div class="row">
                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;">课程信息</h3>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">意向课程</label>
                                    <div class="col-xs-9">
                                        <input name="stu.willCourse" value="${(student.willCourse)!}" type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">意向星级</label>
                                    <div class="col-xs-9">
                                    <select name="stu.willRank">
                                            <option value="">请选择</option>
                                        <#if willRank ??>
                                            <#list willRank as ch>
                                                <option value="${(ch.pkSysDictValues)!}"
                                                        <#if ch.pkSysDictValues?? && student.willRank ??&& student.willRank ==ch.pkSysDictValues>selected</#if>>${(ch.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                       </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">意向课时</label>
                                    <div class="col-xs-9">
                                        <input name="stu.willLesson" value="${(student.willLesson)!}" type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">上课目标</label>
                                    <div class="col-xs-9">
                                        <input name="stu.target" value="${(student.target)!}" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">课程类型</label>
                                    <div class="col-xs-9">
                                        <select name="stu.courseType">
                                            <option value="">请选择</option>
                                        <#if courseType ??>
                                            <#list courseType as ch>
                                                <option value="${(ch.pkSysDictValues)!}"
                                                        <#if ch.pkSysDictValues?? && student.courseType ??&& student.courseType ==ch.pkSysDictValues>selected</#if>>${(ch.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">授课形式</label>
                                    <div class="col-xs-9">
                                        <select name="stu.classType">
                                            <option value="">请选择</option>
                                        <#if classType ??>
                                            <#list classType as ch>
                                                <option value="${(ch.pkSysDictValues)!}"
                                                        <#if ch.pkSysDictValues?? && student.classType ??&& student.classType ==ch.pkSysDictValues>selected</#if>>${(ch.caption)!}</option>
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
                                    <label class="control-label col-xs-3">老师要求</label>
                                    <div class="col-xs-9">
                                        <input name="stu.teacherRequest" value="${(student.teacherRequest)!}" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学科基础</label>
                                    <div class="col-xs-9"><input name="stu.subjectBasis"
                                                                 value="${(student.subjectBasis)!}"
                                                                 type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">英语语言水平</label>
                                    <div class="col-xs-9">
                                        <input name="stu.englishLevel" value="${(student.englishLevel)!}" type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学习习惯</label>
                                    <div class="col-xs-9"><input name="stu.learningHabits"
                                                                 value="${(student.learningHabits)!}"
                                                                 type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">在读状态</label>
                                    <div class="col-xs-9">
                                        <select name="stu.readState">
                                            <option value="">请选择</option>
                                        <#if readState ??>
                                            <#list readState as ch>
                                                <option value="${(ch.pkSysDictValues)!}"
                                                        <#if ch.pkSysDictValues?? && student.readState ??&& student.readState ==ch.pkSysDictValues>selected</#if>>${(ch.caption)!}</option>
                                            </#list>

                                        </#if>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <#--其他信息-->
                        <div class="row">
                            <div class="col-sm-12">
                                <h3 style="padding-left: 0;margin-bottom: 15px;">其他信息</h3>
                            </div>
                            <div class="row">
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
                                            <input name="stu.schoolrool" value="${(student.schoolrool)!}"

                                                   class="form-control">
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">学籍地址</label>
                                        <div class="col-xs-9"><input name="stu.schoolroolAddress"
                                                                     value="${(student.schoolroolAddress)!}"
                                                                     type="text"
                                                                     class="form-control"></div>
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
                                        <#-- <select name="stu.greenCard">
                                             <option value=2
                                                     <#if student.greenCard ?? && student.greenCard ==2>selected="selected"</#if>>
                                                 无
                                             </option>
                                             <option value=1
                                                     <#if student.greenCard ?? && student.greenCard ==1>selected="selected"</#if>>
                                                 有
                                             </option>
                                         </select>-->
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
                                        <#-- <input name="stu.greenCardCountry"
                                                                  value="${(student.greenCardCountry)!}"
                                                                  type="text" class="form-control">-->
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">家长期许</label>
                                        <div class="col-xs-9">
                                            <input name="stu.parentsExpect" value="${(student.parentsExpect)!}" type="text"
                                                   class="form-control">
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-xs-12 col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">毕业去向</label>
                                        <div class="col-xs-9">
                                            <input name="stu.graduationGo" value="${(student.graduationGo)!}" type="text"
                                                   class="form-control">
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
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
                                        <#-- <input name="stu.tracksource" value="${(student.tracksource)!}"
                                                                  type="text"
                                                                  class="form-control">-->
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
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
                            </div>

                        </div>

<#--==================================备注-->
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1 col-md-1">备注</label>
                                    <div class="col-xs-11 col-md-11">
                                    <#--<input name="stu.memo" value="${(student.memo)!}" type="text" class="form-control">-->
                                        <textarea class="form-control" name="stu.memo" rows="3"
                                                  va>${(student.memo)!}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 阶点新加字段 -->
                        <div class="col-sm-12">
                            <h3>家庭信息</h3>
                        </div>
                        <div class="">
                            <table id="tab" class="table bor1 table-color  mb30">
                                <thead>
                                <tr>
                                    <td>姓名</td>
                                    <td>关系</td>
                                    <td width="80px">性别</td>
                                    <td>职业</td>
                                    <td>职务</td>
                                    <td>工作单位</td>
                                    <td>学历</td>
                                    <td>电话</td>
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
                                            <input name="man[${index}].caption" readonly value=" ${(man.caption)!}"
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
                                            <input name="man[${index}].relationship" readonly
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
                                            <input name="man[${index}].occupation" readonly
                                                   value=" ${(man.occupation)!}"
                                                   type="text" class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].duty" readonly value=" ${(man.duty)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].workUnit" readonly value=" ${(man.workUnit)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].degreeCompleted" readonly
                                                   value=" ${(man.degreeCompleted)!}" type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].phone" readonly value=" ${(man.phone)!}"
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input name="man[${index}].address" readonly value=" ${(man.address)!}"
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

                        <table class="table bor1 table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th>校区</th>
                                <th>记录编号</th>

                                <th>面试主题</th>
                                <th>面试老师</th>
                                <th>面试时间</th>
                                <th>面试情况</th>
                                <th>备注</th>

                            </tr>
                            </thead>
                            <tbody>
                            <#if interviews??>
                                <#list interviews as inter>
                                <tr>
                                    <td>${(inter.pkDomain)!}</td>
                                    <td>${(inter.code)!}</td>
                                    <td>${(inter.caption)!}</td>
                                    <td>${(inter.pkEmployee)!}</td>
                                    <td>${(inter.dateTime)!}</td>
                                    <td>${(inter.remark)!}</td>
                                    <td>${(inter.memo)!}</td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            <#--</#if>-->

                <!--学生计划-->
                <div id="plans" class="tab-pane">
                    <div class="col-sm-12">

                        <table class="table bor1 table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th>申请国家</th>
                                <th>选择大学因素排序</th>
                                <th>喜欢的科目</th>
                                <th>擅长的科目</th>
                                <th>计划专业</th>
                                <th>计划工作</th>
                                <th>毕业规划</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th><i class="fa fa-remove op0 add_icon"></i>

                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentPlans/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if studentPlans??>
                                <#list studentPlans as plans>
                                <tr>
                                    <td>${(plans.targetCountries)!}</td>
                                    <td>${(plans.collegeChoosing)!}</td>
                                    <td>${(plans.subjectsLike)!}</td>
                                    <td>${(plans.subjectsGood)!}</td>
                                    <td>${(plans.majorsWant)!}</td>
                                    <td>${(plans.workWant)!}</td>
                                    <td>${(plans.ofterCollegeGraduation)!}</td>
                                    <td>${(plans.creator)!}</td>
                                    <td>${(plans.creationDate?string("yyyy-MM-dd"))!}</td>

                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->

                <!--教育经历-->
                <div id="assigned" class="tab-pane">
                    <div class="col-sm-12">

                        <table class="table bor1 table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th>学校名称</th>
                                <th>学校地址</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>成绩</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentEduExperience/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if exp??>
                                <#list exp as exp>
                                <tr>
                                    <td>${(exp.schoolName)!}</td>
                                    <td>${(exp.schoolAddress)!}</td>
                                    <td>${(exp.startDateTime)!}</td>
                                    <td>${(exp.endDateTime)!}</td>
                                    <td>${(exp.academicRecord)!}</td>
                                    <td>${(exp.creator)!}</td>
                                    <td>${(exp.creationDate?string("yyyy-MM-dd"))!}</td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->

                <!--获奖经历-->
                <div id="prize" class="tab-pane">
                    <div class="col-sm-12">
                        <table class="table bor1 table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th>学校名称</th>
                                <th>奖项名称</th>
                                <th>奖项级别</th>
                                <th>年级</th>
                                <th>获奖时间</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentAwards/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>

                            </tr>
                            </thead>
                            <tbody>
                            <#if studentAwards??>
                                <#list  studentAwards as awards>
                                <tr>
                                    <td>${(domain.caption)!}</td>
                                    <td>${(awards.activityName)!}</td>
                                    <td>${(awards.activityLevel)!}</td>
                                    <td>${(awards.grade)!}</td>
                                    <td>${(awards.dateTime)!}</td>
                                    <td>${(awards.creator)!}</td>
                                    <td>${(awards.creationDate?string("yyyy-MM-dd"))!}</td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->
                <!--作品集-->
                <div id="sampleReels" class="tab-pane">
                    <div class="col-sm-12">

                        <table class="table bor1 table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th>学校名称</th>
                                <th>作品类型</th>
                                <th>年级</th>
                                <th>获奖时间</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentWorksPortfolio/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if worksPortfolios??>
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
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->

                <!--特长爱好-->
                <div id="hobby" class="tab-pane">
                    <div class="col-sm-12">

                        <table class="table bor1  table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th align="left">特长爱好</th>
                                <th align="left">学习时长</th>
                                <th align="left">奖项&成就</th>
                                <th align="left">创建人</th>
                                <th align="left">创建时间</th>
                                <th align="left">修改人</th>
                                <th align="left">修改时间</th>
                                <th align="left">
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentSpecialty/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if specialty??>
                                <#list specialty as spe>
                                <tr>
                                    <td align="left">
                                        <input name="spe.specialty" readonly value="${(spe.specialty)!}" type="text"
                                               class="form-control bor0">

                                    </td>
                                    <td align="left">
                                        <input name="spe.studyTime" readonly value="${(spe.studyTime)!}" type="text"
                                               class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.awardsAchievements" readonly
                                               value="${(spe.awardsAchievements)!}"
                                               type="text" class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.creator" readonly value="${(spe.creator)!}" type="text"
                                               class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.creationDate" readonly
                                               value="${(spe.creationDate?string("yyyy-MM-dd"))!}" type="text"
                                               class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.modifier" readonly value="${(spe.modifier)!}" type="text"
                                               class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.lasteditDate" readonly
                                               value="${(spe.lasteditDate?string("yyyy-MM-dd"))!}" type="text"
                                               class="form-control bor0">
                                    </td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->
                <!--活动经历-->
                <div id="unresolved" class="tab-pane">
                    <div class="col-sm-12">
                        <table class="table bor1 table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th>学校名称</th>
                                <th>活动名称</th>
                                <th>活动类型</th>
                                <th>活动地址</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>角色</th>
                                <th>成绩</th>
                                <th>费用</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentActivityExp/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if act??>
                                <#list act as act>
                                <tr>
                                    <td>${(domain.caption)!}</td>
                                    <td>${(act.activityName)!}</td>
                                    <td>${(act.activityType)!}</td>
                                    <td>${(act.activityType)!}</td>
                                    <td>${(act.startDateTime)!}</td>
                                    <td>${(act.endDateTime)!}</td>
                                    <td>${(act.position)!}</td>
                                    <td>${(act.score)!}</td>
                                    <td>${(act.cost)!}</td>
                                    <td>${(act.creator)!}</td>
                                    <td>${(act.creationDate?string("yyyy-MM-dd"))!}</td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                <#-- <div class="col-sm-12">
                     <h3>
                         年级奖项
                     </h3>

                 </div>-->
                <#-- <div class="col-sm-12">
                     <h3>
                         年级作品集
                     </h3>
                     <table class="table bor1 table-color table-hover mb30">
                         <thead>
                         <tr>
                             <th>学校名称</th>
                             <th>作品类型</th>
                             <th>年级</th>
                             <th>获奖时间</th>
                             <th>创建人</th>
                             <th>创建时间</th>
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
                                                    <td>考试时间</td>
                                                    <td>考试成绩</td>
                                                    <td>考试时间</td>
                                                    <td>考试成绩</td>
                                                    <td>考试时间</td>
                                                    <td>考试成绩</td>
                                                </tr>
                                                <tr>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82 听力 18 口语 18 阅读 20 写作 16</td>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82 听力 18 口语 18 阅读 20 写作 16</td>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82 听力 18 口语 18 阅读 20 写作 16</td>
                                                </tr>
                                                <tr>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82
                                                        听力 18 口语 18 阅读 20 写作 16
                                                    </td>
                                                    <td>无</td>
                                                    <td class="text-left">无</td>
                                                    <td>无</td>
                                                    <td class="text-left">无</td>
                                                </tr>
                                                <tr>
                                                    <td>08/20/2016</td>
                                                    <td class="text-left">TOEFL 82
                                                        听力 18 口语 18 阅读 20 写作 16
                                                    </td>
                                                    <td>无</td>
                                                    <td class="text-left">无</td>
                                                    <td>无</td>
                                                    <td class="text-left">无</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">是否参加校外的TOEFL或SAT培训</td>
                                                    <td colspan="4">否</td>
                                                </tr>
                                                </tbody>
                                            </table>
                    -->

                    </div>

                </div><!-- tab-pane -->
                <!--访谈记录-->
                <div id="added" class="tab-pane">
                    <div class="col-sm-12">

                        <table class="table table-color bor-1 mb30">
                            <thead>
                            <tr>
                                <th>学校名称</th>
                                <th>年级</th>
                                <th>访谈时间</th>
                                <th>访谈内容</th>
                                <th>建议</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>备注</th>
                                <th><a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentInterviewRecord/create?pkStudent=${(student.pkStudent)!}">
                                    <i class="fa fa-plus add_icon"></i>
                                </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if records??>
                                <#list records as reco>
                                <tr>
                                    <td>${(domain.caption)!}</td>
                                    <td>${(reco.grade)!}</td>
                                    <td>${(reco.dateTime)!}</td>
                                    <td>${(reco.what)!}</td>
                                    <td>${(reco.advices)!}</td>
                                    <td>${(reco.notes)!}</td>
                                    <td>${(reco.creator)!}</td>
                                    <td>${(reco.creationDate?string("yyyy-MM-dd"))!}</td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>

                    </div>

                </div><!-- tab-pane -->
                <!--纪律行为记录-->
                <div id="discipline" class="tab-pane">
                    <div class="col-sm-12">
                        <table class="table table-color bor-1 mb30">
                            <thead>
                            <tr>
                                <th>学校名称</th>
                                <th>年级</th>
                                <th>时间</th>
                                <th>事件情形</th>
                                <th>处分</th>
                                <th>备注</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentBehaviorRecord/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if behRecords??>
                                <#list behRecords as ber>
                                <tr>
                                    <td>${(domain.caption)!}</td>
                                    <td>${(ber.grade)!}</td>
                                    <td>${(ber.dateTime)!}</td>
                                    <td>${(ber.details)!}</td>
                                    <td>${(ber.disciplinaryAction)!}</td>
                                    <td>${(ber.notes)!}</td>
                                    <td>${(ber.creator)!}</td>
                                    <td>${(ber.creationDate?string("yyyy-MM-dd"))!}</td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>

                    </div>
                </div><!-- tab-pane -->
                <!--成绩单-->
                <div id="transcript" class="tab-pane">
                    <div class="col-sm-12">
                        <table class="table table-color bor-1 mb30">
                            <thead>
                            <tr>
                                <th>学校名称</th>
                                <th>考试项目</th>
                                <th>考试成绩</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>备注</th>
                                <th>
                                    <a data-toggle="modal" data-target="#modal"
                                       data-url="/student/studentTestPlansScores/create?pkStudent=${(student.pkStudent)!}">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>

                            </tr>
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

<#include "../../commons/footer.ftl" >

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
                '<td><select name="man[' + parentNumber + '].sex"><option value="1" >男</option><option value="2" >女</option></select></td>' +
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
    $('.form-ajaxadd').bootstrapValidator({
//        live: 'disabled',
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'stu.phone': {
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
                            url: '/jiedian/student/student/checkphone',
							type: 'post',
							dataType: 'json',
							data:{
								'pkStu':function(){
									return $("#pkStudent").val();
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