<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<form class="look form-ajax" method="POST" id="formId" action="/question/questionItem/save" data-target="/question/questionItem/query" >
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>添加题目<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <#--<a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>-->
                <input class="btn btn-sm btn-newblue" type="submit"  id="saveItem" value="保存">
            </div>
        </div>
    </div>

    <!--content-->
    <div class="panel panel-default pl35 zhpanel">

            <div class="form-group">
                <label class="control-label col-xs-2 col-md-2">选择知识点</label>
                <div class="col-sm-10">
                        <input type="hidden" name="questionsSubjectId" id="pkParent" value="${(questions.questionsSubjectId)!}"  class="form-control"
                           title="">
                    <input  name="questionsSubjectName" id="pkParentcaption"  value="${(questions.questionsSubjectName)!}" class="form-control" data-toggle="modal"
                            data-target="#modal" data-url="/question/questionSubject/querylist" placeholder="知识点"  >
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-2 col-md-2">选择类型</label>
                <div class="col-sm-10">
                    <select class="form-control" name="pkSysDictValues" required>
                    <#if dict??>
                        <#list dict as v >
                            <option value="${(v.pkSysDictValues)!}">${(v.caption)!}</option>
                        </#list>
                    </#if>

                    </select>
                </div>
            </div>

            <div class="form-group">
                <input type="hidden"  name="id" value="${(questions.id)!}">
                <label class="col-sm-2 control-label no-padding-top"> 题目类型:</label>

                <div class="col-sm-10">
                    <input <#if questions??&&questions.type??&&questions.type==1>checked=""</#if> type="radio" name="type" id="type" onclick="changec(1);" size="70" value="1" checked="">单选题&nbsp;&nbsp;
                    <#--<input <#if questions??&&questions.type??&&questions.type==2>checked=""</#if> type="radio" name="type" id="type" onclick="changec(2);" size="70" value="2">多选题&nbsp;&nbsp;-->
                    <input <#if questions??&&questions.type??&&questions.type==3>checked=""</#if> type="radio" name="type" id="type" onclick="changec(3);" size="70" value="3">判断题&nbsp;&nbsp;
                    <#--<input <#if questions??&&questions.type??&&questions.type==4>checked=""</#if> type="radio" name="type" id="type" onclick="changec(4);" size="70" value="4">填空题&nbsp;&nbsp;-->
                    <#--<input <#if questions??&&questions.type??&&questions.type==5>checked=""</#if> type="radio" name="type" id="type" onclick="changec(5);" size="70" value="5">问答题&nbsp;&nbsp;-->
                    <#--<input <#if questions??&&questions.type??&&questions.type==6>checked=""</#if> type="radio" name="type" id="type" onclick="changec(6);" size="70" value="6">题帽题-->
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-top"> 题干:</label>

                <div class="col-sm-10">
                    <textarea id="title" class="validate[required]" style="width:100%;height:200px" name="title" placeholder="用户昵称" data-bv-notempty="" data-bv-notempty-message="请录入您的昵称">
                        ${(questions.title)!}
                    </textarea>
                    <span id="titleMsg" style="color: red"> </span>
                </div>
            </div>

            <div id="co_1">
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 选项数量：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <select name="partnum1" >
                                <#--<option <#if questions??&&questions.partnum==2>selected="selected"</#if> value="2">2项</option>-->
                                <#--<option <#if questions??&&questions.partnum==3>selected="selected"</#if> value="3"> 3项 </option>-->
                                <option <#if questions??&&questions.partnum==4>selected="selected"</#if> value="4" selected="selected" > 4项 </option>
                                <option <#if questions??&&questions.partnum==5>selected="selected"</#if> value="5"> 5项 </option>
                                <option <#if questions??&&questions.partnum==6>selected="selected"</#if> value="6"> 6项 </option>
                                <option <#if questions??&&questions.partnum==7>selected="selected"</#if> value="7"> 7项 </option>
                                <#--<option <#if questions??&&questions.partnum==8>selected="selected"</#if> value="8"> 8项 </option>-->
                                <#--<option <#if questions??&&questions.partnum==9>selected="selected"</#if> value="9"> 9项 </option>-->
                                <#--<option <#if questions??&&questions.partnum==10>selected="selected"</#if> value="10"> 10项 </option>-->
                            </select>
                        </div>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 选项：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <textarea id="choice1" name="choice1" style="width:100%;height:200px" >
                            ${(questions.option)!}
                            </textarea>
                        </div>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 答案：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                                    <input <#if questions??&&questions.answer??&& questions.answer=='A'>checked=""</#if> type="radio" name="result1" id="result1"  value="A" />选项A：
                                    <input <#if questions??&&questions.answer??&& questions.answer=='B'>checked=""</#if> type="radio" name="result1" id="result1"  value="B" />选项B：
                                    <input <#if questions??&&questions.answer??&& questions.answer=='C'>checked=""</#if> type="radio" name="result1" id="result1"  value="C" />选项C：
                                    <input <#if questions??&&questions.answer??&& questions.answer=='D'>checked=""</#if> type="radio" name="result1" id="result1"  value="D" />选项D：
                                    <input <#if questions??&&questions.answer??&& questions.answer=='E'>checked=""</#if> type="radio" name="result1" id="result1"  value="E" />选项E：
                                    <input <#if questions??&&questions.answer??&& questions.answer=='F'>checked=""</#if> type="radio" name="result1" id="result1"  value="F" />选项F：
                                    <input <#if questions??&&questions.answer??&& questions.answer=='G'>checked=""</#if> type="radio" name="result1" id="result1"  value="G" />选项G：
                                    <#--<input <#if questions??&&questions.answer??&& questions.answer=='H'>checked=""</#if> type="radio" name="result1" id="result1"  value="H" />选项H：-->
                                    <#--<input <#if questions??&&questions.answer??&& questions.answer=='I'>checked=""</#if> type="radio" name="result1" id="result1"  value="I" />选项I：-->
                                    <#--<input <#if questions??&&questions.answer??&& questions.answer=='J'>checked=""</#if> type="radio" name="result1" id="result1"  value="J" />选项J：-->
                        </div>

                    </div>
                </div>
            </div>
            <div id="co_2" style="display:none">
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 选项数量：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <select name="partnum2">
                                <option <#if questions??&&questions.partnum??&&questions.partnum==2> selected="selected"</#if> value="2">2项</option>
                                <option <#if questions??&&questions.partnum??&&questions.partnum==3> selected="selected"</#if> value="3"> 3项 </option>
                                <option <#if questions??&&questions.partnum??&&questions.partnum==4> selected="selected"</#if> value="4" selected="selected" > 4项 </option>
                                <option <#if questions??&&questions.partnum??&&questions.partnum==5> selected="selected"</#if> value="5"> 5项 </option>
                                <option <#if questions??&&questions.partnum??&&questions.partnum==6> selected="selected"</#if> value="6"> 6项 </option>
                                <option <#if questions??&&questions.partnum??&&questions.partnum==7> selected="selected"</#if> value="7"> 7项 </option>
                                <option <#if questions??&&questions.partnum??&&questions.partnum==8> selected="selected"</#if> value="8"> 8项 </option>
                                <option <#if questions??&&questions.partnum??&&questions.partnum==9> selected="selected"</#if> value="9"> 9项 </option>
                                <option <#if questions??&&questions.partnum??&&questions.partnum==10> selected="selected"</#if> value="10"> 10项 </option>
                            </select>
                        </div>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 选项：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <textarea id="choice2" name="choice2" style="width:100%;height:200px" >${(questions.option)!}</textarea>
                        </div>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 答案：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <input type="checkbox"
                                   <#if questions??&&questions.answer??>
                                   <#list questions.answer?split(",") as name>
                                      <#if name??&& name=="A">
                                       checked=""
                                      </#if>
                                   </#list>
                                   </#if>
                                   name="result2[]" id="result2[]"  value="A" /> 选项A：
                            <input type="checkbox"
                                  <#if questions??&&questions.answer??>
                                    <#list questions.answer?split(",") as name>
                                        <#if name??&& name=="B">
                                       checked=""
                                        </#if>
                                    </#list>
                                    </#if>
                                   name="result2[]" id="result2[]"  value="B" />选项B：
                            <input type="checkbox"
                            <#if questions??&&questions.answer??>
                                <#list questions.answer?split(",") as name>
                                    <#if name??&& name=="C">
                                   checked=""
                                    </#if>
                                </#list>
                                </#if>
                                   name="result2[]" id="result2[]"  value="C" /> 选项C：
                            <input type="checkbox"
                                    <#if questions??&&questions.answer??>
                                        <#list questions.answer?split(",") as name>
                                            <#if name??&& name=="D">
                                           checked=""
                                            </#if>
                                        </#list>
                                    </#if>
                                   name="result2[]" id="result2[]"  value="D" />选项D：
                            <input type="checkbox"
                            <#if questions??&&questions.answer??>
                                    <#list questions.answer?split(",") as name>
                                        <#if name??&& name=="E">
                                       checked=""
                                        </#if>
                                    </#list>
                                    </#if>
                                   name="result2[]" id="result2[]"  value="E" />选项E：
                            <input type="checkbox"
                                    <#if questions??&&questions.answer??>
                                        <#list questions.answer?split(",") as name>
                                            <#if name??&& name=="F">
                                           checked=""
                                            </#if>
                                        </#list>
                                    </#if>
                                   name="result2[]" id="result2[]"  value="F" />选项F：
                            <input type="checkbox"
                                    <#if questions??&&questions.answer??>
                                        <#list questions.answer?split(",") as name>
                                            <#if name??&& name=="G">
                                           checked=""
                                            </#if>
                                        </#list>
                                    </#if>
                                   name="result2[]" id="result2[]"  value="G" />选项G：
                            <input type="checkbox"
                                    <#if questions??&&questions.answer??>
                                        <#list questions.answer?split(",") as name>
                                            <#if name??&& name=="H">
                                           checked=""
                                            </#if>
                                        </#list>
                                    </#if>
                                   name="result2[]" id="result2[]"  value="H" />选项H：
                            <input type="checkbox"
                                    <#if questions??&&questions.answer??>
                                        <#list questions.answer?split(",") as name>
                                            <#if name??&& name=="I">
                                           checked=""
                                            </#if>
                                        </#list>
                                    </#if>
                                   name="result2[]" id="result2[]"  value="I" />选项I：
                            <input type="checkbox"
                                    <#if questions??&&questions.answer??>
                                        <#list questions.answer?split(",") as name>
                                            <#if name??&& name=="J">
                                           checked=""
                                            </#if>
                                        </#list>
                                    </#if>
                                   name="result2[]" id="result2[]"  value="J" />选项J：
                        </div>

                    </div>
                </div>
            </div>
            <div id="co_3" style="display:none">

                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 答案：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <input <#if questions??&&questions.answer??&&questions.answer=='1'>checked="" </#if> type="radio"  name="result3" id="result3"  value="1" />TRUE&nbsp;&nbsp;
                            <input <#if questions??&&questions.answer??&&questions.answer=='0'>checked="" </#if> type="radio" name="result3" id="result3"  value="0" />FALSE
                        </div>

                    </div>
                </div>
            </div>
            <div id="co_4" style="display:none">

                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 答案：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <textarea   name="result4" style="width:100%;height:200px" >${(questions.answer)!}</textarea>
                        </div>

                    </div>
                </div>
            </div>
            <div id="co_5" style="display:none">

                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 答案：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <textarea  id="result2" name="result5" style="width:100%;height:200px" >${(questions.answer)!}</textarea>
                        </div>

                    </div>
                </div>
            </div>
            <div id="co_6" style="display:none">

                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-top"> 子题目列表：</label>

                    <div class="col-sm-10">
                        <div class="pos-rel">
                            <a data-toggle="modal" data-target="#modal" data-url="/Exam/additem2" class="btn btn-info" href="#">添加子题目</a>
                            <div class="col-xs-12 clearfix sortbale"  >
                                <ol class="lesson-list sortablelist   " id="lesson"  data-sort-url="/Exam/itemsort"  >

                                </ol>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-top"> 答案解析：</label>

                <div class="col-sm-10">
                    <div class="pos-rel">
                        <textarea id="analysis" name="analysis" style="width:100%;height:200px" >${(questions.analysis)!}</textarea>
                    </div>

                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label no-padding-top"> 难易程度：</label>

                <div class="col-sm-10">
                    <div class="pos-rel">
                       <#if SysDictValues??>
                        <#list SysDictValues as s>
                        <input type="radio" <#if questions??&&questions.rank??&& questions.rank== s.pkSysDictValues>checked="" </#if> name="rank" id="rank" size="70" value="${(s.pkSysDictValues)!}"  />${(s.caption)!} &nbsp;&nbsp;
                        </#list>
                       </#if>
                    </div>
                </div>

            </div>

    </div>
    <!--content-->


