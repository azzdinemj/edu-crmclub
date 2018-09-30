<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                费用项目
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" id="formSumbit" method="POST" action="/finance/expenseItem/save" data-target="/finance/expenseItem/query">
                <input name="exp.pkExpenseItem" value="${(expenseItem.pkExpenseItem)!}" type="hidden">
                <div class="panel panel-default">
                    <div class="tab-content">
                        <div id="all" class="tab-pane active">
                            <div class="row">

                                <div class="col-xs-12 col-md-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">校区名称</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input  value="${(domain.caption)!}" readonly class="form-control"  title="" type="text">
                                            <input name="exp.pkDomain" value="${(domain.pkDomain)!}"    title="" type="hidden">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">编号</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="exp.code" value="${(expenseItem.code)!}" readonly class="form-control"  title="" type="text">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-12 col-md-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">名称</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input name="exp.caption" value="${(expenseItem.caption)!}" class="form-control" title="" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">费用状态</label>
                                        <div class="col-xs-4 col-md-4 pt7">
                                            <div class="rdio rdio-warning">
                                                <input  id="wx" name="exp.isvalid" value="1" <#if expenseItem.isvalid??&& expenseItem.isvalid==0><#else >checked="checked" </#if> type="radio">
                                                <label for="wx">收</label>
                                            </div>
                                        </div>
                                        <div class="col-xs-4 col-md-4 pt7">
                                            <div class="rdio rdio-warning">
                                                <input  id="zfb" name="exp.isvalid" value="0" <#if expenseItem.isvalid??&& expenseItem.isvalid ==0>checked="checked"</#if>type="radio">
                                                <label for="zfb">付</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-xs-1">备注</label>
                                        <div class="col-xs-11">
                                              <textarea class="form-control" name="exp.memo" rows="4" va>${(expenseItem.memo)!}</textarea>
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12 col-md-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-2 col-md-2">创建人</label>
                                        <div class="col-xs-10 col-md-10">
                                            <input readonly class="form-control" value="${(expenseItem.map.creatorEntity.caption)!}" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">创建时间</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control" value="${expenseItem.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-2 col-md-2">修改人</label>
                                        <div class="col-xs-10 col-md-10">
                                            <input readonly class="form-control" value="${(expenseItem.map.modifierEntity.caption)!}" type="text"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-6">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3 col-md-3">修改时间</label>
                                        <div class="col-xs-9 col-md-9">
                                            <input readonly class="form-control" value="${expenseItem.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
                <div class="modal-footer">
                    <input type="hidden" id="menuList" name="menuList">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
                    <input class="btn btn-newblue btn-sm saveButton" name="dosubmit" value="保存" type="submit">
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
    $('.form-ajax').bootstrapValidator().on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function (data) {

            if (data.code == 0) {
                Notify.success(data.message);
                if ($form.data('target')) {
                    setTimeout("window.location.href='" + $form.data('target') + "'", 3000);
                }
            } else {
                Notify.danger(data.message);
            }
        }, 'json');
    });
</script>