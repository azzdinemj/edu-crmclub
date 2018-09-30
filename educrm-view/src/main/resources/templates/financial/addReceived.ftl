
<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>创建 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-success"  onClick="javascript:history.back(-1);">返回</a>
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>基本信息</strong></a></li>
                <!--   <li class=""><a data-toggle="tab" href="#assigned"><strong>教育经历</strong></a></li>
                   <li class=""><a data-toggle="tab" href="#unresolved"><strong>活动经历</strong></a></li>
                   <li class=""><a data-toggle="tab" href="#testplan"><strong>考试计划</strong></a></li>
                   <li class=""><a data-toggle="tab" href="#added"><strong>访谈记录</strong></a></li>
                   <li class=""><a data-toggle="tab" href="#discipline"><strong>纪律行为记录</strong></a></li>
              --> </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">

                    <form class="form-horizontal form-ajax" method="post" action="/finance/receivable/save"
                          data-target="/finance/receivable/query">
                        <div class="form-group row">
                            <label class="control-label col-lg-3">单号</label>
                            <div class="col-lg-9">
                                <input name="rec.code" disabled="disabled" class="form-control" value="默认生成" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">选择学员</label>
                            <div class="col-lg-9">
                                <input name="rec.pkStudent" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">费用项目</label>
                            <div class="col-lg-9">
                                <input name="rec.pkExpenseItem" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">费用</label>
                            <div class="col-lg-9">
                                <input name="rec.cost" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">已收款</label>
                            <div class="col-lg-9">
                                <input name="rec.money" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">经办人</label>
                            <div class="col-lg-9">
                                <input name="rec.pkSysUser" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">备注</label>
                            <div class="col-lg-9">
                                <input name="rec.notes" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-success btn-sm">确定</button>
                        </div>
                    </form>

                </div><!-- tab-content -->



            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->
</div>


<#include "../commons/footer.ftl" >
