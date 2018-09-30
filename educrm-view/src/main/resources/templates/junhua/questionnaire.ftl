<#include "../commons/top.ftl" />

<#include "../commons/left.ftl" />

<div class="contentpanel">
            <div class="pageheader">
                <h2>问卷调查-新建</h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-success" href="" >返回</a>
                        <a class="btn btn-sm btn-default" id="addBtn" href="">保存</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">


                        <form class="form-inline search white form-ajax" method="post" action="/junhua/jhExamination/save"
                              data-target="" id="formId">
                            <input type="hidden" id="pkExamination" name="pkExamination" value="${(jhExamination.pkExamination)!}">
                            <div class="form-group">
                                <label class="control-label">问卷名称 :</label>
                                <input type="text" title=""  name="caption" id="jhExaminationCaption"  class="form-control" value="${(jhExamination.caption)!}" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">截至时间 :</label>
                                <input type="text" title="" id="datas" name="datas" class="form-control  js-datepicker" value="${(jhExamination.datas)!}"  />
                            </div>
                            <br>
                            <div class="form-group">
                                <label class="control-label">调查对象 :</label>
                                <#list objectList as o>
                                    <div class="checkbox">
                                        <label>
                                            <input value="${(o.pkSysDictValues)!}" class="updateObject" type="checkbox"  <#list split as s><#if s = o.pkSysDictValues>checked</#if></#list>>&nbsp;&nbsp;${(o.caption)!}
                                        </label>
                                    </div>
                                </#list>
                            </div>
                            <input type="hidden" id="objects" name="object">
                            <br>
                            <div class="form-group">
                                <label class="control-label">调查区间 :</label>
                                <input type="text" title="" id="section" name="section" class="form-control" value="${(jhExamination.section)!}"><span style="margin-left: -45px; position: absolute;margin-top: 3px;">年级</span>
                            </div>

                        </form>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <h3 style="margin-left: 15px;">题目</h3>
                        <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>题型</th>
                                <th>题干</th>
                                <th>操作</th>

                            </tr>
                            </thead>
                            <tbody>
                            <#if (questionList?size>0)>
                                <#assign num = 1>
                                    <#list questionList as list>
                                        <tr>
                                            <td>${(num)!}</td>
                                            <td><#if list.types == 1>多选<#else>单选</#if></td>
                                            <td>${(list.caption)!}</td>
                                            <td>
                                                <#--<a class="btn btn-sm btn-success" href="" >修改</a>-->
                                                    <#--<a class="btn btn-sm btn-danger" onclick="canshu(1,'${(list.pkQuestion)!}')" href="">删除</a>-->
                                                    <a class="btn btn-sm btn-danger" onclick="deleteQuestion()" href="/junhua/jhExamination/questionDelete?pkQuestion=${(list.pkQuestion)!}">删除</a>
                                                <#--<a style="display: none" id="questionDel${(list.pkQuestion)!}" href="/junhua/jhExamination/questionDelete?pkQuestion=${(list.pkQuestion)!}"></a>-->
                                            </td>

                                        </tr>
                                        <#assign num = num + 1>
                                    </#list>
                            </#if>
                            </tbody>
                        </table>

                        <div class="add" style="text-align: center;margin-top: 35px;">
                            <input type="hidden" id="pkExamination" value="${(jhExamination.pkExamination)!}">
                            <a data-toggle="modal" onclick="canshu(0,'')" data-target="#modal" data-url="/junhua/jhExamination/questionAdd?quesionAddType=${(quesionAddType)!}">新增题目</a>
                        </div>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->

<#include "../commons/footer.ftl"/>

<script>
    $(".updateObject").on("change",function(){
        var objects = "";
        $.each($('input:checkbox:checked'),function(){
            objects += $(this).val() + ",";
        });
        objects=objects.substr(0,objects.length-1);
        $("#objects").val(objects);
    });

    function canshu(number,id){
        var jhExaminationCaption = $("#jhExaminationCaption").val();
        var datas = $("#datas").val();
        var section = $("#section").val();
        var pkExamination = $("#pkExamination").val();

        var objects = "";
        $.each($('input:checkbox:checked'),function(){
            objects += $(this).val() + ",";
        });
        objects=objects.substr(0,objects.length-1);
        $("#objects").val(objects);

//        if(number == 0) {
            $(this).attr("data-url", $(this).attr("data-url") + "&caption=" + jhExaminationCaption + "&datas=" + datas + "&section=" + section + "&object=" + objects + "&pkExamination=" + pkExamination);
//        }
//        }else if(number == 1){
//            var ids = "#questionDel"+id;
//            $(ids).attr("href", $(ids).attr("href") + "&caption=" + jhExaminationCaption + "&datas=" + datas + "&section=" + section + "&object=" + objects);
//            $(ids).click();
//        }

    }

    function deleteQuestion(){
        var jhExaminationCaption = $("#jhExaminationCaption").val();
        var datas = $("#datas").val();
        var section = $("#section").val();

        var objects = "";
        $.each($('input:checkbox:checked'),function(){
            objects += $(this).val() + ",";
        });
        objects=objects.substr(0,objects.length-1);
        $("#objects").val(objects);

        $(this).attr("href", $(this).attr("href") + "&caption=" + jhExaminationCaption + "&datas=" + datas + "&section=" + section + "&object=" + objects);
        $(this).click();
    }

    $("#addBtn").on("click",function(){
        $("#formId").submit();
    })
</script>
