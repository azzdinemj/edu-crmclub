<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl">
<#include "${staticPath}/commons/left.ftl">
<form class="look form-ajax" method="POST" id="formId" action="/student/studentlist/save"
      data-target="/student/studentlist/query"  >
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
            <ul class="nav nav-tabs " style="background: none">

                <li class="active"><a data-toggle="tab" href="#all"><strong>学生信息</strong></a></li>

            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">


                    <#--<h3>学生信息</h3>-->
                   <#--     <input id="pkStudent" name="stu.pkStudent" value="${(student.pkStudent)!}" type="hidden">-->
                       <#-- <input name="stu.creator" value="${(student.creator)!}" type="hidden">-->
                        <input name="stu.pkDomain" value="${(student.pkDomain)!}" type="hidden">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">编号</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" readonly name="stu.pkStudent" value=" ${(student.pkStudent)!}"
                                               type="text">

                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">姓名</label>
                                    <div class="col-xs-9">
                                        <input name="stu.caption" value="${(student.caption)!}"  type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">个人电话</label>
                                    <div class="col-xs-9">
                                        <input name="stu.phone" value="${(student.phone)!}"  type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">部门</label>
                                    <div class="col-xs-9">
                                        <input name="stu.branch" value="${(student.branch)!}"  type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">公司</label>
                                    <div class="col-xs-9">
                                        <input name="stu.company" value="${(student.company)!}"  type="text" class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1 col-md-1">备注</label>
                                    <div class="col-xs-11 col-md-11">
                                        <textarea class="form-control" name="stu.memo" rows="3"
                                                  va>${(stuint.student)!}</textarea>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>
<#--

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
                            </div><!-- col-sm-4 &ndash;&gt;
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
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">学生状态</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.state">
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
                            </div><!-- col-sm-4 &ndash;&gt;
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
                            </div><!-- col-sm-4 &ndash;&gt;
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
                            </div><!-- col-sm-4 &ndash;&gt;
                        </div>

                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>证件类型</label>
                                    <div class="col-xs-9">

                                    <#if idkind??>
                                        <select name="stu.idkind">
                                            <option value="">请选择</option>
                                            <#list idkind as idkind>
                                                <option value="${(idkind.pkSysDictValues)!}"
                                                        <#if idkind.pkSysDictValues?? && student.idKind ?? && student.idKind==idkind.pkSysDictValues>selected="selected"</#if>>${(idkind.caption)!}</option>
                                            </#list>
                                        </select>
                                    </#if>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>证件编号</label>
                                    <div class="col-xs-9">
                                        <input name="stu.idCard" value="${(student.idCard)!}" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">证件地址</label>
                                    <div class="col-xs-9">
                                        <input name="stu.idAddress" value="${(student.idAddress)!}"
                                               type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>居住地址</label>
                                    <div class="col-xs-9"><input name="stu.address"
                                                                 value="${(student.address)!}"
                                                                 type="text"
                                                                 class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学籍</label>
                                    <div class="col-xs-9">
                                        <input name="stu.schoolrool" value="${(student.schoolrool)!}"
                                               type="number"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
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
                                    &lt;#&ndash; <input name="stu.healthyBody" value="${(student.healthyBody)!}" type="text"
                                            class="form-control">&ndash;&gt;
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

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
                                    &lt;#&ndash; <select name="stu.greenCard">
                                         <option value=2
                                                 <#if student.greenCard ?? && student.greenCard ==2>selected="selected"</#if>>
                                             无
                                         </option>
                                         <option value=1
                                                 <#if student.greenCard ?? && student.greenCard ==1>selected="selected"</#if>>
                                             有
                                         </option>
                                     </select>&ndash;&gt;
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
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
                                    &lt;#&ndash; <input name="stu.greenCardCountry"
                                                              value="${(student.greenCardCountry)!}"
                                                              type="text" class="form-control">&ndash;&gt;
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

                        </div>
                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">邮编</label>
                                    <div class="col-xs-9"><input name="stu.zip" value="${(student.zip)!}" type="text" class="form-control" maxlength="6">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">邮箱</label>
                                    <div class="col-xs-9"><input name="stu.email" value="${(student.email)!}"
                                                                 type="text"
                                                                 class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

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

                                    &lt;#&ndash;<select name="stu.isminority">
                                        <option value=2
                                                <#if student.isminority?? && student.isminority ==2>selected="selected"</#if>>
                                            否
                                        </option>
                                        <option value=1
                                                <#if student.isminority?? && student.isminority ==1>selected="selected"</#if>>
                                            是
                                        </option>
                                    </select>&ndash;&gt;
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
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
                                    &lt;#&ndash; <input name="stu.nation" value="${(student.nation)!}" type="text"
                                            class="form-control">&ndash;&gt;
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">原就读学校</label>
                                    <div class="col-xs-9">
                                        <input name="stu.oldSclool" value="${(student.oldSclool)!}" type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

                        </div>
                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">特长</label>
                                    <div class="col-xs-9"><input name="stu.hobby" value="${(student.hobby)!}"
                                                                 type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">原籍</label>
                                    <div class="col-xs-9">
                                        <input name="stu.oldPlace" value="${(student.oldPlace)!}" type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">毕业去向</label>
                                    <div class="col-xs-9">
                                        <input name="stu.graduationGo" value="${(student.graduationGo)!}" type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                        </div>
                        <div class="row">

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
                                    &lt;#&ndash; <input name="stu.mentalHealth" value="${(student.mentalHealth)!}" type="text"
                                            class="form-control">&ndash;&gt;
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">现状分析</label>
                                    <div class="col-xs-9"><input name="stu.situationAnalysis"
                                                                 value="${(student.situationAnalysis)!}"
                                                                 type="text"
                                                                 class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学生梦想</label>
                                    <div class="col-xs-9">
                                        <input name="stu.dream" value="${(student.dream)!}" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">家长期许</label>
                                    <div class="col-xs-9">
                                        <input name="stu.parentsExpect" value="${(student.parentsExpect)!}" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">推荐信</label>
                                    <div class="col-xs-9"><input name="stu.letter" value="${(student.letter)!}"
                                                                 type="text"
                                                                 class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学业计划</label>
                                    <div class="col-xs-9"><input name="stu.planning" value="${(student.planning)!}"
                                                                 type="text"
                                                                 class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;


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


                                    &lt;#&ndash;<select name="stu.isreligion">
                                        <option value="2"
                                                <#if student.isreligion??&& student.isreligion==2>selected="selected"</#if>>
                                            无
                                        </option>
                                        <option value="1
