<!DOCTYPE html>
<html>
<head lang="en">

<#assign staticPath=''>

    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${staticPath}/fonts/ionicons/css/ionicons.min.css">
    <link href="${staticPath}/css/style.css" rel="stylesheet" type="text/css"/>


    <script src="${staticPath}/js/jquery-1.10.2.js"></script>
    <script src="${staticPath}/js/bootstrap.js"></script>
    <script src="${staticPath}/js/bootstrap-notify.js"></script>

    <script type="text/javascript" src="${staticPath}/js/test.js"></script>

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<#include "common/container.ftl">
</nav>



<form class="form-ajax2"  method="POST" id="formId" action="/questionsTest/saveTest" data-target="/questionsTest/historylist" >

    <section class="qu_bank">
    <div class="container">
        <div class="row clearfix" id="testArea">
            <div class="page-header">
                <h3 class="text-center">在线测试</h3>
            </div>
            <form class="" id="testForm">
                <div class="test-form-box">

                <#if listSelect?? && (listSelect?size > 0) >
                    <!--选择-->
                    <div class="jxz-box col-md-12">
                        <h4 class="tesTitle">单项选择题</h4>
                        <!--题号-->
                    <#assign index = 0 >
                        <#if listSelect??>
                            <#list listSelect as v >
                             <div class="testCon" data-type="1" data-answer="${(v.answer)!}">
                                 <#--<input type="hidden" value="${(v.answer)!}" name="Select[]" class="select">-->

                                 <h4 class="jxz-title"><span>${index+1}</span>&nbsp;&nbsp;&nbsp;&nbsp;${(v.title)!}</h4>
                             <#assign index = index + 1>

                                 <!--选项编号-->
                                 <#assign indexs = 0 >
                                     <#list v.option?split("</p>") as vv>
                                         <#if indexs+1<=v.partnum>
                                         <div class="jxz-option radio" >
                                             <label>
                                                 <input  name="${(v.id)!}" value=" <#if indexs+1==1>A<#elseif indexs+1==2>B<#elseif indexs+1==3>C<#elseif indexs+1==4>D<#elseif indexs+1==5>E<#elseif indexs+1==6>F<#elseif indexs+1==7>G</#if>" type="radio"> ${vv}
                                                 <#assign indexs = indexs + 1>
                                             </label>
                                         </div>

                                         </#if>
                                     </#list>

                                     <input type="hidden" value="${(v.id)!}" name="Select[]" class="select">
                                     <input type="hidden" value="${(v.answer)!}" name="trueanswer" class="trueanswer">
                                 <div class="topic-answer">

                                <p>您的答案：<span class="userAnswer"></span> <span class="rightAnswer">正确答案：${(v.answer)!}</span>
                                </p>
                                <p>解析：${(v.analysis)!}</p>

                            </div>
                        </div>
                            </#list>
                        </#if>
                    </div>
                <#elseif listSelect1??&&listSelect1==1>
                    <!--没选择选择题-->
                <#else >
                    <h4 class="tesTitle">选择题已做完</h4>
                </#if>

                <#if listIsNot?? && (listIsNot?size > 0) >

                    <!--判断-->
                    <div class="jxz-box col-md-12">
                        <h4 class="tesTitle">判断题</h4>

                    <#assign index = 0 >
                        <#if listIsNot??>
                            <#list listIsNot as v >
                            <div class="testCon" data-type="2" data-answer="${(v.answer)!}">
                            <h4 class="jxz-title"><span>${index+1}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${(v.title)!}</h4>
                            <#assign index = index + 1>

                            <div class="jxz-option radio">
                                <label>
                                    <input name="${(v.id)!}" value="1" type="radio"> 正确
                                </label>
                            </div>
                            <div class="jxz-option radio">
                                <label>
                                    <input name="${(v.id)!}" value="0" type="radio"> 错误
                                </label>
                            </div>
                                <input type="hidden" value="${(v.answer)!}" name="trueanswer" class="trueanswer">
                                <input type="hidden" value="${(v.id)!}" name="Select[]" class="select">
                            <div class="topic-answer">
                                <p>您的答案：<span class="userAnswer"></span><span class="rightAnswer">正确答案：<#if v.answer??&&v.answer=='1'>正确 <#else>错误</#if> </span></p>

                                <p>解析：${(v.analysis)!}</p>
                            </div>
                        </div>
                            </#list>
                        </#if>

                    </div>
                <#elseif listIsNot1??&&listIsNot1==1>
                        <!--没选择判断题-->
                <#else>
                    <h4 class="tesTitle">判断题已做完</h4>
                </#if>

                </div>
                <div class="form-group assignment">
                    <#if listSelect??  &&(listSelect?size > 0) || listIsNot?? && (listIsNot?size > 0) >
                    <button type="button" class="btn btn-primary active" onclick="assignment()">交卷</button>
                    </#if>

                    <a href="#" data-toggle="modal" data-target="#myModal" class="btn btn-primary other" >继续做题</a>
                    <a href="/questionsTest/historylist"  class="btn btn-primary other" >答题记录</a>

                    <!--<a href="#" data-toggle="modal" data-target="#myModal" class="btn btn-primary" >停止做题</a>-->
                    <!--<a href="question_.ftl" class="btn btn-primary" >返回题库</a>-->

                </div>
            </form></div>
       <!-- <div class="row">
            <div class="middle-top">
                <div class="middle-top-left  pull-left">
                    <div class="stop pull-left">
                        <a href="javascript:void(0);" class="text-center">
                            <div data-toggle="modal" data-target="#myModal" class="time-stop icon ion-pause" title="暂停做题"></div>
                            <div class="time-start icon ion-play" title="开始做题"></div>
                        </a>
                    </div>
                    <div class="pull-left pull-time">
                        <div class="time"> </div>
                    </div>
                    <div class="pull-left pull-right">
                        &lt;!&ndash;已做答的数量和考题总数&ndash;&gt;
                        当前第<span class="questioned"></span>题/共<span class="question_sum"></span>题 </div>
                </div>
            </div>

        </div>
        <div class="row">
            &lt;!&ndash;试题区域&ndash;&gt;
            <div class="question_list">
                <div class="icon ion-map-marker">[判断题]</div>
                <ul class="list-unstyled question" id="question0" name="">
                    <li class="question_title"></li>
                </ul>
            </div>
            &lt;!&ndash;考题的操作区域&ndash;&gt;
            <div class="operation">
                <div class="text-left">
                    <div id="unHeart"> <span class="icon ion-heart-empty"></span> <span>收藏本题</span> </div>
                    <div id="heart"> <span class="icon ion-heart"></span> <span>已收藏</span> </div>
                </div>
                <div class="mid-left">
                    <div id="unFocus"> <span class="icon ion-heart-empty"></span> <span>关注本题</span> </div>
                    <div id="focus"> <span class="icon ion-heart"></span> <span>关注</span> </div>
                </div>
                <div class="text-right">
                    <div class="form-group">
                        <button class="btn btn-success" id="submitQuestions">提交试卷</button>
                        <button class="btn btn-info" id="nextQuestion">下一题</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-default">
            <div class="panel-heading">
                <div id="closeCard">
                    <span>收起答题卡</span> <span class="icon ion-chevron-up"></span>
                </div>
                <div id="openCard"> <span>展开答题卡</span> <span class="icon ion-chevron-down"></span> </div>
            </div>
            <div id="answerCard">
                <div class="panel-body form-horizontal">
                    <ul class="list-unstyled">
                    </ul>
                </div>
            </div>
        </div>-->
    </div>
