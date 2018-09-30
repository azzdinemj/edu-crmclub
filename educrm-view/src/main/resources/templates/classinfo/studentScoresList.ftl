
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>成绩输入 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <!--data-toggle="modal" data-target="#myModal" data-url=""-->
                        <a class="btn btn-sm btn-success" onclick="saveAll()">保存</a>
                        <#--<a class="btn btn-sm btn-danger" >删除</a>-->

                        <!--     <button title="更多操作" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="" data-toggle="modal">删除</a></li>
                        <li class="divider"></li>
                    </ul>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <form id="formId" class="look form-ajax" method="POST" id="formId" action="/classinfo/studentScoress/saveAll"
                              data-target="/classinfo/classinfo/query" >
                        <table id="table" class="table table-color scores" cellspacing="0">
                            <thead>
                            <tr>
                                <td>学生编号</td>
                                <td>学生姓名</td>
                                <td width="150">课程</td>
                                <td>考试成绩</td>
                                <#--<td>老师</td>-->
                                <td>班级</td>
                                <td>考试类型</td>
                                <td>考试名称</td>
                                <td>权重</td>
                                <td>年份</td>
                                <td>学期</td>
                                <td>备注</td>
                            </tr>
                            </thead>
                            <tbody id="parentTab">
                            <#if list??>
                                <#assign  index = 0 >
                                <#list list as v>
                                <tr>
                                    <td>
                                        <input name="scores[${index}].code" readonly value=" ${(v.studentEntity.code)!}" type="text"class="form-control bor0">
                                    </td>
                                    <td>
                                        <input name="scores[${index}].caption" readonly value="${(v.studentEntity.caption)!}" class="form-control bor0">
                                        <input name="scores[${index}].pkStudent" value="${(v.studentEntity.id)!}" type="hidden">
                                    </td>
                                    <td>
                                        <#--<input name="man[${index}].pkCource" value="${(v.studentScoreEntity.pkCource)!}" class="form-control bor0">-->
                                        <select name="scores[${index}].pkCource" class="form-control bor0 selectpicker show-tick" data-live-search="true" >
                                            <option value="">请选择课程</option>
                                            <#if discipline??>
                                                <#list discipline as d>
                                                    <option value="${(d.pkSysDictValues)!}" >${(d.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </td>
                                    <td>
                                        <input name="scores[${index}].scores" value="" type="text" class="form-control bor0">
                                    </td>
                                    <#--<td>-->
                                        <#--<input readonly value="${(v.employeeEntity.caption)!}" class="form-control bor0">-->
                                        <#--<input name="scores[${index}].pkEmployee" value="${(v.employeeEntity.id)!}" type="hidden" class="form-control bor0">-->
                                    <#--</td>-->
                                    <td>
                                        <input readonly value="${(v.classInfoEntity.caption)!}" class="form-control bor0" >
                                        <input name="scores[${index}].pkClassinfo" value="${(v.classInfoEntity.id)!}" type="hidden" >
                                    </td>
                                    <td>
                                        <#--<input name="man[${index}].pkPlans" value="${(v.studentScoreEntity.pkPlans)!}" class="form-control bor0">-->
                                        <select name="scores[${index}].pkPlans" class="form-control bor0">
                                            <option value="">请选择</option>
                                            <#if testplans ??>
                                                <#list testplans as t>
                                                    <option value="${(t.pkSysDictValues)!}">${(t.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </td>
                                    <td>
                                        <input name="scores[${index}].pkStudentTestPlans"  type="text" class="form-control bor0">
                                    </td>

                                    <td>
                                        <input name="scores[${index}].weight"  value=" ${(v.studentScoreEntity.weight)!}" type="text" class="form-control bor0">
                                    </td>
                                    <td>
                                        <input name="scores[${index}].yearTime"  type="text" class="form-control bor0 js-datepickerYYYY">
                                    </td>
                                    <td>
                                        <#--<input name="man[${index}].term" value=" ${(v.studentScoreEntity.term)!}" type="text" class="form-control bor0">-->
                                        <select name="scores[${index}].term" class="form-control bor0">
                                            <option value="">请选择</option>
                                            <#if term ??>
                                            <#list term as m>
                                                <option value="${(m.pkSysDictValues)!}">${(m.caption)!}</option>
                                            </#list>

                                            </#if>
                                        </select>
                                    </td>
                                    <td>
                                        <input name="scores[${index}].memo"  value=" ${(v.studentScoreEntity.memo)!}" type="text" class="form-control bor0">
                                    </td>
                                </tr>
                                    <#assign index = index+1> <!--list循环一次，变量+1-->
                                </#list>
                            </#if>
                            </tbody>
                        </table> 
                        </form>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


        <#include "../commons/footer.ftl"/>

<script type="text/javascript">
    function saveAll() {
        $("#formId").submit();
    }
</script>