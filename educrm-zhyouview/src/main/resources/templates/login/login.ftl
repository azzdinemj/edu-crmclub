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
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="${staticPath}/css/login.css">



</head>
<body>
<div class="top">
    <img src="${staticPath}/images/logo.png">
</div>
<div class="login">
    <div class="container">
        <form action="/"  method="post" id="step1_frm">
            <header>
                <h3>欢迎登录</h3>
            </header>
          <#--  <div class="mt5 row">

                <div class="col-md-12">
                    <input type="text" name="" placeholder="用户名"  class="frm_input name" maxlength="32"/>
                </div>
            </div>-->

            <div class="mt5 row ">

                <div class="col-md-12">
                    <input type="text" name="" placeholder="请输入手机号" class="frm_input mobile" maxlength="11"/>
                </div>

            </div>
            <div class="mt5 row">

                <div class="col-md-12">
                    <input type="password" name=""  placeholder="密码" class="frm_input password" maxlength="11"/>

                </div>
            </div>


            <div class="toolBar col-xs-12 mt5">
            <a id="nextBtn" class="btn btn-orange" href="javascript:">登录</a>
             <#--   <input type="submit" value="登录" id="nextBtn" class="btn btn-orange" >-->
            </div>
            <div class="form-footer">
                <span><a href="/updatephone">更换手机!</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <a href="/forget">忘记密码？</a>
                <span>没有账号？<a href="/registered">注册</a></span>
            </div>
        </form>
    </div>
    <footer>
        <p>@上海无学网络科技有限公司
        </p>
    </footer>
</div>
<script src="${staticPath}/js/jquery.min.js"></script>
<script src="${staticPath}/js/login.js"></script>
</body>
</html>

<script>

    //登录
    $('#nextBtn').click(function(){
        var mobile =$('.mobile').val();
        var password = $('.password').val();
        if(mobile == ''){
            showTips('请输入手机号码')
            return false;
        }else if(password == ''){
            showTips('请输入密码')
            return false;
        }else if(!isPoneAvailable(mobile)){
            showTips('请输入正确的电话号码')
            return false;
        }
        $.ajax({
            url: '/signon',
            type: 'POST',
            data: {
                phone: mobile,
                password :password
            },
            dataType: "json",
            success: function (data) {
                if (data.code==0) {
                    //登陆成功跳转到下单页面
                   // location.href="/course/query?"+new Date().getTime();
                    location.href="/static/about?"+new Date().getTime();
                }else{
                    showTips(data.message);
                }
            },


        })


    });
</script>