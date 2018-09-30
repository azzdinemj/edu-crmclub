<div class="container">
    <div class="row">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
            </button>
            <a href="/course/query" class="navbar-brand">
                <img src="${staticPath}/images/logo.png" class="logo">
            </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav ml-auto navbar-right">

                <li class="nav-item"><a class="nav-link " id="aboutnav" href="/static/about"><b><strong>关于我们</strong></b></a></li>
                <li class="nav-item"><a class="nav-link" id="coursenav"  href="/course/query"><b><strong>课程中心</strong></b></a></li>
                <li class="nav-item"><a class="nav-link" id="questionsnav" href="/questions/question_"><b><strong>题库</strong></b></a></li>
                <li class="nav-item"><a class="nav-link" id="teachernav" href="/teacher/query"><b><strong>师资力量</strong></b></a></li>
                <li class="nav-item"><a class="nav-link" id="adminid" target="_blank" href="//118.24.189.48:8001"><b><strong>后台管理</strong></b></a></li>
                <li>
                    <div class="iconimg">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <a class="myicon" title="${(caption)!}"><img src="${staticPath}/images/img/no2.jpg"></a> <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a class="bh" href="/student/personalcenter"><i class="fa fa-home"></i> 个人中心</a>
                                </li>
                                <li><a class="bh" href="/student/set"><i class="fa fa-home"></i> 我的设置</a></li>
                                <li><a class="bh" href="/signout"><i class="fa fa-sign-out"></i> 退出登录</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                </li>
            </ul>
        </div>

    </div>
</div>
<script src="${staticPath}/js/jquery.min.js"></script>

<script>

    $(function () {
          <#if Session.admin?exists>
              $("#adminid").attr("href","${Session.admin}");
          </#if>
    })
</script>