
<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>Establish <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-success"  onClick="javascript:history.back(-1);">Return</a>
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>Essential information</strong></a></li>
                <!--   <li class=""><a data-toggle="tab" href="#assigned"><strong>Educational experience</strong></a></li>
                   <li class=""><a data-toggle="tab" href="#unresolved"><strong>Activity experience</strong></a></li>
                   <li class=""><a data-toggle="tab" href="#testplan"><strong>Examination plan</strong></a></li>
                   <li class=""><a data-toggle="tab" href="#added"><strong>Interview records</strong></a></li>
                   <li class=""><a data-toggle="tab" href="#discipline"><strong>Record of disciplinary action</strong></a></li>
              --> </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">

                    <form class="form-horizontal form-ajax" method="post" action="/finance/receivable/save"
                          data-target="/finance/receivable/query">
                        <div class="form-group row">
                            <label class="control-label col-lg-3">code</label>
                            <div class="col-lg-9">
                                <input name="rec.code" disabled="disabled" class="form-control" value="Default generation" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">Select students</label>
                            <div class="col-lg-9">
                                <input name="rec.pkStudent" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">item</label>
                            <div class="col-lg-9">
                                <input name="rec.pkExpenseItem" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">Cost</label>
                            <div class="col-lg-9">
                                <input name="rec.cost" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">Receivables</label>
                            <div class="col-lg-9">
                                <input name="rec.money" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">Agent</label>
                            <div class="col-lg-9">
                                <input name="rec.pkSysUser" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group row">
                            <label class="control-label col-lg-3">Remarks</label>
                            <div class="col-lg-9">
                                <input name="rec.notes" class="form-control" value="" title="" type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-success btn-sm">Determine</button>
                        </div>
                    </form>

                </div><!-- tab-content -->



            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->
</div>


<#include "../commons/footer.ftl" >
