
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Result modification
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax form-horizontal" method="post" action="/classinfo/studentScoress/save"
                  data-target="/classinfo/classinfo/edit?pkClassinfo=${(studentScore.pkClassinfo)!}">
                <input type="hidden" name="sc.pkDomain" value="${(studentScore.pkDomain)!}">
                <input type="hidden" name="sc.pkStudentTestPlansScores" value="${(studentScore.pkStudentTestPlansScores)!}">
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">Score number</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly name="sc.code" value="${(studentScore.code)!}" title="" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">Student name</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="${(studentScore.studentEntity.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkStudent" value="${(studentScore.studentEntity.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">curriculum</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="${(studentScore.sysdicEntity.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkCource" value="${(studentScore.sysdicEntity.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">Teacher</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="${(studentScore.userEntity.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkEmployee" value="${(studentScore.userEntity.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">class</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="${(studentScore.classInfoEntity.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkClassinfo" value="${(studentScore.classInfoEntity.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">examination Type</label>
                        <div class="col-xs-9 col-md-9">
                            <#--<input class="form-control" value="${(studentScore.testTypeEntity.caption)!}" title="" type="text">-->
                            <#--<input class="form-control" name="sc.pkPlans" value="${(studentScore.testTypeEntity.id)!}" title="" type="hidden">-->
                            <select name="sc.pkPlans">
                                <option value="">Please choose</option>
                                <#if testPlansType??>
                                    <#list testPlansType as t>
                                        <option  value="${(t.pkSysDictValues)!}" <#if t.pkSysDictValues?? &&studentScore.pkPlans?? && t.pkSysDictValues==studentScore.pkPlans>selected</#if>>${(t.caption)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">exam name</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control"  value="${(studentScore.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkStudentTestPlans" value="${(studentScore.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">achievement</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" name="sc.scores" value="${(studentScore.scores)!}" title="" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">weight</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" name="sc.weight" value="${(studentScore.weight)!}" title="" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">Particular year</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly name="sc.yearTime" value="${(studentScore.year)!}" title="" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">Semester</label>
                        <div class="col-xs-9 col-md-9">
                            <#--<input class="form-control" name="sc.term" value="${(studentScore.term)!}" title="" type="text">-->
                            <select name="sc.term" class="form-control">
                                <option value="">Please choose</option>
                                <#if terms ??>
                                    <#list terms as t >
                                        <option value="${(t.pkSysDictValues)!}" <#if studentScore.term?? &&t.pkSysDictValues?? && t.pkSysDictValues==studentScore.term >selected</#if>>${(t.caption)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-12">
                        <div class="form-group">
                            <label class="control-label  col-xs-1 col-md-1">Remarks</label>
                            <div class="col-xs-11 col-md-11">
                                <textarea rows="2" name="sc.memo"  class="form-control" title="">${(studentScore.memo)!}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group ">
                            <label class="control-label  col-xs-3 col-md-3">Founder</label>
                            <div class="col-xs-9 col-md-9 ">
                                <input readonly class="form-control" value="${(studentScore.creator)!}"
                                       type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Creation time</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly class="form-control"
                                       value="${(studentScore.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                       type="text">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group ">
                            <label class="control-label col-xs-3 col-md-3">Modifier</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly class="form-control" value="${(studentScore.modifier)!}"
                                       type="text">
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-6">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">Modified</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly class="form-control"
                                       value="${(studentScore.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                       type="text">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                <#--<button type="submit" class="btn btn-success btn-sm">Determine</button>-->
                    <input class="btn	btn-success btn-sm"  name="dosubmit" value="Determine" type="submit">
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