</div>
</form>
<#include "../../commons/footer.ftl"/>


<script type="text/javascript">

    $("#lesson").sortable({
        delay : 1,
        serialize: function(parent, children, isContainer) {
            return isContainer ? children : parent.attr('id');
        },

        stop : function(){
            sortList($('#lesson'));
        }
    });

    $("#lesson").on('click', '.delete-chapter-btn', function(e) {
        var btn = $(e.currentTarget);
        BootstrapDialog.confirm("您确定要删除该题目吗？", function(result){
            if(result){
                $.post(btn.data('url'), function(data) {
                    if(data.status==1){

                        btn.parents('.item-chapter').remove();
                        sortList($('#lesson'));
                    }
                },'json');
            }
        });

    });

    KindEditor.ready(function(K) {
        K.create('textarea[name="title"]', {
            allowFileManager : true,
            uploadJson : '/file/upLoad3', //上传的访问的api
            imageTabIndex : 1, //点击上传图片按钮默认显示标签，1为本地上传，默认为0网络图片
            fileManagerJson:'/file/Manager3',
            afterBlur: function () { this.sync(); }
        });
        K.create('textarea[name="choice1"]', {
            allowFileManager : true,
            uploadJson : '/file/upLoad3',
            afterBlur: function () { this.sync(); }
        });
        K.create('textarea[name="choice2"]', {
            uploadJson : '/file/upLoad3', //上传的访问的api
            allowFileManager : true,
            afterBlur: function () { this.sync(); }
        });
        K.create('textarea[name="analysis"]', {
            uploadJson : '/file/upLoad3', //上传的访问的api
            allowFileManager : true,
            afterBlur: function () { this.sync(); }
        });
        K.create('textarea[name="result2"]', {
            uploadJson : '/file/upLoad3', //上传的访问的api
            allowFileManager : true,
            afterBlur: function () { this.sync(); }
        });
        K.create('textarea[name="result5"]', {
            uploadJson : '/file/upLoad3', //上传的访问的api
            allowFileManager : true,
            afterBlur: function () { this.sync(); }
        });
    });



    function changec(v) {

        for(i=1;i<=6;i++){
            if(v==i){
                $("#co_"+i).show();
            }else{
                $("#co_"+i).hide();
            }
        }
    }


    function selectStu(pkId,caption) {
        $("#modal").modal("hide");
        $("#pkParentcaption").val(caption);
        $("#pkParent").val(pkId);
    }


    $(function () {

        <#if questions??&&questions.type??>
            changec(${(questions.type)!});
        </#if>

    })

//    $("#saveItem").click(function () {
//     var texttitle=$("#title").html();
//        alert("1");
//        alert(texttitle);
//     if(texttitle==null||texttitle==""){
//         alert("2");
//         $("#titleMsg").text("不能为空");
//         return false;
//     }
//     alert("3");
//return false;
//    })


</script>