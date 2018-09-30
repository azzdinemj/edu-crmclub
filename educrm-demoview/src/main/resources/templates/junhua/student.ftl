<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<form class="look form-ajax" method="POST" id="formId" action=""
      data-target=""  >
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>学员档案 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
            <button class="btn btn-sm btn-newblue" type="submit">保存</button>
            <a class="btn btn-sm btn-newblue" data-toggle="modal"  data-target="#modal" data-url="">退费</a>
            <a class="btn btn-sm btn-newblue" href="">面试</a>
            <a class="btn btn-sm btn-newblue" href="">报名</a>

        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">

                <li class="active"><a data-toggle="tab" href="#all"><strong>学生信息</strong></a></li>
                <li><a data-toggle="tab" href="#hobby"><strong>特长爱好</strong></a></li>
                <li><a data-toggle="tab" href="#plans"><strong>学生计划</strong></a></li>
                <li class=""><a data-toggle="tab" href="#added"><strong>访谈记录</strong></a></li>
                <li class=""><a data-toggle="tab" href="#discipline"><strong>违纪记录</strong></a></li>
                <li class=""><a data-toggle="tab" href="#assigned"><strong>教育经历</strong></a></li>
                <li class=""><a data-toggle="tab" href="#unresolved"><strong>活动经历</strong></a></li>
                <li class=""><a data-toggle="tab" href="#sampleReels"><strong>作品集</strong></a></li>
                <li class=""><a data-toggle="tab" href="#prize"><strong>获奖经历</strong></a></li>
                <li class=""><a data-toggle="tab" href="#transcript"><strong>成绩单</strong></a></li>
                <li class=""><a data-toggle="tab" href="#prize"><strong>考级记录</strong></a></li>
                <li class=""><a data-toggle="tab" href="#interview"><strong>活动记录</strong></a></li>
            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">

                    <#--<h3>学生信息</h3>-->
                        <input id="pkStudent" name="stu.pkStudent"  type="hidden">
                        <input name="stu.creator"  type="hidden">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">校区</label>
                                    <div class="col-xs-9 col-md-9">
                                       <select>
                                           <option>唐山路南校区</option>
                                           <option>唐山路北校区</option>
                                           <option>唐山开平校区</option>
                                       </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">编号</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" readonly name="stu.code"
                                               type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">别名</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" name="stu.shortCaption"
                                               type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学号</label>
                                    <div class="col-xs-9"><input name="stu.studentId"
                                                                 type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">分类</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.studentClass">
                                            <option value="">请选择</option>
                                        </select>
                                    <#--<input class="form-control" name="stu.studentClass"
                                           value="${(student.studentClass)!}"
                                           type="text">-->

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>姓名</label>
                                    <div class="col-xs-9">
                                        <input class="form-control" id="txtChinese" name="stu.caption" onblur="getmakePy()" value="${(student.caption)!}"
                                               type="text">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>性别</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.sex" value="1" class="deldis"
                                                  id="radio11" type="radio">
                                           <label for="radio11">男</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.sex" value="0" id="radio12" class="deldis"
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
                                        <input class="form-control" name="stu.shortCode" id="shortCodeId" value=""
                                               type="text">

                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="student-img upload text-center">
                                    <input type="file" name="file" id="doc" style="width:80px;"
                                           onchange="ajaxSubmitForm()" />
                                    <div id="dd">
                                            <img src="${staticPath}/images/photos/icon-h.jpg">
                                    </div>
                                    <p class="gray">上传头像</p>
                                    <input type="hidden" name="stu.img" id="studentImg"
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>出生日期</label>
                                    <div class="col-xs-9">
                                        <input name="stu.birthdayTime"
                                                id="data" type="text"
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
                                                意向学生
                                            </option>
                                            <option value=1
                                                正式学生
                                            </option>
                                            <option value=2
                                                无效学生
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">学生状态</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="stu.state">
                                            <option value=0
                                                转出
                                            </option>
                                            <option value=1
                                                在校
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">个人电话</label>
                                    <div class="col-xs-9">
                                        <input name="stu.phone"  type="text" class="form-control"
                                               data-bv-phone="true" data-bv-phone-country="ZHCN" data-bv-phone-message="请输入正确手机号" >
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>年级</label>
                                    <div class="col-xs-9">
                                        <select name="stu.grade">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>是否住宿</label>
                                    <div class="col-xs-9">

                                         <span class="rdio rdio-warning">
                                           <input name="stu.isboarding" value="1" class="deldis"
                                                  id="radio1" type="radio">
                                           <label disabled="disabled" for="radio1">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isboarding" value="0" id="radio2" class="deldis"
                                                  type="radio">
                                           <label for="radio2">否</label>
                                       </span>
                                    <#-- <select name="stu.isboarding">
                                         <option value=0
                                                 <#if student.isboarding ?? && student.isboarding==0>selected="selected"</#if>>
                                             否
                                         </option>
                                         <option value=1
                                                 <#if student.isboarding ?? && student.isboarding==1>selected="selected"</#if>>
                                             是
                                         </option>
                                     </select>-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>

                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>证件类型</label>
                                    <div class="col-xs-9">

                                        <select name="stu.idkind">
                                            <option value="">请选择</option>

                                        </select>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>证件编号</label>
                                    <div class="col-xs-9">
                                        <input name="stu.idCard"  type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">证件到期</label>
                                    <div class="col-xs-9">
                                        <input name="stu.passportDateTime"
                                               class="form-control js-datepicker">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">证件地址</label>
                                    <div class="col-xs-9">
                                        <input name="stu.idAddress"
                                               type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><i class="red">*</i>居住地址</label>
                                    <div class="col-xs-9"><input name="stu.address"
                                                                 type="text"
                                                                 class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">乘坐校车</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.isByschoolbus" value="1" class="deldis"
                                                  id="radio13" type="radio">
                                           <label disabled="disabled" for="radio13">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isByschoolbus" value="0" id="radio14" class="deldis"
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
                                    <label class="control-label col-xs-3">有无学籍</label>
                                    <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="stu.isSchoolrool" value="1" class="deldis"
                                                  id="radio15" type="radio">
                                           <label disabled="disabled" for="radio15">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isSchoolrool" value="0" id="radio16" class="deldis"
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
                                        <input name="stu.schoolrool"
                                               type="number"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学籍地址</label>
                                    <div class="col-xs-9"><input name="stu.schoolroolAddress"
                                                                 type="text"
                                                                 class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">入学情况</label>
                                    <div class="col-xs-9"><input name="stu.inState" value=""
                                                                 type="text"
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
                                        </select>
                                    <#-- <input name="stu.tracksource" value="${(student.tracksource)!}"
                                                              type="text"
                                                              class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">所属人</label>
                                    <div class="col-xs-9">
                                        <input readonly id="empcapid"  class="form-control" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=1">
                                        <input type="hidden" id="pkempid" name="stu.pkSysUser"  class="form-control" >
                                        <#--<input readonly value="${(employee.caption)!}" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?pkStudent=${(student.pkStudent)!}&empkey=${(empkey)!}" class="form-control">-->
                                        <#--<input type="hidden" name="stu.pkSysUser" value="${(employee.pkEmployee)!}"  class="form-control" >-->
                                        <#--<select name="stu.pkSysUser">-->
                                            <#--<option value="">请选择</option>-->
                                        <#--<#if employees ??>-->
                                            <#--<#list employees as em>-->
                                                <#--<option value="${(em.pkEmployee)!}"-->
                                                        <#--<#if em.pkEmployee?? && student.pkSysUser ?? && student.pkSysUser ==em.pkEmployee>selected</#if>>${(em.caption)!}</option>-->
                                            <#--</#list>-->

                                        <#--</#if>-->
                                        <#--</select>-->
                                    <#-- <input name="stu.tracksource" value="${(student.tracksource)!}"
                                                              type="text"
                                                              class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>
                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">国籍</label>
                                    <div class="col-xs-9">
                                        <select name="stu.nationality">
                                            <option value="">请选择</option>
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
                                        <input name="stu.oldPlace"  type="text"
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
                                                  id="radio7" type="radio">
                                           <label disabled="disabled" for="radio7">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.greenCard" value="0" id="radio8" class="deldis"
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
                                        </select>
                                    <#-- <input name="stu.greenCardCountry"
                                                              value="${(student.greenCardCountry)!}"
                                                              type="text" class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">

                                <div class="form-group">
                                    <label class="control-label col-xs-3">可供留学费用</label>
                                    <div class="col-xs-9">
                                    <#--  <input value="val..replace(/,/g,''); " onkeyup="this.value=this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3')">-->
                                        <input name="stu.studyAbroadFee" id="studyAbroadFee"
                                               class="form-control"
                                               onkeypress="if((event.keyCode<48 || event.keyCode>57) &amp; event.keyCode!=46 || /\.\d\d$/.test(value))event.returnValue=false">
                                    <#--<input name="stu.studyAbroadFee" value="${(student.studyAbroadFee)!}" class="form-control" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">-->
                                    </div>
                                </div>


                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">邮编</label>
                                    <div class="col-xs-9"><input name="stu.zip"  type="text" class="form-control" maxlength="6">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">邮箱</label>
                                    <div class="col-xs-9"><input name="stu.email"
                                                                 type="text"
                                                                 class="form-control"></div>
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
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isminority" value="0" id="radio4" class="deldis"
                                                  type="radio">
                                           <label for="radio4">否</label>
                                       </span>

                                    <#--<select name="stu.isminority">
                                        <option value=2
                                                <#if student.isminority?? && student.isminority ==2>selected="selected"</#if>>
                                            否
                                        </option>
                                        <option value=1
                                                <#if student.isminority?? && student.isminority ==1>selected="selected"</#if>>
                                            是
                                        </option>
                                    </select>-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">民族</label>
                                    <div class="col-xs-9">
                                        <select name="stu.nation">
                                            <option value="">请选择</option>
                                        </select>
                                    <#-- <input name="stu.nation" value="${(student.nation)!}" type="text"
                                            class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">原就读学校</label>
                                    <div class="col-xs-9">
                                        <input name="stu.oldSclool" type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>

                        <div class="row">

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">心里健康</label>
                                    <div class="col-xs-9">
                                        <select name="stu.mentalHealth">
                                            <option value="">请选择</option>

                                        </select>
                                    <#-- <input name="stu.mentalHealth" value="${(student.mentalHealth)!}" type="text"
                                            class="form-control">-->
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">现状分析</label>
                                    <div class="col-xs-9"><input name="stu.situationAnalysis"
                                                                 type="text"
                                                                 class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">学生梦想</label>
                                    <div class="col-xs-9">
                                        <input name="stu.dream"  type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">家长期许</label>
                                    <div class="col-xs-9">
                                        <input name="stu.parentsExpect"  type="text"
                                               class="form-control">
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">家长反馈</label>
                                    <div class="col-xs-9"><input name="stu.parentFeedback"
                                                                 type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">毕业去向</label>
                                    <div class="col-xs-9">
                                        <input name="stu.graduationGo"  type="text"
                                               class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">宗教信仰</label>
                                    <div class="col-xs-9">
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isreligion" value="1" class="deldis"
                                                  id="radio5" type="radio">
                                           <label disabled="disabled" for="radio5">是</label>
                                       </span>
                                        <span class="rdio rdio-warning">
                                           <input name="stu.isreligion" value="0" id="radio6" class="deldis"
                                                  type="radio">
                                           <label for="radio6">否</label>
                                       </span>


                                    <#--<select name="stu.isreligion">
                                        <option value="2"
                                                <#if student.isreligion??&& student.isreligion==2>selected="selected"</#if>>
                                            无
                                        </option>
                                        <option value="1
