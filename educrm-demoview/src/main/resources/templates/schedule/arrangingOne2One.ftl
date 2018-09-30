<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>一对一排课 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-newblue" onclick="javascript:history.back(-1);">返回</a>
            <a class="btn btn-sm btn-newblue " id="savebtn" onclick="sub()">保存</a>

        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <div class="tab-content">
                <div id="all" class="tab-pane active">
                    <form class="form-horizontal no-margin form-ajax bv-form" id="formId" method="post" action="/student/studentReport/saveall" data-target="/student/studentSignup/query" novalidate="novalidate">
                        <input id="pkStudentSignup" name="stu.pkStudentSignup" value="201821168431461863" type="hidden">
                        <input name="userKey" value="" type="hidden">
                        <div class="row">
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">学员</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.pkDomain" value="1" type="hidden">
                                        <input class="form-control" value="苍龙跆拳道" readonly="" title="" type="text">
                                    </div><!-- /.col -->
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">开始日期</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.dateTime" id="data2" class="form-control" value="" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group">
                                    <label class="control-label col-xs-3 col-md-3">课程</label>
                                    <div class="col-xs-9 col-md-9">
                                        <i class="fa fa-user c-icon"></i>
                                        <input id="stuCaption" readonly="" value="ceshiTest" class="form-control">
                                        <input id="pkStudent" name="stu.pkStudent" value="201820398037568871" type="hidden">
                                    </div><!-- /.col -->
                                </div>
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">结束日期</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.dateTime" id="data" class="form-control" value="" title="" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-4">
                                <div class="form-group ">
                                    <label class="control-label col-xs-3 col-md-3">老师</label>
                                    <div class="col-xs-9 col-md-9">
                                        <input name="stu.oldSchool" class="form-control" value="" title="" type="text">
                                    </div><!-- /.col -->
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label class="control-label col-xs-1">备注</label>
                                    <div class=" col-xs-11">
                                        <textarea rows="1" name="stu.memo" value="" class="form-control" title=""></textarea>
                                    </div><!-- /.col -->
                                </div>
                            </div>
                        </div>
                    </form>

                </div><!-- tab-content -->


            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->
<#include "../commons/footer.ftl"/>
