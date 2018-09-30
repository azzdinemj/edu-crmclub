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
    <!-- <script type="text/javascript" src="${staticPath}/js/test.js"></script>-->

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<#include "common/container.ftl">
</nav>

<div class="text-nav">
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <h3></h3>
            </div>
            <div class="col-xs-6">
                <div class="thispage-nav-item">
                    <a href="/questions/question_">练习</a>
                    <a href="/questionsTest/historylist">历史练习</a>
                    <a class="item-active" href="/questionsTest/historyerror">错题本</a>
                </div>
            </div>
        </div>

    </div>
</div>

<section class="qu_bank">
    <div class="container">
        <div class="row clearfix" id="testArea">
            <form class="" id="testForm">
                <div class="test-form-box">
                    <div class="row boxtitle">
                        <div class="col-xs-8">

                        </div>
                        <div class="col-xs-4">
                            <span style="color: red">错题数量：${listSelect?size+listIsNot?size}</span>
                        <#--<span class="red"> 错误：1</span>-->
                        </div>
                    </div>


                <#if listSelect?? && (listSelect?size > 0) >
                    <!--选择-->
                    <div class="jxz-box col-md-12">
                        <h4 class="tesTitle">单项选择题</h4>
                        <!--题号-->
                        <#assign index = 0 >
                        <#if listSelect??>
                            <#list listSelect as v >
                                <div class="testCon" data-type="1" data-answer="${(v.answer)!}">
                                    <h4 class="jxz-title"  <#if v.map.userAnswer??&&v.answer??&&v.answer?trim==v.map.userAnswer?trim> <#else>style="color: red"></#if>  <span>${index+1}</span>&nbsp;&nbsp;&nbsp;&nbsp;${(v.title)!}</h4>
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
                                    <div >

                                        <p>您的答案：${(v.map.userAnswer)!}<span class="userAnswer"></span> <span class="rightAnswer">正确答案：${(v.answer)!}</span>
                                        </p>
                                        <p>解析：${(v.analysis)!}</p>

                                    </div>
                                </div>
                            </#list>
                        </#if>
                    </div>
                <#else >

                </#if>

                <#if listIsNot?? && (listIsNot?size > 0) >
                    <!--判断-->
                    <div class="jxz-box col-md-12">
                        <h4 class="tesTitle">判断题</h4>

                        <#assign index = 0 >
                        <#if listIsNot??>
                            <#list listIsNot as v >
                                <div class="testCon" data-type="2" data-answer="${(v.answer)!}">
                                    <h4 class="jxz-title" <#if v.map.userAnswer??&&v.answer??&&v.answer==v.map.userAnswer> <#else>style="color: red"></#if> <span>${index+1}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${(v.title)!}</h4>
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
                                    <div >
                                        <p>您的答案：<#if v.map.userAnswer??&&v.map.userAnswer=='0'>错误<#else >正确</#if><span class="userAnswer"></span><span class="rightAnswer">正确答案：<#if v.answer??&&v.answer=='0'>错误<#else >正确</#if></span></p>

                                        <p>解析：${(v.analysis)!}</p>
                                    </div>
                                </div>
                            </#list>
                        </#if>

                    </div>
                <#else >

                </#if>


                </div>
                <div class="form-group assignment">
                <#--<a href="#" data-toggle="modal" data-target="#myModal" class="btn btn-primary">继续做题</a>-->


                </div>
            </form></div>

    </div>
</section>

<!--在线模考-->
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
                                <select class="form-control" required>
                                    <option></option>
                                </select>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-1"></div>
                            <label class="col-xs-2 control-label">题目类型</label>
                            <div class="col-xs-8">
                                <label>选择题 <input type="checkbox" ></label>
                                <label>判断题 <input type="checkbox"></label>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-1"></div>
                            <label class="col-xs-2 control-label">选择题</label>
                            <div class="col-xs-8">
                                <label><input type="text" class="form-control" placeholder="请输入题数"></label>
                                <label>难 <input type="radio" name="n" value="0"></label>
                                <label>中 <input type="radio" name="n" value="1"></label>
                                <label>易<input type="radio" name="n" value="2"></label>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-1"></div>
                            <label class="col-xs-2 control-label">判断题</label>
                            <div class="col-xs-8">
                                <label><input type="text" class="form-control" placeholder="请输入题数"></label>
                                <label>难 <input type="radio" name="v" value="0"></label>
                                <label>中 <input type="radio" name="v" value="1"></label>
                                <label>易<input type="radio" name="v" value="2"></label>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <a href="question_bank.ftl" class="btn btn-primary">开始做题</a>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>