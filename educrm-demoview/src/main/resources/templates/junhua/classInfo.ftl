<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />


<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>新建班级<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                    <a class="btn btn-sm btn-newblue" onclick="sub()">保存 </a>
                    <a class="btn btn-sm  btn-newblue" onclick="subissubmit()">提交</a>
                    <a class="btn btn-sm  btn-newblue" onclick="subisaudit()">审核</a>

                    <a class="btn btn-sm  btn-newblue" href=""> 班级升班</a>
                    <a class="btn btn-sm  btn-newblue" id="stuinclass" data-toggle="modal" data-target="#modal" data-url=""> 学生转班</a>

                <a class="btn btn-sm  btn-newblue" href=""> 学生报名</a>
                <a class="btn btn-sm  btn-newblue" href=""> 退费</a>
                <#--<a class="btn btn-sm  btn-newblue activityStudent" data-types="1"> 实践评分</a>-->
                <#--<a class="btn btn-sm  btn-newblue activityStudent" data-types="2"> 老师评语</a>-->
                <a class="btn btn-sm btn-newblue activityStudent" data-toggle="modal"  data-target="#modal" data-types="1" data-url="" >实践评分</a>
                <a class="btn btn-sm btn-newblue activityStudent" data-toggle="modal"  data-target="#modal" data-types="2" data-url="" >老师评语</a>

            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-yellow">
                <li class="active"><a data-toggle="tab" href="#all"><strong>班级信息</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#assigned"><strong>学生信息</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#scores"><strong>学生成绩</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#teacher"><strong>老师信息</strong></a></li>

            </ul>
            <form class="look form-horizontal no-margin form-ajax" id="formId" method="post" action=""
                  data-target="">
                <input name="clas.pkParent" value="" type="hidden">
                <div class="tab-content">
                    <div id="all" class="tab-pane active">
                        <input name="clas.pkDomain" value="" type="hidden">
                        <input name="clas.pkClassinfo" id="classId" value="" type="hidden">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">班级编号</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input title="" readonly name="clas.code" value="" class="form-control" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">班级名称</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.caption" id="classCaption" value="" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">开班日期</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.dateTime" value="" id="data" type="text" title="" class="form-control " value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">班主任</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly id="headTeachercap" value="" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=5">
                                        <input readonly name="clas.headTeacher" id="headTeacherpk" value="" type="hidden">
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
                                        <input readonly id="secondTeachercap" value="" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=6">
                                        <input readonly name="clas.secondTeacher" id="secondTeacherpk" value="" type="hidden">
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
                                        <input readonly id="directorcap" value="" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=7">
                                        <input readonly name="clas.director" id="directorpk" value="" type="hidden">
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
                                        <input name="clas.startDateTime"  type="text" title="" class="form-control js-datepicker" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">结束时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.endDateTime"  type="text" title="" class="form-control js-datepicker" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">学部</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.division">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">教室</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.classRoom">
                                        </select>
                                        <#--<input class="form-control" name="clas.classCoom" value="${(classinfo.classCoom)!}" title="" type="text">-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">周课时</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.courseTime"  title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">项目</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.program">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">年级</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.grade">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">班级类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.type">
                                            <option value="0">正式班级</option>
                                            <option value="1" >实践活动班</option>
                                            <option value="2" >社团</option>
                                            <option value="3" >选修课</option>
                                            <option value="4" >兴趣班</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-12">
                                <div class="form-group">
                                    <label class="control-label  col-xs-1 col-md-1">备注</label>
                                    <div class="col-xs-11 col-md-11">
                                        <textarea rows="2" name="clas.notes"  class="form-control" title=""></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">提交人</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly  class="form-control"  title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">提交时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly type="text" title=""  class="form-control" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">是否提交</label>

                                    <div class="col-xs-9 col-md-9 pt7">
                                       <span class="rdio rdio-success">
                                           <input name="clas.issubmit" value="1" disabled class="deldis"
                                                  id="radio1" type="radio">
                                           <label disabled="disabled" for="radio1">是</label>
                                       </span>
                                        <span class="rdio rdio-success">
                                           <input name="clas.issubmit" value="0" id="radio2" disabled class="deldis"
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
                                        <input readonly class="form-control"  title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">审核时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control"  title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">是否审核</label>

                                    <div class="col-xs-9 col-md-9 pt7">
                                       <span class="rdio rdio-success">
                                           <input name="clas.isaudit" value="1" disabled class="deldis"
                                                  id="radio3" type="radio">
                                           <label disabled="disabled" for="radio3">是</label>
                                       </span>
                                        <span class="rdio rdio-success">
                                           <input name="clas.isaudit" value="0" id="radio4" disabled class="deldis"
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
                                        <input readonly name="clas.creator" class="form-control"  title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">创建时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control"  title="" type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">修改人</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly name="clas.modifier" class="form-control"  title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">修改时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input readonly class="form-control"  title="" type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="assigned" class="tab-pane">
                        <table id="table" class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <td>编号</td>
                                <td>姓名</td>
                                <td>性别</td>
                                <td>别名</td>
                                <td>民族</td>
                                <td>学号</td>
                                <td>学生类型</td>
                                <td>实践评分</td>
                                <td>老师评价</td>


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
                            </tr>

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

                    <div id="teacher" class="tab-pane">
                        <table  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <td>编号</td>
                                <td>姓名</td>
                                <td>性别</td>
                                <td width="10%"><a data-toggle="modal"  data-target="#modal" data-url="/classinfo/classinfoEmployee/studentTeacherAdd?pkClassinfo"><i class="fa fa-plus add_icon"></i></a>
                                </td>
                            </tr>
                            </thead>

                            <tbody>

                                <tr >

                                    <td></td>
                                    <td>

                                    </td>
                                    <td>

                                    </td>
                                    <td><i onclick="deleteEmployee()" class="fa fa-remove add_icon"></i></td>
                                </tr>


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

