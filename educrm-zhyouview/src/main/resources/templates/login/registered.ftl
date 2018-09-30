<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="../css/login.css">



</head>
<body>
<div class="top">
    <img src="../images/logo.png">
</div>
<div class="login">
    <div class="container">
        <form action="" method="post" id="step1_frm">
            <header>
                <h3>欢迎注册</h3>
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
                <div class="col-md-4 nolp">
                    <input type="button" name=""  class="frm_input btn-pass" value="获取验证码" onclick="settime(this)" >
                </div>

            </div>

            <div class="toolBar col-xs-12 mt5">
                <a id="nextBtnReg" class="btn btn-orange" href="javascript:">注册</a>
            </div>
            <div class="form-footer">

                <span>已有账号？<a href="/login">登录</a></span>
            </div>
        </form>
    </div>
    <footer>
        <p>@上海无学网络科技有限公司
        </p>
    </footer>
</div>
<script src="../js/jquery.min.js"></script>
<script src="../js/login.js"></script>
</body>
</html>
<script type="text/javascript">


//    var c = 60;
//    var intervalId = setInterval(function () {
//        c = c - 1;
//        $(".codebtn").val(c + 's后重发');
//        $(".codebtn").prop('disabled',true);
//        if (c == 0) {
//            clearInterval(intervalId);
//            $(".codebtn").val('获取验证码');
//            $(".codebtn").prop('disabled',false);
//        }
//    }, 1000)


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
            countdown=60;
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
            showTips('请输入正确的电话号码')
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



    //注册
    $('#nextBtnReg').click(function(){
        var mobile =$('.mobile').val();
        var verifyCode = $('.verifyCode').val();
        if(mobile == ''){
            showTips('请输入手机号码')
            return false;
        }else if(verifyCode == ''){
            showTips('请输入验证码')
            return false;
        }else if(!isPoneAvailable(mobile)){
            showTips('请输入正确的电话号码')
            return false;
        }

        $.ajax({
            url:'/save',
            type:'POST',
            data: {
                phone: mobile,
                verifyCode :verifyCode
            },
            dataType:'json',
            success: function (data) {
                if (data.code==0) {
                    //注册成功跳转到登录
                    location.href="/login?"+new Date().getTime();
                }else{
                    showTips(data.message);
                }
            },
            error: function (data) {
                showTips("error");
            }

        })


    });



</script>