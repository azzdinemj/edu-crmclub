
<div class="modal-dialog lg-modal">
    <div class="modal-content" >
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                学部年级<span style="color: #2fa0ec;margin-left: 15px;">(请按照年纪排序填写(</span>
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" method="POST" method="post" action="/system/divisionGrade/save"
                  data-target="/system/divisionGrade/query">
                <input name="pkDivisionGrade" class="form-control" value="${(division.pkDivisionGrade)!}" type="hidden">
                <div class="row">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">学部</label>
                            <div class="col-xs-9 col-md-9">
                                <#if divisionList??>
                                <select name="divisionId">
                                    <#list divisionList as d>
                                    <option value="${(d.pkSysDictValues)!}" <#if division?? &&d.pkSysDictValues??&&  division.divisionId?? && division.divisionId==d.pkSysDictValues>selected</#if>>${(d.caption)!}</option>
                                    </#list>
                                </select>
                                </#if>
                            </div><!-- /.col -->
                        </div>


                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">年级名称</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="gradeName" class="form-control" value="${(division.gradeName)!}" data-bv-notempty data-bv-notempty-message="请录入校区名称" title="" type="text">
                            </div><!-- /.col -->
                        </div>


                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">年级排序</label>
                            <div class="col-xs-9 col-md-9">
                                <input name="sort"  class="form-control" value="${(division.sort)!}" title="" type="number">
                            </div><!-- /.col -->
                        </div>
                    </div>


                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-12">
                        <div class="form-group">
                            <label class="control-label col-xs-1 col-md-1">备注</label>
                            <div class="col-xs-11 col-md-11">
                                <textarea  rows="4" name="memo" value="${(division.memo)!}" class="form-control"  title=""></textarea>
                            </div><!-- /.col -->
                        </div>
                    </div>
                </div>
<br>
                <div class="row">
                    <div class="col-xs-12 col-md-3">
                        <div class="form-group ">
                            <label class="control-label new-left col-xs-3">创建人</label>
                            <div class="col-xs-9 new-right">
                                <input readonly class="form-control" value="${(division.map.creatorEntity.caption)!}"   type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-3">
                        <div class="form-group">
                            <label class="control-label col-xs-4">创建时间</label>
                            <div class="col-xs-8">
                                <input readonly class="form-control" value="${(division.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}"   type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-3">
                        <div class="form-group ">
                            <label class="control-label col-xs-3">修改人</label>
                            <div class="col-xs-9">
                                <input readonly class="form-control" value="${(division.map.modifierEntity.caption)!}"   type="text">
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-3">
                        <div class="form-group">
                            <label class="control-label col-xs-4">修改时间</label>
                            <div class="col-xs-8">
                                <input readonly class="form-control" value="${(division.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}"   type="text">
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