<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />


<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>学生升学 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <a class="btn btn-sm btn-newblue" onclick="sub()">升学 </a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-yellow">
                    <li class="active"><a data-toggle="tab" href="#assigned"><strong>${(classinfo.caption)!}</strong></a></li>
            </ul>
            <form class="look form-horizontal no-margin form-ajax" id="formId" method="post" action="/classinfo/classinfo/save"
                  data-target="/classinfo/classinfo/query">
                <div class="tab-content">
                    <div id="assigned" class="tab-pane active">
                        <div class="row">
                            <input class="form-control" name="clas.pkClassinfo" id="classCaption" value="${(classinfo.pkClassinfo)!}" title="" type="hidden">
                            <div class="col-md-8">
                                <div class="text-left teachtitle">
                                    <span>学部 &nbsp; : &nbsp;<i> ${(classinfo.map.divisionEntity.caption)!}</i></span>
                                    <span>年级&nbsp; : &nbsp;<i>${(classinfo.map.gradeEntity.caption)!}</i></span>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group newgroup">
                                    <label class="control-label col-xs-3 col-md-3">升学类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select id="selectId">
                                            <option value="">请选择</option>
                                            <#if signupType ??>
                                                <#list signupType as s>
                                                    <option value="${(s.pkSysDictValues)!}">${(s.caption)!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>

                        <table id="table" class="table table1-striped table-bordered" cellspacing="0">

                            <thead>
                            <tr>
                                <td>选择</td>
                                <td>编号</td>
                                <td>姓名</td>
                                <td>性别</td>
                                <td>出生日期</td>
                                <td>民族</td>
                                <td>身份证号</td>


                            </tr>
                            </thead>

                            <tbody>
                            <#if students ??>
                            <#list students as s>
                            <tr>
                                <td>
                                    <input type="checkbox" name="checkbox" value="${(s.pkStudent)!}">
                                </td>
                                <td>${(s.code)!}</td>
                                <td>
                                    ${(s.caption)!}
                                </td>
                                <td>
                                    <#if s.sex ?? && s.sex==1>男<#else >女</#if>
                                </td>
                                <td>${(s.birthday?string("yyyy-MM-dd"))!}</td>
                                <td>${(s.nationEntity.caption)!}</td>
                                <td>${(s.idCard)!}</td>
                            </tr>
                            </#list>
                            </#if>

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
//    保存
    function sub() {

        var pkSysDictValues = $("#selectId").val();
        if(pkSysDictValues == null || pkSysDictValues ==""){
            Notify.danger("请选择升学类型");
            return false;
        }

        var result = new Array();
        $.each($('input:checkbox:checked'),function(){
            result.push($(this).attr("value"));
        });
        pkStudents = JSON.stringify(result);
        if (result.length==0){
            Notify.danger("请选择学生")
            return false;
        }
        $.ajax({
            url:"/classinfo/classinfo/saveGotoSchool",
            data:{"pkClassinfo": $("#classCaption").val(),"pkStudents":pkStudents,"signupType":pkSysDictValues},
            type: "POST",
            dataType: "json",
            traditional: true,
            success:function (data) {
                if(data.code ==0){
                    Notify.success(data.message);
                    window.location.href='/classinfo/classinfo/query';
                }
            }


        });

    }



</script>