<#--<script type="text/javascript">-->
    <#--$(function () {-->

        <#--<#if classinfo.issubmit ?? && classinfo.issubmit ==1>-->
            <#--$(".isnone").attr("disabled", "disabled");-->
        <#--</#if>-->
        <#--<#if classinfo.isaudit??&& classinfo.isaudit==1> $(".isaudit").attr("disabled", "disabled");</#if>-->

        <#--$(".activityStudent").on("click",function(){-->

            <#--var types = $(this).data("types");-->

<#--//            var formdata = {};-->
            <#--var classesid = $("#classId").val();-->
            <#--var className = $("#classCaption").val();-->
            <#--var id = $(".table-color-").attr("dataid");-->
            <#--var studentName = $(".table-color-").attr("dataCaption");-->
            <#--var score = $(".table-color-").attr("dataScore");-->
            <#--var ev = $(".table-color-").attr("dataEv");-->
            <#--if (id == null) {-->
                <#--Notify.danger("请选择一行数据！！！");-->
                <#--return false;-->
            <#--}-->

            <#--if(types == 1) {-->
                <#--if (score != "" && score != null && score != "null") {-->
                    <#--Notify.danger("不可重复打分");-->
                    <#--return false;-->
                <#--}-->
            <#--}else {-->
                <#--if (ev != "" && ev != null) {-->
                    <#--Notify.danger("不可重复评价");-->
                    <#--return false;-->
                <#--}-->
            <#--}-->

            <#--$(this).attr("data-url",$(this).attr("data-url")+"?pkClassinfo="+classesid+"&pkStudent="+id+"&studentName="+studentName+"&className="+className);-->

<#--//            formdata.pkClassinfo = classesid;-->
<#--//            formdata.pkStudent = id;-->
<#--//            if(types == 1){-->
<#--//                formdata.score = "2";-->
<#--//            }else{-->
<#--//                formdata.evaluate = "121212"-->
<#--//            }-->
<#--//            $.ajax({-->
<#--//                url:"/classinfo/classinfo/insertActivityStudent",-->
<#--//                data:formdata,-->
<#--//                type:"POST",-->
<#--//                dataType:"json",-->
<#--//                success:function (data) {-->
<#--//                    if(data.code ==0){-->
<#--//                        location.reload();-->
<#--//                    }else {-->
<#--//                        Notify.danger(data.message);-->
<#--//                    }-->
<#--//                }-->
<#--//            });-->
        <#--})-->
    <#--});-->
