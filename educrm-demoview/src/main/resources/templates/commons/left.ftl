<div class="leftpanel">


    <div class="logopanel">
       <#-- <#if doMain??>
            <#if doMain=='2'>
                 <h1><span>[</span> 中航燃油 <span>]</span></h1>
            <#else>
                <h1><span>[</span> 苍龙跆拳道 <span>]</span></h1>
                &lt;#&ndash;<h1><span>[</span> 力迈中美（国际）学校 <span>]</span></h1>&ndash;&gt;
            </#if>
        </#if>-->
            <h1><span>[</span> ${(doMain)!} <span>]</span></h1>
           <#--<h1><span>[</span> 君华国际学校 <span>]</span></h1>-->
    </div><!-- logopanel -->
    <div class="leftpanelinner">

        <!-- This is only visible to small devices -->
        <div class="visible-xs hidden-sm hidden-md hidden-lg">
            <div class="media userlogged">
                <img alt="" src="${staticPath}/images/photos/blog3.jpg" class="media-object">
                <div class="media-body">
                    <h4>Mike</h4>
                    <span>"..."</span>
                </div>
            </div>

            <h5 class="sidebartitle actitle">账户</h5>
            <ul class="nav nav-pills nav-stacked nav-bracket mb30">
                <li><a href="/signout"><i class="fa fa-sign-out"></i> <span>退出</span></a></li>
            </ul>
        </div>
        <!--导航===================================-->
        <!-- <h5 class="sidebartitle">导航菜单</h5>-->
        <ul class="nav nav-pills nav-stacked nav-bracket" id="sysMenuId">
            <li><a href="/signon"><i class="fa fa-home"></i> <span>首页</span></a></li>
            <!--<li class="nav-parent"><a href=""><i class="fa fa-edit"></i> <span></span></a>-->

            <li class="nav-parent nav-active active"><a href=""><i class="fa fa-bookmark"></i> <span>市场管理</span></a>
                <ul class="children" style="display: block;">
                    <li class="active"><a href="/student/studentSignup/query"><i class="fa fa-caret-right"></i>意向学生</a>
                    </li>
                 <li><a href="/student/studentInterview/query"><i class="fa fa-caret-right"></i>新生面试</a></li><#---->
                 <li><a href="/student/studentReport/query"><i class="fa fa-caret-right"></i>新生报道</a></li><#---->
                 <li><a href="/student/studentListen/query"><i class="fa fa-caret-right"></i>新生试听</a></li><#---->
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-group"></i> <span>教学管理</span></a>
                <ul class="children">
                    <li><a href="/classinfo/classinfo/query"><i class="fa fa-caret-right"></i> 新生开班</a></li>
                    <li><a href="/classinfo/classon/query"><i class="fa fa-caret-right"></i> 班级升学</a></li>
                    <li><a href="/classinfo/classreturn/query"><i class="fa fa-caret-right"></i> 学生转班</a></li>
                    <li><a href="/student/studentSignups/query"><i class="fa fa-caret-right"></i> 学生报名</a></li>
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-file-text"></i> <span>教务管理</span></a>
                <ul class="children">
                    <li><a href="/student/student/query"><i class="fa fa-caret-right"></i>学生档案</a></li>
                 <li><a href="/classinfo/curriulum/query"><i class="fa fa-caret-right"></i>课程表</a></li><#---->
                 <li><a href="/student/studentSubjectResul/query"><i class="fa fa-caret-right"></i>科目成绩</a></li><#---->
                 <li><a href=""><i class="fa fa-caret-right"></i>老师评分</a></li><#---->
                 <li><a href="/classinfo/classinfoActivity/query"><i class="fa fa-caret-right"></i>社会实践活动</a></li><#---->
                 <li><a href="/student/studentActivityExp/query"><i class="fa fa-caret-right"></i>社会实践成绩</a></li><#---->
                 <li><a href="/student/studentAwards/query"><i class="fa fa-caret-right"></i>学生奖励</a></li><#---->
                 <li><a href="/student/studentBehaviorRecord/query"><i class="fa fa-caret-right"></i>学生违纪处分</a></li><#---->
                 <li><a href="/student/studentInterviewRecord/query"><i class="fa fa-caret-right"></i>学生访谈记录</a></li><#---->
                 <li><a href="/student/studentTestPlansScores/query"><i class="fa fa-caret-right"></i>学生成绩单</a></li><#---->
                 <li><a href="/student/studentPlans/query"><i class="fa fa-caret-right"></i>学业规划</a></li><#---->
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-money"></i> <span>财务管理</span></a>
                <ul class="children">
                    <li><a href="/finance/receivable/query"><i class="fa fa-caret-right"></i>应收款</a></li>
                    <li><a href="/stuReceiv/geStutreceivList"><i class="fa fa-caret-right"></i>学生收费</a></li>
                <#-- <li><a href="/payables/geStutrefundList"><i class="fa fa-caret-right"></i>学生退费</a></li>-->
                    <li><a href="/finance/expenseItem/query"><i class="fa fa-caret-right"></i>费用项目</a></li>
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-user"></i> <span>人事管理</span></a>
                <ul class="children">
                <#-- <li><a href="/system/employee/create"><i class="fa fa-caret-right"></i>员工入职</a></li>-->
                    <li><a href="/system/employee/query"><i class="fa fa-caret-right"></i>员工资料</a></li>
                <#-- <li><a href=""><i class="fa fa-caret-right"></i>员工离职</a></li>-->
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-hdd-o"></i> <span>宿舍管理</span></a>
                <ul class="children">
                <#-- <li><a href=""><i class="fa fa-caret-right"></i>宿舍分类</a></li>-->
                    <li><a href="/system/dormRoom/query"><i class="fa fa-caret-right"></i>宿舍维护</a></li>
                <#-- <li><a href=""><i class="fa fa-caret-right"></i>学生入住</a></li>-->
                <#-- <li><a href=""><i class="fa fa-caret-right"></i>学生退宿</a></li>-->
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-signal"></i> <span>报表分析</span></a>
                <ul class="children">
                    <li><a href=""><i class="fa fa-caret-right"></i>校区业绩报表</a></li>
                    <li><a href=""><i class="fa fa-caret-right"></i>校区学生变动情况</a></li>
                    <li><a href=""><i class="fa fa-caret-right"></i>项目业绩报表</a></li>
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-flag-o"></i> <span>基础资料</span></a>
                <ul class="children">
                    <li><a href="/system/domain/query"><i class="fa fa-caret-right"></i>校区管理</a></li>
                    <li><a href="/system/department/query"><i class="fa fa-caret-right"></i>部门资料</a></li>
                    <li><a href="/classinfo/classTime/query"><i class="fa fa-caret-right"></i>上课时间</a></li>
                    <li><a href="/classinfo/classRoom/query"><i class="fa fa-caret-right"></i>教室维护</a></li>
                    <li><a href="/system/sysDict/query"><i class="fa fa-caret-right"></i>数据字典</a></li>
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-cogs"></i> <span>系统管理</span></a>
                <ul class="children">
                    <li><a href="/system/sysUser/query"><i class="fa fa-caret-right"></i>用户管理</a></li>
                    <li><a href="/system/sysRole/query"><i class="fa fa-caret-right"></i>角色管理</a></li>
                    <li><a href="/system/sysAutocode/query"><i class="fa fa-caret-right"></i>编码规则</a></li>
                    <li><a href="/log/userlog"><i class="fa fa-caret-right"></i>操作日志</a></li>
                </ul>
            </li>

        </ul>
        <!--  导航结束-->


    </div><!-- leftpanelinner -->
</div><!-- leftpanel -->
<#--


<#--<script src="/js/jquery-1.11.1.min.js"></script>-->
<#--<script src="/js/jquery-migrate-1.2.1.min.js"></script>-->
<#--<script src="/js/bootstrap.min.js"></script>-->
<#--<script src="/js/modernizr.min.js"></script>-->
<#--<script src="/js/jquery.sparkline.min.js"></script>-->
<#--<script src="/js/toggles.min.js"></script>-->
<#--<script src="/js/retina.min.js"></script>-->
<#--<script src="/js/jquery.cookies.js"></script>-->



<#--<script src="/js/jquery.datatables.min.js"></script>-->
<#--<script type="text/javascript" language="javascript" src="/js/dataTables.bootstrap.js"></script>-->

<#--<script src="/js/custom.js"></script>-->
<#--<script src="/js/login/bootstrapValidator.min.js"></script>-->
<#--<script src="/js/login/bootstrap-notify.js"></script>-->
<#--<script src="/js/function.js?v=3"></script>-->



