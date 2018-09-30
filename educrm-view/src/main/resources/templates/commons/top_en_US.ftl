<#import "../commons/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <#assign staticPath=''>
    <title></title>
    <link href="${staticPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
     <link href="${staticPath}/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet">
    <link href="${staticPath}/css/plug/bootstrapValidator.min.css" type="text/css" rel="stylesheet">
    <link href="${staticPath}/css/style.default.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/datepicker.css">
    <#--<link href="${staticPath}/css/notfound.css" rel="stylesheet">-->

    <link rel="stylesheet" type="text/css" href="${staticPath}/css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap-dialog.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap-notify.css">
   <#-- <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap-select.css">-->
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/tree.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/plus/Bootstrap-select/css/bootstrap-select.min.css">
    <#--<script src="${staticPath}/js/html5shiv.js"></script>-->
    <#--<script src="${staticPath}/js/respond.min.js"></script>-->
    <#--<script type="text/javascript" src="${staticPath}/js/uncompressed/moment.js"></script>-->
    <#--&lt;#&ndash;<script type="text/javascript" src="${staticPath}/js/uncompressed/daterangepicker.js"></script>&ndash;&gt;-->
    <link rel="stylesheet" href="${staticPath}/css/TreeGrid.css" />
    <link rel="stylesheet" href="${staticPath}/layer/skin/layer.css" />
    <link rel="stylesheet" href="${staticPath}/layer/skin/layer.ext.css" />
    <link rel="stylesheet" href="${staticPath}/css/course.css" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <![endif]-->

</head>

<body>


<!-- Preloader -->
<div id="preloader">
    <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>


<section>
    <div class="mainpanel"><!--mainpanel-->
        <div class="headerbar">

            <a class="menutoggle"><i class="fa fa-bars"></i></a>
            <div class="header-right">
                <ul class="headermenu">
                    <li>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                <img src="${staticPath}/images/photos/blog3.jpg" alt=""/>
                                ${(sessionUser)!}
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                                <li><a href="/signout"><i class="glyphicon glyphicon-log-out"></i> out</a></li>
                                <li><a data-toggle="modal" data-target="#modal" data-url="/updatePassword">edit password</a></li>
                            </ul>
                        </div>
                    </li>

                </ul>
            </div><!-- header-right -->

        </div><!-- headerbar -->
