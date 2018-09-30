
<div class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               部门信息
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-horizontal no-margin form-ajax" method="post" action="/system/department/save" data-target="/system/department/query">
                <input type="hidden" name="dep.pkDepartment" value="${(department.pkDepartment)!}" />
                <#--<input type="hidden" name="dep.pkDomain" value="${(department.pkDomain)!}">-->

                <div class="row">
                    <div class="col-md-4 col-xs-12">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">所属公司</label>
                            <div class="col-md-9 col-xs-9">
                                <input readonly  class="form-control"  value="${(domain.caption)!}" type="text">
                                <input readonly name="dep.pkDomain" class="form-control"  value="${(domain.pkDomain)!}" type="hidden">
                            </div><!-- /.col -->
                        </div>


                        <div class="form-group row">
                            <label class="control-label col-md-3 col-xs-3">部门名称</label>
                            <div class="col-md-9 col-xs-9">
                                <input name="dep.caption"  class="form-control" value="${(department.caption)!}" data-bv-notempty data-bv-notempty-message="请录入部门名称">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group row">
                            <label class="control-label col-md-3 col-xs-3">部门主管</label>
                            <div class="col-md-9 col-xs-9">
                                <input  name="dep.pkSysUser" class="form-control" value="${(department.pkSysUser)!}" >
                            </div><!-- /.col -->
                        </div>



                        <div class="form-group row">
                            <label class="control-label col-md-3 col-xs-3">传&emsp;&emsp;真</label>
                            <div class="col-md-9 col-xs-9">
                                <input  name="dep.fax" class="form-control" value="${(department.fax)!}" >
                            </div><!-- /.col -->
                        </div>



                    </div>
                    <div  class="col-md-4 col-xs-12">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">上级部门</label>
                            <div class="col-md-9 col-xs-9">
                                <input  readonly class="form-control" value="${(parent.caption)!}">
                                <input name="dep.pkParent" readonly class="form-control" value="${(parent.pkDepartment)!}" type="hidden">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-lg-3">部门编码</label>
                            <div class="col-lg-9">
                                <input readonly name="dep.code" class="form-control"  value="${(department.code)!}" type="text">
                            </div><!-- /.col -->
                        </div>


                        <div class="form-group">
                            <label class="control-label col-lg-3">电&emsp;&emsp;话</label>
                            <div class="col-lg-9">
                                <input  name="dep.phone" class="form-control" value="${(department.phone)!}">
                            </div><!-- /.col -->
                        </div>


                        <div class="form-group">
                            <label class="control-label col-lg-3">邮&emsp;&emsp;编</label>
                            <div class="col-lg-9">
                                <input name="dep.zip" class="form-control" value="${(department.zip)!}" >
                            </div><!-- /.col -->
                        </div>



                    </div>
                    <div class="col-md-4 col-xs-12">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">是否启用</label>
                            <div class="col-md-9 col-xs-9 pt7">
                                    <span class="rdio rdio-warning">
                                        <input name="dep.isvalid" type="radio" id="radio3" value="${(department.isvalid)!}" <#if department.isvalid?? && department.isvalid ==2><#else >checked="checked"</#if>>
                                        <label for="radio3">是&emsp;</label>
                                    </span>
                                <span class="rdio rdio-warning">
                                        <input name="dep.isvalid" type="radio" id="radio4" value="${(department.isvalid)!}" <#if department.isvalid?? && department.isvalid ==2>checked="checked"</#if>>
                                        <label for="radio4">否&emsp;</label>
                                    </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">类&emsp;&emsp;型</label>
                            <div class="col-md-9 col-xs-9 pt7">
                                    <span class="rdio rdio-warning">
                                        <input name="dep.kind" type="radio" id="radio1" value="${(department.kind)!}" <#if department.kind?? && department.kind ==2><#else >checked="checked"</#if>>
                                        <label for="radio1">部门</label>
                                    </span>
                                <span class="rdio rdio-warning">
                                        <input name="dep.kind" type="radio" id="radio2" value="${(department.kind)!}" <#if department.kind?? && department.kind ==2>checked="checked"</#if>>
                                        <label for="radio2">小组</label>
                                    </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">部门地址</label>
                            <div class="col-md-9 col-xs-9">
                                <input name="dep.address" class="form-control" value="${(department.address)!}">
                            </div><!-- /.col -->
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3">邮&emsp;&emsp;箱</label>
                            <div class="col-md-9 col-xs-9">
                                <input name="dep.email" class="form-control" value="${(department.email)!}" maxlength="6">
                            </div><!-- /.col -->
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group" >
                            <label class="control-label col-md-1 col-xs-1">备&emsp;&emsp;注</label>
                            <div class="col-md-11 col-xs-11">
                                <textarea  rows="4" name="dep.memo" value="${(department.memo)!}" class="form-control"  title=""></textarea>
                            </div><!-- /.col -->
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3 col-xs-3">
                        <div class="form-group ">
                            <label class="control-label new-left col-md-3 col-xs-3">创建人</label>
                            <div class="col-md-9 col-xs-9 new-right">
                                <input readonly class="form-control" value="${(department.map.creatorEntity.caption)!}"   type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-3">
                        <div class="form-group">
                            <label class="control-label col-md-4 col-xs-4">创建时间</label>
                            <div class="col-md-8 col-xs-8">
                                <input readonly class="form-control" value="${(department.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}"   type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 col-xs-3">
                        <div class="form-group ">
                            <label class="control-label col-md-3 col-xs-3">修改人</label>
                            <div class="col-md-9 col-xs-9">
                                <input readonly class="form-control" value="${(department.map.modifierEntity.caption)!}"   type="text">
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3 col-xs-3">
                        <div class="form-group">
                            <label class="control-label col-md-4 col-xs-4">修改时间</label>
                            <div class="col-md-8 col-xs-8">
                                <input readonly class="form-control" value="${(department.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}"   type="text">
                            </div>
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
</div><!-- /.modal -->

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
