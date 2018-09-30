<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />


<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>${(classinfo.caption)!} <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <#--<a class="btn btn-sm btn-newblue" onclick="save()">提交</a>-->
                <a class="btn btn-sm btn-newblue" onclick="save()">保存</a>
                <#if classinfo.status?? && classinfo.status == 0>
                <a class="btn btn-sm btn-newblue" onclick="updateStatus('${(classinfo.pkClassinfo)!}',1)">上架</a>
                <#else>
                <a class="btn btn-sm btn-newblue" onclick="updateStatus('${(classinfo.pkClassinfo)!}',0)">下架</a>
                </#if>

            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-yellow">
                <li class="active"><a data-toggle="tab" href="#all"><strong>班级信息</strong></a></li>
                    <li class=""><a data-toggle="tab" href="#teacher"><strong>课程信息</strong></a></li>

            </ul>
            <form class="look form-horizontal no-margin form-ajax" id="formId" method="post" action="/classinfo/electiveCourse/saveElective"
                  data-target="/classinfo/electiveCourse/query">
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
                                        <input name="clas.dateTime" value="${(classinfo.date?string("yyyy-MM-dd hh:mm:ss"))!}" id="data" type="text" title="" class="form-control " value=""/>
                                    </div>
                                </div>
                            </div>


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
                                        <input   type="hidden" id="doubleGradeId" value="${(classinfo.doubleGrade)!}" />
                                        <select name="clas.doubleGrade" id="selectgtadeId" class="selectpicker show-tick form-control" multiple data-live-search="true">
                                            <#if grade ??>
                                                <#list grade as g>
                                                    <option value="${(g.pkDivisionGrade)!}"  >${(g.gradeName)!}</option>
                                                </#list>
                                            </#if>
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
                                    <label class="control-label col-xs-3 col-md-3">选修课类型</label>
                                    <div class="col-xs-9 col-md-9">
                                        <select name="clas.isPay">
                                            <option value="0" <#if classinfo.fee?? && classinfo.fee==0>selected</#if>>普通课程</option>
                                            <option value="1" <#if classinfo.fee?? && classinfo.fee==1>selected</#if>>精品课程</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">费用</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input class="form-control" name="clas.fee" value="${(classinfo.fee?c)}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" type="text">
                                    <#--<input class="form-control" name="clas.fee" value="" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" type="text">-->
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


                    <div id="teacher" class="tab-pane">
                        <table id="pagingTable" class="table table-bordered table-optional" cellspacing="0">
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
    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = {"type":"2","dateTime": $("#datas").val(),"caption":$("#caption").val(),pkStudent:$("#classId").val()};
        init(GetTableColumn("individualSchedul"),"/schedule/schedule/queryByPaging",data,"pagingTable");
    }

    function renderCaption(data, type, row){
        return '<input name="checkbox" type="checkbox" value="'+data+'" pkClassinfo="'+row.pkClassinfo+'" class="px">';
    }

    function updateStatus(id,status){
        $.ajax({
            type: "POST",
            url: "/classinfo/classinfo/updateStatus",
            data: {"pkClassinfo":id,"status":status},
            traditional: true,
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    window.location.href="/classinfo/electiveCourse/query";
                } else {
                    Notify.danger(data.message);
                    window.location.reload();
                }
            },
            error: function () {

            }
        });
    }

function save() {
    $("#formId").submit();
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

    $(function(){
        var gradeStr =$("#doubleGradeId").val();
        var   mySelect = document.getElementById("selectgtadeId");
        if(gradeStr != null || !gradeStr.equals("") ){

            var arr = gradeStr.split(",");
            if (arr.length>0){
                for (var i =0; i<arr.length;i++){
                    $.each(mySelect,function (j) {
                        if (mySelect.options[j].value ==arr[i]){
                            mySelect.options[j].selected=true;//添加选中状态
                        }
                    });
                }
            }
//            for(var i = 0;i<job.length;i++){
//                if(job.charAt(i)=="1"){
//                    mySelect.options[i].selected=true;//添加选中状态
//                }
//
//            }
        }

    });

</script>