<#import "../commons/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title></title>

    <link href="../css/style.default.css" rel="stylesheet">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/plug/bootstrapValidator.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-dialog.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-notify.css">
    <link rel="stylesheet" type="text/css" href="../css/datepicker.css">


</head>
<body class="signin">
<section>

    <div class="signinpanel">

        <div class="row">

            <!--  <div class="col-md-7">

                  <div class="signin-info">
                      <img src="images/lilogo.png">
                      <div class="mb20"></div>

                      <h5><strong>欢迎登录力迈中美学校!</strong></h5>
                   &lt;!&ndash;   <ul>
                          <li><i class="fa fa-arrow-circle-o-right mr5"></i> Fully Responsive Layout</li>
                          <li><i class="fa fa-arrow-circle-o-right mr5"></i> HTML5/CSS3 Valid</li>
                          <li><i class="fa fa-arrow-circle-o-right mr5"></i> Retina Ready</li>
                          <li><i class="fa fa-arrow-circle-o-right mr5"></i> WYSIWYG CKEditor</li>
                          <li><i class="fa fa-arrow-circle-o-right mr5"></i> and much more...</li>
                      </ul>&ndash;&gt;
                      <div class="mb20"></div>
                  </div>&lt;!&ndash; signin0-info &ndash;&gt;

              </div>--><!-- col-sm-7 -->
            <div class="col-md-2 col-xs-3"></div>
            <div class="col-md-8 col-xs-12">
                <div class="logo text-center">
                    <#--<img src="images/lilogo.png">-->
                        <#--onerror="this.src='images/lilogo.png'"-->
                        <img src="" id="logoUrl">
                </div>
                <form id="formid" class="form-loginajax" method="post" action="/signon" data-target="/sigonindex">
                    <h4 class="nomargin">欢迎</h4>
                    <p class="mt5 mb20">登录进入您的帐户.</p>

                    <input type="text" class="form-control uname" id="username" name="user.pkSysUser" placeholder="用户名" required autofocus/>
                    <input type="password" class="form-control pword" id="password" name="user.password" placeholder="密码" required/>

                    <#--<div id="errordiv" style="display:none"><span style="color:red">错误提示：<span id="errormsg"></span></span></div>-->
                    <input type="submit" class="btn btn-news mb15 btn-block login" value=<@spring.message "login.login"/>></input>

                    <div class="row mt15">
                        <div class="col-xs-6 text-center"><a href="?lang=zh_CN">中文</a></div>

                        <div class="col-xs-6 text-center"><a href="?lang=en_US">English</a></div>
                    </div>
                </form>

            </div><!-- row -->
            <div class="col-xs-2 col-md-3"></div>


        </div><!-- signin -->
    </div>
</section>
</body>

</html>
<script src="../js/jquery-1.11.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/login/bootstrapValidator.min.js"></script>
<script src="../js/login/bootstrap-notify.js"></script>
<script src="../js/bootstrap-datetimepicker.min.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../js/function.js"></script>
<script src="../js/wuxue-common.js"></script>
<script type="text/javascript">
    $(function(){
        <#--setCookie("locale","${.locale}");-->

        lofoU();
    });

    function lofoU(){
         $.ajax({
             type: "POST",
             url: "/system/domain/getLogoUrl",
             success: function (data) {
                 console.log(data);
                 if(data!= null){
                     $("#logoUrl").attr("src", data);
                 }
             },
             error: function(e){
                 console.log(e);
             }
         });
    }
</script>
<#--
${.locale}-->