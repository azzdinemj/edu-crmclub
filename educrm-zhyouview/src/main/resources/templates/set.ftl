<!DOCTYPE html>
<html lang="en">
<head>
<#assign staticPath=''>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${staticPath}/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/login.css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<#include "common/container.ftl">
</nav>

<section class="class_center">
    <div class="container">

        <div class="row">
            <div class="col-xs-12 col-sm-3 col-md-3">
                <div class="left-nav">
                    <h3 class="class-title">我的设置</h3>
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active"><a href="#one" data-toggle="tab">基础信息</a></li>
                        <li><a href="#two" data-toggle="tab">安全设置</a></li>


                    </ul>
                </div>
            </div>
            <div class="col-xs-12 col-sm-9 col-md-9">
                <div id="myTabContent" class="tab-content center-content">
                    <div class="tab-pane active" id="one">

                        <div class="panel panel-default panel-col">
                            <div class="panel-heading">基本信息</div>
                            <div class="panel-body">

                                <form class="form-horizontal bv-form form-ajax" id="form" role="form" method="post" action="/student/save" data-target="/student/set" novalidate="novalidate">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">帐号：</label>
                                        <div class="col-sm-7">${(studentUser.phone)!}</div>
                                    </div>
                                    <input type="hidden" name="pkStudent" value="${(studentUser.pkStudent)!}">

                                    <div class="form-group mt5">
                                        <label class="col-sm-3 control-label">昵称：</label>
                                        <div class="col-sm-7">
                                        <input type="text" class="form-control name" name="caption" value="${(studentUser.caption)!}"   placeholder="用户昵称" data-bv-notempty="" data-bv-notempty-message="请录入您的昵称" data-bv-field="nickname">
                                        </div>
                                    </div>

                                    <div class="form-group mt5">
                                        <label class="col-sm-3 control-label">公司：</label>
                                        <div class="col-sm-7">
                                            <input type="text" class="form-control name" name="company" value="${(studentUser.company)!}"   placeholder="用户公司" data-bv-notempty="" data-bv-notempty-message="请录入您的公司" data-bv-field="nickname">
                                        </div>
                                    </div>

                                    <div class="form-group mt5">
                                        <label class="col-sm-3 control-label">部门：</label>
                                        <div class="col-sm-7">
                                            <input type="text" class="form-control name" name="branch" value="${(studentUser.branch)!}"   placeholder="用户部门" data-bv-notempty="" data-bv-notempty-message="请录入您的公司" data-bv-field="nickname">
                                        </div>
                                    </div>

                                    <#--<div class="form-group">-->
                                        <#--<label class="col-sm-3 control-label">手机：</label>-->
                                        <#--<div class="col-sm-7">-->
                                        <#--&lt;#&ndash;<input type="text" name="" placeholder="请输入手机号" class="form-control mobile" maxlength="11"/>&ndash;&gt;-->
                                        <#--</div>-->
                                     <#--</div>-->

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">备注：</label>
                                        <div class="col-sm-7">
                                            <textarea class="form-control" rows="3" name="planning" placeholder="我是个懒货，没办法啊..." data-bv-notempty="" data-bv-notempty-message="请录入您的签名~" data-bv-field="sign">${(studentUser.planning)!}</textarea>
                                        </div>
                                    </div>

                                     <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-7 toolBar">
                                            <#--<a id="nextBtn" class="btn btn-orange" href="javascript:">确定</a>-->
                                                <input type="submit" class="btn btn-orange" value="确定">
                                            </div>
                                     </div>
                                        <br>
                                </form>

                            </div>
                        </div>

                     </div>
                    <div class="tab-pane" id="two">
                        <div class="panel panel-default panel-col">
                            <div class="panel-heading">安全设置</div>
                            <div class="panel-body">

                                <#--<form class="form-horizontal bv-form" method="post"  action="/updatepwdSet" data-target="/set" enctype="multipart/form-data"  novalidate="novalidate">-->
                                   <form class="form-horizontal">
                                    <input class="mobile"  type="hidden" value=""></input>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label ">当前密码：</label>
                                        <div class="col-sm-7">
                                            <input type="password" class="form-control" name="oldpwd" data-bv-notempty="" data-bv-notempty-message="请录入您的当前密码" data-bv-field="oldpwd">
                                            <small class="help-block" data-bv-validator="notEmpty" data-bv-for="oldpwd" data-bv-result="NOT_VALIDATED" style="display: none;">请录入您的当前密码</small></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label password ">新密码：</label>
                                        <div class="col-sm-7">
                                            <input type="password" class="form-control" name="newpwd" data-bv-notempty="" data-bv-notempty-message="请录入您的新密码" data-bv-field="newpwd">
                                            <small class="help-block" data-bv-validator="notEmpty" data-bv-for="newpwd" data-bv-result="NOT_VALIDATED" style="display: none;">请录入您的新密码</small></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label password2">确认密码：</label>
                                        <div class="col-sm-7">
                                            <input type="password" class="form-control" name="newpwd2" data-bv-notempty="" data-bv-notempty-message="请录入您的确认密码" data-bv-identical="" data-bv-identical-message="两次密码不一致" data-bv-identical-field="newpwd" data-bv-field="newpwd2">
                                            <small class="help-block" data-bv-validator="identical" data-bv-for="newpwd2" data-bv-result="NOT_VALIDATED" style="display: none;">两次密码不一致</small><small class="help-block" data-bv-validator="notEmpty" data-bv-for="newpwd2" data-bv-result="NOT_VALIDATED" style="display: none;">请录入您的确认密码</small></div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-3 control-label"></label>
                                        <div class="col-sm-7">
                                            <#--<button type="submit"  name="signup" value="Sign up" class="btn btn-orange btn-block">确定</button>-->
                                          <a id="nextBtnSet" class="btn btn-orange" href="javascript:">确定</a>
                                        </div>
                                    </div><br>
                                    <br>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>



                </div>
            </div>


        </div>

    </div>
