Ser<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />


<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i><#if classinfo.caption?? >${(classinfo.caption)!}<#else >新建选修班级</#if> <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                    <a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>

            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-yellow">
                <li class="active"><a data-toggle="tab" href="#all"><strong>班级信息</strong></a></li>
            </ul>
            <form class="look form-horizontal no-margin form-ajax" id="formId" method="post" action="/classinfo/electiveCourse/saveElective"
                  data-target="/classinfo/electiveCourse/query">
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
                                        <input name="clas.dateTime" value="${(classinfo.date?string("yyyy-MM-dd hh:mm:ss"))!}" id="data" type="text" title="" class="form-control " value=""/>
                                    </div>
                                </div>
                            </div>
                            <#--<div class="col-xs-12 col-md-4">-->
                                <#--<div class="form-group">-->
                                    <#--<label class="control-label col-xs-3 col-md-3">班主任</label>-->
                                    <#--<div class="col-xs-9 col-md-9">-->
                                        <#--<input readonly id="headTeachercap" value="${(headTeacher.caption)!}" class="form-control changeemployee1" data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=5">-->
                                        <#--<input readonly name="clas.headTeacher" id="headTeacherpk" value="${(headTeacher.pkEmployee)!}" type="hidden">-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</div>-->

                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">开始时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.startDateTime" value="${(classinfo.startDate?string("yyyy-MM-dd hh:mm:ss"))!}" type="text" title="" class="form-control js-datepicker" value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">结束时间</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="clas.endDateTime" value="${(classinfo.endDate?string("yyyy-MM-dd hh:mm:ss"))!}" type="text" title="" class="form-control js-datepicker" value=""/>
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
                                        <select name="clas.doubleGrade" id="selectgtadeId" class="selectpicker show-tick form-control" multiple data-live-search="true">

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
                                    <label class="control-label col-xs-3 col-md-3">班级类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.type">
                                            <option value="3" <#if classinfo.type?? && classinfo.type==3>selected="selected"</#if>>选修课</option>
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
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">选修课类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.isPay">
                                            <option value="0">普通课程</option>
                                            <option value="1">精品课程</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">费用</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.fee" value="" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" type="text">
                                        <#--<input class="form-control" name="clas.fee" value="" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" type="text">-->
                                        <#--<input class="form-control" name="clas.fee" value="" /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/ type="text">-->
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
                    </div>
                    <div>
                        <h3>排课配置信息</h3>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">教师</label>
                            <div class="col-xs-9 col-md-9">
                                <select name="clas.teacherId">
                                    <option value="">请选择教师</option>
                                    <#if teacherList ??>
                                        <#list teacherList as t>
                                            <option value="${(t.pkEmployee)}">${(t.caption)}</option>
                                        </#list>

                                    </#if>
                                </select>
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
                </div>
            </form>
        </div>
    </div>

</div><!-- panel -->

<#include "../commons/footer.ftl"/>

<script type="text/javascript">


//    保存
    function sub() {
        $("#formId").submit();

    }

    $(document).ready(function() {
        query();
    } );

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
                            $('.selectpicker').selectpicker('refresh');
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