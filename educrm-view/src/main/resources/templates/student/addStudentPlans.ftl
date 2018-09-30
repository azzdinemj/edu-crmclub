<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                新建学业规划
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin look form-ajax"  method="post" action="/student/studentPlans/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">校区名称</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="${(domain.caption)!}" readonly title="" type="text">
                            <input class="form-control" name="plan.pkDomain" value="${(domain.pkDomain)!}" title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">学生姓名</label>
                        <div class="col-lg-9">
                            <input class="form-control"  value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="plan.pkStudent" value="${(student.pkStudent)!}" readonly title="" type="hidden">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">申请国家</label>
                        <div class="col-lg-9">
                            <#--<input required="" name="plan.targetCountries" class="form-control"  title="" type="text">-->
                            <select name="plan.targetCountries">
                                <option value="">请选择</option>
                            <#if country ??>
                                <#list country as co>
                                    <option value="${(co.pkSysDictValues)!}"
                                            <#if co.pkSysDictValues?? && plans.targetCountries ?? && plans.targetCountries ==co.pkSysDictValues>selected</#if>>${(co.caption)!}</option>
                                </#list>

                            </#if>
                            </select>
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">毕业规划</label>
                        <div class="col-lg-9">
                            <input name="plan.ofterCollegeGraduation" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>
                </div>
                <div class="col-sm-6">

                    <div class="form-group">
                        <label class="control-label col-lg-3">喜欢的科目</label>
                        <div class="col-lg-9">
                            <#--<input name="plan.subjectsLike" class="form-control" title="" type="text">-->
                            <select name="plan.subjectsLike" class="form-control selectpicker show-tick "  data-live-search="true">
                                <option value="">请选择</option>
                            <#if discipline ??>
                                <#list discipline as d>
                                    <option value="${(d.pkSysDictValues)!}"
                                            <#if d.pkSysDictValues?? && plans.subjectsLike ?? && plans.subjectsLike ==d.pkSysDictValues>selected</#if>>${(d.caption)!}</option>
                                </#list>

                            </#if>
                            </select>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">擅长的科目</label>
                        <div class="col-lg-9">
                            <#--<input name="plan.subjectsGood" class="form-control"  title="" type="text">-->
                            <select name="plan.subjectsGood" class="form-control selectpicker show-tick "  data-live-search="true">
                                <option value="">请选择</option>
                            <#if discipline ??>
                                <#list discipline as d>
                                    <option value="${(d.pkSysDictValues)!}"
                                            <#if d.pkSysDictValues?? && plans.subjectsGood ?? && plans.subjectsGood ==d.pkSysDictValues>selected</#if>>${(d.caption)!}</option>
                                </#list>
                            </#if>
                            </select>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">计划专业</label>
                        <div class="col-lg-9">
                            <input name="plan.majorsWant" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">计划工作</label>
                        <div class="col-lg-9">
                            <input name="plan.workWant" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>

                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">选择大学因素</label>
                    <div class="col-lg-10">
                        <textarea class="form-control" name="plan.collegeChoosing" rows="2" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-sm-2">备&emsp;&emsp;注</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="plan.notes" rows="4" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">创建人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(plans.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">创建时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${plans.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">修改人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(plans.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">修改时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${plans.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-success btn-sm">确定</button>
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

    $('.selectpicker').selectpicker({

    });
</script>
<#--<script src="../plus/"></script>-->
<#--<script src="../../public/js/jquery-1.11.1.min.js"></script>-->
<#--<script src="../../public/plus/Bootstrap-select/js/bootstrap-select.js"></script>-->