"
                                                <#if student.isreligion??&& student.isreligion==2>selected="selected"</#if>>有
                                        </option>
                                    </select>-->

                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">宗教名称</label>
                                    <div class="col-xs-9"><input name="stu.religion" value=""
                                                                 type="text"
                                                                 class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->

                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-1">老师评价</label>
                                    <div class="col-xs-9 col-md-11"><input name="stu.teacherAppraisal"
                                                                           value=""
                                                                           type="text" class="form-control"></div>
                                </div>
                            </div><!-- col-sm-4 -->


                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1 col-md-1">备注</label>
                                    <div class="col-xs-11 col-md-11">
                                    <#--<input name="stu.memo" value="${(student.memo)!}" type="text" class="form-control">-->
                                        <textarea class="form-control" name="stu.memo" rows="3"
                                                  va></textarea>
                                    </div>
                                </div>
                            </div><!-- col-sm-4 -->
                        </div><!-- col-sm-4 -->

                        <div class="col-sm-12">
                            <h3>家庭信息</h3>
                        </div>
                        <div>
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

                                    <tr>
                                        <td align="left">
                                            <input
                                                   type="text"
                                                   class="form-control bor0">
                                            <input
                                                   type="hidden">
                                            <input
                                                   type="hidden">
                                            <input
                                                   type="hidden">
                                            <input  type="hidden">
                                            <input   type="hidden">
                                        </td>
                                        <td align="left">
                                            <input  readonly
                                                   type="text" class="form-control bor0">
                                        </td>
                                        <td>
                                        <#-- <input name="man.sex" readonly value=" ${(man.sex)!}" type="text" class="form-control bor0">-->
                                            <select  class="form-control bor0">
                                                <option value="1"
                                                       男
                                                </option>
                                                <option value="2"
                                                      女
                                                </option>
                                            </select>
                                        </td>
                                        <td align="left">
                                            <input  readonly

                                                   type="text" class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input  readonly
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input  readonly
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input  readonly
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input  readonly
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                        <td align="left">
                                            <input  readonly
                                                   type="text"
                                                   class="form-control bor0">
                                        </td>
                                    </tr>
                                        <#assign index = index+1> <!--list循环一次，变量+1-->

                                </#if>

                                </tbody>
                            </table>
                        </div>


                </div>
                </div>

                <!---->活动记录
            <#--<#if interviewkey?? && interviewkey==2 >-->
                <div id="hobby" class="tab-pane">
                    <div class="col-sm-12">

                        <table class="table bor1 table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>段位</th>

                                <th>班级</th>
                                <th>教练</th>
                                <th>活动名称</th>
                                <th>活动时间</th>
                                <th>获得奖项</th>

                            </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td>李小龙</td>
                                    <td>黑带</td>
                                    <td>高级班</td>
                                    <td>叶问</td>
                                    <td>第二届WTEF跆拳道大赛</td>
                                    <td>2017-04-01</td>
                                    <td>金牌</td>

                                </tr>

                                <tr>
                                    <td>李小龙</td>
                                    <td>黑带</td>
                                    <td>高级班</td>
                                    <td>叶问</td>
                                    <td>第二届WTEF跆拳道大赛</td>
                                    <td>2017-04-01</td>
                                    <td>金牌</td>

                                </tr>

                                <tr>
                                    <td>李小龙</td>
                                    <td>黑带</td>
                                    <td>高级班</td>
                                    <td>叶问</td>
                                    <td>第二届WTEF跆拳道大赛</td>
                                    <td>2017-04-01</td>
                                    <td>金牌</td>

                                </tr>

                                <tr>
                                    <td>李小龙</td>
                                    <td>黑带</td>
                                    <td>高级班</td>
                                    <td>叶问</td>
                                    <td>第二届WTEF跆拳道大赛</td>
                                    <td>2017-04-01</td>
                                    <td>金牌</td>

                                </tr>

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
                                       data-url="/student/studentPlans/create?pkStudent">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>

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
                                       data-url="/student/studentEduExperience/create?pkStudent">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div><!-- tab-pane -->

                <!--考试记录-->
                <div id="prize" class="tab-pane">
                    <div class="col-sm-12">
                        <table class="table bor1 table-color table-hover mb30">
                            <thead>
                            <tr>
                                <th>姓名</th>
                                <th>段位</th>
                                <th>班级</th>
                                <th>教练</th>
                                <th>考试时间</th>
                                <th>结果</th>

                                <#--<th>-->
                                    <#--<a data-toggle="modal" data-target="#modal"-->
                                       <#--data-url="/student/studentAwards/create?pkStudent=${(student.pkStudent)!}">-->
                                        <#--<i class="fa fa-plus add_icon"></i>-->
                                    <#--</a>-->
                                <#--</th>-->

                            </tr>
                            </thead>
                            <tbody>
                            <#--<#if studentAwards??>-->
                                <#--<#list  studentAwards as awards>-->
                                <tr>
                                    <td>李小龙</td>
                                    <td>黑带</td>
                                    <td>高级班</td>
                                    <td>叶问</td>
                                    <td>2018-04-01</td>
                                    <td>合格</td>
                                </tr>

                            <tr>
                                <td>李小龙</td>
                                <td>绿带</td>
                                <td>高级版</td>
                                <td>叶问</td>
                                <td>2018-04-01</td>
                                <td>合格</td>
                            </tr>

                            <tr>
                                <td>李小龙</td>
                                <td>红带</td>
                                <td>高级班</td>
                                <td>叶问</td>
                                <td>2018-04-01</td>
                                <td>合格</td>
                            </tr>

                            <tr>
                                <td>李小龙</td>
                                <td>蓝带</td>
                                <td>高级班</td>
                                <td>叶问</td>
                                <td>2018-04-01</td>
                                <td>合格</td>
                            </tr>
                                <#--</#list>-->
                            <#--</#if>-->
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
                                       data-url="/student/studentWorksPortfolio/create?pkStudent">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>

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
                                       data-url="/student/studentSpecialty/create?pkStudent">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td align="left">
                                        <input name="spe.specialty" readonly  type="text"
                                               class="form-control bor0">

                                    </td>
                                    <td align="left">
                                        <input name="spe.studyTime" readonly  type="text"
                                               class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.awardsAchievements" readonly

                                               type="text" class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.creator" readonly  type="text"
                                               class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.creationDate" readonly
                                               type="text"
                                               class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.modifier" readonly  type="text"
                                               class="form-control bor0">
                                    </td>
                                    <td align="left">
                                        <input name="spe.lasteditDate" readonly
                                               type="text"
                                               class="form-control bor0">
                                    </td>
                                </tr>

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
                                       data-url="/student/studentActivityExp/create?pkStudent">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
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
                                       data-url="/student/studentInterviewRecord/create?pkStudent">
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
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
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
                                       data-url="/student/studentBehaviorRecord/create?pkStudent">
                                        <i class="fa fa-plus add_icon"></i>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
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
                                       data-url="/student/studentTestPlansScores/create?pkStudent">
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
</script>