</section>


</form>

<#include  "common/footer.ftl">

<form  method="POST" id="formId" action="/questionsItem/findItemBy"  >
    <div class="modal fade q-modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        在线练习
                    </h4>
                </div>
                <div class="modal-body select-body">
                    <form>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-1"></div>
                                <label class="col-xs-2 control-label">知识点</label>
                                <div class="col-xs-8">
                                    <select class="form-control" name="questionsSubjectId" required>
                                    <#if qSubjectList??>
                                        <#list qSubjectList as v >
                                            <option value="${(v.id)!}">${(v.name)!}</option>
                                        </#list>
                                    </#if>

                                    </select>
                                </div>
                                <div class="col-xs-1"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-1"></div>
                                <label class="col-xs-2 control-label">题目类型</label>
                                <div class="col-xs-8">
                                    <label>选择题 <input name="checkbox1" type="checkbox" value="1"></label>
                                    <label>判断题 <input name="checkbox2" type="checkbox" value="3"></label>
                                </div>
                                <div class="col-xs-1"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-1"></div>
                                <label class="col-xs-2 control-label">选择题</label>
                                <div class="col-xs-8">
                                    <label><input type="text" name="input1" class="form-control" placeholder="请输入题数"></label>
                                    <label>难 <input type="radio" name="radio1" value="1"></label>
                                    <label>中 <input type="radio" name="radio1" value="2"></label>
                                    <label>易<input type="radio" name="radio1" value="3"></label>
                                </div>
                                <div class="col-xs-1"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-1"></div>
                                <label class="col-xs-2 control-label">判断题</label>
                                <div class="col-xs-8">
                                    <label><input type="text" name="input2" class="form-control" placeholder="请输入题数"></label>
                                    <label>难 <input type="radio" name="radio2" value="1"></label>
                                    <label>中 <input type="radio" name="radio2" value="2"></label>
                                    <label>易<input type="radio" name="radio2" value="3"></label>
                                </div>
                                <div class="col-xs-1"></div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <input type="submit" value="开始做题" class="btn btn-primary">
                        <#--<a href="question_bank.ftl" class="btn btn-primary">开始做题</a>-->
                        </div>
                    </form>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</form>

</body>
</html>
<script src="${staticPath}/js/jquery-1.10.2.js" ></script>
<script src="${staticPath}/js/bootstrapValidator.min.js" ></script>
<#--<script src="${staticPath}/js/function.js" ></script>-->
<script src="${staticPath}/js/bootstrap-datetimepicker.min.js" ></script>
<script src="${staticPath}/js/bootstrap-datetimepicker.zh-CN.js" ></script>
<script src="${staticPath}/js/bootstrap-datepicker.js" ></script>

<script>

    $('.form-ajax2').bootstrapValidator().on('success.form.bv', function(e) {
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
            }else{
                Notify.danger(data.message);
                window.location.reload();
            }
        }, 'json');
    });
</script>