<#--//    保存-->
    <#--function sub() {-->
        <#--$("#formId").submit();-->

    <#--}-->
    <#--//提交-->
    <#--function subissubmit() {-->
        <#--var formdata = $("#formId").serialize();-->
        <#--$.ajax({-->
           <#--url:"/classinfo/classinfo/submit",-->
            <#--data:formdata,-->
            <#--type:"POST",-->
            <#--dataType:"json",-->
            <#--success:function (data) {-->
                <#--if(data.code ==0){-->
                    <#--location.reload();-->
                <#--}else {-->
                    <#--Notify.danger(data.message);-->
                <#--}-->
            <#--}-->
        <#--});-->
    <#--}-->
    <#--//审核-->
    <#--function subisaudit() {-->
        <#--var formdata = $("#formId").serialize();-->
        <#--$.ajax({-->
           <#--url:"/classinfo/classinfo/audit",-->
            <#--data:formdata,-->
            <#--type:"POST",-->
            <#--dataType:"json",-->
            <#--success:function (data) {-->
                <#--if(data.code ==0){-->
                    <#--alert("成功")-->
                    <#--Notify.success(data.message);-->
                    <#--location.reload();-->
                <#--}else {-->
                    <#--Notify.danger(data.message);-->
                    <#--location.reload();-->
                <#--}-->
            <#--}-->
        <#--});-->
    <#--}-->

    <#--$("#stuinclass").on("click",function () {-->
        <#--var pkStudent = $(".table-color-").attr("dataid");-->
        <#--if(pkStudent==null || pkStudent==""){-->
            <#--Notify.danger("请先选择学生");-->
            <#--return false;-->
        <#--}-->
        <#--var oldPkClassinfo = $("#classId").val();-->
        <#--$(this).attr("data-url",$(this).attr("data-url")+"?pkStudent="+pkStudent+"&oldPkClassinfo="+oldPkClassinfo);-->
    <#--});-->

    <#--function deleteEmployee(id) {-->
        <#--if(id) {-->
            <#--var pkClassinfo = $("#classId").val();-->
            <#--var flag = confirm("确定要删除吗？");-->

            <#--if (flag) {-->
                <#--$.ajax({-->
                    <#--type: "POST",-->
                    <#--url: "/classinfo/classinfoEmployee/delete",-->
                    <#--data: {"pkClassinfo": pkClassinfo,"pkEmployee":id},-->
                    <#--dataType: "json",-->
                    <#--success: function (data) {-->
                        <#--if (data.code == 0) {-->
                            <#--Notify.success(data.message);-->
                            <#--setTimeout("location.reload()", 1);-->
                        <#--} else {-->
                            <#--Notify.danger(data.message);-->
                            <#--window.location.reload();-->
                        <#--}-->
                    <#--}-->
                <#--});-->
            <#--}-->
        <#--}-->

    <#--}-->


    <#--var columns = [-->
        <#--{"sTitle":"学生姓名","data" : "map.studentEntity.caption",-->
            <#--"render": function (data, type, row){-->
                <#--return '<a data-toggle="modal"  data-target="#modal" data-url="/classinfo/studentScoress/edit?pkStudentTestPlansScores='+row.pkStudentTestPlansScores+'" class="yellow" >'+data+'</a>';-->
            <#--}} ,-->
        <#--{"sTitle":"课程","data" : "map.sysdicEntity.caption"},-->
        <#--{"sTitle":"老师","data" : "map.userEntity.caption"},-->
        <#--{"sTitle":"考试成绩","data" : "scores"},-->
        <#--{"sTitle":"考试类型","data" : "map.testTypeEntity.caption"},-->
        <#--{"sTitle":"考试名称","data" : "map.scoreCaptionEntity.caption"},-->
        <#--{"sTitle":"权重","data" : "weight"},-->
        <#--{"sTitle":"年份","data" : "year","render": renderDate},-->
        <#--{"sTitle":"学期","data" : "map.termEntity.caption"},-->
        <#--{"sTitle":"备注","data" : "memo"}-->
    <#--];-->

    <#--$(document).ready(function() {-->
        <#--query();-->
    <#--} );-->

    <#--function query() {-->
        <#--var data  = { "pkClassinfo": $("#classId").val(),"caption":$("#caption").val()};-->
        <#--init(columns,"/classinfo/studentScoress/queryByPaging",data,"pagingTable");-->
    <#--}-->

<#--</script>-->