</section>
<#include  "common/footer.ftl">
<script src="${staticPath}/js/jquery.min.js"></script>
<script src="${staticPath}/js/bootstrap.min.js"></script>
<script src="${staticPath}/js/over.js"></script>

<script src="${staticPath}/js/login.js"></script>
<script src="${staticPath}/js/bootstrapValidator.min.js"></script>
<script src="${staticPath}/js/bootstrap-notify.js"></script>


<script>

    $('.form-ajax').bootstrapValidator().on('success.form.bv', function (e) {
// Prevent form submission
        e.preventDefault();

// Get the form instance
        var $form = $(e.target);

// Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

// Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function (data) {

            if (data.code == 0) {
                Notify.success(data.message);
                if ($form.data('target')) {
                    setTimeout("window.location.href='" + $form.data('target') + "'", 1);
                }
            } else {
                Notify.danger(data.message);
            }
        }, 'json');
    });

    //登录后修改密码
    $('#nextBtnSet').click(function(){

        var opwd=$('input[name="oldpwd"]').val();

        var newpwd=$('input[name="newpwd"]').val();

        var newpwd2=$('input[name="newpwd2"]').val();

        if(opwd == ''){
            showTips('请输入旧密码')
            return false;
        }else if(newpwd == ''){
            showTips('请输入新密码密码')
            return false;
        } else if(newpwd2 == ''){
            showTips('请再次输入您的密码~');
            return false;
        }else if(newpwd2 != newpwd || newpwd != newpwd2){
            showTips('两次密码输入不一致呢~');
            return false;
        }
        $.ajax({
            url: '/student/updatepwdSet',
            type: 'POST',
            data: {
                opwd: opwd,
                newpwd :newpwd,
                newpwd2:newpwd2
            },
            dataType: "json",
            success: function (data) {
                if (data.code==0) {
                    //修改成功跳转登录页面
                    location.href="/login?"+new Date().getTime();
                }else{
                    showTips('data.message~');
                }
            },
            error: function (data) {}

        })



    });

</script>

</body>
</html>