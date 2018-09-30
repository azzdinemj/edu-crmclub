<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

<#assign staticPath=''>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${staticPath}/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${staticPath}/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/kmsy.css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<#include "common/container.ftl">
</nav>

<section class="class_center">
    <div class="text-nav">
        <div class="container">
            <div class="row">
                <div class="col-xs-6">
                    <h3></h3>
                </div>
                <div class="col-xs-6">
                    <div class="thispage-nav-item">
                        <a class="item-active" href="">练习</a>
                        <a href="/questionsTest/historylist">历史练习</a>
                        <a href="/questionsTest/historyerror">错题本</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="container">
        <div class="floor" id="zxtk">
            <div class="row">
               <div class="col-xs-12">
                   <div class="title">
                       <h3>在线题库</h3>
                       <span></span>
                   </div>
               </div>
            </div>
            <div class="row">
                <!--在线模考-->
                <div class="col-md-4 col-xs-6">
                    <div class="q_list">
                        <div class="xiebiao"></div>
                        <div class="list-top clearfix">
                            <h3>在线模考</h3>
                            <p></p>
                            <a href="#"><span></span></a>
                        </div>
                        <div class="list-bottom clearfix">
                            <a href="#" data-toggle="modal" data-target="#myModal"><i></i>选择知识点</a>
                        </div>
                    </div>

                </div>
                <!--在线模考结束-->
                <!--章节练习-->
               <!-- <div class="col-xs-4">
                    <div class="q_list">
                        <div class="xiebiao position1"></div>
                        <div class="list-top bg-color1 clearfix">
                            <h3>章节练习</h3>
                            <p>让我们站在巨人的肩膀上，看世界</p>
                            <a href=""><span class="tk-icon1"></span></a>
                        </div>

                        <div class="list-bottom clearfix">
                            <a href="" data-toggle="modal" data-target="#myModal2"><i></i>我要做题</a>
                        </div>
                    </div>

                </div>
                &lt;!&ndash;章节练习结束&ndash;&gt;
                &lt;!&ndash;每日一练&ndash;&gt;
                <div class="col-xs-4">
                    <div class="q_list">
                        <div class="xiebiao position2"></div>
                        <div class="list-top bg-color2 clearfix">
                            <h3>每日一练</h3>
                            <p>让我们站在巨人的肩膀上，看世界</p>
                            <a href=""><span class="tk-icon2"></span></a>
                        </div>
                        <div class="list-bottom clearfix">
                            <a href="" data-toggle="modal" data-target="#myModal3"><i></i>我要做题</a>
                        </div>
                    </div>

                </div>-->
                <!--每日一练结束-->
            </div>
        </div>

    </div>
</section>

<#include  "common/footer.ftl">
<!--在线模考-->
<form  method="POST" id="formId" action="/questionsItem/findItemBy"  >
<div class="modal fade q-modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog lg-modal-dialog">
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
                            <label class="col-xs-2 control-label">类型</label>
                            <div class="col-xs-8">
                                <select class="form-control" name="pkSysDictValues" required>
                                <#if dict??>
                                    <#list dict as v >
                                        <option value="${(v.pkSysDictValues)!}">${(v.caption)!}</option>
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
                                <#if SysDictValues??>
                                    <#list SysDictValues as s>
                                        <label>${(s.caption)} <input type="radio" name="radio1" value="${(s.pkSysDictValues)}"></label>
                                    </#list >
                                </#if>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-1"></div>
                            <label class="col-xs-2 control-label">判断题</label>
                            <div class="col-xs-8">
                                <label><input type="text" name="input2" class="form-control" placeholder="请输入题数"></label>
                                <#if SysDictValues??>
                                    <#list SysDictValues as s>
                                        <label>${(s.caption)} <input type="radio" name="radio2" value="${(s.pkSysDictValues)}"></label>
                                    </#list >
                                </#if>
                            </div>
                            <div class="col-xs-1"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <input type="button" onclick="checkinput()" value="开始做题" class="btn btn-primary">
                    <#--<a href="question_bank.ftl" class="btn btn-primary">开始做题</a>-->
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>

<script src="${staticPath}/js/jquery-1.10.2.js"></script>
<script src="${staticPath}/js/bootstrap.min.js"></script>
<script src="${staticPath}/js/over.js"></script>
<script src="${staticPath}/js/bootstrapValidator.min.js" ></script>
<script src="${staticPath}/js/bootstrap-datepicker.js" ></script>
<script src="${staticPath}/js/bootstrap-datetimepicker.min.js" ></script>
<script src="${staticPath}/js/bootstrap-datetimepicker.zh-CN.js" ></script>
<script src="${staticPath}/js/function.js" ></script>

</body>
</html>
<script>
    function checkinput() {
        var c1=$("input[name='checkbox1']").is(":checked");
        var c2=$("input[name='checkbox2']").is(":checked");

        if(c1){
            var i1=$("input[name='input1']").val();
            i1=i1.trim();

            if(i1 == ""){
                alert("请输入选择题数量 ");
                return false;
            };

            var r1=$("input[name='radio1']").is(":checked");
            if(r1){
                $("#formId").submit();
            }else{
                alert("请选择选择题难度");
                return false;
            }
        } else if(c2){
            var i2=$("input[name='input2']").val();
            i2=i2.trim();
            if(i2=""){
                alert("请输入判断题数量");
                return false;
            }
            var r2=$("input[name='radio2']").is(":checked");
            if(r2){
                $("#formId").submit();
            }else{
                alert("请选择判断题难度");
                return false;
            }
        }else{
            alert("请选择题目类型");
        }


    }



</script>