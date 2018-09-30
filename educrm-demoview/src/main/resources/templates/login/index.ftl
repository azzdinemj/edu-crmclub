
<#include "../commons/top.ftl"/>
<#include "../commons/left.ftl"/>


    <div class="pageheader">
        <h2><i class="fa fa-home"></i>首页 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <span class="label">你的位置:</span>
            <ol class="breadcrumb">
                <li><a href="index.ftl">返回</a></li>
                <li class="active">首页</li>
            </ol>
        </div>
    </div>
    <!--==============中间部分=======================-->
    <div class="contentpanel">
        <div class="padding-md">
            <div class="row home">
                <div class="col-lg-6 col-md-6 col-xs-12">
                    <div class="social-widget left-widget">
                        <div class="smart-widget">
                            <div class="smart-widget-header">
                                <span class="badge badge-success pull-right">${(num)!}</span>
                                公告
                            </div>
                            <#if noticeList??>
                                <#list noticeList as notice >
                                    <div class="paddingTB-sm paddingLR-md panel">
                                        <div class="m-bottom-md clearfix">
                                            <#--<div class="user-profile-pic clearfix">
                                                <img src="images/photos/blog3.jpg" alt="">
                                            </div>-->
                                            <div class="user-name">
                                                <div class="panel-heading">
                                                    <div class="panel-btns">
                                                        <a href="" title="关闭此消息" class="panel-close">×</a>
                                                        <a href="" class="minimize">−</a>
                                                    </div><!-- panel-btns -->
                                                    <h3 class="panel-title"><strong>${(notice.title)!}</strong></h3>
                                                </div>

                                                <div class="panel-body">
                                                    <p class="m-top-sm">
                                                        ${(notice.content)!}
                                                    </p>
                                                    <i>${(notice.creationDate?string("yyyy-MM-dd"))!}</i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </#if>
                        </div>

                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-xs-12">
                    <div class="social-widget left-widget">
                        <div class="smart-widget">
                            <div class="smart-widget-header">
                                <span class="badge badge-danger pull-right">3</span>
                                部门信息
                            </div>
                            <div class="paddingTB-sm paddingLR-md panel">
                                <div class="m-bottom-md clearfix">
                                <#--<div class="user-profile-pic clearfix">
                                    <img src="images/photos/blog3.jpg" alt="">
                                </div>-->
                                    <div class="user-name">
                                        <div class="panel-heading">
                                            <div class="panel-btns">
                                                <a href="" title="关闭此消息" class="panel-close">×</a>
                                                <a href="" class="minimize">−</a>
                                            </div><!-- panel-btns -->
                                            <h3 class="panel-title"><strong>Mike</strong></h3>
                                        </div>

                                        <div class="panel-body">
                                            <p class="m-top-sm">
                                                添加成员：<a href="/">莉莉</a>
                                            </p>
                                            <i>2017-05-06</i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="paddingTB-sm paddingLR-md panel">
                                <div class="m-bottom-md clearfix">
                                <#-- <div class="user-profile-pic clearfix">
                                     <img src="images/photos/blog3.jpg" alt="">
                                 </div>-->
                                    <div class="user-name">
                                        <div class="panel-heading">
                                            <div class="panel-btns">
                                                <a href="" title="关闭此消息" class="panel-close">×</a>
                                                <a href="" class="minimize">−</a>
                                            </div><!-- panel-btns -->
                                            <h3 class="panel-title"><strong>Mike</strong></h3>
                                        </div>

                                        <div class="panel-body">
                                            <p class="m-top-sm">
                                                添加成员：<a href="/">莉莉</a>
                                            </p>
                                            <i>2017-05-06</i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="paddingTB-sm paddingLR-md panel">
                                <div class="m-bottom-md clearfix">
                                <#-- <div class="user-profile-pic clearfix">
                                     <img src="images/photos/blog3.jpg" alt="">
                                 </div>-->
                                    <div class="user-name">
                                        <div class="panel-heading">
                                            <div class="panel-btns">
                                                <a href="" title="关闭此消息" class="panel-close">×</a>
                                                <a href="" class="minimize">−</a>
                                            </div><!-- panel-btns -->
                                            <h3 class="panel-title"><strong>Mike</strong></h3>
                                        </div>

                                        <div class="panel-body">
                                            <p class="m-top-sm">
                                                成功收费：<a href="/">莉莉</a>
                                            </p>
                                            <i>2017-05-06</i>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-xs-12">
                    <div class="social-widget">
                        <div class="smart-widget">
                            <div class="smart-widget-header">
                                <a href="#addanything" title="添加事项" data-toggle="modal" class="btn btn-sm pull-right"><i class="fa fa-plus"></i></a>
                                我的任务
                            </div>
                            <div class="smart-widget-inner">
                                <div class="smart-widget-body clearfix">
                                    <div class="search-input pull-left">
                                        <a href="#" class="input-icon text-normal"></a>
                                    </div>
                                </div>
                                <ul class="list-group no-border">
                                    <#if taskList??>
                                        <#list taskList as task>
                                            <li class="list">
                                                <i class="fa fa-circle color"></i><span class="sender-name ng-binding">${(task.endDate?string("yyyy-MM-dd"))!}</span>
                                                <span class="news">${(task.content)!}</span>
                                            </li>
                                        </#list>
                                    </#if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div><!-- contentpanel -->

<#--<div class="modal fade" id="addanything">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="panel panel-dark">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                            class="sr-only">Close</span></button>
                    <h4 class="modal-title">添加事件</h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="smart-widget-inner">
                    <div class="smart-widget-body">
                        <form class="form-horizontal no-margin" method="post">
                            <div class="form-group">
                                <label class="control-label col-lg-3">事项</label>
                                <div class="col-lg-9">
                                    <input required="" class="form-control" maxlength="30" value="" title=""
                                           type="text">
                                </div><!-- /.col &ndash;&gt;
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-3">时间</label>
                                <div class="col-lg-9">
                                    <input class="form-control" placeholder="mm/dd/yyyy" id="datepicker-multiple"
                                           type="text">
                                </div>

                                &lt;#&ndash;<div class="modal-footer">&ndash;&gt;
                                    &lt;#&ndash;<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消&ndash;&gt;
                                    &lt;#&ndash;</button>&ndash;&gt;
                                    &lt;#&ndash;<button type="submit" class="btn btn-info btn-sm" data-dismiss="modal">确定</button>&ndash;&gt;
                                &lt;#&ndash;</div>&ndash;&gt;
                        </form>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>-->


<#--<script src="js/jquery-1.11.1.min.js"></script>-->
<#--<script src="js/jquery-migrate-1.2.1.min.js"></script>-->
<#--<script src="js/jquery-ui-1.10.3.min.js"></script>-->
<#--<script src="js/bootstrap.min.js"></script>-->
<#--<script src="js/modernizr.min.js"></script>-->
<#--<script src="js/jquery.sparkline.min.js"></script>-->
<#--<script src="js/toggles.min.js"></script>-->
<#--<script src="js/retina.min.js"></script>-->
<#--<script src="js/jquery.cookies.js"></script>-->

<#--<script src="js/flot/jquery.flot.min.js"></script>-->
<#--<script src="js/flot/jquery.flot.resize.min.js"></script>-->
<#--<script src="js/flot/jquery.flot.spline.min.js"></script>-->
<#--<script src="js/morris.min.js"></script>-->
<#--<script src="js/raphael-2.1.0.min.js"></script>-->

<#--<script src="js/custom.js"></script>-->
<#--<script src="js/dashboard.js"></script>-->
<#include "../commons/footer.ftl" />