"
                                                <#if student.isreligion??&& student.isreligion==2>selected="selected"</#if>>有
                                        </option>
                                    </select>&ndash;&gt;

                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">宗教名称</label>
                                    <div class="col-xs-9"><input name="stu.religion" value="${(student.religion)!}"
                                                                 type="text"
                                                                 class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">家长反馈</label>
                                    <div class="col-xs-9"><input name="stu.parentFeedback"
                                                                 value="${(student.parentFeedback)!}"
                                                                 type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-1">老师评价</label>
                                    <div class="col-xs-9 col-md-11"><input name="stu.teacherAppraisal"
                                                                           value="${(student.teacherAppraisal)!}"
                                                                           type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;


                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1 col-md-1">备注</label>
                                    <div class="col-xs-11 col-md-11">
                                    &lt;#&ndash;<input name="stu.memo" value="${(student.memo)!}" type="text" class="form-control">&ndash;&gt;
                                        <textarea class="form-control" name="stu.memo" rows="3"
                                                  va>${(stuint.student)!}</textarea>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 &ndash;&gt;
                        </div><!-- col-sm-4 &ndash;&gt;

-->


                </div><!-- tab-content -->


            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->

</form>

<#include "${staticPath}/commons/footer.ftl" >

<script type="text/javascript">

    function clear(str) {
        str = str.replace(/,/g, "");//取消字符串中出现的所有逗号
        return str;
    }

    $('.form-ajax').bootstrapValidator().on('success.form.bv', function (e) {
// Prevent form submission
        e.preventDefault();

// Get the form instance
        var $form = $(e.target);

// Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

// Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function (data) {

            if (data.code == 0) {
                Notify.success(data.message);
                if ($form.data('target')) {
                    setTimeout("window.location.href='" + $form.data('target') + "'", 1);
                }
            } else {
                Notify.danger(data.message);
            }
        }, 'json');
    });



</script>