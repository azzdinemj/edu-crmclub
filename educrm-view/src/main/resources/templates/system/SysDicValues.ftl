
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                新建数据
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-horizontal form-ajax" method="post" action="/system/sysDictValues/save"
                  data-target="/system/sysDictValues/query?pkSysDict=${(sysDic.pkSysDict)!}">

                <input type="hidden" name="sds.pkSysDictValues" value="${(sysSm.pkSysDictValues)!}">
                <input type="hidden" name="sds.pkDomain" value="${(sysSm.pkDomain)!}">
                <input type="hidden" name="bykey" value="${(sysSm.bykey)!}">

                <div class="row">
                    <div class="col-xs-12 col-md-6 ">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">字典名称</label>
                            <div class="col-xs-9 col-md-9">
                                <input value="${(sysDic.caption)!}" class="form-control" readonly>
                                <input name="sds.pkSysDict" value="${(sysDic.pkSysDict)!}" type="hidden">
                                <#--<select name="sds.pkSysDict">-->
                                    <#--<option value="${(sysDic.pkSysDict)!}">${(sysDic.caption)!}</option>-->
                                <#--</select>-->

                            </div>
                        </div><!-- /.col -->
                        <div class="form-group">
                            <label class="control-label col-lg-3">数据名称</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="sds.caption" value="${(sysSm.caption)!}" class="form-control" data-bv-notempty data-bv-notempty-message="请录入字典名称"  type="text">
                            </div>
                        </div><!-- /.col -->

                        <div class="form-group">

                                <label class="control-label col-lg-3">是否默认</label>
                                <div class="col-xs-9 col-md-9 pt7">
                                    <span class="rdio rdio-warning">
                                        <input name="sds.isdefault" type="radio" id="radio1" value=1 <#if sysSm.isdefault?? && sysSm.isdefault ==1>checked="checked"</#if>>
                                        <label for="radio1">是</label>
                                    </span>
                                    <span class="rdio rdio-warning">
                                        <input name="sds.isdefault" type="radio" id="radio2" value=2 <#if sysSm.isdefault?? && sysSm.isdefault ==1><#else >checked="checked"</#if>>
                                        <label for="radio2">否</label>
                                    </span>
                                </div>

                        </div><!-- /.col -->


                    </div><!-- /.col-6 -->


                    <div class="col-xs-12 col-md-6">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">是否启用</label>
                            <div class="col-xs-9 col-md-9 pt7">
                                 <span class="rdio rdio-warning">
                                   <input name="sds.isvalid" value="1" <#if sysSm.isvalid??&& sysSm.isvalid ==2 ><#else >checked="checked"</#if> id="radio3" type="radio">
                                   <label for="radio3">是</label>
                               </span>
                                <span class="rdio rdio-warning">
                                   <input name="sds.isvalid" value="2" id="radio4" <#if sysSm.isvalid??&& sysSm.isvalid ==2 >checked="checked"</#if> type="radio">
                                   <label for="radio4">否</label>
                               </span>
                            </div>

                        </div><!-- /.col -->
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">标识值</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="sds.value" value="${(sysSm.value)!}" class="form-control" type="text">
                            </div>
                        </div><!-- /.col -->
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">排序</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="sds.sort"  value="${(sysSm.sort)!}" class="form-control"  title="" type="number">
                            </div>
                        </div><!-- /.col -->


                    </div>

                </div>

                <div class="row">
                    <div class="col-xs-12 col-md-12">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">备注</label>
                            <div class="col-xs-10 col-md-10">
                                <textarea  rows="4" name="sds.memo" value="${(sysSm.memo)!}" class="form-control"  title=""></textarea>
                            </div><!-- /.col -->
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group ">
                            <label class="control-label col-xs-3 col-md-3">创建人</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly name="sds.creator" class="form-control" value="${(sysSm.map.creatorEntity.caption)!}"   type="text">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group ">
                            <label class="control-label  col-xs-3 col-md-3">修改人</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly name="sds.modifier" class="form-control" value="${(sysSm.map.modifierEntity.caption)!}"   type="text">
                            </div><!-- /.col -->
                        </div>

                    </div>
                    <div class="col-xs-6">
                        <div class="form-group ">
                            <label class="control-label col-xs-3 col-md-3">创建时间</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly class="form-control" name="sds.creationDateTime" value="${(sysSm.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}"   type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group ">
                            <label class="control-label col-xs-3 col-md-3">修改时间</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly class="form-control" name="sds.lasteditDateTime" value="${(sysSm.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}"   type="text">
                            </div><!-- /.col -->
                        </div>

                    </div>

                </div>


                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-newblue btn-sm">确定</button>
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->



<script type="text/javascript">
    $('.form-ajax').bootstrapValidator().on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function(data) {

            if(data.code==0){
                Notify.success(data.message);
                if($form.data('target')){
                    setTimeout("window.location.href='"+$form.data('target')+"'",3000);
                }
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });
</script>