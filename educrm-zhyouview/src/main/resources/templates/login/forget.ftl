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
        <form action="" method="post" id="step1_frm">
            <header>
                <h3><a href="javascript:history.go(-1)"  class="back">返回</a>找回密码</h3>
            </header>


            <div class="mt5 row ">

                <div class="col-md-12">
                    <input type="text" name="" placeholder="请输入手机号" class="frm_input mobile" maxlength="11"/>
                </div>

            </div>
            <div class="mt5 row ">

                <div class="col-md-8">
                    <input type="text" name="" placeholder="请输入验证码" class="frm_input verifyCode" maxlength="11"/>
                </div>
                <div class="col-md-4">
                    <input type="button" name=""  class="frm_input btn-pass" value="获取验证码" onclick="settime(this)" ></input>

                </div>

            </div>
            <div class="mt5 row">
                <div class="col-md-12">
                    <input type="password" name="test"  placeholder="重设密码" class="frm_input password" maxlength=""/>

                </div>


            </div>
            <div class="mt5 row">
                <div class="col-md-12">
                    <input type="password" name="test"  placeholder="再次输入密码" class="frm_input password2" maxlength=""/>

                </div>
            </div>


            <div class="toolBar col-xs-12 mt5">
                <a id="nextBtnFgt" class="btn btn-orange" href="javascript:">找回</a>

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


    var countdown=60;

    function settime(val) {

        var mobile =$('.mobile').val();
        if(mobile == ''){
            showTips('请输入手机号码')
            return false;
        }else if(!isPoneAvailable(mobile)){
            showTips('请输入正确的手机号码')
            return false;
        }

        if (countdown == 0) {
            val.removeAttribute("disabled");
            val.value="获取验证码";
            countdown = 60;
        } else {
            val.setAttribute("disabled", true);
            val.value="重新发送(" + countdown + ")";
            countdown--;
            setTimeout(function() {
                settime(val)
            },1000)
        }

    }


    //获取验证码
    $('.btn-pass').click(function(){

        var mobile =$('.mobile').val();
        if(mobile == ''){
            showTips('请输入手机号码')
            return false;
        }else if(!isPoneAvailable(mobile)){
            showTips('请输入正确的手机号码')
            return false;
        }
        $.ajax({
            url:'/getValidCode',
            type:'POST',
            data: {
                phone: mobile,
            },
            dataType:'json',
            success: function (data) {
                if (data.code==0) {
                    showTips(data.message);
                }else{
                    showTips(data.message);
                }
            },
            error: function (data) {
                showTips("error");
            }

        })


    });


    //登录页修改密码
    $('#nextBtnFgt').click(function(){
        var mobile =$('.mobile').val();
        var password = $('.password').val();
        var password2 = $('.password2').val();
        var verifyCode = $('.verifyCode').val();

        if(mobile == ''){
            showTips('请输入手机号码')
            return false;
        }else if(!isPoneAvailable(mobile)){
            showTips('请输入正确的电话号码')
            return false;
        }
        else if(password == ''){
            showTips('请输入密码')
            return false;
        } else if(password2 == ''){
            showTips('请再次输入您的密码~');
            return false;
        }else if(password != password2 || password2 != password){
            showTips('两次密码输入不一致呢~');
            return false;
        }
        $.ajax({
            url: '/updatepwd',
            type: 'POST',
            data: {
                phone: mobile,
                password :password,
                valicode:verifyCode
            },
            dataType: "json",
            success: function (data) {
                if (data.code==0) {
                    //修改成功跳转登录页面
                    location.href="/login?"+new Date().getTime();
                }else{
                    showTips(data.message);
                }
            },
            error: function (data) {}

        })



    });
</script>