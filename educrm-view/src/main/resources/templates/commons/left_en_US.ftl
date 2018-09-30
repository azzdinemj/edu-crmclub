<div class="leftpanel">


    <div class="logopanel">
       <#-- <#if doMain??>
            <#if doMain=='2'>
                 <h1><span>[</span> CAC fuel <span>]</span></h1>
            <#else>
                <h1><span>[</span> Cang Dragon Taekwondo <span>]</span></h1>
                &lt;#&ndash;<h1><span>[</span> China and the United States（international）School <span>]</span></h1>&ndash;&gt;
            </#if>
        </#if>-->
            <h1><span>[</span> ${(doMain)!} <span>]</span></h1>
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

            <h5 class="sidebartitle actitle">account</h5>
            <ul class="nav nav-pills nav-stacked nav-bracket mb30">
                <li><a href="/signout"><i class="fa fa-sign-out"></i> <span>out</span></a></li>
            </ul>
        </div>
        <!--Navigation===================================-->
        <!-- <h5 class="sidebartitle">Navigation menu</h5>-->
        <ul class="nav nav-pills nav-stacked nav-bracket" id="sysMenuId">
            <li><a href="/signon"><i class="fa fa-home"></i> <span>home page</span></a></li>
            <!--<li class="nav-parent"><a href=""><i class="fa fa-edit"></i> <span></span></a>-->

            <li class="nav-parent nav-active active"><a href=""><i class="fa fa-bookmark"></i> <span>Market management</span></a>
                <ul class="children" style="display: block;">
                    <li class="active"><a href="/student/studentSignup/query"><i class="fa fa-caret-right"></i>Intentional students</a>
                    </li>
                 <li><a href="/student/studentInterview/query"><i class="fa fa-caret-right"></i>Freshman interview</a></li><#---->
                 <li><a href="/student/studentReport/query"><i class="fa fa-caret-right"></i>New report</a></li><#---->
                 <li><a href="/student/studentListen/query"><i class="fa fa-caret-right"></i>New Auditions</a></li><#---->
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-group"></i> <span>Teaching management</span></a>
                <ul class="children">
                    <li><a href="/classinfo/classinfo/query"><i class="fa fa-caret-right"></i> Freshmen</a></li>
                    <li><a href="/classinfo/classon/query"><i class="fa fa-caret-right"></i> Class promotion</a></li>
                    <li><a href="/classinfo/classreturn/query"><i class="fa fa-caret-right"></i> Student shift</a></li>
                    <li><a href="/student/studentSignups/query"><i class="fa fa-caret-right"></i> Student registration</a></li>
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-file-text"></i> <span>Teaching Management</span></a>
                <ul class="children">
                    <li><a href="/student/student/query"><i class="fa fa-caret-right"></i>Student files</a></li>
                 <li><a href="/classinfo/curriulum/query"><i class="fa fa-caret-right"></i>schedule</a></li><#---->
                 <li><a href="/student/studentSubjectResul/query"><i class="fa fa-caret-right"></i>Subject achievement</a></li><#---->
                 <li><a href=""><i class="fa fa-caret-right"></i>Teacher score</a></li><#---->
                 <li><a href="/classinfo/classinfoActivity/query"><i class="fa fa-caret-right"></i>Social</a></li><#---->
                 <li><a href="/student/studentActivityExp/query"><i class="fa fa-caret-right"></i>Social score</a></li><#---->
                 <li><a href="/student/studentAwards/query"><i class="fa fa-caret-right"></i>reward</a></li><#---->
                 <li><a href="/student/studentBehaviorRecord/query"><i class="fa fa-caret-right"></i>Disobedience</a></li><#---->
                 <li><a href="/student/studentInterviewRecord/query"><i class="fa fa-caret-right"></i>Interviews</a></li><#---->
                 <li><a href="/student/studentTestPlansScores/query"><i class="fa fa-caret-right"></i>Transcript</a></li><#---->
                 <li><a href="/student/studentPlans/query"><i class="fa fa-caret-right"></i>planning</a></li><#---->
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-money"></i> <span>financial</span></a>
                <ul class="children">
                    <li><a href="/finance/receivable/query"><i class="fa fa-caret-right"></i>Receivables</a></li>
                    <li><a href="/stuReceiv/geStutreceivList"><i class="fa fa-caret-right"></i>Student fees</a></li>
                <#-- <li><a href="/payables/geStutrefundList"><i class="fa fa-caret-right"></i>refund</a></li>-->
                    <li><a href="/finance/expenseItem/query"><i class="fa fa-caret-right"></i>item</a></li>
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-user"></i> <span>personnel</span></a>
                <ul class="children">
                <#-- <li><a href="/system/employee/create"><i class="fa fa-caret-right"></i>Onboarding</a></li>-->
                    <li><a href="/system/employee/query"><i class="fa fa-caret-right"></i>Employee information</a></li>
                <#-- <li><a href=""><i class="fa fa-caret-right"></i>Departure</a></li>-->
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-hdd-o"></i> <span>Dormitory</span></a>
                <ul class="children">
                <#-- <li><a href=""><i class="fa fa-caret-right"></i>Dormitory Category</a></li>-->
                    <li><a href="/system/dormRoom/query"><i class="fa fa-caret-right"></i>Dormitory maintenance</a></li>
                <#-- <li><a href=""><i class="fa fa-caret-right"></i>Stay</a></li>-->
                <#-- <li><a href=""><i class="fa fa-caret-right"></i>Tuisu</a></li>-->
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-signal"></i> <span>Report analysis</span></a>
                <ul class="children">
                    <li><a href=""><i class="fa fa-caret-right"></i>School district performance report</a></li>
                    <li><a href=""><i class="fa fa-caret-right"></i>Changes in campus students</a></li>
                    <li><a href=""><i class="fa fa-caret-right"></i>Project performance report</a></li>
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-flag-o"></i> <span>Biography</span></a>
                <ul class="children">
                    <li><a href="/system/domain/query"><i class="fa fa-caret-right"></i>Campus management</a></li>
                    <li><a href="/system/department/query"><i class="fa fa-caret-right"></i>Departmental information</a></li>
                    <li><a href="/classinfo/classTime/query"><i class="fa fa-caret-right"></i>Class time</a></li>
                    <li><a href="/classinfo/classRoom/query"><i class="fa fa-caret-right"></i>Classroom maintenance</a></li>
                    <li><a href="/system/sysDict/query"><i class="fa fa-caret-right"></i>data dictionary</a></li>
                </ul>
            </li>
            <li class="nav-parent"><a href=""><i class="fa fa-cogs"></i> <span>system management</span></a>
                <ul class="children">
                    <li><a href="/system/sysUser/query"><i class="fa fa-caret-right"></i>user management</a></li>
                    <li><a href="/system/sysRole/query"><i class="fa fa-caret-right"></i>Role management</a></li>
                    <li><a href="/system/sysAutocode/query"><i class="fa fa-caret-right"></i>Coding rules</a></li>
                    <li><a href="/log/userlog"><i class="fa fa-caret-right"></i>Operation log</a></li>
                </ul>
            </li>

        </ul>
        <!--  Navigation end-->


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



